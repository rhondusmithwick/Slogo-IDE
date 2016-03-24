/**
 * This entire file is part of my masterpiece. This is an example of a subclass of the PaletteDisp class, which is the other
 * class of my master piece. This object creates a popup that displays each index in the palette, with a preview
 * of the image that this index pertains to. This class shows how using the palette disp abstract
 * class to provide a framework for classes that want to display each entry of an IndexMap, makes it very easy to add a new
 * display to the project. As you can see all the subclass has to do is implement the method to take in a map entry and
 * create the elements it wants to show for every entry and then return those elements. The superclass takes care of everything
 * else. The only refactoring I did of this class for the masterpiece, was to update the addToPalette method to work with
 * the refactorings I made in the palette disp class.
 */

package view.tbar.popupdisplays;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Size;

import java.util.Arrays;
import java.util.List;
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
    protected List<Node> createDisplayFromEntry(Entry<Integer, String> entry) {
        Label index = createLabel("index", entry.getKey().toString());
        Image image = new Image(entry.getValue());
        ImageView display = new ImageView();
        display.setFitHeight(Size.PALETTE_DIM.getSize());
        display.setFitWidth(Size.PALETTE_DIM.getSize());
        display.setImage(image);
        return Arrays.asList(index, display);


    }


}
