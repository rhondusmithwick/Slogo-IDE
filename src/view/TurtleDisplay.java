package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



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
