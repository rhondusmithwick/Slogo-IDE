package view;
/**
 * This is an Internal API for the view. Its purpose is to provide a framework for any class looking to implement the feature to display
 * errors.  
 * @author Cali
 *
 */
public interface ErrorDisplayInterface {
    
    /**
     * creates the graphical node used to display errors
     */
    void createErrorDisplay();
    
    /**
     * Displays an error string to the user
     * @param s Error message to be displayed
     */
    void showError(String s);
    
    /**
     * Clears the error display box if any error message is currently displayed
     */
    void clearError();
    
}
