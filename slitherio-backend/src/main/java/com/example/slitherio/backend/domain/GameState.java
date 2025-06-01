package com.example.slitherio.backend.domain;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public record GameState(
    Map<String, Snake> snakes,
    Map<UUID, Food> foods
) {
    public GameState {
        snakes = Collections.unmodifiableMap(snakes);
        foods = Collections.unmodifiableMap(foods);
    }

    public static GameState empty() {
        return new GameState(Collections.emptyMap(), Collections.emptyMap());
    }

}
