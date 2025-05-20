package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.AdminRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import com.example.SugangMiniProject.Service.SubjectService;
import com.example.SugangMiniProject.dto.SubjectViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectService subjectService;


    // 강의 등록
    @GetMapping("/admin/add_lecture")
    public String showAddLectureForm(Model model) {
        // 나중에 Subject 등록 폼용 model 속성 추가 가능
        return "admin/add_lecture";  // templates/admin/add_lecture.mustache
    }

    //강의 등록
    @PostMapping("/admin/add_lecture")
    public String addLecture(@ModelAttribute Subject subject,
                             RedirectAttributes redirectAttributes) {

        // 중복 체크
        if (subjectService.isDuplicateLecture(subject)) {
            redirectAttributes.addFlashAttribute("error", " 동일한 과목코드/교수/시간 조합이 이미 존재합니다.");
            return "redirect:/admin/lecture_index";
        }

        // 파일 업로드 (syllabusFile은 선택사항)
        MultipartFile syllabusFile = subject.getUploadedSyllabusFile();
        if (syllabusFile != null && !syllabusFile.isEmpty()) {
            try {
                // 운영체제에 따라 수정 가능
                String uploadDir = "C:/Users/user/Desktop/upload/";
                String fileName = UUID.randomUUID() + "_" + syllabusFile.getOriginalFilename();
                File saveFile = new File(uploadDir, fileName);
                syllabusFile.transferTo(saveFile);

                // DB에는 상대 경로 저장
                subject.setSyllabusFile("/uploads/" + fileName);

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", " 강의계획서 업로드 중 오류가 발생했습니다.");
                return "redirect:/admin/lecture_index";
            }
        }

        // 저장
        subjectService.save(subject);
        redirectAttributes.addFlashAttribute("msg", " 과목이 성공적으로 등록되었습니다.");
        return "redirect:/admin/lecture_index";
    }

    //강의 조회
    @GetMapping("/admin/lecture_index")
    public String showLectureIndex(Model model) {
        List<Subject> subjects = subjectRepository.findAll();

        List<SubjectViewDto> subjectDtos = subjects.stream().map(subject -> {
            SubjectViewDto dto = new SubjectViewDto();
            dto.setSubject(subject);
            dto.setEnrolled(false); // 관리자 화면에서는 사용 X
            return dto;
        }).toList();

        model.addAttribute("subjects", subjectDtos);
        return "admin/lecture_index";
    }


    @GetMapping("/admin/lecture/edit/{id}")
    public String showEditLecture(@PathVariable Long id, Model model) {
        System.out.println("넘어온 id = " + id);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의 없음: " + id));
        model.addAttribute("subjects", subject);
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
        subject.setSubjectCode(updated.getSubjectCode());
        subject.setSubjectName(updated.getSubjectName());
        subject.setProfessor(updated.getProfessor());
        subject.setWeekday(updated.getWeekday());
        subject.setStartTime(updated.getStartTime());
        subject.setEndTime(updated.getEndTime());
        subject.setCredit(updated.getCredit());
        subject.setCapacity(updated.getCapacity());

        subjectRepository.save(subject);
        return "redirect:/admin/lecture_index";
    }

    @PostMapping("/admin/lecture/delete/{id}")
    public String deleteLecture(@PathVariable Long id) {
        subjectRepository.deleteById(id);
        return "redirect:/admin/lecture_index";
    }
}
