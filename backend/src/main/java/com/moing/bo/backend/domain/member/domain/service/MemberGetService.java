package com.moing.bo.backend.domain.member.domain.service;

import com.moing.bo.backend.domain.member.domain.repository.MemberRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberGetService {
    private final MemberRepository memberRepository;

}
