package View.TBar;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import View.Defaults;
import View.Size;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Button set = new Button(myResources.getString("save"));
		createComboBoxes();
		getPreferences();
		v.getChildren().add(set);


	}





	private void createComboBoxes() {
		// TODO Auto-generated method stub
		
	}
	private void getPreferences() {
		v.getChildren().addAll();
	}

}
