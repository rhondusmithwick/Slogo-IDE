package view.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import view.Defaults;

/**
 * Class is responsible for parsing workspace preferences xml file. It is a subclass
 * of the abstract XMLParser class.
 * @author Cali
 *
 */

public class LoadWS extends XMLParser {


    private static List<String> parameterDefaults = Arrays.asList(Defaults.TURT_BACKGROUND.getDefault(), 
                                                              Defaults.LANG.getDefault(), 
                                                              Defaults.DEFAULT.getDefault(), 
                                                              Defaults.DEFAULT.getDefault(), 
                                                              Defaults.NUM_TURTS.getDefault());

    private Document document;
    private List<String> parameters;

    /**
     * Gets user workspace preference parameters from an XML file.
     * If no file passed, it sets default preferences.
     * @param file File to load preferences from.
     */
    public void load(File file) {

        try {
            document = createDocBuilder(file);
            getElements();
        }
        catch (Exception e) {
            parameters = new ArrayList<>(parameterDefaults);
        }


    }

    /**
     * Gets the user preferences from the created dom tree, and adds them to the List<String> of user
     * preferences
     */
    @Override
    protected void getElements () {
        parameters = new ArrayList<>();
        Node config = document.getDocumentElement().getChildNodes().item(0);
        AttrNames.WORKSPACE.getNames().stream().forEach(e->parameters.add(config.getAttributes().getNamedItem(e).getTextContent()));


    }

    /**
     * returns the Preference associated with a certain Integer index. These indexes are set in the LoadIndex
     * enum in the view package. If no preference has been specified, the default value is set.
     * @param index index of preference to return
     * @return String representing user preference for given integer index
     */
    public String getParam(int index){
        if(parameters.get(index).equals(Defaults.DEFAULT.getDefault())){
            return parameterDefaults.get(index);
        }
        return parameters.get(index);
    }

}
