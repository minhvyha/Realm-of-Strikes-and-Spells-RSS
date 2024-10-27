# Realm of Strikes and Spells (RSS)

## Overview
Realm of Strikes and Spells (RSS) is a turn-based role-playing game (RPG) where players lead a team of allies against various enemies in strategic battles. Players can choose characters of different races and classes, each offering unique abilities, and navigate multiple battle maps to engage in tactical combat.

## Project Structure
```
src/
 ┣ assets/
 ┣ character/
 ┃ ┣ classes/
 ┃ ┃ ┣ Mage.java
 ┃ ┃ ┣ Rogue.java
 ┃ ┃ ┗ Warrior.java
 ┃ ┣ enemyRaces/
 ┃ ┃ ┣ Golem.java
 ┃ ┃ ┣ Reaper.java
 ┃ ┃ ┗ Zombie.java
 ┃ ┣ races/
 ┃ ┃ ┣ Angel.java
 ┃ ┃ ┣ Minotaur.java
 ┃ ┃ ┗ Orc.java
 ┃ ┣ Character.java
 ┃ ┣ CharacterClass.java
 ┃ ┣ CharacterLabel.java
 ┃ ┗ EnemyLabel.java
 ┣ screen/
 ┃ ┣ log/
 ┃ ┃ ┗ Log.java
 ┃ ┣ menu/
 ┃ ┃ ┣ BattleLogReader.java
 ┃ ┃ ┣ CharacterSelection.java
 ┃ ┃ ┣ GuideMenu.java
 ┃ ┃ ┗ MapSelection.java
 ┃ ┣ BattleScreen.java
 ┃ ┣ BeginOverlay.java
 ┃ ┣ DiceOverlay.java
 ┃ ┣ GameEnd.java
 ┃ ┣ LoadingOverlay.java
 ┃ ┣ MainMenu.java
 ┃ ┗ SelectionListener.java
 ┣ test.
 ┃ ┣ BeginOverlayTest.java
 ┃ ┣ CharacterTest.java
 ┃ ┣ DiceOverlayTest.java
 ┃ ┣ GameEndTest.java
 ┃ ┗ LogTest.java
 ┗ Main.java
```
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

## Technologies Used
- **Java**: Core programming language.
- **Java Swing**: Used for creating graphical user interface components.
- **JUnit**: For unit testing game components.
- 
## How to Run the Program
1. **Clone the repository:**
   ```bash
   git clone https://github.com/minhvyha/Realm-of-Strikes-and-Spells-RSS.git
1. **Compile the project:**
   ```bash
   javac -d bin src/**/*.java
3. **Run the main class:**
   ```bash
   java -cp bin Main
