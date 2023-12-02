package com.moing.bo.backend.domain.team.application.service;

import com.moing.bo.backend.domain.team.application.dto.response.GetLeaderInfoResponse;
import com.moing.bo.backend.domain.team.domain.service.TeamGetService;
import com.moing.bo.backend.domain.team.domain.service.TeamUpdateService;
import com.moing.bo.backend.global.config.fcm.dto.event.FcmEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.moing.bo.backend.global.config.fcm.constant.ApproveTeamMessage.REJECT_TEAM_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional
public class RejectTeamUseCase {

    private final TeamUpdateService teamUpdateService;
    private final TeamGetService teamGetService;
    private final ApplicationEventPublisher eventPublisher;

    public void rejectTeams(List<Long> teamIds) {
        teamUpdateService.updateTeamStatus(false, teamIds);
        List<GetLeaderInfoResponse> leaderInfos = teamGetService.getLeaderInfoResponses(teamIds);
        for (GetLeaderInfoResponse info : leaderInfos) {
            String title = REJECT_TEAM_MESSAGE.title(info.getLeaderName(), info.getTeamName());
            String body = REJECT_TEAM_MESSAGE.body();
            eventPublisher.publishEvent(new FcmEvent(title, body, Collections.singletonList(info.getLeaderFcmToken())));
        }
    }
}
