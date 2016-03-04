package view.TBar;


import java.util.ResourceBundle;
import Maps.IndexMap;
import view.Defaults;
import view.Size;
import view.Xml.MapToXML;
import view.utilities.ButtonFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IndexMapSaver {

    private ResourceBundle myResources;
    private Scene myScene;
    private TextField tField;
    private IndexMap inMap;
    private SimpleStringProperty error;
    private VBox v;
    private Stage stage;


    public IndexMapSaver(IndexMap inMap, SimpleStringProperty error){
        this.inMap = inMap;
        this.error=error;
    }


    public void showSaver() {
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        Group root;
        root = new Group();
        stage = new Stage();
        myScene = new Scene(root, Size.MAP_SAVER.getSize(), Size.MAP_SAVER.getSize());
        
        stage.setScene(myScene);
        createSaver();
        root.getChildren().add(v);
        stage.show();
    }


    private void createSaver () {
        setVBox();
        Label title = new Label(myResources.getString("saverTitle"));
        tField = new TextField();
        tField.prefWidthProperty().bind(myScene.widthProperty());
        Button set = ButtonFactory.createButton(myResources.getString("save"), e->saveList());
        v.getChildren().addAll(title, tField, set);

    }


    private void setVBox () {
        v = new VBox(Size.TB_PADDING.getSize());
        v.setAlignment(Pos.TOP_CENTER);
        v.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        v.prefHeightProperty().bind(myScene.heightProperty());
        v.prefWidthProperty().bind(myScene.widthProperty());
    }


    private void saveList (){

        stage.close();
        try {
            String text = tField.getText();
            if(text.equals("")){
                return;
            }else{
                MapToXML mapper = new MapToXML();
                mapper.saveMap(text, inMap);
            }
        }
        catch (Exception e) {
           e.printStackTrace();
           error.set("");
           error.set("saveError");
           
        }
    }

}



