package view.utilities;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import view.Size;

public abstract class BottomDisplay {

    protected ScrollPane setScroll(){
        ScrollPane myScrollPane = new ScrollPane();
        myScrollPane.setMinViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setPrefViewportHeight(Size.BOTTOM_HEIGHT.getSize());
        myScrollPane.setMaxHeight(Size.BOTTOM_HEIGHT.getSize());
        HBox.setHgrow(myScrollPane, Priority.ALWAYS);
        return myScrollPane;
    }
    
    
}
