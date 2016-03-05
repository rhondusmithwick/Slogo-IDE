package model.position;

import javafx.geometry.Point2D;
import model.action.PositionAction;
import model.action.TurtleAction;
import model.treenode.ConstantNode;
import model.turtle.Turtle;

public class SetHome extends Position {

	@Override
	protected double execute() {
        Point2D home = getTurtle().getTurtleProperties().getHome();
        return changePosition(home.getX(), home.getY());
	}
	
}
