package Main;

import Controller.Controller.Controller;
import Controller.Controller.TurtleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import view.ViewInt;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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
        bindProperties();
        view.getInnerGroup().getChildren().add(controller.getGroup());
    }


    void init(Stage primaryStage) {
        Scene scene = new Scene(view.getGroup(), 1000, 700);
        primaryStage.setScene(scene);
    }

    private void bindProperties() {
        List<SimpleStringProperty> controllerProperties = controller.getProperties();
        List<SimpleStringProperty> viewProperties = view.getProperties();
        controllerProperties.stream().forEach(prop -> findTwin(prop, viewProperties));
    }

    private void findTwin(SimpleStringProperty controllerProperty,
                          List<SimpleStringProperty> viewProperties) {
        String cName = controllerProperty.getName();
        Predicate<SimpleStringProperty> shouldBind = (p) ->
                Objects.equals(p.getName(), cName);
        viewProperties.stream()
                .filter(shouldBind).findFirst()
                .ifPresent(controllerProperty::bindBidirectional);
    }

}
