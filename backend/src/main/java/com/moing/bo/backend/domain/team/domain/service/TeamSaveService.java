package com.moing.bo.backend.domain.team.domain.service;

import com.moing.bo.backend.domain.team.domain.entity.Team;
import com.moing.bo.backend.domain.team.domain.repository.TeamRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TeamSaveService {
    private final TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }
}
