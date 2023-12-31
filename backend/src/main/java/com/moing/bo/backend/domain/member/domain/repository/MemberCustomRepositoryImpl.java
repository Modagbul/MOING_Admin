package com.moing.bo.backend.domain.member.domain.repository;

import com.moing.bo.backend.domain.member.domain.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static com.moing.bo.backend.domain.member.domain.entity.QMember.member;

public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    public MemberCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean checkNickname(String nickname) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.nickName.eq(nickname))
                .where(member.isDeleted.eq(false))
                .fetchFirst() != null;
    }

    @Override
    public Optional<Member> findNotDeletedBySocialId(String socialId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.socialId.eq(socialId))
                .where(member.isDeleted.eq(false))
                .fetchOne());
    }

    @Override
    public Optional<Member> findNotDeletedByEmail(String email) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.email.eq(email))
                .where(member.isDeleted.eq(false))
                .fetchOne());
    }

    @Override
    public Optional<Member> findNotDeletedByMemberId(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.memberId.eq(id))
                .where(member.isDeleted.eq(false))
                .fetchOne());
    }
}
