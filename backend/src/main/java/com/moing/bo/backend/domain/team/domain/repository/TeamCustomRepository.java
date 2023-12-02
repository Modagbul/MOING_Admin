package com.moing.bo.backend.domain.team.domain.repository;

import com.moing.bo.backend.domain.team.application.dto.response.GetLeaderInfoResponse;
import com.moing.bo.backend.domain.team.application.dto.response.GetNewTeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamCustomRepository {

    void updateTeamStatus(boolean isApproved, List<Long> teamIds);

    List<GetLeaderInfoResponse> findLeaderInfoByTeamIds(List<Long> teamIds);

    Page<GetNewTeamResponse> findNewTeam(String dateSort, Pageable pageable);
}
