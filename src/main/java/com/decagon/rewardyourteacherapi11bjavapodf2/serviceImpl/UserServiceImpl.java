package com.decagon.rewardyourteacherapi11bjavapodf2.serviceImpl;

import com.decagon.rewardyourteacherapi11bjavapodf2.dto.OAuth2UserInfo;
import com.decagon.rewardyourteacherapi11bjavapodf2.enums.Role;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserAlreadyExistException;
import com.decagon.rewardyourteacherapi11bjavapodf2.listeners.UserLogoutSuccessListener;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.Teacher;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.User;
import com.decagon.rewardyourteacherapi11bjavapodf2.event.OnUserLogoutSuccessEvent;
import com.decagon.rewardyourteacherapi11bjavapodf2.repository.UserRepository;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.JwtUtil;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    public ApiResponse getOAuth2Teacher(OAuth2UserInfo oAuth2UserInfo) {
        Optional<User> existingTeacher = userRepository.findUserByEmail(oAuth2UserInfo.getEmail());
        if(existingTeacher.isPresent()){
            throw new UserAlreadyExistException("Teacher already exist");
        }
        Teacher teacher = new Teacher();
        teacher.setName(oAuth2UserInfo.getName());
        teacher.setEmail(oAuth2UserInfo.getEmail());
        teacher.setRole(Role.TEACHER);
        userRepository.save(teacher);
        return new ApiResponse<>("Google authentication successful, kindly fill in other details", LocalDateTime.now());
    }

    @Override
    public ApiResponse registerOAuth2Student(OAuth2UserInfo oAuth2UserInfo) {
        Optional<User> existingStudent = userRepository.findUserByEmail(oAuth2UserInfo.getEmail());
        if(existingStudent.isPresent()){
            throw new UserAlreadyExistException("Student already exist");
        }
        User student = new User();
        student.setName(oAuth2UserInfo.getName());
        student.setEmail(oAuth2UserInfo.getEmail());
        student.setRole(Role.STUDENT);
        userRepository.save(student);
        return new ApiResponse<>("account successfully created", LocalDateTime.now());
    }
    @Override
    public ApiResponse<String> logout(CustomUserDetails activeUser, String userToken) {

        String token = userToken.substring(7);
        UserLogoutSuccessListener successListener = new UserLogoutSuccessListener(activeUser.getUsername(), token);
        applicationEventPublisher.publishEvent(successListener);
        String response = activeUser.getUsername() + " was successfully logged out";

        return new ApiResponse<>("success", LocalDateTime.now(), response);
    }

   @Override
   public ApiResponse<String> logoutUser(CustomUserDetails currentUser, String bearerToken){
        String token = bearerToken.substring(7);
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(currentUser.getUsername(), token);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        String response = currentUser.getUsername() + " has successfully logged out from the system!";
        return new ApiResponse<>("success",LocalDateTime.now(), response);
    }


}
