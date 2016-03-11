package view.turtdisplay;

import java.util.Observable;
import java.util.Observer;

import view.Defaults;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.ObjectObservable;

/**
 * Class responsible for containing area where turtle is displayed to the user.
 * @author Cali
 *
 */

public class TurtleDisplay implements Observer {
    private static final double SCROLL_BAR_INITIAL = .5;
    private Rectangle background;
    private Group displayArea;
    private ScrollPane scroll;
    private ObjectObservable<String> backgroundColor;

    /**
     * creates new turtle display instance
     * @param bgColor string observable that allows background color to be set 
     * @param turtleDispDimension 2D dimension object that specifies size of turtle area
     */
    public TurtleDisplay(ObjectObservable<String> backgroundColor, Dimension2D turtleDispDimension) {
        this.backgroundColor=backgroundColor;
        backgroundColor.addObserver(this);
        background = new Rectangle(turtleDispDimension.getWidth(), turtleDispDimension.getHeight());
        displayArea = new Group();
        setBackground(Defaults.TURT_BACKGROUND.getDefault());
        displayArea.getChildren().add(background);
        setScrollPane();

    }


    private void setScrollPane () {
        scroll = new ScrollPane();
        scroll.setVvalue(SCROLL_BAR_INITIAL);
        scroll.setHvalue(SCROLL_BAR_INITIAL);
        scroll.setContent(displayArea);

       
    }

    private void setBackground(String color) {
        background.setFill(Color.web(color));

    }

    /**
     * returns scrollpane containing the area turtle can be displayed in
     * @return scrollpane containg group for turtle to be displayed in
     */
    public Node getTurtlePane() {
        return scroll;
    }

    /**
     * returns the group contained by scroll pane that turtle and its lines can be added to
     * @return group turtle and its lines can be added to
     */
    public Group getTurtleArea() {
        return displayArea;
    }

    /**
     * Called whenever background color string observable is set. This turtle display instance
     * then changes the background color of the display area to the new color.
     * @param o object being observed
     * @param arg1 argument to object
     */
    @Override
    public void update (Observable o, Object arg1) {
        String color = backgroundColor.get();
        setBackground(color);
        
    }


}