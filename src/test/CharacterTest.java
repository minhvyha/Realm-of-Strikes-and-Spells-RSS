package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import character.Character;

import character.classes.Warrior;
import character.classes.Mage;

class CharacterTest {
    Character attacker;
    Character target;

    @BeforeEach
    void setUp() {
        attacker = new Character("Attacker", 150, 20, 10, 10, 10, new Warrior());
        target = new Character("Attacker", 150, 20, 10, 10, 10, new Mage());

    }

    @Test
    void testAttackDamage() {
        int initialHealth = target.getHp();
        int dice1 = 5;
        int dice2 = 3;
        int totalDamge = attacker.attack(target, dice1, dice2);
        int calculatedDamage = calculateDamge(attacker, target, dice1, dice2);
        assertEquals(initialHealth - calculatedDamage, target.getHp(), "Health should decrease correctly");
        assertEquals(calculatedDamage, totalDamge, "Damage should be calculated correctly");
    }

    @Test
    void testSpecialAbility() {
        int initialHealth = target.getHp();

        int caculatedWarriorDamage = calculateWarriorDamage(attacker, target);
        int totalWarriorDamge = attacker.useClassAbility(target);

        assertEquals(initialHealth - caculatedWarriorDamage, target.getHp(), "Health should decrease correctly");
        assertEquals(caculatedWarriorDamage, totalWarriorDamge, "Damage should be calculated correctly");

        int caculatedMageDamage = calculateMageDamage(target, attacker);
        int totalMageDamge = target.useClassAbility(attacker);
        assertEquals(initialHealth - caculatedMageDamage, attacker.getHp(), "Health should decrease correctly");
        assertEquals(caculatedMageDamage, totalMageDamge, "Damage should be calculated correctly");

    }

    private int calculateDamge(Character attacker, Character target, int dice1, int dice2) {
        int damage = attacker.getStrength() * dice1 - target.getDefense() * dice2;
        damage = Math.max(damage, 0);
        return damage;
    }

    private int calculateWarriorDamage(Character attacker, Character target) {

        int damage = attacker.getStrength() * 2 + attacker.getDefense() - target.getDefense();
        if (attacker.getHp() < attacker.getMaxHp() / 4) {
            damage *= 1.5; // 50% more damage if the Warrior's HP is below 25%
        }
        damage = Math.max(damage, 0);
        return damage;
    }

    private int calculateMageDamage(Character attacker, Character target) {
        int damage = attacker.getIntelligence() * 4 - target.getDefense();
        if (target.getHp() > target.getMaxHp() * 0.75) {
            damage *= 1.5; // 50% more damage if target's HP is above 75%
        }
        damage = Math.max(damage, 0);
        return damage;
    }
}
