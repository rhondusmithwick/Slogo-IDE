package View.CommEntry;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import Observables.ObjectObservable;
import View.Defaults;
import View.Size;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * This class implements the CommandEntryInterface interface and allows the user to input
 * commands to be executed when execute button (within View class) is clicked
 *
 * @author Stephen
 */

public class CommandEntry implements Observer {

    private final ObjectObservable<String> input, intCommands, commHistory;


    private TextArea myEntryBox;
    private ScrollPane myScrollPane;
    private VBox container;
    private Label title;
    private String dispLang;
    private ResourceBundle myResources;

    public CommandEntry(ObjectObservable<String> input, ObjectObservable<String> intCommands, ObjectObservable<String> commHistory) {
        this.dispLang = Defaults.DISPLAY_LANG.getDefault();
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() + dispLang);
        this.input = input;
        this.commHistory = commHistory;
        this.intCommands = intCommands;
        intCommands.addObserver(this);
        setScrollPane();
        container = new VBox();
        createTitle();
        createTextBox();
        myScrollPane.setContent(container);
        
        
    }

    private void setScrollPane () {
        myScrollPane = new ScrollPane();
        myScrollPane.setMinViewportWidth(Size.RIGHT_WIDTH.getSize());
        myScrollPane.setPrefViewportWidth(Size.RIGHT_WIDTH.getSize());
        myScrollPane.setMaxWidth(Size.RIGHT_WIDTH.getSize());
        VBox.setVgrow(myScrollPane, Priority.SOMETIMES);
    }

    private void createTextBox () {
        myEntryBox = new TextArea();
        myEntryBox.prefHeightProperty().bind(myScrollPane.heightProperty().subtract(Size.COMMAND_TITLE.getSize()));
        myEntryBox.prefWidthProperty().bind(myScrollPane.widthProperty().subtract(Size.COMM_ENTRY_SPACE.getSize()));
        container.getChildren().add(myEntryBox);
    }

    private void createTitle () {
        title = new Label(myResources.getString("entryTitle"));
        container.getChildren().add(title);
        container.setAlignment(Pos.TOP_CENTER);

    }


    public Node getNode() {
        return myScrollPane;
    }


    private void getCommandsFromString(String text) {
        input.set(text);
    }
    
   private void passInternalCommands(String command, boolean showInTextBox) {
        if(showInTextBox){
            String curr = myEntryBox.getText();
            if(!curr.endsWith("\n") && !curr.equals("")){
            	curr = curr + "\n" + command;
            }else{
            	curr = curr + command;
            }
            myEntryBox.setText(curr);
        }else{
            getCommandsFromString(command);
        }
    }


    public void processCommands() {
        String text = myEntryBox.getText();
        commHistory.set(text);
        getCommandsFromString(text);
        myEntryBox.clear();
    }

    @Override
    public void update (Observable o, Object arg) {

        String command = intCommands.get();
        boolean show =command.startsWith(Defaults.COMMAND_TO_TEXT_BOX.getDefault());
        if(show){
            
            command = command.substring(Defaults.COMMAND_TO_TEXT_BOX.getDefault().length());
        }
        passInternalCommands(command, show);
        
    }


}
