package view.tbar;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class PaletteDisp extends PopUp {
    
    private ScrollPane scroll;
    private VBox vbox;
    private ResourceBundle myResources;
    private String title;
    private HBox h;
    private SimpleStringProperty error;

    
    
    
    public PaletteDisp(String title, SimpleStringProperty error){
    	super(Size.PALETTE.getSize(), Size.PALETTE.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        this.title=title;
        this.error=error;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());

    }
    
    @Override
    protected void createContainer () {
    	setStageTitle(title);
        scroll = new ScrollPane();
        scroll.prefHeightProperty().bind(getSize(true));
        scroll.prefWidthProperty().bind(getSize(false));
        addContainer(scroll);
        
    }
    
    @Override
    protected void createScene(){
    	setVBox();
        scroll.setContent(vbox);
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
    
    protected void showError(String key){
    	error.set("");
    	error.set(myResources.getString(key));
    }


}
