package Main;

import Controller.Controller.Controller;
import Controller.Controller.TurtleController;
import View.View;
import View.ViewInt;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
class Slogo {
    private static final Dimension2D APP_DIMENSIONS = new Dimension2D(1200, 718);

    private static final Dimension2D turtleDispDimension = new Dimension2D(3000, 3000);

    private final Controller controller = new TurtleController(turtleDispDimension);

    private final ViewInt view = new View(turtleDispDimension, controller.getInput(), controller.getLanguage());

    Slogo() {
        view.getInnerGroup().getChildren().add(controller.getGroup());
        bindProperties();
    }

    void init(Stage primaryStage) {
        Scene scene = new Scene(view.getGroup(), APP_DIMENSIONS.getWidth(), APP_DIMENSIONS.getHeight());
        view.bindSize(scene);
        primaryStage.setScene(scene);
    }

    private void bindProperties() {
        List<SimpleStringProperty> controllerProperties = controller.getProperties();
        controllerProperties.parallelStream()
                .forEach(this::findTwin);
    }

    private void findTwin(SimpleStringProperty cProp) {
        String cName = cProp.getName();
        List<SimpleStringProperty> viewProperties = view.getProperties();
        Predicate<SimpleStringProperty> shouldBind = (p) ->
                Objects.equals(p.getName(), cName);
        viewProperties.parallelStream()
                .filter(shouldBind)
                .findFirst()
                .ifPresent(vProp -> vProp.bindBidirectional(cProp));
    }
}
