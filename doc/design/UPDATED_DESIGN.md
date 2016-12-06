## Introduction
The primary goal of the project is to design a game maker engine which specializes in 2D RPG style games, like Pokemon, Evoland, Zelda. The game that can be constructed are all similar in that there are set number of moveable locations defined in a grid, with only one possible element (character, landscape object, etc) occupying that space. Hence, for example, one’s player would not be able to stand in a tree cell. We will have clickable cells where the user can define the map, by placing ui.scenes.editor.objects there. For certain ui.scenes.editor.objects, like enemy players we will have settable attributes (like attack speed, attack damage, magic damage, etc). On the high level, after normal menu screen navigation, we create a new game with a set number of cells. There is a map which shows the relevant subsection of cells (which your character is standing on) and immediately surrounding locations as well as a minimap which displays how your region is in the entire frame of the world. Inside the game, there will be interactions with the environment and other characters that define the progression of the game until the condition is satisfied and the game is finally won.


## Overview
The general design of our program is split up into a front-end (the view) and a back-end (the model). The two main parts of the application will be game creation (editor) and gameplay (engine). The game creation aspect allows the user to create and customize a game, and the gameplay version allows the user to actually play the game. When creating the game, the user will be able to design a grid block by block and specify the state of each block, which can be a decoration, obstacle, or interactable object. The user also adds a player, which can be customized with a name and attributes. The player is stored in the backend, but not as a block. This way, the model can easily store what each location in the game represents, while the view can then see an abstraction of these details and separately decide how to display the graphics. The editor itself can be saved, or the game that is created can be exported (to be played). Within the editor, previously saved editors can be opened. In the game engine, the grid of blocks that is loaded is rendered, as well as the player. The user can then control the movement of the player in the grid and play the game.


## User Interface
At the home page, the user can select between build and play mode. In the play mode, an XML file with a standard overworld design will be loaded. In build mode, the user can build the overworld from a blank canvas and can choose to add obstacles and other NPCs, then the design is saved as an XML file. The canvas is represented as a grid of cells and, in build mode, there is a side menu where ui.scenes.editor.objects can be added to the cells by clicking the cell, then selecting an object that appears in a pop up box. The user can also select multiple number of cells by using the mouse to drag and release across the area they desire and are then able to select the object they want to fill each of the selected cells with from a pop up box.
As for the game mode UI, there will be a grid display with the main character as the center of the section of the overworld being displayed (Pokemon display style). There will also be a side tab with the current game statistics, e.g. player health points, player experience points, remaining enemies. Dialog will appear as a pop up box at the bottom of the grid display. The game play will take in keyboard input to control the character moving through the overworld.


## Design Details


### Front-end 
The front-end is separated from the back-end. The role of the View is as follows. First, the View must be able to take input from the users and then notify the back-end those user inputs. Second, the View must be able to display various block types on the UI. The key components of the View are as follows: GameEditor, GameRunner, Menu, GameObject and SceneManger.


The Game Editor module handles the creation of the game which entails creating the map, NPCs, dialogue and populating these on the Grid.  Grids, menus and user info must be initialized once the editor is first run.  For the Grid, cells will be selected to store various game ui.scenes.editor.objects including ground types, obstacles, doors to other grids, and non-player-characters with which the user can talk.  Selection can be done via highlighting cells or clicking and dragging components into the grid.  Regardless, there are numerous edge conditions will need to be checked in order to make sure the ui.scenes.editor.objects can fit in the allotted space.  For NPCs, we will need to initialize them, define their behavior (monster, trainer, etc) and provide interaction editing between the NPC and player.  With all of these we will need to be able to load a saved Game Creation and be able to edit it (also entails being able to save).  For the sake of frontend, we will most likely only have to read the loaded file from the Grid and animate (back end will handle saving and loading the Grid itself).   In addition, we need to add a separate window for editing characters (NPC, player, etc).  On this, we will be able to upload images and add other attributes to build a character.


The GameEngine module is in charge of running the game using the data from the game engine. The EngineController class is in charge of retrieving the grid world and player that is created after parsing the data  in the XML format. The game on the stage is set using the initGame() method. GameRunner also displays the stats of the player. The Stat Class has three main methods: init() to initialize the front-end elements on the UI that display the stats, getStats() to get the current stats from the model and setStats() to update the stats on the model. In the GameEngine class, an object of the GridUI class is instantiated to initialize an overworld. The key methods of the GridUI are created using init() to initialize and set the screen as a battlefield/overworld, setPlayers() to set the current players, setMonsters() to current monsters and endGame() when the users win or lose.


The UILauncher class creates the initial main menu, via the MainMenu class, that will allow the user to start a new editor or load a created game. The GameEngine class handles the front end UI for the game engine and transitioning from the current game back to the main menu. The GameEditor class handles the front end UI for the game editor that is used to build new games. The SizeChooser class, called upon initializing the editor, prompts the user to pick an initial overworld size to begin creating. The user can also choose a custom overworld map or just open a previous editor file instead. The ItemPanel class is used to generate the menu tabs containing ui.scenes.editor.objects to place on the editor, such as ground panels, obstacles, or non-player characters. There is also a button for generating a new overworld and a UI for creating new grids to connect to other grids via portals (such as doors into other buildings). There is a MenuBar UI that will have various menus used for saving or opening editor files, exporting an editor file to a playable game, or testing a game that is currently being built. There will be some kind of SceneChanger class that handles all of the different grids and the connections between them.


