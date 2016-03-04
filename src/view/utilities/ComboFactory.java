package View.utilities;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;


public class ComboFactory {
	
	
	
	public static ComboBox<String> createBox(String title, List<String> choices, EventHandler<ActionEvent> handler){
		ComboBox<String> comBox = new ComboBox<>();
        comBox.setPromptText(title);
        choices.forEach(e -> comBox.getItems().add(e));
        comBox.setOnAction(handler);
        return comBox;
	}

}
