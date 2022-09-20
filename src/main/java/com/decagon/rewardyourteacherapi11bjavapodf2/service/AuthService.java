package com.decagon.rewardyourteacherapi11bjavapodf2.service;



import com.decagon.rewardyourteacherapi11bjavapodf2.dto.LoginDTO;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.PrincipalDto;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.TeacherRegistrationDto;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.UserDto;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.*;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import org.springframework.http.ResponseEntity;


import java.io.IOException;

public interface AuthService {

    UserRegistrationResponse registerUser(UserDto userDto);
    UserRegistrationResponse registerTeacher(TeacherRegistrationDto teacherDto) throws IOException;


    ApiResponse<PrincipalDto> login(LoginDTO loginDTO);

    ApiResponse<PrincipalDto> authenticateOAuth2User(OAuth2UserInfo auth2UserInfo);

}
