package com.example.SugangMiniProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentNumber;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String major;

    @Column
    private String grade;

    @Column
    private String role = "STUDENT"; // STUDENT or ADMIN

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;



}
