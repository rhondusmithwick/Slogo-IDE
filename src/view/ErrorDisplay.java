package view;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorDisplay implements ErrorDisplayInterface {

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
	private VBox errorDisp;
	private Label title;
	private ResourceBundle myResources;
	
	@Override
	public void createErrorDisplay() {
		myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + "english"+"disp");
		errorDisp = new VBox();
		title = new Label("Errors");
		title.setAlignment(Pos.TOP_CENTER);
		errorDisp.getChildren().add(title);
		

	}

	@Override
	public void showError(String s) {
		

	}

	@Override
	public void clearError() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getErrorDisplay() {
		return errorDisp;
	}

}
