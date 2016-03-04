package model.deprecated;
//package Controller.SlogoParser;
//
//import Model.Turtle.Turtle;
//import javafx.application.Application;
//import javafx.geometry.Dimension2D;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.util.Map.Entry;
//import java.util.Queue;
//
//
//public class TestParsing extends Application {
//
//    private static final Dimension2D turtleDispDimension = new Dimension2D(600, 450);
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        SlogoParser lang = new SlogoParser("languages/English", "languages/Syntax");
//        Turtle myTurtle = new Turtle(turtleDispDimension);
//        Scene myScene = new Scene(myTurtle.getGroup(), 800, 800);
//        primaryStage.setScene(myScene);
//        primaryStage.setTitle("Slogo");
//        primaryStage.show();
////        String userInput = "fd rt 90 BACK 30 Left 20";
//        String userInput = "fd fd 60 fd 20";
//        Queue<Entry<String, String>> parsedText = lang.parseText(userInput);
//        ExpressionTree expressionTree = new ExpressionTree(myTurtle, parsedText);
//        expressionTree.executeAll();
//    }
//}
