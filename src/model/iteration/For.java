package model.iteration;

import controller.slogoparser.ExpressionTree;

import java.util.Map.Entry;
import java.util.Queue;

public class For extends Iteration {

    private int endValue;

    @Override
    public void handleSpecific(ExpressionTree tree) {
        makeVariable(tree.getParsedText());
        super.handleSpecific(tree);
    }

    @Override
    protected int makeNumTimes() {
        return (endValue - getStartValue()) / getIncrement();
    }

    private void makeVariable(Queue<Entry<String, String>> parsedText) {
        if (parsedText.peek().getKey().equals("ListStart")) {
            parsedText.poll();
            while (!parsedText.peek().getKey().equals("ListEnd")) {
                if (parsedText.peek().getKey().equals("Variable")) {
                    setVariableName(parsedText.poll().getValue());
                } else {
                    setStartValue(Integer.parseInt(parsedText.poll().getValue()));
                    endValue = Integer.parseInt(parsedText.poll().getValue());
                    setIncrement(Integer.parseInt(parsedText.poll().getValue()));
                }
            }
            parsedText.poll();
        }
    }

}
