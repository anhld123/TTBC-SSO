package vbsp.ims.ctieu;

public class ChiTieuChiTiet {
    private String id;
    private String parent;
    private String name;
    private String chiTieu;
    
    public ChiTieuChiTiet(){
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(String chiTieu) {
        this.chiTieu = chiTieu;
    }
    //</editor-fold>
}
