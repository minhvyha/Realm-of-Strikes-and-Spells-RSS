package character;


public interface CharacterClass {
    void useClassAbility(Character character, Character target);
    String getClassName(); // For identifying which class a character has

}

