package com.moing.bo.backend.global.config.fcm.dto.request;

import com.moing.bo.backend.domain.history.application.dto.response.MemberIdAndToken;
import com.moing.bo.backend.domain.history.domain.entity.AlarmType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultiRequest {

    private List<MemberIdAndToken> memberIdAndTokens;

    private String title;

    private String body;

    private String idInfo;

    private String name;

    private AlarmType alarmType;

    private String path;

}