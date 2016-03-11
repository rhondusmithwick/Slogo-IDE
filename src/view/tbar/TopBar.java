package view.tbar;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.Size;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.PenDownUpdater;
import view.tbar.popupdisplays.PenSizeUpdater;
import view.tbar.popupdisplays.PenUpUpdater;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.tbar.popupdisplays.TurtleSelector;
import view.utilities.PopUp;
import main.Slogo;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */

public class TopBar extends SubBar{

	private final int width = Size.TURTLE_UPDATE_POPUP_WIDTH.getSize();
	private final int height = Size.TURTLE_UPDATE_POPUP_HEIGHT.getSize();
	private final String popUpColor = Defaults.BACKGROUND_COLOR.getDefault();
	private final SimpleStringProperty turtleIDs;
	private PopUp colorDisplay, imageDisplay;
	private ComboBox<String> languageBox;
	private ObjectObservable<String> internalCommand, parsingLanguage;
	private TurtlePropertyUpdater turtleSelector, penSizeUpdater, penUpUpdater, penDownUpdater;
	private IndexMap colorMap, imageMap;
	private HelpScreen helpScreen;
	private Slogo slogo;

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
	public TopBar(ObjectObservable<String> language,  
			SimpleStringProperty turtleIDs, ObjectObservable<String> internalCommand, ColorMap colorMap, ImageMap imageMap, Slogo slogo) {
		super(language, internalCommand, colorMap);
		this.parsingLanguage = language;
		this.internalCommand = internalCommand;
		this.turtleIDs = turtleIDs;
		colorDisplay = new ColorDisplay("colorTitle");
		imageDisplay = new ImageDisplay("imageTitle");
		helpScreen = new HelpScreen();
		this.slogo= slogo;

	}

	/**
	 * creates all comboboxes needed for sub bar
	 */
	@Override
	protected void createComboBoxes() {
		languageBox = createComboBox("selLang", getLanguages(), e -> setLang());

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
		
        makeButton("colorDisp", e -> ((PaletteDisp) colorDisplay).show(colorMap.getIndexMap()));
        makeButton("imageDisp", e -> ((PaletteDisp) imageDisplay).show(imageMap.getIndexMap()));
		makeButton("selectTurtleButtonTitle", e -> selectTurtle());
		makeButton("setPenSize", e -> setPenSize());
		makeButton("penUp", e -> setPenUp());
		makeButton("penDown", e -> setPenDown());
		makeButton("help", e -> helpScreen.show());
		makeButton("newWS", e -> slogo.newView());
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




}