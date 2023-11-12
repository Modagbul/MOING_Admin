package com.moing.bo.backend.domain.admin.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApprovedStatus {
    WAITING("확인안함"),
    APPROVED("승인"),
    REJECTED("거절");
    private String message;
}
