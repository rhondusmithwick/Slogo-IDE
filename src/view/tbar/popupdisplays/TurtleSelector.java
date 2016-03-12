package view.tbar.popupdisplays;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import observables.ObjectObservable;

/**
 * This class provides a platform that allows users to select a turtle and make
 * it active. It is a subclass of the abstract popup class
 * 
 * @author Stephen Kwok
 *
 */

public class TurtleSelector extends TurtlePropertyUpdater {

    private static final int END_STRING = 2;
    private SimpleStringProperty turtleIDs;

    /**
     * creates a new turtleselector instance
     * @param turtleIDs SimpleStringProperty list of turtle ids
     * @param internalCommand object observable used to pass commands to command entry 
     * @param parsingLanguage simplestringproperty of current parsing language
     */
    public TurtleSelector(SimpleStringProperty turtleIDs,
                          ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
        super(turtleIDs, internalCommand, parsingLanguage, "turtleSelectTitle");
        this.turtleIDs=turtleIDs;
    }

    @Override
    protected void createElementsBelowCheckBoxes() {
        addToScene(new Label(getStringFromResources("turtleSelectClickButtonPrompt")));
    }

    @Override
    protected String makeCommand(String turtleIDs) {
        String tellCommand = getTellCommand(turtleIDs);
        String inactive = getInactive(turtleIDs);
        String askCommand = getAskCommand(inactive);
        String comm= tellCommand +  askCommand + translateCommand("ShowTurtle");
        System.out.println(comm);
        return comm;


    }

    private String getAskCommand (String inactive) {
        String askCommand;
        if(inactive.length()==0){
            askCommand = "";
        }else{
            askCommand = translateCommand("Ask") + " [ " + inactive + " ] [ " + translateCommand("HideTurtle") + " ]\n";
        }
        return askCommand;
    }

    private String getTellCommand (String turtleIDs) {
        String tellCommand;
        if(turtleIDs.length()==0){
            tellCommand = "";
        }else{
            tellCommand = translateCommand("Tell") + " [ " + turtleIDs + "]\n";
        }
        return tellCommand;
    }

    private String getInactive(String turtles) {
        List<String> active =  Arrays.asList(turtles.split(" "));
        List<String> allTurtles =  Arrays.asList(turtleIDs.get().split(", "));
        if(active.size() == allTurtles.size()){
            return "";
        }
        Predicate<String> isIn = (e) -> (!active.contains(e));
        Object[] inActive =   allTurtles.parallelStream().filter(isIn).toArray();
        StringBuilder toHide = new StringBuilder();
        Arrays.asList(inActive).stream().forEach(e-> toHide.append(((String) e) + " "));
        return toHide.toString().substring(1, toHide.length()-END_STRING);
    }

}
