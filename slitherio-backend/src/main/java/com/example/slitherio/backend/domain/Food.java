package com.example.slitherio.backend.domain;

import java.util.UUID;

public record Food(
    UUID id,
    Coordinate position,
    int value
) {
    public Food(Coordinate position, int value) {
        this(UUID.randomUUID(), position, value);
    }
}
