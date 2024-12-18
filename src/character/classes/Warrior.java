package character.classes;

import character.Character;
import character.CharacterClass;

public class Warrior implements CharacterClass {
    @Override
    public int useClassAbility(Character character, Character target) {
        int damage = character.getStrength() * 2 + character.getDefense() - target.getDefense();
        if (character.getHp() < character.getMaxHp() / 4) {
            damage *= 1.5; // 50% more damage if the Warrior's HP is below 25%
        }
        damage = Math.max(damage, 0);
        target.takeDamage(damage);
        return damage;
    }

}
