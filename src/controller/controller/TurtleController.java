package controller.controller;

import controller.slogoparser.ExpressionTree;
import controller.slogoparser.SlogoParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import main.GlobalProperties;
import model.deprecated.Command;
import model.turtle.TurtleManager;
import model.usercontrol.Variable;
import observables.MapObservable;
import observables.ObjectObservable;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_LANGUAGE = "resources/languages/English";

    private final SlogoParser parser = new SlogoParser("resources/languages/Syntax");

    private final Group group = new Group();
    private final TurtleManager turtleManager;
    private final GlobalProperties properties;

    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");

    private final MapObservable<String, Variable> variables = new MapObservable<>("variables");
    private final DefinedCommands definedCommands = new DefinedCommands();

    public TurtleController(GlobalProperties globalProperties, Dimension2D turtleDispDimension) {
        turtleManager = new TurtleManager(turtleDispDimension);
        this.properties = globalProperties;
        properties.getLanguage().addObserver(this);
        properties.getInput().addObserver(this);
        properties.getLanguage().set(DEFAULT_LANGUAGE);
        variables.addObserver(this);
        group.getChildren().add(turtleManager.getGroup());
    }

    @Override
    public void takeInput(String input) {
        Queue<Entry<String, String>> parsedText = parser.parseText(definedCommands, input);
        runCommands(parsedText);
    }

    private void runCommands(Queue<Entry<String, String>> parsedText) {
        if (parsedText == null) {
            error.set("");
            error.set("Command not recognized: " + properties.getInput().get());
        } else {
            try {
                ExpressionTree expressionTree = new ExpressionTree(turtleManager, variables, definedCommands, properties, parsedText);
                new Thread(expressionTree::executeAll).start();
            } catch (Exception es) {
                error.set("");
                error.set("Exception in command argument: " + properties.getInput().get());
            }
        }
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == properties.getLanguage()) {
            parser.addPatterns(properties.getLanguage().get());
        }
        if (o == properties.getInput()) {
            takeInput(properties.getInput().get());
        }
    }

    @Override
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(
                error, turtleManager.getTurtleIDsProperty(),
                variables.getStringProperty(), definedCommands.frontEndTextProperty());
    }
}
