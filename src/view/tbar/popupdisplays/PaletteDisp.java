package view.tbar.popupdisplays;

import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.Defaults;
import view.Size;
import view.utilities.PopUp;
import java.util.Collections;
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
    private VBox vBox;
    private final ResourceBundle myResources;
    private final String title;
    private HBox hBox;

    private ObservableMap<Integer, ?> map;


    /**
     * super constructor for palette display subclasses
     *
     * @param title String title of the display
     * @param map   MapObservable that the contents of the display will be pulled from
     */
    protected PaletteDisp(String title) {
        super(Size.PALETTE.getSize(), Size.PALETTE.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        this.title = title;
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());

    }

    /**
     * Shows the palette display for the given map observable object
     *
     * @param map map obsevable to create display with
     */
    public void show(ObservableMap<Integer, ?> map) {
        this.map = map;
        super.show();
    }

    /**
     * create scroll pane used to show the display
     */
    private void createScroll() {

        scroll = new ScrollPane();
        scroll.prefHeightProperty().bind(getSize(true));
        scroll.prefWidthProperty().bind(getSize(false));


    }

    /**
     * creates all components to be shown in display and adds them to the scene
     */
    @Override
    protected void createScene() {
        setStageTitle(myResources.getString(title));
        createScroll();
        setVBox();
        scroll.setContent(vBox);
        addNodes(Collections.singletonList(scroll));
        map.entrySet().stream().forEach(this::addToPalette);
    }


    private void setVBox() {
        vBox = new VBox(Size.PALETTE_PADDING.getSize());
        vBox.setStyle(Defaults.BACKGROUND_WHITE.getDefault());
        vBox.prefHeightProperty().bind(scroll.heightProperty());
        vBox.prefWidthProperty().bind(scroll.widthProperty());
    }


    /**
     * adds a map entry to the display
     *
     * @param e map entry containing an integer key and string value
     */
    protected void addToPalette(Entry<Integer, ?> e){
        setHBox();
    }

    /**
     * sets up the hBox used to display one map entry
     */
    protected void setHBox() {
        hBox = new HBox(Size.PALETTE_ENT_PADDING.getSize());
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.prefWidthProperty().bind(scroll.widthProperty());
        vBox.getChildren().add(hBox);
    }

    /**
     * adds nodes to the HBox used to display an entry
     *
     * @param nList list of nodes to add
     */
    protected void addNodesToHBox(List<Node> nodeList) {
        hBox.getChildren().addAll(nodeList);

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
