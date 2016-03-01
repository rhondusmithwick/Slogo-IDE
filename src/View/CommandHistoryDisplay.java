package View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import Observables.ObjectObservable;

/**
 * This class implements the CommHistory interface and allows any previously executed commands
 * to be displayed on the GUI
 *
 * @author Stephen
 */

public class CommandHistoryDisplay implements CommHistory, Observer {
    private static final String SHOW_IN_BOX = "show in text box";
    private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DISP = "DISP";
    private final double STARTING_HEIGHT = 195.0;
    private ScrollPane myScrollPane;
    private Label title;
    private List<Label> commandLabels;
    private List<String> commands;
    private VBox myCommHistory;
    private ResourceBundle myResources;
    private String language;
    private ObjectObservable<String> intCommand, commHistory;

    public CommandHistoryDisplay(ObjectObservable<String> intCommand, ObjectObservable<String> commHistory) {
        this.language = DEFAULT_LANGUAGE;
        this.intCommand = intCommand;
        this.commHistory=commHistory;
        commHistory.addObserver(this);
        this.commands = new ArrayList<>();
        this.commandLabels = new ArrayList<>();
        this.myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language + DISP);
        
        createScrollPane();
        createVBox();
        createTitle();
        myScrollPane.setContent(myCommHistory);
    }

    private void createVBox () {
        myCommHistory = new VBox();
        myCommHistory.prefWidthProperty().bind(myScrollPane.widthProperty());

    }

    private void createScrollPane () {
        myScrollPane = new ScrollPane(); 
        myScrollPane.setMinViewportHeight(STARTING_HEIGHT);
        myScrollPane.setPrefViewportHeight(STARTING_HEIGHT);
        myScrollPane.setMaxHeight(STARTING_HEIGHT);
        HBox.setHgrow(myScrollPane, Priority.ALWAYS);
       
    }

    private void createTitle () {
        title = addCommand(myResources.getString("commBTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.setOnMouseClicked(null);
    }

    private Label addCommand(String command) {
        if (command.isEmpty()) return null;
        commands.add(command);
        Label l = new Label(command);
        l.prefWidthProperty().bind(myScrollPane.widthProperty());
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

    @Override
    public void update (Observable o, Object arg) {
        
        addCommand(commHistory.get());
        
    }


}
