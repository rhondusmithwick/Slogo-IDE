package view.tbar;


import java.util.Observable;

import view.Defaults;
import view.envdisplay.EnvActor;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.IndexMapSaver;
import view.tbar.popupdisplays.PenSizeUpdater;
import view.tbar.popupdisplays.WorkSpaceSaver;
import view.utilities.PopUp;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;

import observables.ObjectObservable;

/**
 * class represents the bottom sub bar of the tool bar. it is a sub class of the abstract class sub bar.
 * @author calisnelson and Stephen Kwok
 *
 */
public class BottomBar extends SubBar {

    private PopUp hScreen;

    ObjectObservable<String> intCommand;
    private ComboBox<String> langBox;
    private EnvActor penSizeUpdater;
    private ColorMap cMap;
    private ImageMap iMap;

    /**
     * Creates a new bottom bar instance
     * @param language language string observable for setting and storing parsing language
     * @param intCommand string observable for passing commands to command entry instance
     * @param cMap Index map object for mapping colors to integer indexes
     * @param iMap Index map object for mapping images to integer indexes
     */
    public BottomBar(ObjectObservable<String> language, 
                     ObjectObservable<String> intCommand, ColorMap cMap, ImageMap iMap) {
        super(language, intCommand, cMap);
        this.iMap = iMap;
        this.cMap = cMap;
        this.intCommand=intCommand;
        hScreen = new HelpScreen();

    }


    /**
     * Called on update to any observable. Not used for this class.
     */
    @Override
    public void update(Observable o, Object arg){}

    /**
     * creates all comboboxes needed for sub bar
     */
    @Override
    protected void createComboBoxes() {
        langBox = createComboBox("selLang", getLanguages(), e -> setLang() );

    }

    private void setLang() {
        String pLanguage = Defaults.PARSELANG_LOC.getDefault() + langBox.getSelectionModel().getSelectedItem();
        setParsingLanguage(pLanguage);

    }



    /**
     * creates all buttons needed for sub bar
     */
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
                mSave = new IndexMapSaver(cMap);

            }else{
                mSave = new IndexMapSaver(iMap);
            }
            mSave.show();
        }
        catch (Exception e) {
            return;
        }
    }



    private void saveWorkSpace() {
        PopUp wSaver = new WorkSpaceSaver(getColors(), getLanguages());
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
