# Realm of Strikes and Spells (RSS)

## Overview
Realm of Strikes and Spells (RSS) is a turn-based role-playing game (RPG) where players lead a team of allies against various enemies in strategic battles. Players can choose characters of different races and classes, each offering unique abilities, and navigate multiple battle maps to engage in tactical combat.

## Project Structure
```
📦 
.gitignore
.vscode/
│  └─ settings.json
├─ README.md
├─ lib/
│  └─ junit-platform-console-standalone-1.7.2.jar
└─ src/
   ├─ Main.java                                         # Main class to run the game
   ├─ assets/
   ├─ character/
   │  ├─ Character.java                                 # Core attributes and actions of characters.
   │  ├─ CharacterClass.java                            # Class abilities
   │  ├─ CharacterLabel.java                            # Animate ally states.
   │  ├─ EnemyLabel.java                                # Animates enemy states
   │  ├─ classes/
   │  │  ├─ Mage.java                                   # Mage class implementation
   │  │  ├─ Rogue.java                                  # Rogue class implementation
   │  │  └─ Warrior.java                                # Warrior class implementation
   │  ├─ enemyRaces/
   │  │  ├─ Golem.java                                  # Golem race implementation
   │  │  ├─ Reaper.java                                 # Reaper race implementation
   │  │  └─ Zombie.java                                 # Zombie race implementation
   │  └─ races/
   │     ├─ Angel.java                                  # Angle race implementation
   │     ├─ Minotaur.java                               # Minotaur race implementation
   │     └─ Orc.java                                    # Orc race implementation
   ├─ screen/
   │  ├─ BattleScreen.java                              # UI for the battle screen
   │  ├─ BeginOverlay.java                              # Overlay for starting the battle
   │  ├─ DiceOverlay.java                               # Displaying dice rolls
   │  ├─ GameEnd.java                                   # Displaying game results
   │  ├─ LoadingOverlay.java                            # Display loading screen
   │  ├─ MainMenu.java                                  # UI for game menu
   │  ├─ SelectionListener.java                         # Handles menu and game interactions
   │  ├─ log/
   │  │  └─ Log.java                                    # Class for displaying in-game logs
   │  └─ menu/
   │     ├─ BattleLogReader.java                        # Reads and displays battle logs.
   │     ├─ CharacterSelection.java                     # UI for selecting characters and their classes
   │     ├─ GuideMenu.java                              # User guide
   │     └─ MapSelection.java                           # UI for maps selection
   └─ test/
      ├─ BeginOverlayTest.java                          # Unit tests for BeginOverlay.java
      ├─ CharacterTest.java                             # Unit tests for Character.java
      ├─ DiceOverlayTest.java                           # Unit tests for DiceOverlay.java
      ├─ GameEndTest.java                               # Unit tests for GameEnd.java
      └─ LogTest.java                                   # Unit tests for Log.java

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
