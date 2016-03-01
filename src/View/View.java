package View;


import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Arrays;
import java.util.List;


public class View implements ViewInt {


    private static final String UI_BACKGROUND_COLOR = "-fx-background-color: cornflowerblue";
    private static final int BOTTOM_PADDING =20;
    private final String EXECUTE_BUTTON_LABEL = "Execute";
    private final double EXECUTE_BUTTON_HEIGHT = 20.0;
    private final double EXECUTE_BUTTON_WIDTH = 200.0;
    private final Dimension2D turtleDispDimension;
    private final ObjectObservable<String> pLang, input, backgroundColor, intCommands, commHistory;
    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");
    private final SimpleStringProperty variables = new SimpleStringProperty(this, "variables");
    private final SimpleStringProperty methods = new SimpleStringProperty(this, "methods");
    private final SimpleStringProperty error = new SimpleStringProperty(this, "error");
    private final SimpleStringProperty consoleIn = new SimpleStringProperty(this, "consoleIn");
    private BorderPane UI;
    private Group root;
    private ToolBarInterface tBar;
    private Button executeButton;
    private TurtleAreaInterface turtDisp;
    private CommHistory commandHistory;
    private CommandEntryInterface commandEntry;
    private ErrorDisplayInterface errorDisplay;
    private EnvironmentDisplayInterface vDisplay, methodsDisplay;
    private ConsoleInterface console;
    private HBox bottom;
    private VBox left, right;


    public View(Dimension2D turtleDispDimension, ObjectObservable<String> input, ObjectObservable<String> pLang) {
        this.pLang = pLang;
        this.input = input;
        this.turtleDispDimension = turtleDispDimension;
        this.intCommands = new ObjectObservable<>();
        this.backgroundColor = new ObjectObservable<>();
        this.commHistory = new ObjectObservable<>();
        
       
        createAppView();
    }


    private void createAppView () {
        UI = new BorderPane();
        root = new Group();
        createScene();
        root.getChildren().add(UI);
    }


    private void createScene() {
        UI.setStyle(UI_BACKGROUND_COLOR);
        createTurtleDisplay();
        createToolBar();
        createBottomPane();
        createRightPane();
        createLeftPane();
        addComponents();
    }


    private void addComponents() {
        UI.setCenter(turtDisp.getTurtlePane());
        UI.setRight(right);
        UI.setLeft(left);
        UI.setBottom(bottom);
        UI.setTop(tBar.getToolBarMembers());
    }


    private void createLeftPane() {
        left = new VBox();
        vDisplay = new VariableDisplay(pLang, intCommands, variables);
        left.getChildren().add(vDisplay.getEnvDisplay());
        methodsDisplay = new MethodDisplay(pLang, intCommands, methods);
        left.getChildren().add(methodsDisplay.getEnvDisplay());
    }


    private void createBottomPane() {
        bottom = new HBox(BOTTOM_PADDING);
        errorDisplay = new ErrorDisplay(error);
        bottom.getChildren().add(errorDisplay.getErrorDisplay());
        console = new Console(consoleIn);
        bottom.getChildren().add(console.getConsole());
        commandHistory = new CommandHistoryDisplay(intCommands, commHistory);
        bottom.getChildren().add(commandHistory.getHistoryGraphic());
    }


    private void createToolBar() {
        tBar = new ToolBar(pLang, error, backgroundColor, image, penColor);
    }


    private void createTurtleDisplay() {
        turtDisp = new TurtleDisplay(backgroundColor, turtleDispDimension);

    }

    private void createRightPane() {
        right = new VBox();
        commandEntry = new CommandEntry(input, intCommands, commHistory);
        right.getChildren().add(commandEntry.getNode());
        createExecute();
    }


    private void createExecute() {
        executeButton = new Button(EXECUTE_BUTTON_LABEL);
        executeButton.setPrefSize(EXECUTE_BUTTON_WIDTH, EXECUTE_BUTTON_HEIGHT);
        executeButton.setOnAction(e -> processExecute());
        right.getChildren().add(executeButton);

	}


    private void processExecute() {
        commandEntry.processCommands();
        vDisplay.updateEnvNode();

        
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
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(image, penColor,variables, methods, error, consoleIn);
    }
    

}
