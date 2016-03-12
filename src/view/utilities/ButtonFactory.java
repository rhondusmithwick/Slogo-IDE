package view.utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Class is responsible for creating buttons. Contains a utilities function so is never actually instantiated.
 *
 * @author Cali
 */

public class ButtonFactory {

    private ButtonFactory() {
    }

    /**
     * Creates a new button. Is static so that it can be accessed as the actual class is never instantiated,
     * also so that function can be accessed without this object being passed.
     *
     * @param title   title for new button
     * @param handler event handler for new button
     * @return created button
     */
    public static Button createButton(String title, EventHandler<ActionEvent> handler) {
        Button newButton = new Button(title);
        newButton.setOnAction(handler);
        return newButton;
    }
}
