package com.decagon.rewardyourteacherapi11bjavapodf2.repository;

import com.decagon.rewardyourteacherapi11bjavapodf2.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
