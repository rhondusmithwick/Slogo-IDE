package view.xml;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WorkspaceXML {
    private Document doc;
    private Element root, config;
    private List<String> params;
    private static List<String> paramNames = Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts");
    private File file;
    
    public void saveConfig(File file, List<String> params) throws Exception{
        buildDom();
        this.file=file;
        this.params=  params;
        addElements();
        writeToFile();
    }
    
    private void addElements () {
        for(int i=0; i<params.size();i++){
            config.setAttribute(paramNames.get(i), params.get(i));
            
        }

    }


    private void buildDom () throws Exception {
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = fact.newDocumentBuilder();
        doc = build.newDocument();
        root = doc.createElement("WorkSpaceConfiguration");
        config = doc.createElement("ConfigProps");
        doc.appendChild(root);
        root.appendChild(config);
 
    }
    
    
    private void writeToFile () throws Exception {
        TransformerFactory trans = TransformerFactory.newInstance();
        Transformer tForm = trans.newTransformer();
        Result res = new StreamResult(file);
        Source sour = new DOMSource(doc);
        tForm.transform(sour, res);
        
    }
}
