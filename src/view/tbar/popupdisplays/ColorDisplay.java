/**
 * This entire file is part of my masterpiece. This is an example of a subclass of the PaletteDisp class, which is the other
 * class of my master piece. This object creates a popup that displays each index in the palette, with the name of the
 * color it corresponds to, and a rectangle displaying the actual color. This class shows how using the palette disp abstract
 * class to provide a framework for classes that want to display each entry of an IndexMap, makes it very easy to add a new
 * display to the project. As you can see all the subclass has to do is implement the method to take in a map entry and
 * create the elements it wants to show for every entry and then return those elements. The superclass takes care of everything
 * else. The only refactoring I did of this class for the masterpiece, was to update the addToPalette method to work with
 * the refactorings I made in the palette disp class.
 */

package view.tbar.popupdisplays;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Size;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;


/**
 * sub class of palette display responsible for displaying color palette
 *
 * @author Cali
 */
public class ColorDisplay extends PaletteDisp {

    /**
     * creates a new color display instance
     *
     * @param title String title of the display
     * @param map   MapObservable that the contents of the display will be pulled from
     */
    public ColorDisplay(String title) {
        super(title);

    }

    /**
     * adds a map entry for one color integer pair to the color palette display
     *
     * @param e map entry containing an integer key and string color value
     */
    @Override
    protected List<Node> createDisplayFromEntry(Entry<Integer, String> entry) {
        Label index = createLabel("index", entry.getKey().toString());
        Label colorName = createLabel("colorName", entry.getValue());
        Rectangle rectangle = new Rectangle(Size.PALETTE_DIM.getSize(), Size.PALETTE_DIM.getSize(), Color.web(entry.getValue()));
        return Arrays.asList(index, colorName, rectangle);
    }

}
