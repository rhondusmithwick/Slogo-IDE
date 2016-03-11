package view.tbar.popupdisplays.pen;

import javafx.beans.property.SimpleStringProperty;
import observables.ObjectObservable;
import view.tbar.popupdisplays.TurtlePropertyUpdater;

public abstract class PenUpDownUpdater extends TurtlePropertyUpdater {

	public PenUpDownUpdater(SimpleStringProperty turtleIDs,
			ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, String titleText) {
		super(turtleIDs, internalCommand, parsingLanguage, titleText);
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
