package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import observables.ObjectObservable;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.utilities.ComboFactory;

import java.util.List;

/**
 * This class is responsible for updating the pen color for the turtles a user selects.
 * It extends the abstract TurtlePropertyUpdater class.
 *
 * @author calinelson
 */

public class PenColorUpdater extends TurtlePropertyUpdater {

    private final ColorMap colorMap;
    private final List<String> colors;
    private ComboBox<String> colorBox;

    /**
     * creates a new penColor Updater instance
     *
     * @param turtleIDs       simplestring property list of created turtle ids
     * @param internalCommand object observable string to pass commands to command entry instance
     * @param parsingLanguage object observable of current parsing language
     * @param colorMap        ColorMap instance
     * @param colors          ArrayList of strings of all currently defined colors
     */
    public PenColorUpdater(SimpleStringProperty turtleIDs,
                           ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, ColorMap colorMap, List<String> colors) {
        super(turtleIDs, internalCommand, parsingLanguage, "pColor");
        this.colorMap = colorMap;
        this.colors = colors;

    }


    @Override
    protected void createElementsBelowCheckBoxes() {
        colorBox = ComboFactory.createBox(getStringFromResources("pColor"), colors, null);
        addToScene(colorBox);


    }

    @Override
    protected String makeCommand(String turtleIDs) {
        String askCommand = translateCommand("Ask");
        String penColorCommand = translateCommand("SetPenColor");
        String pColor = colorBox.getSelectionModel().getSelectedItem();
        int index = colorMap.getIndex(pColor);
        return askCommand + " [ " + turtleIDs + "] [ " + penColorCommand + " " + Integer.toString(index) + " ]";
    }

}
