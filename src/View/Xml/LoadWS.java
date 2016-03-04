package View.Xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class LoadWS {
    
    private static List<String> paramNames = Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts");
    private static List<String> paramDefaults = Arrays.asList("white", "black", "english", "default", "default", "1");
    private Document doc;
    private File file;
    private ArrayList<String> params;
    
    
    public void load(File file) throws Exception{
        this.file=file;
        createDocBuilder();
        addElements();

    }
    
    private void addElements () {
        params = new ArrayList<>();
        Node config = doc.getDocumentElement().getChildNodes().item(0);
        paramNames.stream().forEach(e->params.add(config.getAttributes().getNamedItem(e).getTextContent()));
 
        
    }

    private void createDocBuilder () throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuild = dbFactory.newDocumentBuilder();
        doc = dBuild.parse(file);
        
    }
    
    public String getParam(int index){
        if(params.get(index).equals("Default")){
            return paramDefaults.get(index);
        }
        return params.get(index);
    }

}
