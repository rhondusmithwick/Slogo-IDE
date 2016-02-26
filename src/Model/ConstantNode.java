package Model;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public class ConstantNode implements TreeNode {

    private final double value;

    public ConstantNode(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

}
