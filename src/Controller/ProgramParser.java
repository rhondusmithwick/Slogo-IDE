package Controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class ProgramParser {
    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private final List<Entry<String, Pattern>> mySymbols;

    private final String WHITESPACE = "\\p{Space}";

    public ProgramParser() {
        mySymbols = new ArrayList<>();
    }

    // adds the given resource file to this language's recognized types
    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    // returns the language's type associated with the given text if one exists 
    public String getSymbol(String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }

    // returns true if the given text matches the given regular expression pattern
    private boolean match(String text, Pattern regex) {
        // THIS IS THE KEY LINE
        return regex.matcher(text).matches();
    }

    public List<Entry<String, String>> parseText(String input) {
        String[] text = input.split(WHITESPACE);
        List<Entry<String, String>> commandQueue = new ArrayList<>();
        for (String s : text) {
            if (s.trim().length() > 0) {
                String symbol = getSymbol(s);
//                if (symbol.equals("Error")) {
//                    throw new Exception();
//                }
                commandQueue.add(new SimpleEntry<>(getSymbol(s), s));
                System.out.println(String.format("%s : %s", s, getSymbol(s)));
            }
        }
        System.out.println();
        return commandQueue;
    }

}
