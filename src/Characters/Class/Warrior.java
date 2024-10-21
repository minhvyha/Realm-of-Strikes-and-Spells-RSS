package Characters.Class;

import Characters.Character;
import Characters.CharacterClass;

public class Warrior implements CharacterClass {
    @Override
    public void useClassAbility(Character character, Character target) {
        int damage = character.getStrength() * 2 + character.getDefense() - target.getDefense();
        if (character.getHp() < character.getMaxHp() / 4) {
            damage *= 1.5; // 50% more damage if the Warrior's HP is below 25%
        }
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        System.out.println(character.getName() + " uses Power Strike and deals " + damage + " damage to " + target.getName() + "!");
    }

    @Override
    public String getClassName() {
        return "Warrior";
    }
}
