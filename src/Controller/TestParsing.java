package Controller;

import Model.Turtle;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;


public class TestParsing extends Application {

    private static final Dimension2D turtleDispDimension = new Dimension2D(600, 450);

    @Override
    public void start(Stage primaryStage) {
        ProgramParser lang = new ProgramParser("languages/English", "languages/Syntax");
        CommandContainer commandContainer = new CommandContainer();
        Turtle myTurtle = new Turtle(turtleDispDimension);
        Scene myScene = new Scene(myTurtle.getGroup(), 800, 800);
        primaryStage.setScene(myScene);
        primaryStage.setTitle("Slogo");
        primaryStage.show();
        String userInput = "fd rt 90 BACK 30 Left 20";
        List<Entry<String, String>> parsingResult = lang.parseText(userInput);
        ExpressionTree expressionTree = new ExpressionTree(myTurtle, parsingResult);
        expressionTree.executeAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
