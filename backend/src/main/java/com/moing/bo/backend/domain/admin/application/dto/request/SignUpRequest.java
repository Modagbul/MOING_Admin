package com.moing.bo.backend.domain.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpRequest {
    @NotBlank(message="id 을 입력해주세요.")
    private String id;

    @NotBlank(message = "password 을 입력해주세요.")
    private String password;
}
