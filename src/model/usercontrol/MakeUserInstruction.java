package model.usercontrol;

import maps.MapContainer;
import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by rhondusmithwick on 3/6/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeUserInstruction extends CommandNode {
	
	private static final String START_COMMANDS = "ListStart";
	private static final String END_COMMANDS = "ListEnd";
	
    private final Map<Integer, String> variableNames = new TreeMap<>();
    private final Map<String, Variable> variableMap = new HashMap<>();
    private final Queue<Entry<String, String>> myCommands = new LinkedList<>();
    private double value = 0;

    @Override
    protected double execute() {
        return value;
    }

    private void putInMap(int index, String name) {
        variableNames.put(index, name);
        variableMap.put(name, new UserCommandVariable());
    }

    private int numVariables() {
        return variableMap.size();
    }

    private void setValue(int index, String value) {
        String variableName = variableNames.get(index);
        variableMap.get(variableName).setValue(Double.valueOf(value));
    }

    private void makeVariables(Queue<Entry<String, String>> parsedText) {
        if (parsedText.peek().getKey().equals(START_COMMANDS)) {
            parsedText.poll();
            int index = 0;
            while (!parsedText.peek().getKey().equals(END_COMMANDS)) {
                Entry<String, String> curr = parsedText.poll();
                putInMap(index, curr.getValue());
                index++;
            }
            parsedText.poll();
        }
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        Queue<Entry<String, String>> parsedText = tree.getParsedText();
        String name = parsedText.poll().getValue();
        makeVariables(parsedText);
        makeCommands(parsedText);
        tree.getDefinedCommands().put(name, this);
        value = 1;
    }

    public UserCommand getUserCommandNode(ExpressionTree tree) {
        UserCommand userCommand = new UserCommand();
        setValuesForCommand(tree);
        MapContainer<String, Variable> treeVariables = tree.getVariables();
        Set<String> alreadyVariables = getAlreadyInTreeVariables(tree);
        Predicate<Entry<String, Variable>> inAlreadyVariables = (e) -> (alreadyVariables.contains(e.getKey()));
        variableMap.entrySet().parallelStream().filter(inAlreadyVariables.negate())
                .forEach(treeVariables::putEntry);
        addChildrenToUserCommand(userCommand, tree);
        variableMap.entrySet().parallelStream().filter(inAlreadyVariables.negate()).map(Entry::getKey)
                .forEach(treeVariables::remove);
        return userCommand;
    }

    private Set<String> getAlreadyInTreeVariables(ExpressionTree tree) {
        Predicate<String> isAlreadyVariable = (k) -> (isAlreadyVariable(tree, k));
        return variableMap.keySet().parallelStream().filter(isAlreadyVariable)
                .collect(Collectors.toSet());
    }

    private boolean isAlreadyVariable(ExpressionTree tree, String value) {
        return tree.getVariables().contains(value);
    }

    private void makeCommands(Queue<Entry<String, String>> parsedText) {
        if (parsedText.peek().getKey().equals(START_COMMANDS)) {
            int numEnds = 1;
            myCommands.add(parsedText.poll());
            while (numEnds != 0) {
                if (parsedText.peek().getKey().equals(END_COMMANDS)) {
                    numEnds--;
                }
                if (parsedText.peek().getKey().equals(START_COMMANDS)) {
                    numEnds++;
                }
                myCommands.add(parsedText.poll());
            }
        }
    }

    public void setValuesForCommand(ExpressionTree tree) {
        int numAssigned = 0;
        while (numAssigned < numVariables()) {
            Entry<String, String> curr = tree.getParsedText().poll();
            if (!isAlreadyVariable(tree, curr.getValue())) {
                setValue(numAssigned, curr.getValue());
            }
            numAssigned++;
        }
    }

    private void addChildrenToUserCommand(UserCommand userCommand, ExpressionTree tree) {
        Queue<Entry<String, String>> myCommandsCopy = new LinkedList<>(myCommands);
        Queue<Entry<String, String>> oldParsed = tree.getParsedText();
        tree.setParsedText(myCommandsCopy);
        List<TreeNode> commandRoots = tree.getCommandsFromList();
        tree.setParsedText(oldParsed);
        commandRoots.stream().forEach(userCommand::addChild);
    }

    public List<String> getVariableList() {
        return variableNames.entrySet().stream()
                .map(Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getVariableList().toString();
    }
}
