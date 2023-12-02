package com.moing.bo.backend.domain.team.domain.service;

import com.moing.bo.backend.domain.team.application.dto.response.GetLeaderInfoResponse;
import com.moing.bo.backend.domain.team.application.dto.response.GetNewTeamResponse;
import com.moing.bo.backend.domain.team.domain.repository.TeamRepository;
import com.moing.bo.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class TeamGetService {

    private final TeamRepository teamRepository;

    public List<GetLeaderInfoResponse> getLeaderInfoResponses(List<Long> teamIds) {
        return teamRepository.findLeaderInfoByTeamIds(teamIds);
    }

    public Page<GetNewTeamResponse> getNewTeams(String dateSort, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), Sort.by("no").descending());
        return teamRepository.findNewTeam(dateSort, pageable);
    }


}
