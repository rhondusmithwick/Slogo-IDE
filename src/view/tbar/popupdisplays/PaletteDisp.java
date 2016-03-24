/**
 * This entire file is part of my master piece. It is an abstract class that provides a framework for subclasses
 * intended to display the contents of an IndexMap(Object we created, see maps package, basically a map that maps a string
 * to an integer index). As you will see in the other
 * files in this masterpiece, which shows how to use this class, this class makes it very easy to implement a popup
 * that displays the contents of an IndexMap, because all the subclass has to do is implement the abstract method
 * that takes an entry of the IndexMap and creates the graphics it wants displayed for that entry. Using this abstract class
 * you can add a new display for the contents of an index map in 30 to 40 lines of code, instead of the 100 to 150 lines 
 * it would take to write a new display from scratch. Also it prevents a 
 * large amount of duplicated code, since we needed to display the contents of two IndexMaps, 
 * the indexed colors and indexed shapes, which if I hadn't used this abstract class
 * would have taken a very large amount of similar code for each. Thus this class helps prevent code duplication
 * when writing code to display IndexMaps in a popup. This file was one I wrote and
 * heavily refactored during the project. I have refactored it a bit at this point to clean it up a little more, and to 
 * change the layout so it makes more sense. Also before I refactored this, the subclass had to call methods to set up the 
 * hBox and add elements to the hBox for each entry, but this caused some repeated code between the two subclasses, and wasn't
 * particularly intuitive, so I changed this so all the subclass has to do is return a list with the nodes it wants added
 * to the hbox for that particular entry. All the variables and methods are named descriptively, and the methods are
 * all very short and only have one purpose.  It also uses functional programming, which helped to make the code seem much 
 * cleaner and more elegant than having a for loop.  
 */

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
    private ObservableMap<Integer, String> mapToShow;


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
    public void show(ObservableMap<Integer, String> map) {
        this.mapToShow = map;
        super.show();
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
        mapToShow.entrySet().stream().forEach(this::addEntry);
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
     * creates vBox used to hold all the elements of the palette display
     */
    private void setVBox() {
        vBox = new VBox(Size.PALETTE_PADDING.getSize());
        vBox.setStyle(Defaults.BACKGROUND_WHITE.getDefault());
        vBox.prefHeightProperty().bind(scroll.heightProperty());
        vBox.prefWidthProperty().bind(scroll.widthProperty());
    }

    /**
     * Creates a container for and then adds the elements to display an entry to the popup
     * @param e the entry of the IndexMap to display
     */
    private void addEntry(Entry<Integer, String> e){
    	setHBox();
    	List<Node> nodesToAdd = addToPalette(e);
    	hBox.getChildren().addAll(nodesToAdd);
    }
    

    /**
     * creates elements to display for each entry
     *
     * @param e map entry containing an integer key and string value
     */
    protected abstract List<Node> addToPalette(Entry<Integer, String> e);

    /**
     * sets up the hBox used to display one map entry
     */
    private void setHBox() {
        hBox = new HBox(Size.PALETTE_ENT_PADDING.getSize());
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.prefWidthProperty().bind(scroll.widthProperty());
        vBox.getChildren().add(hBox);
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
