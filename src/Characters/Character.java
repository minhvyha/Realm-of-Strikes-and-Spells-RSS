package Characters;

import java.util.Random;

public class Character {
    // Attributes
    private String name;
    private int hp;
    private int maxHp;
    private int strength;
    private int intelligence;
    private int defense;
    private int round;
    private CharacterClass characterClass;
    private Random random;
    
    // Constructor
    public Character(String name, int hp, int strength, int intelligence, int defense, int round, CharacterClass characterClass) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp; // Set max HP equal to initial HP
        this.strength = strength;
        this.intelligence = intelligence;
        this.defense = defense;
        this.characterClass = characterClass;
        this.round=round;
        this.random = new Random();
    }

    // Getters
    public String getName() {
        return name;
    }
    public int round() {
        return round=1;
    }

    public int getHp() {
        return hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDefense() {
        return defense;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public CharacterClass getCharacterClass() {
        return characterClass;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    // Methods
    public void attack(Character target) {
        System.out.println("Round " + round + ":");
        round++;

        // Calculate damage based on strength and target's defense
        int damage = this.strength - target.getDefense();
         // Roll a dice (1-6) and add to damage
         int diceRoll = rollDice();
        System.out.println(this.name + " rolls a " + diceRoll + " on the dice.");
        damage += diceRoll;
 
         //critical hit
         if (random.nextInt(100) < 10) { // 10% chance
             damage *= 2;
             System.out.println("Critical hit!");
         }
         
        // Ensure damage is at least 1
        damage = Math.max(damage, 1);
        target.takeDamage(damage);
        
        System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage.");
    }
    private int rollDice() {
        return random.nextInt(6) + 1; //Roll dice (1-6)
    }

    public void takeDamage(int damage) {
        // Reduce HP by damage amount
        this.hp -= damage;
        // Ensure HP doesn't go below zero
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void heal(int amount) {
        // Heal up to max HP
        this.hp += amount;
        if (this.hp > maxHp) {
            this.hp = maxHp;
        }
        System.out.println(this.name + " heals for " + amount + " HP.");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void reduceDefense(int amount) {
        // Reduce defense by the specified amount
        this.defense -= amount;
        // Ensure defense doesn't go below zero
        if (this.defense < 0) {
            this.defense = 0;
        }
    }

    // Display character status
    public void displayStatus() {
        System.out.println(name + " - HP: " + hp + "/" + maxHp + ", Strength: " + strength + ", Intelligence: "
                + intelligence + ", Defense: " + defense);
    }
    public void useRaceAbility(Character target) {
        // Default ability: basic attack
        attack(target);
    }
    public void useClassAbility(Character target) {
        characterClass.useClassAbility(this, target);
        attack(target);
    }
}
