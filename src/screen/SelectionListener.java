package screen;

public interface SelectionListener {
    void onMapSelected(int map);
    void onCharacterSelected(int[] characters, int[] classes);
    void onMenuMapSelected();
    void onMenuCharacterSelected();
    void onMenuGuideSelected();
    void onMenuPlaySelected();
    int onCharacterAttack(int source, int target, int dice1, int dice2);
    void onCharacterDefend(int source, int dice1);
    void onCharacterUseAbility(int source, int target, int dice1, int dice2);
    int getCharacterTurn();
    boolean isGameOn(); 
    void resetAgility();
    int getAllyHp(int index);
    int getEnemyHp(int index);
}
