package View.TBar;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import View.Defaults;
import View.Size;
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
	private ComboBox<String> langs;
	private ComboBox<String> pColors, bColors;

	public WorkSpaceSaver(ComboBox<String> pColors, ComboBox<String> bColors, ComboBox<String> langs ){
		this.pColors= pColors;
		this.bColors=bColors;
		this.langs= langs;
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
		Button set = new Button(myResources.getString("save"));
		getPreferences();
		v.getChildren().add(set);


	}

	private void getAllFromDirectory (String directoryLocation) {
		ArrayList<String> files = new ArrayList<>();
		File directory = new File(directoryLocation);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			String name = file.getName();
			files.add(name.substring(0, name.lastIndexOf('.')));
		}
	}



	private void getPreferences() {
		v.getChildren().addAll(bColors,pColors,langs);
	}

}
