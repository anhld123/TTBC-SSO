package vbsp.ims.nghiquyet11cp;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.log.CoreLogger;
import java.sql.Array;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import vbsp.ims.dao.DaoConnect;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionContext;
import java.io.InputStream;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;

public class MSCTHTLS extends ActionSupport {

    private List<DULIEU_NT_TQ> lstData;
    private String txtNgayBc;
    private String sGrade;
    private String sUser;
    private InputStream pageResult;
    private DuLieuNTService service;
    protected String pos_cd_username;
    private String txtsMadv;

    public MSCTHTLS() {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }

    public String get_mscthtls() throws Exception {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        lstData = new ArrayList<>();
        String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
        try {
            final CallableStatement st = con.prepareCall("{call MSCTHTLS.GET_MSCTHTLS(?,?,?,?,?)}");
            st.setString(1, sGrade);
            st.setString(2, sUser);
            st.setString(3, sReportdt);
            st.setString(4, txtsMadv);
            st.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(5);
            while (rs.next()) {
                DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
                obj.setKHOA(rs.getString("KHOA_2"));
                obj.setTEN(rs.getString("GIATRI"));
                obj.setD1(rs.getString("D1"));
                lstData.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MSCTHTLS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "success";
    }

    public String save_mscthtls() {
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        try {
            final Object[] array = lstData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            CallableStatement calstatement = null;
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            final String strStoreproce = "{call MSCTHTLS.SAVE_MSCTHTLS(?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce);
                calstatement.setString(1, sGrade);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sReportdt);
                calstatement.setArray(4, (Array) array_to_pass);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NCHAR);
                calstatement.execute();
                code = calstatement.getString(5);
                if (calstatement != null) {
                    calstatement.close();
                }

                System.out.println("Save chi tieu NQ11 => " + code);

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "Save chi tieu NQ11 -> " + e.getMessage());
            }
        } catch (Exception e2) {
            System.err.println("Loi trong ham save_mscthtls " + e2.getMessage());
            CoreLogger.error(this.getClass().getName() + " save_mscthtls -> " + e2.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "success";
    }

    public String Send_mscthtls() throws Exception {
        ArrayList<DuLieuNTRowX> lstSend = new ArrayList<>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        int i = 1;
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayBc));
        try {
            final CallableStatement st = con.prepareCall("{call MSCTHTLS.SEND_MSCTHTLS(?,?,?,?,?)}");
            st.setString(1, sGrade);
            st.setString(2, sUser);
            st.setString(3, sReportdt);
            st.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            st.registerOutParameter(5, oracle.jdbc.OracleTypes.NCHAR);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(4);
            code = st.getString(5);
            while (rs.next()) {
                DuLieuNTRowX obj = new DuLieuNTRowX();
                obj.setKey(rs.getString("KHOA"));
                obj.setCode(rs.getString("MA"));
                obj.setOrderValue(i);
                obj.setBranchCode(rs.getString("MACN"));
                obj.setPosCode(rs.getString("MAPGD"));
                obj.setReportDate(rs.getString("NGAYBC"));
                obj.setMakerDate(rs.getString("NGAY_NHAP"));
                obj.setReportYear(Integer.parseInt(rs.getString("NAMBC")));
                obj.setPosFlag(rs.getString("CO_TONGHOP"));
                obj.setD1(rs.getString("D1"));
                lstSend.add(obj);
                i++;
            }
            
            service = new DuLieuNTService();
            SimpleDateFormat CvDate = new SimpleDateFormat("yyyyMMdd");
            int status = service.updateDataX("MSCTHTLS", "000000", "S", CvDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayBc)), sUser, sUser, lstSend);
            if (status != 200) {
                CoreLogger.error(this.getClass().getName() + "Loi gui api len tw");
                return "unsuccess";
            }
        } catch (SQLException ex) {
            Logger.getLogger(MSCTHTLS.class.getName()).log(Level.SEVERE, "Lỗi hàm gửi tw", ex);
            return "unsuccess";
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "success";
    }

    public String getTxtNgayBc() {
        return this.txtNgayBc;
    }

    public void setTxtNgayBc(final String txtNgayBc) {
        this.txtNgayBc = txtNgayBc;
    }

    public InputStream getPageResult() {
        return this.pageResult;
    }

    public void setPageResult(final InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public List<DULIEU_NT_TQ> getLstData() {
        return lstData;
    }

    public void setLstData(List<DULIEU_NT_TQ> lstData) {
        this.lstData = lstData;
    }

    public String getTxtsMadv() {
        return txtsMadv;
    }

    public void setTxtsMadv(String txtsMadv) {
        this.txtsMadv = txtsMadv;
    }

}
