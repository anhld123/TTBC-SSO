package vbsp.ims.action;

import vbsp.ims.ctieu.KyBaoCaoCT;
import vbsp.ims.ctieu.DonViCT;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.ctieu.ChiTieuChiTiet;
import vbsp.ims.dao.DaoConnect;

public class ChiTieuNHCSXH extends ActionSupport {

    private List<ChiTieuChiTiet> dmctieu;
    private Map session;
    private String capbc;
    private List<DonViCT> danhsachpgd;
    private List<KyBaoCaoCT> danhsachkybc;
    private String labPGD;

    public List<KyBaoCaoCT> getDanhsachkybc() {
        return danhsachkybc;
    }

    public void setDanhsachkybc(List<KyBaoCaoCT> danhsachkybc) {
        this.danhsachkybc = danhsachkybc;
    }


    public String getLabPGD() {
        return labPGD;
    }

    public void setLabPGD(String labPGD) {
        this.labPGD = labPGD;
    }

    public ChiTieuNHCSXH() {
    }

    public List<DonViCT> getDanhsachpgd() {
        return danhsachpgd;
    }

    public void setDanhsachpgd(List<DonViCT> danhsachpgd) {
        this.danhsachpgd = danhsachpgd;
    }

    @Override
    public String execute() throws Exception {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        dmctieu = new ArrayList<>();  
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");
        danhsachpgd = new ArrayList<>();
        danhsachkybc = new ArrayList<>();
        
       //Lấy danh mục chỉ tiêu 
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT ID,PARENT,NAME,CHITIEU FROM DMCTIEU WHERE NHCS = 'T' AND TRVIEW = 'F' ORDER BY ID");
        //ResultSet rs = stm.executeQuery(MQuery);
        while (rs.next()) {
            ChiTieuChiTiet obj = new ChiTieuChiTiet();
            obj.setId(rs.getString("ID"));
            obj.setParent(rs.getString("PARENT"));
            obj.setName(rs.getString("NAME"));
            obj.setChiTieu(rs.getString("CHITIEU"));

            dmctieu.add(obj);
        }
        rs.close();
        // Viết câu Queris lấy ra PGD theo User và cấp báo cáo
        String MSQL = "";
        if (capbc.equals("1")) {
           MSQL = "Select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from DMPOS where PO_MA in (Select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "')";
        }else if (capbc.equals("2")) {
           MSQL = "Select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from DMPOS where PO_MACN in (Select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "')";        
        }else {
            labPGD = "Mã CN: ";
           MSQL = "select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from dmpos where po_ma in (Select distinct Po_macn from DMPOS)" ;         
        } 
        ResultSet rspgd = stm.executeQuery(MSQL);
        while(rspgd.next()){
            danhsachpgd.add(new DonViCT(rspgd.getString("PO_MA"), rspgd.getString("PO_TEN")));
        }
        rspgd.close();
        
        MSQL="SELECT KHOA_2, GIATRI FROM DMKHAC WHERE KHOA_1=22";
        ResultSet rskybc = stm.executeQuery(MSQL);
        while(rskybc.next()){
            danhsachkybc.add(new KyBaoCaoCT(rskybc.getString("KHOA_2"), rskybc.getString("GIATRI")));
        }
        rskybc.close();
        
        return "success";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public List<ChiTieuChiTiet> getDmctieu() {
        return dmctieu;
    }

    public void setDmctieu(List<ChiTieuChiTiet> dmctieu) {
        this.dmctieu = dmctieu;
    }
    //</editor-fold>
}