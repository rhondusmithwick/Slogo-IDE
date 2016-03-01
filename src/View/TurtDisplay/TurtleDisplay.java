package View.TurtDisplay;

import java.util.Observable;
import java.util.Observer;
import Observables.ObjectObservable;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface, Observer {
    private static final double SCROLL_BAR_INITIAL = .5;
	private static final String DEFAULT_BACKGROUND_COLOR = "white";
    private Rectangle background;
    private Group dispArea;
    private ScrollPane scroll;
    private ObjectObservable<String> bgColor;

    public TurtleDisplay(ObjectObservable<String> bgColor, Dimension2D turtleDispDimension) {
        this.bgColor=bgColor;
        bgColor.addObserver(this);
        background = new Rectangle(turtleDispDimension.getWidth(), turtleDispDimension.getHeight());
        dispArea = new Group();
        setBackground(DEFAULT_BACKGROUND_COLOR);
        dispArea.getChildren().add(background);
        setScrollPane();

    }


    private void setScrollPane () {
        scroll = new ScrollPane();
        scroll.setVvalue(SCROLL_BAR_INITIAL);
        scroll.setHvalue(SCROLL_BAR_INITIAL);
        scroll.setContent(dispArea);

       
    }

    private void setBackground(String color) {
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


    @Override
    public void update (Observable o, Object arg1) {
        String color = bgColor.get();
        setBackground(color);
        
    }


}