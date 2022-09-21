package com.decagon.rewardyourteacherapi11bjavapodf2.controllers;


import com.decagon.rewardyourteacherapi11bjavapodf2.model.Notification;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/fund-own-wallet")
    public String fundOwnAccountNotification(@RequestParam Long id, @RequestParam BigDecimal amount){
        Notification notification = notificationService.fundStudentWalletNotification( id, amount);
        return notification.getNotificationBody();
    }


}
