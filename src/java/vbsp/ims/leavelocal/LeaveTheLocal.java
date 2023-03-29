package vbsp.ims.leavelocal;

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
import java.text.SimpleDateFormat;
import vbsp.ims.dao.DaoConnect;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionContext;
import java.io.InputStream;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import vbsp.ims.action.ktktnb.PankService;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;

public class LeaveTheLocal extends ActionSupport {

    private List<DULIEU_NT_TQ> lstData;
    private List<PosClass> lstCN;
    private String txtNgayBc;
    private String txtNghiepVu;
    private String txtMakh;
    private String sGrade;
    private String sUser;
    private String vsbpMakh;
    private String vsbpNgayBC;
    private InputStream pageResult;
    private DuLieuNTService service;
    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();

    public LeaveTheLocal() {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
    }

    public String execute() throws Exception {
        return "success";
    }

    public String getLeaveLocal() throws Exception {
        epsModel dao = new epsModel();
        this.lstData = new ArrayList<DULIEU_NT_TQ>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.GETDANHSACHKH(?,?,?,?,?)}");
            st.setString(1, this.txtNghiepVu);
            st.setString(2, sReportdt);
            st.setString(3, this.txtMakh);
            st.setString(4, this.sUser);
            st.registerOutParameter(5, -10);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(5);
            while (rs.next()) {
                final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
                obj.setD3(rs.getString("KU_MACN"));
                obj.setD4(rs.getString("TENCN"));
                obj.setD5(rs.getString("KU_MAPGD"));
                obj.setD6(rs.getString("TENPGD"));
                obj.setD7(rs.getString("KU_MADP"));
                obj.setD8(rs.getString("TENXA"));
                obj.setD9(rs.getString("KU_MATO"));
                obj.setD10(rs.getString("TENTO"));
                obj.setD11(rs.getString("KU_MAKH"));
                obj.setD12(rs.getString("TENKH"));
                obj.setD13(rs.getString("TENVC"));
                obj.setD14(rs.getString("CMT"));
                obj.setD15(rs.getString("KU_SOKU"));
                obj.setD16(rs.getString("NGAYVAY"));
                obj.setD17(rs.getString("NGAYDH"));
                obj.setD18(rs.getString("DUNO"));
                obj.setD19(rs.getString("NOLAI"));
                obj.setD20(rs.getString("SODUTG"));
                obj.setD21(rs.getString("THOIDIEM"));
                obj.setD22(rs.getString("MAMON"));
                obj.setD23(rs.getString("THONGTIN"));
                obj.setD24(rs.getString("BAOHIEM"));
                obj.setD25(rs.getString("MAQL"));
                obj.setD26(rs.getString("D26"));
                obj.setD27(rs.getString("D27"));
                obj.setD28(rs.getString("D28"));
                obj.setD29(rs.getString("D29"));
                obj.setD30(rs.getString("D30"));
                obj.setD31(rs.getString("D31"));
                obj.setD32(rs.getString("D32"));
                obj.setD33(rs.getString("D33"));
                obj.setD34(rs.getString("D34"));
                obj.setD35(rs.getString("D35"));
                obj.setD36(rs.getString("D36"));
                obj.setD37(rs.getString("D37"));
                obj.setD38(rs.getString("D38"));
                obj.setD39(rs.getString("D39"));
                this.lstData.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveTheLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        lstCN = dao.getChiNhanh("3", "000000");
        return "success";
    }

