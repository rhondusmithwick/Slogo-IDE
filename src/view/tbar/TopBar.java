package view.tbar;
import java.util.Observable;
import view.tbar.popupdisplays.ColorDisplay;
import view.tbar.popupdisplays.ImageChooser;
import view.tbar.popupdisplays.ImageDisplay;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;


public class TopBar extends SubBar {

    private SimpleStringProperty image, penColor;
    private PopUp cDisp, iDisp;
    private ComboBox<String> bColorBox;
    private ComboBox<String> pColorBox;
    private ObjectObservable<String> bgColor;



    public TopBar(ObjectObservable<String> language, ObjectObservable<String> bgColor,
                  SimpleStringProperty image, SimpleStringProperty penColor, 
                  ObjectObservable<String> intCommand, ColorMap cMap, ImageMap iMap) {
        super(language, intCommand, cMap);
        this.image=image;
        this.bgColor = bgColor;
        this.penColor=penColor;
        cDisp = new ColorDisplay("colorTitle", cMap.getIndexMap());
        iDisp = new ImageDisplay("imageTitle",  iMap.getIndexMap());


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
        makeButton("image", e -> chooseTurtIm());
        makeButton("colorDisp", e -> showColorPalette());
        makeButton("imageDisp", e -> showImagePalette());
    }

    private void showImagePalette() {
        iDisp.show();
    }

    private void showColorPalette() {
        cDisp.show();
    }

    private void chooseTurtIm() {
        
            ImageChooser imChoose = new ImageChooser();
            imChoose.show();
            String newImage;
            try {
                newImage = imChoose.getChosen();
                if(newImage!=null){
                    image.set(newImage);
                }
            }
            catch (Exception e) {
                return;
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        getContainer().getChildren().removeAll(bColorBox, pColorBox);
        createComboBoxes();
    }

}
