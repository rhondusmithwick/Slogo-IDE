package Controller.SlogoParser;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class SlogoParser {
    private final Map<String, Pattern> mySymbols;

    public SlogoParser(String... bundles) {
        mySymbols = new HashMap<>();
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
            mySymbols.put(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
        mySymbols.remove("Command");
    }

    private String getSymbol(String text) {
        final String ERROR = "NO MATCH";
        Predicate<Entry<String, Pattern>> matched = (e) -> match(text, e.getValue());
        return mySymbols.entrySet()
                .stream().filter(matched).findFirst()
                .map(Entry::getKey).orElse(ERROR);
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public List<Entry<String, String>> parseText(String input) {
        String WHITESPACE = "\\p{Space}";
        List<Entry<String, String>> parsedText = new ArrayList<>();
        List<String> text = Arrays.asList(input.split(WHITESPACE));
        Predicate<String> notEmpty = (s) -> (s.trim().length() > 0);
        text.stream().filter(notEmpty).forEach(s -> addToParstedText(parsedText, s));
        return parsedText;
    }


    private void addToParstedText(List<Entry<String, String>> parsedText, String s) {
        String symbol = getSymbol(s);
//                if (symbol.equals("Error")) {
//                    throw new Exception();
//                }
        Entry<String, String> entry = new SimpleEntry<>(symbol, s);
        parsedText.add(entry);
        System.out.println(String.format("%s : %s", symbol, s));
    }
}
