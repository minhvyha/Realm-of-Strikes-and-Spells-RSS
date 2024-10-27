# Realm of Strikes and Spells (RSS)

## Overview

Realm of Strikes and Spells (RSS) is a turn-based role-playing game (RPG) where players lead a team of allies against various enemies in strategic battles. Players can choose characters of different races and classes, each offering unique abilities, and navigate multiple battle maps to engage in tactical combat.

## Project Structure

```
├─ README.md
└─ src/
   ├─ Main.java                          # Main class to run the game
   ├─ assets/
   ├─ character/
   │  ├─ Character.java                  # Core attributes and actions of characters.
   │  ├─ CharacterClass.java             # Class abilities
   │  ├─ CharacterLabel.java             # Animate ally states.
   │  ├─ EnemyLabel.java                 # Animates enemy states
   │  ├─ classes/
   │  │  ├─ Mage.java                    # Mage class implementation
   │  │  ├─ Rogue.java                   # Rogue class implementation
   │  │  └─ Warrior.java                 # Warrior class implementation
   │  ├─ enemyRaces/
   │  │  ├─ Golem.java                   # Golem race implementation
   │  │  ├─ Reaper.java                  # Reaper race implementation
   │  │  └─ Zombie.java                  # Zombie race implementation
   │  └─ races/
   │     ├─ Angel.java                   # Angle race implementation
   │     ├─ Minotaur.java                # Minotaur race implementation
   │     └─ Orc.java                     # Orc race implementation
   ├─ screen/
   │  ├─ BattleScreen.java               # UI for the battle screen
   │  ├─ BeginOverlay.java               # Overlay for starting the battle
   │  ├─ DiceOverlay.java                # Displays dice rolls
   │  ├─ GameEnd.java                    # Displays the game result
   │  ├─ LoadingOverlay.java             # Displays loading screen
   │  ├─ MainMenu.java                   # UI for game menu
   │  ├─ Listener.java                   # Handles menu and game interactions
   │  ├─ log/
   │  │  └─ Log.java                     # Class for displaying in-game logs
   │  └─ menu/
   │     ├─ BattleLogReader.java         # Reads and displays battle logs.
   │     ├─ CharacterSelection.java      # UI for selecting characters and their classes
   │     ├─ GuideMenu.java               # User guide
   │     └─ MapSelection.java            # UI for maps selection
   └─ test/
      ├─ BeginOverlayTest.java           # Unit tests for BeginOverlay.java
      ├─ CharacterTest.java              # Unit tests for Character.java
      ├─ DiceOverlayTest.java            # Unit tests for DiceOverlay.java
      ├─ GameEndTest.java                # Unit tests for GameEnd.java
      └─ LogTest.java                    # Unit tests for Log.java

```
### Organize project directories for game assets and components

