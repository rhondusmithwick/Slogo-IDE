package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.Map.Entry;

import view.Size;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.MapObservable;

/**
 * sub class of palette display responsible for displaying color palette
 * @author Cali
 *
 */
public class ColorDisplay extends PaletteDisp {
    
    /**
     * creates a new color display instance
     * @param title String title of the display
     * @param map MapObservable that the contents of the display will be pulled from
     */
    public ColorDisplay (String title, MapObservable<Integer, String> colorMap) {
        super(title, colorMap);
        
    }

    /**
     * adds a map entry for one color integer pair to the color palette display
     * @param e map entry containing an integer key and string color value
     */
    @Override
    protected void addToPalette (Entry<Integer, String> e) {
        setHBox();
        Label title = createLabel("index", e.getKey().toString() );
        Label name = createLabel("colorName",  e.getValue());
        Rectangle rectangle = new Rectangle(Size.PALETTE_DIM.getSize(), Size.PALETTE_DIM.getSize(), Color.web(e.getValue()));
        addNodesToHBox(Arrays.asList(title,name,rectangle));
    }

}
