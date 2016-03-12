package view.utilities;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Defaults;
import view.Size;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

/**
 * An abstract class that represents a base for classes that implement file choosers. It extends the abstract
 * popup class as the fileChooser is itself a popup.
 *
 * @author Cali
 */
public abstract class SlogoFileChooser extends PopUp {

    final String title;
    final List<ExtensionFilter> fileExtensions;
    private final ResourceBundle myResources;
    private final boolean save;
    private FileChooser fileChooser;
    private final String location;

    /**
     * super constructor for a slogofilechooser subclass instance
     *
     * @param title String title for file chooser
     * @param fExt  a list of extension filters for what files the filechooser can display
     * @param save  boolean whether the file chooser will be used to save or open
     */
    public SlogoFileChooser(String title, List<ExtensionFilter> fileExtensions, boolean save, String location) {
        super(Size.MINI.getSize(), Size.MINI.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.title = title;
        this.fileExtensions = fileExtensions;
        this.save = save;
        this.location = location;
    }

    /**
     * creates the file chooser, sets its title and file extensions
     */
    @Override
    protected void createScene() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(location));
        fileChooser.setTitle(myResources.getString(title));
        fileChooser.getExtensionFilters().addAll(fileExtensions);

    }

    /**
     * shows the file chooser window and gets the user chosen file
     *
     * @return the File object the user chose
     */
    protected File showWindow() {
        hideScene();
        File file = showFChooser(fileChooser, save);
        closeScene();
        return file;

    }

}
