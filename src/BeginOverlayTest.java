import screen.BeginOverlay;

public class BeginOverlayTest {

    public static void main(String[] args) {
        testTurnOn();
        testTurnOff();
    }

    public static void testTurnOn() {
        BeginOverlay overlay = new BeginOverlay();
        overlay.turnOn(); // Turn on the overlay
        if (overlay.isVisible()) {
            System.out.println("testTurnOn passed: Overlay is visible.");
        } else {
            System.out.println("testTurnOn failed: Overlay is not visible.");
        }
    }

    public static void testTurnOff() {
        BeginOverlay overlay = new BeginOverlay();
        overlay.turnOn(); // Ensure it is visible first
        overlay.turnOff(); // Turn off the overlay
        if (!overlay.isVisible()) {
            System.out.println("testTurnOff passed: Overlay is not visible.");
        } else {
            System.out.println("testTurnOff failed: Overlay is still visible.");
        }
    }
}