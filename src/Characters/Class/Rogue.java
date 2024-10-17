package Characters.Class;

import Characters.Character;
import Characters.CharacterClass;

public class Rogue implements CharacterClass {
    @Override
    public void useClassAbility(Character character, Character target) {
        int damage = character.getStrength() * 3 - target.getDefense();
        if (target.getHp() < target.getMaxHp() / 2) {
            damage *= 2; // Double damage if target's HP is less than half
        }
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(character.getName() + " uses Backstab and deals " + damage + " damage to " + target.getName() + "!");
    }

    @Override
    public String getClassName() {
        return "Rogue";
    }
}