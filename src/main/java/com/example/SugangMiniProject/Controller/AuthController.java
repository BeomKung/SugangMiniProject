package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Service.StudentDetails;
import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;

@Controller
@RequiredArgsConstructor
public class AuthController {
    //기본 컨트롤러
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole("STUDENT");
        studentRepository.save(student);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Authentication auth) {
        if (auth == null) return "redirect:/login";

        String role = auth.getAuthorities().iterator().next().getAuthority(); // ROLE_ADMIN, ROLE_STUDENT

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/student/dashboard";
        }
    }

}
