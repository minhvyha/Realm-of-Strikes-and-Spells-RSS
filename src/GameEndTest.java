import screen.GameEnd;

public class GameEndTest {

    public static void main(String[] args) {
        testGameEndCreation();
        testTurnOn();
        testTurnOff();
    }

    public static void testGameEndCreation() {
        // Create an instance of GameEnd
        GameEnd gameEnd = new GameEnd();

        // Check if the GameEnd instance is created
        if (gameEnd != null) {
            System.out.println("testGameEndCreation passed: GameEnd instance created.");
        }
    }

    public static void testTurnOn() {
        GameEnd gameEnd = new GameEnd();
        gameEnd.turnOn();

        // Check if the panel is visible
        if (gameEnd.isVisible()) {
            System.out.println("testTurnOn passed: GameEnd panel is visible.");
        } else {
            System.out.println("testTurnOn failed: GameEnd panel is not visible.");
        }
    }

    public static void testTurnOff() {
        GameEnd gameEnd = new GameEnd();
        gameEnd.turnOn(); // First turn it on
        gameEnd.turnOff(); // Then turn it off

        // Check if the panel is invisible
        if (!gameEnd.isVisible()) {
            System.out.println("testTurnOff passed: GameEnd panel is not visible.");
        } else {
            System.out.println("testTurnOff failed: GameEnd panel is still visible.");
        }
    }
}
