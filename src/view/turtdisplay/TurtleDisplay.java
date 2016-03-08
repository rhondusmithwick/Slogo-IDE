package view.turtdisplay;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.ObjectObservable;
import view.Defaults;

import java.util.Observable;
import java.util.Observer;

/**
 * Class responsible for containing area where turtle is displayed to the user.
 *
 * @author Cali
 */

public class TurtleDisplay implements Observer {
    private static final double SCROLL_BAR_INITIAL = .5;
    private Rectangle background;
    private Group dispArea;
    private ScrollPane scroll;
    private ObjectObservable<String> bgColor;

    /**
     * creates new turtle display instance
     *
     * @param bgColor             string observable that allows background color to be set
     * @param turtleDispDimension 2D dimension object that specifies size of turtle area
     */
    public TurtleDisplay(ObjectObservable<String> bgColor, Dimension2D turtleDispDimension) {
        this.bgColor = bgColor;
        bgColor.addObserver(this);
        background = new Rectangle(turtleDispDimension.getWidth(), turtleDispDimension.getHeight());
        dispArea = new Group();
        setBackground(Defaults.TURT_BACKGROUND.getDefault());
        dispArea.getChildren().add(background);
        setScrollPane();

    }


    private void setScrollPane() {
        scroll = new ScrollPane();
        scroll.setVvalue(SCROLL_BAR_INITIAL);
        scroll.setHvalue(SCROLL_BAR_INITIAL);
        scroll.setContent(dispArea);


    }

    private void setBackground(String color) {
        System.out.println(color);
        background.setFill(Color.web(color));

    }

    /**
     * returns scrollpane containing the area turtle can be displayed in
     *
     * @return scrollpane containg group for turtle to be displayed in
     */
    public Node getTurtlePane() {
        return scroll;
    }

    /**
     * returns the group contained by scroll pane that turtle and its lines can be added to
     *
     * @return group turtle and its lines can be added to
     */
    public Group getTurtleArea() {
        return dispArea;
    }

    /**
     * Called whenever background color string observable is set. This turtle display instance
     * then changes the background color of the display area to the new color.
     *
     * @param o    object being observed
     * @param arg1 argument to object
     */
    @Override
    public void update(Observable o, Object arg1) {
        String color = bgColor.get();
        setBackground(color);

    }


}