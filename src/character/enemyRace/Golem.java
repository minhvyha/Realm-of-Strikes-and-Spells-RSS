package character.enemyRace;

import character.Character;
import character.CharacterClass;

public class Golem extends Character {

    public Golem(String name, CharacterClass characterClass) {
        super(name, 150, 15, 20, 20, 10, characterClass); // Example stats
    }

    @Override
    public void useClassAbility(Character target) {
        // Golems can slam the ground to deal area damage
        int damage = this.getStrength() + 5 - target.getDefense();
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(this.getName() + " slams the ground, dealing " + damage + " damage to " + target.getName() + "!");
    }
}
