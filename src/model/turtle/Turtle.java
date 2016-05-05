package model.turtle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import view.tbar.popupdisplays.IEditableElement;

/**
 * Created by rhondusmithwick on 2/22/16.
 *
 * @author Rhondu Smithwick
 */
public class Turtle implements IEditableElement {

    private final Group root = new Group();
    private final Group stamps = new Group();
    private final int ID;
    private final TurtleProperties turtleProperties;
    private final ExecutorService executorService = Executors.newWorkStealingPool(1);
    private int numStamps = 0;
    private String name;

    public Turtle(int ID, Dimension2D turtleDispDimension) {
        this.ID = ID;
        root.getChildren().add(stamps);
        turtleProperties = new TurtleProperties();
        turtleProperties.addListeners();
        turtleProperties.init(turtleDispDimension);
        root.getChildren().add(turtleProperties.getImageView());
    }


    public void addStamp(ImageView imageView) {
        numStamps++;
        stamps.getChildren().add(imageView);
    }

    public int getNumStamps() {
        return numStamps;
    }

    public void clearStamps() {
        stamps.getChildren().clear();
        numStamps = 0;
    }

    public TurtleProperties getTurtleProperties() {
        return turtleProperties;
    }

    public int getID() {
        return ID;
    }

    public Group getGroup() {
        return root;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public String toString() {
        return String.format("Turtle with ID: %d", ID);
    }


	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public ImageView getImageView() {
		return turtleProperties.getImageView();
	}


	@Override
	public void setImage(ImageView imageView) {
		return;
	}


    
    
}
