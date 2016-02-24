package Testing;

import Controller.CommandContainer;
import Controller.TurtleController;

import javax.annotation.Resource;
import java.util.ResourceBundle;

/**
 * Created by rhondusmithwick on 2/23/16.
 *
 * @author Rhondu Smithwick
 */
public class Test {

    public static void main(String[] args) {
        CommandContainer c = new CommandContainer(ResourceBundle.getBundle("English"));
        Class cls = c.getClass();
        String name = cls.getName();
        System.out.println("Class Name = " + name);

        // returns the simple name of the class
        String sname = cls.getSimpleName();
        System.out.println("Class SimpleName = " + sname);

    }

}
