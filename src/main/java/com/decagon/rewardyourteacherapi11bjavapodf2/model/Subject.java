package com.decagon.rewardyourteacherapi11bjavapodf2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseClass{
    private String nameOfSubject;

    @ManyToOne
    @JoinColumn(name = "teacher_id" , referencedColumnName = "id")
    private Teacher teacher;



}
