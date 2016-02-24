package Main;

import Controller.TurtleController;

import java.util.Observable;
import java.util.Observer;
import Controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import view.ViewInt;
import view.View;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Slogo implements Observer {
    private final Controller controller = new TurtleController();

    private final ViewInt view = new View();

    public Slogo() {
      view.inputProperty().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == view.inputProperty()) {
            controller.takeInput(view.inputProperty().get());
        }
    }
}
