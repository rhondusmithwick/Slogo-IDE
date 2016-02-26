package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class implements the CommHistory interface and allows any previously executed commands
 * to be displayed on the GUI
 *
 * @author Stephen
 */

public class CommandHistoryDisplay implements CommHistory {

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private final double SCROLLPANE_WIDTH = 400.00;
    private final double SCROLLPANE_HEIGHT = 195.0;
    private ScrollPane myScrollPane;
    private Label title;
    private List<Label> commandLabels;
    private List<String> commands;
    private VBox myCommHistory;
    private ResourceBundle myResources;

    public CommandHistoryDisplay() {
        commands = new ArrayList<String>();
        commandLabels = new ArrayList<Label>();
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + "english" + "disp");
    }

    @Override
    public void createCommHistory() {
        myCommHistory = new VBox();
        myCommHistory.setPrefWidth(SCROLLPANE_WIDTH - 10);
        myScrollPane = new ScrollPane();
        myScrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        myScrollPane.setContent(myCommHistory);
        title = addCommand(myResources.getString("commBTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.setOnMouseClicked(null);
    }


    @Override
    public Label addCommand(String command) {
        if (command.isEmpty()) return null;
        commands.add(command);
        Label l = new Label(command + "\n");
        l.setPrefWidth(SCROLLPANE_WIDTH);
        l.setStyle("-fx-border-color: black;");
        l.setWrapText(true);
        l.setOnMouseClicked(e -> labelClicked(l));
        commandLabels.add(l);
        myCommHistory.getChildren().add(l);
        return l;
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
