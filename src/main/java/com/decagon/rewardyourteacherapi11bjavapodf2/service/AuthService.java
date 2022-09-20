package com.decagon.rewardyourteacherapi11bjavapodf2.service;


import com.decagon.rewardyourteacherapi11bjavapodf2.dto.*;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.UserRegistrationResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

    ApiResponse<PrincipalDto> login(LoginDTO loginDTO);

    ApiResponse<PrincipalDto> authenticateOAuth2User(OAuth2UserInfo auth2UserInfo);
}
