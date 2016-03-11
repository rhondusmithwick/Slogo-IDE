package main;


import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import controller.controller.Controller;
import controller.controller.TurtleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.View;
import view.ViewInt;

public class Slogo {
	
	   private static final Dimension2D APP_DIMENSIONS = new Dimension2D(1200, 718);
	private static final Dimension2D turtleDispDimension = new Dimension2D(3000, 3000);

	private Controller currController;
	private ViewInt currView;
	private TabPane tabPane;
	private Group root;
	private Stage stage;

	public Slogo(Stage stage){
		this.stage=stage;
		root = new Group();
		tabPane = new TabPane();
		tabPane.prefHeightProperty().bind(this.stage.heightProperty());
		tabPane.prefWidthProperty().bind(this.stage.widthProperty());
		newView();
		root.getChildren().add(tabPane);
	}

	public void newView() {
		Tab tab = new Tab("workspace");
		GlobalProperties globalProperties = new GlobalProperties();
		currView = new View(globalProperties, turtleDispDimension, this);
		currView.bindSize(tabPane.heightProperty(), tabPane.widthProperty());
		currController = new TurtleController(globalProperties, turtleDispDimension);
		currView.getInnerGroup().getChildren().add(currController.getGroup());
		tab.setContent(currView.getGroup());
		bindProperties();
		tabPane.getTabs().add(tab);
		
		
	}
	
	
    private void bindProperties() {
        List<SimpleStringProperty> controllerProperties = currController.getProperties();
        controllerProperties.parallelStream()
                .forEach(this::findTwin);
    }

    private void findTwin(SimpleStringProperty cProp) {
        String cName = cProp.getName();
        List<SimpleStringProperty> viewProperties = currView.getProperties();
        Predicate<SimpleStringProperty> shouldBind = (p) ->
                Objects.equals(p.getName(), cName);
        viewProperties.parallelStream()
                .filter(shouldBind)
                .findFirst()
                .ifPresent(vProp -> vProp.bindBidirectional(cProp));
    }
    

    protected void init() {
        Scene scene = new Scene(root, APP_DIMENSIONS.getWidth(), APP_DIMENSIONS.getHeight());
        stage.setScene(scene);
    }
	
	
	
}
