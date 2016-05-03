package view.tbar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import main.GlobalProperties;
import maps.ColorMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.TurtleImageDisplay;
import view.tbar.popupdisplays.TurtleImageSelector;
import view.tbar.popupdisplays.TurtlePropSelect;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.tbar.popupdisplays.pen.PenColorUpdater;
import view.utilities.PopUp;

import java.lang.reflect.Constructor;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 *
 * @author calisnelson and Stephen Kwok
 */

public class TopBar extends SubBar {

    private final SimpleStringProperty turtleIDs;
    private final ObjectObservable<Integer> selectedTurtle;
    private final ObjectObservable<String> internalCommand, parsingLanguage;
    private final IndexMap colorMap, imageMap;
    private final PopUp colorDisplay;
    private final PopUp imageDisplay;
    private final PopUp turtleImageDisplay;
    private ObservableMap<Integer, SimpleStringProperty> turtleImages;


    /**
     * creates a new top bar instance.
     *
     * @param intCommand string observable for passing commands to command entry
     *                   instance
     * @param cMap       Index map object for mapping colors to integer indexes
     * @param iMap       Index map object for mapping images to integer indexes
     * @param bgColor    string observable to set turtle area background color
     * @param image      simplestring property to set turtles image
     * @param penColor   simplestringproperty to set turtles pen color
     */
    public TopBar(GlobalProperties globalProperties, SimpleStringProperty turtleIDs,
                  ObjectObservable<String> internalCommand, ObjectObservable<Integer> selectedTurtle,
                  SimpleStringProperty error) {
        super(globalProperties.getLanguage(), internalCommand, globalProperties.getColorMap(), error);
        this.parsingLanguage = globalProperties.getLanguage();
        this.internalCommand = internalCommand;
        this.turtleIDs = turtleIDs;
        this.colorMap = globalProperties.getColorMap();
        this.imageMap = globalProperties.getImageMap();
        this.selectedTurtle = selectedTurtle;
        this.turtleImages = globalProperties.getTurtleImages();

        colorDisplay = new ColorDisplay("colorTitle");
        imageDisplay = new ImageDisplay("imageTitle");
        turtleImageDisplay = new TurtleImageDisplay("turtleImageTitle",error);

    }

    /**
     * creates all comboboxes needed for sub bar
     */
    @Override
    protected void createComboBoxes() {
    }

    /**
     * creates all buttons needed for sub bar
     */
    @Override
    protected void createButtons() {
        makeButton("colorDisp", e -> ((PaletteDisp) colorDisplay).show(colorMap.getIndexMap()));
        makeButton("imageDisp", e -> ((PaletteDisp) imageDisplay).show(imageMap.getIndexMap()));
        makeButton("selectTurtleButtonTitle", e -> createTurtlePropertyUpdater(Defaults.TURTLE_SELECTOR.getDefault()));
        makeButton("setPenSize", e -> createTurtlePropertyUpdater(Defaults.PEN_SIZE_UPDATER.getDefault()));
        makeButton("penUp", e -> createTurtlePropertyUpdater(Defaults.PEN_UP.getDefault()));
        makeButton("penDown", e -> createTurtlePropertyUpdater(Defaults.PEN_DOWN.getDefault()));
        makeButton("pColor", e -> setPenColor());
        makeButton("chPropTurtle", e -> changePropertiesTurtle());
        makeButton("image", e -> setImage());
        makeButton("showTurtleImages", e->((PaletteDisp) turtleImageDisplay).show(turtleImages));
    }

    private void setImage() {
        TurtlePropertyUpdater turtleImageSelector = new TurtleImageSelector(turtleIDs, internalCommand, parsingLanguage, imageMap);
        turtleImageSelector.show();
    }

    private void createTurtlePropertyUpdater(String className) {
        try {
            Class<?> classToCreate = Class.forName(className);
            Constructor<?> constructor = classToCreate.getConstructor(SimpleStringProperty.class,
                    ObjectObservable.class, ObjectObservable.class);
            TurtlePropertyUpdater turtlePropertyUpdater = (TurtlePropertyUpdater) constructor.newInstance(turtleIDs, internalCommand,
                    parsingLanguage);
            turtlePropertyUpdater.show();
        } catch (Exception e) {
            e.printStackTrace();
            setError("createTurtlePropertyUpdateError");
        }
    }

    private void changePropertiesTurtle() {
        PopUp turtPropSelect = new TurtlePropSelect(selectedTurtle, turtleIDs);
        turtPropSelect.show();
    }

    private void setPenColor() {
        TurtlePropertyUpdater penColorUpdater = new PenColorUpdater(turtleIDs, internalCommand, parsingLanguage, (ColorMap) colorMap, getColors());
        penColorUpdater.show();
    }

}