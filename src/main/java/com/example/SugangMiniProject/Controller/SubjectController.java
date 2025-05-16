package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.AdminRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    //강의 등록
    @PostMapping("/admin/add_lecture")
    public String addLecture(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/admin/lecture_index";
    }

    //강의 조회
    @GetMapping("/admin/lecture_index")
    public String showLectureIndex(Model model) {
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);
        return "admin/lecture_index";
    }



}
