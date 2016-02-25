package view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTester extends Application {

    private Scene myScene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewInt v = new View(600, 450);
        myScene = new Scene(v.getGroup(), 1000, 700);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

}
