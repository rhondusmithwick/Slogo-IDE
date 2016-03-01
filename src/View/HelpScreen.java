package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class HelpScreen {


    private static final int HTML_HEIGHT = 600;
    private static final int HTML_WIDTH = 800;
    private Scene myScene;

    public HelpScreen() {

    }


    public void showHelpScreen(String htmlFile) {
        Group root;
        root = new Group();
        Stage stage = new Stage();
        stage.setTitle("SLOGO Help");
        myScene = new Scene(root, HTML_WIDTH, HTML_HEIGHT);
        stage.setScene(myScene);
        createHTMLViewer(root, htmlFile);
        stage.show();
    }


    private void createHTMLViewer(Group root, String htmlFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        WebView webView = new WebView();
        webView.prefHeightProperty().bind(myScene.heightProperty());
        webView.prefWidthProperty().bind(myScene.widthProperty());;
        WebEngine webEngine = webView.getEngine();
        URL urlHello = classLoader.getResource(htmlFile);
        webEngine.load(urlHello.toExternalForm());
        root.getChildren().add(webView);

    }

}







