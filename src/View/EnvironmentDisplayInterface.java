package View;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;

/**
 * This class is an Internal API for the view providing visibility to the user defined variables and commands in the program. The classes that implement this will be
 * ones that aim to display either set variables or defined methods during the simulation
 *
 * @author Cali
 */
public interface EnvironmentDisplayInterface {

    /**
     * gets the graphical node displaying the environmental variables or methods
     *
     * @return CommandNode displaying environmental variables or methods
     */
    Node getEnvDisplay();


    /**
     * creates the graphical node to show the user set methods or variables
     */
    void createEnvNode();


    /**
     * Updates the graphical node showing user set methods or variables to reflect the current
     * state of these variables in the backend.
     */
    void updateEnvNode();
    
    SimpleStringProperty getEnvProperty();


    void setCommEntry (CommandEntryInterface cEnt);


	void setPLang(ObjectObservable<String> str);
}
