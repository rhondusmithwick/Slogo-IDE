package view;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface {
	private Rectangle background;
	private Group dispArea;

	public TurtleDisplay(Group root) {
		dispArea = root;
	}


	@Override
	public void createTurtleArea(Dimension2D turtleDispDeminsion) {
		background = new Rectangle(turtleDispDeminsion.getWidth(), turtleDispDeminsion.getHeight(), Color.WHITE);
		dispArea = new Group();
		setBackground("white");
		dispArea.getChildren().add(background);

	}

	@Override
	public void setBackground(String color) {
		background.setFill(Color.web(color));

	}

	@Override
	public Group getTurtleArea() {
		return dispArea;
	}


}