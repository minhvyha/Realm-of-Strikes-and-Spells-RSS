package character.race;

import character.Character;
import character.CharacterClass;

public class Orc extends Character {
    public Orc(String name, CharacterClass characterClass) {
        super(name, 150, 20, 12, 12, 10, characterClass); // Higher strength and health for Orcs
    }

    @Override
    public void useClassAbility(Character target) {
        // Orcs have a Fury Charge ability that deals extra damage based on their strength
        int damage = this.getStrength() + 10 - target.getDefense(); // Extra damage due to strength
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(this.getName() + " uses Fury Charge, dealing " + damage + " damage to " + target.getName() + "!");
    }
}
