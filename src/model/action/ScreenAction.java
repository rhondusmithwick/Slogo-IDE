package model.action;

import javafx.application.Platform;
import model.turtle.Turtle;
import model.turtle.TurtleProperties;

public class ScreenAction extends TurtleAction {

    public ScreenAction(Turtle myTurtle) {
        super(myTurtle);
    }

    @Override
    public void run() {
        Platform.runLater(this::runLater);
        super.run();
    }

    private void runLater() {
        TurtleProperties properties = getMyTurtle().getTurtleProperties();
        getMyTurtle().getGroup().getChildren().clear();
        properties.setLocation(properties.getHome());
        getMyTurtle().getGroup().getChildren().add(properties.getImageView());
    }
}
