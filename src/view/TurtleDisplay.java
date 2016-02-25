package view;

import javafx.scene.Group;
import javafx.scene.Node;
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
        
        Path path = new Path();

        path.getElements().add(new MoveTo(0.0, 0.0f));
        path.getElements().add(new LineTo(100.0f, 100.0f));
        dispArea.getChildren().add(path);

    }

    @Override
    public void setBackground(String color) {
        Rectangle bg = new Rectangle(600,450, Color.web(color));
        dispArea.getChildren().add(bg);

    }

    @Override
    public Node getTurtleArea() {
        return dispArea;
    }
    


}
