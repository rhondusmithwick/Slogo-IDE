package controller.controller;

import controller.slogoparser.ExpressionTree;
import controller.slogoparser.SlogoParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import main.GlobalProperties;
import maps.MapContainer;
import model.turtle.TurtleManager;
import model.usercontrol.MakeUserInstruction;
import model.usercontrol.Variable;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_LANGUAGE = "resources/languages/English";
    private static final String DEFAULT_SYNTAX = "resources/languages/Syntax";

    private final Group group = new Group();
    private final SlogoParser parser = new SlogoParser(DEFAULT_SYNTAX);
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
    private final MapContainer<String, Variable> variables = new MapContainer<>("variables");
    private final MapContainer<String, MakeUserInstruction> definedCommands = new MapContainer<>("definedCommands");

    private final TurtleManager turtleManager;
    private final GlobalProperties properties;

    public TurtleController(GlobalProperties globalProperties, Dimension2D turtleDispDimension) {
        this.turtleManager = new TurtleManager(turtleDispDimension);
        this.properties = globalProperties;
        setProperties();
    }

    private void setProperties() {
        properties.getLanguage().addObserver(this);
        properties.getInput().addObserver(this);
        properties.getLanguage().set(DEFAULT_LANGUAGE);
        group.getChildren().add(turtleManager.getGroup());
    }

    @Override
    public void takeInput(String input) {
        Queue<Entry<String, String>> parsedText = parser.parseText(input);
        if (parsedText == null) {
            error.set("");
            error.set("Command not recognized: " + properties.getInput().get());
        } else {
            runCommands(parsedText);
        }
    }

    private void runCommands(Queue<Entry<String, String>> parsedText) {
        try {
            ExpressionTree expressionTree = new ExpressionTree(turtleManager, variables,
                    definedCommands, properties, parsedText);
            new Thread(expressionTree::executeAll).start();
        } catch (Exception es) {
            error.set("");
            error.set("Exception in command argument: " + properties.getInput().get());
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
                variables.frontEndTextProperty(), definedCommands.frontEndTextProperty());
    }
}
