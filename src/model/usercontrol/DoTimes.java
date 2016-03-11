package model.usercontrol;

import java.util.Queue;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Map.Entry;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

public class DoTimes extends CommandNode {
	
	private Variable variable = new Variable();
	private String variableName;
	private int numTimes;
	private double value;

    @Override
    protected double execute() {
    	variable.setValue(0);
    	IntStream.range(0, numTimes).forEach(i -> doIteration());
    	return value;
    }
    
    private void doIteration() {
    	variable.setValue(variable.getValue() + 1);
    	value = runChildren();
    }
    
    public void handleSpecific(ExpressionTree tree) {
    	Queue<Entry<String, String>> parsedText = tree.getParsedText();
    	makeVariable(parsedText);
    	List<TreeNode> commands = tree.getCommandsFromList();
    	getChildren().addAll(commands);
    	tree.getVariables().put(variableName, variable);
    }
    
    private void makeVariable(Queue<Entry<String, String>> parsedText) {
    	if (parsedText.peek().getKey().equals("ListStart")) {
    		parsedText.poll();
    		while (true) {
    			if (parsedText.peek().getKey().equals("ListEnd")) {
    				parsedText.poll();
    				break;
    			} else if (parsedText.peek().getKey().equals("Variable")) {
    				variableName = parsedText.poll().getValue();
    			} else {
    				numTimes = Integer.parseInt(parsedText.poll().getValue());
    			}
    		}
    	}
    }
    
    @Override
    protected int getNumChildrenRequired() {
    	return 2;
    }

}
