package model.usercontrol;

import model.treenode.CommandNode;
import model.treenode.TreeNode;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
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

    public void putInMap(int index, String name) {
        variableNames.put(index, name);
        variableMap.put(name, new UserCommandVariable());
    }


    public int numVariables() {
        return variableMap.size();
    }

    public void setValue(int index, String value) {
        String variableName = variableNames.get(index);
        variableMap.get(variableName).setValue(Double.valueOf(value));
    }

    public Map<String, MakeVariable> getVariableMap() {
        return variableMap;
    }

    public void makeVariables(Queue<Entry<String, String>> parsedText) {
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
}
