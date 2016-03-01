package View;

import javafx.scene.Node;


/**
 * This is an internal API for the view. It regulates any class that will be implemented to keep track of the command history. It ensures there is a way to access the node
 * displaying the history so it can be shown. It also provides a way to access the datastructure underlying the history node so that it in the future the commands could be saved
 * to a file. It also ensures that it is possible to add commands to the history.
 *
 * @author Cali
 */
public interface CommHistory {


    /**
     * returns the graphical node displaying the history
     *
     * @return graphical node displaying command history
     */
    Node getHistoryGraphic();





}
