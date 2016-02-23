package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_TURTLE_IMAGE = "....";
    private static final String DEFAULT_LANGUAGE = "English";

    private final Group group = new Group();
    private final SimpleStringProperty language = new SimpleStringProperty();
    private final Turtle myTurtle = new Turtle(new Image(getClass()
            .getClassLoader()
            .getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
    private final CommandContainer container;

    private ResourceBundle myResources;

    public TurtleController() {
        addListeners();
        language.set(DEFAULT_LANGUAGE);
        container = new CommandContainer(myResources);
        group.getChildren().add(myTurtle.getGroup());
    }

    public TurtleController(String language) {
        this();
        setLanguage(language);
    }

    @Override
    public void takeInput(String input) {

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
        language.addListener((ov, oldVal, newVal) ->
                myResources = ResourceBundle.getBundle(newVal + ".properties"));
    }

}
