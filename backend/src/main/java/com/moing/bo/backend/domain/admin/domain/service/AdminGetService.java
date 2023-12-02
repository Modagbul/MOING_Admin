package com.moing.bo.backend.domain.admin.domain.service;

import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.domain.repository.AdminRepository;
import com.moing.bo.backend.domain.admin.exception.LogInIdInvalidException;
import com.moing.bo.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AdminGetService {

    private final AdminRepository adminRepository;

    public Admin getAdminByLoginId(String logInId) {
        return adminRepository.findAdminByLoginId(logInId)
                .orElseThrow(LogInIdInvalidException::new);
    }

    public boolean isDuplicatedId(String logInId) {
        return adminRepository.findAdminByLoginId(logInId).isPresent();
    }
}
