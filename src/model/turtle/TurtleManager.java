package model.turtle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;


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
    private final ObservableMap<Integer, Turtle> allTurtles = FXCollections.observableHashMap();

    private final Group group = new Group();
    private final Dimension2D turtDispDimension;
    private final List<Turtle> activeTurtles = new LinkedList<>();

    private final SimpleStringProperty turtleIDs = new SimpleStringProperty(this, "turtleIDs");

    public TurtleManager(Dimension2D turtDispDimension) {
        this.turtDispDimension = turtDispDimension;
        formatActiveTurtles();
        addTurtle(1);
        addToActive(1);
    }

    public Turtle get(int ID) {
        if (!allTurtles.containsKey(ID)) {
            addTurtle(ID);
        }
        return allTurtles.get(ID);
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
        allTurtles.put(ID, turtle);
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
        allTurtles.addListener((MapChangeListener<Integer, Turtle>) change -> {
            turtleIDs.set(allTurtles.keySet().toString());
            System.out.println("TURTLE IDS "+ turtleIDs.get());
        });
    }

    public int numTurtles() {
        return allTurtles.size();
    }

    public SimpleStringProperty getTurtleIDsProperty() {
        return turtleIDs;
    }

}