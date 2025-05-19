package com.example.SugangMiniProject.Service;

import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public boolean isDuplicateLecture(Subject subject) {
        return subjectRepository.existsBySubjectCodeAndProfessorAndWeekdayAndStartTimeAndEndTime(
                subject.getSubjectCode(),
                subject.getProfessor(),
                subject.getWeekday(),
                subject.getStartTime(),
                subject.getEndTime()
        );
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
}
