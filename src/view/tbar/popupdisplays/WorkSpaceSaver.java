package view.tbar.popupdisplays;

import java.io.File;
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
    private TextField textField;
    private List<String> colors, languages;
    private ComboBox<String> backgroundColor, parsingLanguage, colorFile, imageFile;

    /**
     * creates new workspace saver instance
     * @param colors List of string of current possible colors
     * @param langs List of strings of current possible parsing languages
     */
    public WorkSpaceSaver(List<String> colors, List<String> langs){
    	super(Size.HTML_HEIGHT.getSize(), Size.HTML_WIDTH.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
        this.languages =  langs;
        this.colors= colors;
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
        WorkspaceXML workspaceXML = new WorkspaceXML();
        
        try {
            workspaceXML.saveConfig(file,getAllInput());
        }
        catch (Exception e) {

            return;
        }
        closeScene();
    }
    
    
    private List<String> getAllInput () {
        return Arrays.asList(getInput(backgroundColor.getSelectionModel().getSelectedItem()),
                             getInput(parsingLanguage.getSelectionModel().getSelectedItem()),
                             getInput(colorFile.getSelectionModel().getSelectedItem()),
                             getInput(imageFile.getSelectionModel().getSelectedItem()),
                             checkInput(textField.getText()));
                
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
    
    
    private String getInput(String input) {
        if(input==null || input.length()==0){
            input = Defaults.DEFAULT.getDefault();
        }
        return input;
    }
    
    private void createComboBoxes() {

        backgroundColor = ComboFactory.createBox(myResources.getString("bColor"), colors, null);
        parsingLanguage = ComboFactory.createBox(myResources.getString("selLang"), languages, null);
        colorFile = ComboFactory.createBox(myResources.getString("colorFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.COLORLIST_LOC.getDefault()), null);
        colorFile.getItems().add(Defaults.DEFAULT.getDefault());
        imageFile = ComboFactory.createBox(myResources.getString("imageFile"), 
                                           FileGetter.getAllFromDirectory(Defaults.IMAGELIST_LOC.getDefault()), null);
        imageFile.getItems().add(Defaults.DEFAULT.getDefault());
        addNodes(Arrays.asList(backgroundColor, parsingLanguage, colorFile, imageFile));

    }


    private void numTurtleField(){
        textField = new TextField();
        textField.setPromptText(myResources.getString("numTurtles"));
        addNodes(Arrays.asList(textField));
    }




}
