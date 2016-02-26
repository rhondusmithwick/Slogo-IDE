package Controller;

import javafx.collections.FXCollections;
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

    private final ObservableList<String> commandStringList = createCommandsStringList();
    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    private final Map<String, Class<?>> classMap = createClassMap();

    public Class<?> getClassForName(String className) {
        try {
            return Class.forName(commandLocations.getString(className));
        } catch (Exception e) {
            return null;
        }
    }

    private ObservableList<String> createCommandsStringList() {
        return FXCollections.observableArrayList("Forward", "Backward", "PenDown", "PenUp", "SetPenColor");
    }

    private Map<String, Class<?>> createClassMap() {
        Map<String, Class<?>> classMap = new HashMap<>();
        for (String commandString : commandStringList) {
            Class theClass = getClassForName(commandString);
            classMap.put(commandString, theClass);
        }
        return classMap;
    }

    public Class<?> getClass(String className) {
        return classMap.get(className);
    }

    public ObservableList<String> getCommandStringList() {
        return commandStringList;
    }
}
