package view.envdisplay;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.utilities.ButtonFactory;
import view.utilities.GetCommand;
import view.utilities.PopUp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * this class represents an abstract base for classes that acts on objects defined by the
 * user. as it is a popup, it also extends the abstract PopUp class.
 *
 * @author calinelson
 */
public abstract class EnvActor extends PopUp {

    private final ObjectObservable<String> internalCommand;
    private final ResourceBundle myResources;
    private Button setButton;
    private final ObjectObservable<String> parsingLanguage;

    /**
     * Super constructor for any envupdate subclass instance
     *
     * @param intCommand string observable for passing commands to command entry instance
     * @param pLang      string observable that stores the current parsing language
     */
    protected EnvActor(ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
        super(Size.ENV_WIDTH.getSize(), Size.ENV_HEIGHT.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.internalCommand = internalCommand;
        this.parsingLanguage = parsingLanguage;

    }

    /**
     * creates the scene to be displayed in the popup window.
     */
    @Override
    protected void createScene() {
        createSetButton();
        createTextFields();
        updateEnv();
    }

    /**
     * creates the text fields where the user will enter new values
     * into
     */
    protected abstract void createTextFields();

    /**
     * Updates the passed label with the new text value created by the updater
     *
     * @param l label to be updated
     */


    private void createSetButton() {
        setButton = ButtonFactory.createButton(myResources.getString("upButton"), e -> setNewValues());
        setButton.setAlignment(Pos.TOP_CENTER);

    }

    /**
     * creates a new text field area to be used for user input
     *
     * @return created textField instance
     */
    protected TextField createTextArea() {
        TextField textField = new TextField();
        textField.prefWidthProperty().bind(getSize(false));
        VBox.setVgrow(textField, Priority.ALWAYS);
        return textField;
    }


    /**
     * creates a label as a title for a certain component
     *
     * @param titleName key to resource bundle for title string to display
     * @param other     any other string to be added on to end of title taken from resource bundle
     * @return created label
     */
    protected Label createTitle(String titleName, String other) {
        Label title = new Label(myResources.getString(titleName) + other);
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }

    /**
     * Creates a command to be passed to backend using new values entered by user
     *
     * @param newVals String[] of new values entered by user
     * @return String command created to be passes to back end
     */
    protected abstract String getCommand(String[] newVals);

    /**
     * gets the user input from the text fields and sets new values for
     * certain elements of the user defined object
     */
    protected abstract void setNewValues();

    /**
     * creates necessary components for scene(i.e. title label) and adds them
     * to the scene
     */
    protected abstract void updateEnv();

    /**
     * adds all nodes in given list to the scene in given order, and
     * adds button to set new values afterwards
     *
     * @param nodeList list of nodes to be added
     */
    protected void addToScene(List<Node> nodeList) {
        addNodes(nodeList);
        addNodes(Collections.singletonList(setButton));
    }

    /**
     * passes given command to the command entry instance
     * which then passes it to the backend
     *
     * @param command command to be passed
     */
    protected void passCommand(String command) {
        internalCommand.set(command);
    }

    /**
     * Gets the correct command given the correct command key,
     * and the current parsing language
     *
     * @param key command key
     * @return String command in correct parsing language
     */
    protected String makeCommand(String key) {
        return GetCommand.makeCommand(key, parsingLanguage.get());
    }


}
