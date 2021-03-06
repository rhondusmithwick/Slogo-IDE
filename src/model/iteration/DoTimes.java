package model.iteration;

import controller.slogoparser.ExpressionTree;

import java.util.Map.Entry;
import java.util.Queue;

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
            while (!parsedText.peek().getKey().equals("ListEnd")) {
                if (parsedText.peek().getKey().equals("Variable")) {
                    setVariableName(parsedText.poll().getValue());
                } else {
                    doTimesNumTimes = Integer.parseInt(parsedText.poll().getValue());
                }
            }
            parsedText.poll();
        }
    }

    @Override
    protected int makeNumTimes() {
        return doTimesNumTimes;
    }

}
