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

public abstract class XMLWriter {
	private Document doc;
	private Element root;
	
	
	protected Document buildDom(String title) throws Exception{
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = fact.newDocumentBuilder();
		doc = build.newDocument();
		root = doc.createElement(title);
		doc.appendChild(root);
		return doc;
	}
	
	protected void writeToFile (File file) throws Exception {
		TransformerFactory trans = TransformerFactory.newInstance();
		Transformer tForm = trans.newTransformer();
		Result res = new StreamResult(file);
		Source sour = new DOMSource(doc);
		tForm.transform(sour, res);

	}
	
	protected abstract void addElements();
}
