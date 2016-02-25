package Controller;

import Model.Command;
import Model.Turtle;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_LANGUAGE = "languages/English";

    private final Group group = new Group();

    private final SimpleStringProperty language = new SimpleStringProperty(this, "language");

    private final SimpleStringProperty input = new SimpleStringProperty(this, "input");

    private final Turtle myTurtle;

    private final ProgramParser parser = new ProgramParser("languages/Syntax");

    private final CommandContainer container = new CommandContainer();

    private ResourceBundle myResources;

    public TurtleController(Dimension2D turtleDispDimension) {
        myTurtle = new Turtle(turtleDispDimension);
        addListeners();
        language.set(DEFAULT_LANGUAGE);
        group.getChildren().add(myTurtle.getGroup());
    }

    @Override
    public void takeInput(String input) {
        List<Entry<String, String>> commandQueue = parser.parseText(input);
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

    void addListeners() {
        language.addListener((ov, oldVal, newVal) -> {
            parser.addPatterns(newVal);
        });
        input.addListener((ov, oldVal, newVal) -> takeInput(newVal));
    }

    @Override
    public SimpleStringProperty[] getProperties() {
        return new SimpleStringProperty[]{
                language, input, myTurtle.getTurtleProperties().imageProperty()
        };
    }
}
