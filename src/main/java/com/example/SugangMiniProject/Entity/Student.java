package com.example.SugangMiniProject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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
}
