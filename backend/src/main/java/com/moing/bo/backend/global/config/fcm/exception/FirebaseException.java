package com.moing.bo.backend.global.config.fcm.exception;

import com.moing.bo.backend.global.exception.ApplicationException;
import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class FirebaseException extends ApplicationException {
    protected FirebaseException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
