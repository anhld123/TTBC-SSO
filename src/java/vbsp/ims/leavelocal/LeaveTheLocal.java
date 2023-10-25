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
    private String ngaydenghi;
    private String sovbdenghi;
    private String vsbpMakh;
    private String vsbpTenKh;
    private String vsbpNgayBC;
    private InputStream pageResult;
    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    private String gradeAuthor1;
    private String showFlag1;
    private String flagPos;
    private String typeAuth;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    private List<ListValue> lstXuLyNo = new ArrayList<ListValue>();

    public String getNgaydenghi() {
        return ngaydenghi;
    }

    public void setNgaydenghi(String ngaydenghi) {
        this.ngaydenghi = ngaydenghi;
    }

    public String getSovbdenghi() {
        return sovbdenghi;
    }

    public void setSovbdenghi(String sovbdenghi) {
        this.sovbdenghi = sovbdenghi;
    }

    
    public String getFlagPos() {
        return flagPos;
    }

    public void setFlagPos(String flagPos) {
        this.flagPos = flagPos;
    }

    public String getShowFlag1() {
        return showFlag1;
    }

    public void setShowFlag1(String showFlag1) {
        this.showFlag1 = showFlag1;
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
            List<DuLieuNTRow> lstData_tmp = new ArrayList<DuLieuNTRow>();
            lstData = new ArrayList<DuLieuNTRow>();
            lstData_tmp = _leaveHomeService.getCustomers(txtsMadv, "S", txtMakh, sFromDate, sToDate, "1", _openFlag, gradeAuthor1, typeAuth);
            //Kiểm tra user thuộc pos cho vay hay không
            if (!lstData_tmp.isEmpty() && lstData_tmp.size() > 0) {
                for (DuLieuNTRow item : lstData_tmp) {
                    if (txtsMadv.substring(2, 4).equals(item.getPosCode().substring(2, 4))) {
                        item.setD42("1");
                    } else {
                        item.setD42("0");
                    }
                    lstData.add(item);
                }
            }
            lstCN = _leaveHomeService.getDonvi("M");
            lstPGD = _leaveHomeService.getDonvi("S");
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

    public String popupXuLyNo() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
            setLstXuLyNo(new DaoNghiquyet11cp().getDanhMuc("SYSTEM", "XULYNO", "1"));
            this.lstData = homeDao.getDataDebtHandling("BO_DI_KHOI_DP_DH", vsbpMaPgd, "", "1", vsbpMakh);
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }

    public String popupDeNghiHT() throws Exception {
        try {
            _leaveHomeService = new LeaveHomeService();
            setLstXuLyNo(new DaoNghiquyet11cp().getDanhMuc("SYSTEM", "XULYNO", "1"));
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
                        DuLieuNTRow tmp = new DuLieuNTRow();
                        tmp = this.lstData.get(i);
                        
                        String D22 =  tmp.getD22() == null ? "00" : tmp.getD22();
                        if(D22.equals("01"))
                        {
                            if(tmp.getD23() == null || tmp.getD23().trim().length() < 5)
                            {
                                this.pageResult = new ByteArrayInputStream("01".getBytes(StandardCharsets.UTF_8));
                                return "success";
                            }
                        }
                        //Lưu để phê duyệt đề nghị cung cấp thông tin
                        String check = "0";
                        try {
                            check = tmp.getD31() == null ? "0" : tmp.getD31();
                        } catch (Exception e) {
                            check = "0";
                        }
                        if((tmp.getD50().equals("1") || tmp.getD50().equals("5") || tmp.getD50().equals("7")) && check.equals("1"))
                            tmp.setD50("5");
                        lstSelectedData.add(tmp);
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
        } else 
        {
            if (typeAuth.equals("4")) // đề nghị cung cấp tt 4 về D50 =6
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
                        item.setD50("6");
                    }

                    String code = "";
                    _leaveHomeService = new LeaveHomeService();
                    final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
//                    System.out.println("flagPos = " + flagPos);
                    int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
                    code = String.valueOf(_status);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    System.err.println("Loi trong ham saveData " + e.getMessage());
                    CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
                }
                return "success";
            }
            else if (typeAuth.equals("5")) // đề nghị hỗ trợ 5 về D50 =7
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
                        item.setD50("7");
                    }

                    String code = "";
                    _leaveHomeService = new LeaveHomeService();
                    final String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
//                    System.out.println("flagPos = " + flagPos);
                    int _status = _leaveHomeService.saveCustomers(txtsMadv, "S", sReportdt, this.sUser, this.sUser, lstSelectedData, "1");
                    code = String.valueOf(_status);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    System.err.println("Loi trong ham saveData " + e.getMessage());
                    CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
                }
                return "success";
            }  else {
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

    private int checkInput(List<DuLieuNTRow> lstInput) {
        try {
            for (int i = 0; i < lstInput.size(); i++) {
                if (lstInput.get(i).getD22().equals("01") && lstInput.get(i).getD22().trim().length() < 5) {
                    return 1;
                }
            }
        } catch (Exception e) {
            return 9;
        }
        return 0;
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
    
    public String saveDeNghiHT() {
        System.err.println("vao ham saveDeNghiHT ");
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
//                    item.setD35(XuLyNo);
                    item.setD33("1");
                     item.setD38(ngaydenghi);
                     item.setD39(sovbdenghi);
                     item.setD50("5");    //Thông tin đề nghị hỗ trợ

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
