package com.moing.bo.backend.domain.admin.presentation;

import com.moing.bo.backend.domain.admin.application.dto.request.SignInRequest;
import com.moing.bo.backend.domain.admin.application.dto.request.SignUpRequest;
import com.moing.bo.backend.domain.admin.application.dto.response.CheckIdResponse;
import com.moing.bo.backend.domain.admin.application.dto.response.SignInResponse;
import com.moing.bo.backend.domain.admin.application.service.CheckIdUserCase;
import com.moing.bo.backend.domain.admin.application.service.SignInUserCase;
import com.moing.bo.backend.domain.admin.application.service.SignUpUserCase;
import com.moing.bo.backend.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moing.bo.backend.domain.admin.presentation.constant.AdminResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AdminController {

    private final SignInUserCase signInUserCase;
    private final SignUpUserCase signUpUserCase;
    private final CheckIdUserCase checkIdUserCase;

    /**
     * 로그인
     * [POST] api/auth/signIn
     */
    @PostMapping("/signIn")
    public ResponseEntity<SuccessResponse<SignInResponse>> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(SuccessResponse.create(SIGN_IN_SUCCESS.getMessage(), this.signInUserCase.signIn(signInRequest)));
    }

    /**
     * 회원가입
     * [POST] api/auth/signUp
     */
    @PostMapping("/signUp")
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        this.signUpUserCase.signUp(signUpRequest);
        return ResponseEntity.ok(SuccessResponse.create(SIGN_UP_SUCCESS.getMessage()));
    }

    /**
     * 아이디 중복검사
     * [GET] api/auth/checkId?id={}
     */
    @GetMapping("/checkId")
    public ResponseEntity<SuccessResponse<CheckIdResponse>> checkId(@RequestParam("id") String id) {
        return ResponseEntity.ok(SuccessResponse.create(CHECK_ID_SUCCESS.getMessage(), new CheckIdResponse(checkIdUserCase.checkId(id))));
    }
}
