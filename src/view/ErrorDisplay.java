package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class ErrorDisplay implements ErrorDisplayInterface {

    private static final String CSS_BORDER_STYLE = "-fx-border-color: black;";
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DEFAULT_LANGUAGE = "english";
    private static final String DISP = "disp";
    private static final double SCROLLPANE_WIDTH = 400.00;
    private static final double SCROLLPANE_HEIGHT = 195.0;
    private static final double VBOX_WIDTH = 530.00;
    private ScrollPane errorDisp;
    private Label title;
    private ResourceBundle myResources;
    private VBox errorContain;
    private String language;

    @Override
    public void createErrorDisplay() {
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

    private void setTitle() {
        title = new Label(myResources.getString("errorBTitle"));
        title.setPrefWidth(SCROLLPANE_WIDTH);
        title.setAlignment(Pos.TOP_CENTER);
        title.setStyle(CSS_BORDER_STYLE);
    }

    @Override
    public void showError(String s) {
        Label l = new Label(s);
        l.setPrefWidth(VBOX_WIDTH);
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

}
