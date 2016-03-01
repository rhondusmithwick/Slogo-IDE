package View.TurtDisplay;
/**
 * This is an internal API for the view that sets a contract for the Turtles display area. It ensures that any class that creates and manages the turtle display area,
 * must have a method to set background color and a method to return the node upon which the turtle is drawing.
 */

import javafx.scene.Group;
import javafx.scene.Node;

public interface TurtleAreaInterface {





    /**
     * Returns the node that the turtle is drawing on top of. It is only a CommandNode so that the actual object the turtle draw on top of can be anything from a rectangle to
     * a javafx canvas, to a preset image.
     *
     * @return CommandNode underlying turtle area
     */
    Group getTurtleArea();

    /**
     * returns the pane that the turtle area group exists in
     *
     * @return pane that the turtle area group is in
     */
    Node getTurtlePane();

}
