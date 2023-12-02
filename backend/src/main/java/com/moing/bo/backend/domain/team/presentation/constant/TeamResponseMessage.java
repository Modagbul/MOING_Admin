package com.moing.bo.backend.domain.team.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamResponseMessage {

    SEND_APPROVAL_ALARM_SUCCESS("소모임들이 승인되었습니다."),
    SEND_REJECTION_ALARM_SUCCESS("소모임들이 반려되었습니다."),
    GET_NEW_TEAM_SUCCESS("새로운 소모임들을 조회했습니다.");


    private final String message;
}

