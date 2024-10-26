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
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getMaxAgility() {
        return maxAgility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDefense() {
        return defense;
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

    public void setAgility(int agility) {
        this.agility = agility;
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

    // Methods for in-game actions
    public void resetDefense() {
        this.defense = maxDefense;
    }

    public void takeDamage(int damage) {
        hp = Math.max(hp - damage, 0);
    }


    public boolean isAlive() {
        return hp > 0;
    }

    public void reduceDefense(int amount) {
        defense = Math.max(defense - amount, 0);
    }

    // Display character status
    public void displayStatus() {
        System.out.printf("%s - HP: %d/%d, Strength: %d, Intelligence: %d, Agility: %d, Defense: %d%n",
                name, hp, maxHp, strength, intelligence, agility, defense);
    }

    public int useClassAbility(Character target) {
        return characterClass.useClassAbility(this, target);
    }
}
