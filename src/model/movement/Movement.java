package model.movement;

import javafx.scene.shape.Line;
import model.action.MovementAction;
import model.action.TurtleAction;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
abstract class Movement extends TurtleCommandNode {

    double move(int direction) {
        Turtle myTurtle = getTurtle();
        double distance = getChildren().get(0).getValue();
        Line penLine = new Line();
        myTurtle.getGroup().getChildren().add(penLine);
        TurtleAction action = new MovementAction(myTurtle, penLine, distance, direction);
        addAction(action);
        return distance;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
