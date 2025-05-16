package com.example.SugangMiniProject.Service;

import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String studentNumber) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new UsernameNotFoundException("학생을 찾을 수 없습니다."));
        return new StudentDetails(student);
    }
}
