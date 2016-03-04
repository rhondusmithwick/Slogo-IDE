package View.TBar;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import View.Defaults;
import View.Size;
import View.Xml.WorkspaceXML;
import View.Xml.XMLChooser;
import View.utilities.ButtonFactory;
import View.utilities.ComboFactory;
import View.utilities.FileGetter;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkSpaceSaver {


    private ResourceBundle myResources;
    SimpleStringProperty error;
    private TextField tField;
    private VBox v;
    private Scene myScene;
    private ArrayList<String> colors, langs;
    private ComboBox<String> bColor,pColor, pLangs, colorFile, imageFile; //variableFile;
    private Stage stage;

    public WorkSpaceSaver(List<String> colors, List<String> langs , SimpleStringProperty error){
        this.langs = (ArrayList<String>) langs;
        this.error=error;
        this.colors=(ArrayList<String>) colors;
        this.myResources= ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }
    public void showSaver(){
        myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
        Group root;
        root = new Group();
        stage = new Stage();
        myScene = new Scene(root, Size.HTML_WIDTH.getSize(), Size.HTML_HEIGHT.getSize());
        setVBox();
        createSaver();
        root.getChildren().add(v);
        stage.setScene(myScene);
        stage.show();
    }

    private void setVBox () {
        v = new VBox(Size.TB_PADDING.getSize());
        v.setAlignment(Pos.TOP_CENTER);
        v.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        v.prefHeightProperty().bind(myScene.heightProperty());
        v.prefWidthProperty().bind(myScene.widthProperty());
    }

    private void createSaver () {
        Label title = new Label(myResources.getString("workSaver"));
        v.getChildren().addAll(title);
        Button set = ButtonFactory.createButton(myResources.getString("save"), e->setPreferences());
        createComboBoxes();
        numTurtleField();
        v.getChildren().add(set);


    }

    private void setPreferences()  {
        XMLChooser xChoose = new XMLChooser();
        File file = xChoose.getFile(true);
        if(file==null){
            return;
        }
        WorkspaceXML wXML = new WorkspaceXML();
        
        try {
            wXML.saveConfig(file,getAllInput());
        }
        catch (Exception e) {

            showError();
        }
        stage.close();
    }
    private void showError () {
        error.set("");
        error.set(myResources.getString("wSpaceError"));
    }
    private List<String> getAllInput () {
        return Arrays.asList(getInput(bColor.getSelectionModel().getSelectedItem()),
                             getInput(pColor.getSelectionModel().getSelectedItem()),
                             getInput(pLangs.getSelectionModel().getSelectedItem()),
                             getInput(colorFile.getSelectionModel().getSelectedItem()),
                             getInput(imageFile.getSelectionModel().getSelectedItem()),
                             checkInput(tField.getText()));
                
    }
    private String checkInput (String text) {
        int temp;
        text = getInput(text);
        if(!text.equals("Default")){
            try{
                temp = Integer.parseInt(text);
                if(temp<0){
                    return "Default";
                }
                return text;
            }catch (Exception e){
                showError();
            }
        }
        return "Default";
    }
    
    
    private String getInput(String str) {
        if(str==null || str.length()==0){
            str = "Default";
        }
        return str;
    }
    private void createComboBoxes() {

        bColor = ComboFactory.createBox(myResources.getString("bColor"), colors, null);
        pColor = ComboFactory.createBox(myResources.getString("pColor"), colors, null);
        pLangs = ComboFactory.createBox(myResources.getString("selLang"), langs, null);
        colorFile = ComboFactory.createBox(myResources.getString("colorFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.COLORLIST_LOC.getDefault()), null);
        colorFile.getItems().add("Default");
        imageFile = ComboFactory.createBox(myResources.getString("imageFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.IMAGELIST_LOC.getDefault()), null);
        imageFile.getItems().add("Default");
        v.getChildren().addAll(bColor, pColor, pLangs, colorFile, imageFile);

    }


    private void numTurtleField(){
        tField = new TextField();
        tField.setPromptText(myResources.getString("numTurtles"));
        v.getChildren().add(tField);
    }



}
