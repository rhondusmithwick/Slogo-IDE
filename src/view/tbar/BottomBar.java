package view.tbar;


import java.util.Observable;

import view.Defaults;
import view.envdisplay.EnvUpdate;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.IndexMapSaver;
import view.tbar.popupdisplays.PenSizeUpdater;
import view.tbar.popupdisplays.WorkSpaceSaver;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;

import observables.ObjectObservable;

public class BottomBar extends SubBar {

    private PopUp hScreen;
    private SimpleStringProperty error;
    ObjectObservable<String> intCommand;
    private ComboBox<String> langBox;
    private EnvUpdate penSizeUpdater;
	private ColorMap cMap;
	private ImageMap iMap;

    public BottomBar(ObjectObservable<String> language, SimpleStringProperty error, 
                     ObjectObservable<String> intCommand, ColorMap cMap, ImageMap iMap) {
        super(language, intCommand, cMap);
        this.iMap = iMap;
        this.cMap = cMap;
        this.error = error;
        this.intCommand=intCommand;
        hScreen = new HelpScreen();

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
        makeButton("workSaver", e->saveWorkSpace());
        makeButton("penUp", e -> setPen("PenUp"));
        makeButton("penDown", e -> setPen("PenDown"));
        makeButton("setPenSize", e -> setPenSize());
        makeButton("saveColor", e-> saveMap(true));
        makeButton("saveImage", e->saveMap(false));
        makeButton("help", e -> hScreen.show());

    }


    private void saveMap (boolean colors) {
        try {
        	PopUp mSave;
        	if(colors){
        		mSave = new IndexMapSaver(cMap, error);
        		
        	}else{
        		mSave = new IndexMapSaver(iMap, error);
        	}
        	mSave.show();
        }
        catch (Exception e) {
            return;
        }
    }



    private void saveWorkSpace() {
        PopUp wSaver = new WorkSpaceSaver(getColors(), getLanguages(),error);
        wSaver.show();
    }

    private void setPenSize() {
        penSizeUpdater = new PenSizeUpdater(getLanguage(), intCommand);
        penSizeUpdater.show();
    }

    private void setPen(String key) {
        passCommand(getCommand(key));
    }
}
