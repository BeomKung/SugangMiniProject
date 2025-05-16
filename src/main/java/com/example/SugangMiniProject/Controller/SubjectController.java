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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    // 강의 등록
    @GetMapping("/admin/add_lecture")
    public String showAddLectureForm(Model model) {
        // 나중에 Subject 등록 폼용 model 속성 추가 가능
        return "admin/add_lecture";  // templates/admin/add_lecture.mustache
    }

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


    @GetMapping("/admin/lecture/edit/{id}")
    public String showEditLecture(@PathVariable Long id, Model model) {
        System.out.println("넘어온 id = " + id);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의 없음: " + id));
        model.addAttribute("subject", subject);
        return "admin/edit_lecture";
    }

    @PostMapping("/admin/lecture/edit/{id}")
    public String updateLecture(@PathVariable Long id, @ModelAttribute Subject updated) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 강의가 없습니다: " + id));

        // 필드 업데이트
        subject.setCourseType(updated.getCourseType());
        subject.setDepartment(updated.getDepartment());
        subject.setGrade(updated.getGrade());
        subject.setLectureCode(updated.getLectureCode());
        subject.setSubjectCode(updated.getSubjectCode());
        subject.setSubjectName(updated.getSubjectName());
        subject.setProfessor(updated.getProfessor());
        subject.setSchedule(updated.getSchedule());
        subject.setCredit(updated.getCredit());

        subjectRepository.save(subject);
        return "redirect:/admin/lecture_index";
    }

    @PostMapping("/admin/lecture/delete/{id}")
    public String deleteLecture(@PathVariable Long id) {
        subjectRepository.deleteById(id);
        return "redirect:/admin/lecture_index";
    }
}
