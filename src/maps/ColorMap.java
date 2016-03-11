package maps;

import java.lang.reflect.Field;
import java.util.Observer;
import view.Defaults;
import javafx.scene.paint.Color;
import observables.MapObservable;

/**
 * this class is a map between an integer index and a string representing a valid
 * javafx color. It is an observable map so that when new colors are added, observers
 * are notified of the change.
 * @author calinelson
 */

public class ColorMap extends IndexMap {
    private static final int B = 2;
    private static final int G = 1;
    private static final int R = 0;
    private MapObservable<Integer,String> colors;
    private int count;

    /**
     * Constructor for new color map with default index, color values
     * @throws Exception
     */
    public ColorMap() {
        super();
    }

    /**
     * returns the mapObservable backing the color map
     * @return map observable<String,Integer> that backs the color map
     */
    @Override
    public MapObservable<Integer, String> getIndexMap(){
        return colors;
    }
    
    /**
     * Sets the given rgb color string the the given index, either adding
     * a new element or overwriting a previous one
     *@param index index to add or overwrite
     *@param toSet rgb value to set at index
     */
    @Override
    public void setAtIndex(int index, String toSet) {
        String[] rgb = toSet.split(" ");
        String value = "rgb(" + rgb[R] + "," + rgb[G]+ ","+rgb[B]+")";
        colors.put(index, value);
        colors.notifyObservers();
    }

    /**
     * Obtains default set of colors and indexes. Uses reflection to obtain
     * all currently named colors in the JavaFx paint class.
     */
    @SuppressWarnings("rawtypes")
    protected void defaultElements() throws Exception{
        count = 0;

        Class colorClass = Class.forName(Defaults.FX_PAINT_CLASS.getDefault());
        Field[] fields = colorClass.getFields();
        for (Field field : fields) {
            Object o = field.get(null);
            if (o instanceof Color) {
                colors.put(count, field.getName());
                count++;
            }
        }
    }

    /**
     * creates a new map observable object and sets it to overwrite the 
     * current map backing the color map
     */
    @Override
    protected void newMap() {
        colors = new MapObservable<>("colors");
    }

    /**
     * returns the String representing a color mapped to index key
     * @param key index to get color for
     * @return color string for index key
     */
    @Override
    public String get(int key) {
        return colors.get(key);
    }

    /**
     * returns directory where color map xml files are saved
     * @return string of directory path where color map files are saved
     */
    @Override
    protected String getDirectory() {
       return Defaults.COLORLIST_LOC.getDefault();
    }
    
    public void addObserver(Observer obs){
        colors.addObserver(obs);
    }

}
