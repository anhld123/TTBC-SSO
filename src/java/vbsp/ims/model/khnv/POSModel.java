package vbsp.ims.model.khnv;

import vbsp.ims.bcnt.*;

public class POSModel {
    String id;  //Ma POS (key Combobox)
    String desc; //Mo ta tren combox (value combobox)
    
    public POSModel(){
    
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
//</editor-fold>
}
