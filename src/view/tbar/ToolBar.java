package view.tbar;

import view.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;
import maps.ColorMap;
import maps.ImageMap;
import observables.ObjectObservable;

public class ToolBar  {

	private VBox container;
	private SubBar topBar, botBar;


	public ToolBar(ObjectObservable<String> language, SimpleStringProperty error, ObjectObservable<String> bgColor,
			SimpleStringProperty image, SimpleStringProperty penColor, ObjectObservable<String> intCommand, 
			ColorMap cMap, ImageMap iMap) {
		
		setToolBar();
		topBar = new TopBar(language, bgColor, error, image, penColor, intCommand, cMap, iMap);
		botBar = new BottomBar(language,error, intCommand, cMap, iMap);
		setBars();
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
	
	private void setBars() {
            container.getChildren().addAll(topBar.getContainer(), botBar.getContainer());
	}




}
