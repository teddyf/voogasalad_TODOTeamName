//package interactions;
//
//import block.Block;
//import block.EnemyBlock;
//import interactions.Interaction;
//import editor.backend.Item;
//import player.Player;
//import player.PlayerAttribute;
//
///**
// * All interactions are to be used as classes that are going to be composed with board objects.
// * These board objects will interact with the player as dictated by the interaction class(es)
// * with which they are composed.
// * @author Filip Mazurek, Bill Xiong
// */
//public abstract class AbstractInteraction implements Interaction{
////    private PlayerAttribute player;
//
//    //enums hold default interactions for users to choose from.
//    //default methods
//    private Interaction increaseAttribute;
//    private Interaction decreaseAttribute;
//    private Interaction doNothing;
//    private Interaction currentStepAction;
////    public AbstractInteraction(Player player){
////        this.player = player;
////        increaseAttribute = player::increase;
////        decreaseAttribute = player::decrease;
//
////        doNothing = () -> {};
////        currentStepAction = doNothing;
////    }
//    public AbstractInteraction(Player player){
////        this.player = null;
//    }
////    protected Interaction getCurrentAction(){
////        return currentStepAction;
////    }
////    public void setCurrentAction(Interaction action){
////        currentStepAction = action;
////    }
////    protected PlayerAttribute getPlayer(){
////        return player;
////    }
//    public abstract void actOnStep();
//    //public abstract void actOnTalk();
//}
