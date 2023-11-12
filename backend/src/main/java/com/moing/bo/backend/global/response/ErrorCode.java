package com.moing.bo.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("400", "입력값이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED("405", "클라이언트가 사용한 HTTP 메서드가 리소스에서 허용되지 않습니다."),
    INTERNAL_SERVER_ERROR("500", "서버에서 요청을 처리하는 동안 오류가 발생했습니다."),
    NOT_FOUND_REFRESH_TOKEN_ERROR( "J0008",  "유효하지 않는 RefreshToken 입니다."),


    INVALID_LOGINID_EXCEPTION("AU0001", "유효하지 않는 id입니다."),
    INVALID_PASSWORD_EXCEPTION("AU0002","비밀번호가 일치하지 않습니다."),
    NOT_APPROVED_EXCEPTION("AU0003","승인되지 않은 계정입니다"),
    DUPLICATED_LOGINID_EXCEPTION("AU0004","중복된 id입니다");

    private final String errorCode;
    private final String message;

}
