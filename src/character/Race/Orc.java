package character.race;

import character.Character;
import character.CharacterClass;

public class Orc extends Character {
    public Orc(String name, CharacterClass characterClass) {
        super(name, 150, 30, 12, 12, 10, characterClass); // Higher strength and health for Orcs
    }

}
