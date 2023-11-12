package com.moing.bo.backend.domain.admin.application.service;

import com.moing.bo.backend.domain.admin.application.dto.request.SignInRequest;
import com.moing.bo.backend.domain.admin.application.dto.response.SignInResponse;
import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.global.config.security.jwt.TokenUtil;
import com.moing.bo.backend.global.config.security.util.AuthenticationUtil;
import com.moing.bo.backend.global.response.TokenInfoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SignInUserCase {

    private final TokenUtil tokenUtil;
    private final AdminAuthUserCase internalAuthService;

    public SignInResponse signIn(SignInRequest signInRequest) {
        //1. 아이디 비밀번호 확인
        Admin authenticatedMember = internalAuthService.auth(signInRequest);
        //2. security 처리
        AuthenticationUtil.makeAuthentication(authenticatedMember);
        //3. token 만들기
        TokenInfoResponse tokenResponse = tokenUtil.createToken(authenticatedMember);
        //4. refresh token 저장
        tokenUtil.storeRefreshToken(authenticatedMember.getLoginId(), tokenResponse);
        //5. 로그인 시간 업데이트
        authenticatedMember.updateLogInDate();

        return SignInResponse.from(tokenResponse);
    }
}
