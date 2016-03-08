package view.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.Defaults;

import java.io.File;
import java.util.List;

/**
 * This class is responsible for writing the users set workspace preferences to an XML file.
 * It is a subclass of the abstract XML writer class.
 *
 * @author Cali
 */

public class WorkspaceXML extends XMLWriter {
    private Document doc;
    private Element config;
    private List<String> params;

    /**
     * Saves set workspace preferences to a chosen XML file
     *
     * @param file   File object of chosen file to save to
     * @param params List<String> of parameters values to be saved
     * @throws Exception
     */
    public void saveConfig(File file, List<String> params) throws Exception {
        doc = buildDom(Defaults.WS_DOC_EL.getDefault());
        this.params = params;
        addElements();
        writeToFile(file);
    }

    /**
     * Adds all the String values in parameters as attributes of the XML file, using
     * the order and attribute names specified in the AttrNames WorkSpace enum
     */
    @Override
    protected void addElements() {
        config = doc.createElement(Defaults.WS_CHILD_EL.getDefault());
        doc.getDocumentElement().appendChild(config);
        for (int i = 0; i < params.size(); i++) {
            config.setAttribute(AttrNames.WORKSPACE.getNames().get(i), params.get(i));

        }

    }


}
