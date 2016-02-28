package Controller.Controller;

import java.util.Observable;

/**
 * Created by rhondusmithwick on 2/26/16.
 *
 * @author Rhondu Smithwick
 */
public class ObjectObservable<E> extends Observable {

    private E value = null;

    public void set(E value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    public E get() {
        return value;
    }
}
