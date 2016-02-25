package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;




public class TurtleDisplay implements TurtleAreaInterface {
	private Rectangle background;
    private Group dispArea;
    
    public TurtleDisplay(Group root){
    	dispArea = root;
    }


    @Override
    public void createTurtleArea(double width, double height) {
    	background = new Rectangle(width, height, Color.WHITE);
        dispArea = new Group();
        setBackground("red");
        dispArea.getChildren().add(background);

    }

    @Override
    public void setBackground(String color) {
    	background.setFill(Color.RED);

    }

    @Override
    public Node getTurtleArea() {
        return dispArea;
    }
    


}
