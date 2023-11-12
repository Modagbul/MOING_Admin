package com.moing.bo.backend.domain.admin.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    USER("ROLE_ADMIN"),
    ADMIN("ROLE_SA_ADMIN");

    private final String key;
}

