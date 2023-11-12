package com.moing.bo.backend.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovalStatus {
    NO_CONFIRMATION("확인안함"),
    APPROVAL("승인"),
    REJECTION("거절");
    private final String message;
}
