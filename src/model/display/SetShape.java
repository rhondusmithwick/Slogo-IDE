package model.display;

import controller.slogoparser.ExpressionTree;
import maps.IndexMap;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;

public class SetShape extends TurtleCommandNode {

    private int index;
    private IndexMap imageMap;

    @Override
    public double turtleExecute(Turtle turtle) {
        setShape(turtle);
        return index;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        super.handleSpecific(tree);
        this.imageMap = tree.getImageMap();
    }

    @Override
    protected int getNumChildrenRequired() {
        return 1;
    }

    private void setShape(Turtle turtle) {
        index = (int) getChildren().get(0).getValue();
        turtle.getTurtleProperties().setPenShapeIndex(index);
        turtle.getTurtleProperties().setImage(imageMap.get(index));
    }

}
