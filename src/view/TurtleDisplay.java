package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;




public class TurtleDisplay implements TurtleAreaInterface {

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

    }

    @Override
    public void setBackground(String color) {
        dispArea.setBackground(new Background(new BackgroundFill(Color.web(color), null, null)));

    }

    @Override
    public Node getTurtleArea() {
        return dispArea;
    }
    


}
