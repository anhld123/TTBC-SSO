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
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;

public class LeaveTheLocal extends ActionSupport {

    private List<DULIEU_NT_TQ> lstData;
    private List<PosClass> lstCN;
    private List<PosClass> lstPGD;
    private String txtNgayBc;
    private String txtFromDate;
    private String txtToDate;
    private String txtNghiepVu;
    private String txtMakh;
    private String txtsMadv;
    private String sGrade;
    private String sUser;
    private String vsbpMakh;
    private String vsbpTenKh;
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
        this.lstData = new ArrayList<>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
        final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
        final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.GETDANHSACHKH(?,?,?,?,?,?,?,?)}");
            st.setString(1, sGrade);
            st.setString(2, sReportdt);
            st.setString(3, txtMakh);
            st.setString(4, sUser);
            st.setString(5, txtsMadv);
            st.setString(6, sFromDate);
            st.setString(7, sToDate);
            st.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(8);
            while (rs.next()) {
                final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
                obj.setTHUTU(rs.getInt("THUTU"));
                obj.setD3(rs.getString("MACN"));
                obj.setD4(rs.getString("TENCN"));
                obj.setD5(rs.getString("MAPGD"));
                obj.setD6(rs.getString("TENPGD"));
                obj.setD7(rs.getString("MADP"));
                obj.setD8(rs.getString("TENXA"));
                obj.setD9(rs.getString("MATO"));
                obj.setD10(rs.getString("TENTO"));
                obj.setD11(rs.getString("MAKH"));
                obj.setD12(rs.getString("TENKH"));
                obj.setD13(rs.getString("NGAYSINH"));
                obj.setD14(rs.getString("CMTKH"));
                obj.setD15(rs.getString("LOAIDT"));
                obj.setD16(rs.getString("D16")); // Số điện thoại
                obj.setD17(rs.getString("D17"));
                obj.setD18(rs.getString("D18"));
                obj.setD19(rs.getString("D19"));
                obj.setD20(rs.getString("D20"));
                obj.setD21(rs.getString("THOIDIEM"));
                obj.setD22(rs.getString("MANHOM"));
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
        lstCN = dao.getDonvi("ALL_MAIN_POS", "");
        lstPGD = dao.getDonvi("ALL_POS", "");
        return "success";
    }

    public String popupThanhvien() throws Exception {
        this.lstData = new ArrayList<>();
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.LISTTHANHVIEN(?,?,?)}");
            st.setString(1, vsbpMakh);
            st.setString(2, sUser);
            st.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            st.execute();
            final ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
                obj.setTHUTU(rs.getInt("THUTU"));
                obj.setD3(rs.getString("MACN"));
                obj.setD4(rs.getString("TENCN"));
                obj.setD5(rs.getString("MAPGD"));
                obj.setD6(rs.getString("TENPGD"));
                obj.setD7(rs.getString("MADP"));
                obj.setD8(rs.getString("TENXA"));
                obj.setD9(rs.getString("MATO"));
                obj.setD10(rs.getString("TENTO"));
                obj.setD11(rs.getString("MAKH"));
                obj.setD12(rs.getString("TENKH"));
                obj.setD13(rs.getString("NGAYSINH"));
                obj.setD14(rs.getString("CMTKH"));
                obj.setD15(rs.getString("LOAIDT"));
                obj.setD16(rs.getString("D16")); //Số điện thoại
                obj.setD17(rs.getString("D17"));
                obj.setD18(rs.getString("D17"));
                obj.setD19(rs.getString("D18"));
                obj.setD20(rs.getString("D20"));
                obj.setD21(rs.getString("THOIDIEM"));
                obj.setD22(rs.getString("MANHOM"));
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
        
        List<DULIEU_NT_TQ> lstSelectedData = new ArrayList<>();        
        // Lay ra danh sach ma khach hang duoc chon
        List<String> lstSelectedCustomer = new ArrayList<>();
        
        for(int i = 0 ; i < this.lstData.size(); i++)
        {
            if (this.lstData.get(i).getNHAPTAY() != null && this.lstData.get(i).getNHAPTAY().equals("1")) {
                //lstSelectedData.add(this.lstData.get(i));
                if (!lstSelectedCustomer.contains(this.lstData.get(i).getD11()))
                    lstSelectedCustomer.add(this.lstData.get(i).getD11());
            }
        }
        
        for(int i = 0 ; i < this.lstData.size(); i++)
        {
            if (lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {                
                lstSelectedData.add(this.lstData.get(i));
            }
        }
        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        try {
            final Object[] array = lstSelectedData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            CallableStatement calstatement = null;
            final String strStoreproce = "{call BODI_KHOIDP.INSERT_DANHSACHKH(?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce);
                calstatement.setString(1, txtNgayBc);
                calstatement.setString(2, sUser);
                calstatement.setArray(3, (Array) array_to_pass);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NCHAR);
                calstatement.execute();
                code = calstatement.getString(4);
                if (calstatement != null) {
                    calstatement.close();
                }

                System.out.println("Save Leaver Local => " + code);

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

    public String sendLeaveLocal() throws Exception {
        //Lưu dữ liệu trước khi gửi
        //saveLeaveLocal();
        //Gửi số liệu
        String code = "";
        int iResult = getDataSendTW();
        if (iResult != 0) {
            code = "404";
        }else{
            code = "200";
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
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

                System.out.println("LeaverLocal => " + code);

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
        //this.lstData 
        List<DULIEU_NT_TQ> lstSelectedData = new ArrayList<>();        
        for(int i = 0 ; i < this.lstData.size(); i++)
        {
            if (this.lstData.get(i).getNHAPTAY() != null && this.lstData.get(i).getNHAPTAY().equals("1")) {
                lstSelectedData.add(this.lstData.get(i));
            }
        }        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        ArrayList<DuLieuNTRowX> lstUpdateData = new ArrayList<>();
        final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
        final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
        final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
        try {
            final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.GET_DATA_SEND_TW(?,?,?,?,?)}");
            
            final Object[] array = lstSelectedData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            
            st.setString(1, this.sUser);
            st.setString(2, sFromDate);
            st.setString(3, sToDate);
            st.setArray(4,(Array) array_to_pass);
            st.registerOutParameter(5, -10);
            st.execute();
            final ResultSet tmp = (ResultSet) st.getObject(5);
            //SimpleDateFormat vDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            while (tmp.next()) {
                DuLieuNTRowX value = new DuLieuNTRowX();
                value.setKey(tmp.getString("KHOA"));
                value.setOrderValue(Integer.parseInt(tmp.getString("THUTU")));
                value.setOrderDescription(tmp.getString("TT_HIENTHI"));
                value.setCode(tmp.getString("MA"));
                value.setName(tmp.getString("TEN"));
                value.setReportDate(tmp.getString("NGAYBC"));
                value.setReportYear(Integer.parseInt(tmp.getString("NAMBC")));
                value.setPosCode(tmp.getString("MAPGD"));
                value.setPosFlag(tmp.getString("CO_TONGHOP"));
                value.setBranchCode(tmp.getString("MACN"));
                value.setMakerId(tmp.getString("NGUOI_NHAP"));
                value.setMakerDate(tmp.getString("NGAY_NHAP"));
                value.setD1(tmp.getString("D1"));
                value.setD2(tmp.getString("D2"));
                value.setD3(tmp.getString("D3"));
                value.setD4(tmp.getString("D4"));
                value.setD5(tmp.getString("D5"));
                value.setD6(tmp.getString("D6"));
                value.setD7(tmp.getString("D7"));
                value.setD8(tmp.getString("D8"));
                value.setD9(tmp.getString("D9"));
                value.setD10(tmp.getString("D10"));
                value.setD11(tmp.getString("D11"));
                value.setD12(tmp.getString("D12"));
                value.setD13(tmp.getString("D13"));
                value.setD14(tmp.getString("D14"));
                value.setD15(tmp.getString("D15"));
                value.setD16(tmp.getString("D16"));
                value.setD17(tmp.getString("D17"));
                value.setD18(tmp.getString("D18"));
                value.setD19(tmp.getString("D19"));
                value.setD20(tmp.getString("D20"));
                value.setD21(tmp.getString("D21"));
                value.setD22(tmp.getString("D22"));
                value.setD23(tmp.getString("D23"));
                value.setD24(tmp.getString("D24"));
                value.setD25(tmp.getString("D25"));
                value.setD26(tmp.getString("D26"));
                value.setD27(tmp.getString("D27"));
                value.setD28(tmp.getString("D28"));
                value.setD29(tmp.getString("D29"));
                value.setD30(tmp.getString("D30"));
                value.setD31(tmp.getString("D31"));
                value.setD50(tmp.getString("D50"));
                value.setManualFlag(tmp.getString("NHAPTAY"));
                value.setFontFormat(tmp.getString("FONTFORMAT"));
                lstUpdateData.add(value);
            }
            posMainModel = listKTNBDA.get_pos_main_pos(sUser, sGrade);
            pos_cd_username = posMainModel.getPosCd();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            service = new DuLieuNTService();
            int status = service.updateCustomerLeftHome("BO_DI_KHOI_DP", pos_cd_username, "S", dateStr, sUser, sUser, lstUpdateData);
            if (status != 200) {
                CoreLogger.error(this.getClass().getName() + "Loi gui api len tw");
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveTheLocal.class.getName()).log(Level.SEVERE, "Lỗi hàm gửi tw", ex);
            return 2;
        }
        return 0;
    }

    public String deleteLeaveLocal() {
        
        List<DULIEU_NT_TQ> lstSelectedData = new ArrayList<>();        
        // Lay ra danh sach ma khach hang duoc chon
        List<String> lstSelectedCustomer = new ArrayList<>();
        
        for(int i = 0 ; i < this.lstData.size(); i++)
        {
            if (this.lstData.get(i).getNHAPTAY() != null && this.lstData.get(i).getNHAPTAY().equals("1")) {
                //lstSelectedData.add(this.lstData.get(i));
                if (!lstSelectedCustomer.contains(this.lstData.get(i).getD11()))
                    lstSelectedCustomer.add(this.lstData.get(i).getD11());
            }
        }
        
        for(int i = 0 ; i < this.lstData.size(); i++)
        {
            if (lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {                
                // Chuyen trang thai sang dong
                this.lstData.get(i).setD50("C");
                lstSelectedData.add(this.lstData.get(i));
            }
        }
        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        String code = "";
        try {
            final Object[] array = lstSelectedData.toArray();
            final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
            final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
            CallableStatement calstatement = null;
            final String strStoreproce = "{call BODI_KHOIDP.DELETE_DANHSACHKH(?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce);
                calstatement.setString(1, txtNgayBc);
                calstatement.setString(2, sUser);
                calstatement.setArray(3, (Array) array_to_pass);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NCHAR);
                calstatement.execute();
                code = calstatement.getString(4);
                if (calstatement != null) {
                    calstatement.close();
                }

                System.out.println("Save Leaver Local => " + code);

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

    public String getTxtsMadv() {
        return txtsMadv;
    }

    public void setTxtsMadv(String txtsMadv) {
        this.txtsMadv = txtsMadv;
    }

    public String getTxtFromDate() {
        return txtFromDate;
    }

    public void setTxtFromDate(String txtFromDate) {
        this.txtFromDate = txtFromDate;
    }

    public String getTxtToDate() {
        return txtToDate;
    }

    public void setTxtToDate(String txtToDate) {
        this.txtToDate = txtToDate;
    }      

    public String getVsbpTenKh() {
        return vsbpTenKh;
    }

    public void setVsbpTenKh(String vsbpTenKh) {
        this.vsbpTenKh = vsbpTenKh;
    }

    public List<PosClass> getLstPGD() {
        return lstPGD;
    }

    public void setLstPGD(List<PosClass> lstPGD) {
        this.lstPGD = lstPGD;
    }
    
    
    
    
}
