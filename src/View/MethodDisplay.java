package View;

import java.util.ResourceBundle;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MethodDisplay implements EnvironmentDisplayInterface {


    private static final int STARTING_WIDTH = 400;
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
        myScrollPane.setMinViewportWidth(STARTING_WIDTH);
        myScrollPane.setPrefViewportWidth(STARTING_WIDTH);
        myScrollPane.setMaxWidth(STARTING_WIDTH);
        VBox.setVgrow(myScrollPane, Priority.SOMETIMES);

        createCurrVDisp();
    }

    @Override
    public Node getEnvDisplay() {
        return myScrollPane;
    }


    private void createCurrVDisp () {
        vBox = new VBox();
        vBox.prefWidthProperty().bind(myScrollPane.widthProperty());
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
        title.prefWidthProperty().bind(myScrollPane.widthProperty());
        title.setStyle(CSS_BLACK_BORDER);
        vBox.getChildren().add(title);
    }

    private void populateVBox () {
        for(String var: methodsArray){
            Label l = new Label(var);
            if(var.length()==0){
                continue;
            }
            l.prefWidthProperty().bind(myScrollPane.widthProperty());
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
