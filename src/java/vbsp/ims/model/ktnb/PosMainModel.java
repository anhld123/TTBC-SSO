package vbsp.ims.model.ktnb;

import java.util.List;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author CuongBM0211
 */
public class PosMainModel {

    private String posCd; //Ma phong giao dich
    private String mainPosCd; //Ma chi nhanh
    private List<ListValue> lstXa;//Neu cap bao cao la 1 thi lay ra danh sach xa

    public PosMainModel() {

    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getPosCd() {
        return posCd;
    }

    public void setPosCd(String posCd) {
        this.posCd = posCd;
    }

    public String getMainPosCd() {
        return mainPosCd;
    }

    public void setMainPosCd(String mainPosCd) {
        this.mainPosCd = mainPosCd;
    }

    public List<ListValue> getLstXa() {
        return lstXa;
    }

    public void setLstXa(List<ListValue> lstXa) {
        this.lstXa = lstXa;
    }

//</editor-fold>
}
