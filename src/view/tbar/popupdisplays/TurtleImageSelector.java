package view.tbar.popupdisplays;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.utilities.ButtonFactory;
import view.utilities.GetCommand;

import java.net.MalformedURLException;

/**
 * This class is responsible for allowing the user to choose an image for a turtle and assigning it to the correct chosen
 * turtles. It extends the abstract TurtlePropertyUpdater class.
 *
 * @author Cali
 */

public class TurtleImageSelector extends TurtlePropertyUpdater {

    private static final String TITLE = "image";
    private String newImage;
    private final ImageMap imageMap;

    /**
     * Creates an new TurtleImageSelector instance
     *
     * @param turtleIDs       ids of all created turtles
     * @param internalCommand ObjectObservable String to pass commands to command entry instance
     * @param parsingLanguage ObjectObservable String with current parsing language
     * @param imageMap        current ImageMap
     */
    public TurtleImageSelector(SimpleStringProperty turtleIDs,
                               ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, IndexMap imageMap) {
        super(turtleIDs, internalCommand, parsingLanguage, TITLE);
        this.imageMap = (ImageMap) imageMap;
    }


    @Override
    protected void createElementsBelowCheckBoxes() {
        Button chooseFile = ButtonFactory.createButton(getStringFromResources(TITLE), e -> openImageChooser());
        addToScene(chooseFile);
    }


    private void openImageChooser() {
        ImageChooser imChoose = new ImageChooser();
        imChoose.show();
        try {
            newImage = imChoose.getChosen();
            int index = newImage.indexOf(Defaults.IMAGE_LOC.getDefault());
            newImage = newImage.substring(index);
        } catch (MalformedURLException e) {
            return;
        }
    }


    @Override
    protected String makeCommand(String turtleIDs) {
        int index = imageMap.getIndex(newImage);
        String askCommand = translateCommand("Ask");
        String shapeCommand = translateCommand("SetShape");
        return askCommand + " [ " + turtleIDs + "] [ " + shapeCommand + " " + Integer.toString(index) + " ]";
    }

}
