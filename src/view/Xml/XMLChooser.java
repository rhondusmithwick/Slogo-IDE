package view.Xml;

import java.io.File;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class XMLChooser {
    private FileChooser fChoose;
    private ResourceBundle myResources;
    private Stage s;
    private Group root;
    private File file;
    public XMLChooser(){
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }
    
    
    
    
    public File getFile(boolean save){
        s = new Stage();
        root = new Group();
        s.setScene(new Scene(root, Size.MINI.getSize(), Size.MINI.getSize()));
        setUpChooser(save);
        return file;
    }




    private void setUpChooser (boolean save) {
        fChoose = new FileChooser();
        fChoose.setTitle(myResources.getString("xmlSelect"));
        fChoose.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"));
        if(save){
            file = fChoose.showSaveDialog(s);
        }else{
            file = fChoose.showOpenDialog(s);
        }
        
    }
    
}
