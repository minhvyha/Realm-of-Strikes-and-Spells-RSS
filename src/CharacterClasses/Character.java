package CharacterClasses;

public class Character {
    // Attributes
    private String name;
    private int hp;
    private int maxHp;
    private int strength;
    private int intelligence;
    private int defense;

    // Constructor
    public Character(String name, int hp, int strength, int intelligence, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp; // Set max HP equal to initial HP
        this.strength = strength;
        this.intelligence = intelligence;
        this.defense = defense;
    }

    // Getters
    public String getName() {
        return name;
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

    // Methods
    public void attack(Character target) {
        // Calculate damage based on strength and target's defense
        int damage = this.strength - target.getDefense();
        // Ensure damage is at least 1
        damage = Math.max(damage, 1);
        // Apply damage to the target
        target.takeDamage(damage);
        System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage.");
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
    public void useAbility(Character target) {
        // Default ability: basic attack
        attack(target);
    }
}
