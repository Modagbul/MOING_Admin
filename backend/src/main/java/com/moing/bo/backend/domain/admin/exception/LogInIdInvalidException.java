package com.moing.bo.backend.domain.admin.exception;

import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class LogInIdInvalidException extends AdminException {
    public LogInIdInvalidException(){
        super(ErrorCode.INVALID_LOGINID_EXCEPTION,
                HttpStatus.NOT_FOUND);
    }
}
