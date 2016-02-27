package Controller.Controller;

import java.util.Observable;

/**
 * Created by rhondusmithwick on 2/26/16.
 *
 * @author Rhondu Smithwick
 */
public class StringObservable extends Observable {

    private String value = "";

    public void set(String value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    public String get() {
        return value;
    }
}
