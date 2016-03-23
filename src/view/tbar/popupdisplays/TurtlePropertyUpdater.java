// This entire file is part of my masterpiece.
// Stephen Kwok

// The purpose of this class is to serve as the base class for all pop-ups that allow the user to select created turtles
// and update some turtle property

// I believe this code is well designed for many reasons. For one, the use of SimpleStringPropertys and Observables 
// allows this class to communicate with the back end but at the same time be loosely coupled to the back end. By this I mean that 
// this class needs to know nothing about classes in the back end in order to access or change values needed
// by both ends. For example, to tell the back end to execute a command, this class simply needs to format the command
// to be executed, then call internalCommand.set(formattedCommand). Because internalCommand is an Observable, the back end
// will immediately be notified of internalCommand's change in state, and then retrieve that command from the Observable 
// in order to parse and execute it. Regardless of how various classes are written or how various features are implemented,
// the front and back ends can communicate in this way without making any changes. The Open/Closed Principle is upheld 
// since the way in which the front/back ends communicate is closed to modification.

// This class is also well-designed because it balances being flexible enough to be extended by various sub classes, yet 
// contains enough of the similarities between subclasses that the subclasses can actually benefit from extending this abstract
// class. In terms of flexibility, a look at the subclasses will demonstrate how the current design supports updating of
// any turtle property  and for an infinite number of turtles. Other features the current design could support (but the project
// does not implement) include updating of multiple properties at the same time and adding additional GUI elements to the pop-up.
// In terms of providing subclasses functionality, this abstract class supports features common to all property-updating subclasses
// including translating commands into the proper parsing language, passing the translated command to the back end for execution, 
// and generating GUI elements such as the title Label, check boxes for each turtle, etc.

// Last but not least, the code is clean and readable. Variable/method names are descriptive and not abbreviated, method lengths 
// are short, methods serve a single purpose, and there is no duplicated code. To help create short, clean code, I used lambdas
// in the applyChanges() method on line 173 to help consolidate what may have taken 8-10 lines into 2 lines.

package view.tbar.popupdisplays;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.utilities.ButtonFactory;
import view.utilities.GetCommand;
import view.utilities.PopUp;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Abstract class that generates a PopUp with checkboxes that allows user to select
 * multiple turtles and apply changes to the turtles' properties
 *
 * @author Stephen
 */

public abstract class TurtlePropertyUpdater extends PopUp {

    private final List<Node> nodeList;
    private final List<CheckBox> checkBoxList;
    private final ResourceBundle myResources;
    private final SimpleStringProperty turtleIDs;
    private final Button applyChangesButton;
    private final Label title;
    private final ObjectObservable<String> internalCommand;
    private final ObjectObservable<String> parsingLanguage;

    /**
     * Creates new turtleproperty updater instace
     *
     * @param turtleIDs       list of ids of all created turtles
     * @param internalCommand simplestring property to pass input to command entry instance
     * @param parsingLanguage current parsing language in simplestring property form
     * @param titleText       String text to show as title
     */
    protected TurtlePropertyUpdater(SimpleStringProperty turtleIDs,
                                    ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, String titleText) {
        super(Size.TURTLE_UPDATE_POPUP_WIDTH.getSize(), Size.TURTLE_UPDATE_POPUP_HEIGHT.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.parsingLanguage = parsingLanguage;
        this.internalCommand = internalCommand;
        this.turtleIDs = turtleIDs;
        this.title = new Label(myResources.getString(titleText));
        nodeList = new ArrayList<>();
        checkBoxList = new ArrayList<>();
        applyChangesButton = ButtonFactory.createButton(myResources.getString("applyChanges"),
                e -> applyChanges());
    }

    /**
     * Initializes and displays all elements of pop up
     */
    @Override
    protected void createScene() {
        configureTitleLabel();
        createCheckBoxes();
        createElementsBelowCheckBoxes();
        addButtonToDisplay();
        displayElements();
    }

    /**
     * Creates any GUI elements such as text fields for taking user input, buttons, etc.
     * that appear below the check boxes within the pop up
     */
    protected abstract void createElementsBelowCheckBoxes();

    /**
     * Formats a String into a command to be passed to back end for parsing and execution
     * Command is specific to sub class and is applied to all selected turtles
     * 
     * @param turtleIDs String listing turtles to act on with command 
     * @return formatted command as String to be passed to back end for parsing and execution
     */
    protected abstract String makeCommand(String turtleIDs);

    /**
     * Sets title of pop up
     */
    private void configureTitleLabel() {
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        addToScene(title);
    }

    /**
     * Creates a check box for each created turtle 
     */
    private void createCheckBoxes() {
        String turtleIDString = turtleIDs.get();
        turtleIDString = turtleIDString.substring(1, turtleIDString.length() - 1);
        String[] turtleIDS = turtleIDString.split(", ");
        for (String ID : turtleIDS) {
            createCheckBox(ID.trim());
        }
    }

    /**
     * Creates a check box for turtle given 
     * @param title - ID of turtle to create check box for
     */
    private void createCheckBox(String title) {
        CheckBox checkBox = new CheckBox(title);
        checkBoxList.add(checkBox);
        addToScene(checkBox);
    }

    /**
     * Adds button to allow user to apply changes
     */
    private void addButtonToDisplay() {
        addToScene(applyChangesButton);
    }

    /**
     * Adds all GUI elements to Group to be displayed
     */
    private void displayElements() {
        addNodes(nodeList);
    }

    /**
     * Passes given command to back end
     * @param command - command to be passed to back end
     */
    private void passCommand(String command) {
        internalCommand.set(command);
    }

    /**
     * Passes command to back end to update turtle properties after turtles are 
     * selected and any other user input is retrieved 
     */
    private void applyChanges() {
        StringBuilder activeTurtles = new StringBuilder();
        checkBoxList.stream().filter(CheckBox::isSelected).forEach(checkBox -> {
            activeTurtles.append(checkBox.getText()).append(" ");
        });
        passCommand(makeCommand(activeTurtles.toString()));
        closeScene();
    }

    /**
     * Adds a GUI element to list of all GUI elements in class
     * @param node - node to be added to list of GUI elements
     */
    protected void addToScene(Node node) {
        nodeList.add(node);
    }

    /**
     * Retrieves a String from resource bundle given a key
     * 
     * @param key - Key for String in resource bundle
     * @return String associated with key from resource bundle
     */
    protected String getStringFromResources(String key) {
        return myResources.getString(key);
    }

    /**
     * Translates English command to command in current parsing language
     * 
     * @param command - user command in English
     * @return - user command in current parsing language
     */
    protected String translateCommand(String command) {
        return GetCommand.makeCommand(command, parsingLanguage.get());
    }

}
