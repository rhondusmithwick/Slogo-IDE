package view;


import Controller.Controller;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View implements ViewInt{
	
	private final String EXECUTE_BUTTON_LABEL = "Execute";
	private final double EXECUTE_BUTTON_HEIGHT = 20.0;
	private final double EXECUTE_BUTTON_WIDTH = 200.0;
	
	
	private BorderPane UI;
	private Controller controller;
	private Group root;
	private TurtleDisplay turtDisp;
	private Button executeButton;
	private CommandHistoryDisplay commandHistory;
	
	
	
	
	public View(Group group) {
//	public View(Controller controller){
		this.controller = controller;
		UI = new BorderPane();
//		root = controller.getGroup();
		root = group;
		createScene();
		root.getChildren().add(UI);
		
	}
	
	
	
	

	private void createScene() {
		//as components are built take code out of here and rewrite it to add component
		
		//Toolbar here
		Rectangle r = new Rectangle(1000,100);
		r.setFill(Color.GREEN);
		HBox top = new HBox();
		top.getChildren().add(r);
		
		//errors and command history here
		
//		r = new Rectangle(1000,200);
//		r.setFill(Color.BLACK);
		HBox bottom = new HBox();
//		bottom.getChildren().add(r);
		
		commandHistory = new CommandHistoryDisplay();
		commandHistory.createCommHistory();
		Node commandHistoryBox = commandHistory.getRootNode();
		commandHistoryBox.setTranslateX(800.0); // make this a variable
		bottom.getChildren().add(commandHistoryBox);
		
		
		
		//variables and methods here
		VBox left = new VBox();
		r = new Rectangle(100, 400);
		r.setFill(Color.BLUE);
		left.getChildren().add(r);

		//text entry and execute button here
		VBox right = new VBox();
		CommandEntry commandEntry = new CommandEntry();
		commandEntry.createEntryBox();
		Node entryBox = commandEntry.getRootNode();
		right.getChildren().add(entryBox);
		
		executeButton = new Button(EXECUTE_BUTTON_LABEL);
		executeButton.setMaxHeight(EXECUTE_BUTTON_HEIGHT);
		executeButton.setMaxWidth(EXECUTE_BUTTON_WIDTH);
		executeButton.setOnAction(e -> {
			commandHistory.addCommand(commandEntry.getTextBox().getText());
			commandEntry.clearCommands();
			
		});
		right.getChildren().add(executeButton);
		
		//turtle area here
		turtDisp = new TurtleDisplay();
		turtDisp.createTurtleArea();
		Node center = turtDisp.getTurtleArea();
		
		
		//add components to scene
		UI.setCenter(center);
		UI.setRight(right);
		UI.setLeft(left);
		UI.setBottom(bottom);
		UI.setTop(top);
	}





	public void passError(String Error) {
		// TODO Auto-generated method stub
		
	}


	public void passInput(String command) {
		controller.takeInput(command);
		
	}
	
	public Node getTurtleDisplay(){
	    return turtDisp.getTurtleArea();
	}

}
