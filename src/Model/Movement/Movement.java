package Model.Movement;

import Model.Action.MovementAction;
import Model.Action.TurtleAction;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;
import javafx.scene.shape.Line;

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
