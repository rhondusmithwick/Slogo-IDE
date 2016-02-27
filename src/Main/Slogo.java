package Main;

import Controller.Controller.Controller;
import Controller.Controller.TurtleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import view.ViewInt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
class Slogo {
    private static final Dimension2D APP_DIMENSIONS = new Dimension2D(1000, 700);

    private static final Dimension2D turtleDispDimension = new Dimension2D(600, 450);

    private final Controller controller = new TurtleController(turtleDispDimension);

    private final ViewInt view;

    Slogo() {
        Map<String, SimpleStringProperty> propertyMap = createPropertyMap();
        view = new View(propertyMap, turtleDispDimension);
        view.getInnerGroup().getChildren().add(controller.getGroup());
    }

    void init(Stage primaryStage) {
        Scene scene = new Scene(view.getGroup(), APP_DIMENSIONS.getWidth(), APP_DIMENSIONS.getHeight());
        primaryStage.setScene(scene);
    }

    private Map<String, SimpleStringProperty> createPropertyMap() {
        Map<String, SimpleStringProperty> propertyMap = new HashMap<>();
        controller.getProperties().stream().forEach(prop -> putInPropertyMap(propertyMap, prop));
        return propertyMap;
    }

    private void putInPropertyMap(Map<String, SimpleStringProperty> propertyMap, SimpleStringProperty prop) {
        propertyMap.put(prop.getName(), prop);
    }

}
