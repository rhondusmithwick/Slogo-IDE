package view.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import observables.MapObservable;


public class MapFromXML {
    
    private File file;
    private Document doc;
    
    private MapObservable<Integer, String> map;

    public MapFromXML(MapObservable<Integer, String> map){
        this.map = map;
    }
    
    public void getElements(String fileName) throws Exception{
        file = new File(fileName + ".xml");
        createDocBuilder();
        addElements();
        
    }

    private void addElements () {
        NodeList elements = doc.getDocumentElement().getChildNodes();
        Node curr;
        for(int i=0; i<elements.getLength(); i++){
            curr = elements.item(i);
            String index = curr.getAttributes().getNamedItem("index").getTextContent();
            String name = curr.getAttributes().getNamedItem("name").getTextContent();
            map.put(Integer.parseInt(index), name);
        }
        
        
    }

    private void createDocBuilder () throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuild = dbFactory.newDocumentBuilder();
        doc = dBuild.parse(file);
        
    }
}
