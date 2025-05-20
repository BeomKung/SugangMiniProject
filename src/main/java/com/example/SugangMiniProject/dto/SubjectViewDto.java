package com.example.SugangMiniProject.dto;

import com.example.SugangMiniProject.Entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;


@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class SubjectViewDto {
    private Subject subject;
    private boolean enrolled;
    private int enrolledCount;
    public String getFormattedStartTime() {
        if (subject.getStartTime() == null) return "-";
        return subject.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getFormattedEndTime() {
        if (subject.getEndTime() == null) return "-";
        return subject.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public Long getId() {
        return subject.getId();
    }

    public String getCourseType() {
        return subject.getCourseType();
    }

    public String getDepartment() {
        return subject.getDepartment();
    }

    public String getGrade() {
        return subject.getGrade();
    }

    public String getSubjectCode() {
        return subject.getSubjectCode();
    }

    public String getSubjectName() {
        return subject.getSubjectName();
    }

    public String getProfessor() {
        return subject.getProfessor();
    }

    public String getWeekday() {
        return subject.getWeekday();
    }

    public int getCredit() {
        return subject.getCredit();
    }

    public int getCapacity() {
        return subject.getCapacity();
    }

    public String getPlan() {
        return subject.getPlan();
    }

    public String getSyllabusFile() {
        return subject.getSyllabusFile();
    }

}
