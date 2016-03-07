/**
 * this class is an enum of all component sizes,borders, and padding for the view
 * @author calinelson
 */
package view;

public enum Size {
	
	EX_BUTTON(20),
	VIEW_PADDING(10),
	RIGHT_WIDTH(200),
	COMMAND_TITLE(25),
	BOTTOM_HEIGHT(100),
	HTML_HEIGHT(600),
	HTML_WIDTH(800),
	TB_PADDING(3),
	COMM_ENTRY_SPACE(2),
	MINI(1),
	PALETTE(400),
	PALETTE_PADDING(20), 
	PALETTE_DIM(40),
	PALETTE_ENT_PADDING(50),
	ENV_DISPLAY_WIDTH(150),
	MAP_SAVER(200),
	POP_UP_PADDING(20),
	ENV_HEIGHT(600), 
	ENV_WIDTH(300);
	
	private int size;
	Size(int size){
		this.size=size;
	}
	
	/**
	 * returns int size for given enum name
	 * @return int size
	 */
	public int getSize(){
		return size;
	}
}
