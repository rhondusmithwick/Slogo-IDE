package Model;

import java.util.List;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public interface TreeNode {

    default boolean hasTurtle() {
        return false;
    }

    default double getValue() {
        return 0.0;
    }

    default boolean addChild(TreeNode n) {
        return false;
    }

    default List<TreeNode> getChildren() {
        return null;
    }

}
