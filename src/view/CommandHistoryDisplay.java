package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * This class implements the CommHistory interface and allows any previously executed commands 
 * to be displayed on the GUI
 * 
 * @author Stephen
 *
 */

public class CommandHistoryDisplay implements CommHistory {

	private final double SCROLLPANE_WIDTH = 200.0;
	private final double SCROLLPANE_HEIGHT = 170.0;

	private ScrollPane myScrollPane;
	private Label myCommandHistory;
	private List<String> commands;

	public CommandHistoryDisplay() {
		commands = new ArrayList<String>();
	}

	@Override
	public void createCommHistory() {
		myCommandHistory = new Label();
		myScrollPane = new ScrollPane();
		myScrollPane.setMaxSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
		myScrollPane.setMinSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
		myScrollPane.setContent(myCommandHistory);
	}

	@Override
	public void addCommand(String command) {
		if (command.isEmpty()) return;
		commands.add(command);
		StringBuilder sb = new StringBuilder();
		for (String s : commands) {
			sb.append(s + "\n");
		}
		myCommandHistory.setText(sb.toString().trim());
	}

	@Override
	public Node getHistoryGraphic() {
		return myCommandHistory;
	}

	@Override
	public List<String> getCommands() {
		return commands;
	}

	public Node getRootNode() {
		return myScrollPane;
	}

}
