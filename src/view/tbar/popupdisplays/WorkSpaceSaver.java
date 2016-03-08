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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *Class responsible for allowing user to set and save workspace preferences to an xml file.
 *Is a subclass of abstract popup class.
 * @author Cali
 *
 */

public class WorkSpaceSaver extends PopUp{


    private ResourceBundle myResources;
    private TextField tField;
    private ArrayList<String> colors, langs;
    private ComboBox<String> bColor,pColor, pLangs, colorFile, imageFile;

    /**
     * creates new workspace saver instance
     * @param colors List of string of current possible colors
     * @param langs List of strings of current possible parsing languages
     */
    public WorkSpaceSaver(List<String> colors, List<String> langs){
    	super(Size.HTML_HEIGHT.getSize(), Size.HTML_WIDTH.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.langs = (ArrayList<String>) langs;
        this.colors=(ArrayList<String>) colors;
        this.myResources= ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
    }
    
    /**
     * creates all components needed for the updater popup and adds them to the popups scene.
     * Also sets event handlers to handle user input when set button is pressed, and to pass
     * set preferences to and XML writer.
     */
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

            return;
        }
        closeScene();
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
                return Defaults.DEFAULT.getDefault();
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
