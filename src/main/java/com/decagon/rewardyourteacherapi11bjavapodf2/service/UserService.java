package com.decagon.rewardyourteacherapi11bjavapodf2.service;

<<<<<<< HEAD
import com.decagon.rewardyourteacherapi11bjavapodf2.dto.OAuth2UserInfo;
=======
import com.decagon.rewardyourteacherapi11bjavapodf2.pojos.OAuth2UserInfo;
>>>>>>> origin/develop
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;

public interface UserService {


    ApiResponse getOAuth2Teacher(OAuth2UserInfo oAuth2UserInfo);

    ApiResponse registerOAuth2Student(OAuth2UserInfo oAuth2UserInfo);

    ApiResponse<String> logout(CustomUserDetails activeUser, String token);
    ApiResponse<String> logoutUser(CustomUserDetails currentUser, String bearerToken);

}
