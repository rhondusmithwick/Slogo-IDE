package view.xml;

import javafx.collections.ObservableMap;
import maps.ColorMap;
import maps.IndexMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.Defaults;

import java.io.File;
import java.util.Map.Entry;

/**
 * Class is a subclass of the XML writer class and is responsible for writing
 * the entries of an index map into an XML file.
 *
 * @author Cali
 */

public class MapToXML extends XMLWriter {
    private String saveLocation;
    private ObservableMap<Integer, String> map;
    private Document document;

    /**
     * Saves contents of the given indexMap as an XML file with the given name
     *
     * @param fileName String name of file to be saved to
     * @param inMap    IndexMap object to be saved
     * @throws Exception
     */
    public void saveMap(String fileName, IndexMap indexMap) throws Exception {
        this.map = indexMap.getIndexMap();

        getSaveLocation(indexMap);
        File file = new File(saveLocation + fileName + Defaults.XML.getDefault());
        file.createNewFile();
        document = buildDom(Defaults.IM_DOC_EL.getDefault());
        addElements();
        writeToFile(file);
    }

    /**
     * Adds entries of the IndexMap as elements of the newly created Dom tree using the
     * Attribute Names specified in the Indexmap AttrNames Enum
     */
    @Override
    protected void addElements() {
        map.entrySet().stream().forEach(this::addToDoc);

    }

    private void addToDoc(Entry<Integer, String> e) {
        Element color = document.createElement(Defaults.IM_CHILD_ELEMENT.getDefault());
        color.setAttribute(AttrNames.INDEX_MAP.getNames().get(0), e.getKey().toString());
        color.setAttribute(AttrNames.INDEX_MAP.getNames().get(1), e.getValue());
        document.getDocumentElement().appendChild(color);
    }

    private void getSaveLocation(IndexMap indexMap) {
        if (indexMap instanceof ColorMap) {
            saveLocation = Defaults.COLORLIST_LOC.getDefault();
        } else {
            saveLocation = Defaults.IMAGELIST_LOC.getDefault();
        }

    }
}
