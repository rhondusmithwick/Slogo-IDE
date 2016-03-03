package View.TBar;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

import View.Size;

public class HelpScreen {


    private static HelpScreen instance;


    private HelpScreen() {

    }
    
    public static synchronized HelpScreen getInstance() 
    {
            if (instance == null)
                    instance = new HelpScreen();
            return instance;
    }

    public void showHelpScreen(String htmlFile) {
        Group root;
        root = new Group();
        Stage stage = new Stage();
        Scene myScene = new Scene(root, Size.HTML_WIDTH.getSize(), Size.HTML_HEIGHT.getSize());
        stage.setScene(myScene);
        createHTMLViewer(root, htmlFile, myScene);
        stage.show();
    }


    private void createHTMLViewer(Group root, String htmlFile, Scene myScene) {
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







