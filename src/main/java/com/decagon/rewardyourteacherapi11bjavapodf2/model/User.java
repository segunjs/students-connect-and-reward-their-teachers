package com.decagon.rewardyourteacherapi11bjavapodf2.model;


import com.decagon.rewardyourteacherapi.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public class User  extends BaseClass implements Serializable{
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactionList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Message> messageList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Notification> notificationList = new ArrayList<>();

    private String school;

    public User(Long id, LocalDateTime createDate, LocalDateTime updateDate, String name, Role role, String email, String password, List<Transaction> transactionList, List<Message> messageList, List<Notification> notificationList, String school) {
        super(id, createDate, updateDate);
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.transactionList = transactionList;
        this.messageList = messageList;
        this.notificationList = notificationList;
        this.school = school;
    }
}

