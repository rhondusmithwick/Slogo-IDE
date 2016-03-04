package view.utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class ButtonFactory {
	
	public static Button createButton(String title, EventHandler<ActionEvent> handler){
		Button newButt = new Button(title);
        newButt.setOnAction(handler);
        return newButt;
	}
}
