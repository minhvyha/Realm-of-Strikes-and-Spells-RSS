package screen;

// Listener interface for the main menu
public interface Listener {
    void onMenuPlaySelected(); // Called when the play button is pressed
    void onMenuMapSelected(); // Called when the choose map button is pressed
    void onMenuCharacterSelected(); // Called when the choose allies button is pressed
    void onMenuGuideSelected(); // Called when the guide button is pressed
    void onMenuBattleLogReaderSelected(); // Called when the battle log reader button is pressed

    void onMapSelected(int map); // Called when a map is selected
    void onCharacterSelected(int[] characters, int[] classes); // Called when characters are selected
    void onGuideBack(); // Called when the back button is pressed in the guide
    void onBattleLogReaderBack(); // Called when the back button is pressed in the battle log reader

    int getCharacterTurn(); // Get the current character turn as an index
    int onCharacterAttack(int source, int target, int dice1, int dice2); // Called when a character attacks
    int onCharacterDefend(int source, int dice1); // Called when a character defends
    int onCharacterUseAbility(int source, int target, int dice1, int dice2); // Called when a character uses an ability

    int getAllyHp(int index); // Get the HP of an ally character
    int getEnemyHp(int index); // Get the HP of an enemy character
    String getAllyStatus(int index); // Get the status of an ally character
    String getEnemyStatus(int index); // Get the status of an enemy character

    void resetAgility(); // Reset the agility of all characters on new turn
    void resetDefense(int source); // Reset the defense of a character

    boolean isGameOn(); // Check if the game is still running
    void gameEnd(); // Called when the game ends
    
    boolean isUnlocked(int index); // Check if a map is unlocked
    void unlockMap(); // Unlock a map
}
