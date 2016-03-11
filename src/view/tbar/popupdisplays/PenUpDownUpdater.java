package view.tbar.popupdisplays;

import javafx.beans.property.SimpleStringProperty;
import observables.ObjectObservable;

public abstract class PenUpDownUpdater extends TurtlePropertyUpdater {

	public PenUpDownUpdater(SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage) {
		super(turtleIDs, internalCommand, parsingLanguage);
	}

	@Override
	protected abstract String makeCommand(String turtleIDs);
	
	@Override
	protected abstract void createElementsBelowCheckBoxes();
	
	protected String generatePenUpDownCommand(String turtleIDs, String command) {
		String askCommand = translateCommand("Ask");
		String penCommand = translateCommand(command);
		return askCommand + " [ " + turtleIDs + "] [ " + penCommand + " ]";
	}

}
