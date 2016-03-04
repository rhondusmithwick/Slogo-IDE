package view;

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
    
    public ExtensionFilter getFilter(){
            return this.filter;
    }
}
