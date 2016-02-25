package Controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;


public class TestParsing {

    public static void main(String[] args) {
        ProgramParser lang = new ProgramParser("languages/English", "languages/Syntax");
        CommandContainer commandContainer = new CommandContainer();
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        List<Entry<String, String>> commandQueue = lang.parseText(userInput);
        System.out.println(commandQueue);
        for (Entry<Class<?>, Class<?>[]> entry :commandContainer.getParametersMap().entrySet()) {
            System.out.printf("The class was %s and the %s were the parameters\n", entry.getKey(), Arrays.toString(entry.getValue()));
        }
    }
}
