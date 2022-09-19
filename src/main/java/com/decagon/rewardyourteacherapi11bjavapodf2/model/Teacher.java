package com.decagon.rewardyourteacherapi11bjavapodf2.model;


import com.decagon.rewardyourteacherapi.enums.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends User{

    private String teachingPeriod;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;



    private  String teacherIdUrl;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subject;


}
