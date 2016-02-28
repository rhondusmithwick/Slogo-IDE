package Model.Movement;

import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.TransitionAction;
import Model.Turtle.TurtleAction;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
abstract class Movement extends TurtleCommandNode {

    double move(int direction) {
        double distance = getChildren().get(0).getValue();
        Line penLine = new Line();
        getTurtle().getGroup().getChildren().add(penLine);
        ImageView imageView = getTurtle().getView();
        TurtleAction action = new TransitionAction(getTurtle(), imageView, penLine, distance, direction);
        getTurtle().addAction(action);
        return distance;
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
