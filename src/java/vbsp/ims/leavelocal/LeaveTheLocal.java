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
    private String feedback;
    private String currentFeedback;
    private int flag;

    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }            

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public String getCurrentFeedback() {
        return currentFeedback;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }   

    public void setCurrentFeedback(String currentFeedback) {
        this.currentFeedback = currentFeedback;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    
    
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

    LeaveHomeService _leaveHomeService;
    DuLieuNTService _service;
    LeaveHomeDao homeDao = new LeaveHomeDao();

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
            System.out.println("cap phe duyet = " + gradeAuthor1);
            this.lstData = _leaveHomeService.getCustomers(txtsMadv, "S", txtMakh, sFromDate, sToDate, "1", _openFlag, gradeAuthor1, typeAuth);
            lstCN = _leaveHomeService.getDonvi("M");
            lstPGD = _leaveHomeService.getDonvi("S");
            
            posMainModel = listKTNBDA.get_pos_main_pos(sUser, sGrade);
            pos_cd_username = posMainModel.getPosCd();
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        if (gradeAuthor1.equals("1")) {
            return "success";
        } else {
            return "success_author";
        }
    }

    public String popupThanhvien() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
            this.lstData = _leaveHomeService.getMembers(vsbpMaPgd, "S", vsbpMakh, "1");
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }
    
    public String popupFeedback() throws Exception {
        try {
            
        } catch (Exception e) {
            System.err.println("Loi trong ham popupFeedback " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " popupFeedback -> " + e.getMessage());
        }
        return "success";
    }
    
    public String updateFeedback() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
            int _recordCnt = _leaveHomeService.updateFeedback(
                    vsbpMaPgd,
                    "S",
                    "20231231",
                    sUser,
                    sUser,
                    vsbpMakh,
                    feedback                    
            );
            String code = String.valueOf(_recordCnt);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham updateFeedback " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " updateFeedback -> " + e.getMessage());
        }
        return "success";
    }

    public String popupXuLyNo() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
//            ListValue l0 = new ListValue("0", "--Chọn--");
//            ListValue l1 = new ListValue("1", "Hộ vay cam kết trả nợ");
//            ListValue l2 = new ListValue("2", "Đã trả nợ");
//            ListValue l3 = new ListValue("3", "Bàn giao nợ");
//            ListValue l4 = new ListValue("4", "Xem xét xử lý nợ rủi ro");
//            ListValue l5 = new ListValue("5", "Hộ vay chưa hợp tác, tiếp tục đôn đốc");
//            List<ListValue> lst = new ArrayList<>();
//            lst.add(l0);
//           lst.add(l1);
//           lst.add(l2);
//           lst.add(l3);
//           lst.add(l4);
//           lst.add(l5);
//                    setLstXuLyNo(lst);
            setLstXuLyNo(new DaoNghiquyet11cp().getDanhMuc("SYSTEM", "XULYNO", "1"));
