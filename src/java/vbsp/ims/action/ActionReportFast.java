/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoRptFastHstdct;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.report.fast.ModelRptFastEdit;
import vbsp.ims.report.fast.paraReportFast;

/**
 *
 * @author BAOANH
 */
public class ActionReportFast extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien private">
    //cho select module bao cao
    //id cua module bao cao
    private String module_id;
    private List<ListValue> lstModuleObj;
    //Phan nay cho id cac bao cao da luu
    //id cua bao cao da luu
    private String save_id;
    //mang cac tieu de bao cao da luu
    private List<ListValue> lstSaveReportObj;
    private List<paraReportFast> lstParaReportFastObj;
    private DaoRptFastHstdct daoFast = new DaoRptFastHstdct();
    private String message;
    // private InputStream fileInputStream;
    private String fileName;
    private String fileNamelocal;
    private String ma_pgd;
    private String filereport;
    private List<ListValue> lstModule_id;

    //Select tranfer column
    private List<ListValue> leftModuleColumnList;
    private List<ListValue> rightModuleColumnList;
    private List<String> leftColumnList;
    private List<String> rightColumnList;

    //Select tranfer date
    private List<ListValue> leftModuleDateList;
    private List<ListValue> rightModuleDateList;
    private List<String> leftDateList;
    private List<String> rightDateList;

    private String titlereport;
    private String addwhere;

    private List<String> lstViewReport;
    private List<ListValue> lstEditDelFastRpt;
    private List<ReportParam> reportParamsList;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CAC HAM XU LY">

    public String getModule_SaveReport() {
        try {

            //System.err.println("Vao ham getModule_SaveReport ------ " + module_id);
            lstModuleObj = new ArrayList<ListValue>();
            lstSaveReportObj = new ArrayList<ListValue>();
            //lay du lieu cua module
            setLstModuleObj(daoFast.getModuleReportFast());

            if (module_id != null) {
                setLstSaveReportObj(daoFast.getSaveReportFastFromModule(module_id));

            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " Loi khi goi ham getModule_SaveReport  -> " + e.getMessage());
        }
        System.gc();
        return SUCCESS;
    }

    public String createRptFast() {
        try {
            System.err.println("Vao ham createRptFast");
            lstModule_id = new ArrayList<ListValue>();
            setLstModule_id(daoFast.getModuleReportFast());
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createRptFast -> " + e.getMessage());
            System.err.println(e.getMessage());
            return ERROR;
        }

        return "populate";
    }

    public String execute() {
        //System.err.println("module id la " + save_id);
        try {
            if (save_id == null || save_id.length() < 5) {
                setMessage("Lỗi Bạn phải chọn mẫu báo cáo ");
                return ERROR;
            }
            reportParamsList = new ArrayList<ReportParam>();

            if (save_id != null || save_id.length() > 1) {
                //Lay ra session va lay ra user
                Map session = ActionContext.getContext().getSession();
                String strUserName = session.get("username").toString();

//                setLstParaReportFastObj(daoFast.getColumnParaReportFast(save_id, strUserName));
                reportParamsList = daoFast.getReportParmams(save_id, strUserName);
            }
            if (lstParaReportFastObj.size() == 0) {
                setMessage("Lỗi không load được tham số cho báo cáo nhanh");
                return ERROR;
            }
            System.gc();
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception execute _> " + e.getMessage());
            System.err.println("loi execute " + e.getMessage());
        }
        return SUCCESS;
    }

    public String genReportFast() {
        try {
            //Lay ra truy van du lieu dua vao save_id
            //String strQuery = daoreportfast.getQueryReportFastFromSaveID(save_id);
            //Lay ra duong dan root luu file
            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XLS
                    : context.getRealPath("/") + Define.M_REPORT_XLS;
            String strTimeFile = Long.toString(System.currentTimeMillis());

            String strFileSave = save_id + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length()) + ".xlsx";

            //System.err.println("Ten file " + fileName);
            Map<String, Object> prameters = ActionContext.getContext().getParameters();

            //Iterator iterator = prameters.keySet().iterator();
            Map<String, String> OutmapPara = new LinkedHashMap<String, String>();

//        String strNgaybc="";
            for (String parameter : prameters.keySet()) {
                String[] values = (String[]) prameters.get(parameter);

                //Do neu parameter kieu date
                //   nen minh can phai loai bo tham so nay di
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    OutmapPara.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
//                strNgaybc=new SimpleDateFormat("ddMMyyyy").format(sdf);
                } else {
                    if (parameter.toUpperCase().indexOf("_TUNGNV") > 0) {
                         //Lay ra ma PGD de gan vao ten file
                        ma_pgd = values[0].trim();
                        parameter = parameter.replace("_TUNGNV", "");
                    }
                    OutmapPara.put(parameter.substring(0, parameter.length() - 5), values[0] == null ? "" : values[0]);
                }
            }

            if (ma_pgd == null || ma_pgd.length() < 6) {
                ma_pgd = "000000";
            }
            File Checkpath = new File(strPathSave);

            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }
            filereport = ma_pgd.trim().substring(2, ma_pgd.length()) + "_" + strFileSave;
            // System.err.println("Ma pos day ---------"+ma_pgd);
            fileName = strPathSave + ma_pgd.trim().substring(2, ma_pgd.length()) + "_" + strFileSave;
