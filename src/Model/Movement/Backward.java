package Model.Movement;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Backward extends Movement {

    @Override
    public double execute() {
        return move(-1);
    }

}
