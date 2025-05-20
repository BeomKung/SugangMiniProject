package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.StudentRepository;
import com.example.SugangMiniProject.Service.StudentDetails;
import com.example.SugangMiniProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalTime;

@Controller
public class TimetableController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("/article/timetable")
    public String showTimetable(Model model, Authentication authentication) {
        Long studentId = ((StudentDetails) authentication.getPrincipal()).getStudent().getId();
        List<Subject> subjects = studentService.getSubjectsWithTimetable(studentId);

        List<Map<String, Object>> timeSlots = new ArrayList<>();
        List<String> days = List.of("월", "화", "수", "목", "금");
        List<LocalTime> timeRange = List.of(
                LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0),
                LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(14, 0),
                LocalTime.of(15, 0), LocalTime.of(16, 0)
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (LocalTime time : timeRange) {
            Map<String, Object> row = new HashMap<>();
            row.put("time", time.format(formatter));

            for (String day : days) {
                subjects.stream()
                        .filter(s -> s.getWeekday().equals(day))
                        .filter(s -> !s.getStartTime().isAfter(time) && s.getEndTime().isAfter(time))
                        .findFirst()
                        .ifPresent(subject -> {
                            Map<String, String> subjectInfo = new HashMap<>();
                            subjectInfo.put("subjectName", subject.getSubjectName());
                            subjectInfo.put("startTime", subject.getStartTime().format(formatter));
                            subjectInfo.put("endTime", subject.getEndTime().format(formatter));
                            row.put(dayKey(day), subjectInfo);
                        });
            }

            timeSlots.add(row);
        }

        model.addAttribute("timeSlots", timeSlots);
        return "article/timetable";
    }

    private String dayKey(String day) {
        return switch (day) {
            case "월" -> "mon";
            case "화" -> "tue";
            case "수" -> "wed";
            case "목" -> "thu";
            case "금" -> "fri";
            default -> "etc";
        };
    }


}