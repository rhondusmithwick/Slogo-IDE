package main;

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

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Rhondu Smithwick and Cali Nelson
 */

public class Slogo {

    private static final Dimension2D APP_DIMENSIONS = new Dimension2D(1200, 718);
    private static final Dimension2D turtleDispDimension = new Dimension2D(575, 372);

    private Controller currController;
    private ViewInt currView;
    private final TabPane tabPane;
    private final Group root;
    private final Stage stage;

    /**
     * creates a new slogo objcet
     *
     * @param stage stage the visuals will be shown on
     */
    public Slogo(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.tabPane = new TabPane();
        this.tabPane.prefHeightProperty().bind(this.stage.heightProperty());
        this.tabPane.prefWidthProperty().bind(this.stage.widthProperty());
        newView();
        root.getChildren().add(tabPane);
    }

    /**
     * creates a new workspace and adds it as a tab to tab pane
     */
    public void newView() {
        Tab tab = new Tab();
        GlobalProperties globalProperties = new GlobalProperties();
        currController = new TurtleController(globalProperties, turtleDispDimension);
        currView = new View(globalProperties, turtleDispDimension, this);
        currView.bindSize(tabPane.heightProperty(), tabPane.widthProperty());

        currView.getInnerGroup().getChildren().add(currController.getGroup());
        tab.setText(currView.getTitle());
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

    /**
     * Initializes the Slogo object and sets the stage to show it
     */
    protected void init() {
        Scene scene = new Scene(root, APP_DIMENSIONS.getWidth(), APP_DIMENSIONS.getHeight());
        stage.setScene(scene);
    }

}
