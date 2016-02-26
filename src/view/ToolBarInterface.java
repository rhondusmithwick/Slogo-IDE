package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;

/**
 * This is an internal interface intended to allow the views main class to create and access buttons and comboboxes that will be located on the tool bar. This class ensures that any
 * class to create a tool bar will have a method to create all the nodes needed for the tool bar as well as a method to access all the nodes of the tool bar. This holds any tool bar
 * to a contract that it must create nodes and it must give other classes a way to get them to be added to the scene, but it allows extension as to what nodes are actually
 * a part of the toolbar
 *
 * @author Cali
 */
public interface ToolBarInterface {
    /**
     * creates all necessary buttons and boxes that will be shown on the tool bar and allow the user to style the simulation
     */
    void createToolBarMembers();


    /**
     * This method will get all nodes that have been created as part of the tool bar and return them as part of a list.
     *
     * @return List of Nodes to be placed on the toolbar
     */
    Node getToolBarMembers();

    /**
     * sets the turtle display object so that toolbar can change the backgroud
     * @param tDisp turtle display component
     */
    void setTDisp(TurtleAreaInterface tDisp);

    /**
     * sets the error display object so that toolbar can show errors if needed
     * @param eDisp error display component
     */
    void setEDisp(ErrorDisplayInterface eDisp);

    /**
     * sets the command entry object so that toolbar can pass internal commands to backend
     * @param commEnt command entry component
     */
    void setCommEnt(CommandEntryInterface commEnt);

    /**
     * returns the simplestringproperty associated with parsing language
     * @return simple string property for parsing language
     */
    SimpleStringProperty getLanguage();
    
    /**
     * returns the simplestringproperty associated with turtle image's paht
     * @return simple string property for the turtle's image
     */
    SimpleStringProperty getTurtImage();

}
