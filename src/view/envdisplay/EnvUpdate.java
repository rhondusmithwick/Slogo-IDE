package view.envdisplay;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.GetCommand;
import view.utilities.PopUp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import observables.ObjectObservable;

/**
 * this class represents an abstract base for classes that update objects defined by the
 * user. as it is a popup, it also extends the abstract PopUp class.
 * @author calinelson
 *
 */
public abstract class EnvUpdate extends PopUp{

    private ObjectObservable<String> intCommand;
    private ResourceBundle myResources;
    private Button setB;
	private String newVal;
    private ObjectObservable<String> pLang;

    /**
     * Super constructor for any envupdate subclass instance
     * @param intCommand string observable for passing commands to command entry instance
     * @param pLang string observable that stores the current parsing language
     */
    public EnvUpdate(ObjectObservable<String> intCommand, ObjectObservable<String> pLang){
    	super(Size.ENV_WIDTH.getSize(), Size.ENV_HEIGHT.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.intCommand=intCommand;
        this.pLang = pLang;
        
    }

    /**
     * creates the scene to be displayed in the popup window.
     */
    @Override
    protected void createScene(){
    	createSetButton();
        createTextFields();
        updateEnv();
    }
    
    /**
     * creates the text fields where the user will enter new values
     * into
     */
    protected abstract void createTextFields ();
    
    /**
     * Updates the passed label with the new text value created by the updater
     * @param l label to be updated
     */
    public void updateLabel(Label l){
    	l.setText(newVal);
    }


    private void createSetButton() {
        setB = new Button(myResources.getString("upButton"));
        setB.setAlignment(Pos.TOP_CENTER);
        setB.setOnAction(e->setNewValues());

    }

    /**
     * creates a new text field area to be used for user input
     * @return created textField instance
     */
    protected TextField createTextArea() {
        TextField tField = new TextField();
        tField.prefWidthProperty().bind(getSize(false));
        VBox.setVgrow(tField, Priority.ALWAYS);
        return tField;
    }
    

    /**
     * creates a label as a title for a certain component
     * @param titleName key to resource bundle for title string to display
     * @param other any other string to be added on to end of title taken from resource bundle
     * @return created label
     */
	protected Label createTitle(String titleName, String other) {
        Label title = new Label(myResources.getString(titleName)+other);
        title.prefWidthProperty().bind(getSize(false));
        title.setAlignment(Pos.TOP_CENTER);
        return title;
    }
    
	/**
	 * Creates a command to be passed to backend using new values entered by user
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
    public abstract void updateEnv();
    
    /**
     * adds all nodes in given list to the scene in given order, and 
     * adds button to set new values afterwards
     * @param nodeList list of nodes to be added
     */
    protected void addToScene(List<Node> nodeList){
        addNodes(nodeList);
        addNodes(Arrays.asList(setB));
    }
    	
    /**
     * passes given command to the command entry instance
     * which then passes it to the backend
     * @param command command to be passed
     */
    protected void passCommand(String command){
        intCommand.set(command);
    }
    
    /**
     * Gets the correct command given the correct command key, 
     * and the current parsing langauge
     * @param key command key
     * @return String command in correct parsing language
     */
    protected String makeCommand (String key) {
        return GetCommand.makeCommand(key,pLang.get() );
    }


}
