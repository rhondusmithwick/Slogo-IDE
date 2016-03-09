package controller.slogoparser;

import javafx.application.Platform;

import model.treenode.ConstantNode;
import model.treenode.TreeNode;
import model.turtle.Turtle;
import model.usercontrol.MakeUserInstruction;
import observables.MapObservable;

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

    private final Queue<Entry<String, String>> parsedText;

    private final List<TreeNode> rootList;

    private final MapObservable<String, TreeNode> variables;
    private final MapObservable<String, MakeUserInstruction> definedCommands;

    private final Turtle myTurtle;

    public ExpressionTree(Turtle myTurtle, MapObservable<String, TreeNode> variables, MapObservable<String, MakeUserInstruction> definedCommands,
                          Queue<Entry<String, String>> parsedText) {
        this.myTurtle = myTurtle;
        this.variables = variables;
        this.definedCommands = definedCommands;
        this.parsedText = parsedText;
        rootList = createRootList();
    }

    public void executeAll() {
        rootList.stream().forEach(TreeNode::getValue);
        Platform.runLater(variables::modifyIfShould);
        Platform.runLater(definedCommands::modifyIfShould);
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
        } else if (variables.containsKey(curr.getValue())) {
            n = variables.get(curr.getValue());
        } else if (definedCommands.containsKey(curr.getValue())) {
            n = definedCommands.get(curr.getValue());
            ((MakeUserInstruction) n).setValuesForCommand(this);
        } else {
            n = createNodeInstance(curr.getKey());
        }
        return n;
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

    public MapObservable<String, MakeUserInstruction> getDefinedCommands() {
        return definedCommands;
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
    	while (children > 0) {
    		if (parsedText.peek().getKey().equals("ListStart")) {
    			List<TreeNode> tempRoots = new LinkedList<>();
    			parsedText.poll();
    			while (true) {
    				if (parsedText.peek().getKey().equals("ListEnd")) {
    					parsedText.poll();
    					break;
    				}
    				TreeNode root = createRoot();
    				tempRoots.add(root);
    			}
    			myRoots.add(tempRoots);
    		}
    		children--;
    	}
    	return myRoots;
    }
    	
    public Queue<Entry<String, String>> getParsedText() {
        return parsedText;
    }

    public Turtle getMyTurtle() {
        return myTurtle;
    }

    public MapObservable<String, TreeNode> getVariables() {
        return variables;
    }
}
