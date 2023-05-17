package vbsp.ims.leavelocal;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.log.CoreLogger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionContext;
import java.io.InputStream;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRow;

public class LeaveTheLocal extends ActionSupport {

    private List<DuLieuNTRow> lstData;
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
    private String vsbpMakh;
    private String vsbpTenKh;
    private String vsbpNgayBC;
    private InputStream pageResult;    
    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    
    LeaveHomeService _leaveHomeService;

    public LeaveTheLocal() {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
    }

    public String execute() throws Exception {
        return "success";
    }

    public String getLeaveLocal() throws Exception {     
        try {            
            final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
            final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
            _leaveHomeService = new LeaveHomeService();
            String _openFlag = "0";
            if (sGrade.equals("1") && !txtMakh.isEmpty()) {
                _openFlag = "1";
            }
            this.lstData = _leaveHomeService.getCustomers(txtsMadv, "S", txtMakh, sFromDate, sToDate, "1", _openFlag) ;                
            lstCN = _leaveHomeService.getDonvi("M");
            lstPGD = _leaveHomeService.getDonvi("S");
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String popupThanhvien() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
            this.lstData = _leaveHomeService.getMembers(vsbpMaPgd, "S", vsbpMakh, "1") ; 
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String saveLeaveLocal() {
        try {
            List<DuLieuNTRow> lstSelectedData = new ArrayList<>();        
            // Lay ra danh sach ma khach hang duoc chon
            List<String> lstSelectedCustomer = new ArrayList<>();

            for(int i = 0 ; i < this.lstData.size(); i++)
            {
                if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {                
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

            String code = "";
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
            code = String.valueOf( _status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String sendLeaveLocal() throws Exception {        
        try {
            List<DuLieuNTRow> lstSelectedData = new ArrayList<>();        
            // Lay ra danh sach ma khach hang duoc chon
            List<String> lstSelectedCustomer = new ArrayList<>();

            for(int i = 0 ; i < this.lstData.size(); i++)
            {
                if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {                
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

            String code = "";
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            int _status = _leaveHomeService.sendCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData);
            code = String.valueOf( _status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham sendLeaveLocal " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }
    
    public String fetchExcelUploadData(){
        try {       
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));            
            final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
            final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
            _leaveHomeService = new LeaveHomeService();
            this.lstData = _leaveHomeService.getUploadExcelData(this.sUser, txtsMadv, sReportdt, txtMakh, sFromDate, sToDate, "1") ;                
            lstCN = _leaveHomeService.getDonvi("M");
            lstPGD = _leaveHomeService.getDonvi("S");
        } catch (Exception e) {
            System.err.println("Loi trong ham fetchExcelUploadData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " fetchExcelUploadData -> " + e.getMessage());
        }
        return "success";
    }

    public String updateMember() {
        String code = "";
        try {
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));            
            int _status = _leaveHomeService.saveMembers(vsbpMaPgd, "S", sReportdt, this.sUser, this.sUser, this.lstData, "1");           
            code = String.valueOf( _status);
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));        
        return "success";
    }
    
    public String clearMember() {
        String code = "";
        try {
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));            
            int _status = _leaveHomeService.clearMembers(vsbpMaPgd, "S", sReportdt, this.sUser, this.sUser, this.vsbpMakh, "1");           
            code = String.valueOf( _status);
        } catch (Exception e) {
            System.err.println("Loi trong ham clearMember " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " clearMember -> " + e.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        
        return "success";
    }

    public String suggestDeteleCustomer() {        
        try {
            List<DuLieuNTRow> lstSelectedData = new ArrayList<>();        
            // Lay ra danh sach ma khach hang duoc chon
            List<String> lstSelectedCustomer = new ArrayList<>();

            for(int i = 0 ; i < this.lstData.size(); i++)
            {
                if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {                
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

            String code = "";
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            int _status = _leaveHomeService.suggestDeleteCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
            code = String.valueOf( _status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham suggestDeteleCustomer " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " suggestDeteleCustomer -> " + e.getMessage());
        }        
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
