package View.TBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;
import Maps.ColorMap;
import Observables.ObjectObservable;
import View.Defaults;
import View.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class SubBar implements Observer {
	
	private ResourceBundle myResources, myCommands;
	private HBox container;
	private ObjectObservable<String> language;
	private static final char SPLITTER = '|'; 
	private SimpleStringProperty error;
	private ColorMap colors;

	
	public SubBar(ObjectObservable<String> language, SimpleStringProperty error){
		this.language=language;
		this.error=error;
		myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		initHBox();
		getColorMap();
		setParsingLanguage(language.get());
		createButtons();
		createComboBoxes();
	}
	
	
	protected ComboBox<String> createBox(String label, List<String> choices, EventHandler<ActionEvent> handler) {
		ComboBox<String> comBox = new ComboBox<>();
		comBox.setPromptText(myResources.getString(label));
		choices.forEach(e -> comBox.getItems().add(e));
		comBox.setOnAction(handler);
		HBox.setHgrow(comBox, Priority.ALWAYS);
		container.getChildren().add(comBox);
		return comBox;

	}
	
	protected void setParsingLanguage(String pLang){
		
		myCommands = ResourceBundle.getBundle( pLang);
		
		language.set(pLang);
	}
	
	public void initHBox(){
		container = new HBox(Size.TB_PADDING.getSize());
		container.setAlignment(Pos.CENTER);
		
	}
	
	protected void showError(String key){
		error.set("");
		error.set(myResources.getString(key));
	}
	
	public Button makeButton(String key, EventHandler<ActionEvent> handler) {
		Button newButt = new Button(myResources.getString(key));
		container.getChildren().add(newButt);
		newButt.setOnAction(handler);
		HBox.setHgrow(newButt, Priority.ALWAYS);
		return newButt;
	}
	
	protected String getCommand(String key) {
		String retrievedString = myCommands.getString(key);
		int splitterPos = retrievedString.indexOf(SPLITTER);
		if (splitterPos > 0) {
			return retrievedString.substring(0, splitterPos);
		} else {
			return retrievedString;
		}
	}
	
	private void getColorMap() {
		try {
			this.colors = ColorMap.getInstance();
		} catch (Exception e) {
			error.set("");
			error.set(myResources.getString("colorError"));
		}
		colors.getIndexMap().addObserver(this);
	}

	protected List<String> getLanguages() {
		return (ArrayList<String>) ParseLangs.getInstance().getLangs();
	}
	
	protected List<String> getColors(){
		 return new ArrayList<String>(colors.getIndexMap().getValues());
		
	}

	
	public HBox getContainer(){
		return container;
	}
	
	protected abstract void createComboBoxes();
	protected abstract void createButtons();

	
	
}
