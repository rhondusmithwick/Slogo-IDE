package view;


import Controller.Controller;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class View implements ViewInt{
	
	private BorderPane UI;
	private Controller controller;
	private Group root;
	private TurtleDisplay turtDisp;
	
	
	
	
	public View(Controller controller){
		this.controller = controller;
		UI = new BorderPane();
		root = controller.getGroup();
		createScene();
		root.getChildren().add(UI);
		
	}
	
	
	
	

	private void createScene() {
		//as components are built take code out of here and rewrite it to add component
		
		//Toolbar here
		Rectangle r = new Rectangle(1000,100);
		r.setFill(Color.GREEN);
		HBox top = new HBox();
		top.getChildren().add(r);
		
		//errors and command history here
		r = new Rectangle(1000,200);
		r.setFill(Color.BLACK);
		HBox bottom = new HBox();
		bottom.getChildren().add(r);
		
		//variables and methods here
		VBox left = new VBox();
		r = new Rectangle(100, 400);
		r.setFill(Color.BLUE);
		left.getChildren().add(r);

		//text entry and execute button here
		VBox right = new VBox();
		r = new Rectangle(100, 400);
		r.setFill(Color.RED);
		right.getChildren().add(r);
		
		//turtle area here
		turtDisp = new TurtleDisplay();
		turtDisp.createTurtleArea();
		Node center = turtDisp.getTurtleArea();
		
		
		//add components to scene
		UI.setCenter(center);
		UI.setRight(right);
		UI.setLeft(left);
		UI.setBottom(bottom);
		UI.setTop(top);
	}





	public void passError(String Error) {
		// TODO Auto-generated method stub
		
	}


	public void passInput(String command) {
		controller.takeInput(command);
		
	}
	
	public Node getTurtleDisplay(){
	    return turtDisp.getTurtleArea();
	}

}
