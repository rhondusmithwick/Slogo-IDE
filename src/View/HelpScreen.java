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


    public HelpScreen() {

    }


    public void showHelpScreen(String htmlFile) {
        Group root;
        root = new Group();
        Stage stage = new Stage();
        stage.setTitle("SLOGO Help");
        stage.setScene(new Scene(root, HTML_WIDTH, HTML_HEIGHT));
        createHTMLViewer(root, htmlFile);
        stage.show();
    }


    private void createHTMLViewer(Group root, String htmlFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL urlHello = classLoader.getResource(htmlFile);
        webEngine.load(urlHello.toExternalForm());
        root.getChildren().add(webView);

    }

}







