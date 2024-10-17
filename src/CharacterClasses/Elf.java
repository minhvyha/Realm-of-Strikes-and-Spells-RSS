package CharacterClasses;
public class Elf extends Character {
  public Elf(String name) {
      super(name, 100, 10, 20, 15); // Elves have higher intelligence and defense but lower HP
  }

  @Override
  public void useAbility(Character target) {
      // Elves have a magic arrow ability that deals damage based on intelligence
      int damage = this.getIntelligence() * 2 - target.getDefense();
      damage = Math.max(damage, 1);
      target.takeDamage(damage);
      System.out.println(this.getName() + " uses Magic Arrow and deals " + damage + " damage to " + target.getName() + "!");
  }
}
