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
        super(Size.TURTLE_UPDATE_POPUP_HEIGHT.getSize(), Size.TURTLE_UPDATE_POPUP_WIDTH.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
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

    @Override
    protected void createScene() {
        configureTitleLabel();
        createCheckBoxes();
        createElementsBelowCheckBoxes();
        addButtonToDisplay();
        displayElements();
    }

    protected abstract void createElementsBelowCheckBoxes();

    protected abstract String makeCommand(String property);


    private void configureTitleLabel() {
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        addToScene(title);
    }

    private void createCheckBoxes() {
        String turtleIDString = turtleIDs.get();
        turtleIDString = turtleIDString.substring(1, turtleIDString.length() - 1);
        String[] turtleIDS = turtleIDString.split(", ");
        for (String ID : turtleIDS) {
            createCheckBox(ID.trim());
        }
    }

    private void createCheckBox(String title) {
        CheckBox checkBox = new CheckBox(title);
        checkBoxList.add(checkBox);
        addToScene(checkBox);
    }

    private void addButtonToDisplay() {
        addToScene(applyChangesButton);
    }

    private void displayElements() {
        addNodes(nodeList);
    }

    private void passCommand(String command) {
        internalCommand.set(command);
    }

    private void applyChanges() {
        StringBuilder activeTurtles = new StringBuilder();
        checkBoxList.stream().filter(CheckBox::isSelected).forEach(checkBox -> {
            activeTurtles.append(checkBox.getText()).append(" ");
        });
        passCommand(makeCommand(activeTurtles.toString()));
        closeScene();
    }

    protected void addToScene(Node node) {
        nodeList.add(node);
    }

    protected String getStringFromResources(String key) {
        return myResources.getString(key);
    }

    protected String translateCommand(String command) {
        return GetCommand.makeCommand(command, parsingLanguage.get());
    }

}
