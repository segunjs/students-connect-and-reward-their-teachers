package com.decagon.rewardyourteacherapi11bjavapodf2.controller;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.TeacherRegistrationDto;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.UserDto;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.AuthService;
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


}
