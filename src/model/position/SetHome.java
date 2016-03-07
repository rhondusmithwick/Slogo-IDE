package model.position;

import javafx.geometry.Point2D;

public class SetHome extends Position {

    @Override
    protected double execute() {
        Point2D home = getTurtle().getTurtleProperties().getHome();
        return changePosition(home.getX(), home.getY());
    }

}
