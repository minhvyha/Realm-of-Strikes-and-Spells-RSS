package character;


public interface CharacterClass {
    int useClassAbility(Character character, Character target);
    String getClassName(); // For identifying which class a character has

}

