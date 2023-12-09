package com.moing.bo.backend.domain.history.domain.repository;

import com.moing.bo.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;

import java.util.List;

public interface AlarmHistoryCustomRepository {

    List<GetAlarmHistoryResponse> findAlarmHistoriesByMemberId(Long memberId);

    String findUnreadAlarmCount(Long memberId);
}
