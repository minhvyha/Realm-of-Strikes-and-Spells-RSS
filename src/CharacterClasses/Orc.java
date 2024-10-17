// Orc.java
package CharacterClasses;

public class Orc extends Character {
  public Orc(String name) {
      super(name, 150, 20, 5, 10); // Orcs have higher HP and strength but low intelligence
  }

  @Override
  public void useAbility(Character target) {
      // Orcs have a rage ability: deals extra damage based on their strength
      int damage = this.getStrength() * 2 - target.getDefense();
      damage = Math.max(damage, 1);
      target.takeDamage(damage);
      System.out.println(this.getName() + " uses Rage and deals " + damage + " damage to " + target.getName() + "!");
  }
}
