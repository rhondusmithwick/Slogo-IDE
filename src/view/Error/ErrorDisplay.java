package view.Error;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ResourceBundle;
import view.Defaults;



public class ErrorDisplay{


    private ResourceBundle myResources;
    private SimpleStringProperty error;
    
    public ErrorDisplay(SimpleStringProperty error){
        this.error = error;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() );

    }
    
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
