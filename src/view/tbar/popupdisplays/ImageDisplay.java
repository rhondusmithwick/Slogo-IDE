package view.tbar.popupdisplays;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Size;

import java.util.Arrays;
import java.util.Map.Entry;


/**
 * sub class of palette display responsible for displaying image palette
 *
 * @author Cali
 */
public class ImageDisplay extends PaletteDisp {


    /**
     * creates a new image display instance
     *
     * @param title String title of the display
     * @param map   MapObservable that the contents of the display will be pulled from
     */
    public ImageDisplay(String title) {
        super(title);


    }

    /**
     * adds a map entry for one image integer pair to the color palette display
     *
     * @param e map entry containing an integer key and a string for an image path
     */
    @Override
    protected void addToPalette(Entry<Integer, ?> e) {
        super.addToPalette(e);
        Label title = createLabel("index", e.getKey().toString());
        makeEntry(title, (String) e.getValue());



    }
    
    protected ImageView makeEntry(Label title, String filePath){
        Image image = new Image(filePath);
        ImageView display = new ImageView();
        display.setFitHeight(Size.PALETTE_DIM.getSize());
        display.setFitWidth(Size.PALETTE_DIM.getSize());
        display.setImage(image);
        addNodesToHBox(Arrays.asList(title, display));
        return display;
    }

}
