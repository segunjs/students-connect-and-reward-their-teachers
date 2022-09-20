package com.decagon.rewardyourteacherapi11bjavapodf2.controllers;
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.*;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserNotFoundException;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO authRequest) throws UserNotFoundException {
        ApiResponse<?> apiResponse = authService.login(authRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/oauth2/login/callback")
    public ApiResponse<PrincipalDto> authenticateOAuth2User(@RequestBody OAuth2UserInfo principal){
        return authService.authenticateOAuth2User(principal);
    }
}
