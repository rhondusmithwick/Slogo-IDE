package view;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface {
    private Rectangle background;
    private Group dispArea;
    private ScrollPane scroll;

    public TurtleDisplay(Group root) {
        dispArea = root;
        scroll = new ScrollPane();
        scroll.setMaxHeight(450);
        scroll.setMaxWidth(600);

    }


    @Override
    public void createTurtleArea(Dimension2D turtleDispDeminsion) {
        background = new Rectangle(turtleDispDeminsion.getWidth(), turtleDispDeminsion.getHeight(), Color.WHITE);
        dispArea = new Group();
        setBackground("white");
        dispArea.getChildren().add(background);
        scroll.setContent(dispArea);

    }

    @Override
    public void setBackground(String color) {
        background.setFill(Color.web(color));

    }

    @Override
    public Node getTurtlePane() {
        return scroll;
    }


    @Override
    public Group getTurtleArea() {
        return dispArea;
    }


}