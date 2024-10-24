package character.race;

import character.Character;
import character.CharacterClass;

public class Angel extends Character {
  public Angel(String name, CharacterClass characterClass) {
      super(name, 100, 10, 20,25, 15, characterClass); // Elves have higher intelligence and defense but lower HP
  }

  @Override
  public void useClassAbility(Character target) {
      // Elves have a magic arrow ability that deals damage based on intelligence
      int damage = this.getIntelligence() * 2 - target.getDefense();
      damage = Math.max(damage, 1);
      target.takeDamage(damage);
      System.out.println(this.getName() + " uses Magic Arrow and deals " + damage + " damage to " + target.getName() + "!");
  }
}
