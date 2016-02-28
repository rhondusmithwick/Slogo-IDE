package view;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
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

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ToolBar implements ToolBarInterface {

    private static final String GIF_EXT = "*.gif";
    private static final String GIF = "GIF";
    private static final String JPG_EXT = "*.jpg";
    private static final String JPG = "JPG";
    private static final int MINI_HEIGHT = 1;
    private static final int MINI_WIDTH = 1;
    private static final String PNG_EXT = "*.png";
    private static final String PNG = "PNG";
    private static final char FILE_EXTENSION = '.';
    private static final String LANGUAGE_LOCATION = "resources/languages";
    private static final String JAVAFX_PAINT_CLASS = "javafx.scene.paint.Color";
    private static final String LANGUAGE_PATH = "languages/";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final double TB_SPACING = 10.0;
    private static final int TB_HEIGHT = 75;
    private static final int TB_WIDTH = 1000;
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";

    private final SimpleStringProperty image = new SimpleStringProperty(this, "turtleImage");
    private final ObjectObservable<String> language;
    private final SimpleStringProperty penColor = new SimpleStringProperty(this, "penColor");
    private HBox container;
    private HelpScreen hScreen;
    private ResourceBundle myResources;
    private String dispLang, bColor, pLanguage, pColor;
    private TurtleAreaInterface tDisp;
    private ErrorDisplayInterface eDisp;
    private ArrayList<String> parseLangs, possColors;
    private ComboBox<String> langBox, bColorBox, pColorBox;

    public ToolBar(ObjectObservable<String> language) {
        this.language = language;
        this.dispLang = DEFAULT_LANGUAGE;
        container = new HBox();
        container.setPrefWidth(TB_WIDTH);
        container.setPrefHeight(TB_HEIGHT);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(TB_SPACING);
        hScreen = new HelpScreen();
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + dispLang + DISP);
    }

    @Override
    public void createToolBarMembers() {
        createButtons();
        getLanguages();
        try {
            getColors();
        } catch (Exception e) {
            eDisp.showError(myResources.getString("colorError"));
        }
        createComboBoxes();
    }

    @Override
    public Node getToolBarMembers() {

        return container;
    }

    private void createComboBoxes() {
        langBox = createBox("selLang", parseLangs, e -> setLang());
        bColorBox = createBox("bColor", possColors, e -> setBackground());
        pColorBox = createBox("pColor", possColors, e -> setPColor());
    }


    private void setPColor() {
        pColor = pColorBox.getSelectionModel().getSelectedItem();
        penColor.set(pColor.toLowerCase());

    }

    private void setBackground() {
        bColor = bColorBox.getSelectionModel().getSelectedItem();
        tDisp.setBackground(bColor.toLowerCase());
    }

    private void setLang() {
        pLanguage = LANGUAGE_PATH + langBox.getSelectionModel().getSelectedItem();
        language.set(pLanguage);
    }

    private ComboBox<String> createBox(String label, ArrayList<String> choices, EventHandler<ActionEvent> handler) {
        ComboBox<String> comBox = new ComboBox<>();
        comBox.setPromptText(myResources.getString(label));
        for (String choice : choices) {
            comBox.getItems().add(choice);
        }
        comBox.setOnAction(handler);
        container.getChildren().add(comBox);
        return comBox;

    }

    @SuppressWarnings("rawtypes")
    private void getColors() throws Exception {
        possColors = new ArrayList<>();

        Class colorClass = Class.forName(JAVAFX_PAINT_CLASS);
        Field[] fields = colorClass.getFields();
        for (Field field : fields) {
            Object o = field.get(null);
            if (o instanceof Color) {
                possColors.add(field.getName());
            }
        }
    }

    private void getLanguages() {
        parseLangs = new ArrayList<>();
        File directory = new File(LANGUAGE_LOCATION);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String name = file.getName();
            parseLangs.add(name.substring(0, name.lastIndexOf(FILE_EXTENSION)));
        }
    }

    private void makeButton(String label, EventHandler<ActionEvent> handler) {
        Button newButt = new Button();
        newButt.setText(label);
        container.getChildren().add(newButt);
        newButt.setOnAction(handler);
    }


    private void createButtons() {
        makeButton(myResources.getString("help"), e -> hScreen.showHelpScreen(myResources.getString("helpFile")));
        makeButton(myResources.getString("image"), e -> chooseTurtIm());
    }


    private void chooseTurtIm() {
        FileChooser fChoose = new FileChooser();
        Stage s = new Stage();
        setUpFileChooser(fChoose, s);
        File file = fChoose.showOpenDialog(s);
        s.close();
        if (file == null) {
            return;
        }
        try {
            String imagepath = file.toURI().toURL().toString();
            image.set(imagepath);

        } catch (MalformedURLException e) {
            eDisp.showError(myResources.getString("picError"));
        }
    }

    private void setUpFileChooser(FileChooser fChoose, Stage s) {
        Group root = new Group();
        s.setScene(new Scene(root, MINI_WIDTH, MINI_HEIGHT));

        fChoose.setTitle(myResources.getString("getFile"));
        fChoose.getExtensionFilters().addAll(new ExtensionFilter(PNG, PNG_EXT),
                new ExtensionFilter(JPG, JPG_EXT), new ExtensionFilter(GIF, GIF_EXT));
        s.show();
        s.hide();
    }

    @Override
    public void setTDisp(TurtleAreaInterface tDisp) {
        this.tDisp = tDisp;
    }

    @Override
    public void setEDisp(ErrorDisplayInterface errorDisp) {
        this.eDisp = errorDisp;
    }


    @Override
    public List<SimpleStringProperty> getProperties() {
        return Arrays.asList(image, penColor);
    }
    
    public ObjectObservable getParseLang(){
        return language;
    }


}
