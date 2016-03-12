package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import observables.MapObservable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
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
	
    private final Map<Integer, String> variableNames = new HashMap<>();
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

    @Override
    protected int getNumChildrenRequired() {
        return 3;
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
        MapObservable<String, Variable> treeVariables = tree.getVariables();
        Predicate<Entry<String, Variable>> notAlreadyVariable = (e) -> (!isAlreadyVariable(tree, e.getKey()));
        variableMap.entrySet().parallelStream().filter(notAlreadyVariable)
                .forEach(e -> treeVariables.put(e.getKey(), e.getValue()));
        addChildrenToUserCommand(userCommand, tree);
        variableMap.entrySet().parallelStream().filter(notAlreadyVariable)
                .map(Entry::getKey).forEach(treeVariables::remove);
        return userCommand;
    }


    private boolean isAlreadyVariable(ExpressionTree tree, String value) {
        return tree.getVariables().containsKey(value);
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
        return variableNames.keySet().parallelStream()
                .sorted().map(variableNames::get).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getVariableList().toString();
    }
}
