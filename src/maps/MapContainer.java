package maps;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class MapContainer<K, V> {
    private final ObservableMap<K, V> map = FXCollections.observableHashMap();
    private final List<String> theStrings = new LinkedList<>();
    private final SimpleStringProperty frontEndText;

    public MapContainer(String name) {
        frontEndText = new SimpleStringProperty(this, name);
        addListener(change -> modifyString());
    }

    public void addListener(MapChangeListener<? super K, ? super V> listener) {
        map.addListener(listener);
    }

    public void modifyString() {
        theStrings.clear();
        map.entrySet().parallelStream().forEach(this::addString);
        frontEndText.set(theStrings.toString());
    }

    private void addString(Entry<K, V> entry) {
        theStrings.add(entry.getKey().toString() + "=" + entry.getValue().toString());
    }

    public SimpleStringProperty frontEndTextProperty() {
        return frontEndText;
    }

    public void putEntry(Entry<K, V> e) {
        map.put(e.getKey(), e.getValue());
    }


    public void put(K key, V value) {
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public void remove(K key) {
        map.remove(key);
    }


}
