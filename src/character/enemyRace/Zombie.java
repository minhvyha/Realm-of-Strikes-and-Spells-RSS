package character.enemyRace;

import character.Character;
import character.CharacterClass;

public class Zombie extends Character {
    
    public Zombie(String name, CharacterClass characterClass) {
        super(name, 140, 15, 5, 10, 5, characterClass); // Example stats
    }

    @Override
    public void useClassAbility(Character target) {
        // Zombies can deal damage and inflict a "bleed" effect
        int damage = this.getStrength() + 3 - target.getDefense();
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(this.getName() + " bites " + target.getName() + ", dealing " + damage + " damage!");

        // Here you could also implement a "bleed" effect if needed
    }
}
