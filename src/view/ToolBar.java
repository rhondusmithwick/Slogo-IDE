package view;

import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ToolBar implements ToolBarInterface {

    private HBox container;
    private Stage stage;
    private TurtleDisplay turtDisp;
    private HelpScreen hScreen;
    public ToolBar(Stage stage){
        container = new HBox();
        container.setPrefWidth(1000);
        container.setPrefHeight(75);
        container.setAlignment(Pos.CENTER);
        hScreen = new HelpScreen();
    }



    private void makeButton(String label,EventHandler<ActionEvent> handler){
        Button newButt = new Button();
        newButt.setText(label);
        container.getChildren().add(newButt);
        newButt.setOnAction(handler);
    }




    @Override
    public void createToolBarMembers() {
        createButtons();

    }

    private void createButtons() {
        makeButton("Help", e-> showHelpPage());
        makeButton("Choose Turtle Image", e->chooseTurtIm());
    }



    private void chooseTurtIm () {
        FileChooser fChoose = new FileChooser();
        fChoose.setTitle("Select Image File");
        fChoose.getExtensionFilters().addAll(new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPG", "*.jpg"), new ExtensionFilter("GIF", "*.gif"));
        File file = fChoose.showOpenDialog(stage);
        if(file==null){
            return;
        }
        //make this observable for backend
        try {
            String imagepath = file.toURI().toURL().toString();
            System.out.println(imagepath);

        }
        //make this observable for ErrorDisplay
        catch (MalformedURLException e) {
            System.out.print("hook this up to error display");
        }
    }



    private void showHelpPage() {
        hScreen.showHelpScreen();
    }



    @Override
    public Node getToolBarMembers() {

        return container;
    }

}
