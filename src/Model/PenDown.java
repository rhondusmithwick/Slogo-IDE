package Model;

/**
 * Created by rhondusmithwick on 2/24/16.
 *
 * @author Rhondu Smithwick
 */
public class PenDown extends PenCommand {

    public double execute() {
        return changePen(true);
    }
}
