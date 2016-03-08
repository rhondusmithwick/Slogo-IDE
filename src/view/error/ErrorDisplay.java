package view.error;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ResourceBundle;
import view.Defaults;

/**
 * Class responsible for waiting for and then displaying any error messages from other components, or the parser
 * on the backend.
 * @author Cali
 *
 */

public class ErrorDisplay{


    private ResourceBundle myResources;
    private SimpleStringProperty error;
    
    /**
     * creates new errordisplay instance
     * @param error simplestringproperty other components use to pass error messages to be displayed
     */
    public ErrorDisplay(SimpleStringProperty error){
        this.error = error;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() );

    }
    
    /**
     * sets the error display for use by setting the display to listen for changes the the error
     * simplestring property, and then showing the error when the property changes
     */
    public void set(){
    	error.addListener((ov, oldVal, newVal) -> 
        handleError(newVal));
    }

    private void showError(String errorText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("errorTitle"));
        alert.setHeaderText(myResources.getString("errorHeader"));
        alert.setContentText(errorText);
        alert.show();

    }


    private void handleError(String newError){
    	if(newError.equals("")){
    		return;
    	}
    	showError(newError);
    }

}
