package view;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTester extends Application {

    private Scene myScene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        myScene = new Scene(root, 1000, 700);
        //View v = new View(root);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

}
