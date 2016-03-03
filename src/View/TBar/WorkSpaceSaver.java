package View.TBar;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import View.Defaults;
import View.Size;
import View.utilities.ButtonFactory;
import View.utilities.ComboFactory;
import View.utilities.FileGetter;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkSpaceSaver {


	private ResourceBundle myResources;
	private TextField tField;
	private VBox v;
	private Scene myScene;
	private ArrayList<String> colors, langs;
	private ComboBox<String> bColor,pColor, pLangs, colorFile, imageFile; //variableFile;

	public WorkSpaceSaver(List<String> colors, List<String> langs ){
		this.langs = (ArrayList<String>) langs;
		this.colors=(ArrayList<String>) colors;
		this.myResources= ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
	}
	public void showSaver(){
		myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		Group root;
		root = new Group();
		Stage stage = new Stage();
		myScene = new Scene(root, Size.HTML_WIDTH.getSize(), Size.HTML_HEIGHT.getSize());
		setVBox();
		createSaver();
		root.getChildren().add(v);
		stage.setScene(myScene);
		stage.show();
	}

	private void setVBox () {
		v = new VBox(Size.TB_PADDING.getSize());
		v.setAlignment(Pos.TOP_CENTER);
		v.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
		v.prefHeightProperty().bind(myScene.heightProperty());
		v.prefWidthProperty().bind(myScene.widthProperty());
	}

	private void createSaver () {
		Label title = new Label(myResources.getString("workSaver"));
		v.getChildren().addAll(title);
		Button set = ButtonFactory.createButton("save", e->setPreferences());
		createComboBoxes();
		numTurtleField();
		v.getChildren().add(set);


	}

	private Object setPreferences() {
		// TODO Auto-generated method stub
		return null;
	}
	private void createComboBoxes() {
		bColor = ComboFactory.createBox(myResources.getString("bColor"), colors, null);
		pColor = ComboFactory.createBox(myResources.getString("pColor"), colors, null);
		pLangs = ComboFactory.createBox(myResources.getString("selLang"), langs, null);
		colorFile = ComboFactory.createBox(myResources.getString("colorFile"), 
				FileGetter.getAllFromDirectory(Defaults.COLORLIST_LOC.getDefault()), null);
		imageFile = ComboFactory.createBox(myResources.getString("imageFile"), 
				FileGetter.getAllFromDirectory(Defaults.IMAGELIST_LOC.getDefault()), null);
		v.getChildren().addAll(bColor, pColor, pLangs, colorFile, imageFile);
		
	}
	
	private void numTurtleField(){
		tField = new TextField();
		tField.setPromptText(myResources.getString("numTurtles"));
		v.getChildren().add(tField);
	}
	


}
