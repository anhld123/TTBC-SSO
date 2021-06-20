/*
 * Ngày tạo: 1/6/14
 * Mục đích: Tạo báo cáo từ chỉ tiêu
 * 
 */
package vbsp.ims.action;

import vbsp.ims.ctieu.DonViCT;
import vbsp.ims.ctieu.BaoCaoCT;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Administrator
 */
public class CreateIndicatorReport extends ActionSupport {

    private Map session;
    private String capbc;
    private String tendn;
    private List<BaoCaoCT> danhsachbaocao;
    private List<DonViCT> danhsachdonvi;

    public List<BaoCaoCT> getDanhsachbaocao() {
        return danhsachbaocao;
    }

    public String getCapbc() {
        return capbc;
    }

    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public void setDanhsachbaocao(List<BaoCaoCT> danhsachbaocao) {
        this.danhsachbaocao = danhsachbaocao;
    }

    public List<DonViCT> getDanhsachdonvi() {
        return danhsachdonvi;
    }

    public void setDanhsachdonvi(List<DonViCT> danhsachdonvi) {
        this.danhsachdonvi = danhsachdonvi;
    }

    @Override
    public String execute() throws Exception {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        danhsachbaocao = new ArrayList<>();
        danhsachdonvi = new ArrayList<>();

        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");

        //Mục đich: Load danh mục các báo cáo từ chỉ tiêu lên combo
        try ( 
                Statement stm = con.createStatement()) {
            String lcQuery;
            lcQuery = "SELECT DM_MABC,DM_MOTA FROM DMBC_CT,BCTUCT_DIEUKIEN WHERE DM_MABC = MABC AND GHICHU<>'00' ORDER BY GHICHU";
            try (ResultSet rsbaocao = stm.executeQuery(lcQuery)) {
                while (rsbaocao.next()) {
                    danhsachbaocao.add(new BaoCaoCT(rsbaocao.getString("DM_MABC"), 
                            rsbaocao.getString("DM_MOTA")));
                }
            }
        }
        //Mục đích: Load danh mục đơn vị theo user và cấp báo cáo
        CallableStatement st = con.prepareCall("{call app_priv_view.p_get_priv_4indi(?,?,?)}");
        st.setString(1, capbc);
        st.setString(2, tendn);
        st.registerOutParameter(3, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(3);
        while (rs.next()) {
            danhsachdonvi.add(new DonViCT(rs.getString("PO_MA"), rs.getString("PO_TEN")));
        }
        return SUCCESS;
    }

    public String hienthi() {
        // Hàm thực hiện load dữ liệ hiển thị khi chọn combox báo cáo

        return "hienthi";
    }
}
