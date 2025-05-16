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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    // 강의 목록 조회 / 수정
//    @GetMapping("/admin/lecture_index")
//    public String showLectureIndex(Model model) {
//        // 나중에 lecture 목록을 model에 담을 수 있음
//        return "admin/lecture_index";  // templates/admin/lecture_index.mustache
//    }

    // 학생 목록 조회 / 수정
    @GetMapping("/admin/student_index")
    public String showStudentIndex(Model model) {
        // 나중에 student 목록을 model에 담을 수 있음
        return "admin/student_index";  // templates/admin/student_index.mustache
    }

    // 강의 등록
    @GetMapping("/admin/add_lecture")
    public String showAddLectureForm(Model model) {
        // 나중에 Subject 등록 폼용 model 속성 추가 가능
        return "admin/add_lecture";  // templates/admin/add_lecture.mustache
    }

    // 학생 등록
    @GetMapping("/admin/add_student")
    public String showAddStudentForm(Model model) {
        // 나중에 Student 등록 폼용 model 속성 추가 가능
        return "admin/add_student";  // templates/admin/add_student.mustache
    }

}
