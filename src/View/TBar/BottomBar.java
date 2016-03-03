package View.TBar;

import java.util.Observable;
import Observables.ObjectObservable;
import View.Defaults;
import View.utilities.ComboFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;

public class BottomBar extends SubBar {
	
	private ObjectObservable<String> intCommand;
	private ComboBox<String> langBox;
	
	public BottomBar(ObjectObservable<String> language, SimpleStringProperty error, 
			ObjectObservable<String> intCommand) {
		super(language, error);
		this.intCommand=intCommand;
		
	}



	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	protected void createComboBoxes() {
		langBox = createComboBox("selLang", getLanguages(), e -> setLang() );
		
	}

	private void setLang() {
		String pLanguage = Defaults.PARSELANG_LOC.getDefault() + langBox.getSelectionModel().getSelectedItem();
		setParsingLanguage(pLanguage);
		
	}



	@Override
	protected void createButtons() {
		makeButton("penUp", e -> setPen("PenUp"));
		makeButton("penDown", e -> setPen("PenDown"));
		makeButton("workSaver", e->saveWorkSpace());
		makeButton("setPenSize", e -> setPen("setPenSize"));

	}



	private void saveWorkSpace() {
		WorkSpaceSaver wSaver = new WorkSpaceSaver(getColors(), getLanguages());
		wSaver.showSaver();
	}



	private void setPen(String key) {
		intCommand.set(getCommand(key));
	}
}
