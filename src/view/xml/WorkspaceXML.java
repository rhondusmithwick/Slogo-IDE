package view.xml;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WorkspaceXML extends XMLWriter {
	private Document doc;
	private Element config;
	private List<String> params;
	private static List<String> paramNames = Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts");

	public void saveConfig(File file, List<String> params) throws Exception{
		doc = buildDom("WorkspaceConfig");
		this.params=  params;
		addElements();
		writeToFile(file);
	}

	@Override
	protected void addElements () {
		config = doc.createElement("ConfigProps");
		doc.getDocumentElement().appendChild(config);
		for(int i=0; i<params.size();i++){
			config.setAttribute(paramNames.get(i), params.get(i));

		}

	}





	
}
