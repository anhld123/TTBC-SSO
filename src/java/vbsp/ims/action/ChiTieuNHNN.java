package vbsp.ims.action;

import vbsp.ims.ctieu.DonViCT;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.ctieu.ChiTieuChiTiet;
import vbsp.ims.dao.DaoConnect;

public class ChiTieuNHNN extends ActionSupport {

    private Map session;
    private String capbc;
    private List<DonViCT> danhsachpgd;
    private List<ChiTieuChiTiet> dmctieu;
    private String labPGD;
    private String nganHang;

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getLabPGD() {
        return labPGD;
    }

    public void setLabPGD(String labPGD) {
        this.labPGD = labPGD;
    }

    public ChiTieuNHNN() {
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
        dmctieu = new ArrayList<ChiTieuChiTiet>();
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");
        danhsachpgd = new ArrayList<DonViCT>();
        
        CallableStatement st = con.prepareCall("{call P_LOAD_TREE_NHNN()}");
        st.execute();
        
        Statement stm = con.createStatement();
        //Lấy danh mục các chỉ tiêu
        ResultSet rs = stm.executeQuery("SELECT ID,PARENT,NAME,CHITIEU FROM DMCTIEU_TMP ORDER BY CHITIEU ASC");
        while (rs.next()) {
            ChiTieuChiTiet obj = new ChiTieuChiTiet();
            obj.setId(rs.getString("ID"));
            obj.setParent(rs.getString("PARENT"));
            obj.setName(rs.getString("NAME"));
            obj.setChiTieu(rs.getString("CHITIEU"));
            dmctieu.add(obj);
        }
        // Viết câu Queris lấy ra PGD theo User và cấp báo cáo
        String MSQL = "";
        if (capbc.equals("1")) {
            labPGD = "Mã PGD: ";
            MSQL = "Select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from DMPOS where PO_MA in (Select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "')";
        } else if (capbc.equals("2")) {
            labPGD = "Mã PGD: ";
            MSQL = "Select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from DMPOS where PO_MACN in (Select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "')";
        } else {
            labPGD = "Mã CN: ";
            MSQL = "select PO_MA,po_ma ||  ' - ' || po_ten AS PO_TEN from dmpos where po_ma in (Select distinct Po_macn from DMPOS)";
        }
        ResultSet rspgd = stm.executeQuery(MSQL);
        while (rspgd.next()) {
            danhsachpgd.add(new DonViCT(rspgd.getString("PO_MA"), rspgd.getString("PO_TEN")));
        }
        rspgd.close();
        rs.close();
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
