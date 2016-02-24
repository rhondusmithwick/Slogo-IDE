package Controller;

import Model.Forward;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class CommandContainer {

    private final Map<Class<?>, Class<?>[]> parametersMap = new HashMap<>();
    private final Map<String, Class<?>> inputMap = new HashMap<>();

    private final ObservableList<String> commandStringList = FXCollections.observableArrayList();
    private final SimpleObjectProperty<ResourceBundle> resourceBundle = new SimpleObjectProperty<>();
    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    public CommandContainer(ResourceBundle resourceBundle) {
        addListeners();
        setResourceBundle(resourceBundle);
        addToCommandStringList("Forward", "Backward", "SetImage", "SetPenColor");
    }

    private void addListeners() {
        resourceBundle.addListener((ov, oldVal, newVal) ->
                modifyInputMap());
        commandStringList.addListener((ListChangeListener<String>) c ->
                modifyParametersMap());
    }

    private void modifyInputMap() {
//        inputMap.clear();
//        for (String key : resourceBundle.get().keySet()) {
        for (String key : commandStringList) {
            String val = resourceBundle.get().getString(key);
            String[] valArray = val.split("|");
            Class<?> commandClass = getClassForName(key);
            for (String commandInput : valArray) {
                inputMap.put(commandInput, commandClass);
            }
        }
    }

    private void modifyParametersMap() {
        parametersMap.clear();
        for (String commandString : commandStringList) {
            Class theClass = getClassForName(commandString);
            Class<?>[] parameters = getParametersForClass(theClass);
            parametersMap.put(theClass, parameters);
        }
    }

    private Class<?>[] getParametersForClass(Class theClass) {
        Constructor[] allConstructors = theClass.getDeclaredConstructors();
        return allConstructors[0].getParameterTypes();
    }

    private Class<?> getClassForName(String className) {
        try {
            return Class.forName(commandLocations.getString(className));
        } catch (Exception e) {
            return Forward.class;
        }
    }

    void addToCommandStringList(String... commands) {
        commandStringList.addAll(commands);
    }

    void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle.set(resourceBundle);
    }

}
