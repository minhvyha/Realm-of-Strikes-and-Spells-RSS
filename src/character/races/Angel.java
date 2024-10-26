package character.races;

import character.Character;
import character.CharacterClass;

public class Angel extends Character {
    public Angel(String name, CharacterClass characterClass) {
        super(name, 40, 15, 25, 30, 15, characterClass); // Elves have higher intelligence and defense but lower HP
    }

}
