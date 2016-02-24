package Controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


public class Main {

    // given some text, prints results of parsing it using the given language
    private static List<Entry<String, String>> parseText(ProgramParser lang, String[] text) {
        List<Entry<String, String>> commandQueue = new ArrayList<>();
        for (String s : text) {
            if (s.trim().length() > 0) {
                commandQueue.add(new AbstractMap.SimpleEntry<>(lang.getSymbol(s), s));
                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            }
        }
        System.out.println();
        return commandQueue;
    }

    public static void main(String[] args) {
        final String WHITESPACE = "\\p{Space}";
        ProgramParser lang = new ProgramParser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        List<Entry<String, String>> commandQueue = parseText(lang, userInput.split(WHITESPACE));
        System.out.println(commandQueue);
    }
}
