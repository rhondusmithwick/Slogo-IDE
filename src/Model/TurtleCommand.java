package Model;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
abstract class TurtleCommand implements Command {
    private final Turtle myTurtle;

    TurtleCommand(Turtle myTurtle) {
        this.myTurtle = myTurtle;
    }

    Turtle getTurtle() {
        return myTurtle;
    }

}
