package View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import Observables.ObjectObservable;

public class ErrorDisplay implements ErrorDisplayInterface, Observer {

    private static final String CSS_BORDER_STYLE = "-fx-border-color: black;";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DISP = "disp";
    private static final double SCROLLPANE_WIDTH = 380.00;
    private static final double SCROLLPANE_HEIGHT = 195.0;
    private static final double VBOX_WIDTH = 530.00;
    private ScrollPane errorDisp;
    private Label title;
    private ResourceBundle myResources;
    private VBox errorContain;
    private String language;
    private ObjectObservable<String> error;

    @Override
    public void createErrorDisplay(ObjectObservable<String> error) {
        this.error = error;
        setUpListner();
        this.language = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language + DISP);
        errorDisp = new ScrollPane();
        errorDisp.setMaxSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        errorContain = new VBox();
        errorContain.setPrefWidth(VBOX_WIDTH);
        setTitle();
        errorContain.getChildren().add(title);
        errorDisp.setContent(errorContain);


    }

    private void setUpListner () {
        error.addObserver(this);
        
    }

    private void setTitle() {
        title = new Label(myResources.getString("errorBTitle"));
        title.setPrefWidth(SCROLLPANE_WIDTH);
        title.setAlignment(Pos.TOP_CENTER);
        title.setStyle(CSS_BORDER_STYLE);
    }

    private void showError(String s) {
        Label l = new Label(s);
        l.setMaxWidth(SCROLLPANE_WIDTH);
        l.setStyle(CSS_BORDER_STYLE);
        l.setWrapText(true);
        l.setOnMouseClicked(e -> clearError(l));
        errorContain.getChildren().add(l);

    }


    @Override
    public void clearError(Label l) {
        errorContain.getChildren().remove(l);

    }

    @Override
    public Node getErrorDisplay() {
        return errorDisp;
    }

    @Override
    public void update (Observable o, Object arg1) {
        showError(error.get());
        
    }

}
