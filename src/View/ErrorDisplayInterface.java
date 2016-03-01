package View;

import Observables.ObjectObservable;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * This is an Internal API for the view. Its purpose is to provide a framework for any class looking to implement the feature to display
 * errors.
 *
 * @author Cali
 */
public interface ErrorDisplayInterface {

    /**
     * creates the graphical node used to display errors
     */
    void createErrorDisplay (ObjectObservable<String> error);



    /**
     * returns graphical node used to display errors
     *
     * @return the graphical node that errors are displaed in
     */
    Node getErrorDisplay();

    /**
     * removes a label from the graphical error node
     *
     * @param l label to be removed
     */
    void clearError(Label l);

    
    


}