//            this.lstData = _leaveHomeService.getMembers(vsbpMaPgd, "S", vsbpMakh, "1") ;             
            this.lstData = homeDao.getDataDebtHandling("BO_DI_KHOI_DP_DH", vsbpMaPgd, "", "1", vsbpMakh);
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String saveLeaveLocal() {
        if (gradeAuthor1.equals("1")) {
            try {
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
                _leaveHomeService = new LeaveHomeService();
                final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
                int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
                code = String.valueOf(_status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                System.err.println("Loi trong ham saveData " + e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
            }
            return "success";
        } else // Xu ly phe duyet
        {
            if (typeAuth.equals("1")) // phe duyet cho xln
            {
                try {
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
                    for (DuLieuNTRow item : lstSelectedData) {
                        item.setD36("2");
                    }

                    String code = "";
                    _leaveHomeService = new LeaveHomeService();
                    final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
                    int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
                    code = String.valueOf(_status);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    System.err.println("Loi trong ham saveData " + e.getMessage());
                    CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
                }
                return "success";
            } else {
//phe duyet xoa   
// gọi api xóa nhưng ko thành công
//                try {
//                    List<DuLieuNTRow> lstSelectedData = new ArrayList<>();
//                    // Lay ra danh sach ma khach hang duoc chon
//                    List<String> lstSelectedCustomer = new ArrayList<>();
//
//                    for (int i = 0; i < this.lstData.size(); i++) {
//                        if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {
//                            if (!lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {
//                                lstSelectedCustomer.add(this.lstData.get(i).getD11());
//                            }
//                        }
//                    }
//
//                    for (int i = 0; i < this.lstData.size(); i++) {
//                        if (lstSelectedCustomer.contains(this.lstData.get(i).getD11())) {
//                            lstSelectedData.add(this.lstData.get(i));
//                        }
//                    }
//
//                    String code = "";
//                    _leaveHomeService = new LeaveHomeService();
//                    final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
//                    int _status = _leaveHomeService.clearCustomersLeave(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
//                    System.err.println("_status=" + _status);
//                    code = String.valueOf(_status);
//                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
//                } catch (Exception e) {
//                    System.err.println("Loi trong ham clearCustomersLeave " + e.getMessage());
//                    CoreLogger.error(this.getClass().getName() + " clearCustomersLeave -> " + e.getMessage());
//                }

// chuyển trạng thái D50 =9 để làm trạng thái xóa
 try {
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
                    for (DuLieuNTRow item : lstSelectedData) {
                        item.setD50("9");
                    }

                    String code = "";
                    _leaveHomeService = new LeaveHomeService();
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
    }
}

public String sendLeaveLocal() throws Exception {
        try {
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
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            int _status = _leaveHomeService.sendCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData);
            code = String.valueOf(_status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham sendLeaveLocal " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String fetchExcelUploadData() {
        try {
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
            final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
            _leaveHomeService = new LeaveHomeService();
            this.lstData = _leaveHomeService.getUploadExcelData(this.sUser, txtsMadv, sReportdt, txtMakh, sFromDate, sToDate, "1");
            lstCN = _leaveHomeService.getDonvi("M");
            lstPGD = _leaveHomeService.getDonvi("S");
            posMainModel = listKTNBDA.get_pos_main_pos(sUser, sGrade);
            pos_cd_username = posMainModel.getPosCd();
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
            code = String.valueOf(_status);
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "success";
    }

    public String debtHandling() {
        System.err.println("vao ham debtHandling ");
        String code = "";

        try {
            final String sFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtFromDate));
            final String sToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtToDate));
            _leaveHomeService = new LeaveHomeService();
            //Lấy thông tin khách hàng nhập xử lý nợ
            this.lstData = _leaveHomeService.getCustomers(vsbpMaPgd, "S", vsbpMakh, sFromDate, sToDate, "1", "1", "1", "");
            List<DuLieuNTRow> lstData_tmp = new ArrayList();
            for (DuLieuNTRow item : lstData) {
                if (item.getD20().equals("HOVAY")) {
                    item.setD35(XuLyNo);
                    item.setD36("1");
                    //Kiểm trả trường hợp Đã thu nợ nhưng ko chọn ngày bắt đầu trả nợ
                    if (XuLyNo.equals("2")) {
                        System.out.println("startPaymentDate = " + startPaymentDate);
                    }
                    if (startPaymentDate.length() < 8) {
                        return ERROR;
                    } else {
                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = inputFormat.parse(startPaymentDate);
                        String output = outputFormat.format(date);
                        System.out.println("startPaymentDate==== = " + output);
                        item.setD37(output);
                    }
                    lstData_tmp.add(item);
                }
            }
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.vsbpNgayBC));
            //Cập nhật thông tin khách hàng xử lý nợ 
            int _status = _leaveHomeService.saveCustomers(vsbpMaPgd, "S", sReportdt, this.sUser, this.sUser, lstData_tmp, "1");
            System.err.println("xulyno = " + lstData.get(0).getD35() + lstData.get(0).getD36() + lstData.get(0).getCode() + "_status = " + _status);
            code = String.valueOf(_status);
        } catch (Exception e) {
            System.err.println("Loi trong ham debtHandling " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " debtHandling -> " + e.getMessage());
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
            code = String.valueOf(_status);
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
            _leaveHomeService = new LeaveHomeService();
            final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            int _status = _leaveHomeService.suggestDeleteCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
            code = String.valueOf(_status);
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
