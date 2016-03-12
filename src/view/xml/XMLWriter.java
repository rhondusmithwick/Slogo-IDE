package view.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Abstract class that acts as a base for subclasses that write to XML files.
 *
 * @author Cali
 */

public abstract class XMLWriter {
    private Document document;

    /**
     * Creates the Document object needed to create a Dom Tree
     *
     * @param title String name of root element of Dom tree
     * @return Document object for new Dom Tree
     * @throws Exception
     */
    protected Document buildDom(String title) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = documentBuilderFactory.newDocumentBuilder();
        document = build.newDocument();
        Element root = document.createElement(title);
        document.appendChild(root);
        return document;
    }

    /**
     * Writes currently created Dom Tree to a file
     *
     * @param file File to write Dom Tree to
     * @throws Exception
     */
    protected void writeToFile(File file) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Result res = new StreamResult(file);
        Source sour = new DOMSource(document);
        transformer.transform(sour, res);

    }

    /**
     * add elements to newly created Dom Tree
     */
    protected abstract void addElements();
}
