package view;

import java.io.File;

import main.GlobalProperties;
import maps.IndexMap;
import observables.ObjectObservable;
import view.utilities.GetCommand;
import view.xml.LoadWS;
import view.xml.XMLChooser;


/**
 * this class is repsonsible for opening a file loader, and
 *  loading workspace preferences from a chosen xml file or setting default settings
 *  if no file is chosen
 *  @author calinelson
 */
public class PreferenceSetter {
    private ObjectObservable<String> pLang;
    private ObjectObservable<String> backgroundColor;
    private ObjectObservable<String> intCommands;
    private IndexMap cMap;
    private IndexMap iMap;

    /**
     * creates a new preference setter object
     * @param penColor simplestringproperty for pencolor
     * @param pLang observable string for parsing language
     * @param cMap color map object
     * @param iMap index map object
     * @param backgroundColor observable string for background color
     * @param intCommands observable string for passing internal commands
     */
    public PreferenceSetter (GlobalProperties globalProperties, ObjectObservable<String> intCommands) {
        this.pLang = globalProperties.getLanguage();
        this.cMap=globalProperties.getColorMap();
        this.iMap=globalProperties.getImageMap();
        this.backgroundColor= globalProperties.getBackgroundColor();
        this.intCommands=intCommands;
    }

    /**
     * Opens a file loader and allows user to choose xml file to load, then
     * passes xml file to parser to be read. After the file is read it then 
     * sets the appropriate parameters in the workspace based on the xml 
     * file chosen.
     */
    public void setPreferences () {
        XMLChooser xChoose = new XMLChooser(false);
        xChoose.show();
        File file = xChoose.getFile();
        LoadWS wsLoader = new LoadWS();
        wsLoader.load(file);
        setParams(wsLoader);

    }





    private void setParams (LoadWS wsLoader) {
        backgroundColor.set(wsLoader.getParam(LoadIndex.BG_COLOR.getIndex()).toLowerCase());
        pLang.set(Defaults.PARSELANG_LOC.getDefault()+wsLoader.getParam(LoadIndex.P_LANG.getIndex()));
        setMaps(wsLoader);
        setTurts(wsLoader);

    }

    private void setTurts (LoadWS wsLoader) {
        int num= Integer.parseInt(wsLoader.getParam(LoadIndex.NUM_TURT.getIndex()));
        String turtList = "";
        for(int i=1; i<=num; i++){
        	turtList = turtList + Integer.toString(i) + " ";
        }
        String comm = GetCommand.makeCommand("Tell", pLang.get()) + " [ " + turtList + "]";
        intCommands.set(comm);


    }

    private void setMaps (LoadWS wsLoader) {
        try {
            iMap.addElements(wsLoader.getParam(LoadIndex.I_FILE.getIndex()));
            cMap.addElements(wsLoader.getParam(LoadIndex.C_FILE.getIndex()));
        }
        catch (Exception e) {
            return;
        }
    }
}