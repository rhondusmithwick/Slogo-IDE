package view.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import view.Defaults;


public class LoadWS extends XMLParser {
    
    
    private static List<String> paramDefaults = Arrays.asList(Defaults.WHITE.getDefault(), 
    															Defaults.PEN_COLOR.getDefault(), 
    															Defaults.LANG.getDefault(), 
    															Defaults.DEFAULT.getDefault(), 
    															Defaults.DEFAULT.getDefault(), 
    															Defaults.NUM_TURTS.getDefault());
    
    private Document doc;
    private ArrayList<String> params;
    
    
    public void load(File file) {
        
        try {
            doc = createDocBuilder(file);
            addElements();
        }
        catch (Exception e) {
            params = new ArrayList<String>(paramDefaults);
        }
        

    }
    
    @Override
    protected void addElements () {
        params = new ArrayList<>();
        Node config = doc.getDocumentElement().getChildNodes().item(0);
        AttrNames.WORKSPACE.getNames().stream().forEach(e->params.add(config.getAttributes().getNamedItem(e).getTextContent()));
 
        
    }

    
    public String getParam(int index){
        if(params.get(index).equals(Defaults.DEFAULT.getDefault())){
            return paramDefaults.get(index);
        }
        return params.get(index);
    }

}
