package view.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class is responsible for getting all the files from a given directory. 
 * Contains a utilities function so is never actually instantiated.
 * @author Cali
 *
 */

public class FileGetter {

    private FileGetter(){};


    /**
     * Gets all file names from a given directory. 
     * Is static so that it can be accessed as the actual class is never instantiated,
     * also so that function can be accessed without this object being passed.
     * @param directoryLocation String path to a file directory
     * @return List of Strings of all file names within given directory
     */
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



