package view.xml;

import java.io.File;

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

/**
 * Abstract class that acts as a base for subclasses that write to XML files.
 * @author Cali
 *
 */

public abstract class XMLWriter {
	private Document doc;
	private Element root;
	
	/**
	 * Creates the Document object needed to create a Dom Tree
	 * @param title String name of root element of Dom tree
	 * @return Document object for new Dom Tree
	 * @throws Exception
	 */
	protected Document buildDom(String title) throws Exception{
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = fact.newDocumentBuilder();
		doc = build.newDocument();
		root = doc.createElement(title);
		doc.appendChild(root);
		return doc;
	}
	
	/**
	 * Writes currently created Dom Tree to a file
	 * @param file File to write Dom Tree to
	 * @throws Exception
	 */
	protected void writeToFile (File file) throws Exception {
		TransformerFactory trans = TransformerFactory.newInstance();
		Transformer tForm = trans.newTransformer();
		Result res = new StreamResult(file);
		Source sour = new DOMSource(doc);
		tForm.transform(sour, res);

	}
	
	/**
	 * add elements to newly created Dom Tree
	 */
	protected abstract void addElements();
}
