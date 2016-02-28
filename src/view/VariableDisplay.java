package view;

import java.util.ResourceBundle;
import Controller.Controller.StringObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VariableDisplay implements EnvironmentDisplayInterface {
    
    private static final int SCROLL_HEIGHT = 200;
    private static final int SCROLL_WIDTH = 400;
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String CSS_BLACK_BORDER = "-fx-border-color: black;";

    private final SimpleStringProperty variablesString = new SimpleStringProperty(this, "variablesString");
    private VariableUpdate updater;
    private String dispLang;
    private ResourceBundle myResources;
    private ScrollPane scroll;
    private VBox vBox;
    private String[] vArray;
    private StringObservable pLang;
    private CommandEntryInterface cEnt;
    
    public VariableDisplay(){
        
        this.dispLang = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + dispLang + DISP);
        
    }
    
    @Override
    public Node getEnvDisplay () {
        return scroll;
    }

    @Override
    public void createEnvNode () {
        scroll = new ScrollPane();
        scroll.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        createCurrVDisp();
        

    }

    private void createCurrVDisp () {
        vBox = new VBox();
        setTitle();
        String vString = variablesString.get();
        if(vString!=null){
            
            vArray = vString.split("\n");
            populateVBox();
        }
        scroll.setContent(vBox);
        
    }

    private void setTitle () {
        Label title= new Label(myResources.getString("varTitle"));
        title.setAlignment(Pos.TOP_CENTER);
        title.setPrefWidth(SCROLL_WIDTH);
        title.setStyle(CSS_BLACK_BORDER);
        vBox.getChildren().add(title);
        
    }

    private void populateVBox () {
        for(String var:vArray){
            Label l = new Label(var);
            l.setPrefWidth(SCROLL_WIDTH);
            l.setWrapText(true);
            l.setStyle(CSS_BLACK_BORDER);
            l.setOnMouseClicked(e->updateVariable(l));
            vBox.getChildren().add(l);
        }
        
    }

    
    private void updateVariable (Label l) {
        updater = new VariableUpdate(myResources, cEnt, pLang);
        updater.updateVariable(l);
        
    }

    @Override
    public void updateEnvNode () {
        createCurrVDisp();

    }

    @Override
    public SimpleStringProperty getEnvProperty () {
        return variablesString;
    }
    
    @Override
    public void setPLang(StringObservable str){
        this.pLang=str;
    }
    
    @Override
    public void setCommEntry(CommandEntryInterface cEnt){
        this.cEnt= cEnt;
    }
    
    
    




}
