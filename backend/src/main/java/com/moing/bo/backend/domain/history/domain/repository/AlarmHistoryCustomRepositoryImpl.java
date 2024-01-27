package com.moing.bo.backend.domain.history.domain.repository;

import com.moing.bo.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;
import com.moing.bo.backend.domain.history.application.dto.response.QGetAlarmHistoryResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.moing.bo.backend.domain.history.domain.entity.QAlarmHistory.alarmHistory;

public class AlarmHistoryCustomRepositoryImpl implements AlarmHistoryCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AlarmHistoryCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public String findUnreadAlarmCount(Long memberId) {
        Long count = queryFactory.select(alarmHistory.count())
                .from(alarmHistory)
                .where(alarmHistory.receiverId.eq(memberId)
                        .and(alarmHistory.isRead.eq(false)))
                .fetchOne();

        return count != null ? (count > 99 ? "99+" : count.toString()) : "0";
    }
}
