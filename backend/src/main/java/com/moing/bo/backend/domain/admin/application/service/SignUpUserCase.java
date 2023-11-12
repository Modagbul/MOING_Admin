package com.moing.bo.backend.domain.admin.application.service;

import com.moing.bo.backend.domain.admin.application.dto.request.SignUpRequest;
import com.moing.bo.backend.domain.admin.application.mapper.AdminMapper;
import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import com.moing.bo.backend.domain.admin.domain.service.AdminSaveService;
import com.moing.bo.backend.domain.admin.exception.DuplicatedIdException;
import com.moing.bo.backend.global.config.security.jwt.TokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpUserCase {

    private final AdminGetService adminGetService;
    private final AdminSaveService adminSaveService;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        //1. 아이디 중복 체크
        if (adminGetService.isDuplicatedId(signUpRequest.getId()))
            throw new DuplicatedIdException();
        //2. signUp 처리
        Admin admin = adminMapper.toAdmin(signUpRequest);
        admin.encodePassword(passwordEncoder.encode(admin.getPassword()));
        adminSaveService.saveAdmin(admin);
    }

}