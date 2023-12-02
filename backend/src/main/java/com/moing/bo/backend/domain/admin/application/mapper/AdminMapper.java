package com.moing.bo.backend.domain.admin.application.mapper;

import com.moing.bo.backend.domain.admin.application.dto.request.SignUpRequest;
import com.moing.bo.backend.domain.admin.domain.constant.Role;
import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.team.domain.constant.ApprovalStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    public Admin toAdmin(SignUpRequest signUpRequest) {
        return Admin.builder()
                .loginId(signUpRequest.getId())
                .password(signUpRequest.getPassword())
                .role(Role.ADMIN)
                .approvedStatus(ApprovalStatus.NO_CONFIRMATION)
                .build();
    }
}
