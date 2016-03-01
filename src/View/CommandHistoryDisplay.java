package View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Observables.ObjectObservable;

/**
 * This class implements the CommHistory interface and allows any previously executed commands
 * to be displayed on the GUI
 *
 * @author Stephen
 */

public class CommandHistoryDisplay implements CommHistory {
    private static final String SHOW_IN_BOX = "show in text box";
    private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DISP = "DISP";
    private final double SCROLLPANE_WIDTH = 430.00;
    private final double SCROLLPANE_HEIGHT = 195.0;
    private ScrollPane myScrollPane;
    private Label title;
    private List<Label> commandLabels;
    private List<String> commands;
    private VBox myCommHistory;
    private ResourceBundle myResources;
    private String language;
    private ObjectObservable<String> intCommand;

    public CommandHistoryDisplay(ObjectObservable<String> intCommand) {
        this.language = DEFAULT_LANGUAGE;
        this.intCommand = intCommand;
        commands = new ArrayList<>();
        commandLabels = new ArrayList<>();
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language + DISP);
    }

    @Override
    public void createCommHistory() {
        myCommHistory = new VBox();
        myCommHistory.setPrefWidth(SCROLLPANE_WIDTH);
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
        Label l = new Label(command);
        l.setPrefWidth(SCROLLPANE_WIDTH);
        l.setStyle(CSS_BLACK_BORDER);
        l.setWrapText(true);
        l.setOnMouseClicked(e -> labelClicked(l));
        commandLabels.add(l);
        myCommHistory.getChildren().add(l);
        return l;
    }

    private void labelClicked(Label l) {
        String command = SHOW_IN_BOX+l.getText();
        intCommand.set(command);
        
    }

    @Override
    public Node getHistoryGraphic() {
        return myScrollPane;
    }


}
