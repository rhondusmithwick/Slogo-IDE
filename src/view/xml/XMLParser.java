package view.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Abstract class that provides a framework for subclass which read and parse XML files
 * @author Cali
 *
 */

public abstract class XMLParser {
	
	private Document document;

	/**
	 * Creates a new dom tree based on the elements and attributes of the passed XML file
	 * @param file File object of XML file to be parsed
	 * @return created Document object containing Dom tree representing the XML structure in File
	 * @throws Exception
	 */
	 protected Document createDocBuilder (File file) throws Exception {
	        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuild = documentBuilderFactory.newDocumentBuilder();
	        document = dBuild.parse(file);
	        return document;
	        
	 }
	 
	 /**
	  * Reads needed data from XML file and sets parameters based on the read data
	  */
	 protected abstract void getElements();



}


