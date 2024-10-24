package character.Class;

import character.Character;
import character.CharacterClass;

public class Mage implements CharacterClass {
    @Override
    public void useClassAbility(Character character, Character target) {
        int damage = character.getIntelligence() * 4 - target.getDefense();
        if (target.getHp() > target.getMaxHp() * 0.75) {
            damage *= 1.5; // 50% more damage if target's HP is above 75%
        }
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(character.getName() + " casts Fireball and deals " + damage + " damage to " + target.getName() + "!");
    }

    @Override
    public String getClassName() {
        return "Mage";
    }
}
