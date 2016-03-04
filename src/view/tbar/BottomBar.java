package view.tbar;


import java.util.Observable;

import view.Defaults;
import view.envdisplay.EnvUpdate;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;

import observables.ObjectObservable;

public class BottomBar extends SubBar {


    private static final String SAVE_ERROR = "saveError";
    private PopUp hScreen;
    private SimpleStringProperty error;
    ObjectObservable<String> intCommand;
    private ComboBox<String> langBox;
    private EnvUpdate penSizeUpdater;

    public BottomBar(ObjectObservable<String> language, SimpleStringProperty error, 
                     ObjectObservable<String> intCommand) {
        super(language, error, intCommand);
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
        makeButton("saveColor", e-> saveColors());
        makeButton("saveImage", e->saveImages());
        makeButton("help", e -> hScreen.show());

    }


    private void saveColors () {
        try {
            PopUp mSave = new IndexMapSaver(ColorMap.getInstance(), error);
            mSave.show();
        }
        catch (Exception e) {
            showError(SAVE_ERROR);
        }
    }



    private void saveImages () {
        try {
            IndexMapSaver mSave = new IndexMapSaver(ImageMap.getInstance(), error);
            mSave.show();
        }
        catch (Exception e) {
            showError(SAVE_ERROR);
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
