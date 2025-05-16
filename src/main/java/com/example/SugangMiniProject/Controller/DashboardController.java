package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.AdminRepository;
import com.example.SugangMiniProject.Repository.StudentRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import com.example.SugangMiniProject.Service.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; // templates/admin_dashboard.mustache
    }


    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model, Authentication authentication) {
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Student student = studentDetails.getStudent();

        List<Subject> subjects = subjectRepository.findAll(); // 모든 과목 조회 (필터링은 나중에)
        model.addAttribute("student", student);
        model.addAttribute("subjects", subjects);

        return "student_dashboard"; // student_dashboard.mustache
    }

    @GetMapping("/student/profile") //학생 상세정보 출력
    public String studentProfilePage(Authentication authentication, Model model) {
        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Student student = studentDetails.getStudent();

        model.addAttribute("student", student);
        return "student_profile"; // templates/student_profile.mustache
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
