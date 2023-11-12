package com.moing.bo.backend.domain.admin.application.service;

import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.exception.NotApprovedException;
import com.moing.bo.backend.domain.admin.exception.PasswordInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationUserCase {
    public void checkValidPw(String inputPw, String originalPw, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(inputPw, originalPw))
            throw new PasswordInvalidException();
    }

    public void checkApproved(Admin admin) {
        if (!admin.isApproved())
            throw new NotApprovedException();
    }
}
