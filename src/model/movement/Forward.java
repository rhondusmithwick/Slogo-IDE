package model.movement;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Forward extends Movement {

    @Override
    public double execute() {
        return move(1);
    }

}
