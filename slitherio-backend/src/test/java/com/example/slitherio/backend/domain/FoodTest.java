package com.example.slitherio.backend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FoodTest {

    @Test
    void testFoodCreationAndAccessors() {
        Coordinate position = new Coordinate(50, 100);
        int value = 5;
        
        Food food = new Food(position, value);

        assertEquals(position, food.position(), "The food's position should be (50, 100)");
        assertEquals(value, food.value(), "The food's value should be 5");
        assertNotNull(food.id(), "The food must have a unique generated ID");
    }
}
