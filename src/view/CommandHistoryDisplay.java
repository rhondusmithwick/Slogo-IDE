package view;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * This class implements the CommHistory interface and allows any previously executed commands 
 * to be displayed on the GUI
 * 
 * @author Stephen
 *
 */

public class CommandHistoryDisplay implements CommHistory {

	private final double SCROLLPANE_WIDTH = 400.00;
	private final double SCROLLPANE_HEIGHT = 195.0;

	private ScrollPane myScrollPane;
	private List<Label> commandLabels;
	private List<String> commands;
	private VBox myCommHistory;

	public CommandHistoryDisplay() {
		commands = new ArrayList<String>();	
		commandLabels = new ArrayList<Label>();
	}

	@Override
	public void createCommHistory() {
		myCommHistory = new VBox();
		myCommHistory.setPrefWidth(SCROLLPANE_WIDTH-10);
		myScrollPane = new ScrollPane();
		myScrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
		myScrollPane.setContent(myCommHistory);
		addCommand("Command History");
	}

	@Override
	public void addCommand(String command) {
		if (command.isEmpty()) return;
		commands.add(command);
		Label l = new Label(command+"\n");
		l.setPrefWidth(SCROLLPANE_WIDTH);
		l.setStyle("-fx-border-color: black;");
		l.setWrapText(true);
		l.setOnMouseClicked(e-> labelClicked(l));
		commandLabels.add(l);
		myCommHistory.getChildren().add(l);
	}

	private void labelClicked(Label l) {
		System.out.print(l.getText());
	}

	@Override
	public Node getHistoryGraphic() {
		return myScrollPane;
	}

	@Override
	public List<String> getCommands() {
		return commands;
	}

}
