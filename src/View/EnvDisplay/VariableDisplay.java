package View.EnvDisplay;

import java.util.ResourceBundle;
import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class VariableDisplay implements EnvironmentDisplayInterface {
    
    private static final int STARTING_WIDTH = 400;
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";
    private EnvUpdate updater;
    private String dispLang;
    private ResourceBundle myResources;
    private ScrollPane scroll;
    private VBox vBox;
    private String[] vArray;
    private ObjectObservable<String> pLang, internalCommand;
    private SimpleStringProperty variables;
    
    public VariableDisplay(ObjectObservable<String> pLang, ObjectObservable<String> internalCommand, 
                           SimpleStringProperty variables){
        this.variables=variables;
        this.pLang=pLang;
        this.dispLang = DEFAULT_LANGUAGE;
        this.internalCommand= internalCommand;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + dispLang + DISP);
        setScroll();
        
        createCurrVDisp();
        
    }

    private void setScroll () {
        scroll = new ScrollPane();
        scroll.setMinViewportWidth(STARTING_WIDTH);
        scroll.setPrefViewportWidth(STARTING_WIDTH);
        scroll.setMaxWidth(STARTING_WIDTH);
        VBox.setVgrow(scroll, Priority.SOMETIMES);
    }
    
    @Override
    public Node getEnvDisplay () {
        return scroll;
    }

    private void createCurrVDisp () {
        vBox = new VBox();
        vBox.prefWidthProperty().bind(scroll.widthProperty());
        setTitle();
        String vString = variables.get();
        if(vString!=null){
            vArray = vString.split("\n");
            populateVBox();
        }
        scroll.setContent(vBox);
        
    }

    private void setTitle () {
        Label title= new Label(myResources.getString("varTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.prefWidthProperty().bind(scroll.widthProperty());
        title.setStyle(CSS_BLACK_BORDER);
        vBox.getChildren().add(title);
        
    }

    private void populateVBox () {
        for(String var:vArray){
            Label l = new Label(var);
            if(var.length()==0){
            	continue;
            }
            l.prefWidthProperty().bind(scroll.widthProperty());
            l.setWrapText(true);
            l.setStyle(CSS_BLACK_BORDER);
            l.setOnMouseClicked(e->updateVariable(l));
            vBox.getChildren().add(l);
        }
        
    }

    
    private void updateVariable (Label l) {
        updater = new VariableUpdate(myResources, internalCommand, pLang);
        updater.updateEnv(l);
        
    }

    @Override
    public void updateEnvNode () {
        createCurrVDisp();

    }

    

}
