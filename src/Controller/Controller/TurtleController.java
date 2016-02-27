package Controller.Controller;

import Controller.SlogoParser.ExpressionTree;
import Controller.SlogoParser.SlogoParser;
import Model.Deprecated.Command;
import Model.TreeNode.TreeNode;
import Model.Turtle.Turtle;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller, Observer {

    private static final String DEFAULT_LANGUAGE = "languages/English";

    private final Map<String, TreeNode> variables = new HashMap<>();

    private final SlogoParser parser = new SlogoParser("languages/Syntax");

    private final Group group = new Group();
    private final Turtle myTurtle;
    private final StringObservable language = new StringObservable();
    private final StringObservable input = new StringObservable();

    public TurtleController(Dimension2D turtleDispDimension) {
        myTurtle = new Turtle(turtleDispDimension);
        language.addObserver(this);
        input.addObserver(this);
        language.set(DEFAULT_LANGUAGE);
        group.getChildren().add(myTurtle.getGroup());
    }

    public StringObservable getLanguage() {
        return language;
    }

    public StringObservable getInput() {
        return input;
    }

    @Override
    public void takeInput(String input) {
        System.out.printf("text backend is doing: %s \n", input);
        Queue<Entry<String, String>> parsedText = parser.parseText(input);
        ExpressionTree expressionTree = new ExpressionTree(variables, myTurtle, parsedText);
        expressionTree.executeAll();
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == language) {
            parser.addPatterns(language.get());
        }
        if (o == input) {
            takeInput(input.get());
        }
    }


    @Override
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(
                myTurtle.getTurtleProperties().imageProperty(),
                myTurtle.getTurtleProperties().penColorProperty());
    }


}
