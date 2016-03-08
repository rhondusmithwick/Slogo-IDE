package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.Map.Entry;

import view.Size;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import observables.MapObservable;

/**
 * sub class of palette display responsible for displaying image palette
 * @author Cali
 *
 */
public class ImageDisplay extends PaletteDisp{


    /**
     * creates a new image display instance
     * @param title String title of the display
     * @param map MapObservable that the contents of the display will be pulled from
     */
    public ImageDisplay (String title,  MapObservable<Integer, String> iMap) {
        super(title,  iMap);
   
       
        
    }

    /**
     * adds a map entry for one image integer pair to the color palette display
     * @param e map entry containing an integer key and a string for an image path
     */
    @Override
    protected void addToPalette (Entry<Integer, String> e) {
        setHBox();
        Label title = createLabel("index", e.getKey().toString() );
        Image image = new Image(e.getValue());
        ImageView disp = new ImageView();
        disp.setFitHeight(Size.PALETTE_DIM.getSize());
        disp.setFitWidth(Size.PALETTE_DIM.getSize());
        disp.setImage(image);
        addNodesToHBox(Arrays.asList(title, disp));
        
        
    }
    



    

}
