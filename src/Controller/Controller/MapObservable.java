package Controller.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by rhondusmithwick on 2/28/16.
 *
 * @author Rhondu Smithwick
 */
public class MapObservable<K, V> extends Observable {

    private final Map<K, V> map = new HashMap<>();

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        map.entrySet().parallelStream().forEach(e ->
                sb.append(e.getKey())
                        .append(" ")
                        .append(e.getValue())
                        .append("\n")
        );
        clearChanged();
        return sb.toString();
    }
}
