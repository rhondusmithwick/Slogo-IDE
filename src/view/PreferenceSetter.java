package view;
/**
 * this class is repsonsible for opening a file loader, and
 *  loading workspace preferences from a chosen xml file or setting default settings
 *  if no file is chosen
 *  @author calinelson
 */
import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import maps.IndexMap;
import observables.ObjectObservable;
import view.utilities.GetCommand;
import view.xml.LoadWS;
import view.xml.XMLChooser;

public class PreferenceSetter {
    public ObjectObservable<String> pLang;
    public ObjectObservable<String> backgroundColor;
    public ObjectObservable<String> intCommands;
    public SimpleStringProperty penColor;
    public IndexMap cMap;
    public IndexMap iMap;

    public PreferenceSetter (SimpleStringProperty penColor, ObjectObservable<String> pLang, IndexMap cMap, IndexMap iMap, ObjectObservable<String> backgroundColor, ObjectObservable<String> intCommands) {
        this.penColor = penColor;
        this.pLang=pLang;
        this.cMap=cMap;
        this.iMap=iMap;
        this.backgroundColor=backgroundColor;
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
        penColor.set(wsLoader.getParam(LoadIndex.P_COLOR.getIndex()).toLowerCase());
        pLang.set(Defaults.PARSELANG_LOC.getDefault()+wsLoader.getParam(LoadIndex.P_LANG.getIndex()));
        setMaps(wsLoader);
        setTurts(wsLoader);
        
    }
    
    private void setTurts (LoadWS wsLoader) {
        int num= Integer.parseInt(wsLoader.getParam(LoadIndex.NUM_TURT.getIndex()));
        String comm = GetCommand.makeCommand("Tell", pLang.get()) + " "+Integer.toString(num);
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