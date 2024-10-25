
import org.junit.jupiter.api.Test;
import character.Character;
import character.classes.Warrior; // Import your concrete subclass

public class CharacterTest {

    private Character character;

 
    public void setUp() {
        // Create a new character for testing with a concrete class
        character = new Character("Hero", 100, 10, 5, 3, 8, new Warrior()); // Use Warrior class
    }

    @Test
    public void testTakeDamage() {
        character.takeDamage(20);
        assertEquals(80, character.getHp(), "HP should decrease by the damage taken.");

        character.takeDamage(90);
        assertEquals(0, character.getHp(), "HP should not go below zero.");
    }

    @Test
    public void testDisplayStatus() {
        character.displayStatus(); 
    }
}
