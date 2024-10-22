package Characters;

import Characters.Class.Rogue;
import Characters.Class.Warrior;
import java.util.Random;

public class BattleSimulator {
    private Character attacker;
    private Character target;
    private Random random;

    public static void main(String[] args) {
        CharacterClass warriorClass = new Warrior(); // Assuming you have a Warrior class that implements CharacterClass
        CharacterClass rogueClass = new Rogue(); // Assuming you have a Rogue class that implements CharacterClass
    
        Character warrior = new Character("Warrior", 100, 15, 10, 6, 0, warriorClass);
        Character rogue = new Character("Rogue", 80, 10, 5, 8, 0, rogueClass);
        
        warrior.attack(rogue); // Warrior attacks Rogue
        rogue.displayStatus(); // Display Rogue's status after attack
        warrior.displayStatus(); 
    }
    
}
