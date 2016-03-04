package view.utilities;

import java.io.File;
import java.util.List;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.Size;

public abstract class PopUp {
	private int height;
	private int width; 
	private Stage s;
	private Scene myScene;
	private Group root;
	private VBox vBox;
	private String backgroundColor;
	public PopUp(int height, int width, String backgroundColor){
		this.height=height;
		this.width = width;
		this.backgroundColor=backgroundColor;
	}
	
	
	public void show(){
		s = new Stage();
		root = new Group();
		myScene = new Scene(root, height, width);
		createContainer();
		createScene();
		showScene();
	}


	protected abstract void createScene();


	protected void createContainer() {
		vBox = new VBox(Size.POP_UP_PADDING.getSize());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle(backgroundColor);
        vBox.prefHeightProperty().bind(myScene.heightProperty());
        vBox.prefWidthProperty().bind(myScene.widthProperty());
        root.getChildren().add(vBox);
		
	}


	private void showScene() {
		
		s.setScene(myScene);
		s.show();
		
	}
	
	protected File showFChooser(FileChooser fChoose, boolean save){
		File file;
		if(save){
			file= fChoose.showSaveDialog(s);
		}else{
			file = fChoose.showOpenDialog(s);
		}
		return file;
		
	}
	
	protected void closeScene(){
		s.close();
	}
	
	protected void addNodes(List<Node> nodeList){
		vBox.getChildren().addAll(nodeList);
	}
	
	protected void addContainer(Node container){
		root.getChildren().remove(vBox);
		root.getChildren().add(container);
	}
	
	protected ReadOnlyDoubleProperty getSize(boolean height){
		if(height){
			return myScene.heightProperty();
		}
		return myScene.widthProperty();
	}
	
	
}
