package com.moing.bo.backend.domain.admin.exception;

import org.springframework.http.HttpStatus;

import static com.moing.bo.backend.global.response.ErrorCode.DUPLICATED_LOGINID_EXCEPTION;

public class DuplicatedIdException extends AdminException {
    public DuplicatedIdException() {
        super(DUPLICATED_LOGINID_EXCEPTION,
                HttpStatus.BAD_REQUEST);
    }
}
