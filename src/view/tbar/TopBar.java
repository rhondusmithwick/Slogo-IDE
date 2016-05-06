// This entire file is part of my masterpiece (Look only at the createButtons() and createTurtlePropertyUpdater() methods 
// on lines 88-113
// Stephen Kwok

// This class holds various buttons that allows the user to perform certain actions such as changing colors, properties, etc. 
// I included it in the masterpiece to highlight another benefit of the TurtlePropertyUpdater class. As seen in the 
// createTurtlePropertyUpdater() method, the abstract class allows for the use of reflection to create an instance 
// of the needed TurtlePropertyUpdater depending on what button is clicked and what property needs to be updated. Then,
// the use of reflection allows us to create multiple TurtlePropertyUpdater classes with the same method, as seen in the
// createButtons() method, so that we don't need a new method just to create each new TurtlePropertyUpdater. This makes
// the class more flexible and maintainable since adding a new updater requires only adding a single line to the 
// createButtons() class. 

package view.tbar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import main.GlobalProperties;
import maps.ColorMap;
import maps.IndexMap;
import model.turtle.Turtle;
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
    private final ObservableMap<Integer, Turtle> turtles;


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
                  SimpleStringProperty error, ObservableMap<Integer, Turtle> turtles) {
        super(globalProperties.getLanguage(), internalCommand, globalProperties.getColorMap(), error);
        this.parsingLanguage = globalProperties.getLanguage();
        this.internalCommand = internalCommand;
        this.turtleIDs = turtleIDs;
        this.colorMap = globalProperties.getColorMap();
        this.imageMap = globalProperties.getImageMap();
        this.selectedTurtle = selectedTurtle;
        this.turtles = turtles;

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
        makeButton("pColor", e -> setPenColor());

        makeButton("chPropTurtle", e -> changePropertiesTurtle());
        makeButton("image", e -> setTurtleImages());
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
    
    private void setTurtleImages() {
    	TurtleImageSelector imageSelector = new TurtleImageSelector(turtles, internalCommand, parsingLanguage, imageMap);
    	imageSelector.show();
    }

}