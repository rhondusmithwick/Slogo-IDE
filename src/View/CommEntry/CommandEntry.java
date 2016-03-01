package View.CommEntry;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import Observables.ObjectObservable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * This class implements the CommandEntryInterface interface and allows the user to input
 * commands to be executed when execute button (within View class) is clicked
 *
 * @author Stephen
 */

public class CommandEntry implements CommandEntryInterface, Observer {

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String NEW_LINE = "\n";
    private static final String SHOW_IN_BOX = "show in text box";
    private final ObjectObservable<String> input, intCommands, commHistory;
    private final double WIDTH = 200.0;
    private final double HEIGHT = 400.0;
    private final int T_HEIGHT = 350;
    private TextArea myEntryBox;
    private ScrollPane myScrollPane;
    private VBox container;
    private Label title;
    private String dispLang;
    private ResourceBundle myResources;

    public CommandEntry(ObjectObservable<String> input, ObjectObservable<String> intCommands, ObjectObservable<String> commHistory) {
        this.dispLang = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + dispLang + DISP);
        this.input = input;
        this.intCommands = intCommands;
        this.commHistory = commHistory;
        intCommands.addObserver(this);
        container = new VBox();
        createTitle();
        createTextBox();
        myScrollPane = new ScrollPane(container);
        myScrollPane.setPrefSize(WIDTH, HEIGHT);
    }

    private void createTextBox () {
        myEntryBox = new TextArea();
        myEntryBox.setPrefSize(WIDTH, T_HEIGHT);
        container.getChildren().add(myEntryBox);
    }

    private void createTitle () {
        title = new Label(myResources.getString("entryTitle"));
        container.getChildren().add(title);
        container.setAlignment(Pos.TOP_CENTER);
    }

    @Override
    public Node getNode() {
        return myScrollPane;
    }


    private void getCommandsFromString(String text) {
        input.set(text);
    }
    
   private void passInternalCommands(String command, boolean showInTextBox) {
        if(showInTextBox){
            String curr = myEntryBox.getText();
            if(!curr.endsWith(NEW_LINE) && !curr.equals("")){
            	curr = curr + NEW_LINE + command;
            }else{
            	curr = curr + command;
            }
            myEntryBox.setText(curr);
        }else{
            getCommandsFromString(command);
        }
    }

   @Override
    public void processCommands() {
        String text = myEntryBox.getText();
        commHistory.set(text);
        getCommandsFromString(text);
        myEntryBox.clear();
    }

    @Override
    public void update (Observable o, Object arg) {

        String command = intCommands.get();
        boolean show =command.startsWith(SHOW_IN_BOX);
        if(show){
            
            command = command.substring(SHOW_IN_BOX.length());
        }
        passInternalCommands(command, show);
        
    }


}
