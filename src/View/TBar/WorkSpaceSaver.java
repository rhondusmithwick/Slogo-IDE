package View.TBar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import View.Defaults;
import View.Size;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class WorkSpaceSaver {
	
	private ArrayList<String> colors, langs;
	private ResourceBundle myResources;
	private TextField tField;
	private VBox v;
	private Scene myScene;
	
	public WorkSpaceSaver(List<String> colors, List<String> langs ){
		this.colors=(ArrayList<String>) colors;
		this.langs=(ArrayList<String>) langs;
		this.myResources= ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		createSaver();
	}

    private void createSaver () {
        setVBox();
        Label title = new Label(myResources.getString("saverTitle"));
        tField = new TextField();
        tField.prefWidthProperty().bind(myScene.widthProperty());
        Button set = new Button(myResources.getString("save"));
        v.getChildren().addAll(title, tField, set);

    }


    private void setVBox () {
        v = new VBox(Size.TB_PADDING.getSize());
        v.setAlignment(Pos.TOP_CENTER);
        v.setStyle(Defaults.BACKGROUND_COLOR.getDefault());
        v.prefHeightProperty().bind(myScene.heightProperty());
        v.prefWidthProperty().bind(myScene.widthProperty());
    }
}
