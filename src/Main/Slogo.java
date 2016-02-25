package Main;

import Controller.Controller;
import Controller.TurtleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import view.ViewInt;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
class Slogo {
    private static final Dimension2D turtleDispDimension = new Dimension2D(600, 450);

    private final Controller controller = new TurtleController(turtleDispDimension);

    private final ViewInt view = new View(turtleDispDimension);

    public Slogo() {
//        bindProperties();
        view.getInnerGroup().getChildren().add(controller.getGroup());
    }


    void init(Stage primaryStage) {
        Scene scene = new Scene(view.getGroup(), 1000, 700);
        primaryStage.setScene(scene);
    }

    private void bindProperties() {
        SimpleStringProperty[] controllerProperties = controller.getProperties();
        SimpleStringProperty[] viewProperties = view.getProperties();
        for (SimpleStringProperty controllerProperty : controllerProperties) {
            findTwin(controllerProperty, viewProperties);
        }
    }

    private boolean findTwin(SimpleStringProperty controllerProperty,
                             SimpleStringProperty[] viewProperties) {
        String cName = controllerProperty.getName();
        for (SimpleStringProperty viewProperty : viewProperties) {
            String vName = viewProperty.getName();
            if (cName.equals(vName)) {
                controllerProperty.bindBidirectional(viewProperty);
                return true;
            }
        }
        return false;
    }
}
