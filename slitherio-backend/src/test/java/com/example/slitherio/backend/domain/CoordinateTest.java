package com.example.slitherio.backend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CoordinateTest {

    @Test
    void testCoordinateCreationAndAccessors() {
        int x = 10;
        int y = 20;

        Coordinate coordinate = new Coordinate(x, y);

        assertEquals(x, coordinate.x(), "The coordinate X should be 10");
        assertEquals(y, coordinate.y(), "The coordinate Y should be 20");
    }
}
