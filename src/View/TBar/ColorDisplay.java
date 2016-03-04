package View.TBar;

import java.util.Arrays;
import java.util.Map.Entry;
import Maps.ColorMap;
import Observables.MapObservable;
import View.Size;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorDisplay extends PaletteDisp {
    
    
    private MapObservable<Integer, String> cMap;
    public ColorDisplay (String title) {
        super(title);
        
    }

    @Override
    public void createDisp() throws Exception{
        super.createDisp();
        cMap = ColorMap.getInstance().getIndexMap();
        cMap.getEntrySet().stream().forEach(e-> addToPalette(e));
        showDisplay();
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
