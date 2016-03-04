package view.tbar.popupdisplays;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import view.Defaults;
import view.Size;
import view.xml.WorkspaceXML;
import view.xml.XMLChooser;
import view.utilities.ButtonFactory;
import view.utilities.ComboFactory;
import view.utilities.FileGetter;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WorkSpaceSaver extends PopUp{


    private ResourceBundle myResources;
    SimpleStringProperty error;
    private TextField tField;
    private ArrayList<String> colors, langs;
    private ComboBox<String> bColor,pColor, pLangs, colorFile, imageFile;

    public WorkSpaceSaver(List<String> colors, List<String> langs , SimpleStringProperty error){
    	super(Size.HTML_HEIGHT.getSize(), Size.HTML_WIDTH.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.langs = (ArrayList<String>) langs;
        this.error=error;
        this.colors=(ArrayList<String>) colors;
        this.myResources= ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }
    
    @Override
    protected void createScene () {
        Label title = new Label(myResources.getString("workSaver"));
        addNodes(Arrays.asList(title));
        Button set = ButtonFactory.createButton(myResources.getString("save"), e->setPreferences());
        createComboBoxes();
        numTurtleField();
        addNodes(Arrays.asList(set));

    }

    private void setPreferences()  {
        XMLChooser xChoose = new XMLChooser(true);
        xChoose.show();
        File file = xChoose.getFile();
        if(file==null){
        	closeScene();
            return;
        }
        WorkspaceXML wXML = new WorkspaceXML();
        
        try {
            wXML.saveConfig(file,getAllInput());
        }
        catch (Exception e) {

            showError();
        }
        closeScene();
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
        if(!text.equals(Defaults.DEFAULT.getDefault())){
            try{
                temp = Integer.parseInt(text);
                if(temp<0){
                    return Defaults.DEFAULT.getDefault();
                }
                return text;
            }catch (Exception e){
                showError();
            }
        }
        return Defaults.DEFAULT.getDefault();
    }
    
    
    private String getInput(String str) {
        if(str==null || str.length()==0){
            str = Defaults.DEFAULT.getDefault();
        }
        return str;
    }
    
    private void createComboBoxes() {

        bColor = ComboFactory.createBox(myResources.getString("bColor"), colors, null);
        pColor = ComboFactory.createBox(myResources.getString("pColor"), colors, null);
        pLangs = ComboFactory.createBox(myResources.getString("selLang"), langs, null);
        colorFile = ComboFactory.createBox(myResources.getString("colorFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.COLORLIST_LOC.getDefault()), null);
        colorFile.getItems().add(Defaults.DEFAULT.getDefault());
        imageFile = ComboFactory.createBox(myResources.getString("imageFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.IMAGELIST_LOC.getDefault()), null);
        imageFile.getItems().add(Defaults.DEFAULT.getDefault());
        addNodes(Arrays.asList(bColor, pColor, pLangs, colorFile, imageFile));

    }


    private void numTurtleField(){
        tField = new TextField();
        tField.setPromptText(myResources.getString("numTurtles"));
        addNodes(Arrays.asList(tField));
    }




}
