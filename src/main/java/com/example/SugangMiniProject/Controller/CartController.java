package com.example.SugangMiniProject.Controller;

import com.example.SugangMiniProject.Entity.Cart;
import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import com.example.SugangMiniProject.Repository.CartRepository;
import com.example.SugangMiniProject.Repository.SubjectRepository;
import com.example.SugangMiniProject.Service.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/student/cart/add/{subjectId}")
    public String addToCart(@PathVariable Long subjectId, Authentication auth, RedirectAttributes redirectAttrs) {
        Student student = ((StudentDetails) auth.getPrincipal()).getStudent();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 과목이 없습니다."));

        if (cartRepository.existsByStudentAndSubject(student, subject)) {
            redirectAttrs.addFlashAttribute("message", "이미 장바구니에 있는 과목입니다.");
        } else {
            Cart cart = new Cart();
            cart.setStudent(student);
            cart.setSubject(subject);
            cartRepository.save(cart);
            redirectAttrs.addFlashAttribute("message", "장바구니에 추가되었습니다.");
        }

        return "redirect:/student/dashboard";
    }

    //장바구니 조회
    @GetMapping("/student/cart")
    public String viewCart(Model model, Authentication auth) {
        Student student = ((StudentDetails) auth.getPrincipal()).getStudent();
        List<Cart> cartList = cartRepository.findByStudent(student);

        model.addAttribute("cartItems", cartList);
        return "student/cart";  // templates/student/cart.mustache
    }
}
