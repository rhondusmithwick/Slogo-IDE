package model.turtle;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {

    private final Group root = new Group();
    private final TurtleProperties turtleProperties;

    public Turtle(Dimension2D turtleDispDimension) {
        turtleProperties = new TurtleProperties();
        turtleProperties.addListeners();
        turtleProperties.init(turtleDispDimension);
        root.getChildren().add(turtleProperties.getImageView());
    }

    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public Group getGroup() {
        return root;
    }


}
