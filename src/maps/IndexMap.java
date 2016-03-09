package maps;

import java.util.Map.Entry;
import java.util.stream.Stream;

import observables.MapObservable;
import view.Defaults;
import view.xml.MapFromXML;

/**
 * This class represents a map from an Integer index to a string value.  It is backed by
 * a MapObservable so that observers can be notified whenever components are added to the map.
 * @author calinelson
 */

public abstract class IndexMap {
	
	/**
	 * Constructor for new index map. Adds default elements to newly created map
	 * @throws Exception
	 */
    protected IndexMap() throws Exception{
        addElements(Defaults.DEFAULT.getDefault());
    }
    
    /**
     * Creates a new map and adds elements to the map.
     * @param type Where the elements are added from. Can be either an XML file name or "Default"
     * @throws Exception 
     */
    public void addElements(String type) throws Exception{
        newMap();
        if(type.equals(Defaults.DEFAULT.getDefault())){
            defaultElements();
        }else{
            getElementsFromXML(type);
        }
    }
    
    public int getIndex(String value){
    	Stream<Entry<Integer, String>>  filt= this.getIndexMap().getEntrySet().stream().filter(e-> e.getValue().equals(value));
    	Entry<Integer, String> first =filt.findFirst().get();
    	return first.getKey();

    }
    
    /**
     * creates a new mapObservable object
     */
    protected abstract void newMap ();

    /**
     * Obtains the elements for a new map from an xml file, or if the type is
     * "Default" sets default values.
     * @param type Either an XML file name or "Default"
     * @throws Exception
     */
    protected void getElementsFromXML(String type) throws Exception{
        MapFromXML xMap = new MapFromXML(this.getIndexMap());
        String direct = getDirectory();
        xMap.populateMap(direct+type);
    }

    /**
     * Returns the location in which XML files containing saved maps 
     * are saved 
     * @return file directory path as string
     */
    protected abstract String getDirectory ();

    
    /**
     * If the type is default sets the map values and keys to a set of default entry
     * values
     * @throws Exception
     */
    protected abstract void defaultElements () throws Exception;

    /**
     * returns the MapObservable object that backs the index map
     * @return MapObservable backing index map
     */
    public abstract MapObservable<Integer, String> getIndexMap();
    
    /**
     * adds a key, value pair to the index map
     * @param index index to set value at
     * @param value value to be set for the index
     * @throws Exception
     */
    public abstract void setAtIndex(int index, String value) throws Exception;
    
    /**
     * returns a value string based on the given index key
     * @param key index to get string at
     * @return value string associated with key index
     */
    public abstract String get(int key);

}
