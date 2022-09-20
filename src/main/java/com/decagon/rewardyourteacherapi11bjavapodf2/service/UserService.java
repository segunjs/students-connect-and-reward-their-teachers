package com.decagon.rewardyourteacherapi11bjavapodf2.service;

import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;

public interface UserService {

    ApiResponse<String> logout(CustomUserDetails activeUser, String token);
}
