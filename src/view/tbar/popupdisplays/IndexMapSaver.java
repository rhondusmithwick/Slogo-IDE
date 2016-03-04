package view.tbar.popupdisplays;


import java.util.Arrays;
import java.util.ResourceBundle;

import view.Defaults;
import view.Size;
import view.xml.MapToXML;
import view.utilities.ButtonFactory;
import view.utilities.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import maps.IndexMap;

public class IndexMapSaver extends PopUp{

	private ResourceBundle myResources;
	private TextField tField;
	private IndexMap inMap;
	private SimpleStringProperty error;



	public IndexMapSaver(IndexMap inMap, SimpleStringProperty error){
		super(Size.MAP_SAVER.getSize(), Size.MAP_SAVER.getSize(), Defaults.BACKGROUND_COLOR.getDefault());
		this.inMap = inMap;
		this.error=error;
		this.myResources =  ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
	}

	private void saveList (){

		closeScene();
		try {
			String text = tField.getText();
			if(text.equals("")){
				return;
			}else{
				MapToXML mapper = new MapToXML();
				mapper.saveMap(text, inMap);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			error.set("");
			error.set("saveError");

		}
	}


	@Override
	protected void createScene() {
		Label title = new Label(myResources.getString("saverTitle"));
		tField = new TextField();
		tField.prefWidthProperty().bind(getSize(false));
		Button set = ButtonFactory.createButton(myResources.getString("save"), e->saveList());
		addNodes(Arrays.asList(title, tField, set));

	}

}



