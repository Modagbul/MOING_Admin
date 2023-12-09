package com.moing.bo.backend.domain.member.domain.service;

import com.moing.bo.backend.domain.member.domain.entity.Member;
import com.moing.bo.backend.domain.member.domain.repository.MemberRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberGetService {
    private final MemberRepository memberRepository;

    public Member getMemberBySocialId(String socialId) {
        return memberRepository.findNotDeletedBySocialId(socialId).orElseThrow(() -> new NotFoundBySocialIdException());
    }

    public Member getMemberByMemberId(Long memberId) {
        return memberRepository.findNotDeletedByMemberId(memberId).orElseThrow(() -> new NotFoundBySocialIdException());
    }
}
