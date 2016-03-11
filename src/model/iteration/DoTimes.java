package model.iteration;

import java.util.Queue;
import java.util.Map.Entry;

import controller.slogoparser.ExpressionTree;

public class DoTimes extends Iteration {

	private int doTimesNumTimes = 0;

    public void handleSpecific(ExpressionTree tree) {
		setStartValue(1);
		setIncrement(1);
    	makeVariable(tree.getParsedText());
		super.handleSpecific(tree);
    }
    
    private void makeVariable(Queue<Entry<String, String>> parsedText) {
    	if (parsedText.peek().getKey().equals("ListStart")) {
    		parsedText.poll();
    		while (true) {
    			if (parsedText.peek().getKey().equals("ListEnd")) {
    				parsedText.poll();
    				break;
    			} else if (parsedText.peek().getKey().equals("Variable")) {
    				setVariableName(parsedText.poll().getValue());
    			} else {
    				doTimesNumTimes = Integer.parseInt(parsedText.poll().getValue());
    			}
    		}
    	}
    }

	@Override
	protected int makeNumTimes() {
		return doTimesNumTimes;
	}

}
