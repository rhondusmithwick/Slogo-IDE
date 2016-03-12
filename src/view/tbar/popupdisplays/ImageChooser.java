package view.tbar.popupdisplays;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import view.Defaults;
import view.FileExtensions;
import view.utilities.SlogoFileChooser;

/**
 * Class extends the slogoFileChooser abstract class and is responsible for allowing
 * the user to choose an image file to be used as the turtle image.
 * @author Cali
 *
 */
public class ImageChooser extends SlogoFileChooser {

    private File file;
    
    /**
     * creates new image chooser instance
     */
    public ImageChooser(){
    	
        super("image", Arrays.asList(FileExtensions.JPG.getFilter(), 
                FileExtensions.PNG.getFilter(),
                FileExtensions.GIF.getFilter() ), false, Defaults.IMAGE_LOC.getDefault());
    }
	/**
	 * returns the string file path of the file the user chose, or null if no file was chosen
	 * @return string file path of chosen file or null if no file chosen
	 * @throws MalformedURLException
	 */
	public String getChosen() throws MalformedURLException{
		file = showWindow();
		if (file == null) {
            return null;
        } 
		return file.toURI().toURL().toString();
	}
}
