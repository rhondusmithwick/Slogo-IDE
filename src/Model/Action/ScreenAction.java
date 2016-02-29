package Model.Action;

import Model.Turtle.Turtle;
import Model.Turtle.TurtleProperties;
import javafx.application.Platform;

public class ScreenAction extends TurtleAction {
	
	private final TurtleProperties properties;

	public ScreenAction(Turtle myTurtle, TurtleProperties properties) {
		super(myTurtle);
		this.properties = properties;
	}
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				getMyTurtle().getGroup().getChildren().clear();
				properties.setLocation(properties.getHome());
				getMyTurtle().getGroup().getChildren().add(properties.getImageView());
			}
		});
		super.run();
	}
}
