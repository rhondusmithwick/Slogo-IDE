package view;

<<<<<<< HEAD
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
=======
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
>>>>>>> master
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface {
    private Rectangle background;
    private Group dispArea;

<<<<<<< HEAD
    private ScrollPane dispArea;


    @Override
    public void createTurtleArea() {
        dispArea = new ScrollPane();
        setBackground("red");
        dispArea.setMaxHeight(450);
        dispArea.setMaxWidth(600);
        Group root = new Group();
        
        Path path = new Path();

        path.getElements().add(new MoveTo(0.0, 0.0f));
        path.getElements().add(new LineTo(100.0f, 100.0f));
        root.getChildren().add(path);
        dispArea.setContent(root);
=======
    public TurtleDisplay(Group root) {
        dispArea = root;
    }


    @Override
    public void createTurtleArea(Dimension2D turtleDispDeminsion) {
        background = new Rectangle(turtleDispDeminsion.getWidth(), turtleDispDeminsion.getHeight(), Color.WHITE);
        dispArea = new Group();
        setBackground("red");
        dispArea.getChildren().add(background);
>>>>>>> master

    }

    @Override
    public void setBackground(String color) {
        background.setFill(Color.RED);

    }

    @Override
    public Group getTurtleArea() {
        return dispArea;
    }


}
