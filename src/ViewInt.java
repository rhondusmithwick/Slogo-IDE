/**
 * This is the external Interface for the View. It is the external API for displaying the results of SLOGO commands, as well as taking in the input SLOGO commands, and displaying
 * any errors that may be caught by the Model or Controller.
 * @author Cali
 *
 */
public interface ViewInt {
    
    
    /**
     * This method will recieve a string from the Controller/Backend whenever the Backend detects an error with the entered commands. The View will recieve that string and
     * use it to display a user friendly error message to the user in a box below the turtle display.
     * @param Error String representing the error caught by the backend
     */
    void passError(String Error);
    
    
    
    
    /**
     * This method ensures that the View will always have a method whose responsiblity is passing an input command to the controller or model. This method will be called once the 
     * user has entered commands into the text box and has pressed the execute button, indicating that they wish the commands to be processed.
     * @param command String indicating SLOGO command that the user has entered
     */
    void passInput(String command);
    
    
}
