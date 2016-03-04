package view.tbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import view.Defaults;
import view.Size;
import view.utilities.ButtonFactory;
import view.utilities.ComboFactory;
import view.utilities.FileGetter;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import maps.ColorMap;
import observables.ObjectObservable;

public abstract class SubBar implements Observer {

    private ResourceBundle myResources, myCommands;
    private HBox container;
    private ObjectObservable<String> language, intCommand;
    private SimpleStringProperty error;
    private ColorMap colors;


    public SubBar(ObjectObservable<String> language, SimpleStringProperty error, ObjectObservable<String> intCommand, 
    		ColorMap cMap){
        this.language=language;
        this.intCommand=intCommand;
        this.error=error;
        this.colors = cMap;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        initHBox();
        setParsingLanguage(language.get());
        createButtons();
        createComboBoxes();
    }


    protected void setParsingLanguage(String pLang){

        myCommands = ResourceBundle.getBundle( pLang);

        language.set(pLang);
    }
    
    protected void passCommand(String command){
        intCommand.set(command);
    }
    
    public void initHBox(){
        container = new HBox(Size.TB_PADDING.getSize());
        container.setAlignment(Pos.CENTER);

    }

    public void showError(String key){
        error.set("");
        error.set(myResources.getString(key));
    }

    public Button makeButton(String key, EventHandler<ActionEvent> handler) {
        Button newButt = ButtonFactory.createButton(myResources.getString(key), handler);
        container.getChildren().add(newButt);
        HBox.setHgrow(newButt, Priority.ALWAYS);
        return newButt;
    }

    protected String getCommand(String key) {
        String retrievedString = myCommands.getString(key);
        int splitterPos = retrievedString.indexOf(Defaults.COMM_SPLITER.getDefault());
        if (splitterPos > 0) {
            return retrievedString.substring(0, splitterPos);
        } else {
            return retrievedString;
        }
    }
    
    protected ComboBox<String> createComboBox(String key, List<String> choices, EventHandler<ActionEvent> handler){
    	ComboBox<String> cBox = ComboFactory.createBox(myResources.getString(key), choices, handler);
    	HBox.setHgrow(cBox, Priority.ALWAYS);
    	container.getChildren().add(cBox);
    	return cBox;
    	
    }

    protected List<String> getLanguages() {
        return FileGetter.getAllFromDirectory(Defaults.PARSELANG_LOC.getDefault());
    }

    protected List<String> getColors(){
            return new ArrayList<>(colors.getIndexMap().getValues());
    }


    public HBox getContainer(){
        return container;
    }
    
    public ObjectObservable<String> getLanguage() {
    	return language;
    }

    protected abstract void createComboBoxes();
    protected abstract void createButtons();



}
