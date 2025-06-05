package com.example.slitherio.backend.domain;

import java.util.Collections;
import java.util.HashMap;
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

    public GameState updateSnake(Snake snake) {
        Map<String, Snake> newSnakes = new HashMap<>(this.snakes);
        newSnakes.put(snake.getPlayerId(), snake);
        return new GameState(newSnakes, this.foods);
    }

    public GameState removeSnake(String playerId) {
        Map<String, Snake> newSnakes = new HashMap<>(this.snakes);
        newSnakes.remove(playerId);
        return new GameState(newSnakes, this.foods);
    }

    public GameState updateFood(Food food) {
        Map<UUID, Food> newFoods = new HashMap<>(this.foods);
        newFoods.put(food.id(), food);
        return new GameState(this.snakes, newFoods);
    }

    public GameState removeFood(UUID foodId) {
        Map<UUID, Food> newFoods = new HashMap<>(this.foods);
        newFoods.remove(foodId);
        return new GameState(this.snakes, newFoods);
    }

}
