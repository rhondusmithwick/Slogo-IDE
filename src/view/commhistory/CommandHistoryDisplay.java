package view.commhistory;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import view.Defaults;
import view.Size;

/**
 * This class implements the CommHistory interface and allows any previously executed commands
 * to be displayed on the GUI
 *
 * @author Stephen
 */

public class CommandHistoryDisplay implements Observer {
    private ScrollPane myScrollPane;
    private Label title;
    private List<Label> commandLabels;
    private List<String> commands;
    private VBox myCommHistory;
    private ResourceBundle myResources;

    private ObjectObservable<String> intCommand, commHistory;

    /**
     * Creates a new command history object.
     * @param intCommand string observable used to pass commands from view components to command entry
     * @param commHistory string observable observed by this to add commands to history display
     */
    public CommandHistoryDisplay(ObjectObservable<String> intCommand, ObjectObservable<String> commHistory) {
        this.intCommand = intCommand;
        this.commHistory=commHistory;
        commHistory.addObserver(this);
        this.commands = new ArrayList<>();
        this.commandLabels = new ArrayList<>();
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
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
        myScrollPane.setMinViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setPrefViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setMaxHeight(Size.BOTTOM_HEIGHT.getSize());
        HBox.setHgrow(myScrollPane, Priority.ALWAYS);
    }

    private void createTitle () {
        title = addCommand(myResources.getString("commBTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.setOnMouseClicked(null);
    }

    private Label addCommand(String command) {
        if (command.isEmpty()){
            return null;
        }
        commands.add(command);
        Label label = new Label(command);
        label.prefWidthProperty().bind(myScrollPane.widthProperty());
        label.setStyle(Defaults.BORDER_COLOR.getDefault() );
        label.setWrapText(true);
        label.setOnMouseClicked(e -> labelClicked(label));
        commandLabels.add(label);
        myCommHistory.getChildren().add(label);
        return label;
    }

    private void labelClicked(Label label) {
        String command = Defaults.COMMAND_TO_TEXT_BOX.getDefault()+label.getText();
        intCommand.set(command);
    }

    /**
     * returns the node containing all visual components for the command history display,
     * so it can be added to the view
     * @return node containing all command history components
     */
    public Node getHistoryGraphic() {
        return myScrollPane;
    }

    @Override
    public void update (Observable o, Object arg) {
        addCommand(commHistory.get());
    }


}
