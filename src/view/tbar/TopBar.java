package view.tbar;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.PenDownUpdater;
import view.tbar.popupdisplays.PenSizeUpdater;
import view.tbar.popupdisplays.PenUpUpdater;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
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

	private final int width = Size.TURTLE_UPDATE_POPUP_WIDTH.getSize();
	private final int height = Size.TURTLE_UPDATE_POPUP_HEIGHT.getSize();
	private final String popUpColor = Defaults.BACKGROUND_COLOR.getDefault();
	private final SimpleStringProperty image, turtleIDs;
	private PopUp colorDisplay, imageDisplay;
	private ComboBox<String> backgroundColorBox;
	private ComboBox<String> penColorBox;
	private ObjectObservable<String> backgroundColor, internalCommand, parsingLanguage;
	private TurtlePropertyUpdater turtleSelector, penSizeUpdater, penUpUpdater, penDownUpdater;
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
		this.parsingLanguage = language;
		this.internalCommand = internalCommand;
		this.turtleIDs = turtleIDs;
		this.image = image;
		this.backgroundColor = backgroundColor;
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
		String pColor = penColorBox.getSelectionModel().getSelectedItem();
		String command = getCommand("SetPenColor");
		int index = getColorIndex(pColor);
		passCommand(command + " " + index);

	}

	private void setBackground() {
		String bColor = backgroundColorBox.getSelectionModel().getSelectedItem();
		backgroundColor.set(bColor.toLowerCase());

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
		makeButton("setPenSize", e -> setPenSize());
		makeButton("penUp", e -> setPenUp());
		makeButton("penDown", e -> setPenDown());
	}

	private void selectTurtle() {
		turtleSelector = new TurtleSelector(width, height, popUpColor, turtleIDs, internalCommand, parsingLanguage);
		turtleSelector.show();
	}

	private void setPenSize() {
		penSizeUpdater = new PenSizeUpdater(width, height, popUpColor, turtleIDs, internalCommand, parsingLanguage);
		penSizeUpdater.show();
	}

	private void setPenUp() {
		penUpUpdater = new PenUpUpdater(width, height, popUpColor, turtleIDs, internalCommand, parsingLanguage);
		penUpUpdater.show();
	}

	private void setPenDown() {
		penDownUpdater = new PenDownUpdater(width, height, popUpColor, turtleIDs, internalCommand, parsingLanguage);
		penDownUpdater.show();
	}

	private void chooseTurtIm() {

		ImageChooser imChoose = new ImageChooser();
		imChoose.show();
		String newImage;
		try {
			newImage = imChoose.getChosen();
			if (newImage != null) {
				image.set(newImage);
			}
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * called whenever color map is updated with new option. Removes and
	 * recreates background color box and pen color box so that that they
	 * include the new option.
	 */
	@Override
	public void update(Observable o, Object arg) {
		getContainer().getChildren().removeAll(backgroundColorBox, penColorBox);
		createComboBoxes();
	}
}