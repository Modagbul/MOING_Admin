package com.moing.bo.backend.global.config.security.jwt;

import com.moing.bo.backend.global.exception.ApplicationException;
import com.moing.bo.backend.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundRefreshToken extends ApplicationException {
    public NotFoundRefreshToken() {
        super(ErrorCode.NOT_FOUND_REFRESH_TOKEN_ERROR,
                HttpStatus.NOT_FOUND);
    }

}
