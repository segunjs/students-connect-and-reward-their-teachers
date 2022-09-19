package com.decagon.rewardyourteacherapi11bjavapodf2.model;


import com.decagon.rewardyourteacherapi11bjavapodf2.enums.SchoolType;
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
@DiscriminatorValue(value = "Teacher")
public class Teacher extends User{

    private String yearsOfService;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    private  String valid_id;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subject;


}
