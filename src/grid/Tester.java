package grid;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tester extends Application {
    GridPane gp = new GridPane(20,20,600,800);
    

    @Override
    public void start (Stage primaryStage) throws Exception {
        //System.out.println(gp.getNodeList());
        //System.out.println(gp.getRenderMap());
        Group g = new Group();
        
        for(int i = 0; i < gp.getNodeList().size(); i++){
            g.getChildren().add(gp.getNodeList().get(i).getImage());
        }        
        Scene sc = new Scene(g);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}