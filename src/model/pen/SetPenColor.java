package model.pen;

import controller.slogoparser.ExpressionTree;
import maps.IndexMap;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class SetPenColor extends TurtleCommandNode {

    private int index;
    private IndexMap colorMap;

    @Override
    public double turtleExecute(Turtle turtle) {
        setPenColor(turtle);
        return index;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 1;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        super.handleSpecific(tree);
        this.colorMap = tree.getColorMap();
    }

    private void setPenColor(Turtle turtle) {
        index = (int) getChildren().get(0).getValue();
        turtle.getTurtleProperties().setPenColorIndex(index);
        turtle.getTurtleProperties().setPenColor(colorMap.get(index));
    }

}
