package view.utilities;

import java.util.Observable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import view.tbar.popupdisplays.IEditableElement;

/**
 *
 */

public class PreviewUnitWithEditable extends Observable {

	private static final Double IMAGE_FIT_SIZE = 75.0;
	private static final Double LABEL_PADDING = 10.0;
	private static final Double HBOX_SPACING = 5.0;
	private final HBox myHBox;
	private final Label myLabel;
	private IEditableElement myEditable;

	/**
	 * Creates a preview unit for an IEditableElement
	 * @param editable: the IEditableElement the preview unit will be displaying
	 */
	public PreviewUnitWithEditable(IEditableElement editable) {
		myEditable = editable;
		myLabel = new Label();
		myLabel.setPadding(new Insets(LABEL_PADDING));
		myLabel.setOnMouseClicked(e -> notifyObservers(myEditable));
		myLabel.wrapTextProperty().setValue(true);
		myHBox = new HBox(myLabel);
		myHBox.setAlignment(Pos.CENTER_LEFT);
		myHBox.setSpacing(HBOX_SPACING);
	}
	
	/**
	 * Updates the preview unit's text and image to account for any changes in the
	 * Actor or Level's name and image
	 */
	public void update() {
		myLabel.setText(myEditable.getName());
		ImageView imageView = new ImageView(myEditable.getImageView().getImage());
		imageView.setFitHeight(IMAGE_FIT_SIZE);
		imageView.setPreserveRatio(true);
		myLabel.setGraphic(imageView);
	}

	/**
	 * 
	 * @return the preview unit's instance of IEditableGameElement
	 */
	public IEditableElement getEditable() {
		return myEditable;
	}

	/**
	 * 
	 * @return the HBox containing all nodes in the preview unit
	 */
	public HBox getHBox() {
		return myHBox;
	}

}