package com.moing.bo.backend.domain.team.application.service;

import com.moing.bo.backend.domain.team.application.dto.response.GetNewTeamResponse;
import com.moing.bo.backend.domain.team.domain.service.TeamGetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GetTeamUseCase {

    private final TeamGetService teamGetService;


    public Page<GetNewTeamResponse> getNewTeam(String dateSort, Pageable pageable) {
        return teamGetService.getNewTeams(dateSort, pageable);
    }

}
