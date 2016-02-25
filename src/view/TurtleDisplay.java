package view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;




public class TurtleDisplay implements TurtleAreaInterface {

    private AnchorPane dispArea;


    @Override
    public void createTurtleArea() {
        dispArea = new AnchorPane();
        setBackground("red");
        dispArea.setMaxHeight(450);
        dispArea.setMaxWidth(600);
        
        Path path = new Path();

        path.getElements().add(new MoveTo(0.0, 0.0f));
        path.getElements().add(new LineTo(100.0f, 100.0f));
        dispArea.getChildren().add(path);

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
