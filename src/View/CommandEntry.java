package View;

import java.util.Observable;
import java.util.Observer;
import Observables.ObjectObservable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * This class implements the CommandEntryInterface interface and allows the user to input
 * commands to be executed when execute button (within View class) is clicked
 *
 * @author Stephen
 */

public class CommandEntry implements CommandEntryInterface, Observer {

    private static final String NEW_LINE = "\n";
    private static final String SHOW_IN_BOX = "show in text box";
    private final ObjectObservable<String> input, intCommands, commHistory;
    private final double WIDTH = 200.0;
    private final double HEIGHT = 400.0;
    private TextArea myEntryBox;
    private ScrollPane myScrollPane;

    public CommandEntry(ObjectObservable<String> input, ObjectObservable<String> intCommands, ObjectObservable<String> commHistory) {
        this.input = input;
        this.intCommands = intCommands;
        this.commHistory = commHistory;
        intCommands.addObserver(this);
        myEntryBox = new TextArea();
        myEntryBox.setPrefSize(WIDTH, HEIGHT);
        myScrollPane = new ScrollPane(myEntryBox);
        myScrollPane.setPrefSize(WIDTH, HEIGHT);
    }

    @Override
    public TextArea getTextBox() {
        return myEntryBox;
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
