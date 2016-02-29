package Model.Turtle;

import Model.Action.TurtleAction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {

    private final Group root = new Group();
    private final TurtleProperties turtleProperties;

    private final Queue<TurtleAction> myActions = new LinkedList<>();

    public Turtle(Dimension2D turtleDispDimension) {
        turtleProperties = new TurtleProperties();
        turtleProperties.addListeners();
        turtleProperties.init(turtleDispDimension);
        root.getChildren().add(turtleProperties.getImageView());
    }

    public void clearActions() {
        myActions.clear();
    }

    public Queue<TurtleAction> getActions() {
        return myActions;
    }

    public void addAction(TurtleAction action) {
        myActions.add(action);
    }

    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public Group getGroup() {
        return root;
    }


}
