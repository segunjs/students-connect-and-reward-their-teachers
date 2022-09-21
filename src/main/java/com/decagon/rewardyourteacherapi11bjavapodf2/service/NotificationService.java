package com.decagon.rewardyourteacherapi11bjavapodf2.service;

import com.decagon.rewardyourteacherapi11bjavapodf2.model.Notification;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;

import java.math.BigDecimal;

public interface NotificationService {


     Notification fundStudentWalletNotification(Long id, BigDecimal amount);
     ApiResponse<Notification> appreciationNotification(Long senderId, Long receiverId);
     Notification successfulWithdrawNotification(Long userId, BigDecimal amount);
     Notification receiveFundsNotification(Long receiverId, BigDecimal amount);
     Notification sendFundsNotification(Long senderId, BigDecimal amount);
     void TransferNotification(Long senderTransactionId, Long receiverTransactionId, BigDecimal amount);

}
