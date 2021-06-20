/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoExportHstdct;
import vbsp.ims.dao.DaoRptFormula;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelFormula;
import vbsp.ims.model.RptFormula;
import vbsp.ims.model.ValueFormula;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author LION
 */
public class FormulaRptAction extends ActionSupport {
//<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">

    private List<ListValue> lstBranchObj = new ArrayList<ListValue>();
    private List<ListValue> lstSourceData = new ArrayList<ListValue>();
    private List<ListValue> lstUntilData = new ArrayList<ListValue>();
    private List<ListValue> lstReportType = new ArrayList<ListValue>();
    private List<ListValue> lstReportTimes = new ArrayList<ListValue>();
    private List<ListValue> lstRowColumn = new ArrayList<ListValue>();
    private List<ListValue> lstDrawTable = new ArrayList<ListValue>();
    private List<DrawRowAndColumn> lstRowTable = new ArrayList<DrawRowAndColumn>();
    private List<DrawRowAndColumn> lstColumnTable = new ArrayList<DrawRowAndColumn>();
    private List<ListValue> lstObjFormula = new ArrayList<ListValue>();
    private List<ListValue> lstObjFormulaExp = new ArrayList<ListValue>();
    private TreeNode nodes_pos = new TreeNode();

    private String message;//Tra ra message loi cho nguoi su dung lstObjFormula
    private String source_data; //Nguồn số liệu tạo báo cáo lstRowColumn
    private String until_data;//Đơn vị tính
    private String check_branch;//Mã chi nhánh (main pos) khi chon cac chi nhánh)
    private String report_type;//Loại báo cáo
    private String report_times;//Ky bao cao
    private String title_name;//Tiêu đề cho báo cáo
    private String number_row;//Số dòng cần tạo báo cáo
    private String number_column;//Số cột báo cáo
    private String row_column;//Radio button cho chi nhanh chi tieu
    private String fileNamelocal;
    private String filereport;
    private String pos_cd;
    private String export_date;

    private String save_id;

    //set default gia tri
    private String defaultSourceData = "";
    private String defaultUntilData = "";
    private String defaultReportType = "";
    private String defaultReportTimes = "";

    private String defaultDisplayRowColumn = "";
    private HttpServletRequest servletRequest;
    private String result;
    private List<ModelFormula> data;
    private List<ModelFormula> objReturn = new ArrayList<ModelFormula>();

    //Hien cho phan quyen cap hien thi bao cao
    private List<ListValue> lstGrade = new ArrayList<ListValue>();
    private String[] defaultGrade;
    private List<String> rptGrade = new ArrayList<>();
//</editor-fold>    

    public String execute() throws Exception {
        //lay ra session
        Map session = ActionContext.getContext().getSession();
        //lay ra user
        String strUserName = session.get("username").toString();
        DaoRptFormula daoFormula = new DaoRptFormula();
        //lay ra phan danh muc da cau hinh
        HashMap<Integer, List<ListValue>> hm = daoFormula.getDmKhac();
        lstBranchObj = daoFormula.getLoadAllBranch(strUserName);
        //khoi tao cho phan danh muc
        lstSourceData.addAll(hm.get(31));
        lstUntilData.addAll(hm.get(32));
        lstReportType.addAll(hm.get(33));
        lstReportTimes.addAll(hm.get(34));
        lstRowColumn.addAll(hm.get(35));

        lstGrade.add(new ListValue("1", "Ngân hàng"));
        lstGrade.add(new ListValue("2", "Chi nhánh"));
        lstGrade.add(new ListValue("3", "Toàn quốc"));

        setDefaultGrade(new String[]{"1", "2", "3"});
        return SUCCESS;
    }

