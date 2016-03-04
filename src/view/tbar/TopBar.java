package view.tbar;

import java.io.File;
import java.util.Observable;

import view.Defaults;
import view.LoadIndex;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.ImageDisplay;
import view.utilities.PopUp;
import view.xml.LoadWS;
import view.xml.XMLChooser;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;


public class TopBar extends SubBar {
    private static final String LOAD_ERROR = "workLoadError";
    private SimpleStringProperty image, penColor;
    private PopUp cDisp, iDisp;
    
    private ComboBox<String> bColorBox;
    private ComboBox<String> pColorBox;
    private ObjectObservable<String> bgColor;
	private ColorMap cMap;
	private ImageMap iMap;


    public TopBar(ObjectObservable<String> language, ObjectObservable<String> bgColor,
                  SimpleStringProperty error, SimpleStringProperty image, SimpleStringProperty penColor, 
                  ObjectObservable<String> intCommand, ColorMap cMap, ImageMap iMap) {
        super(language, error, intCommand, cMap);
        this.image=image;
        this.bgColor = bgColor;
        this.penColor=penColor;
        cDisp = new ColorDisplay("colorTitle", error, cMap.getIndexMap());
        iDisp = new ImageDisplay("imageTitle", error, iMap.getIndexMap());
        this.iMap = iMap;
        this.cMap = cMap;

    }

    @Override
    protected void createComboBoxes() {
        bColorBox = createComboBox("bColor", getColors(), e -> setBackground());
        pColorBox = createComboBox("pColor", getColors(), e -> setPColor());

    }

    private void setPColor() {
        String pColor = pColorBox.getSelectionModel().getSelectedItem();
        penColor.set(pColor.toLowerCase());
        
    }

    private void setBackground() {
        String bColor = bColorBox.getSelectionModel().getSelectedItem();
        bgColor.set(bColor.toLowerCase());

    }

    @Override
    protected void createButtons() {
        makeButton("workLoader" ,e->loadWorkSpace());
        makeButton("image", e -> chooseTurtIm());
        makeButton("colorDisp", e -> showColorPalette());
        makeButton("imageDisp", e -> showImagePalette());
        



    }

    
    private void loadWorkSpace () {
       
        try {
        	XMLChooser xChoose = new XMLChooser(false);
        	xChoose.show();
            File file = xChoose.getFile();
            LoadWS wsLoader = new LoadWS();
            wsLoader.load(file);
            setParams(wsLoader);
            
        }
        catch (Exception e) {
            e.printStackTrace();
            showError(LOAD_ERROR);
        }
    }

    
    
    

    private void setParams (LoadWS wsLoader) {
        bgColor.set(wsLoader.getParam(LoadIndex.BG_COLOR.getIndex()).toLowerCase());
        penColor.set(wsLoader.getParam(LoadIndex.P_COLOR.getIndex()).toLowerCase());
        setParsingLanguage(Defaults.PARSELANG_LOC.getDefault()+wsLoader.getParam(LoadIndex.P_LANG.getIndex()));
        setMaps(wsLoader);
        setTurts(wsLoader);
        
        
        
    }

    private void setTurts (LoadWS wsLoader) {
        int num= Integer.parseInt(wsLoader.getParam(LoadIndex.NUM_TURT.getIndex()));
        String comm = getCommand("Tell") + " "+Integer.toString(num);
        passCommand(comm);

        
    }

    private void setMaps (LoadWS wsLoader) {
        try {
            iMap.addElements(wsLoader.getParam(LoadIndex.I_FILE.getIndex()));
            cMap.addElements(wsLoader.getParam(LoadIndex.C_FILE.getIndex()));
        }
        catch (Exception e) {
            showError(LOAD_ERROR);
        }
    }

    private void showImagePalette() {
            iDisp.show();

    }

    private void showColorPalette() {
            cDisp.show();

    }

    private void chooseTurtIm() {
        try {
            ImageChooser imChoose = new ImageChooser();
            imChoose.show();
            String newImage = imChoose.getChosen();
            if (newImage != null) {
                image.set(newImage);
            }
        } catch (Exception e) {
            showError("picError");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        getContainer().getChildren().removeAll(bColorBox, pColorBox);
        createComboBoxes();

    }

}
