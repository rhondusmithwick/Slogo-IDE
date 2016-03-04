package View.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileGetter {

	public static List<String> getAllFromDirectory (String directoryLocation) {
        
        ArrayList<String> files = new ArrayList<>();
        File directory = new File(directoryLocation);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String name = file.getName();
            files.add(name.substring(0, name.lastIndexOf('.')));
        }
        return files;


    }
	
	
	
}



