package controller.slogoparser;

import controller.controller.DefinedCommands;
import javafx.application.Platform;
import main.GlobalProperties;
import maps.IndexMap;
import model.treenode.ConstantNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import model.turtle.TurtleManager;
import model.usercontrol.Variable;
import observables.MapObservable;
import observables.ObjectObservable;

import java.util.Collection;
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

    private final ResourceBundle commandLocations = ResourceBundle.getBundle("model/commandLocations");

    private Queue<Entry<String, String>> parsedText;

    private final List<TreeNode> rootList;

    private final MapObservable<String, Variable> variables;
    private final DefinedCommands definedCommands;
    private final ObjectObservable<String> backgroundColor;

    private final IndexMap imageMap;
    private final IndexMap colorMap;

    private final TurtleManager turtleManager;

    public ExpressionTree(TurtleManager turtleManager, MapObservable<String, Variable> variables, DefinedCommands definedCommands,
                          GlobalProperties properties, Queue<Entry<String, String>> parsedText) {
        this.turtleManager = turtleManager;
        this.variables = variables;
        this.definedCommands = definedCommands;
        this.colorMap = properties.getColorMap();
        this.imageMap = properties.getImageMap();
        this.backgroundColor = properties.getBackgroundColor();
        this.parsedText = parsedText;
        rootList = createRootList();
    }

    public void executeAll() {
        rootList.stream().forEach(TreeNode::getValue);
        Platform.runLater(variables::modifyIfShould);
    }

    private List<TreeNode> createRootList() {
        List<TreeNode> rootList = new LinkedList<>();
        while (inBounds()) {
            TreeNode root = createRoot();
            rootList.add(root);
        }
        return rootList;
    }

    public TreeNode createRoot() {
        TreeNode root = createNode();
        createSubTree(root);
        return root;
    }

    private void createSubTree(TreeNode root) {
        while (stillRoot(root)) {
            TreeNode n = createNode();
            root.addChild(n);
            createSubTree(n);
        }
    }

    private TreeNode createNode() {
        TreeNode n;
        Entry<String, String> curr = parsedText.poll();
        if (isConstant(curr.getKey())) {
            n = getConstant(curr);
        } else if (curr.getKey().equals("Tell")) {
            Collection<Integer> IDs = turtleManager.doTell(parsedText);
            turtleManager.populateActiveTurtles(IDs);
            n = new ConstantNode(0.0);
        } else if (variables.containsKey(curr.getValue())) {
            n = variableHandle(curr);
        } else if (definedCommands.containsCommand(curr.getValue())) {
            n = definedCommands.getNode(curr.getValue()).getUserCommandNode(this);
        } else {
            n = createNodeInstance(curr.getKey());
        }
        return n;
    }

    private TreeNode variableHandle(Entry<String, String> curr) {
        if (curr.getValue().equals(":repcount")) {
            return variables.get(":repcount");
        }
        return variables.get(curr.getValue()).getConstantNode();

    }
    public TurtleManager getTurtleManager() {
        return turtleManager;
    }

    private ConstantNode getConstant(Entry<String, String> curr) {
        String doubleText = curr.getValue();
        double constant = Double.parseDouble(doubleText);
        return new ConstantNode(constant);
    }

    private TreeNode createNodeInstance(String className) {
        TreeNode n;
        try {
            Class<?> theClass = Class.forName(commandLocations.getString(className));
            n = (TreeNode) theClass.newInstance();
        } catch (Exception e) {
            n = new ConstantNode(0);
        }
        n.handleSpecific(this);
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

    public DefinedCommands getDefinedCommands() {
        return definedCommands;
    }

    public IndexMap getColorMap() {
        return colorMap;
    }

    public IndexMap getImageMap() {
        return imageMap;
    }

    public ObjectObservable<String> getBackgroundColor() {
        return backgroundColor;
    }

    public List<TreeNode> getCommandsFromList() {
        List<TreeNode> myRoots = new LinkedList<>();
        if (parsedText.peek().getKey().equals("ListStart")) {
            parsedText.poll();
            while (true) {
                if (parsedText.peek().getKey().equals("ListEnd")) {
                    parsedText.poll();
                    break;
                }
                TreeNode root = createRoot();
                myRoots.add(root);
            }
        }
        return myRoots;
    }

    public List<List<TreeNode>> getMultipleCommandsList(int children) {
        List<List<TreeNode>> myRoots = new LinkedList<>();
        for (int i = 0; i < children; i++) {
            myRoots.add(getCommandsFromList());
        }
        return myRoots;
    }

    public Queue<Entry<String, String>> getParsedText() {
        return parsedText;
    }

    public Turtle getMyTurtle() {
        return turtleManager.get(1);
    }

    public MapObservable<String, Variable> getVariables() {
        return variables;
    }

    public void setParsedText(Queue<Entry<String, String>> parsedText) {
        this.parsedText = parsedText;
    }
}
