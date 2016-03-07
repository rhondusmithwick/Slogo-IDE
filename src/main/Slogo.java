package main;

import view.MultiView;
import view.ViewInt;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import controller.controller.Controller;
import controller.controller.TurtleController;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
class Slogo {
    private static final Dimension2D APP_DIMENSIONS = new Dimension2D(1200, 800);

    private static final Dimension2D turtleDispDimension = new Dimension2D(3000, 3000);

    private final Controller controller = new TurtleController(turtleDispDimension);

    private final MultiView multView = new MultiView(turtleDispDimension, controller.getInput(), controller.getLanguage());

    Slogo() {
        MultiView.createTab();
        multView.getViews().get(0).getInnerGroup().getChildren().add(controller.getGroup());
        multView.getViews().forEach(e->bindProperties(e));
    }

    void init(Stage primaryStage) {
        Scene scene = new Scene(multView.getPane(), APP_DIMENSIONS.getWidth(), APP_DIMENSIONS.getHeight());
        multView.bindSize(scene);
        primaryStage.setScene(scene);
    }


    private void bindProperties(ViewInt v) {
        List<SimpleStringProperty> controllerProperties = controller.getProperties();
        controllerProperties.parallelStream()
                .forEach(e->findTwin(e, v));
    }

    private void findTwin(SimpleStringProperty cProp, ViewInt view) {
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
