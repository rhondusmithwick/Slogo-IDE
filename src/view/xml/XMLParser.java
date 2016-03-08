package view.xml;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Abstract class that provides a framework for subclass which read and parse XML files
 *
 * @author Cali
 */

public abstract class XMLParser {

    private Document doc;

    /**
     * Creates a new dom tree based on the elements and attributes of the passed XML file
     *
     * @param file File object of XML file to be parsed
     * @return created Document object containing Dom tree representing the XML structure in File
     * @throws Exception
     */
    protected Document createDocBuilder(File file) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuild = dbFactory.newDocumentBuilder();
        doc = dBuild.parse(file);
        return doc;

    }

    /**
     * Reads needed data from XML file and sets parameters based on the read data
     */
    protected abstract void getElements();


}


