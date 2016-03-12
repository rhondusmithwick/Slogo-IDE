package view;
/**
 * enum representing all string defaults for the view, for example css styling strings
 * for the background
 * @author calinelson
 *
 */
public enum Defaults {
	DISPLAY_LOC("resources/guiStrings/english"),
	BACKGROUND_COLOR("-fx-background-color: cornflowerblue"),
	BACKGROUND_WHITE("-fx-background-color: white"),
	COMMAND_TO_TEXT_BOX("show in text box"),
	BORDER_COLOR("-fx-border-color: black;"),
	RESOURCE_LOCATION("resources/"),
	PARSELANG_LOC("resources/languages/"),
	FX_PAINT_CLASS("javafx.scene.paint.Color"),
	TURT_BACKGROUND("white"),
	IMAGE_LOC("resources/images/"),
	IMAGELIST_LOC("resources/imageFiles/"),
	COLORLIST_LOC("resources/colors/"),
	DEFAULT("Default"),
	PEN_COLOR("black"),
	COMM_SPLITER("|"),
	METH_ACT_LOC("view.envdisplay.MethodExec"),
	VAR_UP_LOC("view.envdisplay.VariableUpdate"),
	WS_DOC_EL("WorkspaceConfig"),
	WS_CHILD_EL("ConfigProps"),
	LANG("english"),
	NUM_TURTS("1"),
	XML(".xml"),
	IM_DOC_EL("IndexedMap"),
	IM_CHILD_ELEMENT("Element"),
	WS_PREF_LOC("WorkSpacePreferences/"),
	REP_VAR(":repcount"),
	ENV_SPLITTER(", "),
	METH_SPLITTER("], "),
	VAR_SPLITTER(","),
	TURTLE_SELECTOR("view.tbar.popupdisplays.TurtleSelector"),
	PEN_UP("view.tbar.popupdisplays.pen.PenUpUpdater"),
	PEN_DOWN("view.tbar.popupdisplays.pen.PenDownUpdater"),
	PEN_COLOR_UPDATER("view.tbar.popupdisplays.pen.PenColorUpdater"),
	PEN_SIZE_UPDATER("view.tbar.popupdisplays.pen.PenSizeUpdater");
	
	
	
	private String content;
	
	/**
	 * creates default for string str
	 * @param str default string
	 */
	Defaults(String str){
		this.content=str;
	}
	
	/**
	 * returns default string
	 * @return default string for enum
	 */
	public String getDefault(){
		return this.content;
	}
}
