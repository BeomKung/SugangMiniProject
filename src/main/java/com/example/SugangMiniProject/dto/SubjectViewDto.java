package com.example.SugangMiniProject.dto;

import com.example.SugangMiniProject.Entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class SubjectViewDto {
    private Subject subject;
    private boolean enrolled;


}
