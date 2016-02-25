package Model;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class SetPenColor extends TurtleCommand {

    private final String penColor;

    public SetPenColor(Turtle myTurtle, String penColor) {
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
