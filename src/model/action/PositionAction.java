package model.action;

import javafx.geometry.Point2D;
import model.turtle.Turtle;

public class PositionAction extends TurtleAction {

    private final double positionX;
    private final double positionY;


    public PositionAction(Turtle myTurtle, double positionX, double positionY) {
        super(myTurtle);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void run() {
        getMyTurtle().getTurtleProperties().setLocation(getDestination());
        super.run();
    }

    private Point2D getDestination() {
        double currentX = getMyTurtle().getTurtleProperties().getHome().getX();
        double currentY = getMyTurtle().getTurtleProperties().getHome().getY();

        double newX = currentX + positionX;
        double newY = currentY + positionY;

        return new Point2D(newX, newY);
    }
}
