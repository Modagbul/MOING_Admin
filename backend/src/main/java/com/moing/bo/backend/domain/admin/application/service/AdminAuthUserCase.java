package com.moing.bo.backend.domain.admin.application.service;

import com.moing.bo.backend.domain.admin.application.dto.request.SignInRequest;
import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminAuthUserCase {

    private final AdminGetService adminGetService;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUserCase validationUserCase;

    @Transactional
    public Admin auth(SignInRequest signInRequest) {
        Admin admin = adminGetService.getAdminByLoginId(signInRequest.getId());
        validationUserCase.checkValidPw(signInRequest.getPassword(), admin.getPassword(), passwordEncoder);
        validationUserCase.checkApproved(admin);
        return admin;
    }

}
