package view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

public class CommandHistoryDisplay implements CommHistory {
	
	Label myCommandHistory;
	List<String> commands;

	public CommandHistoryDisplay() {
		commands = new ArrayList<String>();
		
	}

	@Override
	public void createCommHistory() {
		myCommandHistory = new Label();
		myCommandHistory.setBorder(null);
		addCommand("Command History");
		
	}

	@Override
	public void addCommand(String command) {
		if (command.isEmpty()) return;
		commands.add(command);
		StringBuilder sb = new StringBuilder();
		for (String s : commands) {
			sb.append(s + "\n");
		}
		myCommandHistory.setText(sb.toString());
	}

	@Override
	public Label getHistoryGraphic() {
		return myCommandHistory;
	}

	@Override
	public List<String> getCommands() {
		return commands;
	}

}
