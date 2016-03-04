package view.xml;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import observables.MapObservable;


public class MapFromXML extends XMLParser {
    
    private File file;
    private Document doc;
    
    private MapObservable<Integer, String> map;

    public MapFromXML(MapObservable<Integer, String> map){
        this.map = map;
    }
    
    public void getElements(String fileName) throws Exception{
        file = new File(fileName + ".xml");
        doc =createDocBuilder(file);
        addElements();
        
    }
    
    @Override
    protected void addElements () {
        NodeList elements = doc.getDocumentElement().getChildNodes();
        Node curr;
        for(int i=0; i<elements.getLength(); i++){
            curr = elements.item(i);
            String index = curr.getAttributes().getNamedItem(AttrNames.INDEX_MAP.getNames().get(0)).getTextContent();
            String name = curr.getAttributes().getNamedItem(AttrNames.INDEX_MAP.getNames().get(1)).getTextContent();
            map.put(Integer.parseInt(index), name);
        }
        
        
    }

   
}
