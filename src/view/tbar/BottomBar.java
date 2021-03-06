package view.tbar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.control.ComboBox;
import main.GlobalProperties;
import main.Slogo;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;
import view.Defaults;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.IndexMapSaver;
import view.tbar.popupdisplays.WorkSpaceSaver;
import view.utilities.PopUp;

/**
 * class represents the bottom sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 *
 * @author calisnelson and Stephen Kwok
 */
public class BottomBar extends SubBar {


    private final ObjectObservable<String> backgroundColor;
    private final ColorMap colorMap;
    private final ImageMap imageMap;
    private ComboBox<String> backgroundColorBox;
    private ComboBox<String> languageBox;
    private final HelpScreen helpScreen;
    private final Slogo slogo;

    /**
     * Creates a new bottom bar instance
     *
     * @param globalProperties
     * @param language         language string observable for setting and storing parsing
     *                         language
     * @param intCommand       string observable for passing commands to command entry
     *                         instance
     * @param cMap             Index map object for mapping colors to integer indexes
     * @param iMap             Index map object for mapping images to integer indexes
     */
    public BottomBar(GlobalProperties globalProperties, ObjectObservable<String> internalCommand,
                     SimpleStringProperty error, Slogo slogo) {
        super(globalProperties.getLanguage(), internalCommand, globalProperties.getColorMap(), error);
        this.imageMap = (ImageMap) globalProperties.getImageMap();
        this.colorMap = (ColorMap) globalProperties.getColorMap();
        this.colorMap.getIndexMap().addListener((MapChangeListener<Integer, String>) change -> updateColors());
        helpScreen = new HelpScreen();
        this.slogo = slogo;
        this.backgroundColor = globalProperties.getBackgroundColor();

    }

    private void updateColors() {
        getContainer().getChildren().remove(backgroundColorBox);
        createColorBox();
    }

    /**
     * creates all comboboxes needed for sub bar
     */
    @Override
    protected void createComboBoxes() {
        createColorBox();
        languageBox = createComboBox("selLang", getLanguages(), e -> setLang());

    }

    private void createColorBox() {
        backgroundColorBox = createComboBox("bColor", getColors(), e -> setBackground());
    }

    private void setBackground() {
        String bColor = backgroundColorBox.getSelectionModel().getSelectedItem();
        backgroundColor.set(bColor.toLowerCase());

    }

    private void setLang() {
        String parsingLanguage = Defaults.PARSELANG_LOC.getDefault()
                + languageBox.getSelectionModel().getSelectedItem();
        setParsingLanguage(parsingLanguage);

    }


    /**
     * creates all buttons needed for sub bar
     */
    @Override
    protected void createButtons() {
        makeButton("workSaver", e -> saveWorkSpace());
        makeButton("saveColor", e -> saveMap(true));
        makeButton("saveImage", e -> saveMap(false));
        makeButton("help", e -> helpScreen.show());
        makeButton("newWS", e -> slogo.newView());

    }

    private void saveMap(boolean colors) {
        try {
            PopUp mapSave;
            if (colors) {
                mapSave = new IndexMapSaver(colorMap);
            } else {
                mapSave = new IndexMapSaver(imageMap);
            }
            mapSave.show();
        } catch (Exception e) {
            return;
        }
    }


    private void saveWorkSpace() {
        PopUp workspaceSaver = new WorkSpaceSaver(getColors(), getLanguages());
        workspaceSaver.show();
    }


}
