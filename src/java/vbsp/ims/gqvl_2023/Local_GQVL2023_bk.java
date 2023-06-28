package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.log.CoreLogger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionContext;
import java.io.InputStream;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.Date;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.NQ11cpModel;

public class Local_GQVL2023_bk extends ActionSupport {

    private List<DuLieuNTRow> lstData;
//    lstDulieuNt
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
    private String vsbpMaPgd;
    private String XuLyNo;
    private String startPaymentDate;
    private String vsbpMakh;
    private String vsbpTenKh;
    private String vsbpNgayBC;
    private InputStream pageResult;
    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    private String gradeAuthor1;
    private String typeAuth;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    private List<ListValue> lstXuLyNo = new ArrayList<ListValue>();

    public String getStartPaymentDate() {
        return startPaymentDate;
    }

    public void setStartPaymentDate(String startPaymentDate) {
        this.startPaymentDate = startPaymentDate;
    }

    public String getTypeAuth() {
        return typeAuth;
    }

    public void setTypeAuth(String typeAuth) {
        this.typeAuth = typeAuth;
    }

    public String getGradeAuthor1() {
        return gradeAuthor1;
    }

    public void setGradeAuthor1(String gradeAuthor1) {
        this.gradeAuthor1 = gradeAuthor1;
    }

    public List<ListValue> getLstXuLyNo() {
        return lstXuLyNo;
    }

    public String getXuLyNo() {
        return XuLyNo;
    }

    public void setXuLyNo(String XuLyNo) {
        this.XuLyNo = XuLyNo;
    }

    public void setLstXuLyNo(List<ListValue> lstXuLyNo) {
        this.lstXuLyNo = lstXuLyNo;
    }

    Service_GQVL2023 _leaveHomeService;
    DuLieuNTService _service;
    

    public Local_GQVL2023_bk() {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
    }

    public String execute() throws Exception {
        return "success";
    }
    
    

  public String getLeaveLocal() {
       System.out.println("vao getLeaveLocal");
        String code = "";
        try {
//            _leaveHomeService = new Service_GQVL2023();
//            final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
//             this.lstData = _leaveHomeService.getCustomers(txtsMadv, "S", sFromDate, "1");
//             
//            lstCN = _leaveHomeService.getDonvi("M");
//            lstPGD = _leaveHomeService.getDonvi("S");
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }     
            return "success";
    }


    public String saveGQVL_2023() {
       
            try {
                System.out.println("vao saveGQVL_2023");
                List<DuLieuNTRow> lstSelectedData = new ArrayList<>();
                // Lay ra danh sach ma khach hang duoc chon
                List<String> lstSelectedCustomer = new ArrayList<>();

                for (int i = 0; i < this.lstData.size(); i++) {
                    if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {
                        if (!lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {
                            lstSelectedCustomer.add(this.lstData.get(i).getD11());
                        }
                    }
                }

                for (int i = 0; i < this.lstData.size(); i++) {
                    if (lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {
                        lstSelectedData.add(this.lstData.get(i));
                    }
                }

                String code = "";
                _leaveHomeService = new Service_GQVL2023();
                final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
                int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
                code = String.valueOf(_status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                System.err.println("Loi trong ham saveData " + e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
            }
            return "success";
          
}

    public String updateMember() {
        String code = "";
        try {
            _leaveHomeService = new Service_GQVL2023();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));
            int _status = _leaveHomeService.saveMembers(vsbpMaPgd, "S", sReportdt, this.sUser, this.sUser, this.lstData, "1");
            code = String.valueOf(_status);
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "success";
    }

 

    public List<DuLieuNTRow> getLstData() {
        return this.lstData;
    }

    public void setLstData(final List<DuLieuNTRow> lstData) {
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

    public String getVsbpMaPgd() {
        return vsbpMaPgd;
    }

    public void setVsbpMaPgd(String vsbpMaPgd) {
        this.vsbpMaPgd = vsbpMaPgd;
    }

}
