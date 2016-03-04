package view.utilities;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Defaults;
import view.Size;

public abstract class SlogoFileChooser extends PopUp{

	private ResourceBundle myResources;
	String title;
	List<ExtensionFilter> fExt;
	private boolean save;
	private FileChooser fChoose;
	
	public SlogoFileChooser(String title, List<ExtensionFilter> fExt , boolean save) {
		super(Size.MINI.getSize(), Size.MINI.getSize(), Defaults.BACKGROUND_WHITE.getDefault());
		myResources = ResourceBundle.getBundle(Defaults.DISPLAY_LOC.getDefault());
		this.title = title;
		this.fExt = fExt;
		this.save = save;
	}

	@Override
	protected void createScene() {
		 fChoose = new FileChooser();
		 fChoose.setTitle(myResources.getString(title));
	     fChoose.getExtensionFilters().addAll(fExt);
		
	}
	
	protected File showWindow(){
		File file = showFChooser(fChoose,save);
		closeScene();
		return file;
		
	}

}
