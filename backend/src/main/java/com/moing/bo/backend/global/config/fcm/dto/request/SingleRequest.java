package com.moing.bo.backend.global.config.fcm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingleRequest {
    @NotNull(message = "기기 등록 토큰을 입력해 주세요.")
    private String registrationToken;

    @NotNull(message = "알림 제목을 입력해 주세요.")
    private String title;

    @NotNull(message = "알림 내용을 입력해 주세요.")
    private String body;
}
