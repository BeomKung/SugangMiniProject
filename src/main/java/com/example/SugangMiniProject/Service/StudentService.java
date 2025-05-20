package com.example.SugangMiniProject.Service;

import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.StudentRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public Student findByStudentNumberWithSubjects(String studentNumber) {
        return studentRepository.findByStudentNumberWithSubjects(studentNumber)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
    }
    @Transactional
    public void enrollSubject(Student loginStudent, Long subjectId) {
        // 세션에 묶인 student가 아니므로, DB에서 다시 조회
        Student student = studentRepository.findByStudentNumberWithSubjects(loginStudent.getStudentNumber())
                .orElseThrow(() -> new RuntimeException("학생 없음"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("과목 없음"));

        if (student.getSubjects().contains(subject)) {
            throw new IllegalStateException("이미 신청한 과목입니다.");
        }

        student.getSubjects().add(subject);
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Subject> getSubjectsWithTimetable(Long studentId) {
        Student student = studentRepository.findByIdWithSubjects(studentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));
        return student.getSubjects();
    }
}
