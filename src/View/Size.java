package View;

public enum Size {
	
	EX_BUTTON(20),
	VIEW_PADDING(10),
	RIGHT_WIDTH(200),
	COMMAND_TITLE(25),
	BOTTOM_HEIGHT(195),
	HTML_HEIGHT(600),
	HTML_WIDTH(800),
	TB_PADDING(10),
	COMM_ENTRY_SPACE(2),
	MINI(1);
	
	private int size;
	Size(int size){
		this.size=size;
	}
	
	public int getSize(){
		return size;
	}
}
