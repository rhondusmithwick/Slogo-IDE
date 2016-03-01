package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.List;

/**
 * This is the external Interface for the View. It is the external API for displaying the results of SLOGO commands, as well as taking in the input SLOGO commands, and displaying
 * any errors that may be caught by the Model or Controller.
 *
 * @author Cali
 */
public interface ViewInt {


    /**
     * This method returns the main group of nodes for the project.
     *
     * @return main group for the scene
     */
    Group getGroup();

    /**
     * gets all string properties that are part of the view or the view components, so that they can be bound in slogo to their
     * twin properties in the controller
     *
     * @return list of views properties
     */
    List<SimpleStringProperty> getProperties();

    /**
     * Gets the group that defines the turtle area, and which sits inside the turtle areas display pane
     *
     * @return group that defines turtles area
     */
    Group getInnerGroup();

    void bindSize (Scene scene);


}
