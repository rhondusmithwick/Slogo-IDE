package model.usercontrol;

import model.treenode.TreeNode;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public class UserCommand extends TreeNode {
	
    @Override
    public double getValue() {
        return runChildren();
    }
}
