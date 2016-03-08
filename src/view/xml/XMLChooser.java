package view.xml;

import view.FileExtensions;
import view.utilities.SlogoFileChooser;

import java.io.File;
import java.util.Arrays;

/**
 * Class extends the slogoFileChooser abstract class and is responsible for allowing
 * the user to choose an xml File to be loaded or saved.
 *
 * @author Cali
 */
public class XMLChooser extends SlogoFileChooser {
    /**
     * creates new xml chooser instance
     *
     * @param save boolean whether to save or open file
     */
    public XMLChooser(boolean save) {
        super("xmlSelect", Arrays.asList(FileExtensions.XML.getFilter()), save);
    }

    /**
     * Shows the chooser and gets the users chosen file
     *
     * @return File object of user chosen file
     */
    public File getFile() {
        return showWindow();
    }


}
