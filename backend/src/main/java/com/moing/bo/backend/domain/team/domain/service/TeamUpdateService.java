package com.moing.bo.backend.domain.team.domain.service;

import com.moing.bo.backend.domain.team.domain.repository.TeamRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class TeamUpdateService {

    private final TeamRepository teamRepository;

    private final ApplicationEventPublisher eventPublisher;

    public void updateTeamStatus(boolean isApproved, List<Long> teamIds) {
        teamRepository.updateTeamStatus(isApproved, teamIds);
    }
}
