package View.TBar;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import View.Defaults;
import View.FileExtensions;
import View.Size;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageChooser {
    
    private ResourceBundle myResources;
    public ImageChooser(){
        
    }
    
    
    private void setUpFileChooser(FileChooser fChoose, Stage s) {
        Group root = new Group();
        s.setScene(new Scene(root, Size.MINI.getSize(), Size.MINI.getSize()));

        fChoose.setTitle(myResources.getString("image"));
        fChoose.getExtensionFilters().addAll(FileExtensions.JPG.getFilter(), 
                                             FileExtensions.PNG.getFilter(),
                                             FileExtensions.GIF.getFilter());
        s.show();
        s.hide();
    }
    
    
    public String chooseTurtIm() throws MalformedURLException {
    	myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        FileChooser fChoose = new FileChooser();
        Stage s = new Stage();
        setUpFileChooser(fChoose, s);
        File file = fChoose.showOpenDialog(s);
        s.close();
        if (file == null) {
            return null;
        }
            return file.toURI().toURL().toString();


    }
}
