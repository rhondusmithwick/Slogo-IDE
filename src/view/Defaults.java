package View;

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
	PEN_COLOR("black");
	
	
	
	private String content;
	Defaults(String str){
		this.content=str;
	}
	
	public String getDefault(){
		return this.content;
	}
}
