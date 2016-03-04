package observables;

import javafx.beans.property.SimpleStringProperty;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class MapObservable<K, V> extends Observable {

    private final Map<K, V> map = new HashMap<>();

    private final SimpleStringProperty myString;

    public MapObservable(String name) {
        this.myString = new SimpleStringProperty(this, name, "");
    }
    public void put(K key, V value) {
        map.put(key, value);
        setChanged();
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public void modifyIfShould() {
        if (hasChanged()) {
            modifyString();
            clearChanged();
        }
    }
    
    public Collection<V> getValues(){
        return map.values();
    }
    
    public Set<Entry<K, V>> getEntrySet(){
        return map.entrySet();
    }
    
    

    private void modifyString() {
        StringBuilder sb = new StringBuilder();
        map.entrySet().parallelStream().forEach(e ->
                sb.append(e.getKey() + " " + e.getValue() + ",")
        );
        
        myString.set(sb.toString());
    }

    public SimpleStringProperty getStringProperty() {
        return myString;
    }
}