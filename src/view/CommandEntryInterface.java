package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

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
     * @return CommandNode that allows user to enter command into it
     */
    TextArea getTextBox();

    /**
     * Gets text from the text box and returns any commands currently entered into the text box
     *
     * @return String[] of entered commands to be executed
     */
    void getCommandsFromString(String s);

    /**
     * Clears the textbox of any currently entered but not yet run commands
     */
    void clearCommands();

    /**
     * create the graphical nod to allow user to enter commands
     */
    void createEntryBox();

    /**
     * used to pass commands from one view component to the back end such as changing the pen color
     * These commands do not come from user typed input
     * @param command command to be run
     */
    void passInternalCommands(String command);
    
    /**
     * get user input commands from the text box in the gui
     */
    void getBoxCommands();
    
    /**
     * returns the simple string property bound to the input property in the back end 
     * @return SimpleStringProperty containing input
     */
    SimpleStringProperty getInput();

}
