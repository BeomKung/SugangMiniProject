package com.example.SugangMiniProject.Repository;

import com.example.SugangMiniProject.Entity.Enrollment;
import com.example.SugangMiniProject.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.subject = :subject")
    int countBySubject(@Param("subject") Subject subject);
}
