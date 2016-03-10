package view.tbar;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.ImageDisplay;
import view.utilities.PopUp;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */
public class TopBar extends SubBar {

	private SimpleStringProperty image;
	private PopUp cDisp, iDisp;
	private ComboBox<String> bColorBox;
	private ComboBox<String> pColorBox;
	private ObjectObservable<String> bgColor;
	private TurtleSelector turtleSelector;

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
	public TopBar(ObjectObservable<String> language, ObjectObservable<String> bgColor, SimpleStringProperty image,
			ObjectObservable<String> intCommand, ColorMap cMap, ImageMap iMap) {
		super(language, intCommand, cMap);
		this.image = image;
		this.bgColor = bgColor;
		cDisp = new ColorDisplay("colorTitle", cMap.getIndexMap());
		iDisp = new ImageDisplay("imageTitle", iMap.getIndexMap());

	}

	/**
	 * creates all comboboxes needed for sub bar
	 */
	@Override
	protected void createComboBoxes() {
		bColorBox = createComboBox("bColor", getColors(), e -> setBackground());
		pColorBox = createComboBox("pColor", getColors(), e -> setPColor());

	}

	private void setPColor() {
		String pColor = pColorBox.getSelectionModel().getSelectedItem();
		String command = getCommand("SetPenColor");
		int index = getColorIndex(pColor);
		passCommand(command + " " + index);

	}

	private void setBackground() {
		String bColor = bColorBox.getSelectionModel().getSelectedItem();
		bgColor.set(bColor.toLowerCase());

	}

	/**
	 * creates all buttons needed for sub bar
	 */
	@Override
	protected void createButtons() {
		makeButton("image", e -> chooseTurtIm());
		makeButton("colorDisp", e -> showColorPalette());
		makeButton("imageDisp", e -> showImagePalette());
		makeButton("selectTurtleButtonTitle", e -> selectTurtle());
	}

	private void showImagePalette() {
		iDisp.show();
	}

	private void showColorPalette() {
		cDisp.show();
	}

	private void selectTurtle() {
		int width = Size.TURT_SELECT_WIDTH.getSize();
		int height = Size.TURT_SELECT_HEIGHT.getSize();
		String backgroundColor = Defaults.BACKGROUND_COLOR.getDefault();
		turtleSelector = new TurtleSelector(width, height, backgroundColor);
		turtleSelector.show();
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
		getContainer().getChildren().removeAll(bColorBox, pColorBox);
		createComboBoxes();
	}

}
