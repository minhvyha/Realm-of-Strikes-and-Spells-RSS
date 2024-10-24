package screen;

public interface SelectionListener {
    void onMapSelected(int map);
    void onCharacterSelected(int[] characters, int[] classes);
    void onMenuMapSelected();
    void onMenuCharacterSelected();
    void onMenuPlaySelected();
}
