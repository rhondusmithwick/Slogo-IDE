package view.tbar.popupdisplays;

import java.util.ResourceBundle;
import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Defaults;
/**
 * this class displays each turtles image along with its turtle id. it extends image display as both rely on a map of integers
 * to objects that contain filepaths
 * @author Cali
 *
 */
public class TurtleImageDisplay extends ImageDisplay {

    private SimpleStringProperty error;
    /**
     * create new turtle image display object
     * @param title title of display
     * @param error simple string property used to display error
     */
    public TurtleImageDisplay (String title, SimpleStringProperty error) {
        super(title);
        this.error=error;
        
    }

    /**
     * adds entries from turtle images map to the display
     * @param e
     */
    @Override
    protected void addToPalette (Entry<Integer, ?> e) {
        super.setHBox();
        Label title = createLabel("id", e.getKey().toString());
        ImageView display = makeEntry(title, ((SimpleStringProperty) e.getValue()).get());
        display.setOnMouseClicked(clicked->changeImage(e, display));

        
    }

    /**
     * when a display for a turtle is clicked on opens a file chooser and allows user to set new image file
     * @param e entry corresponding to selected display
     * @param display ImageView display of turtles image
     */
    private void changeImage (Entry<Integer, ?> e, ImageView display) {
        try {
            ImageChooser imageChooser = new ImageChooser();
            imageChooser.show();
            String file = imageChooser.getChosen();
            ((SimpleStringProperty) e.getValue()).set(file);
            display.setImage(new Image(file));
        }
        catch (Exception e1) {
            error.set("");
            error.set(ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault()).getString("picError"));
        }
        
    }



}
