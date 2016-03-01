package View;

public enum Defaults {
	DISPLAY_LANG("English"),
	DISPLAY_LOC("resources/guiStrings/"),
	BACKGROUND_COLOR("-fx-background-color: cornflowerblue"),
	COMMAND_TO_TEXT_BOX("show in text box"),
	BORDER_COLOR("-fx-border-color: black;"),
	RESOURCE_LOCATION("resources/"),
	PARSELANG_LOC("resources/languages"),
	FX_PAINT_CLASS("javafx.scene.paint.Color"),
	TURT_BACKGROUND("white");
	
	
	
	private String content;
	Defaults(String str){
		this.content=str;
	}
	
	public String getDefault(){
		return this.content;
	}
}
