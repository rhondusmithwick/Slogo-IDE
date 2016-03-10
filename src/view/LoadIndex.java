package view;

/**
 * this class represents the index of preferences in the workspace preferences in 
 * the xml file and the index at which the preference is placed in the params arraylist
 * in the preference loader
 * @author calinelson
 *
 */
public enum LoadIndex {
    BG_COLOR(0),
    P_LANG(1),
    C_FILE(2),
    I_FILE(3),
    NUM_TURT(4);
    
    private int index;
    
    /**
     * sets index for parameter
     * @param index index of param
     */
    LoadIndex(int index){
            this.index=index;
    }
    
    /**
     * returns the index of the specified parameter
     * @return index of parameter
     */
    public int getIndex(){
            return index;
    }
}
