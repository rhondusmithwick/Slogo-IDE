package Model;

import javafx.scene.paint.Color;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class SetPenColor extends TurtleCommand {

    private final Color penColor;

    public SetPenColor(Turtle myTurtle, Color penColor) {
        super(myTurtle);
        this.penColor = penColor;
    }

    @Override
    public double execute() {
        try {
            getTurtle().getTurtleProperties().setPenColor(penColor);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}