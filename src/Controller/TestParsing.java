package Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TestParsing {

    public static void main(String[] args) {
        ProgramParser lang = new ProgramParser("languages/English", "languages/Syntax");
        CommandContainer commandContainer = new CommandContainer();
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        List<Entry<String, String>> commandQueue = lang.parseText(userInput);
        Map<Class<?>, Class<?>[]> parametersMap = commandContainer.getParametersMap();
        Map<String, Class<?>> classMap = commandContainer.getClassMap();
        for (Entry<String, String> entry : commandQueue) {
            Class<?> theClass = classMap.get(entry.getKey());
            if (parametersMap.containsKey(theClass)) {
                System.out.printf("The class was %s and the parameters are %s \n", theClass, Arrays.toString(parametersMap.get(theClass)));
            }
        }
    }
}
