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
public class MapContainer<T> {
    private final ObservableMap<String, T> map = FXCollections.observableHashMap();
    private final List<String> theStrings = new LinkedList<>();
    private final SimpleStringProperty frontEndText;

    public MapContainer(String name) {
        frontEndText = new SimpleStringProperty(this, name);
        addListeners();
    }

    private void addListeners() {
        map.addListener((MapChangeListener<String, T>) change -> modifyString());
    }

    public void put(String name, T value) {
        map.put(name, value);
    }

    private void addString(Entry<String, T> entry) {
        theStrings.add(entry.getKey() + "=" + entry.getValue().toString());
    }

    public SimpleStringProperty frontEndTextProperty() {
        return frontEndText;
    }

    public T get(String commandName) {
        return map.get(commandName);
    }

    public boolean contains(String commandName) {
        return map.containsKey(commandName);
    }

    public void remove(String key) {
        map.remove(key);
    }


    public void modifyString() {
        theStrings.clear();
        map.entrySet().parallelStream().forEach(this::addString);
        frontEndText.set(theStrings.toString());
    }

    public void putEntry(Entry<String, T> e) {
        map.put(e.getKey(), e.getValue());
    }



}
