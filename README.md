# Realm of Strikes and Spells (RSS)
## Overview
Realm of Strikes and Spells (RSS) is a turn-based strategy game where players can select characters of different races and classes to engage in battles. The game features various characters, each with unique abilities and attributes, and allows players to choose their allies and battle maps.
## Project Structure
The project is organized into three main folders:
- **assets**: Contains visual assets for the game, such as character sprites and background images.
- **Characters**: Contains the core logic for character classes and races, as well as the battle simulation.
- **screen**: Contains the UI components for different screens in the game, such as character selection and battle screens.
## Characters
Characters in the game are divided into races and classes. Each character has unique attributes and abilities based on their race and class.
### Races
- **Elf**: High intelligence and defense but lower HP.
- **Orc**: High HP and strength but low intelligence.
- **Human**: Balanced stats.
### Classes
- **Mage**: Uses intelligence to deal high damage, especially effective against targets with high HP.
- **Rogue**: Uses strength to deal high damage, especially effective against targets with low HP.
- **Warrior**: Uses strength and defense to deal damage, especially effective when the warrior's HP is low.
## Key Files
### Characters
- **Character.java**: Base class for all characters, containing common attributes and methods.
-**CharacterClass.java**: Interface for character classes, defining the useClassAbility method. This method is called to execute the special ability of the character class.
- **Elf.java, Orc.java, Human.java**: Implementations of different races.
- **Mage.java, Rogue.java, Warrior.java**: Implementations of different classes.
- **BattleSimulator.java**: To simulate battles between characters.
### Screen 
- **BattleScreen.java**: UI for the battle screen, displaying characters and handling user interactions. Includes health bars for characters.
- **CharacterSelection.java**: UI for selecting characters and their classes.
- **LoadingOverlay.java**: UI overlay for displaying loading animations.
- **MapSelection.java**: UI for selecting the battle map.
- **SelectionListener.java**: Interface for handling selection events.
- **Main.java**: Main entry point of the application, handling the overall game flow and screen transitions.
## How to Run
1. **Clone the repository**:
 git clone https://github.com/minhvyha/Realm-of-Strikes-and-Spells-RSS.git
2. **Compile the project**:
 javac -d bin src/**/*.java
3. **Run the main class**:
 java -cp bin Main
## Usage
1. **Character Selection**: Choose your allies by selecting their race and class.
2. **Map Selection**: Choose the battle map.
3. **Battle**: Engage in battles, using different abilities and strategies to defeat your enemies.
1. character