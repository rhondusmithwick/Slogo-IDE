package view.tbar;

import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.TurtleSelector;
import view.utilities.PopUp;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */
public class TopBar extends SubBar implements Observer{

    private SimpleStringProperty image, turtleIDs;
    private PopUp colorDisplay, imageDisplay;
    private ComboBox<String> backgroundColorBox;
    private ComboBox<String> penColorBox;
    private ObjectObservable<String> backgroundColor, internalCommand;
    private TurtleSelector turtleSelector;
    private IndexMap colorMap, imageMap;

    /**
     * creates a new top bar instance.
     * 
     * @param language
     *            language string observable for setting and storing parsing
     *            language
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
    public TopBar(ObjectObservable<String> language, ObjectObservable<String> backgroundColor, SimpleStringProperty image,
                  SimpleStringProperty turtleIDs, ObjectObservable<String> internalCommand, ColorMap colorMap, ImageMap imageMap) {
        super(language, internalCommand, colorMap);
        this.internalCommand = internalCommand;
        this.turtleIDs = turtleIDs;
        this.image = image;
        this.backgroundColor = backgroundColor;
        this.colorMap = colorMap;
        this.imageMap=imageMap;
        colorDisplay = new ColorDisplay("colorTitle");
        imageDisplay = new ImageDisplay("imageTitle");

    }

    /**
     * creates all comboboxes needed for sub bar
     */
    @Override
    protected void createComboBoxes() {
        backgroundColorBox = createComboBox("bColor", getColors(), e -> setBackground());
        penColorBox = createComboBox("pColor", getColors(), e -> setPColor());

    }

    private void setPColor() {
        String selectedPenColor = penColorBox.getSelectionModel().getSelectedItem();
        String command = getCommand("SetPenColor");
        int index = getColorIndex(selectedPenColor);
        passCommand(command + " " + index);

    }

    private void setBackground() {
        String selectedBackgroundColor = backgroundColorBox.getSelectionModel().getSelectedItem();
        backgroundColor.set(selectedBackgroundColor.toLowerCase());

    }

    /**
     * creates all buttons needed for sub bar
     */
    @Override
    protected void createButtons() {
        makeButton("image", e -> chooseTurtIm());
        makeButton("colorDisp", e -> ((PaletteDisp) colorDisplay).show(colorMap.getIndexMap()));
        makeButton("imageDisp", e -> ((PaletteDisp) imageDisplay).show(imageMap.getIndexMap()));
        makeButton("selectTurtleButtonTitle", e -> selectTurtle());
    }


    private void selectTurtle() {
        turtleSelector = new TurtleSelector(turtleIDs, internalCommand);
        turtleSelector.show();
    }

    private void chooseTurtIm() {

        ImageChooser imageChoose = new ImageChooser();
        imageChoose.show();
        String newImage;
        try {
            newImage = imageChoose.getChosen();
            if (newImage != null) {
                image.set(newImage);
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("hi");
        
    }



}
