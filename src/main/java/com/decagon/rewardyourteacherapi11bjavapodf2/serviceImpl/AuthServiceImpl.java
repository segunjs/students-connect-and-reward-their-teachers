package com.decagon.rewardyourteacherapi11bjavapodf2.serviceImpl;

import com.decagon.rewardyourteacherapi11bjavapodf2.dto.*;
import com.decagon.rewardyourteacherapi11bjavapodf2.enums.Role;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.OAuth2AuthenticationException;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserAlreadyExistException;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserNotFoundException;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.Subject;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.Teacher;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.User;
import com.decagon.rewardyourteacherapi11bjavapodf2.repository.SubjectRepository;
import com.decagon.rewardyourteacherapi11bjavapodf2.repository.UserRepository;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.JwtUtil;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;



    @Override
    public ApiResponse<PrincipalDto> login(LoginDTO loginDTO) {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new UserNotFoundException("invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User loggedInUser = userRepository.findUserByEmail(loginDTO.getEmail()).get();
        return new ApiResponse<>("success" , LocalDateTime.now() , new PrincipalDto( loggedInUser.getId() , loggedInUser.getName() ,  loggedInUser.getEmail(), loggedInUser.getRole(), jwtUtil.generateToken(loginDTO.getEmail())));

    }

    @Override
    public ApiResponse<PrincipalDto> authenticateOAuth2User(OAuth2UserInfo auth2UserInfo) {
        User user = userRepository.findUserByEmail(auth2UserInfo.getEmail()).orElseThrow(() ->
                new OAuth2AuthenticationException("Email not Found " + auth2UserInfo.getEmail())); //TODO
        String token = "Bearer " + jwtUtil.generateToken(auth2UserInfo.getEmail());
        return new ApiResponse<>("success", LocalDateTime.now(), new PrincipalDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), jwtUtil.generateToken(auth2UserInfo.getEmail())));
    }

}
