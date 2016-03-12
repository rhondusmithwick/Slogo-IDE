package view.tbar;

import java.lang.reflect.Constructor;
import javafx.beans.property.SimpleStringProperty;
import main.GlobalProperties;
import maps.ColorMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.TurtleImageSelector;
import view.tbar.popupdisplays.TurtlePropSelect;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.tbar.popupdisplays.pen.PenColorUpdater;
import view.utilities.PopUp;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */

public class TopBar extends SubBar {

    private SimpleStringProperty turtleIDs;
    private PopUp colorDisplay, imageDisplay, turtPropSelect;
    private ObjectObservable<Integer> selectedTurtle;

    private ObjectObservable<String> internalCommand, parsingLanguage;
    private TurtlePropertyUpdater turtlePropertyUpdater, penColorUpdater, turtleImageSelector;
    private IndexMap colorMap, imageMap;


    /**
     * creates a new top bar instance.
     * 
     * @param intCommand
     *            string observable for passing commands to command entry
     *            instance
     * @param cMap
     *            Index map object for mapping colors to integer indexes
     * @param iMap
     *            Index map object for mapping images to integer indexes
     * @param bgColor
     *            string observable to set turtle area background color
     * @param image
     *            simplestring property to set turtles image
     * @param penColor
     *            simplestringproperty to set turtles pen color
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
        
        colorDisplay = new ColorDisplay("colorTitle");
        imageDisplay = new ImageDisplay("imageTitle");
       
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
        makeButton("pColor", e->setPenColor());
        
        makeButton("chPropTurtle", e -> changePropertiesTurtle());
        makeButton("image", e-> setImage());
    }

    private void setImage () {
        turtleImageSelector = new TurtleImageSelector(turtleIDs, internalCommand, parsingLanguage, imageMap);
        turtleImageSelector.show();
    }

    private void createTurtlePropertyUpdater(String className) {
        try {
            Class<?> classToCreate = Class.forName(className);
            Constructor<?> constructor = classToCreate.getConstructor(SimpleStringProperty.class,
                                                                      ObjectObservable.class, ObjectObservable.class);
            turtlePropertyUpdater = (TurtlePropertyUpdater) constructor.newInstance(turtleIDs, internalCommand,
                                                                                    parsingLanguage);
            turtlePropertyUpdater.show();
        } catch (Exception e) {
            e.printStackTrace();
            setError("createTurtlePropertyUpdateError");
        }
    }

    private void changePropertiesTurtle() {
        turtPropSelect = new TurtlePropSelect(selectedTurtle, turtleIDs);
        turtPropSelect.show();
    }

    private void setPenColor() {
        penColorUpdater = new PenColorUpdater(turtleIDs, internalCommand, parsingLanguage, (ColorMap) colorMap, getColors());
        penColorUpdater.show();
    }

}