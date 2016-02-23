

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTester extends Application{
	
	private Scene myScene;
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		myScene = new Scene(root, 1000, 750);
		View v = new View(root);
		primaryStage.setScene(myScene);
		primaryStage.show();
		
	}

}
