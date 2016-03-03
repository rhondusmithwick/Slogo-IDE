package View.TBar;

import java.net.MalformedURLException;
import java.util.Observable;
import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;


public class TopBar extends SubBar {
	private SimpleStringProperty image, penColor;
	private PaletteDisp cDisp, iDisp;
	private HelpScreen hScreen;
	private ComboBox<String> bColorBox;
	private ComboBox<String> pColorBox;
	private ObjectObservable<String> bgColor;
	

	public TopBar(ObjectObservable<String> language, ObjectObservable<String> bgColor,
			SimpleStringProperty error, SimpleStringProperty image, SimpleStringProperty penColor) {
		super(language, error);
		this.image=image;
		this.bgColor = bgColor;
		this.penColor=penColor;
		cDisp = new ColorDisplay("colorTitle");
		iDisp = new ImageDisplay("imageTitle");
		hScreen = HelpScreen.getInstance();
		
	}

	@Override
	protected void createComboBoxes() {
		bColorBox = createBox("bColor", getColors(), e -> setBackground());
		pColorBox = createBox("pColor", getColors(), e -> setPColor());
		
	}

	private void setPColor() {
		String pColor = pColorBox.getSelectionModel().getSelectedItem();
		penColor.set(pColor.toLowerCase());
	}

	private void setBackground() {
		String bColor = bColorBox.getSelectionModel().getSelectedItem();
		bgColor.set(bColor.toLowerCase());
		
	}

	@Override
	protected void createButtons() {
		makeButton("help", e -> hScreen.showHelpScreen( ));
		makeButton("image", e -> chooseTurtIm());
		makeButton("colorDisp", e -> showColorPalette());
		makeButton("imageDisp", e -> showImagePalette());
		
		
	}

	private void showImagePalette() {
		try {
			iDisp.createDisp();
		} catch (Exception e) {
			showError("imagePalError");
		}
	}

	private void showColorPalette() {
		try {
			cDisp.createDisp();
		} catch (Exception e) {
			e.printStackTrace();
			showError("colorPalError");
		}
	}

	private void chooseTurtIm() {
		try {
			String newImage = ImageChooser.getInstance().chooseTurtIm();
			if (newImage != null) {
				image.set(newImage);
			}
		} catch (MalformedURLException e) {
			showError("picError");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		getContainer().getChildren().removeAll(bColorBox, pColorBox);
		createComboBoxes();
		
	}

}
