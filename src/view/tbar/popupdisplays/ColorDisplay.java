package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.Map.Entry;

import view.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.MapObservable;

public class ColorDisplay extends PaletteDisp {
    
    
    private MapObservable<Integer, String> cMap;
    public ColorDisplay (String title, SimpleStringProperty error, MapObservable<Integer, String> cMap) {
        super(title, error);
        this.cMap=cMap;
        
    }

    @Override
    public void createScene() {
        super.createScene();
		cMap.getEntrySet().stream().forEach(e-> addToPalette(e));
        
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
