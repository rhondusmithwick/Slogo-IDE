package Model;

import javafx.scene.Group;

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

    /**
     * Get a list of all commands.
     **/
    List<Command> getCommands();

    Group getGroup();

    void setLanguage(String language);
}
