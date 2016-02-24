package view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ToolBar implements ToolBarInterface {
	
	private HBox container;
	
	public ToolBar(){
		container = new HBox();
	}
	
	
	
    private void makeButton(String label,EventHandler<ActionEvent> handler){
        Button newButt = new Button();
        newButt.setText(label);
        container.getChildren().add(newButt);
        newButt.setOnAction(handler);
    }
	
	
	
	
	@Override
	public void createToolBarMembers() {
		createButtons();

	}

	private void createButtons() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Node getToolBarMembers() {
		
		return null;
	}

}
