package controller.deprecated;

import Model.Deprecated.Command;
import Model.Turtle.Turtle;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;


/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public class CommandExecutor {

    private final CommandContainer myContainer;

    private final Turtle myTurtle;
    private final List<Entry<String, String>> parsingResult;
    private final List<Command> commandQueue = new LinkedList<>();
    private int currIndex;

    public CommandExecutor(CommandContainer myContainer, Turtle myTurtle, List<Entry<String, String>> parsingResult) {
        this.myContainer = myContainer;
        this.myTurtle = myTurtle;
        this.parsingResult = parsingResult;
    }


    public void createCommands() {
        currIndex = 0;
//        for (Entry<String, String> curr : parsingResult) {
//            String name = curr.getKey();
//            createCommand(name);
//        }
        parsingResult.stream().map(Entry::getKey).forEach(this::createCommand);

//        parsingResult.stream()
//                .filter(c -> myContainer.getCommandStringList().contains(c.getKey()))
//                .forEach(c-> createCommand(c.getKey()));
    }


    private void createCommand(String name) {
        if (myContainer.getCommandStringList().contains(name)) {
            Command command = createCommandInstance(myContainer.getClass(name));
            commandQueue.add(command);
        }
        currIndex++;
    }

    public Command createCommandInstance(Class<?> commandClass) {
        try {
            Constructor[] allConstructors = commandClass.getDeclaredConstructors();
            Constructor theConstructor = allConstructors[0];
            Class<?>[] myParameters = theConstructor.getParameterTypes();
//            Class<?>[] myParameters = CommandContainer.getParametersForClass(commandClass);
            Object[] params = new Object[myParameters.length];
            for (int i = 0; i < myParameters.length; i++) {
                if (myParameters[i] == Turtle.class) {
                    params[i] = myTurtle;
                } else {
                    params[i] = findDouble();
                }
            }
            return (Command) theConstructor.newInstance(params);
        } catch (Exception e) {
            return null;
        }
    }

    private double findDouble() {
        for (int i = currIndex + 1; i < parsingResult.size(); i++) {
            Entry<String, String> curr = parsingResult.get(i);
            if (Objects.equals(curr.getKey(), "Constant")) {
                return Double.parseDouble(curr.getValue());
            }
        }
        return 0.0;
    }

    public void executeAllCommands() {
        commandQueue.stream().forEach(Command::execute);
    }
}