    public String PopupThanhvien() throws Exception {
        this.lstData = new ArrayList<DULIEU_NT_TQ>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.LISTTHANHVIEN(?,?,?)}");
            st.setString(1, this.vsbpMakh);
            st.setString(2, sReportdt);
            st.registerOutParameter(3, -10);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
                obj.setD3(rs.getString("KU_MACN"));
                obj.setD4(rs.getString("TENCN"));
                obj.setD5(rs.getString("KU_MAPGD"));
                obj.setD6(rs.getString("TENPGD"));
                obj.setD7(rs.getString("KU_MADP"));
                obj.setD8(rs.getString("TENXA"));
                obj.setD9(rs.getString("KU_MATO"));
                obj.setD10(rs.getString("TENTO"));
                obj.setD11(rs.getString("KU_MAKH"));
                obj.setD12(rs.getString("TENKH"));
                obj.setD13(rs.getString("TENVC"));
                obj.setD14(rs.getString("CMT"));
                obj.setD15(rs.getString("KU_SOKU"));
                obj.setD16(rs.getString("NGAYVAY"));
                obj.setD17(rs.getString("NGAYDH"));
                obj.setD18(rs.getString("DUNO"));
                obj.setD19(rs.getString("NOLAI"));
                obj.setD20(rs.getString("SODUTG"));
                obj.setD21(rs.getString("THOIDIEM"));
                obj.setD22(rs.getString("MAMON"));
                obj.setD23(rs.getString("THONGTIN"));
                obj.setD24(rs.getString("BAOHIEM"));
                obj.setD25(rs.getString("MAQL"));
                obj.setD26(rs.getString("D26"));
                obj.setD27(rs.getString("D27"));
                obj.setD28(rs.getString("D28"));
                obj.setD29(rs.getString("D29"));
                obj.setD30(rs.getString("D30"));
                obj.setD31(rs.getString("D31"));
                obj.setD32(rs.getString("D32"));
                obj.setD33(rs.getString("D33"));
                obj.setD34(rs.getString("D34"));
                obj.setD35(rs.getString("D35"));
                obj.setD36(rs.getString("D36"));
                obj.setD37(rs.getString("D37"));
                obj.setD38(rs.getString("D38"));
                obj.setD39(rs.getString("D39"));
                this.lstData.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveTheLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "success";
    }

    public String saveLeaveLocal() {
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        try {
            final Object[] array = this.lstData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            CallableStatement calstatement = null;
            final String strStoreproce = "{call BODI_KHOIDP.INSERT_DANHSACHKH(?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, 1003, 1007);
                calstatement.setString(1, this.txtNgayBc);
                calstatement.setString(2, this.txtMakh);
                calstatement.setString(3, this.sUser);
                calstatement.setArray(4, (Array) array_to_pass);
                calstatement.registerOutParameter(5, 12);
                calstatement.execute();
                code = calstatement.getString(5);
                if (calstatement != null) {
                    calstatement.close();
                }

                int iResult = getDataSendTW();
                if (iResult != 0) {
                    return "unsuccess";
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "saveData -> " + e.getMessage());
            }
        } catch (Exception e2) {
            System.err.println("Loi trong ham saveData " + e2.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e2.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));

        //ApiDataByTem
        return "success";
    }

    public String addRemoveTV() {
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        try {
            final Object[] array = this.lstData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            CallableStatement calstatement = null;
            final String strStoreproce = "{call BODI_KHOIDP.INSERT_LISTTHANHVIEN(?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, 1003, 1007);
                calstatement.setString(1, this.vsbpNgayBC);
                calstatement.setString(2, this.vsbpMakh);
                calstatement.setString(3, this.sUser);
                calstatement.setArray(4, (Array) array_to_pass);
                calstatement.registerOutParameter(5, 12);
                calstatement.execute();
                code = calstatement.getString(5);
                if (calstatement != null) {
                    calstatement.close();
                }

                int iResult = getDataSendTW();
                if (iResult != 0) {
                    return "unsuccess";
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "saveData -> " + e.getMessage());
            }
        } catch (Exception e2) {
            System.err.println("Loi trong ham saveData " + e2.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e2.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "success";
    }

    public int getDataSendTW() throws Exception {
        epsModel dao = new epsModel();
        this.lstData = new ArrayList<DULIEU_NT_TQ>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
        System.out.println("Đến 2");
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.GET_DATA_SEND_TW(?,?)}");
            st.setString(1, this.sUser);
            st.registerOutParameter(2, -10);
            st.execute();
            final ResultSet tmp = (ResultSet) st.getObject(2);
            SimpleDateFormat CvDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            while (tmp.next()) {
                DuLieuNTRowX value = new DuLieuNTRowX();
                value.setKey(tmp.getString(1));
                value.setOrderValue(Integer.parseInt(tmp.getString(2)));
                value.setOrderDescription(tmp.getString(3));
                value.setCode(tmp.getString(4));
                value.setName(tmp.getString(5));
                value.setReportDate(tmp.getString(6));
                value.setReportYear(Integer.parseInt(tmp.getString(7)));
                value.setPosCode(tmp.getString(8));
                value.setPosFlag(tmp.getString(9));
                value.setBranchCode(tmp.getString(10));
                value.setMakerId(tmp.getString(11));
                value.setMakerDate(tmp.getString(12));
//                    value.setD1(tmp.getString(14));
                value.setD1(tmp.getString(15));
                value.setD2(tmp.getString(16));
                value.setD3(tmp.getString(17));
                value.setD4(tmp.getString(18));
                value.setD5(tmp.getString(19));
                value.setD6(tmp.getString(20));
                value.setD7(tmp.getString(21));
                value.setD8(tmp.getString(22));
                value.setD9(tmp.getString(23));
                value.setD10(tmp.getString(24));

                value.setD11(tmp.getString(25));
                value.setD12(tmp.getString(26));
                value.setD13(tmp.getString(27));
                value.setD14(tmp.getString(28));
                value.setD15(tmp.getString(29));
                value.setD16(tmp.getString(30));
                value.setD17(tmp.getString(31));
                value.setD18(tmp.getString(32));
                value.setD19(tmp.getString(33));
                value.setD20(tmp.getString(34));

                value.setD21(tmp.getString(35));
                value.setD22(tmp.getString(36));
                value.setD23(tmp.getString(37));
                value.setD24(tmp.getString(38));
                value.setD25(tmp.getString(39));
                value.setD26(tmp.getString(40));
                value.setD27(tmp.getString(41));
                value.setD28(tmp.getString(42));
                value.setD29(tmp.getString(43));
                value.setD30(tmp.getString(44));

                value.setManualFlag("0");
                value.setFontFormat(tmp.getString(46));
                lstUpdateDate.add(value);
            }
            System.out.println("Đến 1");
            posMainModel = listKTNBDA.get_pos_main_pos(sUser, sGrade);
            pos_cd_username = posMainModel.getPosCd();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
//            System.out.println("sizi--------------" + lstUpdateDate.size());
            service = new DuLieuNTService();
            int status = service.updateDataX("BO_DI_KHOI_DP", pos_cd_username, "S", dateStr, sUser, sUser, lstUpdateDate);
            if (status != 200) {
                CoreLogger.error(this.getClass().getName() + "Loi gui api len tw");
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveTheLocal.class.getName()).log(Level.SEVERE, "Lỗi hàm gửi tw", ex);
            return 2;
        }
