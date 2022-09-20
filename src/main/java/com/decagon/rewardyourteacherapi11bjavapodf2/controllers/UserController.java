package com.decagon.rewardyourteacherapi11bjavapodf2.controllers;

<<<<<<< HEAD
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CurrentUser;
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
=======
import com.decagon.rewardyourteacherapi11bjavapodf2.security.CustomUserDetails;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.CurrentUser;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
>>>>>>> origin/develop
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import static org.springframework.http.HttpStatus.OK;
>>>>>>> origin/develop

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CurrentUser CustomUserDetails currentUser, @RequestHeader("Authorization") String bearToken){
        return new ResponseEntity<>(userService.logout(currentUser, bearToken), HttpStatus.OK);
    }

}
