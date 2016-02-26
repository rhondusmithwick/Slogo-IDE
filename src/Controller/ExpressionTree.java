package Controller;

import Model.ConstantNode;
import Model.TreeNode;
import Model.Turtle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public class ExpressionTree {

    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    private final List<Entry<String, String>> parsedText;

    private final List<TreeNode> rootList;

    private final Turtle myTurtle;

    private int currIndex = 0;


    public ExpressionTree(Turtle myTurtle, List<Entry<String, String>> parsedText) {
        this.myTurtle = myTurtle;
        this.parsedText = parsedText;
        rootList = createRootList();
    }

    public void executeAll() {
        System.out.println(rootList);
        rootList.stream().forEach(TreeNode::getValue);
    }

    private List<TreeNode> createRootList() {
        List<TreeNode> rootList = new LinkedList<>();
        while (inBounds()) {
            rootList.add(createRoot());
        }
        return rootList;
    }

    private TreeNode createRoot() {
        String className = parsedText.get(currIndex).getKey();
        TreeNode root = createNodeInstance(className);
        currIndex++;
        TreeNode currNode = root;
        while (stillRoot(className)) {
            className = parsedText.get(currIndex).getKey();
            TreeNode n = createNode(className);
            currNode.addChild(n);
            currNode = n;
            currIndex++;
        }
        return root;
    }

    private TreeNode createNode(String className) {
        TreeNode n;
        if (isConstant(className)) {
            Double constant = Double.parseDouble(parsedText.get(currIndex).getValue());
            n = new ConstantNode(constant);
        } else {
            n = createNodeInstance(className);
        }
        return n;
    }

    private TreeNode createNodeInstance(String className) {
//        Class<?> theClass = getClassForName(className);
        TreeNode n;
        try {
            Class<?> theClass = Class.forName(commandLocations.getString(className));
            n = (TreeNode) theClass.newInstance();
        } catch (Exception e) {
            n = new ConstantNode(0);
        }
        if (n.hasTurtle()) {
            n.addChild(myTurtle);
        }
        return n;
    }

    private boolean stillRoot(String className) {
        return inBounds() && !isConstant(className);
    }

    private boolean inBounds() {
        return (currIndex < parsedText.size());
    }

    private boolean isConstant(String className) {
        return (className.equals("Constant"));
    }

//    public Class<?> getClassForName(String className) {
//        try {
//            return Class.forName(commandLocations.getString(className));
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
