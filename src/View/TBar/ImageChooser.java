package View.TBar;

import java.io.File;
import java.net.MalformedURLException;
import View.FileExtensions;
import View.Size;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageChooser {
    private static ImageChooser instance;
    
    private ImageChooser(){
        
    }
    
    public static synchronized ImageChooser getInstance() 
    {
            if (instance == null)
                    instance = new ImageChooser();
            return instance;
    }
    
    private void setUpFileChooser(FileChooser fChoose, Stage s, String title) {
        Group root = new Group();
        s.setScene(new Scene(root, Size.MINI.getSize(), Size.MINI.getSize()));

        fChoose.setTitle(title);
        fChoose.getExtensionFilters().addAll(FileExtensions.JPG.getFilter(), 
                                             FileExtensions.PNG.getFilter(),
                                             FileExtensions.GIF.getFilter());
        s.show();
        s.hide();
    }
    
    
    public String chooseTurtIm(String title) throws MalformedURLException {
        FileChooser fChoose = new FileChooser();
        Stage s = new Stage();
        setUpFileChooser(fChoose, s, title);
        File file = fChoose.showOpenDialog(s);
        s.close();
        if (file == null) {
            return null;
        }
            String imagepath = file.toURI().toURL().toString();
            return imagepath;

    }
}
