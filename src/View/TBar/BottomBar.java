package View.TBar;

import java.util.Observable;
import Observables.ObjectObservable;
import View.Defaults;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;

public class BottomBar extends SubBar {
	
	private ObjectObservable<String> intCommand;
	private ComboBox<String> langBox;
	private PenSizeUpdater penSizeUpdater;
	
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
		makeButton("setPenSize", e -> setPenSize());
		makeButton("workSaver", e->saveWorkSpace());
	}



	private void saveWorkSpace() {
		WorkSpaceSaver wSaver = new WorkSpaceSaver(getColors(), getLanguages());
		wSaver.showSaver();
	}

	private void setPenSize() {
		penSizeUpdater = new PenSizeUpdater(getLanguage(), intCommand);
	}

	private void setPen(String key) {
		intCommand.set(getCommand(key));
	}
}
