package controller.slogoparser;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SlogoParser {

    private final Map<String, Pattern> mySymbols;

    private final String ERROR = "NO MATCH";

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
//        mySymbols.remove("Command");
    }

    private String getSymbol(String text) {
        Predicate<Entry<String, Pattern>> matched = (e) -> match(text, e.getValue());
        List<String> symbols = mySymbols.entrySet()
                .parallelStream().filter(matched)
                .map(Entry::getKey)
                .collect(Collectors.toList());
        if (symbols.size() == 0) return ERROR;
        if (symbols.size() == 1) return symbols.get(0);
        Predicate<String> getRidOfCommand = (e -> !e.equals("Command"));
        return symbols.parallelStream()
                .filter(getRidOfCommand)
                .findFirst().orElse(ERROR);
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public Queue<Entry<String, String>> parseText(String input) {
        Predicate<Entry<String, String>> containsError = (e) -> Objects.equals(e.getKey(), ERROR);
        Queue<Entry<String, String>> parsedText = createParsedText(input);
        if (parsedText.stream().anyMatch(containsError)) {
            return null;
        } else {
            return parsedText;
        }
    }

    private Queue<Entry<String, String>> createParsedText(String input) {
        String WHITESPACE = "\\p{Space}";
        List<String> text = Arrays.asList(input.split(WHITESPACE));
        Predicate<String> notEmpty = (s) -> (s.trim().length() > 0);
        Queue<Entry<String, String>> answer = new LinkedList<>();
        return text.stream().filter(notEmpty)
                .map(s -> new SimpleEntry<>(getSymbol(s), s))
                .collect(Collectors.toCollection(() -> answer));
    }
}
