import character.Character;
import character.classes.Warrior; // Import your concrete subclass

public class CharacterTest {

    private Character character;

    public static void main(String[] args) {
        CharacterTest test = new CharacterTest();
        test.setUp();
        test.testTakeDamage();
        test.testDisplayStatus();
    }

    public void setUp() {
        // Create a new character for testing with a concrete class
        character = new Character("Hero", 100, 10, 5, 3, 8, new Warrior());
    }

    public void testTakeDamage() {
        character.takeDamage(20);
        if (character.getHp() == 80) {
            System.out.println("testTakeDamage passed: HP is correctly decreased.");
        } else {
            System.out.println("testTakeDamage failed: Expected HP 80, got " + character.getHp());
        }

        character.takeDamage(90);
        if (character.getHp() == 0) {
            System.out.println("testTakeDamage passed: HP does not go below zero.");
        } else {
            System.out.println("testTakeDamage failed: Expected HP 0, got " + character.getHp());
        }
    }

    public void testDisplayStatus() {
        System.out.println("Displaying character status:");
        character.displayStatus(); // Assuming this method prints the status
    }
}
