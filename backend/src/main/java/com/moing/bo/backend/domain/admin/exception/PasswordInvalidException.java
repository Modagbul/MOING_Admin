package com.moing.bo.backend.domain.admin.exception;

import org.springframework.http.HttpStatus;

import static com.moing.bo.backend.global.response.ErrorCode.INVALID_PASSWORD_EXCEPTION;

public class PasswordInvalidException extends AdminException {
    public PasswordInvalidException() {
        super(INVALID_PASSWORD_EXCEPTION,
                HttpStatus.UNAUTHORIZED);
    }
}
