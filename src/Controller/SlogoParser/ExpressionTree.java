package Controller.SlogoParser;

import Observables.MapObservable;
import Model.TreeNode.ConstantNode;
import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;
import Model.UserControl.MakeVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public class ExpressionTree {

    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    private final Queue<Entry<String, String>> parsedText;

    private final List<TreeNode> rootList;

    private final MapObservable<String, TreeNode> variables;
    private final Turtle myTurtle;

    public ExpressionTree(Turtle myTurtle, MapObservable<String, TreeNode> variables, Queue<Entry<String, String>> parsedText) {
        this.myTurtle = myTurtle;
        this.variables = variables;
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
        Entry<String, String> curr = parsedText.poll();
        TreeNode root = createNode(curr);
        createSubTree(root);
        return root;
    }

    private void createSubTree(TreeNode root) {
        while (stillRoot(root)) {
            TreeNode n = createSingleNode();
            root.addChild(n);
            createSubTree(n);
        }
    }

    private TreeNode createSingleNode() {
        Entry<String, String> curr = parsedText.poll();
        TreeNode n;
        if (variables.containsKey(curr.getValue())) {
            n = variables.get(curr.getValue());
        } else {
            n = createNode(curr);
        }
        return n;
    }

    private TreeNode createNode(Entry<String, String> curr) {
        TreeNode n;
        if (isConstant(curr.getKey())) {
            String doubleText = curr.getValue();
            Double constant = Double.parseDouble(doubleText);
            n = new ConstantNode(constant);
        } else {
            n = createNodeInstance(curr.getKey());
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
        addTurtleIfShould(n);
        addVariableIfShould(n);
        return n;
    }

    private boolean stillRoot(TreeNode root) {
        return inBounds()
                && (root.needsMoreChildren());
    }

    private boolean inBounds() {
        return !parsedText.isEmpty();
    }

    private boolean isConstant(String className) {
        return (className.equals("Constant"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, rootList.size()).forEach(i ->
                sb.append(i).append("th Node:\n").append(rootList.get(i)).append("\n")
        );
        return sb.toString();
    }

    private void addTurtleIfShould(TreeNode n) {
        if (n instanceof TurtleCommandNode) {
            TurtleCommandNode turtleNode = (TurtleCommandNode) n;
            turtleNode.setTurtle(myTurtle);
        }
    }

    private void addVariableIfShould(TreeNode n) {
        if (n instanceof MakeVariable) {
            Entry<String, String> curr = parsedText.poll();
            variables.put(curr.getValue(), n);
            createSubTree(n);
        }
    }


}
