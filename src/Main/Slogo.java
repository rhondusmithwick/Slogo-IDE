package Main;

import Controller.Controller;
import Controller.TurtleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.java2d.pipe.SpanShapeRenderer;
import view.View;
import view.ViewInt;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        List<SimpleStringProperty> controllerProperties = controller.getProperties();
        List<SimpleStringProperty> viewProperties = Arrays.asList(view.getProperties());
        controllerProperties.stream().forEach(prop -> findTwin(prop, viewProperties));
//        for (SimpleStringProperty controllerProperty : controllerProperties) {
//            findTwin(controllerProperty, viewProperties);
//        }
    }

    private void findTwin(SimpleStringProperty controllerProperty,
                             List<SimpleStringProperty> viewProperties) {
        viewProperties.stream().filter(viewProp -> shouldBindTogether(controllerProperty, viewProp))
                .forEach(controllerProperty::bindBidirectional);
//        for (SimpleStringProperty viewProperty : viewProperties) {
//            String vName = viewProperty.getName();
//            if (cName.equals(vName)) {
//                controllerProperty.bindBidirectional(viewProperty);
//                return true;
//            }
//        }
//        return false;
    }

    private boolean shouldBindTogether(SimpleStringProperty controllerProperty, SimpleStringProperty viewProperty) {
        String cName = controllerProperty.getName();
        String vName = viewProperty.getName();
        return Objects.equals(cName, vName);
    }
}
