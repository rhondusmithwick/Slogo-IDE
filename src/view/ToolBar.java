package view;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ToolBar implements ToolBarInterface {

    private static final double TB_SPACING = 10.0;
    private static final int TB_HEIGHT = 75;
    private static final int TB_WIDTH = 1000;
    private static final String DEFAULT_LOCATION = "resources/buttons/";
    private static final String DISP = "disp";
    private HBox container;
    private HelpScreen hScreen;
    private ResourceBundle myResources;
    private String language;
    private ArrayList<String> parseLangs, possColors;
    private ComboBox<String> langBox, bColorBox, pColorBox;
    
    public ToolBar(){
        this.language="english";
        container = new HBox();
        container.setPrefWidth(TB_WIDTH);
        container.setPrefHeight(TB_HEIGHT);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(TB_SPACING);
        hScreen = new HelpScreen();
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + language+DISP);
    }
    
    @Override
    public void createToolBarMembers() {
        createButtons();
        getLanguages();
        try {
            getColors();
        }
        catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
           System.out.println("Something went wrong, add better thing later");
        }
        createComboBoxes();
    }
    
    @Override
    public Node getToolBarMembers() {

        return container;
    }
    
    private void createComboBoxes () {
        langBox = createBox("selLang", parseLangs, e->setLang() );
        bColorBox = createBox("bColor", possColors, e->setBackground() );
        pColorBox = createBox("pColor", possColors, e->setPColor());
    }
    


    private void setPColor () {
        String chosenColor = pColorBox.getSelectionModel().getSelectedItem();
        System.out.println("New pen Color is " + chosenColor);
    }

    private void setBackground() {
        String chosenColor = bColorBox.getSelectionModel().getSelectedItem();
        System.out.println("New Background Color is " + chosenColor);
    }

    private void setLang() {
        String chosenLang = langBox.getSelectionModel().getSelectedItem();
        System.out.println("Language is now "+ chosenLang);   
    }

    private ComboBox<String> createBox (String label, ArrayList<String> choices, EventHandler<ActionEvent> handler) {
        ComboBox<String> comBox = new ComboBox<String>();
        comBox.setPromptText(myResources.getString(label));
        for(String choice: choices){
            comBox.getItems().add(choice);
        }
        comBox.setOnAction(handler);
        container.getChildren().add(comBox);
        return comBox;

    }

    @SuppressWarnings("rawtypes")
    private void getColors() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        possColors = new ArrayList<String>();
        
        Class colorClass = Class.forName("javafx.scene.paint.Color");
        Field[] fields = colorClass.getFields();
        for(Field field: fields){
            Object o = field.get(null);
            if(o instanceof Color){
                possColors.add(field.getName());
            }
        }
    }
    
    private void getLanguages(){
        parseLangs = new ArrayList<String>();
        File directory = new File("resources/languages");
        File[] fList = directory.listFiles();
        String name = null;
        for (File file : fList){
            name = file.getName();
            parseLangs.add(name.substring(0, name.lastIndexOf('.')));
        }
    }

    private void makeButton(String label,EventHandler<ActionEvent> handler){
        Button newButt = new Button();
        newButt.setText(label);
        container.getChildren().add(newButt);
        newButt.setOnAction(handler);
    }


    private void createButtons() {
        makeButton(myResources.getString("help"), e-> hScreen.showHelpScreen());
        makeButton(myResources.getString("image"), e->chooseTurtIm());
    }


    private void chooseTurtIm () {
        Stage s = new Stage();
        Group root = new Group();
        s.setScene(new Scene(root, 1, 1));
        FileChooser fChoose = new FileChooser();
        fChoose.setTitle(myResources.getString("getFile"));
        fChoose.getExtensionFilters().addAll(new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPG", "*.jpg"), new ExtensionFilter("GIF", "*.gif"));
        s.show();
        s.hide();
        File file = fChoose.showOpenDialog(s);
        s.close();
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






}
