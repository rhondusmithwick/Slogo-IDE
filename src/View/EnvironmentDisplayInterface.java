package View;


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
     * Updates the graphical node showing user set methods or variables to reflect the current
     * state of these variables in the backend.
     */
    void updateEnvNode();
    



}
