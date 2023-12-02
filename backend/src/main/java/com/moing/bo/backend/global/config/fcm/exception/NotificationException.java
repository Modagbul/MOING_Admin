package com.moing.bo.backend.global.config.fcm.exception;

import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotificationException extends FirebaseException {
    public NotificationException(String message) {
        super(ErrorCode.NOTIFICATION_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
