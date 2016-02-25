package view;


import javafx.beans.property.SimpleStringProperty;
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

public class View implements ViewInt{

    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    private final double EXECUTE_BUTTON_WIDTH = 200.0;


    private BorderPane UI;
    private Group root;
    private TurtleDisplay turtDisp;
    private ToolBar tBar;
    private Button executeButton;
    private CommandHistoryDisplay commandHistory;
    private double width, height;





    public View(double width, double height) {
    	this.height=height;
    	this.width=width;
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);

    }





    private void createScene() {


        //turtle area here
        turtDisp = new TurtleDisplay(root);
        turtDisp.createTurtleArea(width, height);
        ScrollPane center = new ScrollPane();
        center.setMaxHeight(450);
        center.setMaxWidth(600);
        center.setContent(turtDisp.getTurtleArea());

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
        bottom.getChildren().add(commandHistoryBox);



        //variables and methods here
        VBox left = new VBox();
        Rectangle r = new Rectangle(100, 400);
        r.setFill(Color.BLUE);
        left.getChildren().add(r);

        //text entry and execute button here
        VBox right = new VBox();
        Label commandEntTitle = new Label("Enter Commands Here");
        right.getChildren().add(commandEntTitle);
        
        CommandEntry commandEntry = new CommandEntry();
        commandEntry.createEntryBox();
        Node entryBox = commandEntry.getTextBox();
        right.getChildren().add(entryBox);

        executeButton = new Button(EXECUTE_BUTTON_LABEL);
        executeButton.setMaxHeight(EXECUTE_BUTTON_HEIGHT);
        executeButton.setMaxWidth(EXECUTE_BUTTON_WIDTH);
        executeButton.setOnAction(e -> {
            commandHistory.addCommand(commandEntry.getTextBox().getText());
            commandEntry.clearCommands();

        });
        right.getChildren().add(executeButton);

       


        //add components to scene
        UI.setCenter(center);
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(tBar.getToolBarMembers());
    }





    public void passError(String Error) {
        // TODO Auto-generated method stub

    }


    public void passInput(String command) {

    }

    public Node getGroup(){
        return root;
    }





	public static SimpleStringProperty[] getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
