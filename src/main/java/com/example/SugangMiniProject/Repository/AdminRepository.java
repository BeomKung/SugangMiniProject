package com.example.SugangMiniProject.Repository;

import com.example.SugangMiniProject.Entity.Admin;
import com.example.SugangMiniProject.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByStudentNumber(String studentNumber);
}
