package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.Map.Entry;

import view.Size;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.MapObservable;

public class ColorDisplay extends PaletteDisp {
    
    
    public ColorDisplay (String title, MapObservable<Integer, String> cMap) {
        super(title, cMap);
        
    }

    @Override
    protected void addToPalette (Entry<Integer, String> e) {
        setHBox();
        Label title = createLabel("index", e.getKey().toString() );
        Label name = createLabel("colorName",  e.getValue());
        Rectangle rect = new Rectangle(Size.PALETTE_DIM.getSize(), Size.PALETTE_DIM.getSize(), Color.web(e.getValue()));
        addNodesToHBox(Arrays.asList(title,name,rect));
    }

}
