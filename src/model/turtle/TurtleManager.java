package model.turtle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    private final ObservableList<Turtle> allTurtles = FXCollections.observableArrayList();
    private final Group group = new Group();
    private final Dimension2D turtDispDimension;
    private final List<Turtle> activeTurtles = new LinkedList<>();

    private final SimpleStringProperty numTurtles = new SimpleStringProperty(this, "numTurtles", "1");

    public TurtleManager(Dimension2D turtDispDimension) {
        this.turtDispDimension = turtDispDimension;
        formatActiveTurtles();
        addTurtle(1);
        addToActive(1);
    }

    public Turtle get(int ID) {
        int index = ID - 1;
        if (index >= allTurtles.size()) {
            addTurtle(ID);
        }
        return allTurtles.get(index);
    }


    public void populateActiveTurtles(Collection<Integer> IDs) {
        activeTurtles.clear();
        IDs.stream().forEach(this::addToActive);
    }

    public void replaceActiveTurtles(List<Turtle> newActives) {
        activeTurtles.clear();
        activeTurtles.addAll(newActives);
    }

    public void addTurtle(int ID) {
        Turtle turtle = new Turtle(ID, turtDispDimension);
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

    public Collection<Integer> doTell(Queue<Entry<String, String>> parsedText) {
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
        return IDs;
    }

    private void formatActiveTurtles() {
        allTurtles.addListener((ListChangeListener<Turtle>) change -> {
            String value = Integer.toString(allTurtles.size());
            numTurtles.set(value);
            System.out.println("NUM TURTLES " + numTurtles.get());
        });
    }


    public SimpleStringProperty numTurtlesProperty() {
        return numTurtles;
    }

}
