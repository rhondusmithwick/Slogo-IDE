package View.TBar;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import Maps.ColorMap;
import Observables.ObjectObservable;
import View.Defaults;
import View.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ToolBar implements Observer {

	private final ObjectObservable<String> language, bgColor, intCommand;
	private SimpleStringProperty image, penColor, error;
	private VBox buttonContainer;
	private HBox topContainer;
	private HBox bottomContainer;
	private HelpScreen hScreen;
	private ResourceBundle myResources, myCommands;
	private String dispLang, bColor, pLanguage, pColor;
	private ArrayList<String> parseLangs, possColors;
	private ComboBox<String> langBox, bColorBox, pColorBox;
	private ColorMap colors;
	private PaletteDisp cDisp, iDisp;
	private static final char SPLITTER = '|'; // put this in defaults?

	public ToolBar(ObjectObservable<String> language, SimpleStringProperty error, ObjectObservable<String> bgColor,
			SimpleStringProperty image, SimpleStringProperty penColor, ObjectObservable<String> intCommand) {
		this.intCommand = intCommand;
		this.error = error;
		this.image = image;
		this.penColor = penColor;
		this.language = language;
		this.bgColor = bgColor;
		this.dispLang = Defaults.DISPLAY_LANG.getDefault();
		myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() + dispLang);
		myCommands = ResourceBundle.getBundle(Defaults.RESOURCE_LOCATION.getDefault() + language.get());
		cDisp = new ColorDisplay("colorTitle");
		iDisp = new ImageDisplay("imageTitle");
		hScreen = HelpScreen.getInstance();

		setToolBar();
	}

	private void setToolBar() {
		getColors();
		initButtonContainer();
		setHBoxes();
		createButtons();
		getLanguages();
		createComboBoxes();
	}

	private void initButtonContainer() {
		buttonContainer = new VBox();
		bottomContainer = new HBox();
		bottomContainer.setAlignment(Pos.CENTER);
	}

	private void setHBoxes() {
		topContainer = new HBox();
		topContainer.setAlignment(Pos.CENTER);
		topContainer.setSpacing(Size.TB_PADDING.getSize());
		buttonContainer.getChildren().add(topContainer);
		buttonContainer.getChildren().add(bottomContainer);
	}


	public VBox getToolBarMembers() {
		return buttonContainer;
	}

	private void createComboBoxes() {
		this.possColors = new ArrayList<String>(colors.getIndexMap().getValues());
		langBox = createBox("selLang", parseLangs, e -> setLang());
		bColorBox = createBox("bColor", possColors, e -> setBackground());
		pColorBox = createBox("pColor", possColors, e -> setPColor());
	}

	private void setPColor() {
		pColor = pColorBox.getSelectionModel().getSelectedItem();
		penColor.set(pColor.toLowerCase());

	}

	private void setBackground() {
		bColor = bColorBox.getSelectionModel().getSelectedItem();
		bgColor.set(bColor.toLowerCase());

	}

	private void setLang() {
		pLanguage = Defaults.PARSELANG_LOC.getDefault() + langBox.getSelectionModel().getSelectedItem();
		language.set(pLanguage);
		myCommands =ResourceBundle.getBundle(language.get());
	}

	private ComboBox<String> createBox(String label, ArrayList<String> choices, EventHandler<ActionEvent> handler) {
		ComboBox<String> comBox = new ComboBox<>();
		comBox.setPromptText(myResources.getString(label));
		choices.forEach(e -> comBox.getItems().add(e));
		comBox.setOnAction(handler);
		HBox.setHgrow(comBox, Priority.ALWAYS);
		topContainer.getChildren().add(comBox);
		return comBox;

	}

	private void getColors() {
		try {
			this.colors = ColorMap.getInstance();
		} catch (Exception e) {
			error.set("");
			error.set(myResources.getString("colorError"));
		}
		colors.getIndexMap().addObserver(this);
	}

	private void getLanguages() {
		this.parseLangs = (ArrayList<String>) ParseLangs.getInstance().getLangs();
	}

	private void makeButton(String label, EventHandler<ActionEvent> handler, HBox container) {
		Button newButt = new Button();
		newButt.setText(label);
		container.getChildren().add(newButt);
		newButt.setOnAction(handler);
		HBox.setHgrow(newButt, Priority.ALWAYS);
	}

	private void createButtons() {
		makeButton(myResources.getString("help"), e -> hScreen.showHelpScreen(myResources.getString("helpFile")),
				topContainer);
		makeButton(myResources.getString("image"), e -> chooseTurtIm(), topContainer);
		makeButton(myResources.getString("colorDisp"), e -> showColorPalette(), topContainer);
		makeButton(myResources.getString("imageDisp"), e -> showImagePalette(), topContainer);
		makeButton(myResources.getString("penUp"), e -> setPenUp(), bottomContainer);
		makeButton(myResources.getString("penDown"), e -> setPenDown(), bottomContainer);
	}

	private void showImagePalette() {
		try {
			iDisp.createDisp();
		} catch (Exception e) {
			e.printStackTrace();
			error.set("");
			error.set("imagePalError");
		}
	}

	private void showColorPalette() {
		try {
			cDisp.createDisp();
		} catch (Exception e) {
			e.printStackTrace();
			error.set("");
			error.set(myResources.getString("colorPalError"));
		}
	}

	private void chooseTurtIm() {
		try {
			String newImage = ImageChooser.getInstance().chooseTurtIm(myResources.getString("getFile"));
			if (newImage != null) {
				image.set(newImage);
			}
		} catch (MalformedURLException e) {
			error.set("");
			error.set(myResources.getString("picError"));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		updateColorBoxes();

	}

	private void updateColorBoxes() {
		topContainer.getChildren().removeAll(bColorBox, pColorBox, langBox);
		createComboBoxes();
	}

	private String getCommand(String key) {
		String retrievedString = myCommands.getString(key);
		int splitterPos = retrievedString.indexOf(SPLITTER);
		if (splitterPos > 0) {
			return retrievedString.substring(0, splitterPos);
		} else {
			return retrievedString;
		}
	}

	private void setPenUp() {
    	String command = getCommand("PenUp");
    	intCommand.set(command);
    }

	private void setPenDown() {
		String command = getCommand("PenDown");
		intCommand.set(command);
	}

}
