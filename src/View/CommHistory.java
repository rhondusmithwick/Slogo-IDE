package View;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.List;


/**
 * This is an internal API for the view. It regulates any class that will be implemented to keep track of the command history. It ensures there is a way to access the node
 * displaying the history so it can be shown. It also provides a way to access the datastructure underlying the history node so that it in the future the commands could be saved
 * to a file. It also ensures that it is possible to add commands to the history.
 *
 * @author Cali
 */
public interface CommHistory {


    /**
     * creates the graphical node to show command history
     */
    void createCommHistory();

    /**
     * Adds a command to the command history and will display it in the command history box, and it should (hopefully) be clickable so that the command
     * can be run again.
     *
     * @param command command to add to history
     */
    Label addCommand(String command);

    /**
     * returns the graphical node displaying the history
     *
     * @return graphical node displaying command history
     */
    Node getHistoryGraphic();


    /**
     * Returns a string array of all previously executed commands in the session
     *
     * @return String array of command history
     */
    List<String> getCommands();

    /**
     * this sets the command entry object in the command history so that commands can be passed to the
     * entry model when clicked
     *
     * @param commEntry command entry module to be set
     */

    void setCommEntry(CommandEntryInterface commEntry);

}
