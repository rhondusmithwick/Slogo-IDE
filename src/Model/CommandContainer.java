package Model;

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
    private final Map<Class, Class<?>[]> parametersMap = new HashMap<>();
    private final Map<String, Class> inputMap = new HashMap<>();
    private final ObservableList<String> commandStringList = FXCollections.observableArrayList();
    private SimpleObjectProperty<ResourceBundle> resourceBundle = new SimpleObjectProperty<>();


    public CommandContainer(ResourceBundle resourceBundle) {
        addListeners();
        setResourceBundle(resourceBundle);
        addToCommandStringList("Model.Forward", "Model.Backward");
    }

    private static Class<?> getClassForname(String className) {
        try {
            return Class.forName("Model." + className);
        } catch (Exception e) {
            return Forward.class;
        }
    }

    private void addListeners() {
        resourceBundle.addListener((ov, oldVal, newVal) ->
                modifyInputMap());
        commandStringList.addListener((ListChangeListener<String>) c -> {
            modifyParametersMap();
        });
    }

    private void modifyInputMap() {
        inputMap.clear();
        for (String key : resourceBundle.get().keySet()) {
            String val = resourceBundle.get().getString(key);
            String[] valArray = val.split("|");
            Class<?> commandClass = getClassForname(key);
            for (String commandInput : valArray) {
                inputMap.put(commandInput, commandClass);
            }
        }
    }

    private void modifyParametersMap() {
        parametersMap.clear();
        for (String commandString : commandStringList) {
            Class theClass = getClassForname(commandString);
            Class<?>[] parameters = getParametersForClass(theClass);
            parametersMap.put(theClass, parameters);
        }
    }

    private Class<?>[] getParametersForClass(Class theClass) {
        Constructor[] allConstructors = theClass.getDeclaredConstructors();
        return allConstructors[0].getParameterTypes();
    }

    public void addToCommandStringList(String... commands) {
        commandStringList.addAll(commands);
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle.set(resourceBundle);
    }

}