- **assets/**: Contains game assets.
- **character/**: Includes classes for character attributes and behaviors, categorized by classes (Mage, Rogue, Warrior) and races (Golem, Reaper, Angel, Minotaur).
- **screen/**: Houses UI components like battle screens, menus, and overlays (e.g., BattleScreen.java, GameEnd.java).
- **test/**: Contains unit tests for various components, ensuring each part of the game functions correctly.

![6b7e4fd7](https://github.com/user-attachments/assets/b269d2f6-85da-49aa-9e55-9ba4305014e1)

## Character Classes and Races

Characters in RSS are defined by both their race and class, each contributing unique attributes and abilities:

### Races:

#### Allies:

- **Minotaur**: High HP, attack but low agility and intelligence.
- **Orc**: High HP, attack but low agility and intelligence.
- **Angel**: High defense, intelligence and agility but low HP and attack.

#### Enemies:

- **Golem**: High HP, attack and defense, but low agility and intelligence.
- **Reaper**: High attack and agility.
- **Zombie**: High HP.

### Classes:

- **Mage**: deal 1.5x damage when target's HP is high.
- **Rogue**: doubles damage to low-HP targets.
- **Warrior**: excels when HP is low, dealing 1.5x damage.

Each class executes a unique skill through the `useClassAbility` method in combat, adding variety and strategy to gameplay.

### Game Screens and UI Components

RSS provides interactive UI screens for different phases of gameplay:

- **Main Menu**: Starting screen, with options to play, choose allies, select maps, view guides, or quit.
- **Character Selection**: Choose team members, customizing their race and class for a strategic advantage.
- **Map Selection**: Select one of several battle environments, including Enchanted Forest, Frozen Tundra, and Dungeon Chambers.
- **Battle Log Reader**: Displays a history of battle events, including attacks, defense moves, special abilities used, and turn outcomes, allowing players to review each action and strategize.
- **Battle Screen**: Engages players in combat, displaying characters, health bars, and action buttons (Strike, Defense Stand, Special Ability).
- **Overlays**: Screens like `LoadingOverlay`, `BeginOverlay`, and `DiceOverlay` enhance visual transitions and effects.
- **End Game Screen**: Displays the outcome when the battle ends.

## Technologies Used

- **Java**: Core programming language.
- **Java Swing**: Used for creating graphical user interface components.
- **JUnit**: For unit testing game components.

## How to Run the Program

1. **Clone the repository:**
   ```bash
   git clone https://github.com/minhvyha/Realm-of-Strikes-and-Spells-RSS.git
   ```
1. **Compile the project:**
   ```bash
   javac -d bin $(find src -name "*.java" ! -path "src/test/*")
   ```
1. **Run the main class:**
   ```bash
   java -cp bin Main
   ```

## Description of the feature or task>

- **Percentage of Work:**
  - **Minh Vy Ha (35%)**
    - Main contributor, designed the core game mechanics and implemented character classes, redacted code for readability.
    - Created Character Class Structure with attributes like HP, strength, intelligence, and defense.
    - Implemented Character Inheritance with subclasses based on race (Orc, Elf, Alien) and class (Rogue, Barbarian, Mage).
    - Developed Combat System, determining turn order using stats like initiative and randomization with dice rolls.
    - Created an Equipment System for characters to equip items that modify base stats.
    - Handled Status Effects management, including "Poisoned" and "Wounded."

  - **Minying Wu (25%)**
    - Developed part of the UI component of battle, including buttons, and assisted in implementing game mechanics.
    - Designed Action Methods (e.g., attack(), defend(), special abilities).
    - Implemented Dice Rolls and Randomness for damage modifiers and success chances.
    - Managed Teams and Victory Conditions tracking (e.g., when all characters on one team have 0 HP).
    - Created part of the Map Lock system and added a navigation bar for improved game UI.
    - Documented the process for Section B.

  - **Khoi Nguyen (20%)**
    - Designed unit tests for Character, enhancing overall testing framework.
    - Assisted in Equipment and Combat systems.
    - Streamlined testing process and implemented endgame tests.
    - Debugged issues.

  - **Daniel Huynh (20%)**
    - Assisted in implementing game mechanics and debugging.
    - Documented processes.

## Application Solves
- **Character Test**
  - Enables damage calculation during attacks, factoring in attacker's strength and target's defense.
  - Implements damage computation with variability from dice rolls.
  - Contains specific damage calculation logic for different character classes (e.g., `calculateWarriorDamage`, `calculateMageDamage`).

- **Character Abilities**
  - Validates that special abilities for character classes work correctly, ensuring strengths are represented in mechanics.
  - Tests include:
    - `testAttackDamage`: Validates regular attack damage calculation and health decrease.
    - `testSpecialAbility`: Validates special abilities for Warrior and Mage classes.

## Class
- **Example of class for character**
  ![09ce80fd](https://github.com/user-attachments/assets/5043a777-0dfa-450f-b599-00a471e044bb)


- **Example of class for GameEnd**
  ![4a7c1ceb](https://github.com/user-attachments/assets/0a30429b-0ed9-4cca-8011-1ac59bf4668f)



