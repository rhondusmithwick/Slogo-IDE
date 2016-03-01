package View.TBar;

import Observables.ObjectObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
    private static final String DEFAULT_LOCATION = "resources/guiStrings/";
    private static final String DISP = "disp";
    private final ObjectObservable<String> language, bgColor;
    private SimpleStringProperty image, penColor, error;
    private HBox container;
    private HelpScreen hScreen;
    private ResourceBundle myResources;
    private String dispLang, bColor, pLanguage, pColor;
    private ArrayList<String> parseLangs, possColors;
    private ComboBox<String> langBox, bColorBox, pColorBox;

    public ToolBar(ObjectObservable<String> language, SimpleStringProperty error, ObjectObservable<String> bgColor, 
                   SimpleStringProperty image, SimpleStringProperty penColor) {
        hScreen = new HelpScreen();
        this.image=image;
        this.penColor=penColor;
        this.language = language;
        this.error = error;
        this.bgColor = bgColor;
        this.dispLang = DEFAULT_LANGUAGE;
        myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + dispLang + DISP);
        setHBox();
        createButtons();
        getLanguages();
        getColors();
        createComboBoxes();
    }



    private void setHBox () {
        container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(TB_SPACING);
    }

    @Override
    public HBox getToolBarMembers() {

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
        bgColor.set(bColor.toLowerCase());
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
        HBox.setHgrow(comBox, Priority.ALWAYS);
        container.getChildren().add(comBox);
        return comBox;

    }

    @SuppressWarnings("rawtypes")
    private void getColors() {
        try{
            possColors = new ArrayList<>();
            Class colorClass = Class.forName(JAVAFX_PAINT_CLASS);
            Field[] fields = colorClass.getFields();
            for (Field field : fields) {
                Object o = field.get(null);
                if (o instanceof Color) {
                    possColors.add(field.getName());
                }
            }
        }catch (Exception e) {
        	error.set("");
            error.set(myResources.getString("colorError"));
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
        HBox.setHgrow(newButt, Priority.ALWAYS);
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
        	error.set("");
            error.set(myResources.getString("picError"));
        }
    }

    private void setUpFileChooser(FileChooser fChoose, Stage s) {
        Group root = new Group();
        s.setScene(new Scene(root, MINI_WIDTH, MINI_HEIGHT));

        fChoose.setTitle(myResources.getString("getFile"));
        fChoose.getExtensionFilters().addAll(new ExtensionFilter(PNG, PNG_EXT),
                                             new ExtensionFilter(JPG, JPG_EXT), 
                                             new ExtensionFilter(GIF, GIF_EXT));
        s.show();
        s.hide();
    }



}
