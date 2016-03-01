package View;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodDisplay implements EnvironmentDisplayInterface {

    private static final int SCROLL_HEIGHT = 200;
    private static final int SCROLL_WIDTH = 400;
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";

    private SimpleStringProperty methods;
    private MethodUpdate updater;
    private String displayLanguage;
    private ResourceBundle myResources;
    private ScrollPane myScrollPane;
    private VBox vBox;
    private String[] methodsArray;
    private ObjectObservable<String> parsingLanguage, intCommand;


    public MethodDisplay(ObjectObservable<String> pLang, ObjectObservable<String> intCommand, SimpleStringProperty methods) {
        this.intCommand=intCommand;
        this.parsingLanguage = pLang;
        this.methods=methods;
        this.displayLanguage = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + displayLanguage + DISP);
        myScrollPane = new ScrollPane();
        myScrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        createCurrVDisp();
    }

    @Override
    public Node getEnvDisplay() {
        return myScrollPane;
    }


    private void createCurrVDisp () {
        vBox = new VBox();
        setTitle();
        String methodsString = methods.get();
        if(methodsString!=null){
            methodsArray = methodsString.split("\n");

            populateVBox();
        }
        myScrollPane.setContent(vBox);

    }

    private void setTitle () {
        Label title= new Label(myResources.getString("methodDisplayTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.setPrefWidth(SCROLL_WIDTH);
        title.setStyle(CSS_BLACK_BORDER);
        vBox.getChildren().add(title);
    }

    private void populateVBox () {
        for(String var: methodsArray){
            Label l = new Label(var);
            if(var.length()==0){
                continue;
            }
            l.setPrefWidth(SCROLL_WIDTH);
            l.setWrapText(true);
            l.setStyle(CSS_BLACK_BORDER);
            l.setOnMouseClicked(e->updateMethod(l));
            vBox.getChildren().add(l);
        }

    }


    private void updateMethod (Label label) {
        updater = new MethodUpdate(myResources, intCommand, parsingLanguage);
        updater.updateMethod(label); // different from the method in this class      
    }

    @Override
    public void updateEnvNode() {
        createCurrVDisp();
    }



}
