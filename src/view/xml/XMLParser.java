package view.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public abstract class XMLParser {
	
	private Document doc;

	 protected Document createDocBuilder (File file) throws Exception {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuild = dbFactory.newDocumentBuilder();
	        doc = dBuild.parse(file);
	        return doc;
	        
	 }
	 
	 protected abstract void addElements();



}


