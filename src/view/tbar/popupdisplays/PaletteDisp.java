package view.tbar.popupdisplays;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import observables.MapObservable;
import view.Defaults;
import view.Size;
import view.utilities.PopUp;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 * This abstract class acts as a base for subclasses that display palettes to the user such
 * as the color or image palettes.
 *
 * @author Cali
 */
public abstract class PaletteDisp extends PopUp {

    private ScrollPane scroll;
    private VBox vbox;
    private ResourceBundle myResources;
    private String title;
    private HBox h;

    private MapObservable<Integer, String> map;


    /**
     * super constructor for palette display subclasses
     *
     * @param title String title of the display
     * @param map   MapObservable that the contents of the display will be pulled from
     */
    public PaletteDisp(String title, MapObservable<Integer, String> map) {
        super(Size.PALETTE.getSize(), Size.PALETTE.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        this.title = title;
        this.map = map;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());

    }

    /**
     * create scroll pane used to show the display
     */
    protected void createScroll() {

        scroll = new ScrollPane();
        scroll.prefHeightProperty().bind(getSize(true));
        scroll.prefWidthProperty().bind(getSize(false));


    }

    /**
     * creates all components to be shown in display and adds them to the scene
     */
    @Override
    protected void createScene() {
        setStageTitle(title);
        createScroll();
        setVBox();
        scroll.setContent(vbox);
        addNodes(Arrays.asList(scroll));
        map.getEntrySet().stream().forEach(e -> addToPalette(e));
    }


    private void setVBox() {
        vbox = new VBox(Size.PALETTE_PADDING.getSize());
        vbox.setStyle(Defaults.BACKGROUND_WHITE.getDefault());
        vbox.prefHeightProperty().bind(scroll.heightProperty());
        vbox.prefWidthProperty().bind(scroll.widthProperty());
    }


    /**
     * adds a map entry to the display
     *
     * @param e map entry containing an integer key and string value
     */
    protected abstract void addToPalette(Entry<Integer, String> e);

    /**
     * sets up the hBox used to display one map entry
     */
    protected void setHBox() {
        h = new HBox(Size.PALETTE_ENT_PADDING.getSize());
        h.setAlignment(Pos.CENTER_LEFT);
        h.prefWidthProperty().bind(scroll.widthProperty());
        vbox.getChildren().add(h);
    }

    /**
     * adds nodes to the HBox used to display an entry
     *
     * @param nList list of nodes to add
     */
    protected void addNodesToHBox(List<Node> nList) {
        h.getChildren().addAll(nList);

    }

    /**
     * creates and returns a label from given key
     *
     * @param key key to get string from current display resource bundle
     * @param end string to be added to end of title pulled from resource bundle
     * @return created label
     */
    protected Label createLabel(String key, String end) {
        return new Label(myResources.getString(key) + end);

    }


}
