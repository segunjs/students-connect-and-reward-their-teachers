package com.decagon.rewardyourteacherapi11bjavapodf2.controllers;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.*;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserNotFoundException;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.AuthService;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO authRequest) throws UserNotFoundException {
        ApiResponse<?> apiResponse = authService.login(authRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/oauth2/login/callback")
    public ApiResponse<PrincipalDto> authenticateOAuth2User(@RequestBody OAuth2UserInfo principal){
        return authService.authenticateOAuth2User(principal);
    }

    @PostMapping(value = "/register/student")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserDto userDto) {
        UserRegistrationResponse userRegistrationResponse = authService.registerUser(userDto);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/register/teacher")
    public ResponseEntity<UserRegistrationResponse> registerTeacher(@RequestBody TeacherRegistrationDto teacherDto) throws IOException {
        UserRegistrationResponse userRegistrationResponse = authService.registerTeacher(teacherDto);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/register/teacher/callback")
    public ResponseEntity<ApiResponse> getTeacherInfoFromGoogle(@RequestBody OAuth2UserInfo principal){
        ApiResponse apiResponse = userService.getOAuth2Teacher(principal);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    @PostMapping("/register/student/callback")
    public ResponseEntity<ApiResponse> registerStudent(@RequestBody OAuth2UserInfo principal){
        ApiResponse apiResponse = userService.registerOAuth2Student(principal);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


}
