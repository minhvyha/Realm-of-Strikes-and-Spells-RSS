package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screen.GameEnd;

class GameEndTest {
    GameEnd gameEnd;

    @BeforeEach
    void setUp() {
        gameEnd = new GameEnd();
    }

    @Test
    void testGameEndCreation() {
        // Check if the GameEnd instance is created
        assertNotNull(gameEnd, "GameEnd instance should be created.");
    }

    @Test
    void testTurnOn() {
        gameEnd.turnOn();

        // Check if the panel is visible
        assertTrue(gameEnd.isVisible(), "GameEnd panel should be visible.");
    }

    @Test
    void testTurnOff() {
        gameEnd.turnOn(); // First turn it on
        gameEnd.turnOff(); // Then turn it off

        // Check if the panel is invisible
        assertFalse(gameEnd.isVisible(), "GameEnd panel should not be visible.");
    }
}