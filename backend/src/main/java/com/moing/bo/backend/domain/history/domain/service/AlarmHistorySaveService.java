package com.moing.bo.backend.domain.history.domain.service;

import com.moing.bo.backend.domain.history.domain.entity.AlarmHistory;
import com.moing.bo.backend.domain.history.domain.repository.AlarmHistoryRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class AlarmHistorySaveService {

    private final AlarmHistoryRepository alarmHistoryRepository;

    public void saveAlarmHistories(List<AlarmHistory> alarmHistories) {
        alarmHistoryRepository.saveAll(alarmHistories);
    }

    public void saveAlarmHistory(AlarmHistory alarmHistory) {
        alarmHistoryRepository.save(alarmHistory);
    }

}
