package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    private Battle battle; // Assuming this is the class where checkGameEnd is located
    private SimpleListener listener;
    private SimpleLogPanel logPanel; // A simple implementation for testing

    @BeforeEach
    public void setUp() {
        listener = new SimpleListener();
        logPanel = new SimpleLogPanel(); // Simple logging for testing
        battle = new Battle(listener, logPanel); // Initialize your battle class
    }

    @Test
    public void testCheckGameEnd_AlliesWin() {
        listener.setGameOn(false);
        listener.setAllyHp(100, 0, 0); // At least one ally alive
        
        boolean result = battle.checkGameEnd();

        assertTrue(result, "Expected game to end when allies win");
        assertEquals("=== Allies Win! ===", logPanel.getLastMessage());
    }

    @Test
    public void testCheckGameEnd_EnemiesWin() {
        listener.setGameOn(false);
        listener.setAllyHp(0, 0, 0); // All allies defeated
        
        boolean result = battle.checkGameEnd();

        assertTrue(result, "Expected game to end when enemies win");
        assertEquals("=== Enemies Win! ===", logPanel.getLastMessage());
    }

    // Simple listener implementation for testing
    private static class SimpleListener implements SelectionListener {
        private boolean gameOn;
        private final int[] allyHp = new int[3];

        @Override
        public void unlockMap() {
            // Implement unlock logic for the test if needed
        }

        @Override
        public boolean isGameOn() {
            return gameOn;
        }

        public void setGameOn(boolean gameOn) {
            this.gameOn = gameOn;
        }

        public void setAllyHp(int hp0, int hp1, int hp2) {
            allyHp[0] = hp0;
            allyHp[1] = hp1;
            allyHp[2] = hp2;
        }

        @Override
        public int getAllyHp(int index) {
            return allyHp[index];
        }
    }

    // Simple log panel implementation for testing
    private static class SimpleLogPanel {
        private String lastMessage;

        public void addMessage(String message) {
            lastMessage = message; // Save the last message for assertion
        }

        public String getLastMessage() {
            return lastMessage; // Return the last logged message
        }
    }
}
