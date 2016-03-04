package controller.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import model.deprecated.Command;
import observables.ObjectObservable;

import java.util.List;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public interface Controller {
    /**
     * Accept an input string.
     **/
    void takeInput(String input);

    List<SimpleStringProperty> getProperties();

    /**
     * Get a list of all commands.
     **/
    List<Command> getCommands();

    Group getGroup();

    ObjectObservable<String> getLanguage();

    ObjectObservable<String> getInput();
}
