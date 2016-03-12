package maps;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import view.Defaults;

import java.io.File;


/**
 * this class is a map between an integer index and a string representing a valid
 * image file path. It is an observable map so that when new images are added, observers
 * are notified of the change.
 *
 * @author calinelson
 */

public class ImageMap extends IndexMap {

    private ObservableMap<Integer, String> images;

    /**
     * creates a new image map with default images and indexes
     *
     * @throws Exception
     */
    public ImageMap() {
        super();
    }


    /**
     * returns the MapObservable<Integer,String> backing the image map
     *
     * @return mapObservable backing the image map
     */
    @Override
    public ObservableMap<Integer, String> getIndexMap() {
        return images;
    }

    /**
     * Sets the given image file path string to the the given index, either adding
     * a new element or overwriting a previous one
     *
     * @param index index to add or overwrite
     * @param toSet image file path of new image
     */
    @Override
    public void setAtIndex(int index, String value) {
        images.put(index, value);


    }


    /**
     * Obtains default set of images and indexes. Finds names of all files in
     * default image file directory and adds them to the map.
     */
    @Override
    protected void defaultElements() {
        int count = 0;
        File directory = new File(Defaults.IMAGE_LOC.getDefault());
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String name = file.getName();
            images.put(count, Defaults.IMAGE_LOC.getDefault() + name);
            count++;
        }

    }


    /**
     * creates a new map observable object and sets it to overwrite the
     * current map backing the image map
     */
    @Override
    protected void newMap() {
        images = FXCollections.observableHashMap();

    }


    /**
     * returns the String representing an image mapped to index key
     *
     * @param key index to get image for
     * @return image file path string for index key
     */
    @Override
    public String get(int key) {
        return images.get(key);
    }


    /**
     * returns directory where image map xml files are saved
     *
     * @return string of directory path where image map files are saved
     */
    @Override
    protected String getDirectory() {
        return Defaults.IMAGELIST_LOC.getDefault();
    }
}
