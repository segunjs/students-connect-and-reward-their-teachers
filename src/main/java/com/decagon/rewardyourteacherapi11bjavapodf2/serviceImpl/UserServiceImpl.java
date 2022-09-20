package com.decagon.rewardyourteacherapi11bjavapodf2.serviceImpl;

import com.decagon.rewardyourteacherapi11bjavapodf2.listeners.UserLogoutSuccessListener;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public ApiResponse<String> logout(CustomUserDetails activeUser, String userToken) {

        String token = userToken.substring(7);
        UserLogoutSuccessListener successListener = new UserLogoutSuccessListener(activeUser.getUsername(), token);
        applicationEventPublisher.publishEvent(successListener);
        String response = activeUser.getUsername() + " was successfully logged out";

        return new ApiResponse<>("success", LocalDateTime.now(), response);
    }
}
