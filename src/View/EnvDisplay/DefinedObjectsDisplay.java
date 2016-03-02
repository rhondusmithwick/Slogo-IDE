package View.EnvDisplay;

import java.util.ResourceBundle;
import View.Defaults;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * This class implements EnviromentDisplayInterface and serves as the
 * abstract base class for all GUI elements that display any user-defined
 * objects such as methods or variables
 *
 * @author Stephen
 */

public abstract class DefinedObjectsDisplay {
    private static final String ENGLISH = "English";
    private static final int size = 400;
    private SimpleStringProperty definedObjects;
    private String displayLanguage;
    private ResourceBundle myResources;
    private ScrollPane myScrollPane;
    private VBox vBox;
    private String[] definedObjectsArray;

    private String displayTitle;

    public DefinedObjectsDisplay(SimpleStringProperty definedObjects) {
        this.definedObjects = definedObjects;
        this.displayLanguage = ENGLISH;
        this.myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault() + displayLanguage);
        setScrollPane();
    }

    private void setScrollPane () {
        myScrollPane = new ScrollPane();
        myScrollPane.setMinViewportWidth(size);
        myScrollPane.setPrefViewportWidth(size);
        myScrollPane.setMaxWidth(size);
        VBox.setVgrow(myScrollPane, Priority.SOMETIMES);
    }

    protected abstract void updateDefinedObject(Label label);

    public Node getEnvDisplay() {
        return myScrollPane;
    }


    public void updateEnvNode() {
        createCurrVDisp();
    }

    private void createCurrVDisp() {
        vBox = new VBox();
        vBox.prefWidthProperty().bind(myScrollPane.widthProperty());
        setTitle();
        String definedObjectsString = definedObjects.get();
        if (definedObjectsString != null) {
            definedObjectsArray = definedObjectsString.split(",");
            populateVBox();
        }
        myScrollPane.setContent(vBox);
    }

    private void setTitle() {
        Label title = new Label(displayTitle); 
        title.setAlignment(Pos.TOP_CENTER);
        title.prefWidthProperty().bind(myScrollPane.widthProperty());
        title.setStyle(Defaults.BORDER_COLOR.getDefault());
        vBox.getChildren().add(title);
    }

    private void populateVBox() {
        for (String definedObject : definedObjectsArray) {
            Label label = new Label(definedObject);
            if (definedObject.length() == 0)
                continue;
            label.prefWidthProperty().bind(myScrollPane.widthProperty());
            label.setStyle(Defaults.BORDER_COLOR.getDefault());
            label.setWrapText(true);
            label.setOnMouseClicked(e -> updateDefinedObject(label));
            vBox.getChildren().add(label);
        }
    }

    public ResourceBundle getResources() {
        return myResources;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

}
