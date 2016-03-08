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

    String title;
    List<ExtensionFilter> fExt;
    private ResourceBundle myResources;
    private boolean save;
    private FileChooser fChoose;

    /**
     * super constructor for a slogofilechooser subclass instance
     *
     * @param title String title for file chooser
     * @param fExt  a list of extension filters for what files the filechooser can display
     * @param save  boolean whether the file chooser will be used to save or open
     */
    public SlogoFileChooser(String title, List<ExtensionFilter> fExt, boolean save) {
        super(Size.MINI.getSize(), Size.MINI.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        this.title = title;
        this.fExt = fExt;
        this.save = save;
    }

    /**
     * creates the file chooser, sets its title and file extensions
     */
    @Override
    protected void createScene() {
        fChoose = new FileChooser();
        fChoose.setTitle(myResources.getString(title));
        fChoose.getExtensionFilters().addAll(fExt);

    }

    /**
     * shows the file chooser window and gets the user chosen file
     *
     * @return the File object the user chose
     */
    protected File showWindow() {
        hideScene();
        File file = showFChooser(fChoose, save);
        closeScene();
        return file;

    }

}
