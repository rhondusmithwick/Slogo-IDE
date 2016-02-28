package view;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * This class implements the CommandEntryInterface interface and allows the user to input
 * commands to be executed when execute button (within View class) is clicked
 *
 * @author Stephen
 */

public class CommandEntry implements CommandEntryInterface {

    private final ObjectObservable<String> input;
    private final double WIDTH = 200.0;
    private final double HEIGHT = 400.0;
    private TextArea myEntryBox;
    private ScrollPane myScrollPane;

    public CommandEntry(ObjectObservable<String> input) {
        this.input = input;
    }

    @Override
    public TextArea getTextBox() {
        return myEntryBox;
    }

    @Override
    public void getCommandsFromString(String text) {
        input.set(text);
    }

    @Override
    public void clearCommands() {
        myEntryBox.clear();
    }

    @Override
    public void createEntryBox() {
        myEntryBox = new TextArea();
        myEntryBox.setPrefSize(WIDTH, HEIGHT);
        myScrollPane = new ScrollPane(myEntryBox);
        myScrollPane.setPrefSize(WIDTH, HEIGHT);
    }

    public Node getRootNode() {
        return myScrollPane;
    }

    @Override
    public void passInternalCommands(String command, boolean showInTextBox) {
        if(showInTextBox){
            String curr = myEntryBox.getText();
            curr = curr + "\n" + command;
            myEntryBox.setText(curr);
        }else{
            getCommandsFromString(command);
        }
        

    }

    @Override
    public void getBoxCommands() {
        String text = myEntryBox.getText();
        getCommandsFromString(text);

    }

    @Override
    public SimpleStringProperty getInput() {
        return null;
    }


}
