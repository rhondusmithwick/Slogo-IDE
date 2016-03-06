package view.xml;

import java.io.File;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import view.Defaults;

public class WorkspaceXML extends XMLWriter {
	private Document doc;
	private Element config;
	private List<String> params;
	

	public void saveConfig(File file, List<String> params) throws Exception{
		doc = buildDom(Defaults.WS_DOC_EL.getDefault());
		this.params=  params;
		addElements();
		writeToFile(file);
	}

	@Override
	protected void addElements () {
		config = doc.createElement(Defaults.WS_CHILD_EL.getDefault());
		doc.getDocumentElement().appendChild(config);
		for(int i=0; i<params.size();i++){
			config.setAttribute(AttrNames.WORKSPACE.getNames().get(i), params.get(i));

		}

	}





	
}
