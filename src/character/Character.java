package character;

public class Character {
    // Attributes
    private String name;
    private int hp, maxHp, strength, agility, maxAgility, intelligence, defense, maxDefense;
    private CharacterClass characterClass;

    // Constructor
    public Character(String name, int hp, int strength, int agility, int intelligence, int defense,
            CharacterClass characterClass) {
        this.name = name;
        this.hp = this.maxHp = hp;
        this.strength = strength;
        this.agility = this.maxAgility = agility;
        this.intelligence = intelligence;
        this.defense = this.maxDefense = defense;
        this.characterClass = characterClass;
    }

    // Getters and Setters
    public String getName() {
        return name; // Get the character's name
    }

    public int getHp() {
        return hp; // Get the current health points
    }

    public int getMaxHp() {
        return maxHp; // Get the maximum health points
    }

    public int getStrength() {
        return strength; // Get the strength attribute
    }

    public int getAgility() {
        return agility; // Get the agility attribute
    }

    public int getMaxAgility() {
        return maxAgility; // Get the maximum agility value
    }

    public int getIntelligence() {
        return intelligence; // Get the intelligence attribute
    }

    public int getDefense() {
        return defense; // Get the defense attribute
    }

    public CharacterClass getCharacterClass() {
        return characterClass; // Get the character's class
    }

    public void setName(String name) {
        this.name = name; // Set the character's name
    }

    public void setHp(int hp) {
        this.hp = hp; // Set the current health points
    }

    public void setStrength(int strength) {
        this.strength = strength; // Set the strength attribute
    }

    public void setAgility(int agility) {
        this.agility = agility; // Set the agility attribute
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence; // Set the intelligence attribute
    }

    public void setDefense(int defense) {
        this.defense = defense; // Set the defense attribute
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp; // Set the maximum health points
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass; // Set the character's class
    }


    // Methods for in-game actions
    public void resetDefense() {
        this.defense = maxDefense;
    }

    public void takeDamage(int damage) {
        hp = Math.max(hp - damage, 0); // Reduce HP by damage taken, ensuring it doesn't go below 0
    }

    public boolean isAlive() {
        return hp > 0; // Check if the character is still alive
    }

    public void reduceDefense(int amount) {
        defense = Math.max(defense - amount, 0); // Reduce defense by a specified amount, ensuring it doesn't go below 0
    }

    public int attack(Character target, int dice1, int dice2) {
        int totalDamge = this.getStrength() * dice1 - target.getDefense() * dice2; // Calculate the total damage
        totalDamge = Math.max(totalDamge, 0); // Ensure that the total damage is at least 0
        target.takeDamage(totalDamge); // Apply the damage to the target
        return totalDamge; // Return the total damage
    }

    // Display character status
    public String displayStatus() {
        return String.format("%s - HP: %d/%d, Strength: %d, Intelligence: %d, Agility: %d, Defense: %d%n",
                name, hp, maxHp, strength, intelligence, agility, defense);
    }

    public int useClassAbility(Character target) {
        return characterClass.useClassAbility(this, target);
    }
}
