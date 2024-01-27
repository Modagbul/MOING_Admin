package com.moing.bo.backend.domain.history.domain.service;


import com.moing.bo.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;
import com.moing.bo.backend.domain.history.domain.entity.AlarmHistory;
import com.moing.bo.backend.domain.history.domain.repository.AlarmHistoryRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class AlarmHistoryGetService {

    private final AlarmHistoryRepository alarmHistoryRepository;

    public String getUnreadAlarmCount(Long memberId) {
        return alarmHistoryRepository.findUnreadAlarmCount(memberId);
    }
}
