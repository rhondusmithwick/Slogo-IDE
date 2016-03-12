package view.utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.List;

/**
 * Class is responsible for creating comboboxes. Contains a utilities function so is never actually instantiated.
 *
 * @author Cali
 */

public class ComboFactory {

    private ComboFactory() {
    }

    /**
     * Creates a new combobox. Is static so that it can be accessed as the actual class is never instantiated,
     * also so that function can be accessed without this object being passed.
     *
     * @param title   String title for comobobox
     * @param choices List of Strings of choices for combobox
     * @param handler Event handler to be called upon selection
     * @return created combobox
     */
    public static ComboBox<String> createBox(String title, List<String> choices, EventHandler<ActionEvent> handler) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(title);
        choices.forEach(e -> comboBox.getItems().add(e));
        comboBox.setOnAction(handler);
        return comboBox;
    }

}
