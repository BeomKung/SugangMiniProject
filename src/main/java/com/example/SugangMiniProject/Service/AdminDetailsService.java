package com.example.SugangMiniProject.Service;

import com.example.SugangMiniProject.Entity.Admin;
import com.example.SugangMiniProject.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByStudentNumber(username) // ✅ 변경: findByUsername → findByStudentNumber
                .orElseThrow(() -> new UsernameNotFoundException("관리자 정보를 찾을 수 없습니다."));
        return new AdminDetails(admin);
    }
}