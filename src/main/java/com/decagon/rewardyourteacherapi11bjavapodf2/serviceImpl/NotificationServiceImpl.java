package com.decagon.rewardyourteacherapi11bjavapodf2.serviceImpl;

import com.decagon.rewardyourteacherapi11bjavapodf2.enums.NotificationType;
import com.decagon.rewardyourteacherapi11bjavapodf2.exceptions.UserNotFoundException;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.Notification;
import com.decagon.rewardyourteacherapi11bjavapodf2.model.User;
import com.decagon.rewardyourteacherapi11bjavapodf2.repository.NotificationRepository;
import com.decagon.rewardyourteacherapi11bjavapodf2.repository.UserRepository;
import com.decagon.rewardyourteacherapi11bjavapodf2.response.ApiResponse;
import com.decagon.rewardyourteacherapi11bjavapodf2.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor

public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;


    public Notification fundStudentWalletNotification(Long id, BigDecimal amount){
        User student = userRepository.findById(id).get();
        String message = String.format("You funded your wallet with ₦%.2f", amount);
        Notification notification =  new Notification();
        notification.setNotificationBody(message);
        notification.setUser(student);
        notification.setNotificationType(NotificationType.FUND_PERSONAL_WALLET);
        return notificationRepository.save(notification);
    }

    public ApiResponse<Notification> appreciationNotification(Long senderId, Long receiverId) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> receiver = userRepository.findById(receiverId);
        Notification notification = new Notification();
        if (sender.isPresent()) {
            notification.setNotificationBody(String.format("%s appreciated you", sender.get().getName()));
            notification.setUser(receiver.orElseThrow(() -> new UserNotFoundException(String.format("Receiver with id %d not found",receiverId))));
            notification.setNotificationType(NotificationType.APPRECIATION);
            notificationRepository.save(notification);
        }else{
            throw new UserNotFoundException(String.format("Receiver with id %d not found", senderId));
        }
        return new ApiResponse<>("Notification sent successfully", LocalDateTime.now(), notification);
    }

    public Notification successfulWithdrawNotification(Long userId, BigDecimal amount) {
        User teacher = userRepository.findById(userId).get();
        String message = String.format("You have successfully withdrawn ₦%.2f", amount);
        Notification notification = new Notification();
        notification.setNotificationBody(message);
        notification.setUser(teacher);
        notification.setNotificationType(NotificationType.SUCCESSFUL_WITHDRAW);
        return notificationRepository.save(notification);
    }

    public Notification receiveFundsNotification(Long receiverId, BigDecimal amount){
        User teacher = userRepository.findById(receiverId).get();
        String message = "You received " + amount + " from ";
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.RECEIVE_FUNDS);
        notification.setUser(teacher);
        notification.setNotificationBody(message);
        return notification;
    }

    public Notification sendFundsNotification(Long senderId, BigDecimal amount){
        User student = userRepository.findById(senderId).get();
        String message = "You sent " + amount + " to ";
        Notification notification = new Notification();
        notification.setNotificationType(NotificationType.TRANSFER_FUNDS);
        notification.setUser(student);
        notification.setNotificationBody(message);
        return notification;
    }

    public void TransferNotification(Long senderTransactionId, Long receiverTransactionId, BigDecimal amount) {
        Notification receiverNotification = receiveFundsNotification(receiverTransactionId, amount);
        Notification senderNotification = sendFundsNotification(senderTransactionId, amount);
        receiverNotification.setNotificationBody(receiverNotification.getNotificationBody() + senderNotification.getUser().getName());
        senderNotification.setNotificationBody(senderNotification.getNotificationBody() + receiverNotification.getUser().getName());
        notificationRepository.save(receiverNotification);
        notificationRepository.save(senderNotification);
    }


}