//        System.err.println("Tao file nao fileName "+fileName);
            daoFast.ExportExcelFromQrySaveId(OutmapPara, save_id, fileName);

            File fileOut = new File(fileName);
            if (!fileOut.exists()) {
                System.err.println("Khong co file du lieu");
                setMessage("Lỗi Không tìm thấy file dữ liệu đã xuất ra");
                return ERROR;

            } else {
                fileNamelocal = fileName;
                //System.err.println(fileNamelocal+" da Tao file fileName "+fileNamelocal);
            }
            if (fileNamelocal.length() < 1 || fileNamelocal == null) {
                setMessage("Lỗi Không tìm thấy file dữ liệu đã xuất ra");
                return ERROR;
            }
            System.gc();
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception genReportFast _> " + e.getMessage());
            System.err.println("loi genReportFast " + e.getMessage());
            setMessage("Xuất file bị lỗi xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }

        return SUCCESS;
    }

    public String getColumnCreateRpt() {
        try {
            System.err.println("Vao ham execute module_id " + module_id);
            if (module_id == null || module_id.isEmpty() || module_id.equals("-1")) {
                setMessage("Bạn phải chọn module tạo báo cáo");
                return "error";
            }
            leftModuleColumnList = new ArrayList<ListValue>();
            rightModuleColumnList = new ArrayList<ListValue>();
            leftModuleDateList = new ArrayList<ListValue>();
            rightModuleDateList = new ArrayList<ListValue>();

            setLeftModuleColumnList(daoFast.getColumnReportFastArrObj(module_id));
            setLeftModuleDateList(daoFast.getColumnDateReportFast(module_id));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception getColumnCreateRpt _> " + e.getMessage());
            System.err.println("loi getColumnCreateRpt " + e.getMessage());
        }
        return SUCCESS;
    }

    public String ViewReportFast() {
        try {
//            HttpServletRequest request = ServletActionContext.getRequest();
//            
//            titlereport=request.getParameter("titlereport");
//            
//            Map<String, String[]> mapPara = request.getParameterMap();
//            String[] arrrightColumnList= mapPara.get("rightColumnList");
//            
//            String[] arrrightDateList= mapPara.get("rightDateList");
//            
//            rightColumnList=new ArrayList<String>(Arrays.asList(arrrightColumnList));
//            
//            rightDateList=new ArrayList<String>(Arrays.asList(arrrightDateList));

            if (titlereport == null || titlereport.length() < 1) {
                setMessage("Bạn chưa điền tiêu đề cho báo cáo");
                return "error";
            }
            if (rightColumnList == null || rightColumnList.size() == 0) {
                setMessage("Bạn chưa chọn cột dữ liệu cho báo cáo ?");
                return "error";
            }
            if (rightDateList == null || rightDateList.size() == 0) {
                setMessage("Bạn chưa chọn tham số ngày lấy báo cáo ?");
                return "error";
            }
            String sCheck = daoFast.getCheckSelectQuery(rightColumnList, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " có quan hệ với các bảng trên");
                return "error";
            }
            sCheck = daoFast.getCheckSelectQueryAddWhere(rightColumnList, rightDateList, addwhere, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " Cho phần điều kiện thêm ngoài");
                return "error";
            }
            setLstViewReport(daoFast.viewReport(rightColumnList, module_id, addwhere, "31-mar-2014", "003401"));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception ViewReportFast _> " + e.getMessage());
            System.err.println("loi ViewReportFast " + e.getMessage());
            setMessage("Lỗi khi xem báo cáo "+e.getMessage());
            return "error";
        }
        return SUCCESS;
    }

    public String save_bc_nhanh() {
        try {
            //System.err.println("Vao function save_bc_nhanh()");

            if (titlereport == null || titlereport.length() < 1) {
                setMessage("Bạn chưa điền tiêu đề cho báo cáo");
                return "error";
            }
            if (rightColumnList == null || rightColumnList.size() == 0) {
                setMessage("Bạn chưa chọn cột dữ liệu cho báo cáo ?");
                return "error";
            }
            if (rightDateList == null || rightDateList.size() == 0) {
                setMessage("Bạn chưa chọn tham số ngày lấy báo cáo ?");
                return "error";
            }
            HttpServletRequest request = ServletActionContext.getRequest();
//        Map<String, String[]> prameters = request.getParameterMap();
            HttpSession session = request.getSession();
            String strUserName = session.getAttribute("username").toString();

            //them phan kiem tra cot du lieu cho bao cao
            String sCheck = daoFast.getCheckSelectQuery(rightColumnList, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " có quan hệ với các bảng trên");
                return "error";
            }
             sCheck = daoFast.getCheckSelectQueryAddWhere(rightColumnList, rightDateList, addwhere, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " Cho phần điều kiện thêm ngoài");
                return "error";
            }
            //System.err.println("User name nhe: "+strUserName);
            //session.setAttribute("username", name);
            //Lây user truyền vào chưa lấy được
            if (!daoFast.saveReportFastArr(module_id, rightColumnList, rightDateList, titlereport, addwhere, strUserName)) {
                setMessage("Bạn chưa lưu được báo cáo ");
                return ERROR;
            } else {
                setMessage("Bạn đã lưu báo cáo thành công !");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception save_bc_nhanh _> " + e.getMessage());
            System.err.println("loi save_bc_nhanh " + e.getMessage());
        }

        return SUCCESS;
    }

    public String loadAllFastRpt() {
        try {
             Map session = ActionContext.getContext().getSession();

            if (session == null || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tải được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            
            setLstEditDelFastRpt(daoFast.getAllFastRpt(sUserName));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception loadAllFastRpt _> " + e.getMessage());
            System.err.println("loi loadAllFastRpt " + e.getMessage());
        }
        return SUCCESS;
    }

    public String DeleteRptFast() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                setMessage("Tạo http request bi loi");
                return ERROR;
            }
            HttpSession session = request.getSession();
            String sUserName = session.getAttribute("username").toString();

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Bạn phải logout ra khỏi hệ thống sau đó login vào mới thực hiện được chức năng này");
                return ERROR;
            }
            if (save_id == null || save_id.isEmpty()) {
                setMessage("Không lấy ra được id báo cáo xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
            String sUserCreateRpt = daoFast.getUserCreateReport(save_id);
            if (!sUserName.toUpperCase().contains("ADMIN")) {
                if (!sUserName.toUpperCase().equals(sUserCreateRpt.toUpperCase())) {
                    setMessage("Bạn không thể xóa được báo cáo, do bạn không phải là người tạo báo cáo. Người tạo báo cáo là " + sUserCreateRpt);
                    return ERROR;
                }
            }
            if (!daoFast.deleteRptFast(save_id, sUserName)) {
                setMessage("Bạn chưa xóa được báo cáo xin liên hệ với quản trị để được hỗ trợ !");
                return ERROR;
            } else {
                setMessage("Bạn đã xóa báo cáo thành công ");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception DeleteRptFast _> " + e.getMessage());
            System.err.println("loi DeleteRptFast " + e.getMessage());
        }
        return SUCCESS;
    }

    public String editRptFast() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                setMessage("Tạo http request bi loi");
                return ERROR;
            }
            HttpSession session = request.getSession();
            String sUserName = session.getAttribute("username").toString();

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Bạn phải logout ra khỏi hệ thống sau đó login vào mới thực hiện được chức năng này");
                return ERROR;
            }
            if (save_id == null || save_id.isEmpty()) {
                setMessage("Không lấy ra được id báo cáo xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
            String sUserCreateRpt = daoFast.getUserCreateReport(save_id);
            if (!sUserName.toUpperCase().contains("ADMIN")) {
                if (!sUserName.toUpperCase().equals(sUserCreateRpt.toUpperCase())) {
                    setMessage("Bạn không thể sửa được báo cáo này, do bạn không phải là người tạo báo cáo. Người tạo báo cáo là " + sUserCreateRpt);
                    return ERROR;
                }
            }

            setLstModule_id(daoFast.getModuleReportFast());
            ModelRptFastEdit objEdit = daoFast.getRptFastEdit(save_id);
            setTitlereport(objEdit.getTitle());
//             setSave_id(objEdit.getSave_id());
            setModule_id(objEdit.getModule_id());
            setAddwhere(objEdit.getAdd_where());
            setLeftModuleColumnList(objEdit.getLeftModuleColumnList());
            setRightModuleColumnList(objEdit.getRightModuleColumnList());
            setLeftModuleDateList(objEdit.getLeftModuleDateList());
            setRightModuleDateList(objEdit.getRightModuleDateList());

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception editRptFast _> " + e.getMessage());
            System.err.println("loi editRptFast " + e.getMessage());
        }
        return SUCCESS;
    }

    public String SaveEditReportFast() {
        try {
            //System.err.println("Vao function save_bc_nhanh()");

            if (titlereport == null || titlereport.length() < 1) {
                setMessage("Bạn chưa điền tiêu đề cho báo cáo");
                return "error";
            }
            if (rightColumnList == null || rightColumnList.size() == 0) {
                setMessage("Bạn chưa chọn cột dữ liệu cho báo cáo ?");
                return "error";
            }
            if (rightDateList == null || rightDateList.size() == 0) {
                setMessage("Bạn chưa chọn tham số ngày lấy báo cáo ?");
                return "error";
            }
            HttpServletRequest request = ServletActionContext.getRequest();
//        Map<String, String[]> prameters = request.getParameterMap();
            HttpSession session = request.getSession();
            String strUserName = session.getAttribute("username").toString();

            //them phan kiem tra cot du lieu cho bao cao
            String sCheck = daoFast.getCheckSelectQuery(rightColumnList, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " có quan hệ với các bảng trên");
                return "error";
            }
             sCheck = daoFast.getCheckSelectQueryAddWhere(rightColumnList, rightDateList, addwhere, module_id);
            if (!sCheck.equals("OK")) {
                setMessage("Bạn phải chọn thêm cột dữ liệu của bảng " + sCheck + " Cho phần điều kiện thêm ngoài");
                return "error";
            }
            //System.err.println("User name nhe: "+strUserName);
            //session.setAttribute("username", name);
            //Lây user truyền vào chưa lấy được
            if (!daoFast.saveEditReportFastArr(save_id, module_id, rightColumnList, rightDateList, titlereport, addwhere, strUserName)) {
                setMessage("Bạn chưa lưu được báo cáo ");
                return ERROR;
            } else {
                setMessage("Bạn đã lưu báo cáo thành công !");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception SaveEditReportFast _> " + e.getMessage());
            System.err.println("loi SaveEditReportFast " + e.getMessage());
        }

        return SUCCESS;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get, set">
    public List<ReportParam> getReportParamsList() {
        return reportParamsList;
    }

    public void setReportParamsList(List<ReportParam> reportParamsList) {
        this.reportParamsList = reportParamsList;
    }

    public List<ListValue> getLstModule_id() {
        return lstModule_id;
    }

    public void setLstModule_id(List<ListValue> lstModule_id) {
        this.lstModule_id = lstModule_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public List<ListValue> getLstModuleObj() {
        return lstModuleObj;
    }

    public void setLstModuleObj(List<ListValue> lstModuleObj) {
        this.lstModuleObj = lstModuleObj;
    }

    public String getSave_id() {
        return save_id;
    }

    public void setSave_id(String save_id) {
        this.save_id = save_id;
    }

    public List<ListValue> getLstSaveReportObj() {
        return lstSaveReportObj;
    }

    public void setLstSaveReportObj(List<ListValue> lstSaveReportObj) {
        this.lstSaveReportObj = lstSaveReportObj;
    }

    public List<paraReportFast> getLstParaReportFastObj() {
        return lstParaReportFastObj;
    }

    public void setLstParaReportFastObj(List<paraReportFast> lstParaReportFastObj) {
        this.lstParaReportFastObj = lstParaReportFastObj;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getMa_pgd() {
        return ma_pgd;
    }

    public void setMa_pgd(String ma_pgd) {
        this.ma_pgd = ma_pgd;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public List<ListValue> getLeftModuleColumnList() {
        return leftModuleColumnList;
    }

    public void setLeftModuleColumnList(List<ListValue> leftModuleColumnList) {
        this.leftModuleColumnList = leftModuleColumnList;
    }

    public List<ListValue> getRightModuleColumnList() {
        return rightModuleColumnList;
    }

    public void setRightModuleColumnList(List<ListValue> rightModuleColumnList) {
        this.rightModuleColumnList = rightModuleColumnList;
    }

    public List<String> getLeftColumnList() {
        return leftColumnList;
    }

    public void setLeftColumnList(List<String> leftColumnList) {
        this.leftColumnList = leftColumnList;
    }

    public List<String> getRightColumnList() {
        return rightColumnList;
    }

    public void setRightColumnList(List<String> rightColumnList) {
        this.rightColumnList = rightColumnList;
    }

    public List<ListValue> getLeftModuleDateList() {
        return leftModuleDateList;
    }

    public void setLeftModuleDateList(List<ListValue> leftModuleDateList) {
        this.leftModuleDateList = leftModuleDateList;
    }

    public List<ListValue> getRightModuleDateList() {
        return rightModuleDateList;
    }

    public void setRightModuleDateList(List<ListValue> rightModuleDateList) {
        this.rightModuleDateList = rightModuleDateList;
    }

    public List<String> getLeftDateList() {
        return leftDateList;
    }

    public void setLeftDateList(List<String> leftDateList) {
        this.leftDateList = leftDateList;
    }

    public List<String> getRightDateList() {
        return rightDateList;
    }

    public void setRightDateList(List<String> rightDateList) {
        this.rightDateList = rightDateList;
    }

    public String getTitlereport() {
        return titlereport;
    }

    public void setTitlereport(String titlereport) {
        this.titlereport = titlereport;
    }

    public String getAddwhere() {
        return addwhere;
    }

    public void setAddwhere(String addwhere) {
        this.addwhere = addwhere;
    }

    public List<String> getLstViewReport() {
        return lstViewReport;
    }

    public void setLstViewReport(List<String> lstViewReport) {
        this.lstViewReport = lstViewReport;
    }

    public List<ListValue> getLstEditDelFastRpt() {
        return lstEditDelFastRpt;
    }

    public void setLstEditDelFastRpt(List<ListValue> lstEditDelFastRpt) {
        this.lstEditDelFastRpt = lstEditDelFastRpt;
    }
//</editor-fold>

}
