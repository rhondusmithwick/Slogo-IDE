package view;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;

/**
 * This is an internal API for the view that provides a framework for any class implementing the feature allowing the user to enter text commands. It ensures that any class
 * implementing this feature must have methods to get its graphical node, get any commands entered, as well as clear any commands currently entered, but not yet run.
 *
 * @author Cali
 */
public interface CommandEntryInterface {


    /**
     * returns node that user is entering text into
     *
     * @return Node that allows user to enter command into it
     */
    Node getTextBox();

    /**
     * Gets text from the text box and returns any commands currently entered into the text box
     *
     * @return String[] of entered commands to be executed
     */
    SimpleStringProperty getCommands();

    /**
     * Clears the textbox of any currently entered but not yet run commands
     */
    void clearCommands();

    /**
     * create the graphical nod to allow user to enter commands
     */
    void createEntryBox();
}
