package view.tbar.popupdisplays;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.PopUp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import observables.MapObservable;

public abstract class PaletteDisp extends PopUp {
    
    private ScrollPane scroll;
    private VBox vbox;
    private ResourceBundle myResources;
    private String title;
    private HBox h;

	private MapObservable<Integer, String> map;

    
    
    
    public PaletteDisp(String title, MapObservable<Integer,String> map){
    	super(Size.PALETTE.getSize(), Size.PALETTE.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        this.title=title;
        this.map = map;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());

    }
    
    protected void createScroll() {
    	
        scroll = new ScrollPane();
        scroll.prefHeightProperty().bind(getSize(true));
        scroll.prefWidthProperty().bind(getSize(false));
        
        
    }
    
    @Override
    protected void createScene(){
    	setStageTitle(title);
    	createScroll();
    	setVBox();
        scroll.setContent(vbox);
        addNodes(Arrays.asList(scroll));
        map.getEntrySet().stream().forEach(e->addToPalette(e));
    }
    


    
    private void setVBox(){
        vbox = new VBox(Size.PALETTE_PADDING.getSize());
        vbox.setStyle(Defaults.BACKGROUND_WHITE.getDefault());
        vbox.prefHeightProperty().bind(scroll.heightProperty());
        vbox.prefWidthProperty().bind(scroll.widthProperty());
    }




    protected abstract void addToPalette (Entry<Integer, String> e);
    
    protected void setHBox(){
        h = new HBox(Size.PALETTE_ENT_PADDING.getSize());
        h.setAlignment(Pos.CENTER_LEFT);
        h.prefWidthProperty().bind(scroll.widthProperty());
        vbox.getChildren().add(h);
    }
    
    protected void addNodesToHBox(List<Node> nList){
        h.getChildren().addAll(nList);

    }
    
    protected void addImagesToHBox(List<ImageView> nList){
        h.getChildren().addAll(nList);
    }

    
    protected Label createLabel(String key, String end){
        return new Label(myResources.getString(key)+end);
        
    }
    


}
