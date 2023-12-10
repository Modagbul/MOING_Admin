package com.moing.bo.backend.global.config.fcm.dto.event;

import com.moing.bo.backend.domain.history.domain.entity.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingleFcmEvent {

    private String registrationToken;
    private String title;
    private String body;
    private Long memberId;
    private String idInfo;
    private String name;
    private AlarmType alarmType;
    private String path;
}
