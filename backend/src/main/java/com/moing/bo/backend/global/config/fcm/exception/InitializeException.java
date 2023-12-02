package com.moing.bo.backend.global.config.fcm.exception;

import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class InitializeException extends FirebaseException {
    public InitializeException() {
        super(ErrorCode.INITIALIZE_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
