package controller.slogoparser;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import model.turtle.Turtle;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Created by rhondusmithwick on 3/9/16.
 *
 * @author Rhondu Smithwick
 */
public class TurtleManager {
    private final List<Turtle> allTurtles = new ArrayList<>();
    private final Group group = new Group();
    private final Dimension2D turtDispDimension;
    private final List<Turtle> activeTurtles = new LinkedList<>();

    public TurtleManager(Dimension2D turtDispDimension) {
        this.turtDispDimension = turtDispDimension;
        addTurtle();
        addToActive(1);
    }

    public Turtle get(int ID) {
        int index = ID - 1;
        if (index >= allTurtles.size()) {
            addTurtle();
        }
        return allTurtles.get(index);
    }


    public void populateActiveTurtles(Collection<Integer> IDs) {
        activeTurtles.clear();
        IDs.parallelStream().forEach(this::addToActive);
    }

    public void addTurtle() {
        Turtle turtle = new Turtle(turtDispDimension);
        allTurtles.add(turtle);
        group.getChildren().add(turtle.getGroup());
    }

    public List<Turtle> getActiveTurtles() {
        return activeTurtles;
    }

    public Group getGroup() {
        return group;
    }


    private void addToActive(int ID) {
        activeTurtles.add(get(ID));
    }

    public void doTell(Queue<Entry<String, String>> parsedText) {
        Collection<Integer> IDs = new LinkedList<>();
        if (parsedText.peek().getKey().equals("ListStart")) {
            parsedText.poll();
            while (true) {
                if (parsedText.peek().getKey().equals("ListEnd")) {
                    parsedText.poll();
                    break;
                }
                Entry<String, String> curr = parsedText.poll();
                IDs.add(Integer.parseInt(curr.getValue()));
            }
        }
        populateActiveTurtles(IDs);
    }

}
