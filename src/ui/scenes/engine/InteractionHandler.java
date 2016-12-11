package ui.scenes.engine;

import block.BlockUpdate;
import javafx.scene.Parent;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * This class handles interactions on the front end.
 * @author Aninda Manocha, Filip Mazurek, Bill Xiong
 */

public class InteractionHandler {
    private Parent myRoot;
    private UIBuilder myUIBuilder;

    public InteractionHandler(Parent root, UIBuilder uiBuilder) {
        myRoot = root;
        myUIBuilder = uiBuilder;
    }

    public void displayMessage(String message) {
        ComponentProperties prop = new ComponentProperties();
//        prop.text(message);
        String msg = "You have to fight all these mother-effers to pass onto the nxt our and bring to us the Gym "
        		+ "bade belonging to Randy.... fdaoihshfaohsgopaiosjfiodahaiohdiaohfdaoihfioshoahgioadhgioadhd"
        		+ "kdjhfjkdha;lkjfakjad;khfdfaklfjkladjdlaknfldahadkljfal;";
        System.out.println("LEN" + msg.length());
        prop.text(msg);
        prop.height(100);
        prop.width(600);
        myUIBuilder.addDialogBubble(myRoot, prop);
    }

    public void enterBattle() {

    }

    public void renderBlock() {

    }

    public void handleUpdate(BlockUpdate blockUpdate) {
        switch (blockUpdate.getType()) {
            case BATTLE:
                break;
            case DISPLAY_MESSAGE:
                System.out.println("MESSAGE");
                displayMessage(blockUpdate.getContent());
                break;
            case RE_RENDER:
                break;
            default:
                break;
        }
    }
}
