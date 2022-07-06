/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.InputBranchXml;

/**
 *
 * @author BAOANH
 */
public class ActionConfigBranch extends ActionInputFormBranchMain {

//    private static final long serialVersionUID = -3827439829486925185L;
    private List<ListValue> lstGrade = new ArrayList<ListValue>();
    private String[] defaultGrade;
    private List<String> rptGrade = new ArrayList<>();
    private String khoa;
    private String tenmau;
    private String grade_id;
    private String grouprpt_id;
    private String msg;
    private List<ListValue> lstDmucTso = new ArrayList<ListValue>();
    private List<ListValue> lstAllMau = new ArrayList<>();
    private String loai_chitieu;
    private String macdinh_chitieu;
    private List<DULIEU_NT_CN> lstDulieu = new ArrayList<>();
    protected List<ReportParam> lstParameterChomau = new ArrayList<>();
    private List<Map> listmap = new ArrayList<Map>();
    private List<MappingCot> lstMapCot = new ArrayList<>();
    private List<DULIEU_VIEW> lstDuliewView = new ArrayList<>();
    private String loaimau_daluu;
    private List<ModelParameter> lstParameter = new ArrayList<>();
    private String donvitinh;
    private boolean copydl;
    private boolean dongbo_dl;
    private int index;
    private String queryString;
    private MappingCot cottruyvan;
    private List<ListValue> lstGroupRpt = new ArrayList<ListValue>();

