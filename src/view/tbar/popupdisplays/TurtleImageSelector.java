package view.tbar.popupdisplays;


import java.net.MalformedURLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;
import view.Defaults;
import view.utilities.ButtonFactory;

public class TurtleImageSelector extends TurtlePropertyUpdater {
    
    private String newImage;
    private ImageMap imageMap;
    
    public TurtleImageSelector(SimpleStringProperty turtleIDs,
                               ObjectObservable<String> internalCommand, ObjectObservable<String> parsingLanguage, IndexMap imageMap){
        super(turtleIDs, internalCommand, parsingLanguage, "image");
        this.imageMap=(ImageMap) imageMap;
    }
    
    
    @Override
    protected void createElementsBelowCheckBoxes () {
        Button chooseFile = ButtonFactory.createButton(getStringFromResources("image"), e->openImageChooser());
        addToScene(chooseFile);
    }




    private void openImageChooser () {
        ImageChooser imChoose = new ImageChooser();
        imChoose.show();
        try {
            newImage = imChoose.getChosen();
            int index = newImage.indexOf(Defaults.IMAGE_LOC.getDefault());
            newImage = newImage.substring(index);
        }
        catch (MalformedURLException e) {
       
            return;
        }
    }


    @Override
    protected String makeCommand (String turtleIDs) {
        int index = imageMap.getIndex(newImage);
        String askCommand = translateCommand("Ask");
        String shapeCommand = translateCommand("SetShape");
        return askCommand + " [ " + turtleIDs + "] [ " + shapeCommand + " " + Integer.toString(index) + " ]";
    }

}
