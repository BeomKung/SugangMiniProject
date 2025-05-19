package com.example.SugangMiniProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String courseType;

    @Column
    private String department;

    @Column
    private String grade;

    @Column
    private String subjectCode;

    @Column
    private String subjectName;

    @Column
    private String professor;

    @Column
    private String weekday;

    @Column(columnDefinition = "TIME")
    private String startTime;

    @Column(columnDefinition = "TIME")
    private String endTime;

    @Column
    private int credit;

    @Column
    private int capacity;



}