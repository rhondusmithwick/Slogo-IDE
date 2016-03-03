package View.TBar;

import Observables.ObjectObservable;
import View.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

public class ToolBar  {

	private VBox container;
	private SubBar topBar, botBar;





	public ToolBar(ObjectObservable<String> language, SimpleStringProperty error, ObjectObservable<String> bgColor,
			SimpleStringProperty image, SimpleStringProperty penColor, ObjectObservable<String> intCommand) {
		
		setToolBar();
		topBar = new TopBar(language, bgColor, error, image, penColor);
		botBar = new BottomBar(language,error, intCommand);
		setBars();
	}

	private void setBars() {
		container.getChildren().addAll(topBar.getContainer(), botBar.getContainer());
		
	}

	private void setToolBar() {
		initTBContainer();
	}


	private void initTBContainer() {
		container = new VBox(Size.TB_PADDING.getSize());
		
	}

	public VBox getToolBarMembers() {
		return container;
	}





}
