package Controller.SlogoParser;

import Model.TreeNode.ConstantNode;
import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;

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
        rootList.stream().forEach(TreeNode::getValue);
    }

    private List<TreeNode> createRootList() {
        List<TreeNode> rootList = new LinkedList<>();
        while (inBounds()) {
            TreeNode root = createRoot();
            rootList.add(root);
        }
        return rootList;
    }

    private TreeNode createRoot() {
        String className = parsedText.get(currIndex).getKey();
        TreeNode root = createNodeInstance(className);
        currIndex++;
        createSubTree(root);
        return root;
    }

    private void createSubTree(TreeNode root) {
        while (stillRoot(root)) {
            String className = parsedText.get(currIndex).getKey();
            TreeNode n = createNode(className);
            root.addChild(n);
            currIndex++;
            createSubTree(n);
        }
//        while (root.needsMoreChildren()) {
//            TreeNode currNode = root;
//            while (stillRoot(className)) {
//                className = parsedText.get(currIndex).getKey();
//                TreeNode n = createNode(className);
//                currIndex++;
//                createSubTree(className, n);
//                currNode.addChild(n);
//                currNode = n;
//            }
//        }
    }

    private TreeNode createNode(String className) {
        TreeNode n;
        if (isConstant(className)) {
            String doubleText = parsedText.get(currIndex).getValue();
            Double constant = Double.parseDouble(doubleText);
            n = new ConstantNode(constant);
        } else {
            n = createNodeInstance(className);
        }
        return n;
    }

    private TreeNode createNodeInstance(String className) {
        TreeNode n;
        try {
            Class<?> theClass = Class.forName(commandLocations.getString(className));
            n = (TreeNode) theClass.newInstance();
        } catch (Exception e) {
            n = new ConstantNode(0);
        }
        if (n instanceof TurtleCommandNode) {
            n.addChild(myTurtle);
        }
        return n;
    }

    private boolean stillRoot(TreeNode root) {
        return inBounds()
                && (root.needsMoreChildren());
    }

    private boolean inBounds() {
        return (currIndex < parsedText.size());
    }

    private boolean isConstant(String className) {
        return (className.equals("Constant"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rootList.size(); i++) {
            sb.append(i)
                    .append("th Node:\n")
                    .append(rootList.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

}
