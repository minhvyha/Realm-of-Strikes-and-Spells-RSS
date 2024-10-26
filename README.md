# Realm of Strikes and Spells (RSS)

## Overview
Realm of Strikes and Spells (RSS) is a turn-based role-playing game (RPG) where players lead a team of allies against various enemies in strategic battles. Players can choose characters of different races and classes, each offering unique abilities, and navigate multiple battle maps to engage in tactical combat.

## Project Structure
The code is organized into three main directories under the `src` folder:
- **assets**: Contains visual assets including sprites, background images, and icons.
- **character**: Contains core logic for characters, defining races, classes, and enemy configurations. Subfolders include:
  - **classes**: Contains `Mage.java`, `Rogue.java`, and `Warrior.java`, each representing a distinct combat style.
  - **enemyRaces**: Contains enemy character types like `Golem.java`, `Reaper.java`, and `Zombie.java`.
  - **races**: Defines ally races such as `Angel.java`, `Minotaur.java`, and `Orc.java`.
- **screen**: Contains components for the game's UI screens, including selection menus, battle displays, overlays, and action listeners. Key components:
  - **menu**: Includes `CharacterSelection.java`, `GuideMenu.java`, and `MapSelection.java` for game navigation.
  - **log**: `Log.java` for displaying game logs.
  - Other files provide in-game overlays and visuals, including `BattleScreen.java`, `LoadingOverlay.java`, `GameEnd.java`, and `MainMenu.java`.
- Additional helper and test files (`BeginOverlayTest.java`, `CharacterTest.java`, `GameEndTest.java`, and `Main.java`).

## Key Components

### Character Classes and Races
Characters in RSS are defined by both their race and class, each contributing unique attributes and abilities:

#### Races:
##### Allies:
- **Minotaur**: High HP, attack but low agility and intelligence.
- **Orc**: High HP, attack but low agility and intelligence.
- **Angle**: High defense, intelligence and agility but low HP and attack.
##### Enemies:
- **Golem**: High HP, attack and defense, but low agility and intelligence.
- **Reaper**: High attack and agility.
- **Zombie**: High HP. 
#### Classes:
- **Mage**: deal 1.5x damage when target's HP is high.
- **Rogue**: doubles damage to low-HP targets.
- **Warrior**: excels when HP is low, dealing 1.5x damage.

Each class executes a unique skill through the `useClassAbility` method in combat, adding variety and strategy to gameplay.

### Game Screens and UI Components
RSS provides interactive UI screens for different phases of gameplay:
- **Main Menu**: Starting screen, with options to play, choose allies, select maps, view guides, or quit.
- **Character Selection**: Choose team members, customizing their race and class for a strategic advantage.
- **Map Selection**: Select one of several battle environments, including Enchanted Forest, Frozen Tundra, and Dungeon Chambers.
- **Battle Screen**: Engages players in combat, displaying characters, health bars, and action buttons (Strike, Defense Stand, Special Ability).
- **Overlays**: Screens like `LoadingOverlay`, `BeginOverlay`, and `DiceOverlay` enhance visual transitions and effects.
- **End Game Screen**: Displays the outcome when the battle ends.

## How to Run the Game
1. **Clone the repository:**
   ```bash
   git clone https://github.com/minhvyha/Realm-of-Strikes-and-Spells-RSS.git
1. **Compile the project:**
   ```bash
   javac -d bin src/**/*.java
3. **Run the main class:**
   ```bash
   java -cp bin Main
