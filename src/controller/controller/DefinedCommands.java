package controller.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.usercontrol.MakeUserInstruction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class DefinedCommands<T> {
    private final ObservableMap<String, T> map = FXCollections.observableHashMap();
    private final List<String> theStrings = new LinkedList<>();
    private final SimpleStringProperty frontEndText;

    public DefinedCommands(String name) {
        frontEndText = new SimpleStringProperty(this, name);
        addListeners();
    }

    private void addListeners() {
        map.addListener((MapChangeListener<String, T>) change -> {
            frontEndText.set(theStrings.toString());
        });
    }


    public void put(String name, T value) {
        map.put(name, value);
        theStrings.add(name + "= " + value.toString());
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
}
