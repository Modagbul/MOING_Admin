package com.moing.bo.backend.domain.history.domain.repository;

import com.moing.bo.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;

import java.util.List;

public interface AlarmHistoryCustomRepository {

    String findUnreadAlarmCount(Long memberId);
}
