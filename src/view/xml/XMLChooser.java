package view.xml;

import java.io.File;
import java.util.Arrays;
import view.FileExtensions;
import view.utilities.SlogoFileChooser;


public class XMLChooser extends SlogoFileChooser{

    public XMLChooser(boolean save){
    	super("xmlSelect", Arrays.asList(FileExtensions.XML.getFilter()), save);
    }

    public File getFile(){
    	return showWindow();
    }


    
}
