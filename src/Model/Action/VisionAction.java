package Model.Action;

import Model.Turtle.Turtle;

public class VisionAction extends TurtleAction {

    private final boolean isVisible;

    public VisionAction(Turtle myTurtle, boolean isVisible) {
        super(myTurtle);
        this.isVisible = isVisible;
    }

    @Override
    public void run() {
        getMyTurtle().getTurtleProperties().setVisible(isVisible);
        super.run();
    }

}
