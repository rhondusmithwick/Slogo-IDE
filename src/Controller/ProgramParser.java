package Controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class ProgramParser {
    private final List<Entry<String, Pattern>> mySymbols;

    private final String WHITESPACE = "\\p{Space}";

    public ProgramParser(String... bundles) {
        mySymbols = new ArrayList<>();
        for (String bundle : bundles) {
            addPatterns(bundle);
        }
    }

    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    public String getSymbol(String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }

    private boolean match(String text, Pattern regex) {
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
                commandQueue.add(new SimpleEntry<>(symbol, s));
                System.out.println(String.format("%s : %s", s, symbol));
            }
        }
        System.out.println(commandQueue);
        return commandQueue;
    }

}