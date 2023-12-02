package com.moing.bo.backend.domain.admin.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record CheckIdResponse(
        Boolean isDuplicated
) {
}
