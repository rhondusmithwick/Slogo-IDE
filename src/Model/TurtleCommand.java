package Model;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
abstract class TurtleCommand extends CommandNode {

    Turtle getTurtle() {
        return (Turtle) getChildren().get(0);
    }

    @Override
    public boolean hasTurtle() {
        return true;
    }

}
