package view;

/**
 * this class is an enum of all component sizes,borders, and padding for the view
 *
 * @author calinelson
 */

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
    ENV_WIDTH(300),
    TURTLE_UPDATE_POPUP_WIDTH(500),
    TURTLE_UPDATE_POPUP_HEIGHT(300),
    TOP_TAB(25),
    PROP_SEL_HEIGHT(200),
    TURTLE_IMAGE_DISP(400);

    private final int size;

    /**
     * creates new size enum for component name
     *
     * @param size size for component
     */
    Size(int size) {
        this.size = size;
    }

    /**
     * returns int size for given enum name
     *
     * @return int size
     */
    public int getSize() {
        return size;
    }
}
