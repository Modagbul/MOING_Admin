package com.moing.bo.backend.domain.admin.presentation;

import com.moing.bo.backend.config.CommonControllerTest;
import com.moing.bo.backend.domain.admin.application.dto.request.SignInRequest;
import com.moing.bo.backend.domain.admin.application.dto.response.SignInResponse;
import com.moing.bo.backend.domain.admin.application.service.CheckIdUserCase;
import com.moing.bo.backend.domain.admin.application.service.SignInUserCase;
import com.moing.bo.backend.domain.admin.application.service.SignUpUserCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest extends CommonControllerTest {

    @MockBean
    private SignInUserCase signInUserCase;

    @MockBean
    private SignUpUserCase signUpUserCase;

    @MockBean
    private CheckIdUserCase checkIdUserCase;

    @Test
    public void sign_in() throws Exception {
        //given
        SignInRequest input = SignInRequest.builder()
                .id("아이디")
                .password("비밀번호")
                .build();

        String body = objectMapper.writeValueAsString(input);

        SignInResponse output = new SignInResponse("SERVER_ACCESS_TOKEN", "SERVER_REFRESH_TOKEN");

        given(signInUserCase.signIn(any())).willReturn(output);

        //when
        ResultActions actions = mockMvc.perform(
                post("/api/auth/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("id").description("아이디"),
                                        fieldWithPath("password").description("비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description("로그인을 했습니다"),
                                        fieldWithPath("data.accessToken").description("서버 접근용 Access Token"),
                                        fieldWithPath("data.refreshToken").description("서버 접근용 Refresh Token")
                                )
                        )
                );
    }

}