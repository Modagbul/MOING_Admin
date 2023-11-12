package com.moing.bo.backend.domain.admin.exception;

import com.moing.bo.backend.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import static com.moing.bo.backend.global.response.ErrorCode.NOT_APPROVED_EXCEPTION;

public class NotApprovedException extends ApplicationException {
    public NotApprovedException() {
        super(NOT_APPROVED_EXCEPTION,
                HttpStatus.UNAUTHORIZED);
    }
}
