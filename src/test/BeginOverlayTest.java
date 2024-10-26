package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screen.BeginOverlay;

class BeginOverlayTest {
    BeginOverlay overlay;

    @BeforeEach
    void setUp() {
        overlay = new BeginOverlay();
    }

    @Test
    void testTurnOn() {
        overlay.turnOn(); // Turn on the overlay

        // Check if the overlay is visible
        assertTrue(overlay.isVisible(), "Overlay should be visible.");
    }

    @Test
    void testTurnOff() {
        overlay.turnOn(); // Ensure it is visible first
        overlay.turnOff(); // Turn off the overlay

        // Check if the overlay is invisible
        assertFalse(overlay.isVisible(), "Overlay should not be visible.");
    }
}