package view.tbar;

import view.Defaults;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.IndexMapSaver;
import view.tbar.popupdisplays.WorkSpaceSaver;
import view.utilities.PopUp;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import main.GlobalProperties;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;

/**
 * class represents the bottom sub bar of the tool bar. it is a sub class of the
 * abstract class sub bar.
 * 
 * @author calisnelson and Stephen Kwok
 *
 */
public class BottomBar extends SubBar implements Observer {


	private ObjectObservable<String> backgroundColor;
	private ColorMap colorMap;
	private ImageMap imageMap;
	private ComboBox<String> backgroundColorBox;
	private ComboBox<String> languageBox;

	private SimpleStringProperty image;

	/**
	 * Creates a new bottom bar instance
	 * @param globalProperties 
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
	 */
	public BottomBar( GlobalProperties globalProperties, ObjectObservable<String> internalCommand, 
			 SimpleStringProperty image ) {
		super(globalProperties.getLanguage(), internalCommand, globalProperties.getColorMap());
		this.imageMap =(ImageMap) globalProperties.getImageMap();
		this.colorMap = (ColorMap) globalProperties.getColorMap();
		this.colorMap.getIndexMap().addObserver(this);
		this.image =image;
		
		this.backgroundColor = globalProperties.getBackgroundColor();
		
	}

	/**
	 * creates all comboboxes needed for sub bar
	 */
	@Override
	protected void createComboBoxes() {
		
		backgroundColorBox = createComboBox("bColor", getColors(), e -> setBackground());
		languageBox = createComboBox("selLang", getLanguages(), e -> setLang());

	}

	private void setBackground() {
		String bColor = backgroundColorBox.getSelectionModel().getSelectedItem();
		backgroundColor.set(bColor.toLowerCase());

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
		makeButton("image", e -> chooseTurtIm());
		makeButton("workSaver", e -> saveWorkSpace());
		makeButton("saveColor", e -> saveMap(true));
		makeButton("saveImage", e -> saveMap(false));

	}

	private void saveMap(boolean colors) {
		try {
			PopUp mapSave;
			if (colors) {
				mapSave = new IndexMapSaver(colorMap);
			} else {
				mapSave = new IndexMapSaver(imageMap);
			}
			mapSave.show();
		} catch (Exception e) {
			return;
		}
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

	private void saveWorkSpace() {
		PopUp workspaceSaver = new WorkSpaceSaver(getColors(), getLanguages());
		workspaceSaver.show();
	}
	
	/**
	 * called whenever color map is updated with new option. Removes and
	 * recreates background color box and pen color box so that that they
	 * include the new option.
	 */
	@Override
	public void update(Observable o, Object arg) {
		getContainer().getChildren().remove(backgroundColorBox);
		createComboBoxes();
	}

}
