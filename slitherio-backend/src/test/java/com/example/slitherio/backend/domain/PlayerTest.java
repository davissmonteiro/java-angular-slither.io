package com.example.slitherio.backend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    @Test
    void testPlayerCreationAndAccessors() {
        String id = "player123";
        String name = "TestPlayer";
        
        Player player = new Player(id, name);

        assertEquals(id, player.id(), "The player's ID should be 'player123'");
        assertEquals(name, player.name(), "The player's name  should be 'TestPlayer'");
    }
}
