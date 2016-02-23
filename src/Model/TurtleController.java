package Model;

import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleController implements Controller {

    private static final String DEFAULT_TURTLE_IMAGE = "....";

    private final Group group = new Group();

    private final Turtle myTurtle = new Turtle(new Image(getClass()
            .getClassLoader()
            .getResourceAsStream(DEFAULT_TURTLE_IMAGE)));


    public TurtleController() {
        group.getChildren().add(myTurtle.getGroup());
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

}
