package View;


import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Console implements ConsoleInterface{

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String CSS_BORDER_STYLE = "-fx-border-color: black;";
    private static final String NEW_LINE = "\n";
    private static final String DISP = "disp";

    private static final int STARTING_HEIGHT = 195;

    private String language;
    private ResourceBundle myResources;
    private ScrollPane scroll;
    private Label contents;
    private Label title;
    private VBox box;
    private SimpleStringProperty consoleInput;


    public Console(SimpleStringProperty consoleInput){
        this.consoleInput=consoleInput;
        addListner();
        
        this.language = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language + DISP);
        setScroll();
        
        box = new VBox();
        box.prefWidthProperty().bind(scroll.widthProperty());
        scroll.setContent(box);
        addTitle();
    }


    private void setScroll () {
        scroll = new ScrollPane();
        scroll.setMinViewportHeight(STARTING_HEIGHT);
        scroll.setPrefViewportHeight(STARTING_HEIGHT);
        scroll.setMaxHeight(STARTING_HEIGHT);
        HBox.setHgrow(scroll, Priority.ALWAYS);
    }


    private void addListner () {
        consoleInput.addListener((ov, oldVal, newVal) ->
                                    addContents(newVal));
        
    }


    private void addTitle() {
        title = createLabel(myResources.getString("consTitle"));
        box.getChildren().add(title);
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.TOP_CENTER);
        label.setStyle(CSS_BORDER_STYLE);
        label.prefWidthProperty().bind(scroll.widthProperty());
        label.setWrapText(true);
        return label;
    }

   private void addContents(String s){
        if(contents == null){
            createNew(s);
        }else{
            addToCurrent(s);
        }
    }

    private void addToCurrent(String s) {
        String curr = contents.getText();
        curr = curr + s;
        contents.setText(curr);

    }

    private void createNew(String s) {
        String end;
        if(s.endsWith(NEW_LINE)){
            end = "";
        }else{
            end = NEW_LINE;
        }
        contents = createLabel(s+end);
        contents.setOnMouseClicked(e->clearConsole());
        box.getChildren().add(contents);
    }

    private void clearConsole() {
        box.getChildren().remove(contents);
    }

    @Override
    public Node getConsole(){
        return scroll;
    }


}
