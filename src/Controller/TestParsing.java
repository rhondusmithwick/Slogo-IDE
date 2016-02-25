package Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TestParsing {

    public static void main(String[] args) {
        ProgramParser lang = new ProgramParser("languages/English", "languages/Syntax");
        CommandContainer commandContainer = new CommandContainer();
        String userInput = "fd rt 90 BACK 30 Left 20";
        List<Entry<String, String>> parsingResult = lang.parseText(userInput);
        for (Entry<String, String> entry : parsingResult) {
            if (commandContainer.getCommandStringList().contains(entry.getKey())) {
                Class<?> theClass = commandContainer.getClass(entry.getKey());
                System.out.printf("The class was %s and the parameters are %s \n", theClass, Arrays.toString(CommandContainer.getParametersForClass(theClass)));
            }
        }
    }
}
