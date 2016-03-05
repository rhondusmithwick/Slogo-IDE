package model.position;

import model.action.PositionAction;
import model.action.TurtleAction;
import model.treenode.ConstantNode;
import model.turtle.Turtle;

public class SetHome extends Position {

	@Override
	protected double execute() {
        addChild(new ConstantNode(0));
        addChild(new ConstantNode(0));
        return changePosition();
	}
	
}
