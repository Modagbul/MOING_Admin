package com.moing.bo.backend.domain.admin.exception;

import com.moing.bo.backend.global.exception.ApplicationException;
import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class AdminException extends ApplicationException {
    protected AdminException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
