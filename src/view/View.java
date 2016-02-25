package view;


import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View implements ViewInt {

    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    private final double EXECUTE_BUTTON_WIDTH = 200.0;
    private final double COMMAND_HIST_X_POS =600.0;


    private BorderPane UI;
    private Group root;
    private TurtleDisplay turtDisp;
    private ToolBar tBar;
    private Button executeButton;
    private CommandHistoryDisplay commandHistory;
    private CommandEntry commandEntry;
    private final Dimension2D turtleDispDimension;
    private ErrorDisplay errorDisplay;

    public View(Dimension2D turtleDispDimension) {
        this.turtleDispDimension = turtleDispDimension;
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);

    }


    private void createScene() {


        //turtle area here
        turtDisp = new TurtleDisplay(root);
        turtDisp.createTurtleArea(turtleDispDimension);
        

        //Tool Bar here
        tBar = new ToolBar();
        tBar.createToolBarMembers();

        //errors and command history here
        //		r = new Rectangle(1000,200);
        //		r.setFill(Color.BLACK);
        HBox bottom = new HBox();
        //		bottom.getChildren().add(r);

        commandHistory = new CommandHistoryDisplay();
        commandHistory.createCommHistory();
        Node commandHistoryBox = commandHistory.getHistoryGraphic();
        commandHistoryBox.setTranslateX(COMMAND_HIST_X_POS);
        bottom.getChildren().add(commandHistoryBox);
        
        errorDisplay = new ErrorDisplay();
        


        //variables and methods here
        VBox left = new VBox();
        Rectangle r = new Rectangle(100, 400);
        r.setFill(Color.BLUE);
        left.getChildren().add(r);
        
    	//text entry and execute button here
        VBox right = initRightPane();
        
        //add components to scene
        UI.setCenter(turtDisp.getTurtlePane());
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(tBar.getToolBarMembers());
        setToolBar();
    }

    private VBox initRightPane() {
        VBox right = new VBox();
        Label commandEntTitle = new Label("Enter Commands Here");
        right.getChildren().add(commandEntTitle);

        commandEntry = new CommandEntry();
        commandEntry.createEntryBox();
        Node entryBox = commandEntry.getTextBox();
        right.getChildren().add(entryBox);

        executeButton = new Button(EXECUTE_BUTTON_LABEL);
        executeButton.setPrefSize(EXECUTE_BUTTON_WIDTH, EXECUTE_BUTTON_HEIGHT);
        executeButton.setOnAction(e -> processExecute());
        right.getChildren().add(executeButton);
        return right;
    }



	private void processExecute() {
		commandHistory.addCommand(commandEntry.getTextBox().getText());
		commandEntry.getBoxCommands();
		commandEntry.clearCommands();
	}


    private void setToolBar() {
		tBar.setCommEnt(commandEntry);
		tBar.setTDisp(turtDisp);
		tBar.setEDisp(errorDisplay);
		
	}


	public void passError(String Error) {
        // TODO Auto-generated method stub

    }


    public void passInput(String command) {

    }

    @Override
    public Group getGroup() {
        return root;
    }


    @Override
    public Group getInnerGroup() {
        return turtDisp.getTurtleArea();
    }

    @Override
    public SimpleStringProperty[] getProperties() {
        // TODO Auto-generated method stub
        return null;
    }

}
