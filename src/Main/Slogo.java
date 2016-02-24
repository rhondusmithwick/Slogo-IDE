package Main;

import Controller.TurtleController;

import java.util.Observable;
import java.util.Observer;
import Controller.Controller;
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
      view.addObserver(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == view) {
            controller.takeInput(view.getInput());
        }
    }
}