//        lstCN = dao.getChiNhanh("3", "000000");
        return 0;
    }

    public List<DULIEU_NT_TQ> getLstData() {
        return this.lstData;
    }

    public void setLstData(final List<DULIEU_NT_TQ> lstData) {
        this.lstData = lstData;
    }

    public String getTxtNgayBc() {
        return this.txtNgayBc;
    }

    public void setTxtNgayBc(final String txtNgayBc) {
        this.txtNgayBc = txtNgayBc;
    }

    public String getTxtNghiepVu() {
        return this.txtNghiepVu;
    }

    public void setTxtNghiepVu(final String txtNghiepVu) {
        this.txtNghiepVu = txtNghiepVu;
    }

    public String getTxtMakh() {
        return this.txtMakh;
    }

    public void setTxtMakh(final String txtMakh) {
        this.txtMakh = txtMakh;
    }

    public InputStream getPageResult() {
        return this.pageResult;
    }

    public void setPageResult(final InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getVsbpMakh() {
        return this.vsbpMakh;
    }

    public void setVsbpMakh(final String vsbpMakh) {
        this.vsbpMakh = vsbpMakh;
    }

    public String getVsbpNgayBC() {
        return this.vsbpNgayBC;
    }

    public void setVsbpNgayBC(final String vsbpNgayBC) {
        this.vsbpNgayBC = vsbpNgayBC;
    }

    public List<PosClass> getLstCN() {
        return lstCN;
    }

    public void setLstCN(List<PosClass> lstCN) {
        this.lstCN = lstCN;
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

}
