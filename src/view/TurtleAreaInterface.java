package view;
/**
 * This is an internal API for the view that sets a contract for the Turtles display area. It ensures that any class that creates and manages the turtle display area,
 * must have a method to set background color and a method to return the node upon which the turtle is drawing.
 */

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;

public interface TurtleAreaInterface {


    /**
     * creates the turtles display area.
     */
    void createTurtleArea(Dimension2D turtleDispDimension);

    /**
     * Sets the background color of the turtles display area
     *
     * @param color Color to set the background to
     */
    void setBackground(String color);


    /**
     * Returns the node that the turtle is drawing on top of. It is only a CommandNode so that the actual object the turtle draw on top of can be anything from a rectangle to
     * a javafx canvas, to a preset image.
     *
     * @return CommandNode underlying turtle area
     */
    Group getTurtleArea();

    Node getTurtlePane();

}
