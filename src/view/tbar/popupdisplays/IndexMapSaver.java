package view.tbar.popupdisplays;


import java.util.Arrays;
import java.util.ResourceBundle;

import view.Defaults;
import view.Size;
import view.xml.MapToXML;
import view.utilities.ButtonFactory;
import view.utilities.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import maps.IndexMap;

/**
 *Class responsible for allowing user to save current palettes based on index maps such as color and index palettes.
 *Is a subclass of abstract popup class.
 * @author Cali
 *
 */
public class IndexMapSaver extends PopUp{

    private ResourceBundle myResources;
    private TextField textField;
    private IndexMap indexMap;



    /**
     * creates new indexmap save instance
     * @param inMap index map to be saved
     */
    public IndexMapSaver(IndexMap indexMap){
        super(Size.MAP_SAVER.getSize(), Size.MAP_SAVER.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.indexMap = indexMap;
        this.myResources =  ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }

    private void saveList (){

        closeScene();
        try {
            String text = textField.getText();
            if(text.equals("")){
                return;
            }else{
                MapToXML mapper = new MapToXML();
                mapper.saveMap(text, indexMap);
            }
        }
        catch (Exception e) {

            return;

        }
    }

    /**
     * creates the scene and components needed to save the map, and adds them to
     * popup scene
     */
    @Override
    protected void createScene() {
        Label title = new Label(myResources.getString("saverTitle"));
        textField = new TextField();
        textField.prefWidthProperty().bind(getSize(false));
        Button set = ButtonFactory.createButton(myResources.getString("save"), e->saveList());
        addNodes(Arrays.asList(title, textField, set));

    }

}



