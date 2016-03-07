package view;
/**
 * @author calinelson
 * class representing file extensions used in file choosers in the view
 */
import javafx.stage.FileChooser.ExtensionFilter;

public enum FileExtensions {
    
    JPG("JPG", "*.jpg"),
    GIF("GIF","*.gif"),
    PNG("PNG", "*.png"),
    XML("XML", "*.xml");
    
    
    private ExtensionFilter filter;
    
    FileExtensions(String type, String ext){
            this.filter = new ExtensionFilter(type, ext);
    }
    
    /**
     * returns the ExtensionFilter for the given enum file type
     * @return ExtensionFilter for a file type
     */
    public ExtensionFilter getFilter(){
            return this.filter;
    }
}
