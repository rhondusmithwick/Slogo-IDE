package Controller.SlogoParser;

import Model.TreeNode.ConstantNode;
import Model.TreeNode.TreeNode;
import Model.TreeNode.TurtleCommandNode;
import Model.Turtle.Turtle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.DelayQueue;
import java.util.stream.IntStream;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public class ExpressionTree {

    private final ResourceBundle commandLocations = ResourceBundle.getBundle("Model/commandLocations");

    private final Queue<Entry<String, String>> parsedText;

    private final DelayQueue<TreeNode> rootList;

    private final Turtle myTurtle;

    public ExpressionTree(Turtle myTurtle, Queue<Entry<String, String>> parsedText) {
        this.myTurtle = myTurtle;
        this.parsedText = parsedText;
        rootList = createRootList();
    }

    public void executeAll() {
        System.out.println(this);
        while(!rootList.isEmpty()) {
            try {
                TreeNode root = rootList.take();
                root.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        rootList.stream().forEach(TreeNode::getValue);
    }

    private DelayQueue<TreeNode> createRootList() {
        DelayQueue<TreeNode> rootList = new DelayQueue<>();
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
            Entry<String, String> curr = parsedText.poll();
            TreeNode n = createNode(curr);
            root.addChild(n);
            createSubTree(n);
        }
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
        if (n instanceof TurtleCommandNode) {
            TurtleCommandNode turtleN = (TurtleCommandNode) n;
            turtleN.setTurtle(myTurtle);
        }
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
        System.out.println(className);
        return (className.equals("Constant"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<TreeNode> iter = rootList.iterator();
        int i = 0;
        while (iter.hasNext()) {
            TreeNode curr = iter.next();
            sb.append(i).append("th Node:\n").append(curr).append("\n");
            i++;
        }
        return sb.toString();
    }


}
