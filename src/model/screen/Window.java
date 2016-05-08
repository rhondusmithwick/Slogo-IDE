package model.screen;

import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;
import model.turtle.TurtleProperties.ScreenType;

/**
 * Created by rhondusmithwick on 5/7/16.
 *
 * @author Rhondu Smithwick
 */
public class Window extends TurtleCommandNode {

    @Override
    protected double turtleExecute (Turtle turtle) {
        turtle.getTurtleProperties().setScreenType(ScreenType.WINDOW);
        return 2;
    }

}
