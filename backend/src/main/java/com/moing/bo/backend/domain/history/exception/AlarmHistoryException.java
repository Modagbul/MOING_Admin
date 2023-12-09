package com.moing.bo.backend.domain.history.exception;

import com.moing.bo.backend.global.exception.ApplicationException;
import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class AlarmHistoryException extends ApplicationException {
    protected AlarmHistoryException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
