package view.commentry;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.GlobalProperties;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * This class allows the user to input commands to be executed when execute button
 * (within View class) is clicked
 *
 * @author Stephen
 */

public class CommandEntry implements Observer {

    private final ObjectObservable<String> input, internalCommand, commandHistory;


    private TextArea myEntryBox;
    private ScrollPane myScrollPane;
    private final VBox container;
    private final ResourceBundle myResources;


    /**
     * creates new command entry object
     *
     * @param globalProperties observable string to pass input to backend
     * @param intCommands      observable string used to pass input from other view components to command entry
     * @param commHistory      observable string used to pass entered commands to be entered into history
     */
    public CommandEntry(GlobalProperties globalProperties, ObjectObservable<String> intCommands, ObjectObservable<String> commHistory) {
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.input = globalProperties.getInput();
        this.commandHistory = commHistory;
        this.internalCommand = intCommands;
        intCommands.addObserver(this);
        setScrollPane();
        container = new VBox();
        createTitle();
        createTextBox();
        myScrollPane.setContent(container);
    }

    private void setScrollPane() {
        myScrollPane = new ScrollPane();
        myScrollPane.setMinViewportWidth(Size.RIGHT_WIDTH.getSize());
        myScrollPane.setPrefViewportWidth(Size.RIGHT_WIDTH.getSize());
        myScrollPane.setMaxWidth(Size.RIGHT_WIDTH.getSize());
        VBox.setVgrow(myScrollPane, Priority.SOMETIMES);
    }

    private void createTextBox() {
        myEntryBox = new TextArea();
        myEntryBox.prefHeightProperty().bind(myScrollPane.heightProperty().subtract(Size.COMMAND_TITLE.getSize()));
        myEntryBox.prefWidthProperty().bind(myScrollPane.widthProperty().subtract(Size.COMM_ENTRY_SPACE.getSize()));
        container.getChildren().add(myEntryBox);
    }

    private void createTitle() {
        Label title = new Label(myResources.getString("entryTitle"));
        container.getChildren().add(title);
        container.setAlignment(Pos.TOP_CENTER);
    }

    /**
     * returns the Node containing all visual components needed for the command
     * entry component of the view
     *
     * @return node containing all command entry components
     */
    public Node getNode() {
        return myScrollPane;
    }

    private void passInternalCommands(String command, boolean showInTextBox) {
        if (showInTextBox) {
            String curr = myEntryBox.getText();
            if (!curr.endsWith("\n") && !curr.equals("")) {
                curr = curr + "\n" + command;
            } else {
                curr = curr + command;
            }
            myEntryBox.setText(curr);
        } else {
            input.set(command);
        }
    }

    /**
     * Gets all entered text from the text box, sends the commands to the back end,
     * adds commands to the history, and clears the text box for further use.
     */
    public void processCommands() {
        String text = myEntryBox.getText();
        commandHistory.set(text);
        input.set(text);
        myEntryBox.clear();
    }

    @Override
    public void update(Observable o, Object arg) {

        String command = internalCommand.get();
        boolean show = command.startsWith(Defaults.COMMAND_TO_TEXT_BOX.getDefault());
        if (show) {
            command = command.substring(Defaults.COMMAND_TO_TEXT_BOX.getDefault().length());
        }
        passInternalCommands(command, show);

    }


}
