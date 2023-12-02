package com.moing.bo.backend.domain.admin.application.dto.response;

import com.moing.bo.backend.global.response.TokenInfoResponse;

public record SignInResponse(
        String accessToken,
        String refreshToken
) {
    public static SignInResponse from(TokenInfoResponse tokenInfoResponse) {
        return new SignInResponse(
                tokenInfoResponse.getAccessToken(),
                tokenInfoResponse.getRefreshToken()
        );
    }
}
