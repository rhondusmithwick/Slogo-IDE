package view.tbar;

import view.Defaults;
import view.envdisplay.EnvActor;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.IndexMapSaver;
import view.tbar.popupdisplays.PenSizeUpdater;
import view.tbar.popupdisplays.WorkSpaceSaver;
import view.utilities.PopUp;
import javafx.scene.control.ComboBox;
import main.Slogo;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;

/**
 * class represents the bottom sub bar of the tool bar. it is a sub class of the abstract class sub bar.
 * @author calisnelson and Stephen Kwok
 *
 */
public class BottomBar extends SubBar {

    private PopUp helpScreen;

    ObjectObservable<String> internalCommand;
    private ComboBox<String> languageBox;
    private EnvActor penSizeUpdater;
    private ColorMap colorMap;
    private ImageMap imageMap;

	private Slogo multView;

    /**
     * Creates a new bottom bar instance
     * @param language language string observable for setting and storing parsing language
     * @param intCommand string observable for passing commands to command entry instance
     * @param cMap Index map object for mapping colors to integer indexes
     * @param iMap Index map object for mapping images to integer indexes
     */
    public BottomBar(ObjectObservable<String> language, 
                     ObjectObservable<String> internalCommand, ColorMap colorMap, ImageMap imageMap, Slogo multView) {
        super(language, internalCommand, colorMap);
        this.imageMap = imageMap;
        this.colorMap = colorMap;
        this.internalCommand=internalCommand;
        this.multView = multView;
        helpScreen = new HelpScreen();

    }



    /**
     * creates all comboboxes needed for sub bar
     */
    @Override
    protected void createComboBoxes() {
        languageBox = createComboBox("selLang", getLanguages(), e -> setLang() );

    }

    private void setLang() {
        String parsingLanguage = Defaults.PARSELANG_LOC.getDefault() + languageBox.getSelectionModel().getSelectedItem();
        setParsingLanguage(parsingLanguage);

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
        makeButton("help", e -> helpScreen.show());
        makeButton("newWS", e-> multView.newView());
        

    }


    private void saveMap (boolean colors) {
        try {
            PopUp mapSave;
            if(colors){
                mapSave = new IndexMapSaver(colorMap);

            }else{
                mapSave = new IndexMapSaver(imageMap);
            }
            mapSave.show();
        }
        catch (Exception e) {
            return;
        }
    }



    private void saveWorkSpace() {
        PopUp workspaceSaver = new WorkSpaceSaver(getColors(), getLanguages());
        workspaceSaver.show();
    }

    private void setPenSize() {
        penSizeUpdater = new PenSizeUpdater(getLanguage(), internalCommand);
        penSizeUpdater.show();
    }

    private void setPen(String key) {
        passCommand(getCommand(key));
    }

}
