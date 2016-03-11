package view.tbar.popupdisplays;

import java.util.ArrayList;
import java.util.Arrays;
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
		String tellCommand = translateCommand("Tell");
		String askCommand = translateCommand("Ask");
		String hideCommand = translateCommand("HideTurtle");
		String inactive = getInactive(turtleIDs);
		String comm = tellCommand + " [ " + turtleIDs + "]\n" + askCommand + " [" + inactive + "] [ " + hideCommand + " ]";
		System.out.println(comm);
		return comm;
	}

	private String getInactive(String turtles) {
		ArrayList<String> active = new ArrayList<String>( Arrays.asList(turtles.split(" ")));
		ArrayList<String> allTurtles = new ArrayList<String> (Arrays.asList(turtleIDs.get().split(", ")));
		Predicate<String> isIn = (e) -> (active.contains(e));
		Object[] inActive =   allTurtles.parallelStream().filter(isIn).toArray();
		StringBuilder toHide = new StringBuilder();
		Arrays.asList(inActive).stream().forEach(e-> toHide.append(((String) e) + " "));
		return toHide.toString();
	}

}
