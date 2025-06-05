package com.example.slitherio.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.example.slitherio.backend.domain.Coordinate;
import com.example.slitherio.backend.domain.Food;
import com.example.slitherio.backend.domain.GameState;
import com.example.slitherio.backend.domain.Snake;

public class GameService {
    private final AtomicReference<GameState> currentGameState = new AtomicReference<>(GameState.empty());

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final double SNAKE_SPEED = 2.0;
    private static final int FOOD_VALUE = 1;
    private static final int MAX_FOOD = 50;

    public GameState getCurrenGameState() {
        return currentGameState.get();
    }

    public GameState updatePlayerDirection(String playerId, double newDirection) {
        return currentGameState.updateAndGet(currentState -> {
            Snake currentSnake = currentState.snakes().get(playerId);
            if (currentSnake == null) return currentState;
            currentSnake.setDirection(newDirection);
            return currentState.updateSnake(currentSnake);
        });
    }

    public GameState gameTick() {
        return currentGameState.updateAndGet(currentState -> {
            Map<String, Snake> nextSnakes = new HashMap<>();
            Map<UUID, Food> nextFoods = new HashMap<>(currentState.foods());
            List<String> deadSnakeIds = new ArrayList<>();
            List<UUID> eatenFoodIds = new ArrayList<>();

            for (Snake snake : currentState.snakes().values()) {
                snake.move(SNAKE_SPEED);
                Coordinate head = snake.getHead();

                if (checkWallCollision(head)) {
                    deadSnakeIds.add(snake.getPlayerId());
                    continue;
                }

                nextSnakes.put(snake.getPlayerId(), snake);
            }

            deadSnakeIds.forEach(nextSnakes::remove);

            for (Snake snake : nextSnakes.values()) {
                Coordinate head = snake.getHead();
                for (Food food : nextFoods.values()) {
                    if (head.equals(food.position())) {
                        snake.grow(food.value());
                        eatenFoodIds.add(food.id());
                    }
                }
            }

            eatenFoodIds.forEach(nextFoods::remove);

            return new GameState(nextSnakes, nextFoods);
        });
    }

    private boolean checkWallCollision(Coordinate head) {
        return head.x() < 0 || head.x() >= BOARD_WIDTH || head.y() < 0 || head.y() >= BOARD_HEIGHT;
    }


}
