package model.turtle;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle {

    private final Group root = new Group();
    private final int ID;
    private final TurtleProperties turtleProperties;
    private final ExecutorService executorService = Executors.newWorkStealingPool(1);

    public Turtle(int ID, Dimension2D turtleDispDimension) {
        this.ID = ID;
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

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public String toString() {
        return String.format("Turtle with ID: %d", ID);
    }
}
