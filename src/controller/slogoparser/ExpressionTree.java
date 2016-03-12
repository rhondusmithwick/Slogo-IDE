package controller.slogoparser;

import maps.MapContainer;
import javafx.application.Platform;
import main.GlobalProperties;
import maps.IndexMap;
import model.treenode.ConstantNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import model.turtle.TurtleManager;
import model.usercontrol.MakeUserInstruction;
import model.usercontrol.Variable;
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
	
	private static final String START_COMMANDS = "ListStart";
	private static final String END_COMMANDS = "ListEnd";
	private static final String REPCOUNT_COMMAND = ":repcount";
	private static final String COMMANDS_LIST = "model/commandLocations";

    private final ResourceBundle commandLocations;

    private final List<TreeNode> rootList;

    private final MapContainer<Variable> variables;
    private final MapContainer<MakeUserInstruction> definedCommands;
    private final ObjectObservable<String> backgroundColor;

    private final IndexMap imageMap;
    private final IndexMap colorMap;

    private final TurtleManager turtleManager;
    
    private Queue<Entry<String, String>> parsedText;
    
    public ExpressionTree(TurtleManager turtleManager, MapContainer<Variable> variables, MapContainer<MakeUserInstruction> definedCommands,
                          GlobalProperties properties, Queue<Entry<String, String>> parsedText) {
    	this.commandLocations = ResourceBundle.getBundle(COMMANDS_LIST);
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
        Platform.runLater(variables::modifyString);
    }

    public TreeNode createRoot() {
        TreeNode root = createNode();
        createSubTree(root);
        return root;
    }
    
    public TurtleManager getTurtleManager() {
        return turtleManager;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, rootList.size()).forEach(i ->
                sb.append(i).append("th Node:\n").append(rootList.get(i)).append("\n")
        );
        return sb.toString();
    }

    public List<TreeNode> getCommandsFromList() {
        List<TreeNode> myRoots = new LinkedList<>();
        if (parsedText.peek().getKey().equals(START_COMMANDS)) {
            parsedText.poll();
            while (!parsedText.peek().getKey().equals(END_COMMANDS)) {
                TreeNode root = createRoot();
                myRoots.add(root);
            }
            parsedText.poll();
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
    
    public MapContainer<MakeUserInstruction> getDefinedCommands() {
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

    public Queue<Entry<String, String>> getParsedText() {
        return parsedText;
    }

    public Turtle getMyTurtle() {
        return turtleManager.get(1);
    }

    public MapContainer<Variable> getVariables() {
        return variables;
    }

    public void setParsedText(Queue<Entry<String, String>> parsedText) {
        this.parsedText = parsedText;
    }
    
    private List<TreeNode> createRootList() {
        List<TreeNode> rootList = new LinkedList<>();
        while (inBounds()) {
            TreeNode root = createRoot();
            rootList.add(root);
        }
        return rootList;
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
        return inBounds() && root.needsMoreChildren();
    }

    private boolean inBounds() {
        return !parsedText.isEmpty();
    }

    private boolean isConstant(String className) {
        return className.equals("Constant");
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
        } else if (variables.contains(curr.getValue())) {
            n = variableHandle(curr);
        } else if (definedCommands.contains(curr.getValue())) {
            n = definedCommands.get(curr.getValue()).getUserCommandNode(this);
        } else {
            n = createNodeInstance(curr.getKey());
        }
        return n;
    }

    private TreeNode variableHandle(Entry<String, String> curr) {
        if (curr.getValue().equals(REPCOUNT_COMMAND)) {
            return variables.get(REPCOUNT_COMMAND);
        }
        return variables.get(curr.getValue());
    }
    
}
