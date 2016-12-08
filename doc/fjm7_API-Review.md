API Review
==========

The point of our API now is to not make it limiting. We are continually discovering new facets to the features which we need to implement. Even simple design decisions are being revisited--we had previously planned movement of our player character in the four cardinal directions, and limited it for strictness and to ensure that game design would be simplified for the game designer. However, as we progress and want to make the game engine flexible, we realized we might need to redesign the entire movement system and movement input if we would at any point later decide to add diagonal movement. Therefore it will be much easier now to design the code with the idea of implementing diagonal movement, but with the ability to artificially restrict it to only the four cardinal directions if the game designer so chooses.

My current work with the project focuses on the Grid, which is the way that the level is stored in the back end. I am also working on the ui.scenes.editor.objects which may be placed on the grid and the manners in which they will interact with the player. I am staying careful to make the ui.scenes.editor.objects and interactions as flexible as possible. If we would like to add any sort of ability to the player (e.g. jumping), I want to make that easy to implement. As of now, the plan is to allow the game engine to respond differently to different types of interaction classes. This way, if we want to add more functionality, we need only to add to the API of the game engine to work with some new interaction class.

As I said above, I am most excited to cleanly implement the types of interactions which will allow the player to have more types of actions and for the game designer to place more interesting ui.scenes.editor.objects. I am worried about how to implement connection among grids. Connections between grids will require placing the player character in a completely different location, and loading the grid to which the player character moves. I am not really looking forward to the grid switching.

I plan to implement more interactions and make the actual engine which decides the actual reaction when interactions occur. This will happen with my fellow back end developers.

As far as the use cases go, we are developing our own additional use cases using pencil and paper in order to cover the new features as we think of them--this allows us to have the specific items we need as we write the code.

We will need to implement custom errors in the near future. In the game engine, I feel it most suitable that the Controller will be able to run the entire model and throw out to the user any exceptions which it may catch.
