package character.classes;

import character.Character;
import character.CharacterClass;

public class Rogue implements CharacterClass {
    @Override
    public int useClassAbility(Character character, Character target) {
        int damage = character.getStrength() * 3 - target.getDefense();
        if (target.getHp() < target.getMaxHp() / 2) {
            damage *= 2; // Double damage if target's HP is less than half
        }
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        return damage;
    }


}