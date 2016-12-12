package ui.scenes.engine;

import battle.controller.BattleController;
import battle.model.BattleModel;
import battle.model.Difficulty;
import battle.view.BattleView;
import block.BlockUpdate;
import block.EnemyBlock;
import javafx.scene.Parent;
import javafx.stage.Stage;
import player.Player;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * This class handles interactions on the front end.
 * @author Aninda Manocha, Filip Mazurek
 */

public class InteractionHandler {
    private Parent myRoot;
    private UIBuilder myUIBuilder;
    private GridForEngine myGridForEngine;

    public InteractionHandler(Parent root, UIBuilder uiBuilder, GridForEngine grid) {
        myRoot = root;
        myUIBuilder = uiBuilder;
        myGridForEngine = grid;
    }

    public void displayMessage(String message) {
        myUIBuilder.addDialogBubble(myRoot, message);
    }

    private void renderTeleportation(int r, int c) {

    }

    private void winGame() {
        // TODO: win the game and display win message
    }

    private void reRenderBlock(int row, int col, String imagePath) {
        myGridForEngine.reRender(row, col, imagePath);
    }

    public void handleUpdate(BlockUpdate blockUpdate) {
        switch (blockUpdate.getType()) {
            case BATTLE:
                break;
            case DISPLAY_MESSAGE:
                displayMessage(blockUpdate.getContent());
                break;
            case RE_RENDER:
                reRenderBlock(blockUpdate.getRow(), blockUpdate.getColumn(), blockUpdate.getContent());
                break;
            case TELEPORT:
                renderTeleportation(blockUpdate.getRow(), blockUpdate.getColumn());
                break;
            case WIN_GAME:
                winGame();
                break;
            default:
                break;
        }
    }
}
