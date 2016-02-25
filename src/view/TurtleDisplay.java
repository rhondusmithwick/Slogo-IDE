package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;



public class TurtleDisplay implements TurtleAreaInterface {

    private Group dispArea;


    @Override
    public void createTurtleArea() {
        dispArea = new Group();
        setBackground("red");

    }

    @Override
    public void setBackground(String color) {
        Rectangle bg = new Rectangle(1000,700, Color.RED);
        dispArea.getChildren().add(bg);
    }

    @Override
    public Node getTurtleArea() {
        return dispArea;
    }
    


}
