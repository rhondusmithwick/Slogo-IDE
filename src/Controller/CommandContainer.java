package Controller;

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

    private final ObservableList<String> commandStringList = FXCollections.observableArrayList();

    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    public CommandContainer() {
        addListeners();
        addToCommandStringList("Forward", "Backward", "PenDown", "PenUp", "SetPenColor");
    }

    private void addListeners() {
        commandStringList.addListener((ListChangeListener<String>) c ->
                modifyParametersMap());
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
            return null;
        }
    }

    void addToCommandStringList(String... commands) {
        commandStringList.addAll(commands);
    }

    Map<Class<?>, Class<?>[]> getParametersMap() {
        return parametersMap;
    }

}
