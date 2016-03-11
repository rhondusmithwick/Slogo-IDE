package view.tbar;

import javafx.beans.property.SimpleStringProperty;
import maps.ColorMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.HelpScreen;
import view.tbar.popupdisplays.ImageDisplay;
import view.tbar.popupdisplays.PaletteDisp;
import view.tbar.popupdisplays.TurtlePropSelect;
import view.tbar.popupdisplays.TurtlePropertyUpdater;
import view.tbar.popupdisplays.TurtleSelector;
import view.tbar.popupdisplays.pen.PenColorUpdater;
import view.tbar.popupdisplays.pen.PenDownUpdater;
import view.tbar.popupdisplays.pen.PenSizeUpdater;
import view.tbar.popupdisplays.pen.PenUpUpdater;
import view.utilities.PopUp;
import main.GlobalProperties;
import main.Slogo;

/**
 * class represents the top sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */

public class TopBar extends SubBar{

	private SimpleStringProperty turtleIDs;
	private PopUp colorDisplay, imageDisplay, turtPropSelect;
	private ObjectObservable<Integer> selectedTurtle;

	private ObjectObservable<String> internalCommand, parsingLanguage;
	private TurtlePropertyUpdater turtleSelector, penSizeUpdater, penUpUpdater, penDownUpdater, penColorUpdater;
	private IndexMap colorMap, imageMap;
	private HelpScreen helpScreen;
	private Slogo slogo;

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
	public TopBar(GlobalProperties globalProperties, SimpleStringProperty turtleIDs, ObjectObservable<String> internalCommand, Slogo slogo, ObjectObservable<Integer> selectedTurtle) {
		super(globalProperties.getLanguage(), internalCommand, globalProperties.getColorMap());
		this.parsingLanguage = globalProperties.getLanguage();
		this.internalCommand = internalCommand;
		this.turtleIDs = turtleIDs;
		this.colorMap = globalProperties.getColorMap();
		this.imageMap = globalProperties.getImageMap();
		this.selectedTurtle=selectedTurtle;
		colorDisplay = new ColorDisplay("colorTitle");
		imageDisplay = new ImageDisplay("imageTitle");
		helpScreen = new HelpScreen();
		this.slogo= slogo;

	}

	/**
	 * creates all comboboxes needed for sub bar
	 */
	@Override
	protected void createComboBoxes() {}

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
		makeButton("pColor", e->setPenColor());
		makeButton("help", e -> helpScreen.show());
		makeButton("newWS", e -> slogo.newView());
		makeButton("chPropTurtle", e-> changePropertiesTurtle());
	}

	private void setPenColor() {
		penColorUpdater = new PenColorUpdater(turtleIDs, internalCommand, parsingLanguage, (ColorMap) colorMap, getColors());
		penColorUpdater.show();
	}

	private void changePropertiesTurtle() {
		turtPropSelect = new TurtlePropSelect(selectedTurtle, turtleIDs);
		turtPropSelect.show();
	}

	private void selectTurtle() {
		turtleSelector = new TurtleSelector( turtleIDs, internalCommand, parsingLanguage);
		turtleSelector.show();
	}

	private void setPenSize() {
		penSizeUpdater = new PenSizeUpdater(turtleIDs, internalCommand, parsingLanguage);
		penSizeUpdater.show();
	}

	private void setPenUp() {
		penUpUpdater = new PenUpUpdater(turtleIDs, internalCommand, parsingLanguage);
		penUpUpdater.show();
	}

	private void setPenDown() {
		penDownUpdater = new PenDownUpdater( turtleIDs, internalCommand, parsingLanguage);
		penDownUpdater.show();
	}




}