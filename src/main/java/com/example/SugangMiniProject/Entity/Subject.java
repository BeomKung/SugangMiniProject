package com.example.SugangMiniProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

    @DateTimeFormat(pattern = "HH:mm")
    @Column(columnDefinition = "TIME")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(columnDefinition = "TIME")
    private LocalTime endTime;

    @Column
    private int credit;

    @Column
    private int capacity;

    @Column(nullable = false, length = 1000)
    private String plan; //강의 개요

    @Column(nullable = true)
    private String syllabusFile; // 강의 계획서

    @Transient
    private MultipartFile uploadedSyllabusFile; // 실제 업로드된 파일 (DB 저장 X)

}