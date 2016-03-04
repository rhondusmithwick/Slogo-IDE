package view.xml;

import java.io.File;
import java.util.Map.Entry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import maps.ColorMap;
import maps.IndexMap;
import observables.MapObservable;
import view.Defaults;

public class MapToXML extends XMLWriter {
    private String saveLocation;
    private MapObservable<Integer, String> map;
    private File file;
    private Document doc;

    
    public void saveMap(String fileName, IndexMap inMap) throws Exception{
        this.map = inMap.getIndexMap();
       
        getSaveLocation(inMap);
        this.file = new File(saveLocation +fileName+".xml");
        file.createNewFile();
        doc = buildDom("IndexedMap");
        addElements();
        writeToFile(file);
    }


    @Override
    protected void addElements () {
        map.getEntrySet().stream().forEach(e->addToDoc(e));
        
    }

    private void addToDoc (Entry<Integer, String> e) {
        Element color = doc.createElement("Element");
        color.setAttribute("index", e.getKey().toString());
        color.setAttribute("name", e.getValue());
        doc.getDocumentElement().appendChild(color);
    }

    private void getSaveLocation (IndexMap inMap) {
        if(inMap instanceof ColorMap){
            saveLocation = Defaults.COLORLIST_LOC.getDefault();
        }else{
            saveLocation = Defaults.IMAGELIST_LOC.getDefault();
        }
        
    }
}
