package view;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface {
    private static final double SCROLL_BAR_INITIAL = .5;
	private static final String DEFAULT_BACKGROUND_COLOR = "white";
    private static final int SCROLL_WIDTH = 600;
    private static final int SCROLL_HEIGHT = 450;
    private Rectangle background;
    private Group dispArea;
    private ScrollPane scroll;

    public TurtleDisplay(Group root) {
        dispArea = root;
        scroll = new ScrollPane();
        scroll.setMaxHeight(SCROLL_HEIGHT);
        scroll.setMaxWidth(SCROLL_WIDTH);
        scroll.setVvalue(SCROLL_BAR_INITIAL);
        scroll.setHvalue(SCROLL_BAR_INITIAL);

    }


    @Override
    public void createTurtleArea(Dimension2D turtleDispDimension) {
        background = new Rectangle(turtleDispDimension.getWidth(), turtleDispDimension.getHeight());
        dispArea = new Group();
        setBackground(DEFAULT_BACKGROUND_COLOR);
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