package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.AdminRepository;
import com.example.SugangMiniProject.Repository.StudentRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import com.example.SugangMiniProject.Service.StudentDetails;
import com.example.SugangMiniProject.Service.StudentService;
import com.example.SugangMiniProject.dto.SubjectViewDto;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentService studentService;


    @Autowired
    private SubjectRepository subjectRepository;
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; // templates/admin_dashboard.mustache
    }


    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model, Authentication authentication) {
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Student sessionStudent = studentDetails.getStudent();

        // 세션 말고 DB에서 다시 조회 (LazyInitializationException 방지)
        Student student = studentRepository.findByStudentNumberWithSubjects(sessionStudent.getStudentNumber())
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));

        List<Subject> allSubjects = subjectRepository.findAll();

        Set<String> enrolledSubjectCodes = student.getSubjects().stream()
                .map(Subject::getSubjectCode)
                .collect(Collectors.toSet());

        List<SubjectViewDto> subjectDtos = allSubjects.stream()
                .map(subject -> {
                    SubjectViewDto dto = new SubjectViewDto();
                    dto.setSubject(subject);
                    dto.setEnrolled(enrolledSubjectCodes.contains(subject.getSubjectCode()));
                    return dto;
                })
                .collect(Collectors.toList());

        model.addAttribute("subjectViews", subjectDtos);
        model.addAttribute("student", student);

        return "student_dashboard";
    }

    @GetMapping("/student/profile")
    public String studentProfilePage(Authentication authentication, Model model) {
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        String studentNumber = studentDetails.getStudent().getStudentNumber();

        Student student = studentService.findByStudentNumberWithSubjects(studentNumber);

        model.addAttribute("student", student);
        model.addAttribute("subjects", student.getSubjects());

        return "article/my_page";
    }


    @GetMapping("/student/profile/edit")
    public String editProfileForm(Model model, Authentication auth) {
        StudentDetails studentDetails = (StudentDetails) auth.getPrincipal();
        model.addAttribute("student", studentDetails.getStudent());
        return "edit_profile"; // edit_profile.mustache
    }

    @PostMapping("/student/profile/edit")
    public String updateProfile(@RequestParam String email,
                                @RequestParam String password,
                                Authentication auth) {
        StudentDetails studentDetails = (StudentDetails) auth.getPrincipal();
        Student student = studentDetails.getStudent();

        student.setEmail(email);
        student.setPassword(password); // NoOpPasswordEncoder or 암호화 필요
        studentRepository.save(student);

        return "redirect:/student/profile";
    }

    @PostMapping("/student/subject/cancel/{id}")
    public String cancelSubject(@PathVariable Long id, Authentication auth) {
        StudentDetails studentDetails = (StudentDetails) auth.getPrincipal();
        String studentNumber = studentDetails.getStudent().getStudentNumber();

        //세션에 연결된 Student 객체를 fetch join으로 다시 조회
        Student student = studentRepository.findByStudentNumberWithSubjects(studentNumber)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("과목을 찾을 수 없습니다."));

        // 수강 연결 해제
        student.getSubjects().remove(subject);

        studentRepository.save(student); // student_subject 테이블에서 제거됨

        return "redirect:/student/profile";
    }

    @PostMapping("/student/enroll")
    public String enrollSubject(@RequestParam("subjectId") Long subjectId, Authentication authentication) {
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Student student = studentDetails.getStudent();

        studentService.enrollSubject(student, subjectId);

        return "redirect:/student/dashboard";
    }



    @GetMapping("/admin/student_index")
    public String showStudentIndex(Model model) {
        List<Student> students = studentRepository.findAll();  // 전체 학생 목록 가져오기
        model.addAttribute("students", students);
        return "admin/student_index";
    }


    // 학생 등록
    @GetMapping("/admin/add_student")
    public String showAddStudentForm(Model model) {
        // 나중에 Student 등록 폼용 model 속성 추가 가능
        return "admin/add_student";
    }

    // 학생 수정 화면
    @GetMapping("/admin/student/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. id=" + id));
        model.addAttribute("student", student);
        return "admin/edit_student";
    }

    // 학생 수정 처리
    @PostMapping("/admin/student/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. id=" + id));

        // 관리자 수정 가능한 항목만 업데이트
        student.setStudentNumber(updatedStudent.getStudentNumber());
        student.setMajor(updatedStudent.getMajor());
        student.setGrade(updatedStudent.getGrade());

        studentRepository.save(student);

        return "redirect:/admin/student_index";
    }

    @PostMapping("/admin/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/admin/student_index";
    }

}
