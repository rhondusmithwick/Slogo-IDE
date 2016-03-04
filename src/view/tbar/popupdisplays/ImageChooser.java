package view.tbar.popupdisplays;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import view.FileExtensions;
import view.utilities.SlogoFileChooser;


public class ImageChooser extends SlogoFileChooser {

    private File file;
    public ImageChooser(){
    	
        super("image", Arrays.asList(FileExtensions.JPG.getFilter(), 
                FileExtensions.PNG.getFilter(),
                FileExtensions.GIF.getFilter() ), false);
    }
	
	public String getChosen() throws MalformedURLException{
		file = showWindow();
		if (file == null) {
            return null;
        } 
		return file.toURI().toURL().toString();
	}
}
