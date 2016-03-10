package controller.controller;

import controller.slogoparser.ExpressionTree;
import controller.slogoparser.SlogoParser;
import main.GlobalProperties;
import maps.IndexMap;
import model.turtle.TurtleManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import model.deprecated.Command;
import model.usercontrol.MakeUserInstruction;
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
public class TurtleController implements Controller, Observer {

    private static final String DEFAULT_LANGUAGE = "resources/languages/English";

    private final SlogoParser parser = new SlogoParser("resources/languages/Syntax");

    private final Group group = new Group();
    private final TurtleManager turtleManager;
    private final ObjectObservable<String> language;
    private final ObjectObservable<String> input;
    private final IndexMap colorMap, imageMap;

    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");

    private final MapObservable<String, Variable> variables = new MapObservable<>("variables");

    private final MapObservable<String, MakeUserInstruction> definedCommands = new MapObservable<>("definedCommands");

    public TurtleController(GlobalProperties globalProperties, Dimension2D turtleDispDimension) {
        turtleManager = new TurtleManager(turtleDispDimension);
        this.language = globalProperties.getLanguage();
        this.input = globalProperties.getInput();
        this.colorMap = globalProperties.getColorMap();
        this.imageMap = globalProperties.getImageMap();
        language.addObserver(this);
        input.addObserver(this);
        language.set(DEFAULT_LANGUAGE);
        variables.addObserver(this);
        definedCommands.addObserver(this);
        group.getChildren().add(turtleManager.getGroup());
    }

    public ObjectObservable<String> getLanguage() {
        return language;
    }

    public ObjectObservable<String> getInput() {
        return input;
    }

    @Override
    public void takeInput(String input) {
        Queue<Entry<String, String>> parsedText = parser.parseText(input);
        runCommands(parsedText);
    }
    
    private void runCommands(Queue<Entry<String, String>> parsedText) {
        if (parsedText == null) {
            error.set("");
            error.set("Command not recognized: " + input);
        } else {
            try {
                ExpressionTree expressionTree = new ExpressionTree(turtleManager, variables, definedCommands, colorMap, parsedText);
                new Thread(expressionTree::executeAll).start();
            } catch (Exception es) {
                error.set("");
                error.set("Exception in command argument: " + input);
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
        		error, turtleManager.getTurtleIDsProperty(),
                turtleManager.get(1).getTurtleProperties().imageProperty(),
                turtleManager.get(1).getTurtleProperties().penColorProperty(),
                variables.getStringProperty(), definedCommands.getStringProperty());
    }
}
