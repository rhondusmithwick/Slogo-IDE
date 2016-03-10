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

/**
 * Created by rhondusmithwick on 3/6/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeUserInstruction extends CommandNode {
    private final Map<Integer, String> variableNames = new HashMap<>();
    private final Map<String, Variable> variableMap = new HashMap<>();
    private final Queue<Entry<String, String>> myComamnds = new LinkedList<>();
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
        if (parsedText.peek().getKey().equals("ListStart")) {
            parsedText.poll();
            int index = 0;
            while (true) {
                if (parsedText.peek().getKey().equals("ListEnd")) {
                    parsedText.poll();
                    break;
                }
                Entry<String, String> curr = parsedText.poll();
                putInMap(index, curr.getValue());
                index++;
            }
        }
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        Queue<Entry<String, String>> parsedText = tree.getParsedText();
        String name = parsedText.poll().getValue();
        tree.getDefinedCommands().put(name, this);
        makeVariables(parsedText);
        makeCommands(parsedText);
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
    private void  makeCommands(Queue<Entry<String, String>> parsedText) {
        if (parsedText.peek().getKey().equals("ListStart")) {
            int numEnds = 1;
            myComamnds.add(parsedText.poll());
            while (true) {
                if (numEnds == 0) break;
                if (parsedText.peek().getKey().equals("ListEnd")) {
                    numEnds--;
                }
                if (parsedText.peek().getKey().equals("ListStart")) {
                    numEnds++;
                }
                myComamnds.add(parsedText.poll());
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
        Queue<Entry<String, String>> myCommandsCopy = new LinkedList<>(myComamnds);
        Queue<Entry<String, String>> oldParsed = tree.getParsedText();
        tree.setParsedText(myCommandsCopy);
        List<TreeNode> commandRoots = tree.getCommandsFromList();
        tree.setParsedText(oldParsed);
        commandRoots.stream().forEach(userCommand::addChild);
    }
}
