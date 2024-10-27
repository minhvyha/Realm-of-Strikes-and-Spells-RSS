# Realm of Strikes and Spells (RSS)

## Overview
Realm of Strikes and Spells (RSS) is a turn-based role-playing game (RPG) where players lead a team of allies against various enemies in strategic battles. Players can choose characters of different races and classes, each offering unique abilities, and navigate multiple battle maps to engage in tactical combat.

## Project Structure
```
📦 
.gitignore
.vscode
│  └─ settings.json
├─ README.md
├─ battle_log.csv
├─ battle_log_1.csv
├─ battle_log_2.csv
├─ battle_log_3.csv
├─ bin
│  ├─ Main$1.class
│  ├─ Main$10.class
│  ├─ Main$11.class
│  ├─ Main$12.class
│  ├─ Main$13.class
│  ├─ Main$14.class
│  ├─ Main$2.class
│  ├─ Main$3.class
│  ├─ Main$4.class
│  ├─ Main$5.class
│  ├─ Main$6.class
│  ├─ Main$7.class
│  ├─ Main$8.class
│  ├─ Main$9.class
│  ├─ Main.class
│  ├─ assets
│  │  ├─ Orc
│  │  │  ├─ Attack
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Die
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 12.png
│  │  │  │  ├─ 13.png
│  │  │  │  ├─ 14.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Hurt
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  └─ Idle
│  │  │     ├─ 0.png
│  │  │     ├─ 1.png
│  │  │     ├─ 10.png
│  │  │     ├─ 11.png
│  │  │     ├─ 12.png
│  │  │     ├─ 13.png
│  │  │     ├─ 14.png
│  │  │     ├─ 15.png
│  │  │     ├─ 16.png
│  │  │     ├─ 17.png
│  │  │     ├─ 2.png
│  │  │     ├─ 3.png
│  │  │     ├─ 4.png
│  │  │     ├─ 5.png
│  │  │     ├─ 6.png
│  │  │     ├─ 7.png
│  │  │     ├─ 8.png
│  │  │     └─ 9.png
│  │  ├─ RSS.png
│  │  ├─ angel
│  │  │  ├─ Attack
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Die
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 12.png
│  │  │  │  ├─ 13.png
│  │  │  │  ├─ 14.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Hurt
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  └─ Idle
│  │  │     ├─ 0.png
│  │  │     ├─ 1.png
│  │  │     ├─ 10.png
│  │  │     ├─ 11.png
│  │  │     ├─ 12.png
│  │  │     ├─ 13.png
│  │  │     ├─ 14.png
│  │  │     ├─ 15.png
│  │  │     ├─ 16.png
│  │  │     ├─ 17.png
│  │  │     ├─ 2.png
│  │  │     ├─ 3.png
│  │  │     ├─ 4.png
│  │  │     ├─ 5.png
│  │  │     ├─ 6.png
│  │  │     ├─ 7.png
│  │  │     ├─ 8.png
│  │  │     └─ 9.png
│  │  ├─ background
│  │  │  ├─ battleback1.png
│  │  │  ├─ battleback2.png
│  │  │  ├─ battleback3.png
│  │  │  ├─ battleback4.png
│  │  │  ├─ battleback5.png
│  │  │  ├─ battleback6.png
│  │  │  ├─ battleback7.png
│  │  │  ├─ battleback8.png
│  │  │  └─ battleback9.png
│  │  ├─ dice
│  │  │  ├─ 1.png
│  │  │  ├─ 2.png
│  │  │  ├─ 3.png
│  │  │  ├─ 4.png
│  │  │  ├─ 5.png
│  │  │  └─ 6.png
│  │  ├─ golem
│  │  │  ├─ Attack
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Die
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 12.png
│  │  │  │  ├─ 13.png
│  │  │  │  ├─ 14.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Hurt
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  └─ Idle
│  │  │     ├─ 0.png
│  │  │     ├─ 1.png
│  │  │     ├─ 10.png
│  │  │     ├─ 11.png
│  │  │     ├─ 12.png
│  │  │     ├─ 13.png
│  │  │     ├─ 14.png
│  │  │     ├─ 15.png
│  │  │     ├─ 16.png
│  │  │     ├─ 17.png
│  │  │     ├─ 2.png
│  │  │     ├─ 3.png
│  │  │     ├─ 4.png
│  │  │     ├─ 5.png
│  │  │     ├─ 6.png
│  │  │     ├─ 7.png
│  │  │     ├─ 8.png
│  │  │     └─ 9.png
│  │  ├─ lock.png
│  │  ├─ logo.png
│  │  ├─ minotaur
│  │  │  ├─ Attack
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Die
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 12.png
│  │  │  │  ├─ 13.png
│  │  │  │  ├─ 14.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Hurt
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  └─ Idle
│  │  │     ├─ 0.png
│  │  │     ├─ 1.png
│  │  │     ├─ 10.png
│  │  │     ├─ 11.png
│  │  │     ├─ 12.png
│  │  │     ├─ 13.png
│  │  │     ├─ 14.png
│  │  │     ├─ 15.png
│  │  │     ├─ 16.png
│  │  │     ├─ 17.png
│  │  │     ├─ 2.png
│  │  │     ├─ 3.png
│  │  │     ├─ 4.png
│  │  │     ├─ 5.png
│  │  │     ├─ 6.png
│  │  │     ├─ 7.png
│  │  │     ├─ 8.png
│  │  │     └─ 9.png
│  │  ├─ reaper
│  │  │  ├─ Attack
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Die
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 12.png
│  │  │  │  ├─ 13.png
│  │  │  │  ├─ 14.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  ├─ Hurt
│  │  │  │  ├─ 0.png
│  │  │  │  ├─ 1.png
│  │  │  │  ├─ 10.png
│  │  │  │  ├─ 11.png
│  │  │  │  ├─ 2.png
│  │  │  │  ├─ 3.png
│  │  │  │  ├─ 4.png
│  │  │  │  ├─ 5.png
│  │  │  │  ├─ 6.png
│  │  │  │  ├─ 7.png
│  │  │  │  ├─ 8.png
│  │  │  │  └─ 9.png
│  │  │  └─ Idle
│  │  │     ├─ 0.png
│  │  │     ├─ 1.png
│  │  │     ├─ 10.png
│  │  │     ├─ 11.png
│  │  │     ├─ 12.png
│  │  │     ├─ 13.png
│  │  │     ├─ 14.png
│  │  │     ├─ 15.png
│  │  │     ├─ 16.png
│  │  │     ├─ 17.png
│  │  │     ├─ 2.png
│  │  │     ├─ 3.png
│  │  │     ├─ 4.png
│  │  │     ├─ 5.png
│  │  │     ├─ 6.png
│  │  │     ├─ 7.png
│  │  │     ├─ 8.png
│  │  │     └─ 9.png
│  │  ├─ weapon
│  │  │  ├─ mage.png
│  │  │  ├─ rogue.png
│  │  │  └─ warrior.png
│  │  └─ zombie
│  │     ├─ Attack
│  │     │  ├─ 0.png
│  │     │  ├─ 1.png
│  │     │  ├─ 10.png
│  │     │  ├─ 11.png
│  │     │  ├─ 2.png
│  │     │  ├─ 3.png
│  │     │  ├─ 4.png
│  │     │  ├─ 5.png
│  │     │  ├─ 6.png
│  │     │  ├─ 7.png
│  │     │  ├─ 8.png
│  │     │  └─ 9.png
│  │     ├─ Die
│  │     │  ├─ 0.png
│  │     │  ├─ 1.png
│  │     │  ├─ 10.png
│  │     │  ├─ 11.png
│  │     │  ├─ 12.png
│  │     │  ├─ 13.png
│  │     │  ├─ 14.png
│  │     │  ├─ 2.png
│  │     │  ├─ 3.png
│  │     │  ├─ 4.png
│  │     │  ├─ 5.png
│  │     │  ├─ 6.png
│  │     │  ├─ 7.png
│  │     │  ├─ 8.png
│  │     │  └─ 9.png
│  │     ├─ Hurt
│  │     │  ├─ 0.png
│  │     │  ├─ 1.png
│  │     │  ├─ 10.png
│  │     │  ├─ 11.png
│  │     │  ├─ 2.png
│  │     │  ├─ 3.png
│  │     │  ├─ 4.png
│  │     │  ├─ 5.png
│  │     │  ├─ 6.png
│  │     │  ├─ 7.png
│  │     │  ├─ 8.png
│  │     │  └─ 9.png
│  │     └─ Idle
│  │        ├─ 0.png
│  │        ├─ 1.png
│  │        ├─ 10.png
│  │        ├─ 11.png
│  │        ├─ 12.png
│  │        ├─ 13.png
│  │        ├─ 14.png
│  │        ├─ 15.png
│  │        ├─ 16.png
│  │        ├─ 17.png
│  │        ├─ 2.png
│  │        ├─ 3.png
│  │        ├─ 4.png
│  │        ├─ 5.png
│  │        ├─ 6.png
│  │        ├─ 7.png
│  │        ├─ 8.png
│  │        └─ 9.png
│  ├─ character
│  │  ├─ Character.class
│  │  ├─ CharacterClass.class
│  │  ├─ CharacterLabel$1.class
│  │  ├─ CharacterLabel.class
│  │  ├─ EnemyLabel$1.class
│  │  ├─ EnemyLabel.class
│  │  ├─ classes
│  │  │  ├─ Mage.class
│  │  │  ├─ Rogue.class
│  │  │  └─ Warrior.class
│  │  ├─ enemyRaces
│  │  │  ├─ Golem.class
│  │  │  ├─ Reaper.class
│  │  │  └─ Zombie.class
│  │  └─ races
│  │     ├─ Angel.class
│  │     ├─ Minotaur.class
│  │     └─ Orc.class
│  ├─ screen
│  │  ├─ BattleScreen$1.class
│  │  ├─ BattleScreen$10.class
│  │  ├─ BattleScreen$11.class
│  │  ├─ BattleScreen$12$1.class
│  │  ├─ BattleScreen$12.class
│  │  ├─ BattleScreen$13.class
│  │  ├─ BattleScreen$14$1.class
│  │  ├─ BattleScreen$14.class
│  │  ├─ BattleScreen$2.class
│  │  ├─ BattleScreen$3.class
│  │  ├─ BattleScreen$4.class
│  │  ├─ BattleScreen$5.class
│  │  ├─ BattleScreen$6.class
│  │  ├─ BattleScreen$7.class
│  │  ├─ BattleScreen$8.class
│  │  ├─ BattleScreen$9.class
│  │  ├─ BattleScreen.class
│  │  ├─ BeginOverlay$1.class
│  │  ├─ BeginOverlay$2.class
│  │  ├─ BeginOverlay.class
│  │  ├─ DiceOverlay$1.class
│  │  ├─ DiceOverlay$2.class
│  │  ├─ DiceOverlay.class
│  │  ├─ GameEnd$1.class
│  │  ├─ GameEnd$2.class
│  │  ├─ GameEnd.class
│  │  ├─ LoadingOverlay$1.class
│  │  ├─ LoadingOverlay$2.class
│  │  ├─ LoadingOverlay.class
│  │  ├─ MainMenu$1.class
│  │  ├─ MainMenu$2.class
│  │  ├─ MainMenu$3.class
│  │  ├─ MainMenu$4.class
│  │  ├─ MainMenu$5.class
│  │  ├─ MainMenu$6.class
│  │  ├─ MainMenu$7.class
│  │  ├─ MainMenu$8.class
│  │  ├─ MainMenu.class
│  │  ├─ SelectionListener.class
│  │  ├─ log
│  │  │  ├─ Log$LogNode.class
│  │  │  └─ Log.class
│  │  └─ menu
│  │     ├─ BattleLogReader$1.class
│  │     ├─ BattleLogReader.class
│  │     ├─ CharacterSelection.class
│  │     ├─ GuideMenu.class
│  │     ├─ MapSelection$1.class
│  │     └─ MapSelection.class
│  └─ test
│     ├─ BeginOverlayTest.class
│     ├─ CharacterTest.class
│     ├─ DiceOverlayTest.class
│     ├─ GameEndTest.class
│     └─ LogTest.class
├─ lib
│  └─ junit-platform-console-standalone-1.7.2.jar
└─ src
   ├─ Main.java
   ├─ assets
   │  ├─ RSS.png
   │  ├─ angel
   │  │  ├─ Attack
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Die
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 12.png
   │  │  │  ├─ 13.png
   │  │  │  ├─ 14.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Hurt
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  └─ Idle
   │  │     ├─ 0.png
   │  │     ├─ 1.png
   │  │     ├─ 10.png
   │  │     ├─ 11.png
   │  │     ├─ 12.png
   │  │     ├─ 13.png
   │  │     ├─ 14.png
   │  │     ├─ 15.png
   │  │     ├─ 16.png
   │  │     ├─ 17.png
   │  │     ├─ 2.png
   │  │     ├─ 3.png
   │  │     ├─ 4.png
   │  │     ├─ 5.png
   │  │     ├─ 6.png
   │  │     ├─ 7.png
   │  │     ├─ 8.png
   │  │     └─ 9.png
   │  ├─ background
   │  │  ├─ battleback1.png
   │  │  ├─ battleback2.png
   │  │  ├─ battleback3.png
   │  │  ├─ battleback4.png
   │  │  ├─ battleback5.png
   │  │  ├─ battleback6.png
   │  │  ├─ battleback7.png
   │  │  ├─ battleback8.png
   │  │  └─ battleback9.png
   │  ├─ dice
   │  │  ├─ 1.png
   │  │  ├─ 2.png
   │  │  ├─ 3.png
   │  │  ├─ 4.png
   │  │  ├─ 5.png
   │  │  └─ 6.png
   │  ├─ golem
   │  │  ├─ Attack
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Die
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 12.png
   │  │  │  ├─ 13.png
   │  │  │  ├─ 14.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Hurt
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  └─ Idle
   │  │     ├─ 0.png
   │  │     ├─ 1.png
   │  │     ├─ 10.png
   │  │     ├─ 11.png
   │  │     ├─ 12.png
   │  │     ├─ 13.png
   │  │     ├─ 14.png
   │  │     ├─ 15.png
   │  │     ├─ 16.png
   │  │     ├─ 17.png
   │  │     ├─ 2.png
   │  │     ├─ 3.png
   │  │     ├─ 4.png
   │  │     ├─ 5.png
   │  │     ├─ 6.png
   │  │     ├─ 7.png
   │  │     ├─ 8.png
   │  │     └─ 9.png
   │  ├─ lock.png
   │  ├─ logo.png
   │  ├─ minotaur
   │  │  ├─ Attack
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Die
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 12.png
   │  │  │  ├─ 13.png
   │  │  │  ├─ 14.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Hurt
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  └─ Idle
   │  │     ├─ 0.png
   │  │     ├─ 1.png
   │  │     ├─ 10.png
   │  │     ├─ 11.png
   │  │     ├─ 12.png
   │  │     ├─ 13.png
   │  │     ├─ 14.png
   │  │     ├─ 15.png
   │  │     ├─ 16.png
   │  │     ├─ 17.png
   │  │     ├─ 2.png
   │  │     ├─ 3.png
   │  │     ├─ 4.png
   │  │     ├─ 5.png
   │  │     ├─ 6.png
   │  │     ├─ 7.png
   │  │     ├─ 8.png
   │  │     └─ 9.png
   │  ├─ orc
   │  │  ├─ Attack
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Die
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 12.png
   │  │  │  ├─ 13.png
   │  │  │  ├─ 14.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Hurt
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  └─ Idle
   │  │     ├─ 0.png
   │  │     ├─ 1.png
   │  │     ├─ 10.png
   │  │     ├─ 11.png
   │  │     ├─ 12.png
   │  │     ├─ 13.png
   │  │     ├─ 14.png
   │  │     ├─ 15.png
   │  │     ├─ 16.png
   │  │     ├─ 17.png
   │  │     ├─ 2.png
   │  │     ├─ 3.png
   │  │     ├─ 4.png
   │  │     ├─ 5.png
   │  │     ├─ 6.png
   │  │     ├─ 7.png
   │  │     ├─ 8.png
   │  │     └─ 9.png
   │  ├─ reaper
   │  │  ├─ Attack
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Die
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 12.png
   │  │  │  ├─ 13.png
   │  │  │  ├─ 14.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  ├─ Hurt
   │  │  │  ├─ 0.png
   │  │  │  ├─ 1.png
   │  │  │  ├─ 10.png
   │  │  │  ├─ 11.png
   │  │  │  ├─ 2.png
   │  │  │  ├─ 3.png
   │  │  │  ├─ 4.png
   │  │  │  ├─ 5.png
   │  │  │  ├─ 6.png
   │  │  │  ├─ 7.png
   │  │  │  ├─ 8.png
   │  │  │  └─ 9.png
   │  │  └─ Idle
   │  │     ├─ 0.png
   │  │     ├─ 1.png
   │  │     ├─ 10.png
   │  │     ├─ 11.png
   │  │     ├─ 12.png
   │  │     ├─ 13.png
   │  │     ├─ 14.png
   │  │     ├─ 15.png
   │  │     ├─ 16.png
   │  │     ├─ 17.png
   │  │     ├─ 2.png
   │  │     ├─ 3.png
   │  │     ├─ 4.png
   │  │     ├─ 5.png
   │  │     ├─ 6.png
   │  │     ├─ 7.png
   │  │     ├─ 8.png
   │  │     └─ 9.png
   │  ├─ weapon
   │  │  ├─ mage.png
   │  │  ├─ rogue.png
   │  │  └─ warrior.png
   │  └─ zombie
   │     ├─ Attack
   │     │  ├─ 0.png
   │     │  ├─ 1.png
   │     │  ├─ 10.png
   │     │  ├─ 11.png
   │     │  ├─ 2.png
   │     │  ├─ 3.png
   │     │  ├─ 4.png
   │     │  ├─ 5.png
   │     │  ├─ 6.png
   │     │  ├─ 7.png
   │     │  ├─ 8.png
   │     │  └─ 9.png
   │     ├─ Die
   │     │  ├─ 0.png
   │     │  ├─ 1.png
   │     │  ├─ 10.png
   │     │  ├─ 11.png
   │     │  ├─ 12.png
   │     │  ├─ 13.png
   │     │  ├─ 14.png
   │     │  ├─ 2.png
   │     │  ├─ 3.png
   │     │  ├─ 4.png
   │     │  ├─ 5.png
   │     │  ├─ 6.png
   │     │  ├─ 7.png
   │     │  ├─ 8.png
   │     │  └─ 9.png
   │     ├─ Hurt
   │     │  ├─ 0.png
   │     │  ├─ 1.png
   │     │  ├─ 10.png
   │     │  ├─ 11.png
   │     │  ├─ 2.png
   │     │  ├─ 3.png
   │     │  ├─ 4.png
   │     │  ├─ 5.png
   │     │  ├─ 6.png
   │     │  ├─ 7.png
   │     │  ├─ 8.png
   │     │  └─ 9.png
   │     └─ Idle
   │        ├─ 0.png
   │        ├─ 1.png
   │        ├─ 10.png
   │        ├─ 11.png
   │        ├─ 12.png
   │        ├─ 13.png
   │        ├─ 14.png
   │        ├─ 15.png
   │        ├─ 16.png
   │        ├─ 17.png
   │        ├─ 2.png
   │        ├─ 3.png
   │        ├─ 4.png
   │        ├─ 5.png
   │        ├─ 6.png
   │        ├─ 7.png
   │        ├─ 8.png
   │        └─ 9.png
   ├─ character
   │  ├─ Character.java
   │  ├─ CharacterClass.java
   │  ├─ CharacterLabel.java
   │  ├─ EnemyLabel.java
   │  ├─ classes
   │  │  ├─ Mage.java
   │  │  ├─ Rogue.java
   │  │  └─ Warrior.java
   │  ├─ enemyRaces
   │  │  ├─ Golem.java
   │  │  ├─ Reaper.java
   │  │  └─ Zombie.java
   │  └─ races
   │     ├─ Angel.java
   │     ├─ Minotaur.java
   │     └─ Orc.java
   ├─ screen
   │  ├─ BattleScreen.java
   │  ├─ BeginOverlay.java
   │  ├─ DiceOverlay.java
   │  ├─ GameEnd.java
   │  ├─ LoadingOverlay.java
   │  ├─ MainMenu.java
   │  ├─ SelectionListener.java
   │  ├─ log
   │  │  └─ Log.java
   │  └─ menu
   │     ├─ BattleLogReader.java
   │     ├─ CharacterSelection.java
   │     ├─ GuideMenu.java
   │     └─ MapSelection.java
   └─ test
      ├─ BeginOverlayTest.java
      ├─ CharacterTest.java
      ├─ DiceOverlayTest.java
      ├─ GameEndTest.java
      └─ LogTest.java

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