    public String exportXlsxFileFormula() {
        try {
            if (save_id == null || save_id.isEmpty()) {
                setMessage("Bạn chưa lấy được ID báo cáo. Xin chọn báo cáo sau đó nhấn tiếp tục");
                return ERROR;
            }
            //lay ra session
            Map session = ActionContext.getContext().getSession();

            if (session == null) {
                setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            //lay ra context cua web sau do lay ra duong dan thu muc goc
            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XLS
                    : context.getRealPath("/") + Define.M_REPORT_XLS;
            DaoRptFormula daoExp = new DaoRptFormula();
            String strFullFile = "";
            //lay ra phan header khi luu bao cao
            RptFormula rptObj = daoExp.getLoadHeaderFormula(sUserName, save_id);
            //neu bao cao la loai 01 thi moi can them pos
            if (rptObj.getReport_type().equals("01")) {
//        System.err.println("Duong dan " + strPathSave);
                if (pos_cd == null || pos_cd.isEmpty()) {
                    setMessage("Bạn chưa chọn pos cần tạo số liệu. Xin chọn pos cần tạo số liệu");
                    return ERROR;
                }
                pos_cd = pos_cd.replace(" ", "");
                pos_cd = pos_cd.replace("000000", "");
                pos_cd = pos_cd.replace(",,", "");
                pos_cd = pos_cd.replace(",", "#");
            }
            File Checkpath = new File(strPathSave);

            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }

            //ArrayList<String> ArrlstPosCd = new ArrayList<String>(java.util.Arrays.asList(pos_cd.split(",")));
            String strDateExport = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(export_date));
//        System.err.println("Ngay khi da convert " + strDateExport.toString());

            //lay ra phan body (bang du lieu va cong thuc da ve len bao cao)
            HashMap<Integer, List<String>> hmDataBody = daoExp.getLoadBodyCaculatorHashMap(save_id);
            //Load ra cong thuc cua bao cao va pos voi loai bao cao la dang 2
            //loai bo phan tieu de hang, tieu de cot chi lay ra cong thuc duy nhat
            //cho cac loai bao cao
            HashMap<String, List<String>> hmLstFormulaByPos = DefineFun.standardListFormula(rptObj, hmDataBody);
            String sPos_cd = "";
            //Lay ra object cac cong thuc voi dieu kien
            List<ValueFormula> lstObjFormula = new ArrayList<ValueFormula>();
            for (String sKey : hmLstFormulaByPos.keySet()) {
                sPos_cd = sKey;
                List<String> lstFormula = hmLstFormulaByPos.get(sKey);
                //Cong thuc can thiet de dua vao tinh toan
                lstObjFormula = DefineFun.splitFormulaByFormula(save_id, lstFormula, 1);

            }
            if (sPos_cd.equals("ARR_POS_CD")) {
                sPos_cd = pos_cd;
            }
            //Du lieu da duoc tinh toan ra
            HashMap<String, String> hmDataFormula = daoExp.CaculatorRptFormula(save_id, lstObjFormula, sUserName, sPos_cd, strDateExport);
            //tu cong thuc load len khi luu chuan hoa them pos vao do
            HashMap<Integer, List<String>> hmDataxls = new HashMap<Integer, List<String>>();

            if (rptObj.getReport_type().equals("02") && rptObj.getRow_column().equals("1") && rptObj.getPos_cd().equals("000100")) {
                hmDataxls = daoExp.getLoadBodyCaculatorHashMap_tmp(save_id, rptObj);
            } else {
                hmDataxls = DefineFun.standardListFormulaAddPos(rptObj, hmDataBody);
            }
            //HashMap<Integer, List<String>> hmDataxls = DefineFun.standardListFormulaAddPos(rptObj, hmDataBody);
            //replace cong thuc bang du lieu da luu
            for (Integer i : hmDataxls.keySet()) {
                List<String> lstData = hmDataxls.get(i);
                for (int j = 0; j < lstData.size(); j++) {
                    String sData = hmDataFormula.get(lstData.get(j));
                    if (sData != null) {
                        lstData.remove(j);
                        lstData.add(j, sData);
                    }
                    if (lstData.get(j).indexOf("#") > 0) {
                        String sRemovePos = lstData.get(j);
                        int pos = sRemovePos.indexOf("#");
                        lstData.remove(j);
                        lstData.add(j, sRemovePos.substring(pos + 1, sRemovePos.length()));
                    }
//                    System.err.println("Data -> " + lstData.get(j));
                }
            }

            //Doan nay de them ma chi nhanh len truoc ten chi nhanh trong bao cao hieu thi 
            //theo chi tieu
            List<String> lstMacn = new ArrayList<String>();
            if (rptObj.getReport_type().equals("02") && rptObj.getRow_column().equals("2") && rptObj.getPos_cd().equals("000100")) {
                lstMacn.add("Mã Chi nhánh");
                List<String> lstPoscd = DefineFun.SplitStringToArrayList(sPos_cd, "#");
                lstMacn.addAll(lstPoscd);
                HashMap<Integer, List<String>> hmDataxls_Tmp = new HashMap<Integer, List<String>>();
                hmDataxls_Tmp.put(0, lstMacn);
                for (Integer i : hmDataxls.keySet()) {
                    hmDataxls_Tmp.put(i + 1, hmDataxls.get(i));
                }

                hmDataxls.clear();
                hmDataxls.putAll(hmDataxls_Tmp);
                //new ArrayList<String>(Arrays.asList(sPos_cd.split("#")));
            }
//            for (String key:hmDataFormula.keySet())
//                System.err.println("Khoa -> "+key+" Du lieu -> "+hmDataFormula.get(key));
            //Zip file sau do cho nguoi dung tai ve
            String strDateExportFile;

            strDateExportFile = new SimpleDateFormat("ddMMyyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(export_date));
            Random rand = new Random();
            String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
            filereport = save_id + "_" + sUserName + "_" + strDateExportFile + "_" + Integer.toString(rand.nextInt(10000)) + ".xlsx";
            fileNamelocal = strPathSave + filereport;

            File fileOut = new File(fileNamelocal);
            if (fileOut.exists()) {
                System.err.println("File nay da co nen tao file moi " + fileNamelocal);
                //setMessage("Lỗi tìm thấy file dữ liệu đã xuất ra");
                //continue;
                int random = (int) (Math.random() * 50000 + 1);
                // filereport = "HSTDCT_" + strUserName + "_" + strCurrDate + "_" + strDateExportFile + "_" + Integer.toString(random) + ".zip";
                filereport = save_id + "_" + sUserName + "_" + strDateExportFile + "_" + Integer.toString(rand.nextInt(10000)) + ".xlsx";
                fileNamelocal = strPathSave + filereport;
            }
            //lay ra phan tieu de bao cao va don vi tinh
            List<String> lstTitleUntil = daoExp.getTitleUntil(save_id);
            ExportExcelFile expXls = new ExportExcelFile();
            //ghi ra file excel
            expXls.ExportFileExcelFormula(hmDataxls, lstTitleUntil.get(0), lstTitleUntil.get(1), fileNamelocal);
            // FileZip.ZipFileFromArray(ArrlstFile, fileNamelocal);
        } catch (Exception e) {
            CoreLogger.error("Loi khi load cac bao cao exportXlsxFileFormula " + e.getMessage());
            System.err.println("exportXlsxFileFormula" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String DrawReportFormula() throws Exception {
//        System.err.println(row_column);
        //kiểm tra nguồn số liệu xem đã được chọn chưa
        if (source_data.equals("-1") || source_data == null || source_data.isEmpty()) {
            setMessage("Bạn phải chọn nguồn số liệu ");
            return ERROR;
        }
        //Kiểm tra kỳ báo cáo xem đã được chọn chưa
        if (report_times.equals("-1") || report_times == null || report_times.isEmpty()) {
            setMessage("Bạn phải chọn kỳ báo cáo ");
            return ERROR;
        }
        //Kiểm tra checkbox các chi nhánh xem đã được chọn chưa
        if (check_branch == null || check_branch.isEmpty()) {
            setMessage("Bạn phải chọn chi nhánh tạo báo cáo ");
            return ERROR;
        }
        //Kiểm tra tiêu đề báo cáo xem đã điền chưa
        if (title_name == null || title_name.isEmpty()) {
            setMessage("Bạn phải điền tiêu đề báo cáo ");
            return ERROR;
        }
        //Nếu báo cáo là loại chi tiết thì phải kiểm tra cả số dòng lẫn số cột
        if (report_type.equals("01")) {
            //Kiểm tra xem người sử dụng đã điền số dòng chưa 
            if (number_row == null || number_row.equals("0") || number_row.isEmpty()) {
                setMessage("Bạn phải điền số dòng của báo cáo ");
                return ERROR;
            }
            //kiểm tra xem đã điền số cột chưa
            if (number_column == null || number_column.equals("0") || number_column.isEmpty()) {
                setMessage("Bạn phải điền số cột của báo cáo ");
                return ERROR;
            }
        } else {//truong hop neu báo cáo là tổng hợp
            //Nếu chọn là báo cáo theo chỉ tiêu (Hiển thị theo dòng/cột)
            //thì phải điền số dòng
            if (row_column.equals("2") && (number_row == null || number_row.equals("0") || number_row.isEmpty())) {
                setMessage("Bạn phải điền số dòng của báo cáo ");
                return ERROR;
            }
            //Nếu chọn là báo cáo theo chi nhánh (Hiển thị theo dòng/cột)
            //thì phải điền số cột
            if (row_column.equals("1") && (number_column == null || number_column.equals("0") || number_column.isEmpty())) {
                setMessage("Bạn phải điền số cột của báo cáo ");
                return ERROR;
            }
        }
        //Kiểm tra xem số dòng số cột được điển vào có đùng là số hay không
        if (number_row != null && !DefineFun.isNumeric(number_row)) {
            setMessage("Ô dữ liệu số dòng bạn phải điền là số không được điền ký tự");
            return ERROR;
        }
        if (number_column != null && !DefineFun.isNumeric(number_column)) {
            setMessage("Ô dữ liệu số cột bạn phải điền là số không được điền ký tự");
            return ERROR;
        }

        if (rptGrade == null || rptGrade.isEmpty()) {
            setMessage("Bạn phải chọn cấp xuất báo cáo ");
            return ERROR;
        }

        //Lấy ra chuỗi pos là 1 mảng các pos dạng "pos1","pos2",...
        String strArr_pos_cd = check_branch.replace(" ", "");
        //Neu ky tu dau tien la , thi loai bo
        if (strArr_pos_cd.startsWith(",")) {
            strArr_pos_cd = strArr_pos_cd.substring(1, strArr_pos_cd.length());

        }
        //neu ky tu cuoi cung la , thi loai bo
        if (strArr_pos_cd.endsWith(",")) {
            strArr_pos_cd = strArr_pos_cd.substring(0, strArr_pos_cd.length() - 1);
        }

        strArr_pos_cd = strArr_pos_cd.replaceAll(",", "\",\"");
        strArr_pos_cd = "\"" + strArr_pos_cd + "\"";

        //<editor-fold defaultstate="collapsed" desc="Ve thu table">
//        for (int row = 0; row <= nRow; row++) {
//            String sKey = "";
//            String sDesc = "";
//            for (int col = 0; col <= nColumn; col++) {
//                if (row == 0 && col == 0) {
//                    sKey = Integer.toString(row) + Integer.toString(col);
//                    sDesc = "Tiêu đề cột/Chỉ tiêu";
//                    //sdata=sdata+"<td> <h3>Tiêu đề cột/Chỉ tiêu </h3></td>";
//                } else if (row == 0) {
//                    sKey = Integer.toString(row) + Integer.toString(col);
//                    sDesc = "column_name";
//                    //sdata=sdata+"<td><h3><input type='text'  name=\"column_name\" value='tiêu đề cột "+col+"' size=\"20\"/></h3> </td>";
//                } else if (col == 0) {
//                    sKey = Integer.toString(row) + Integer.toString(col);
//                    sDesc = "Chi nhánh";
//                    //sdata=sdata+"<td> Chi nhánh "+row+" </td>";
//                } else {
//                    sKey = Integer.toString(row) + Integer.toString(col);
//                    sDesc = "column_data";
//                    //sdata=sdata+"<td> <input type='text'  name=\"column_data\" size=\"20\"/> </td>";
//                }
//            }
//            
//            lstDrawTable.add(new ListValue(sKey, sDesc));
//        }
//</editor-fold>
        //Nếu báo cáo là chi tiết thì 
        System.err.println("Gia tri pos khi xu ly " + strArr_pos_cd);
        if (report_type.equals("01")) {
            //Vẽ lên số dòng và số cột như người sử dụng đã điền
            int nRow = Integer.parseInt(number_row);
            int nColumn = Integer.parseInt(number_column);
            for (int row = 0; row <= nRow; row++) {
                lstRowTable.add(new DrawRowAndColumn(Integer.toString(row), "Dòng " + row));
            }

            for (int col = 0; col <= nColumn; col++) {
                lstColumnTable.add(new DrawRowAndColumn("Cột " + col, Integer.toString(col)));
            }
        } else {//nếu tao báo là tổng hợp
            //Lấy ra danh sách pos đã chọn
            List<ListValue> lstPoscd = new DaoRptFormula().getBranchReport(strArr_pos_cd);
            if (row_column.equals("1")) {//trường hợp chọn số dòng là chi nhánh
                int nColumn = Integer.parseInt(number_column);
                //Vẽ lên số cột người sử dụng đã điền
                for (int col = 0; col <= nColumn; col++) {
                    lstColumnTable.add(new DrawRowAndColumn("Cột " + col, Integer.toString(col)));
                }
                lstRowTable.add(new DrawRowAndColumn("0", "Dòng 0"));
                //tiêu đề dòng để là tên chi nhánh
                for (int row = 0; row < lstPoscd.size(); row++) {
                    ListValue lstValue = lstPoscd.get(row);
                    System.err.println("Trong for row " + lstValue.getsDesc());
                    lstRowTable.add(new DrawRowAndColumn(Integer.toString(row + 1), lstValue.getsDesc()));
//                    System.err.println(lstValue.getsDesc());
                }
            } else {//trường hợp chọn số dòng là chỉ tiêu
                int nRow = Integer.parseInt(number_row);
                //lấy số dòng là số người sử dụng chon
                for (int row = 0; row <= nRow; row++) {
                    lstRowTable.add(new DrawRowAndColumn(Integer.toString(row), "Dòng " + row));
                }
                lstColumnTable.add(new DrawRowAndColumn("Dòng 0", "0"));
                //số cột là số chi nhánh
                for (int col = 0; col < lstPoscd.size(); col++) {
                    ListValue lstValue = lstPoscd.get(col);
                    System.err.println("Trong for col " + lstValue.getsDesc());
                    lstColumnTable.add(new DrawRowAndColumn(lstValue.getsDesc(), Integer.toString(col + 1)));
                }
            }
        }
        return SUCCESS;
    }

    public String checkFormula() throws Exception {
        //try {
        //System.err.println("Vao ham moi roi nhe data "+data.toString());
        DaoRptFormula daoFormula = new DaoRptFormula();
        HashMap<String, Integer> hmMapColumn = daoFormula.getMappingColumn();
        result = "";
        if (data == null) {
            setMessage("Không thể load được tham số từ bảng để kiểm tra công thức");
            return ERROR;
        }
        int nFalse = 0;
        for (ModelFormula model : data) {
//                System.err.println(model.getsData());
//                if(model.getsData().equals("TK913_16%&DCNO&+TK913_16%&DCNO&+TK919_16%&DCNO&"))
//                    System.err.println("thu xem nhe");
            if (!DefineFun.isCheckFormula(model.getsData(), hmMapColumn) && !model.getsData().isEmpty()) {
                result += model.getsData() + ",";
                //System.err.println("tra ra -> "+result+" Duyet -> "+model.getsData());
                model.setsColor("red");
                nFalse++;
                //objReturn.add(model);
            } else {
                model.setsColor("white");
            }
            objReturn.add(model);
            //System.err.println("getsColor ->"+model.getsColor()+" getName ->"+model.getsName()+" getsData -> "+model.getsData() +" getnID -> "+model.getnID());
        }
//        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Loi trong ham checkFormula " + e.getMessage());
//            System.err.println(e.getMessage());
//        }
        return SUCCESS;
    }

    public String SavaRptFormula() {
        Map session = ActionContext.getContext().getSession();
        String strUserName = session.get("username").toString();
        if (session == null) {
            setMessage("Bạn phải logout ra và login lai thì mới xóa được báo cáo!");
            return ERROR;
        }

        DaoRptFormula daoSaveFormula = new DaoRptFormula();
        if (save_id != null) {
            String sUserCreateReport = daoSaveFormula.getUserCreateReport(save_id);
            if (!strUserName.toUpperCase().contains("ADMIN")) {
                if (!sUserCreateReport.toLowerCase().equals(strUserName.toLowerCase())) {
                    setMessage("Bạn không có quyền sửa báo cáo này, Do bạn không phải là người tạo báo cáo này! Người tạo là " + sUserCreateReport);
                    return ERROR;
                }
            }
        }

        List<ListValue> ArrBodyData = new ArrayList<ListValue>();
        if (number_column == null || number_column.isEmpty()) {
            number_column = "0";
        }
        if (number_row == null || number_row.isEmpty()) {
            number_row = "0";
        }
        //System.err.println("column_data "+column_data);
        HttpServletRequest request = ServletActionContext.getRequest();
        LinkedHashMap<String, String> paramHashMap = new LinkedHashMap<String, String>();
        Map<String, String[]> prameters = request.getParameterMap();
        for (String key : prameters.keySet()) {
            String[] values = prameters.get(key);
            if ((key.toUpperCase().equals("title_column_name".toUpperCase())
                    || key.toUpperCase().equals("title_row_name".toUpperCase())
                    || key.toUpperCase().indexOf("row_data".toUpperCase()) >= 0)
                    && values.length >= 1) {
                //Neu la tieu de cot va tieu de dong trong do co ky tu "," thi se replace ve ky tu **
                if (key.toUpperCase().equals("title_column_name".toUpperCase())
                        || key.toUpperCase().equals("title_row_name".toUpperCase())) {
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].replace(",", "**");
                    }
                }
                paramHashMap.put(key, convertObjectArrayToString(values));
                ArrBodyData.add(new ListValue(key, convertObjectArrayToString(values)));
            }
            //System.err.println("key " + key + " gia tri " + convertObjectArrayToString(values));
        }

//         System.err.println(convertHashMapToString(paramHashMap));
        String sGradeReport = "";
        for (String sRptGrade : rptGrade) {
            sGradeReport += "#" + sRptGrade;
        }
        sGradeReport += "#";

        int nNumber_Row = Integer.parseInt(number_row);
        int nNumber_Column = Integer.parseInt(number_column);
        try {
            boolean bSuccess = daoSaveFormula.saveRptFormula(save_id, title_name, strUserName, source_data, until_data,
                    report_type, report_times, nNumber_Row, nNumber_Column, row_column, check_branch, ArrBodyData, sGradeReport);
            if (bSuccess) {
                setMessage("Bạn đã lưu đữ liệu thành công");
            } else {
                setMessage("Lỗi bạn chưa thể update được dữ liệu do có trường dữ liệu bị null");
            }
        } catch (Exception ex) {
            Logger.getLogger(FormulaRptAction.class.getName()).log(Level.SEVERE, null, ex);
            setMessage("Lỗi không lưu được dữ liệu bạn xem lại");
            System.err.println(ex.getMessage());
        }

        return SUCCESS;
    }

    public String EditDelFormulaAction() throws Exception {
        Map session = ActionContext.getContext().getSession();
            String Grade = session.get("reportGrade").toString();
            if (session == null) {
                setMessage("Bạn phải logout ra và login lai thì mới xóa được báo cáo!");
                return ERROR;
            }
//        System.err.println("Vao ham load cac bao cao da luu ");
        lstObjFormula = new DaoRptFormula().getLoadAllSaveFormula("");
        return SUCCESS;
    }

    public String loadReportExp() {
        try {
            Map session = ActionContext.getContext().getSession();
            String Grade = session.get("reportGrade").toString();
            if (session == null) {
                setMessage("Bạn phải logout ra và login lai thì mới xóa được báo cáo!");
                return ERROR;
            }
//            System.err.println("Vao ham load cac bao cao da luu ");
            lstObjFormulaExp = new DaoRptFormula().getLoadAllSaveFormula(Grade);
        } catch (Exception e) {
            CoreLogger.error("Loi khi load cac bao cao loadReportExp " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadParameterPosExp() {
        try {
//            System.err.println(save_id);
            if (save_id == null || save_id.isEmpty() || save_id.equals("-1")) {
                setMessage("Bạn phải chọn báo cáo trước khi load tham số. Xin chọn báo cáo");
                return ERROR;
            }
            HashMap<String, ArrayList<String>> hmPos = new HashMap<String, ArrayList<String>>();
            //tao ra bang map luu tru bang du lieu load len
            //HashMap<String, String> hmModuleTable = new HashMap<String, String>();
            lstObjFormulaExp = new ArrayList<ListValue>();

            //Lay ra user name da luu trong session
            Map session = ActionContext.getContext().getSession();
            String strUserName = session.get("username").toString();
            //String strUserName = "M2721";
            DaoRptFormula exporthstdct = new DaoRptFormula();
            hmPos = exporthstdct.getPosExportFormula(strUserName);
            //hmModuleTable = exporthstdct.getModuleExportHstdct();

            //Add gia tri bang len select
//            for (String key : hmModuleTable.keySet()) {
//                lstObjFormulaExp.add(new ListValue(key, hmModuleTable.get(key)));
////            System.err.println("Gia tri " + hmModuleTable.get(key));
//            }
//        TreeNode nodeChild = new TreeNode();
            //HttpSession session = servletRequest.getSession();
            //Luu lai module trong session
            //session.setAttribute("moduletable", lstObjFormulaExp);
            ArrayList<String> ArrlstPoscd = hmPos.get("POS_CD");
            ArrayList<String> ArrlstPosDesc = hmPos.get("POS_DESC");

            if (ArrlstPosDesc.isEmpty() || ArrlstPoscd.isEmpty()) {
                System.err.println("Loi khi lay pos");
                setMessage("Lỗi không thể lấy ra được pos từ user này");
                return ERROR;
            }
            //Add du lieu len TreeNode cần sửa lại để lấy dữ liệu được theo tỉnh, theo trung ương
//        nodes_pos.setId("HOISOTINH");
//        nodes_pos.setTitle("Hội sở tỉnh");
//        nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
//        nodes_pos.setChildren(new LinkedList<TreeNode>());

            for (int i = 0; i < ArrlstPoscd.size(); i++) {
                String strPos_key = ArrlstPoscd.get(i);
                if (strPos_key.equals("000000")) {
                    nodes_pos.setId(strPos_key);
                    nodes_pos.setTitle(ArrlstPosDesc.get(i));
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                } else {
                    TreeNode nodeChild = new TreeNode();
                    nodeChild.setId(strPos_key);
                    nodeChild.setTitle(ArrlstPosDesc.get(i));
//                System.err.println(ArrlstPosDesc.get(i));
                    nodes_pos.getChildren().add(nodeChild);
                }
            }
        } catch (Exception e) {
            CoreLogger.error("Loi khi load cac bao cao loadReportExp " + e.getMessage());
            System.err.println(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadEditFormula() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String strUserName = session.get("username").toString();
        DaoRptFormula daoFormula = new DaoRptFormula();
        HashMap<Integer, List<ListValue>> hm = daoFormula.getDmKhac();
        lstBranchObj = daoFormula.getLoadBranch(save_id, strUserName);
        lstSourceData.addAll(hm.get(31));
        lstUntilData.addAll(hm.get(32));
        lstReportType.addAll(hm.get(33));
        lstReportTimes.addAll(hm.get(34));
        lstRowColumn.addAll(hm.get(35));

        //Lay gia tri da luu tu csdl ra 1 mang
        RptFormula objdefaultValues = new RptFormula();
        objdefaultValues = daoFormula.getLoadEditFormula(save_id);

        //Thiet lap default gia tri cho cac select
        setDefaultSourceData(objdefaultValues.getSource_data());
        setDefaultUntilData(objdefaultValues.getUntil_data());
        setDefaultReportType(objdefaultValues.getReport_type());
        setDefaultReportTimes(objdefaultValues.getReport_times());
        setDefaultDisplayRowColumn(objdefaultValues.getRow_column());
        //Thiet lap gia tri cho cac dieu khien

//        System.err.println("ROw_COLUMN "+objdefaultValues.getRow_column());
        title_name = objdefaultValues.getTitle_name();
        //row_column=objdefaultValues.getRow_column();
        number_column = objdefaultValues.getNumber_column();
        number_row = objdefaultValues.getNumber_row();

        //thiet lap cho cap bao cao
        lstGrade = new ArrayList<ListValue>();
        //        System.err.println("vao phan sửa query " + save_id);
        lstGrade.add(new ListValue("1", "Ngân hàng"));
        lstGrade.add(new ListValue("2", "Chi nhánh"));
        lstGrade.add(new ListValue("3", "Toàn quốc"));

        String strGradeRpt = objdefaultValues.getGrade();
        ArrayList<String> lsttmp = DefineFun.SplitStringToArrayList(strGradeRpt == null ? "" : strGradeRpt, "#");
        if (lsttmp != null) {
            defaultGrade = new String[lsttmp.size()];
            for (int i = 0; i < lsttmp.size(); i++) {
                defaultGrade[i] = lsttmp.get(i);
            }
//            defaultGrade=(String[]) DefineFun.SplitStringToArrayList(strGradeRpt, "#").toArray();
            setDefaultGrade(defaultGrade);
        } else {
            setDefaultGrade(defaultGrade);
        }
        System.err.println("Save ID " + save_id);
        return SUCCESS;
    }

    public String ReDrawEditFormula() throws Exception {
//        System.err.println("Gia tri Save ID la " + getSave_id());
        if (source_data.equals("-1") || source_data == null || source_data.isEmpty()) {
            setMessage("Bạn phải chọn nguồn số liệu ");
            return ERROR;
        }
        //Kiểm tra kỳ báo cáo xem đã được chọn chưa
        if (report_times.equals("-1") || report_times == null || report_times.isEmpty()) {
            setMessage("Bạn phải chọn kỳ báo cáo ");
            return ERROR;
        }
        //Kiểm tra checkbox các chi nhánh xem đã được chọn chưa
        if (check_branch == null || check_branch.isEmpty()) {
            setMessage("Bạn phải chọn chi nhánh tạo báo cáo ");
            return ERROR;
        }
        //Kiểm tra tiêu đề báo cáo xem đã điền chưa
        if (title_name == null || title_name.isEmpty()) {
            setMessage("Bạn phải điền tiêu đề báo cáo ");
            return ERROR;
        }
        //Nếu báo cáo là loại chi tiết thì phải kiểm tra cả số dòng lẫn số cột
        if (report_type.equals("01")) {
            //Kiểm tra xem người sử dụng đã điền số dòng chưa 
            if (number_row == null || number_row.equals("0") || number_row.isEmpty()) {
                setMessage("Bạn phải điền số dòng của báo cáo ");
                return ERROR;
            }
            //kiểm tra xem đã điền số cột chưa
            if (number_column == null || number_column.equals("0") || number_column.isEmpty()) {
                setMessage("Bạn phải điền số cột của báo cáo ");
                return ERROR;
            }
        } else {//truong hop neu báo cáo là tổng hợp
            //Nếu chọn là báo cáo theo chỉ tiêu (Hiển thị theo dòng/cột)
            //thì phải điền số dòng
            if (row_column.equals("2") && (number_row == null || number_row.equals("0") || number_row.isEmpty())) {
                setMessage("Bạn phải điền số dòng của báo cáo ");
                return ERROR;
            }
            //Nếu chọn là báo cáo theo chi nhánh (Hiển thị theo dòng/cột)
            //thì phải điền số cột
            if (row_column.equals("1") && (number_column == null || number_column.equals("0") || number_column.isEmpty())) {
                setMessage("Bạn phải điền số cột của báo cáo ");
                return ERROR;
            }
        }
        //Kiểm tra xem số dòng số cột được điển vào có đùng là số hay không
        if (number_row != null && !DefineFun.isNumeric(number_row)) {
            setMessage("Ô dữ liệu số dòng bạn phải điền là số không được điền ký tự");
            return ERROR;
        }
        if (number_column != null && !DefineFun.isNumeric(number_column)) {
            setMessage("Ô dữ liệu số cột bạn phải điền là số không được điền ký tự");
            return ERROR;
        }

        if (rptGrade == null || rptGrade.isEmpty()) {
            setMessage("Bạn phải chọn cấp xuất báo cáo ");
            return ERROR;
        }

        //Lay gia tri da luu tu csdl ra 1 mang
        RptFormula objdefaultValues = new RptFormula();
        objdefaultValues = new DaoRptFormula().getLoadEditFormula(save_id);

        if (report_type.equals("01")) {
//            if (!number_row.equals(objdefaultValues.getNumber_row())) {
//                setMessage("Bạn không được thay đổi số dòng của báo cáo");
//                return ERROR;
//            }
//            if (!number_column.equals(objdefaultValues.getNumber_column())) {
//                setMessage("Bạn không được thay đổi số cột của báo cáo");
//                return ERROR;
//            }
        } else {
            if (!row_column.equals(objdefaultValues.getRow_column())) {
                setMessage("Bạn không được thay đổi cách hiển thị của báo cáo");
                return ERROR;
            }

//            if (row_column.equals("2") && !number_row.equals(objdefaultValues.getNumber_row())) {
//                setMessage("Bạn không được thay đổi số dòng của báo cáo");
//                return ERROR;
//            }
            //Nếu chọn là báo cáo theo chi nhánh (Hiển thị theo dòng/cột)
            //thì phải điền số cột
//            if (row_column.equals("1") && !number_column.equals(objdefaultValues.getNumber_column())) {
//                setMessage("Bạn không được thay đổi số cột của báo cáo");
//                return ERROR;
//            }
        }

        return SUCCESS;
    }

    public String deleteRptFormula() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String strUserName = session.get("username").toString();
        if (session == null) {
            setMessage("Bạn phải logout ra và login lai thì mới xóa được báo cáo!");
            return ERROR;
        }
        DaoRptFormula daorptFormula = new DaoRptFormula();
        boolean bSuccess = daorptFormula.deleteRptFormula(save_id, strUserName);
        if (!bSuccess) {
            setMessage("Bạn chưa xóa được báo cáo, Do bạn không phải là người tạo báo cáo!");
            return ERROR;
        }
        setMessage("Xóa báo cáo thành công!");
        return SUCCESS;
    }

    //<editor-fold defaultstate="collapsed" desc="Ham convert du lieu cho lop">
    public static String convertHashMapToString(LinkedHashMap<String, String> paramHashMap) {
        String sOut = "";
        for (String key : paramHashMap.keySet()) {
            sOut = sOut + key + ":" + paramHashMap.get(key) + "#";
        }
        return sOut;
    }

    public static String convertObjectArrayToString(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj.toString().trim() + ",");
        }
        return sb.toString();
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public List<ModelFormula> getObjReturn() {
        return objReturn;
    }

    public void setObjReturn(List<ModelFormula> objReturn) {
        this.objReturn = objReturn;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ModelFormula> getData() {
        return data;
    }

    public void setData(List<ModelFormula> data) {
        this.data = data;
    }

    public List<ListValue> getLstObjFormulaExp() {
        return lstObjFormulaExp;
    }

    public void setLstObjFormulaExp(List<ListValue> lstObjFormulaExp) {
        this.lstObjFormulaExp = lstObjFormulaExp;
    }

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getExport_date() {
        return export_date;
    }

    public void setExport_date(String export_date) {
        this.export_date = export_date;
    }

    public String getDefaultDisplayRowColumn() {
        return defaultDisplayRowColumn;
    }

    public void setDefaultDisplayRowColumn(String defaultDisplayRowColumn) {
        this.defaultDisplayRowColumn = defaultDisplayRowColumn;
    }

    public String getDefaultSourceData() {
        return defaultSourceData;
    }

    public void setDefaultSourceData(String defaultSourceData) {
        this.defaultSourceData = defaultSourceData;
    }

    public String getDefaultUntilData() {
        return defaultUntilData;
    }

    public void setDefaultUntilData(String defaultUntilData) {
        this.defaultUntilData = defaultUntilData;
    }

    public String getDefaultReportType() {
        return defaultReportType;
    }

    public void setDefaultReportType(String defaultReportType) {
        this.defaultReportType = defaultReportType;
    }

    public String getDefaultReportTimes() {
        return defaultReportTimes;
    }

    public void setDefaultReportTimes(String defaultReportTimes) {
        this.defaultReportTimes = defaultReportTimes;
    }

    public String getSave_id() {
        return save_id;
    }

    public void setSave_id(String save_id) {
        this.save_id = save_id;
    }

    public List<ListValue> getLstObjFormula() {
        return lstObjFormula;
    }

    public void setLstObjFormula(List<ListValue> lstObjFormula) {
        this.lstObjFormula = lstObjFormula;
    }

    public List<ListValue> getLstBranchObj() {
        return lstBranchObj;
    }

    public void setLstBranchObj(List<ListValue> lstBranchObj) {
        this.lstBranchObj = lstBranchObj;
    }

    public List<ListValue> getLstSourceData() {
        return lstSourceData;
    }

    public void setLstSourceData(List<ListValue> lstSourceData) {
        this.lstSourceData = lstSourceData;
    }

    public List<ListValue> getLstUntilData() {
        return lstUntilData;
    }

    public void setLstUntilData(List<ListValue> lstUntilData) {
        this.lstUntilData = lstUntilData;
    }

    public List<ListValue> getLstReportType() {
        return lstReportType;
    }

    public void setLstReportType(List<ListValue> lstReportType) {
        this.lstReportType = lstReportType;
    }

    public List<ListValue> getLstReportTimes() {
        return lstReportTimes;
    }

    public void setLstReportTimes(List<ListValue> lstReportTimes) {
        this.lstReportTimes = lstReportTimes;
    }

    public List<ListValue> getLstRowColumn() {
        return lstRowColumn;
    }

    public void setLstRowColumn(List<ListValue> lstRowColumn) {
        this.lstRowColumn = lstRowColumn;
    }

    public String getDefaultRowColumnValue() {
        return "1";
    }

    public String getSource_data() {
        return source_data;
    }

    public void setSource_data(String source_data) {
        this.source_data = source_data;
    }

    public String getUntil_data() {
        return until_data;
    }

    public void setUntil_data(String until_data) {
        this.until_data = until_data;
    }

    public String getCheck_branch() {
        return check_branch;
    }

    public void setCheck_branch(String check_branch) {
        this.check_branch = check_branch;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReport_times() {
        return report_times;
    }

    public void setReport_times(String report_times) {
        this.report_times = report_times;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getNumber_row() {
        return number_row;
    }

    public void setNumber_row(String number_row) {
        this.number_row = number_row;
    }

    public String getNumber_column() {
        return number_column;
    }

    public void setNumber_column(String number_column) {
        this.number_column = number_column;
    }

    public String getRow_column() {
        return row_column;
    }

    public void setRow_column(String row_column) {
        this.row_column = row_column;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListValue> getLstDrawTable() {
        return lstDrawTable;
    }

    public void setLstDrawTable(List<ListValue> lstDrawTable) {
        this.lstDrawTable = lstDrawTable;
    }

    public List<DrawRowAndColumn> getLstRowTable() {
        return lstRowTable;
    }

    public void setLstRowTable(List<DrawRowAndColumn> lstRowTable) {
        this.lstRowTable = lstRowTable;
    }

    public List<DrawRowAndColumn> getLstColumnTable() {
        return lstColumnTable;
    }

    public void setLstColumnTable(List<DrawRowAndColumn> lstColumnTable) {
        this.lstColumnTable = lstColumnTable;
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

    public List<String> getRptGrade() {
        return rptGrade;
    }

    public void setRptGrade(List<String> rptGrade) {
        this.rptGrade = rptGrade;
    }

//</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Class Static draw report">
    public static class DrawRowAndColumn {

        String row;
        String column;

        public DrawRowAndColumn(String row, String column) {
            this.row = row;
            this.column = column;
        }

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }
    }
//</editor-fold>
}