    //<editor-fold defaultstate="collapsed" desc="Cho phần thêm chỉ tiêu dạng hàng cột, truy vấn">
    public String Themmoichitieu() {
        try {
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            setLstAllMau(daobranc.getAllNhaptayBranch(UserName, Grade, "ADD_IND", null));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> Themmoichitieu: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> Themmoichitieu: " + e.getMessage());
            setMessage(e.getMessage());
            setMsg(" " + e.getMessage());

            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String LoadAllMauNhaplieu() {
        try {
            HashMap<String, Object> hmParameter = getParameter();
            System.err.println("Load bao cao nhap du lieu grouprpt_id=" + grouprpt_id);
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            setLstAllMau(daobranc.getAllNhaptayBranch(UserName, Grade, "LOAD", grouprpt_id));
            setLstGroupRpt(daobranc.getGroupReport(UserName));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> Themmoichitieu: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> Themmoichitieu: " + e.getMessage());
            setMsg(" " + e.getMessage());
            setMessage(e.getMessage());
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    /*
    Hàm  này khi chọn mẫu biểu sẽ tự động load lên các dạnh chỉ tiêu hàng, cột để lựa chọn
     */
    public String LoadDanhmucchitieu() {
        try {
            if (khoa == null || khoa.isEmpty() || khoa.equals("-1")) {
                setMessage("Bạn phải chọn mẫu cần nhập chỉ tiêu ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            setMacdinh_chitieu(daobranc.getLoaimau(khoa));
            loaimau_daluu = getMacdinh_chitieu();

            if (macdinh_chitieu == null || macdinh_chitieu.isEmpty()) {
                setMacdinh_chitieu("CT");
                setLoaimau_daluu("NEW");
            }
            System.err.println(khoa);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadDanhmucchitieu: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadDanhmucchitieu: " + e.getMessage());

            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * Hàm này thực hiện load cấu hình hàng cột đã lưu lên
     *
     * @return
     */
    public String loadCauhinhChieuCotDulieu() {
        try {
            System.err.println("loai_chitieu=" + loai_chitieu);
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể lấy được khóa của báo cáo ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (loai_chitieu == null || loai_chitieu.isEmpty()) {
                setMessage("Không thể lấy ra được loại dữ liệu ");
                setMsg(message);
                addActionError(message);
                return ERROR;
            }
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (loai_chitieu.equals("QR")) {

            } else {
                setLstDulieu(daobranc.getDulieuMauCauhinh(khoa));
                HashMap<String, List<DULIEU_NT_CN>> mapDulieu = new HashMap<>();
                mapDulieu = daobranc.getCauhinhChitieuCot(khoa);

                setLstDulieuChitieu(mapDulieu.get("CHITIEU"));
                setLstDulieuCot(mapDulieu.get("COT"));
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadDulieuMauCauhinh: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadDulieuMauCauhinh: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return loai_chitieu;
    }

    /**
     * Hàm này lưu chỉ tiêu của mẫu nhập theo dạng, Chỉ tiêu dạng hàng, dạng
     * cột, dạng theo truy vấn Lưu tên chỉ tiêu, mã chỉ tiêu được tự sinh, kiểu
     * dữ liệu Hàm này lưu chung cho các mẫu Chỉ tiêu cột, hàng, truy vấn
     *
     * @return
     */
    public String saveChitieunhaptay() {
        try {
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (khoa == null || khoa.isEmpty() || khoa.equals("-1")) {
                setMessage("Bạn phải chọn mẫu cần nhập chỉ tiêu ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (lstDulieuChitieu.size() == 0) {
                setMessage("Bạn phải thêm chỉ tiêu mới thực hiện lưu dữ liệu");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (lstDulieuCot.size() == 0) {
                setMessage("Bạn phải thêm ít nhất một cột mới thực hiện lưu dữ liệu");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            /*
            List<DULIEU_NT_CN> lstDulieuSave = new ArrayList<>();
            for (DULIEU_NT_CN dulieu : lstDulieu) {
                if (dulieu != null) {
                    dulieu.setKHOA(khoa);
                    if (loai_chitieu.equals("CT")) {
                        if (dulieu.getMA() == null || dulieu.getMA().isEmpty() || dulieu.getTEN() == null || dulieu.getTEN().isEmpty()) {
                            setMessage("Bạn phải nhập đầy đủ dữ liệu trước khi lưu");
                            addActionError(message);
                            setMsg(message);
                            return ERROR;
                        }
                    } else {
                        if (dulieu.getTEN() == null || dulieu.getTEN().isEmpty()) {
                            setMessage("Bạn phải nhập đầy đủ dữ liệu trước khi lưu");
                            addActionError(message);
                            setMsg(message);
                            return ERROR;
                        }
                    }
                    System.err.println("ma=" + dulieu.getMA() + " ten=" + dulieu.getTEN() + " kieudulieu=" + dulieu.getKIEUDULIEU());
                    lstDulieuSave.add(dulieu);
                }
            }
             */
            //Kiểm tra dữ liệu cấu hình theo chỉ tiêu
            List<DULIEU_NT_CN> lstChitieuSave = new ArrayList<>();
            for (DULIEU_NT_CN dulieu : lstDulieuChitieu) {
                if (dulieu != null) {
                    dulieu.setKHOA(khoa);
                    if (dulieu.getMA() == null || dulieu.getMA().isEmpty() || dulieu.getTEN() == null || dulieu.getTEN().isEmpty()) {
                        setMessage("Bạn phải nhập đầy đủ dữ liệu trước khi lưu");
                        addActionError(message);
                        setMsg(message);
                        return ERROR;
                    }
                    System.err.println("mact=" + dulieu.getMA() + " ten=" + dulieu.getTEN());
                    lstChitieuSave.add(dulieu);
                }
            }
            //Cấu hình theo cột
            List<DULIEU_NT_CN> lstCotSave = new ArrayList<>();
            for (DULIEU_NT_CN dulieu : lstDulieuCot) {
                if (dulieu != null) {
                    dulieu.setKHOA(khoa);
                    if (dulieu.getTEN() == null || dulieu.getTEN().isEmpty()) {
                        setMessage("Bạn phải nhập đầy đủ dữ liệu trước khi lưu");
                        addActionError(message);
                        setMsg(message);
                        return ERROR;
                    }
                    if (dulieu.getKIEUDULIEU().equals("L")) {
                        String stringData = dulieu.getMA();
                        if (isNullOrEmpty(stringData)) {
                            setMessage("Bạn phải điền đẩy đủ dữ liệu cho kiểu danh mục của cột (" + dulieu.getTEN() + ") !");
                            addActionError(message);
                            setMsg(message);
                            return ERROR;
                        } else {
                            String[] arrayQuery = stringData.split("#");
                            for (int i = 0; i < arrayQuery.length; i++) {

                                switch (i) {
                                    case 0:
                                        dulieu.setD1(arrayQuery[0]); //Lưu bảng dữ liệu cho tham số cột
                                        break;
                                    case 1:
                                        dulieu.setD2(arrayQuery[1]); //Lưu cột hiển thị cho tham số cột
                                        break;
                                    case 2:
                                        dulieu.setD3(arrayQuery[2]); //Lưu cột tham số cho tha số nhập 
                                        break;
                                    case 3:
                                        dulieu.setD4(arrayQuery[3]); //Lưu điều kiện lọc cho tham số nhập
                                        break;
                                    case 4:
                                        dulieu.setD5(arrayQuery[4]); //Lưu điều kiện sắp xếp
                                        break;
                                }
                            }
                            if (isNullOrEmpty(dulieu.getD1()) || isNullOrEmpty(dulieu.getD2()) || isNullOrEmpty(dulieu.getD3())) {
                                setMessage("Bạn phải điền đẩy đủ dữ liệu cho kiểu danh mục của cột (" + dulieu.getTEN() + ")");
                                addActionError(message);
                                setMsg(message);
                                return ERROR;
                            }
                        }
                    }
                    dulieu.setD50(dulieu.getMA());
                    dulieu.setMA("");
                    System.err.println(" ten=" + dulieu.getTEN() + " kieudulieu=" + dulieu.getKIEUDULIEU());
                    lstCotSave.add(dulieu);
                }
            }
            daobranc.saveChitieuCot(lstChitieuSave, lstCotSave, UserName);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveChitieunhaptay: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveChitieunhaptay: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu chỉ tiêu thành công ");
        return SUCCESS;
    }

    public boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    public String showdialog() {
        try {
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            System.err.println("Load du lieu index=" + index);

            if (queryString != null || !queryString.isEmpty()) {
                String[] arrayQuery = queryString.split("#");
                MappingCot cot = new MappingCot();
                for (int i = 0; i < arrayQuery.length; i++) {

                    switch (i) {
                        case 0:
                            cot.setBANGSL(arrayQuery[0]);
                            break;
                        case 1:
                            cot.setCOTHIENTHI(arrayQuery[1]);
                            break;
                        case 2:
                            cot.setCOTTSO(arrayQuery[2]);
                            break;
                        case 3:
                            cot.setDKLOC(arrayQuery[3]);
                            break;
                        case 4:
                            cot.setDKSAPXEP(arrayQuery[4]);
                            break;
                    }
                }
                setCottruyvan(cot);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveChitieunhaptay: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveChitieunhaptay: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String checkDulieuDanhmucCotDulieu() {
        try {
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            //System.err.println("Load du lieu index=" + index);
            if (queryString != null || !queryString.isEmpty()) {
                String[] arrayQuery = queryString.split("#");
                MappingCot cot = new MappingCot();
                for (int i = 0; i < arrayQuery.length; i++) {

                    switch (i) {
                        case 0:
                            cot.setBANGSL(arrayQuery[0]);
                            break;
                        case 1:
                            cot.setCOTHIENTHI(arrayQuery[1]);
                            break;
                        case 2:
                            cot.setCOTTSO(arrayQuery[2]);
                            break;
                        case 3:
                            cot.setDKLOC(arrayQuery[3]);
                            break;
                        case 4:
                            cot.setDKSAPXEP(arrayQuery[4]);
                            break;
                    }
                }
                setCottruyvan(cot);
            } else {
                setMessage("Bạn phải điền đầy đủ tham số khi tạo kiểu dữ liệu là danh mục !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            int rownum = daobranc.checkDulieuDanhmucCotDulieu(khoa == null ? "9999999" : khoa, UserName, Grade,
                    cottruyvan.getBANGSL(), cottruyvan.getCOTHIENTHI(),
                    cottruyvan.getCOTTSO(), cottruyvan.getDKLOC(), cottruyvan.getDKSAPXEP());
            if (rownum == 0) {
                setMessage("Bạn xem lại dữ liệu lấy lên danh mục ?, Kiểm tra dữ liệu thấy không có dữ liệu cho danh mục !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
        } catch (Exception e) {

            CoreLogger.error(this.getClass().getName() + " Exception -> checkDulieuDanhmucCotDulieu: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> checkDulieuDanhmucCotDulieu: " + e.getMessage());
            setMessage("Lỗi: Bạn kiểm tra lại các trường dữ liệu khai báo (Bảng số liệu), (Cột hiển thị), (Cột tham số) ... có trường bị sai !. " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho form nhập liệu mẫu và thám số cho mẫu">
    /**
     * Hàm này khởi tạo form khi thực hiện nhập cho mẫu nhập tay Tải thông tin 3
     * cấp nhập liệu, Thông tin các tham số khi nhập
     *
     * @return
     */
    public String loadAddNewNameForm() {
        try {
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            lstGrade.add(new ListValue("1", "Ngân hàng"));
            lstGrade.add(new ListValue("2", "Chi nhánh"));
            lstGrade.add(new ListValue("3", "Toàn quốc"));
            setLstGroupRpt(daobranc.getGroupReport(UserName));

            if (loaimau_daluu.equals("EDIT")) {
                //setLoaimau_daluu("EDIT");
                LoadEditMauView editView = daobranc.getMaubieuEdit(khoa);

                setTenmau(editView.getTenmau());
                String[] arrCapbc = editView.getCapbc().split("#");
                List<String> lstCapbc = new ArrayList<>();
                for (String capbc : arrCapbc) {
                    if (!capbc.isEmpty()) {
                        lstCapbc.add(capbc);
                    }
                }
                setDefaultGrade(lstCapbc.toArray(new String[lstCapbc.size()]));

                setLstParameter(editView.getLstParameter());
                setDonvitinh(editView.getDonvitinh());
                setCopydl(editView.isCopydl());
                setGrouprpt_id(editView.getNhombc());
                setDongbo_dl(editView.isDongbo_dl());
            } else {
                //setLoaimau_daluu("NEW");
                setDefaultGrade(new String[]{"1"});
                //setLstDmucTso(daobranc.getDmucTso());

                if (khoa.equals("-1")) {
                    khoa = "";
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
        }
        return SUCCESS;
    }

    public String saveAddNameForm() {
        try {
            HashMap<String, Object> hp = getParameter();
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Bạn phải nhập khóa cần tạo của mẫu ");
                addActionError(message);
                msg = "Bạn phải nhập khóa cần tạo của mẫu ";
                return ERROR;
            }
            if (tenmau == null || tenmau.isEmpty()) {
                setMessage("Bạn phải điền tên mô tả báo cáo ");
                addActionError(message);
                msg = "Bạn phải điền tên mô tả báo cáo ";
                return ERROR;
            }
//            if (grade_id == null || grade_id.isEmpty()) {
//                setMessage("Bạn phải chọn cấp nhập dữ liệu ");
//                addActionError(message);
//                msg = "Bạn phải chọn cấp nhập dữ liệu ";
//                //reponseMsg(message);
//                return ERROR;
//            }
            rptGrade = convertStringtoList(grade_id.replace(" ", "").split(","));
            if (rptGrade == null || rptGrade.isEmpty()) {
                setMessage("Bạn phải chọn cấp nhập dữ liệu ");
                msg = "Bạn phải chọn cấp nhập dữ liệu ";
                addActionError(message);
                return ERROR;
            }
            String sGradeReport = "";
            for (String sRptGrade : rptGrade) {
                sGradeReport += "#" + sRptGrade;
            }
            sGradeReport += "#";
            if (!getParaSession()) {
                return ERROR;
            }
//            daobranc.saveTenMauBieu(khoa, UserName, tenmau, sGradeReport);
            //msg = "Đã lưu dữ liệu thành công";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    //Đang dung ham nay
    /**
     * Hàm này lưu lại dữ liệu khi tạo mẫu gồm Tiêu đề của mẫu Khóa của mẫu Tham
     * số của mẫu
     *
     * @return
     */
    public String saveAddNameForm1() {
        try {
            HashMap<String, Object> hmParameter = getParameter();

            khoa = hmParameter.get("khoa").toString();
            tenmau = hmParameter.get("tenmau").toString();
            grade_id = hmParameter.get("rptGrade").toString();
            loaimau_daluu = hmParameter.get("loaimau_daluu").toString();
            donvitinh = hmParameter.get("donvitinh").toString();
            grouprpt_id = hmParameter.get("grouprpt_id").toString();
            try {
                setCopydl(hmParameter.get("copydl").toString().toLowerCase().equals("on") ? true : false);
            } catch (Exception e) {
                setCopydl(false);
            }

            try {
                setDongbo_dl(hmParameter.get("dongbo_dl").toString().toLowerCase().equals("on") ? true : false);
            } catch (Exception e) {
                setDongbo_dl(false);
            }
            if (loaimau_daluu == null || loaimau_daluu.isEmpty()) {
                setMessage("Lỗi khi thực hiện lấy tham số, xin liên hệ với quản trị để khắc phục !");
                addActionError(message);
                msg = "Bạn phải nhập khóa cần tạo của mẫu ";
                return ERROR;
            }

            if (khoa == null || khoa.isEmpty()) {
                setMessage("Bạn phải nhập khóa cần tạo của mẫu ");
                addActionError(message);
                msg = "Bạn phải nhập khóa cần tạo của mẫu ";
                return ERROR;
            }
            if (tenmau == null || tenmau.isEmpty()) {
                setMessage("Bạn phải điền tên mô tả báo cáo ");
                addActionError(message);
                msg = "Bạn phải điền tên mô tả báo cáo ";
                return ERROR;
            }
            if (donvitinh == null || donvitinh.isEmpty()) {
                setMessage("Bạn nhập đơn vị tính ");
                addActionError(message);
                msg = "Bạn nhập đơn vị tính";
                return ERROR;
            }
            if (grade_id == null || grade_id.isEmpty()) {
                setMessage("Bạn phải chọn cấp nhập dữ liệu ");
                addActionError(message);
                msg = "Bạn phải chọn cấp nhập dữ liệu ";
                //reponseMsg(message);
                return ERROR;
            }

            if (grouprpt_id == null || grouprpt_id.isEmpty() || grouprpt_id.equals("-1")) {
                setMessage("Bạn phải chọn nhóm báo cáo");
                addActionError(message);
                msg = "Bạn phải chọn nhóm báo cáo ";
                //reponseMsg(message);
                return ERROR;
            }
            rptGrade = convertStringtoList(grade_id.replace(" ", "").split(","));
            if (rptGrade == null || rptGrade.isEmpty()) {
                setMessage("Bạn phải chọn cấp nhập dữ liệu ");
                msg = "Bạn phải chọn cấp nhập dữ liệu ";
                addActionError(message);
                return ERROR;
            }
            String sGradeReport = "";
            for (String sRptGrade : rptGrade) {
                sGradeReport += "#" + sRptGrade;
            }
            sGradeReport += "#";
            if (!getParaSession()) {
                return ERROR;
            }

            int[] countpara = getCountParameter(hmParameter);
            List<ModelParameter> modelParameters = new ArrayList<>();
            Arrays.sort(countpara);

            int stt = 1;
            for (int index : countpara) {
                ModelParameter parameter = getParameterObject(khoa, hmParameter, index);
                parameter.setStt(stt);
                parameter.setThamso("thamso_" + stt);
                modelParameters.add(parameter);
                stt++;
            }
            for (ModelParameter model : modelParameters) {
                if (model.getLoaitso().equals("L")) {
                    int rownum = daobranc.checkDulieuDanhmucCotDulieu(khoa, UserName, Grade, model.getBangsl(),
                            model.getCothienthi(), model.getCottso(), model.getDkloc(), model.getDksapxep());
                    if (rownum == 0) {
                        setMessage("Lỗi: Bạn xem lại dữ liệu lấy lên danh mục ?, Kiểm tra dữ liệu thấy không có dữ liệu cho danh mục !");
                        addActionError(message);
                        setMsg(message);
                        CoreLogger.error(message);
                        return ERROR;
                    }
                }
            }
            daobranc.saveTenMauBieu(khoa, UserName, tenmau, sGradeReport, modelParameters, loaimau_daluu, donvitinh,
                    copydl ? "Y" : "N", grouprpt_id, dongbo_dl ? "Y" : "N");
            //msg = "Đã lưu dữ liệu thành công";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadAddNewNameForm: " + e.getMessage());

            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String LoadGroupRpt() {
        try {

            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            setLstGroupRpt(daobranc.getGroupReport(null));
            grouprpt_id = "-1";
            //setLstAllMau(daobranc.getAllNhaptayBranch(UserName, Grade, "EDIT"));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String LoadAllBCEditDel() {
        try {

            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            if (grouprpt_id == null || grouprpt_id.isEmpty() || grouprpt_id.equals("-1")) {
                setMessage("Bạn phải chọn nhóm báo cáo");
                addActionError(message);
                msg = "Bạn phải chọn nhóm báo cáo ";
                //reponseMsg(message);
                return ERROR;
            }
            setLstAllMau(daobranc.getAllNhaptayBranch(UserName, Grade, "DELETE", grouprpt_id));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String deleteMaubc() {
        try {
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể xóa được báo cáo này xin liên hệ với quản trị ");
                addActionError(message);
                msg = "Bạn phải nhập khóa cần tạo của mẫu ";
                return ERROR;
            }
            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            daobranc.deleteMaubaocao(khoa);
            addActionMessage("Bạn đã xóa mẫu biểu thành công");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadAllBCEditDel: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Các hàm cho phần nhập liệu">
    public String loadThamsochomau() {
        try {
            System.err.println("khoa=" + khoa);
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể lấy được khóa của báo cáo ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            setLstParameterChomau(daobranc.getParameterChomau(khoa, UserName, Grade));

            String Sync = daobranc.getLoaimauSync(khoa);
            if (Sync == null || !Sync.equals("Y")) {
                setDongbo_dl(false);
            } else {
                setDongbo_dl(true);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadThamsochomau: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadThamsochomau: " + e.getMessage());
            msg = e.getMessage();
            //addActionError(e.getMessage());
            setMessage("Không tải được tham số, xin liên hệ với quản trị để được khắc phục !. " + e.getMessage());
            setMsg(message);
            //addActionError("Không tải được tham số, xin liên hệ với quản trị để được khắc phục !");
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadDulieuMau() {
        try {

            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            HashMap<String, Object> hmParameter = getParameter();

            //khoa = hmParameter.get("khoa").toString();
            System.err.println("khoa=" + khoa);
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể lấy được khóa của báo cáo ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            List<DULIEU_NT_CN> lstThamso = getParameterLoadData(hmParameter);
            for (DULIEU_NT_CN dl : lstThamso) {
                if (!DefineFun.isValid(dl.getTEN())) {
                    setMessage("Dữ liệu trường tham số không đúng, xin nhập lại dữ liệu tham số THAMSO="+dl.getMA()+" -> VALUE="+dl.getTEN());
                    addActionError(message);
                    setMsg(message);
                    return ERROR;
                }
            }
            //Lấy dữ liệu đã lưu
            setLstDulieu(daobranc.getDulieuBaocaoNhap(khoa, lstThamso, UserName, Grade));
            //Lấy tên cột và mapping đã lưu vào bảng
            setLstMapCot(daobranc.getDulieuMappingCot(khoa));

            HashMap<String, List<ListValue>> hmDataColumn = daobranc.getDulieuColumnDanhmuc(khoa, UserName, Grade);
            //đưa sang view
            setLstDuliewView(daobranc.convertDataToView(lstDulieu, lstMapCot, hmDataColumn));

            //setTenmau(daobranc.getTenmau(khoa));
            HashMap<String, String> hmTieuDe_donvitinh = daobranc.getTenmauDonvitinh(khoa);

            setTenmau(hmTieuDe_donvitinh.get("tenmau"));
            setDonvitinh(hmTieuDe_donvitinh.get("donvitinh"));

            //listmap.add(hmParameter);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadDulieuMau: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadDulieuMau: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            //addActionError("Bạn chưa tải được dữ liệu, xin liên hệ với quản trị để được khắc phục !");
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveDulieuNhaptay() {
        try {

            if (!getParaSession()) {
                setMessage("Đã hết phiên làm việc bạn phải thoát ra khỏi hệ thống và đăng nhập lại !");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            HashMap<String, Object> hmParameter = getParameter();

            //khoa = hmParameter.get("khoa").toString();
            System.err.println("khoa=" + khoa);
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể lấy được khóa của báo cáo ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            if (lstDulieu == null || lstDulieu.size() == 0) {
                setMessage("Không thể lấy dữ liệu đã nhập để lưu? ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }
            for (DULIEU_NT_CN dulieu : lstDulieu) {
                dulieu.setNGUOI_NHAP(UserName);
            }
             
            for (DULIEU_NT_CN dl : lstDulieu) {
                if (!DefineFun.isValid(dl.getTHAMSO_1())||!DefineFun.isValid(dl.getTHAMSO_2())
                        ||!DefineFun.isValid(dl.getTHAMSO_3())||!DefineFun.isValid(dl.getTHAMSO_4())
                        ||!DefineFun.isValid(dl.getTHAMSO_5())||!DefineFun.isValid(dl.getTHAMSO_6())
                        ||!DefineFun.isValid(dl.getTHAMSO_7())||!DefineFun.isValid(dl.getTHAMSO_8())
                        ||!DefineFun.isValid(dl.getTHAMSO_9())||!DefineFun.isValid(dl.getTHAMSO_10())) {
                    setMessage("Dữ liệu trường tham số đã bị điều chỉnh không đúng, xin nhập lại dữ liệu tham số ");
                    addActionError(message);
                    setMsg(message);
                    return ERROR;
                }
            }
            if (daobranc.saveDulieuNhapTay(khoa, lstDulieu, UserName, Grade)) {
                addActionMessage("Bạn đã lưu dữ liệu thành công");
            }
            //listmap.add(hmParameter);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveDulieuNhaptay: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveDulieuNhaptay: " + e.getMessage());
            setMessage("Lỗi, Xin liên hệ với quản trị để được khắc phục ! " + e.getMessage());
            setMsg(message);
            addActionError(message.replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    private boolean isValidateData(DULIEU_NT_CN dulieu)
    {
        return true;
    }
    public String sendDulieuNhapTaySync() {
        System.err.println("Vao ham sendDulieuNhapTaySync");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            setKhoa(hmParameter.get("khoa").toString());

            String tt_lock = hmParameter.get("Send") == null ? "SEND" : hmParameter.get("Send").toString();
            System.err.println("khoa=" + khoa);
            if (khoa == null || khoa.isEmpty()) {
                setMessage("Không thể lấy được khóa của báo cáo ");
                addActionError(message);
                setMsg(message);
                return ERROR;
            }

            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            Date date = new Date();

            String str2 = format.format(date);

            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : context.getRealPath("/") + Define.M_REPORT_XML;
            strPathSave += hmParameter.get("khoa").toString()
                    + "_" + UserName + "_" + str2 + "_"
                    + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

            List<String> lstData = new ArrayList<>();
            boolean bStatus_file = false;

            List<DULIEU_NT_CN> lstThamso = getParameterLoadData(hmParameter);
            lstData = daobranc.getDulieuSync(khoa, lstThamso, UserName, Grade);

            bStatus_file = new InputBranchXml().createXmlFileInputBranch(Define.PARA_SYN_INPUT_BRANCH, khoa, UserName, Grade, lstData, strPathSave, tt_lock);
            if (!bStatus_file) {
                addActionError("Bạn chưa tạo được file dữ liệu để gửi của về Trung ương ");
                setMessage("Bạn chưa tạo được file dữ liệu để gửi của về Trung ương ! ");
                setMsg(message);
                CoreLogger.error(this.getClass().getName() + " Exception -> sendDulieuNhapTaySync: Khong tao duoc file " + strPathSave);
                return ERROR;
            }
            //Tao file xml theo cau truc
//
            File checkfile = new File(strPathSave);
            if (!checkfile.exists()) {
                addActionError("Bạn chưa tạo được file dữ liệu để gửi của về Trung ương ");
                setMessage("Bạn chưa tạo được file dữ liệu để gửi của về Trung ương ! ");
                setMsg(message);
                CoreLogger.error(this.getClass().getName() + " Exception -> sendDulieuNhapTaySync: Khong tao duoc file " + strPathSave);
                return ERROR;
            }
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
            if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                addActionError("Error: Đồng bộ dữ liệu về TW lỗi, xin liên hệ với quản trị để khắc phục");
                setMessage("Error: Đồng bộ dữ liệu về TW lỗi, xin liên hệ với quản trị để khắc phục");
                setMsg(message);
                if (checkfile.exists()) {
                    checkfile.delete();
                }
                CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                return ERROR;
            } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                if (checkfile.exists()) {
                    checkfile.delete();
                }
            } else {
                if (checkfile.exists()) {
                    checkfile.delete();
                }
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                addActionError("Mẫu này đã bị khóa không thể đồng bộ dữ liệu về, xin liên hệ về TW để được đồng bộ dữ liệu");
                setMessage("Mẫu này đã bị khóa không thể đồng bộ dữ liệu về, xin liên hệ về TW để được đồng bộ dữ liệu");
                setMsg(message);
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }

        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        setMessage("Bạn gửi dữ liệu về trung ương thành công !");
        setMsg(message);
        return SUCCESS;
    }
//</editor-fold>

    public String getGrouprpt_id() {
        return grouprpt_id;
    }

    public void setGrouprpt_id(String grouprpt_id) {
        this.grouprpt_id = grouprpt_id;
    }

    public List<ListValue> getLstGroupRpt() {
        return lstGroupRpt;
    }

    public void setLstGroupRpt(List<ListValue> lstGroupRpt) {
        this.lstGroupRpt = lstGroupRpt;
    }

    public MappingCot getCottruyvan() {
        return cottruyvan;
    }

    public void setCottruyvan(MappingCot cottruyvan) {
        this.cottruyvan = cottruyvan;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isCopydl() {
        return copydl;
    }

    public void setCopydl(boolean copydl) {
        this.copydl = copydl;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public List<ModelParameter> getLstParameter() {
        return lstParameter;
    }

    public void setLstParameter(List<ModelParameter> lstParameter) {
        this.lstParameter = lstParameter;
    }

    public String getLoaimau_daluu() {
        return loaimau_daluu;
    }

    public void setLoaimau_daluu(String loaimau_daluu) {
        this.loaimau_daluu = loaimau_daluu;
    }

    public List<Map> getListmap() {
        return listmap;
    }

    public void setListmap(List<Map> listmap) {
        this.listmap = listmap;
    }

    public List<ReportParam> getLstParameterChomau() {
        return lstParameterChomau;
    }

    public void setLstParameterChomau(List<ReportParam> lstParameterChomau) {
        this.lstParameterChomau = lstParameterChomau;
    }

    public List<DULIEU_NT_CN> getLstDulieu() {
        return lstDulieu;
    }

    public void setLstDulieu(List<DULIEU_NT_CN> lstDulieu) {
        this.lstDulieu = lstDulieu;
    }

    public String getMacdinh_chitieu() {
        return macdinh_chitieu;
    }

    public void setMacdinh_chitieu(String macdinh_chitieu) {
        this.macdinh_chitieu = macdinh_chitieu;
    }

    public String getLoai_chitieu() {
        return loai_chitieu;
    }

    public void setLoai_chitieu(String loai_chitieu) {
        this.loai_chitieu = loai_chitieu;
    }

    public List<ListValue> getLstAllMau() {
        return lstAllMau;
    }

    public void setLstAllMau(List<ListValue> lstAllMau) {
        this.lstAllMau = lstAllMau;
    }

    public List<ListValue> getLstDmucTso() {
        return lstDmucTso;
    }

    public void setLstDmucTso(List<ListValue> lstDmucTso) {
        this.lstDmucTso = lstDmucTso;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public List<String> getRptGrade() {
        return rptGrade;
    }

    public void setRptGrade(List<String> rptGrade) {
        this.rptGrade = rptGrade;
    }

    public List<ListValue> getLstGrade() {
        return lstGrade;
    }

    public void setLstGrade(List<ListValue> lstGrade) {
        this.lstGrade = lstGrade;
    }

    public String[] getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(String[] defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public List<MappingCot> getLstMapCot() {
        return lstMapCot;
    }

    public void setLstMapCot(List<MappingCot> lstMapCot) {
        this.lstMapCot = lstMapCot;
    }

    public List<DULIEU_VIEW> getLstDuliewView() {
        return lstDuliewView;
    }

    public void setLstDuliewView(List<DULIEU_VIEW> lstDuliewView) {
        this.lstDuliewView = lstDuliewView;
    }

    public boolean isDongbo_dl() {
        return dongbo_dl;
    }

    public void setDongbo_dl(boolean dongbo_dl) {
        this.dongbo_dl = dongbo_dl;
    }

}
