package view.TBar;

import java.io.File;
import java.util.Observable;
import Maps.ColorMap;
import Maps.ImageMap;
import Observables.ObjectObservable;
import view.Defaults;
import view.Xml.LoadWS;
import view.Xml.XMLChooser;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;


public class TopBar extends SubBar {
    private static final String LOAD_ERROR = "workLoadError";
    private SimpleStringProperty image, penColor;
    private PaletteDisp cDisp, iDisp;
    
    private ComboBox<String> bColorBox;
    private ComboBox<String> pColorBox;
    private ObjectObservable<String> bgColor;


    public TopBar(ObjectObservable<String> language, ObjectObservable<String> bgColor,
                  SimpleStringProperty error, SimpleStringProperty image, SimpleStringProperty penColor, 
                  ObjectObservable<String> intCommand) {
        super(language, error, intCommand);
        this.image=image;
        this.bgColor = bgColor;
        this.penColor=penColor;
        cDisp = new ColorDisplay("colorTitle");
        iDisp = new ImageDisplay("imageTitle");
        

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
            XMLChooser xChoose = new XMLChooser();
            File file = xChoose.getFile(false);
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
        bgColor.set(wsLoader.getParam(0).toLowerCase());
        penColor.set(wsLoader.getParam(1).toLowerCase());
        setParsingLanguage(Defaults.PARSELANG_LOC.getDefault()+wsLoader.getParam(2));
        setMaps(wsLoader);
        setTurts(wsLoader);
        
        
        
    }

    private void setTurts (LoadWS wsLoader) {
        int num= Integer.parseInt(wsLoader.getParam(5));
        String comm = getCommand("Tell") + " "+Integer.toString(num);
        passCommand(comm);

        
    }

    private void setMaps (LoadWS wsLoader) {
        try {
            System.out.println(wsLoader.getParam(3));
            ImageMap.getInstance().addElements(wsLoader.getParam(4));
            ColorMap.getInstance().addElements(wsLoader.getParam(3));
        }
        catch (Exception e) {
            e.printStackTrace();
            showError(LOAD_ERROR);
        }
    }

    private void showImagePalette() {
        try {
            iDisp.createDisp();
        } catch (Exception e) {
            showError("imagePalError");
        }
    }

    private void showColorPalette() {
        try {
            cDisp.createDisp();
        } catch (Exception e) {
            e.printStackTrace();
            showError("colorPalError");
        }
    }

    private void chooseTurtIm() {
        try {
            ImageChooser imChoose = new ImageChooser();
            String newImage = imChoose.chooseTurtIm();
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
