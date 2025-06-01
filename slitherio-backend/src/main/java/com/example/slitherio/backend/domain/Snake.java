package com.example.slitherio.backend.domain;

import java.util.List;
import java.util.Objects;

public record Snake(
    String playerId,
    List<Coordinate> body,
    double direction 
) {
    public Snake {
        Objects.requireNonNull(playerId, "playerId cannot be null");
        Objects.requireNonNull(body, "body cannot be null");
        if (body.isEmpty()) {
            throw new IllegalArgumentException("The snake's body cannot be empty");
        }
    }

    public Coordinate getHead() {
            return body.get(0);
        }

}
