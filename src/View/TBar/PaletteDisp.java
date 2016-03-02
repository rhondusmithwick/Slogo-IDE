package View.TBar;

import java.util.Map.Entry;
import java.util.ResourceBundle;
import Maps.ColorMap;
import Observables.MapObservable;
import View.Defaults;
import View.Size;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PaletteDisp {
    
    private Stage s;
    private Group root;
    private ScrollPane scroll;
    private VBox vbox;
    private Scene scene;
    private ResourceBundle myResources;
    private String language;
    private MapObservable<Integer, String> cMap;

    
    
    
    public PaletteDisp(){
        this.language=Defaults.DISPLAY_LANG.getDefault();
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault()+language);
        setStage();
        root = new Group();
        scene = new Scene(root, Size.PALETTE.getSize(),Size.PALETTE.getSize());
        setScrollPane();
    }
    
    private void setStage () {
        s = new Stage();
        s.setTitle(myResources.getString("paletteTitle"));
        
    }

    private void setScrollPane () {
        scroll = new ScrollPane();
        
        scroll.prefHeightProperty().bind(scene.heightProperty());
        scroll.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().add(scroll);
        
    }
    
    private void setVBox(){
        vbox = new VBox(Size.PALETTE_PADDING.getSize());
        vbox.setStyle(Defaults.BACKGROUND_WHITE.getDefault());
        vbox.prefHeightProperty().bind(scroll.heightProperty());
        vbox.prefWidthProperty().bind(scroll.widthProperty());
    }

    public void createDisp() throws Exception{
        setVBox();
        scroll.setContent(vbox);
        cMap = ColorMap.getInstance().getIndexMap();
        cMap.getEntrySet().stream().forEach(e-> addToPallete(e));
        s.setScene(scene);
        s.show();
        
    }

    private void addToPallete (Entry<Integer, String> e) {
        Label title = new Label(myResources.getString("index") +e.getKey().toString() );
        Label name = new Label(myResources.getString("colorName") + e.getValue());
        HBox h = new HBox(Size.PALETTE_ENT_PADDING.getSize());
        h.setAlignment(Pos.CENTER_LEFT);
        h.prefWidthProperty().bind(scroll.widthProperty());
        Rectangle rect = new Rectangle(Size.PALETTE_RECT.getSize(), Size.PALETTE_RECT.getSize(), Color.web(e.getValue()));
        h.getChildren().addAll(title, name,rect);
        vbox.getChildren().add(h);

    }

}
