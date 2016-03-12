package view.utilities;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import view.Size;

/**
 * Abstract class acts as a framework for displays on the bottom part of the border pane
 * @author Cali
 *
 */

public abstract class BottomDisplay {

    /**
     * creates scrollpanes for the displays in the bottom part of the border pane
     * @return created scrollpane
     */
    protected ScrollPane setScroll(){
        ScrollPane myScrollPane = new ScrollPane();
        myScrollPane.setMinViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setPrefViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setMaxHeight(Size.BOTTOM_HEIGHT.getSize());
        HBox.setHgrow(myScrollPane, Priority.ALWAYS);
        return myScrollPane;
    }
    
    
}
