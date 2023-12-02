package com.moing.bo.backend.domain.team.application.dto.response;

public record GetTeamResponse(
        Long teamId,
        String name,
        String introduction,
        String promise,
        String profileImgUrl) {
}

