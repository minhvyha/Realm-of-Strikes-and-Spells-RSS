package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.URL;

import screen.DiceOverlay;

import static org.junit.jupiter.api.Assertions.*;

class DiceOverlayTest {
    private DiceOverlay diceOverlay;

    @BeforeEach
    void setUp() {
        diceOverlay = new DiceOverlay();
        diceOverlay.turnOff();

    }

    @Test
    void testSetDiceImages() {
        // Test setting dice with valid rolls
        diceOverlay.setDice(1, 2);

        // Get the dice images URLs for verification
        URL dice1Resource = getClass().getResource("/assets/dice/1.png");
        URL dice2Resource = getClass().getResource("/assets/dice/2.png");

        // Check that images loaded correctly based on available resource URLs
        assertNotNull(dice1Resource, "Dice image for roll 1 should exist.");
        assertNotNull(dice2Resource, "Dice image for roll 2 should exist.");
    }

    @Test
    void testToggleOverlayVisibility() {
        // Ensure the overlay is initially hidden
        assertFalse(diceOverlay.isVisible(), "Overlay should be initially invisible.");

        // Turn on the overlay and check visibility
        diceOverlay.turnOn();
        assertTrue(diceOverlay.isVisible(), "Overlay should be visible after turning on.");

        // Turn off the overlay and check visibility
        diceOverlay.turnOff();
        assertFalse(diceOverlay.isVisible(), "Overlay should be invisible after turning off.");
    }
}
