package controller.controller;

import Model.Action.TurtleAction;
import Model.Action.VisionAction;
import Model.Deprecated.Command;
import Model.TreeNode.TreeNode;
import Model.Turtle.Turtle;
import Observables.MapObservable;
import Observables.ObjectObservable;
import controller.slogoparser.ExpressionTree;
import controller.slogoparser.SlogoParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller, Observer {

    private static final String DEFAULT_LANGUAGE = "resources/languages/English";

    private final SlogoParser parser = new SlogoParser("resources/languages/Syntax");

    private final Group group = new Group();
    private final Turtle myTurtle;
    private final ObjectObservable<String> language = new ObjectObservable<>();
    private final ObjectObservable<String> input = new ObjectObservable<>();
    
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");

    private final MapObservable<String, TreeNode> variables = new MapObservable<>("variables");

    private final MapObservable<String, TreeNode> definedCommands = new MapObservable<>("definedComamnds");

    public TurtleController(Dimension2D turtleDispDimension) {
        myTurtle = new Turtle(turtleDispDimension);
        language.addObserver(this);
        input.addObserver(this);
        language.set(DEFAULT_LANGUAGE);
        variables.addObserver(this);
        group.getChildren().add(myTurtle.getGroup());
    }

    public ObjectObservable<String> getLanguage() {
        return language;
    }

    public ObjectObservable<String> getInput() {
        return input;
    }

    @Override
    public void takeInput(String input) {
        System.out.printf("text backend is doing: %s \n", input);
        Queue<Entry<String, String>> parsedText = parser.parseText(input);
        if (parsedText == null) {
        	error.set("");
        	error.set("Command not recognized: " + input);
        } else {
        	try {
        		ExpressionTree expressionTree = new ExpressionTree(myTurtle, variables, definedCommands, parsedText);        		
        		expressionTree.executeAll();
        		variables.modifyIfShould();
        		new Thread(this::runActions).start();
        	} catch (Exception es) {
        		error.set("");
        		error.set("Exception in command argument: " + input);
        	}
        }
    }

    private void runActions() {
        Queue<TurtleAction> actions = myTurtle.getActions();
        if (!actions.isEmpty()) {
            boolean isVisible = myTurtle.getTurtleProperties().getVisible();
            runAction(new VisionAction(myTurtle, false));
            actions.stream().forEach(this::runAction);
            runAction(new VisionAction(myTurtle, isVisible));
            myTurtle.clearActions();
        }
    }

    private void runAction(TurtleAction action) {
        new Thread(action).start();
        while (!action.isDone()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        		error,
                myTurtle.getTurtleProperties().imageProperty(),
                myTurtle.getTurtleProperties().penColorProperty(),
                variables.getStringProperty());
    }
}
