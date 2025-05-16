package com.example.SugangMiniProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 과목 고유 ID
    @Column
    private String courseType;     // 이수구분 (전공, 교양 등)
    @Column
    private String department;     // 개설학과
    @Column
    private String grade;          // 학년 (1~4, 석사 등)
    @Column
    private String lectureCode;    // 강좌번호
    @Column
    private String subjectCode;    // 과목코드
    @Column
    private String subjectName;    // 과목명
    @Column
    private String professor;      // 교수명
    @Column
    private String schedule;       // 스케줄 (예: "월 1-2교시")
    @Column
    private int credit;            // 학점
}
