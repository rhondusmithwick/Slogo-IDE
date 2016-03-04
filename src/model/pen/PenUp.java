package model.pen;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class PenUp extends PenCommand {

    public double execute() {
        return changePen(false);
    }
}
