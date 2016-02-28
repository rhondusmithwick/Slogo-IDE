package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class VariableDisplay implements EnvironmentDisplayInterface {
    private final SimpleStringProperty variablesString = new SimpleStringProperty(this, "variablesString");
    private ScrollPane scroll;
    
    @Override
    public Node getEnvDisplay () {
        return scroll;
    }

    @Override
    public void createEnvNode () {
        scroll = new ScrollPane();
        Label l = new Label();

    }

    @Override
    public void updateEnvNode () {
        System.out.println(variablesString.toString());

    }

    @Override
    public SimpleStringProperty getEnvProperty () {
        return variablesString;
    }

}
