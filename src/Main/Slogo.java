package Main;

import Controller.TurtleController;

import Controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Dimension2D;
import view.ViewInt;
import view.View;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Slogo  {
    private static final Dimension2D turtleDimension = new Dimension2D(600, 450);

    private final Controller controller = new TurtleController(turtleDimension.getWidth(), turtleDimension.getHeight());

    private final ViewInt view = new View(turtleDimension.getWidth(), turtleDimension.getHeight());

    public Slogo() {
        bindProperties();
    }

   private void bindProperties() {
       SimpleStringProperty[] controllerProperties = controller.getProperties();
       SimpleStringProperty[] viewProperties = View.getProperties();
       for (SimpleStringProperty controllerProperty: controllerProperties) {
          findTwin(controllerProperty, viewProperties);
       }
   }

    private boolean findTwin(SimpleStringProperty controllerProperty,
                          SimpleStringProperty[] viewProperties) {
        String cName = controllerProperty.getName();
        for (SimpleStringProperty viewProperty: viewProperties) {
            String vName = viewProperty.getName();
            if (cName.equals(vName)) {
                controllerProperty.bindBidirectional(viewProperty);
                return true;
            }
        }
        return false;
    }
}
