package com.moing.bo.backend.domain.team.domain.repository;

import com.moing.bo.backend.domain.team.application.dto.response.GetLeaderInfoResponse;
import com.moing.bo.backend.domain.team.application.dto.response.GetNewTeamResponse;
import com.moing.bo.backend.domain.team.domain.constant.ApprovalStatus;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.moing.bo.backend.domain.member.domain.entity.QMember.member;
import static com.moing.bo.backend.domain.team.domain.entity.QTeam.team;

public class TeamCustomRepositoryImpl implements TeamCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public TeamCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }


    @Override
    public void updateTeamStatus(boolean isApproved, List<Long> teamIds) {
        ApprovalStatus approvalStatus = isApproved ? ApprovalStatus.APPROVAL : ApprovalStatus.REJECTION;

        JPAUpdateClause updateClause = queryFactory
                .update(team)
                .set(team.approvalStatus, approvalStatus);

        // 승인되었을 때만 현재 시간으로 approvalTime 설정
        if (isApproved) {
            updateClause.set(team.approvalTime, LocalDateTime.now());
        }

        updateClause
                .where(team.teamId.in(teamIds))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public List<GetLeaderInfoResponse> findLeaderInfoByTeamIds(List<Long> teamIds) {
        return queryFactory
                .select(Projections.constructor(GetLeaderInfoResponse.class,
                        team.teamId,
                        team.name,
                        member.memberId,
                        member.nickName,
                        member.fcmToken))
                .from(team)
                .join(member).on(team.leaderId.eq(member.memberId))
                .where(team.teamId.in(teamIds))
                .fetch();
    }

    @Override
    public Page<GetNewTeamResponse> findNewTeam(String dateSort, Pageable pageable) {
        OrderSpecifier<?> orderBy = team.createdDate.asc();
        if ("desc".equals(dateSort)) {
            orderBy = team.createdDate.desc();
        }

        List<GetNewTeamResponse> teams = queryFactory
                .select(Projections.constructor(GetNewTeamResponse.class,
                        team.name, team.category, team.promise, team.introduction, team.profileImgUrl, team.createdDate))
                .from(team)
                .where(team.approvalStatus.eq(ApprovalStatus.NO_CONFIRMATION))
                .orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(team.count())
                .from(team)
                .where(team.approvalStatus.eq(ApprovalStatus.NO_CONFIRMATION))
                .fetchOne();

        return PageableExecutionUtils.getPage(teams, pageable, () -> count);
    }


}
