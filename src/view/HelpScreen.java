package view;

import java.net.URL;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpScreen {

    private static final String htmlHelp = "SLOGO_help.html";

    public HelpScreen(){

    }




    public void showHelpScreen(){
        Group root;
        root = new Group();
        Stage stage = new Stage();
        stage.setTitle("SLOGO Help");
        stage.setScene(new Scene(root, 1000, 600));
        createHTMLViewer(root);
        stage.show();
    }





    private void createHTMLViewer (Group root) {
        ClassLoader classLoader = getClass().getClassLoader();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        System.out.println(htmlHelp);
        URL urlHello = classLoader.getResource("resources.html/SLOGO_help.html");
        System.out.println(urlHello);
        webEngine.load(urlHello.toExternalForm());
    

        root.getChildren().add(webView);

    }

}







