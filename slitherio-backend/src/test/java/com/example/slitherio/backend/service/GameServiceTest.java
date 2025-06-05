package com.example.slitherio.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import com.example.slitherio.backend.domain.Coordinate;
import com.example.slitherio.backend.domain.GameState;
import com.example.slitherio.backend.domain.Snake;

public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService();
    }

    @Test
    void testUpdatePlayerDirection() {
        String playerId = "p1";
        Snake initialSnake = new Snake(playerId, List.of(new Coordinate(10, 10)), 0.0);
        GameState initialState = new GameState(Map.of(playerId, initialSnake), Map.of()); 

        gameService.getCurrenGameState().snakes().put(playerId, initialSnake);
        double newDirection = Math.PI / 2;

        //gameService.forceState(initialState);

        GameState updatedState = gameService.updatePlayerDirection(playerId, newDirection);

        assertNotNull(updatedState.snakes().get(playerId), "Snake cannot be null");
        assertEquals(newDirection, updatedState.snakes().get(playerId).getDirection(), "Snake direction must be updated");
        assertEquals(initialSnake.getBody(), updatedState.snakes().get(playerId).getBody(), "Snake body cannot change when updated");
    }

    @Test
    void testGameTickMovesSnake() {
        String playerId = "p1";
        List<Coordinate> initialBody = List.of(new Coordinate(10, 10), new Coordinate(9, 10));
        Snake initialSnake = new Snake(playerId, initialBody, 0.0);
        GameState initialState = new GameState(Map.of(playerId, initialSnake), Map.of()); 
        this.forceState(initialState);
        
        GameState nextState = gameService.gameTick();

        Snake movedSnake = nextState.snakes().get(playerId);
        assertNotNull(movedSnake, "Snake not found after tick.");

        Coordinate expectedHead = new Coordinate(12, 10);
        Coordinate expectedTail = new Coordinate(10, 10);

        assertEquals(expectedHead, movedSnake.getHead(), "The head did not move to the expected position");
        assertEquals(2, movedSnake.getBody().size(), "Snake size cannot change");
        assertTrue(movedSnake.getBody().contains(expectedTail), "Last segment should be in the body");
    }

    private void forceState(GameState state) {
        try {
            java.lang.reflect.Field field = GameService.class.getDeclaredField("currentGameState");
            field.setAccessible(true);
            ((AtomicReference<GameState>) field.get(gameService)).set(state);
        } catch (Exception e) {
            throw new RuntimeException("Failed to force state to test", e);
        }
    }
}
