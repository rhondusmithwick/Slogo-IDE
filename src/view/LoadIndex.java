package view;

public enum LoadIndex {
    BG_COLOR(0),
    P_COLOR(1),
    P_LANG(2),
    C_FILE(3),
    I_FILE(4),
    NUM_TURT(5);
    
    private int index;
    LoadIndex(int index){
            this.index=index;
    }
    
    public int getIndex(){
            return index;
    }
}
