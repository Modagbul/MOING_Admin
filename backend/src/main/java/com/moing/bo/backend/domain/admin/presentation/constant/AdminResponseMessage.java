package com.moing.bo.backend.domain.admin.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminResponseMessage {
    SIGN_IN_SUCCESS("로그인을 했습니다"),
    SIGN_UP_SUCCESS("회원 가입을 했습니다."),
    REISSUE_TOKEN_SUCCESS("토큰을 재발급했습니다"),
    CHECK_ID_SUCCESS("아이디 중복검사를 했습니다");
    private final String message;
}
