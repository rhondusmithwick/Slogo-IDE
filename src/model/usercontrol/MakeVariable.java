package model.usercontrol;

/**
 * Created by rhondusmithwick on 3/8/16.
 *
 * @author Rhondu Smithwick
 */
public class MakeVariable extends Variable {

    @Override
    public double getValue() {
        if (getVal() == null) {
            double myVal = getChildren().get(0).getValue();
            setValue(myVal);
        }
        return super.getValue();
    }

    @Override
    public int getNumChildrenRequired() {
        return 1;
    }
}
