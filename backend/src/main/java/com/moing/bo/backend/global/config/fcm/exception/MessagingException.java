package com.moing.bo.backend.global.config.fcm.exception;

import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class MessagingException extends FirebaseException {
    public MessagingException(String message) {
        super(ErrorCode.MESSAGING_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
