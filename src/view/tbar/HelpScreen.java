package view.tbar;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.utilities.PopUp;

public class HelpScreen extends PopUp {

	private ResourceBundle myResources;
	private ClassLoader classLoader;
	private WebEngine webEngine;
	private WebView webView;


    public HelpScreen() {
    	super(Size.HTML_WIDTH.getSize(), Size.HTML_HEIGHT.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
    	myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }

    @Override
    protected void createContainer() {
        classLoader = getClass().getClassLoader();
        webView = new WebView();
        webView.prefHeightProperty().bind(getSize(true));
        webView.prefWidthProperty().bind(getSize(false));
        webEngine = webView.getEngine();
        

    }

	@Override
	protected void createScene() {
		URL urlHello = classLoader.getResource(myResources.getString("helpFile"));
        webEngine.load(urlHello.toExternalForm());
        addContainer(webView);
		
	}



}