##Integration
There are two controllers: one for the editor and one for the engine. These controllers allow the frontend and backend to communicate with one another. The editor controller contains an addBlock() method that is called from the frontend in order to update a block in the grid. The controller then adds this block to the grid stored in the backend. The same procedure is performed with a player object, only a player is not stored as a block and is instead its row and column are stored. In order for the frontend to render a grid (after loading a grid in), it calls the getBlock() method in the controller and can render the grid block by block based on the string returned. The engine controller also contains a getBlock() method in order for the frontend to display each block that is contained within the file describing the game that is loaded. The player is also loaded and its row and column are received by the frontend so that the player can be displayed in a specific location on the grid.


### Back-end
The map is generally stored as a grid of blocks. This way, the back-end can track the state of each position in the map, and these details are abstracted away from the front-end. Then the front-end can separately decide how to display the graphics of the blocks.


The Player object represents the main character(s) of the game, and everything centers and interacts with this class. 


The Status and WinCondition modules allow users to specify win conditions and statuses of different attributes, which allows for a great deal of flexibility in terms of design.


The interactions could include player-block interactions (player walking around in the grid) and battle interactions (characters battling each other). This way, there can be a generalized event history for everything that occurs throughout the history of a game. This makes the design flexible because it allows users to specify a variety of interactions.


The Mode module allows the user to specify different types of modes, such as battle or walkaround mode, or any mode that the user wants. This is an integral part of the game design that performs an integral task in this program.


The Item module allows the user to define different types of items that a GamePlayer can use to interact with other ui.scenes.editor.objects in the grid. The user can define items that can have a variety of effects on other ui.scenes.editor.objects, such as HP, speed, power, etc. 


The GameInstance module stores the stats of a certain game so that these can be easily displayed in the front-end. These stats are extracted into its own class to allow for greater code modularity and flexibility.


The DataSaver and DataReader modules are used to save and load game states into an XML file. This way, a user can load up pre-made games and does not need to design the same games over and over again. We decided to extract these functions into their own dedicated classes to allow for more code modularity and flexibility. 


## Example Games


### Pokemon
The Pokemon video game series is arguably the most popular RPG series of games ever created. A player is accompanied by a party of Pokemon monsters that are used to battle various other trainers and explore the Pokemon world. Our environment allows for the creation of a open world to explore along with adding NPCs and giving these NPCs certain characteristics/weapons. In addition, it will be possible to interact with the NPCs, some of which you will be able to battle. Finally, there will be buildings that can be explored and even areas of the world that can only be accessed once a level has been reached. Given all of these base settings, the following games should also be able to be supported.


### Zelda
Zelda games (2D exploring ones like awakening): revolve around exploring the world, interacting with NPCs, exploring dungeons and battling low level enemies along with bosses.  Our authoring environment will allow the creation of the open world by default.  An extension that may be challenging is the dungeons, but these can be viewed as an extension of building a world, thus when a dungeon is encountered, we would just have to make sure we can allow traversal between the world and dungeon.  Other than that, we allow the creation of unique NPCs and an open world, thus this game should be feasible to build.


### Dragon Warrior / Dragon Quest
Similar to Pokemon, Dragon Warrior involves exploring a 2D overworld with an arsenal of “monsters” used for battling enemies and capturing other “monsters.” The basic premise of the Dragon Warrior games follows a story wherein the player represents a hero that sets out to save the land from peril caused by a super-evil enemy. Our game authoring environment will support creating an overworld along with NPCs with which the user can interact. However, unlike Pokemon and Zelda, a super-evil boss can be created that would serve as the winning condition, which is unique to Dragon Warrior (Pokemon, for example, is won when all battle-able players have been defeated). Furthermore, the goal of the game can be customized through the NPCs’ dialogues; the goal of “saving the land from peril” could be reflected in a game created through our game authoring environment by selecting dialogue that crafts this particular story.


## Design Considerations
### Front-end
* We need to think about how the grids are getting the updates from the back-end. We’re considering observer pattern. The observer pattern allows the view to be updated by the model while the view is encapsulated from the model.
* We need to consider how to place blocks in the workspace.  There are numerous edge cases to consider giving the design we choose.  We need to account for placing items on top of each other of different sizes and for creating items with different characteristics, like obstacles versus ground panels.
* We need to consider how to animate the grid.  It is a mix of back end and front end so division of labor is confusing.  For updating the grid, we need to consider how we handle animations.  Ideally, the grid will move around the player, giving the illusion that the player is navigating throughout the overworld. Obstacles will be updated as the sprite walks around the world so that the player is not allowed to walk over trees, rocks, or other un-walkable ui.scenes.editor.objects.


### Back-end
* We need to consider how to generalize all the types of interactions. These interactions could include player-block interactions (player walking around in the grid) and battle interactions (characters battling each other). This way, there can be a generalized event history for everything that occurs throughout the history of a game.
* We need to consider how we animate the grid.  The grid is a mix of both back end and front end so we need to figure out a flexible way to integrate things, because the grid is the entry point into the display and control of the entire program.  


## Responsibilities
Robert, Teddy, Harshil, Nisa and Pim are responsible for the front-end. The specific features that they are responsible of are as follows:
* Robert - Game editor menu bar, overall layout, 
* Teddy - Selecting from menu to grid, Grid editor animation
* Harshil - Menu, clicking and dragging to the map, and pinging backend
* Nisa - Game editor, game ui.scenes.editor.objects
* Pim - Game runner, getting the data from the game editor and run the game


Filip, Bill, Aninda, Daniel and Ryan are responsible for the back-end. The specific features that they are responsible of are as follows:
* Filip - Grid and block contents, Interactions
* Bill - Items
* Aninda - Player 
* Daniel - Interactions
* Ryan - Grid and Block contents
