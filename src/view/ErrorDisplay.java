package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class ErrorDisplay implements ErrorDisplayInterface {

    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private final double SCROLLPANE_WIDTH = 550.00;
    private final double SCROLLPANE_HEIGHT = 195.0;
    private ScrollPane errorDisp;
    private Label title;
    private ResourceBundle myResources;
    private VBox errorContain;

    @Override
    public void createErrorDisplay() {
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + "english" + "disp");
        errorDisp = new ScrollPane();
        errorDisp.setTranslateX(0);
        errorDisp.setMaxSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        errorContain = new VBox();
        errorContain.setPrefWidth(SCROLLPANE_WIDTH - 20);
        title = new Label(myResources.getString("errorBTitle"));
        title.setPrefWidth(SCROLLPANE_WIDTH);
        title.setAlignment(Pos.TOP_CENTER);
        title.setStyle("-fx-border-color: black;");
        errorContain.getChildren().add(title);
        errorDisp.setContent(errorContain);


    }

    @Override
    public void showError(String s) {
        Label l = new Label(s);
        l.setPrefWidth(SCROLLPANE_WIDTH - 20);
        l.setStyle("-fx-border-color: black;");
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
