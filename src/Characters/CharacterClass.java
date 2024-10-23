package Characters;

import Characters.Class.Mage;
import Characters.Class.Rogue;
import Characters.Class.Warrior;

public interface CharacterClass {
    void useClassAbility(Character character, Character target);
    String getClassName(); // For identifying which class a character has

    Character rogue = new Character("Thief", 90, 20, 15, 10, new Rogue());
    Character warrior = new Character("Knight", 120, 30, 10, 25, new Warrior());
    Character mage = new Character("Sorcerer", 80, 10, 40, 5, new Mage());

}

