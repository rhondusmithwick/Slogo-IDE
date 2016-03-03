package View.TBar;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import View.Defaults;
import View.Size;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class PaletteDisp {
    
    private Stage s;
    private Group root;
    private ScrollPane scroll;
    private VBox vbox;
    private Scene scene;
    private ResourceBundle myResources;
    private String title;
    private HBox h;

    
    
    
    public PaletteDisp(String title){
        this.title=title;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        setStage();
        root = new Group();
        scene = new Scene(root, Size.PALETTE.getSize(),Size.PALETTE.getSize());
        setScrollPane();
    }
    
    private void setStage () {
        s = new Stage();
        s.setTitle(myResources.getString(title));
        
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
        

    }
    
    protected void showDisplay(){
        s.setScene(scene);
        s.show();
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
        Label label = new Label(myResources.getString(key)+end);
        return label;
    }


}
