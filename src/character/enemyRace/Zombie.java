package character.enemyRace;

import character.Character;
import character.CharacterClass;

public class Zombie extends Character {
    public Zombie(String name, CharacterClass characterClass) {
        super(name, 200, 15, 5, 10, 5, characterClass); // Example stats
    }
}
