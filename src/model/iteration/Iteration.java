//This entire file is part of my masterpiece. However, I also refactored the For, DoTimes, and Repeat classes. 
//Jonathan Ma
/*
 * This code is used to create the iteration-based commands, including For, DoTimes, and Repeat. 
 * I felt as though the original makeVariable() method for For and Dotimes was extremely redundant
 * and so I decided to put it in the abstract class, which significantly reduced the size of both
 * subclasses. I also thought that having a private int variable in each class to keep track of the 
 * number of iterations was also redundant, so I moved that into this abstract class as well. I think
 * this class is well designed since it really consolidated a lot of repetitive code. It also makes 
 * some good use of lambda expressions. 
 */

package model.iteration;

import controller.slogoparser.ExpressionTree;
import model.treenode.CommandNode;
import model.treenode.TreeNode;
import model.usercontrol.Variable;

import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.stream.IntStream;

/**
 * Created by rhondusmithwick on 3/11/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class Iteration extends CommandNode {
	
	private static final int DOTIMES = 0;
	private static final int FOR = 1;
	
	private int endValue;
	private int startValue;
	private int increment;
    private Integer numTimes;
    
    private final Variable variable = new Variable();
    private String variableName;
    private Double value = null;

    @Override
    protected double execute() {
        variable.setValue(startValue);
        if (numTimes == null) {
            setNumTimes();
        }
        IntStream.range(0, numTimes).forEach(i -> doIteration());
        return (value != null) ? value : 0;
    }

    private void doIteration() {
        double newValue = variable.getValue() + increment;
        variable.setValue(newValue);
        value = runChildren();
    }

    private void setNumTimes() {
    	this.numTimes = (endValue - startValue) / increment;
    }
    
    protected void createIteration(Queue<Entry<String, String>> parsedText, int iterFunction) {
        if (parsedText.peek().getKey().equals("ListStart")) {
            parsedText.poll();
            while (!parsedText.peek().getKey().equals("ListEnd")) {
                if (parsedText.peek().getKey().equals("Variable")) {
                    setVariableName(parsedText.poll().getValue());
                } else {
                	if (iterFunction == FOR) {
                		setStartValue(Integer.parseInt(parsedText.poll().getValue()));
                		endValue = Integer.parseInt(parsedText.poll().getValue());
                		setIncrement(Integer.parseInt(parsedText.poll().getValue()));
                	} else if (iterFunction == DOTIMES) {
                		setStartValue(1);
                		endValue = Integer.parseInt(parsedText.poll().getValue());
                		setIncrement(1);
                	}
                }
            }
            parsedText.poll();
        }
    }
    
    @Override
    public void handleSpecific(ExpressionTree tree) {
        tree.getVariables().put(variableName, variable);
        List<TreeNode> nRoots = tree.getCommandsFromList();
        getChildren().addAll(nRoots);
        tree.getVariables().remove(variableName);
    }

    protected void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    protected void setIncrement(int increment) {
        this.increment = increment;
    }

    protected void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    @Override
    protected int getNumChildrenRequired() {
        return 2;
    }
}
