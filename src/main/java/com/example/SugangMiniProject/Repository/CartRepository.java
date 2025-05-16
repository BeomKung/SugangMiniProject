package com.example.SugangMiniProject.Repository;

import com.example.SugangMiniProject.Entity.Cart;
import com.example.SugangMiniProject.Entity.Student;
import com.example.SugangMiniProject.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStudent(Student student);
    boolean existsByStudentAndSubject(Student student, Subject subject);
    void deleteByStudentAndSubject(Student student, Subject subject);

}
