package character.enemyRace;

import character.Character;
import character.CharacterClass;

public class Reaper extends Character {

    public Reaper(String name, CharacterClass characterClass) {
        super(name, 100, 20, 10, 15, 10, characterClass); // Example stats
    }

    @Override
    public void useClassAbility(Character target) {
        // Reapers can drain life from their target
        int damage = this.getStrength() + 8 - target.getDefense();
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        // Reaper heals itself by a portion of the damage dealt
        this.heal(damage / 2);
        System.out.println(this.getName() + " drains life from " + target.getName() + ", dealing " + damage + " damage and healing itself!");
    }
}
