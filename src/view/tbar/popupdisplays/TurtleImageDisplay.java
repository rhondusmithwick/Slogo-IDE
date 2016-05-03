package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Defaults;
public class TurtleImageDisplay extends ImageDisplay {

    private SimpleStringProperty error;
    public TurtleImageDisplay (String title, SimpleStringProperty error) {
        super(title);
        this.error=error;
        
    }

    @Override
    protected void addToPalette (Entry<Integer, ?> e) {
        super.setHBox();
        Label title = createLabel("id", e.getKey().toString());
        Image image = new Image( ((SimpleStringProperty) e.getValue()).get());
        ImageView display = super.setImage(image);
        display.setOnMouseClicked(clicked->changeImage(e, display));
        addNodesToHBox(Arrays.asList(title, display));
        
    }

    private void changeImage (Entry<Integer, ?> e, ImageView display) {
        try {
            ImageChooser imageChooser = new ImageChooser();
            imageChooser.show();
            String file = imageChooser.getChosen();
            ((SimpleStringProperty) e.getValue()).set(file);
            display.setImage(new Image(file));
        }
        catch (Exception e1) {
            e1.printStackTrace();
            error.set("");
            error.set(ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault()).getString("picError"));
        }
        
    }



}
