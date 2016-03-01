package View;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import Observables.ObjectObservable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Console implements ConsoleInterface, Observer{

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String CSS_BORDER_STYLE = "-fx-border-color: black;";
    private static final String NEW_LINE = "\n";
    private static final String DISP = "disp";
    private static final int SCROLL_WIDTH = 350;
    private static final int SCROLL_HEIGHT = 195;

    private String language;
    private ResourceBundle myResources;
    private ScrollPane scroll;
    private Label contents;
    private Label title;
    private VBox box;
    private ObjectObservable<String> consoleInput;


    public Console(ObjectObservable<String> consoleInput){
        this.consoleInput=consoleInput;
        consoleInput.addObserver(this);
        
        this.language = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language + DISP);
        scroll = new ScrollPane();
        scroll.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        box = new VBox();
        scroll.setContent(box);
        addTitle();
    }


    private void addTitle() {
        title = createLabel(myResources.getString("consTitle"));
        box.getChildren().add(title);
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.TOP_CENTER);
        label.setStyle(CSS_BORDER_STYLE);
        label.setPrefWidth(SCROLL_WIDTH);
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

    @Override
    public void update (Observable o, Object arg) {
        addContents(consoleInput.get());
        
    }

}
