package model.usercontrol;

import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.stream.IntStream;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;

public class For extends CommandNode {
	
	private Variable variable = new Variable();
	private String variableName;
	private int endValue;
	private int startValue;
	private int increment;
	private double value;

    @Override
    protected double execute() {
    	variable.setValue(startValue);
    	int numTimes = endValue/increment;
    	IntStream.range(0, numTimes).forEach(i -> doIteration());
    	return value;
    }
    
    private void doIteration() {
    	variable.setValue(variable.getValue() + increment);
    	value = runChildren();
    }
    
    public void handleSpecific(ExpressionTree tree) {
    	Queue<Entry<String, String>> parsedText = tree.getParsedText();
    	makeVariable(parsedText);
    	tree.getVariables().put(variableName, variable);
    	List<TreeNode> commands = tree.getCommandsFromList();
    	getChildren().addAll(commands);
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
    				startValue = Integer.parseInt(parsedText.poll().getValue());
    				endValue = Integer.parseInt(parsedText.poll().getValue());
    				increment = Integer.parseInt(parsedText.poll().getValue());
    			}
    		}
    	}
    }
    
    @Override
    protected int getNumChildrenRequired() {
    	return 2;
    }

}
