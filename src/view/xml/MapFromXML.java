package view.xml;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import observables.MapObservable;
import view.Defaults;

/**
 * Creates IndexMap object from preferences written in XML file.
 * Subclass of abstract XMLParser class.
 * @author Cali
 *
 */

public class MapFromXML extends XMLParser {
    
    private File file;
    private Document document;
    
    private MapObservable<Integer, String> map;

    /**
     * creates new MapFromXML instance
     * @param map MapObservable object to be populated by file data
     */
    public MapFromXML(MapObservable<Integer, String> map){
        this.map = map;
    }
    
    /**
     * Populates the MapObservable object with data read from xml file
     * @param fileName String filename of file to be read and parsed into dom tree
     * @throws Exception
     */
    public void populateMap(String fileName) throws Exception{
        file = new File(fileName + Defaults.XML.getDefault());
        document =createDocBuilder(file);
        getElements();
        
    }
    
    /**
     * Gets the elements from the created dom tree and adds them to the given MapObservable object from the constructor.
     */
    @Override
    protected void getElements () {
        NodeList elements = document.getDocumentElement().getChildNodes();
        Node curr;
        for(int i=0; i<elements.getLength(); i++){
            curr = elements.item(i);
            String index = curr.getAttributes().getNamedItem(AttrNames.INDEX_MAP.getNames().get(0)).getTextContent();
            String name = curr.getAttributes().getNamedItem(AttrNames.INDEX_MAP.getNames().get(1)).getTextContent();
            map.put(Integer.parseInt(index), name);
        }
        
        
    }

   
}
