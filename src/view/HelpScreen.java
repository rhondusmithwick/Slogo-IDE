package view;

import java.net.URL;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpScreen {

    private static final String HTML_HELP = "resources/html/SLOGO_help.html";

    public HelpScreen(){

    }




    public void showHelpScreen(){
        Group root;
        root = new Group();
        Stage stage = new Stage();
        stage.setTitle("SLOGO Help");
        stage.setScene(new Scene(root, 800, 600));
        createHTMLViewer(root);
        stage.show();
    }





    private void createHTMLViewer (Group root) {
        ClassLoader classLoader = getClass().getClassLoader();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL urlHello = classLoader.getResource(HTML_HELP);
        webEngine.load(urlHello.toExternalForm());
    

        root.getChildren().add(webView);

    }

}







