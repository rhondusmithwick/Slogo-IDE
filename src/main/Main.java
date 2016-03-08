package main;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slogo");
        Slogo slogo = new Slogo();
        slogo.init(primaryStage);
        primaryStage.show();
    }
}
