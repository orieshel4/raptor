package model;

import java.util.ArrayList;

public class RowList extends ArrayList<Bubble> {
    private boolean fullFlag;

    public RowList(boolean fullFlag) {
        this.fullFlag = fullFlag;
    }

    public boolean isFullFlag(){
        return fullFlag;
    }

    public void setFull(){
        fullFlag =true;
    }

}
