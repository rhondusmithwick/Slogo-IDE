package view.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import view.Defaults;


public class LoadWS extends XMLParser {
    
    private static List<String> paramNames = Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts");
    private static List<String> paramDefaults = Arrays.asList("white", "black", "english", Defaults.DEFAULT.getDefault(), Defaults.DEFAULT.getDefault(), "1");
    private Document doc;
    private ArrayList<String> params;
    
    
    public void load(File file) throws Exception{
        doc = createDocBuilder(file);
        addElements();

    }
    
    @Override
    protected void addElements () {
        params = new ArrayList<>();
        Node config = doc.getDocumentElement().getChildNodes().item(0);
        paramNames.stream().forEach(e->params.add(config.getAttributes().getNamedItem(e).getTextContent()));
 
        
    }

    
    public String getParam(int index){
        if(params.get(index).equals(Defaults.DEFAULT.getDefault())){
            return paramDefaults.get(index);
        }
        return params.get(index);
    }

}
