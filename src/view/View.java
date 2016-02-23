package view;

import javax.swing.GroupLayout.Alignment;

import Model.Controller;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class View implements ViewInt{
	
	private BorderPane UI;
	private Controller controller;
	private Group root;
	
	
	
	
	public View(Group root){ //Controller controller){
		//this.controller = controller;
		UI = new BorderPane();
		//root = controller.getGroup();
		createScene();
		root.getChildren().add(UI);
		
	}
	
	
	
	

	private void createScene() {
		Label top = new Label("toolbar will be here");
		UI.setTop(top);
		
	}





	public void passError(String Error) {
		// TODO Auto-generated method stub
		
	}


	public void passInput(String command) {
		controller.takeInput(command);
		
	}

}
