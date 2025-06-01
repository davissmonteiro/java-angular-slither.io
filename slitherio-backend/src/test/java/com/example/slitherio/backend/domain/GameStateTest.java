package com.example.slitherio.backend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class GameStateTest {
    @Test
    void testGameStateCreationAndAccessors() {
        
        Snake snake1 = new Snake("p1", List.of(new Coordinate(1,1)),0);
        Food food1 = new Food(new Coordinate(5, 5), 1);
        Map<String, Snake> snakes = Map.of(snake1.playerId(), snake1);
        Map<UUID, Food> foods = Map.of(food1.id(), food1);

        GameState gameState = new GameState(snakes, foods);

        assertEquals(snakes, gameState.snakes(), "The snake map must match");
        assertEquals(foods, gameState.foods(), "The foods map must match");
    }

    @Test
    void testEmptyGameState() {
        GameState emptyState = GameState.empty();

        assertTrue(emptyState.snakes().isEmpty(), "The snakes map must be empty");
        assertTrue(emptyState.foods().isEmpty(), "The foods map must be empty");
    }
}
