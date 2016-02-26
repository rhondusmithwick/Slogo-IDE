package Controller.Controller;

import Controller.SlogoParser.ExpressionTree;
import Controller.SlogoParser.SlogoParser;
import Model.Deprecated.Command;
import Model.Turtle.Turtle;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_LANGUAGE = "languages/English";

    private final SlogoParser parser = new SlogoParser("languages/Syntax");

    private final Group group = new Group();

    private final SimpleStringProperty language = new SimpleStringProperty(this, "language");

    private final SimpleStringProperty input = new SimpleStringProperty(this, "input");

    private final Turtle myTurtle;

    public TurtleController(Dimension2D turtleDispDimension) {
        myTurtle = new Turtle(turtleDispDimension);
        addListeners();
        language.set(DEFAULT_LANGUAGE);
        group.getChildren().add(myTurtle.getGroup());
    }

    @Override
    public void takeInput(String input) {
        List<Entry<String, String>> parsedText = parser.parseText(input);
        ExpressionTree expressionTree = new ExpressionTree(myTurtle, parsedText);
        System.out.println(expressionTree);
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
    public void setLanguage(String language) {
        this.language.set(language);
    }

    private void addListeners() {
        language.addListener((ov, oldVal, newVal)
                -> parser.addPatterns(newVal));
        input.addListener((ov, oldVal, newVal) -> takeInput(newVal));
    }

    @Override
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(language, input, myTurtle.getTurtleProperties().imageProperty());
    }
}
