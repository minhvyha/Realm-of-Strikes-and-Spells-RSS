// Orc.java
package character.races;

import character.Character;
import character.CharacterClass;

public class Minotaur extends Character {
    public Minotaur(String name, CharacterClass characterClass) {
        super(name, 50, 25, 5, 5, 40, characterClass); // Orcs have higher HP and strength but low intelligence
    }

}