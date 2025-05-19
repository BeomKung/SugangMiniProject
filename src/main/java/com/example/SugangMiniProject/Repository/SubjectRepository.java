package com.example.SugangMiniProject.Repository;


import com.example.SugangMiniProject.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // 강의 등록 중복 검사 (과목코드 + 교수 + 요일 + 시간)
    boolean existsBySubjectCodeAndProfessorAndWeekdayAndStartTimeAndEndTime(
            String subjectCode,
            String professor,
            String weekday,
            LocalTime startTime,
            LocalTime endTime
    );
}
