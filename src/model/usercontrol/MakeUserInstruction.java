package model.usercontrol;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import observables.MapObservable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 3/6/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeUserInstruction extends CommandNode {
    private final Map<Integer, String> variableNames = new HashMap<>();
    private final Map<String, MakeVariable> variableMap = new HashMap<>();
    private boolean hasRun = false;

    @Override
    protected double execute() {
        if (!hasRun) {
            hasRun = true;
            return 1.0;
        }
        return getChildren().stream().map(TreeNode::getValue).reduce((a, b) -> b).orElse(null);
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
        String name = tree.getParsedText().poll().getValue();
        tree.getDefinedCommands().put(name, this);
        makeVariables(tree.getParsedText());
        MapObservable<String, TreeNode> treeVariables = tree.getVariables();
        treeVariables.putAll(variableMap);
        List<TreeNode> myRoots = tree.getCommandsFromList();
        myRoots.stream().forEach(this::addChild);
        variableMap.keySet().parallelStream().forEach(treeVariables::remove);
    }

    public void setValuesForCommand(ExpressionTree tree) {
        int numAssigned = 0;
        while (numAssigned < numVariables()) {
            Entry<String, String> curr = tree.getParsedText().poll();
            setValue(numAssigned, curr.getValue());
            numAssigned++;
        }
    }
}
