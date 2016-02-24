package view;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;


public class TurtleDisplay implements TurtleAreaInterface{
	
	private Rectangle dispArea;
	
	
	@Override
	public void createTurtleArea() {
		dispArea = new Rectangle();

		

		
	}

	@Override
	public void setBackground(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getTurtleArea() {
		return dispArea;
	}
	
	
	
	
	

}
