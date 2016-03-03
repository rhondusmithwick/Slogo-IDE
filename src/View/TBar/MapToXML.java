package View.TBar;

import java.io.File;
import java.util.Map.Entry;
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
import Maps.ColorMap;
import Maps.IndexMap;
import Observables.MapObservable;
import View.Defaults;

public class MapToXML {
    private String saveLocation;
    private MapObservable<Integer, String> map;
    private File file;
    private Document doc;
    private Element root;

    
    public MapToXML(){
        
    }
    
    public void saveMap(String fileName, IndexMap inMap) throws Exception{
        this.map = inMap.getIndexMap();
       
        getSaveLocation(inMap);
        this.file = new File(saveLocation +fileName+".xml");
        file.createNewFile();
        
        buildDom();
        addElements();
        writeToFile();
    }

    private void writeToFile () throws Exception {
        TransformerFactory trans = TransformerFactory.newInstance();
        Transformer tForm = trans.newTransformer();
        Result res = new StreamResult(file);
        Source sour = new DOMSource(doc);
        tForm.transform(sour, res);
        
    }

    private void buildDom () throws Exception {
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = fact.newDocumentBuilder();
        doc = build.newDocument();
        root = (Element) doc.createElement("IndexedMap");
        doc.appendChild(root);
        
        
        
    }

    private void addElements () {
        map.getEntrySet().stream().forEach(e->addToDoc(e));
        
    }

    private void addToDoc (Entry<Integer, String> e) {
        Element color = doc.createElement("Element");
        color.setAttribute("index", e.getKey().toString());
        color.setAttribute("name", e.getValue());
        root.appendChild(color);
    }

    private void getSaveLocation (IndexMap inMap) {
        if(inMap instanceof ColorMap){
            saveLocation = Defaults.COLORLIST_LOC.getDefault();
        }else{
            saveLocation = Defaults.IMAGELIST_LOC.getDefault();
        }
        
    }
}
