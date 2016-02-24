package view;

import javafx.scene.Group;
import javafx.scene.Node;


public class TurtleDisplay implements TurtleAreaInterface {

    private Group dispArea;


    @Override
    public void createTurtleArea() {
        dispArea = new Group();

    }

    @Override
    public void setBackground(String color) {
        // TODO Auto-generated method stub

    }

    @Override
    public Node getTurtleArea() {
        return dispArea;
    }


}
