package com.moing.bo.backend.domain.history.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberIdAndToken {

    private String fcmToken;
    private Long memberId;

}
