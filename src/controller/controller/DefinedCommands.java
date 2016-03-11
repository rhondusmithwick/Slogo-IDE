package controller.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import model.usercontrol.MakeUserInstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class DefinedCommands {
    private final ObservableMap<String, MakeUserInstruction> definedCommands = FXCollections.observableHashMap();
    private final Map<String, List<String>> commandStrings = new HashMap<>();
    private final SimpleStringProperty frontEndText = new SimpleStringProperty(this, "definedCommands");

    public DefinedCommands() {
        addListeners();
    }

    private void addListeners() {
        definedCommands.addListener((MapChangeListener<String, MakeUserInstruction>) change -> {
            commandStrings.clear();
            definedCommands.entrySet().parallelStream().forEach(this::addToCommandString);
            frontEndText.set(commandStrings.toString());
        });
    }

    private void addToCommandString(Map.Entry<String, MakeUserInstruction> entry) {
        commandStrings.put(entry.getKey(), entry.getValue().getVariableList());
    }

    public void put(String name, MakeUserInstruction makeUserInstruction) {
        definedCommands.put(name, makeUserInstruction);
        commandStrings.put(name, makeUserInstruction.getVariableList());
    }

    public SimpleStringProperty frontEndTextProperty() {
        return frontEndText;
    }

    public MakeUserInstruction getNode(String commandName) {
        return definedCommands.get(commandName);
    }

    public boolean containsCommand(String commandName) {
        return definedCommands.containsKey(commandName);
    }
}
