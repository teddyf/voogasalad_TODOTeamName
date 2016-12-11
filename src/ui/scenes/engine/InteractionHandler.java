package ui.scenes.engine;

import block.BlockUpdate;
import javafx.scene.Parent;
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
        ComponentProperties prop = new ComponentProperties();
        prop.text(message);
        prop.height(100);
        prop.width(600);
        myUIBuilder.addDialogBubble(myRoot, prop);
    }

    public void enterBattle() {

    }

    public void winGame() {
        // TODO: win the game and display win message
    }

    public void reRenderBlock(int row, int col, String imagePath) {
        System.out.println("re-render");
        System.out.println(imagePath);
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
            case WIN_GAME:
                winGame();
                break;
            default:
                break;
        }
    }
}
