package character.race;

import character.Character;
import character.CharacterClass;

public class Angel extends Character {
  public Angel(String name, CharacterClass characterClass) {
      super(name, 140,15, 25,30, 15, characterClass); // Elves have higher intelligence and defense but lower HP
  }

}
