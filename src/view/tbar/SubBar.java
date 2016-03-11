package view.tbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.ButtonFactory;
import view.utilities.ComboFactory;
import view.utilities.FileGetter;
import view.utilities.GetCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import maps.ColorMap;
import maps.IndexMap;
import observables.ObjectObservable;

/**
 * This is an abstract class that provides a base for creating the different bar objects displayed in the
 * tool bar in the view.
 * @author Cali
 *
 */

public abstract class SubBar{

    private ResourceBundle myResources;
    private HBox container;
    private ObjectObservable<String> language, internalCommand;
    private ColorMap colorMap;

    /**
     * Super constructor for subbar sub classes.
     * @param language string observable for setting and storing parsing language
     * @param intCommand string observable for passing commands to command entry instance
     * @param cMap Index map object for mapping colors to integer indexes
     */
    public SubBar(ObjectObservable<String> language, ObjectObservable<String> internalCommand, 
    		IndexMap colorMap){
        this.language=language;
        this.internalCommand=internalCommand;
        this.colorMap = (ColorMap) colorMap;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        initHBox();
        setParsingLanguage(language.get());
        createButtons();
        createComboBoxes();
    }

    /**
     * sets the parsing language
     * @param pLang string observable used to set parsing language in front and back end
     */
    protected void setParsingLanguage(String pLang){

        language.set(pLang);
    }
    
    /**
     * passes a command to the command entry instance to be passed to the back end
     * @param command command string to be passed
     */
    protected void passCommand(String command){
        internalCommand.set(command);
    }
    
    /**
     * initializes the HBox that will contain all of the different subbar nodes
     */
    public void initHBox(){
        container = new HBox(Size.TB_PADDING.getSize());
        container.setAlignment(Pos.CENTER);

    }

    /**
     * creates a button to be added to sub bar
     * @param key key string to be used to get button title from resource bundle
     * @param handler event handler to be called when button is pressed
     * @return newly created button
     */
    public Button makeButton(String key, EventHandler<ActionEvent> handler) {
        Button newButton = ButtonFactory.createButton(myResources.getString(key), handler);
        container.getChildren().add(newButton);
        HBox.setHgrow(newButton, Priority.ALWAYS);
        return newButton;
    }
    
    /**
     * Given the command key, retrieves the user command from the correct language 
     * resource bundle and returns it
     * @param key key for command wanted
     * @return user command related to key
     */
    protected String getCommand(String key) {
        return GetCommand.makeCommand(key, language.get());
    }
    
    /**
     * creates a combo box of strings and returns it
     * @param key key key string to be used to get combobox title from resource bundle
     * @param choices List of string choices to be displayed
     * @param handler event handler to be called when a choice is selected
     * @return created combobox
     */
    protected ComboBox<String> createComboBox(String key, List<String> choices, EventHandler<ActionEvent> handler){
    	ComboBox<String> comboBox = ComboFactory.createBox(myResources.getString(key), choices, handler);
    	container.getChildren().add(comboBox);
    	return comboBox;
    	
    }
    
    /**
     * gets all possible parsing languages
     * @return list of all parsing languages
     */
    protected List<String> getLanguages() {
        return FileGetter.getAllFromDirectory(Defaults.PARSELANG_LOC.getDefault());
    }
    
    /**
     * gets list of all possible colors
     * @return list of all colors
     */
    protected List<String> getColors(){
            return new ArrayList<>(colorMap.getIndexMap().getValues());
    }
    
    protected int getColorIndex(String value){
    	return colorMap.getIndex(value);
    }
    
    

    /**
     * gets Hbox containing all of subbars components
     * @return Hbox containing all of the bars components
     */
    public HBox getContainer(){
        return container;
    }
    
    /**
     * gets current parsing language
     * @return current parsing language
     */
    public ObjectObservable<String> getLanguage() {
    	return language;
    }
    
    /**
     * creates all comboboxes needed for sub bar
     */
    protected abstract void createComboBoxes();
    
    /**
     * creates all buttons needed for sub bar
     */
    protected abstract void createButtons();



}
