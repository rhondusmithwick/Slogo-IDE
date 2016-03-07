package view.utilities;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

/**
 * Class is responsible for creating comboboxes. Contains a utilities function so is never actually instantiated.
 * @author Cali
 *
 */

public class ComboFactory {
	
	private ComboFactory(){};
	
	/**
	 * Creates a new combobox. Is static so that it can be accessed as the actual class is never instantiated,
         * also so that function can be accessed without this object being passed.
	 * @param title String title for comobobox
	 * @param choices List of Strings of choices for combobox
	 * @param handler Event handler to be called upon selection
	 * @return created combobox
	 */
	public static ComboBox<String> createBox(String title, List<String> choices, EventHandler<ActionEvent> handler){
		ComboBox<String> comBox = new ComboBox<>();
        comBox.setPromptText(title);
        choices.forEach(e -> comBox.getItems().add(e));
        comBox.setOnAction(handler);
        return comBox;
	}

}
