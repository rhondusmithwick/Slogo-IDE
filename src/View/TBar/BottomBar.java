package View.TBar;


import java.util.Observable;
import Maps.ColorMap;
import Maps.ImageMap;
import Observables.ObjectObservable;
import View.Defaults;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;

public class BottomBar extends SubBar {
    
    private HelpScreen hScreen;
    private SimpleStringProperty error;
    private ComboBox<String> langBox;

    public BottomBar(ObjectObservable<String> language, SimpleStringProperty error, 
                     ObjectObservable<String> intCommand) {
        super(language, error, intCommand);
        this.error = error;
        hScreen = HelpScreen.getInstance();

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
        makeButton("setPenSize", e -> setPen("setPenSize"));
        makeButton("saveColor", e-> saveColors());
        makeButton("saveImage", e->saveImages());
        makeButton("help", e -> hScreen.showHelpScreen( ));

    }



    
    
    private void saveColors () {
        try {
            IndexMapSaver mSave = new IndexMapSaver(ColorMap.getInstance(), error);
            mSave.showSaver();
        }
        catch (Exception e) {
            showError("saveError");
        }
    }

    private void saveImages () {
        try {
            IndexMapSaver mSave = new IndexMapSaver(ImageMap.getInstance(), error);
            mSave.showSaver();
        }
        catch (Exception e) {
            showError("saveError");
        }
    }
    


    private void saveWorkSpace() {
        WorkSpaceSaver wSaver = new WorkSpaceSaver(getColors(), getLanguages(),error);
        wSaver.showSaver();
    }



    private void setPen(String key) {
        passCommand(getCommand(key));
    }
}
