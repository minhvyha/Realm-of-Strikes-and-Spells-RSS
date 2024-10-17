package CharacterClasses;

public class Human extends Character {
  public Human(String name) {
      super(name, 120, 15, 15, 15); // Balanced stats
  }

  @Override
  public void useAbility(Character target) {
      // Humans have a shield bash ability that deals damage and reduces target's defense temporarily
      int damage = this.getStrength() + 5 - target.getDefense();
      damage = Math.max(damage, 1);
      target.takeDamage(damage);
      target.reduceDefense(Math.max(0, target.getDefense() - 5)); // Reduce target's defense temporarily
      System.out.println(this.getName() + " uses Shield Bash, deals " + damage + " damage and reduces " + target.getName() + "'s defense!");
  }
}
