package com.example.slitherio.backend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SnakeTest {
    @Test
    void testSnakeCreationAndAccessors() {
        Player player = new Player("p1", "SnakePlayer");
        List<Coordinate> initialBody = List.of(new Coordinate(10, 10), new Coordinate(9, 10));
        double direction = 0.0;
        
        Snake snake = new Snake(player.id(), initialBody, direction);

        assertEquals(player.id(), snake.playerId(), "The ID of snake's player should be 'p1'");
        assertEquals(initialBody, snake.body(), "The initial body must match");
        assertEquals(initialBody.get(0), snake.getHead(), "The head must be the first of segment");
        assertEquals(direction, snake.direction(), "The initial direction must be 0.0");
        
    }
}
