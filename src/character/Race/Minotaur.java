// Orc.java
package character.race;

import character.Character;
import character.CharacterClass;

public class Minotaur extends Character {
  public Minotaur(String name, CharacterClass characterClass) {
      super(name, 150, 25, 5,5, 40, characterClass); // Orcs have higher HP and strength but low intelligence
  }

  @Override
  public void useClassAbility(Character target) {
      // Orcs have a rage ability: deals extra damage based on their strength
      int damage = this.getStrength() * 2 - target.getDefense();
      damage = Math.max(damage, 1);
      target.takeDamage(damage);
      System.out.println(this.getName() + " uses Rage and deals " + damage + " damage to " + target.getName() + "!");
  }
}
