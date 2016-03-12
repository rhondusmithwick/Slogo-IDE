package controller.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;

import java.util.List;
import java.util.Observer;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public interface Controller extends Observer {
    /**
     * Accept an input string.
     **/
    void takeInput(String input);

    List<SimpleStringProperty> getProperties();

    Group getGroup();

}
