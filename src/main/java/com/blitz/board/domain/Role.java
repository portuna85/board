package com.blitz.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    GOLD("GOLD"), SILVER("SILVER"), BRONZE("BRONZE");

    private final String value;

    public String getValue() {
        return value;
    }
}
