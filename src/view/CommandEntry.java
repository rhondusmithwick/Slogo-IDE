package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class CommandEntry implements CommandEntryInterface {

	private final double WIDTH = 200.0;
	private final double HEIGHT = 400.0;
	private TextArea myEntryBox;

	@Override
	public TextArea getTextBox() {
		return myEntryBox;
	}

	@Override
	public SimpleStringProperty getCommands() {
		String text = myEntryBox.getText();
		SimpleStringProperty stringWrapper = new SimpleStringProperty();
		stringWrapper.setValue(text);
		return stringWrapper;
		
	}

	@Override
	public void clearCommands() {
		myEntryBox.clear();
	}

	@Override
	public void createEntryBox() {
		myEntryBox = new TextArea();
		myEntryBox.setPrefSize(WIDTH, HEIGHT);
	}

}
