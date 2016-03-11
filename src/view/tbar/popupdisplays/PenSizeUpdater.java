package view.tbar.popupdisplays;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;

/**
 * Class responsible for creating popup that allows user to select certain turtles
 * and change their pen sizes to a given value
 * 
 * @author Stephen
 *
 */

public class PenSizeUpdater extends TurtlePropertyUpdater{
	
    private TextField userInput;
    
    /**
     * creates new PenSizeUpdater instance
     * @param language string observable for setting and getting parsing language
     * @param intCommand string observable to pass commands to command entry instance to pass to backend
     */
    
    public PenSizeUpdater(SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage){
        super( turtleIDs, internalCommand, parsingLanguage);
        setTitle("penSizeUpdateTitle");
    }

	@Override
	protected void createElementsBelowCheckBoxes() {
		userInput = new TextField();
		userInput.prefWidthProperty().bind(getSize(false));
        VBox.setVgrow(userInput, Priority.ALWAYS);
        addToScene(new Label(getStringFromResources("penSizeInputPrompt")));
        addToScene(userInput);
	}

	@Override
	protected String makeCommand(String turtleIDs) {
		String askCommand = translateCommand("Ask");
		String penSizeCommand = translateCommand("SetPenSize");
		String newPenSize = userInput.getText().trim();
		return askCommand + " [ " + turtleIDs + "] [ " + penSizeCommand + " " + newPenSize + " ]";
	}
    
}
