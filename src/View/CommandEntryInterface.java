package View;


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
     * get user input commands from the text box in the gui
     */
    void processCommands();




}
