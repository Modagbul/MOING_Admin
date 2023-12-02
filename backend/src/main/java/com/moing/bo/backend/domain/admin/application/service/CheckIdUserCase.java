package com.moing.bo.backend.domain.admin.application.service;

import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CheckIdUserCase {

    private final AdminGetService adminGetService;
    public boolean checkId(String logInId){
        return adminGetService.isDuplicatedId(logInId);
    }
}
