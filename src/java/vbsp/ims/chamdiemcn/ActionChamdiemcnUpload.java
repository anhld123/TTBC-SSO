/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import vbsp.ims.chamdiemcn.*;
import vbsp.ims.bcqt.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlBcqtSync;
import vbsp.ims.xml.XmlKtgsSync;
import vbsp.ims.define.Define;
import vbsp.ims.model.Pagination;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT;
import vbsp.ims.define.DefineFun;

/**
 *
 * @author LION
 */
public class ActionChamdiemcnUpload extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected String tableDetail;
    protected String thuyetminh;
    protected String khoa_cdtt;
    protected String pos_string;
    protected String macb;

    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    private List<ModelExcelFile> lstExcel = new ArrayList<>();
    private String fileNameNew;
//    private int startrow;
//    private int endcell;    

    public List<String> getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(List<String> fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public List<ModelExcelFile> getLstExcel() {
        return lstExcel;
    }

    public void setLstExcel(List<ModelExcelFile> lstExcel) {
        this.lstExcel = lstExcel;
    }

    public String getFileNameNew() {
        return fileNameNew;
    }

    public void setFileNameNew(String fileNameNew) {
        this.fileNameNew = fileNameNew;
    }

//    public int getStartrow() {
//        return startrow;
//    }
//
//    public void setStartrow(int startrow) {
//        this.startrow = startrow;
//    }
//
//    public int getEndcell() {
//        return endcell;
//    }
//
//    public void setEndcell(int endcell) {
//        this.endcell = endcell;
//    }
    public List<File> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<File> fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<String> getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(List<String> fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_TH() {
        return lstDulieuNt_TH;
    }

    public void setLstDulieuNt_TH(List<QT_DULIEU_NT> lstDulieuNt_TH) {
        this.lstDulieuNt_TH = lstDulieuNt_TH;
    }
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_TH = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();
    private List<ListValue> lstCBTindung = new ArrayList<ListValue>();
    private List<ListValue> lstCBKetoan = new ArrayList<ListValue>();
    protected List<String> poscd = new ArrayList<String>();
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String tt_khoa;
    private String macn;
    private String ngay_bc;
    protected String tt_cdtt;
    protected String heso_k;
    protected String RULEUSER;
    protected String pheduyet;
    protected Pagination pagination = new Pagination(50, 1);

    protected String totalDataView;

    public String getTotalDataView() {
        return totalDataView;
    }

    public void setTotalDataView(String totalDataView) {
        this.totalDataView = totalDataView;
    }

    public List<saveDulieuNT> getLstsaveNT() {
        return lstsaveNT;
    }

    public void setLstsaveNT(List<saveDulieuNT> lstsaveNT) {
        this.lstsaveNT = lstsaveNT;
    }
    protected List<saveDulieuNT> lstsaveNT = new ArrayList<saveDulieuNT>();

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getHeso_k() {
        return heso_k;
    }

    public String getPheduyet() {
        return pheduyet;
    }

    public void setPheduyet(String pheduyet) {
        this.pheduyet = pheduyet;
    }

    public void setHeso_k(String heso_k) {
        this.heso_k = heso_k;
    }

    public String getMacb() {
        return macb;
    }

    public void setMacb(String macb) {
        this.macb = macb;
    }

    public String getPos_string() {
        return pos_string;
    }

    public void setPos_string(String pos_string) {
        this.pos_string = pos_string;
    }

    public String getThuyetminh() {
        return thuyetminh;
    }

    public void setThuyetminh(String thuyetminh) {
        this.thuyetminh = thuyetminh;
    }

    public String getTableDetail() {
        return tableDetail;
    }

    public void setTableDetail(String tableDetail) {
        this.tableDetail = tableDetail;
    }

    public String getRULEUSER() {
        return RULEUSER;
    }

    public void setRULEUSER(String RULEUSER) {
        this.RULEUSER = RULEUSER;
    }
    protected String TT_DUYET;

    private List<ListValue> lstPhongBan = new ArrayList<ListValue>();
    private List<ListValue> lstDatKhong = new ArrayList<ListValue>();
    private List<ListValue> lstDambao = new ArrayList<ListValue>();

    private List<ListValue> lstUser = new ArrayList<ListValue>();
    private List<ListValue> lstFuncTTBC = new ArrayList<ListValue>();

    public String getTT_DUYET() {
        return TT_DUYET;
    }

    public List<ListValue> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<ListValue> lstUser) {
        this.lstUser = lstUser;
    }

    public List<ListValue> getLstFuncTTBC() {
        return lstFuncTTBC;
    }

    public void setLstFuncTTBC(List<ListValue> lstFuncTTBC) {
        this.lstFuncTTBC = lstFuncTTBC;
    }

    public List<ListValue> getLstPhongBan() {
        return lstPhongBan;
    }

    public void setLstPhongBan(List<ListValue> lstPhongBan) {
        this.lstPhongBan = lstPhongBan;
    }

    public List<ListValue> getLstDatKhong() {
        return lstDatKhong;
    }

    public void setLstDatKhong(List<ListValue> lstDatKhong) {
        this.lstDatKhong = lstDatKhong;
    }

    public List<ListValue> getLstDambao() {
        return lstDambao;
    }

    public void setLstDambao(List<ListValue> lstDambao) {
        this.lstDambao = lstDambao;
    }

    public void setTT_DUYET(String TT_DUYET) {
        this.TT_DUYET = TT_DUYET;
    }

    public String getTt_cdtt() {
        return tt_cdtt;
    }

    public void setTt_cdtt(String tt_cdtt) {
        this.tt_cdtt = tt_cdtt;
    }

    protected List<ReportParam> lstCdttParams = new ArrayList<>();

    public List<ReportParam> getLstCdttParams() {
        return lstCdttParams;
    }

    public void setLstCdttParams(List<ReportParam> lstCdttParams) {
        this.lstCdttParams = lstCdttParams;
    }

    protected List<ListValue> lstAllCdtt = new ArrayList<>();

    public List<ListValue> getLstAllCdtt() {
        return lstAllCdtt;
    }

    public void setLstAllCdtt(List<ListValue> lstAllCdtt) {
        this.lstAllCdtt = lstAllCdtt;
    }

    protected String MACT;

    public String getMACT() {
        return MACT;
    }

    public void setMACT(String MACT) {
        this.MACT = MACT;
    }

    public List<String> getLstDat() {
        return lstDat;
    }

    public void setLstDat(List<String> lstDat) {
        this.lstDat = lstDat;
    }
    private List<ListValue> lstGioiTinh = new ArrayList<ListValue>();
    private List<ListValue> lstDanToc = new ArrayList<ListValue>();
    private List<ListValue> lstDonVi = new ArrayList<ListValue>();
    private List<ListValue> lstChucVu = new ArrayList<ListValue>();
    private List<ListValue> lstTrangThai = new ArrayList<ListValue>();
    private List<ListValue> lstThanhVien = new ArrayList<ListValue>();
    private List<ListValue> lstBDD = new ArrayList<ListValue>();

    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();
    protected String addedit;
    protected List<String> lstDat = new ArrayList<String>();

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xu ly cho action">
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoi tao form chinh">    
    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        //lay ra user
        setUserName(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (UserName == null || UserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setGrade(session.get("reportGrade").toString());
        if (Grade == null || Grade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        return true;
    }

    protected List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        try {

            Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
            for (String parameter : prameters.keySet()) {
                String[] values = prameters.get(parameter);
                if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                    if (parameter.startsWith("1_")) {
                        parameter = parameter.substring(2, parameter.length());
                    }
                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                        lstParameters.add(new ListValue(parameter, values[0]));
                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                        lstParameters.add(new ListValue(parameter, values[0]));
                    }
                } else {
                    if (parameter.startsWith("1_")) {
                        parameter = parameter.substring(2, parameter.length());
                    }
                    if (parameter.equals("poscd")) {
                        paramHashMap.put(parameter, convertStringtoList(values));
                    } else {
                        paramHashMap.put(parameter, values[0]);
                        lstParameters.add(new ListValue(parameter, values[0]));
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return paramHashMap;
    }

    public String execute() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            setLstAllCdtt(DaoChamdiemcnMain.newInstance().getAllBcUpload(Grade, UserName));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

  public String uploadCDCNTH() {
    try {
        System.err.println("vao upload file");

        if (!getParaSession()) {
            System.err.println("Lỗi session.");
            return ERROR;
        }

        HashMap hmParameter = getParameter();

        if (fileUploadFileName.isEmpty()) {
            addActionError("Bạn chưa chọn file để thực hiện upload !");
            return ERROR;
        }

        // Kiểm tra file có đúng định dạng Excel không
        for (int i = 0; i < fileUploadFileName.size(); i++) {
            if (!DefineFun.isFileExcel(fileUploadFileName.get(i))) {
                addActionError("(*) File không phải là file excel. " + fileUploadFileName.get(i));
                return ERROR;
            }
        }

        String new_file_path = copy_file();
        File new_file = new File(new_file_path);
        DaoChamdiemcnMain dao = new DaoChamdiemcnMain();
        String start_end = dao.getStartEndCel(khoa_cdtt);
        String poscd = dao.getPosCd(UserName);

        System.out.println("✔ poscd (mã người dùng): " + poscd);
        System.out.println("✔ start_end cấu hình: " + start_end);

        int startrow = 0, endcell = 0;
        if (!start_end.equals("AAA")) {
            startrow = Integer.parseInt(start_end.split("-")[0]);
            endcell = Integer.parseInt(start_end.split("-")[1]);
        }

        if (new_file.isFile()) {
            setLstExcel(readFileExcel(new_file_path, startrow, endcell));

            System.out.println("so dong doc dc: " + lstExcel.size());

            if (lstExcel.isEmpty()) {
                addActionError("❌ Không đọc được dữ liệu nào từ file Excel.");
                return ERROR;
            }

            setFileNameNew(new_file.getName());

            if (!getFileNameNew().contains(khoa_cdtt)) {
                addActionError("Bạn chọn file upload không đúng với báo cáo !");
                return ERROR;
            }

            // Gọi insert
            System.out.println("✔ Gọi insert dữ liệu vào DB...");
            boolean insertResult = dao.insertCDCN_FROM_FILE(
                khoa_cdtt,
                poscd,
                getFileNameNew(),
                convertStringToDate(hmParameter.get("ngaybc").toString()),
                UserName,
                lstExcel
            );

            System.out.println("✔ Kết quả insert: " + insertResult);

            if (!insertResult) {
                addActionError("❌ Lỗi khi đọc dữ liệu từ file excel ");
                return ERROR;
            }

            String sNgayBC = hmParameter.get("ngaybc").toString();
            Connection conn = new DaoConnect().getConnect();
            setLstExcel(dao.getDataAfterUpFile(conn, khoa_cdtt, sNgayBC, poscd, UserName, Grade));

            if (conn != null) {
                conn.close();
            }

            System.out.println("✔ Dữ liệu sau khi insert, số dòng trả về: " + lstExcel.size());
        }

    } catch (Exception e) {
        CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
        System.err.println("❌ Exception xảy ra: " + e.getMessage());
        addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
        return ERROR;
    }

    // Trả về view tương ứng
    switch (khoa_cdtt) {
        case "GIAO_KHNV":
            return "GIAO_KHNV";
        case "GIAO_KHTK_DC":
            return "GIAO_KHTK_DC";
        case "GIAO_KHTK_TO":
            return "GIAO_KHTK_TO";
        case "KT_CHUNGTU_KT":
            return "KT_CHUNGTU_KT";
        case "DT_KHNV_HST":
            return "DT_KHNV_HST";
        case "TM_QATCT":
            return "TM_QATCT";
        case "PHUTRACHXA_PGD":
            return "PHUTRACHXA_PGD";
        default:
            return SUCCESS;
    }
}


    public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            date = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }

    private String copy_file() throws Exception {
        String destPath, mainReportPath = "";
        try {

            File destFile;
            int index = 0;
            for (String filename : fileUploadFileName) {
                try {
                    destPath = getPathRoot() + Define.M_UPLOAD_DIR;
                    if (!new File(destPath).exists()) {
                        new File(destPath).mkdirs();
                    }
                    filename = String.valueOf(System.currentTimeMillis()) + "_" + filename;
                    destFile = new File(destPath, filename);
                    //FileUtils.copyFile(fileUpload.get(index), destFile);
                    copyFileUsingFileStreams(fileUpload.get(index), destFile);
                    if (index == 0) {
                        mainReportPath = destPath + filename;
                    }
                    index++;
                } catch (IOException e) {
                    System.err.println("error when copy large file: " //+fileUpload.get(index)
                            + "~" + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

        return mainReportPath;
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {

        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    public String getPathRoot() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        path = DefineFun.backlashReplace(path);
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path;
    }

    private List<ModelExcelFile> readFileExcel(String fileName, int startRow, int EndCell) throws IOException, InvalidFormatException {
        List<ModelExcelFile> lstExcelKhnv = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(new File(fileName));

//            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if (startRow >= row.getRowNum() + 1) {
                    continue;
                }
                ModelExcelFile value = new ModelExcelFile();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() + 1 > EndCell) {
                        continue;
                    }
                    int cellType = cell.getCellType();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen

                    switch (cellType) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            System.err.println(cell.getNumericCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            System.err.println(cell.getStringCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                            value.setFormula(value.getFormula() + " -> " + evaluator.evaluate(cell).getStringValue());
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            //Not again
                            break;
                    }
                }
//                System.out.println("");
                lstExcelKhnv.add(value);
            }

            workbook.close();

        } catch (IOException e) {
            throw new IOException(e);
        }
        return lstExcelKhnv;
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public String getAddedit() {
        return addedit;
    }

    public void setAddedit(String addedit) {
        this.addedit = addedit;
    }

    public List<ListValue> getLstCBTindung() {
        return lstCBTindung;
    }

    public void setLstCBTindung(List<ListValue> lstCBTindung) {
        this.lstCBTindung = lstCBTindung;
    }

    public List<ListValue> getLstCBKetoan() {
        return lstCBKetoan;
    }

    public void setLstCBKetoan(List<ListValue> lstCBKetoan) {
        this.lstCBKetoan = lstCBKetoan;
    }

    public String getType_bcqt() {
        return type_bcqt;
    }

    public void setType_bcqt(String type_bcqt) {
        this.type_bcqt = type_bcqt;
    }

    public List<ListValue> getLstGioiTinh() {
        return lstGioiTinh;
    }

    public void setLstGioiTinh(List<ListValue> lstGioiTinh) {
        this.lstGioiTinh = lstGioiTinh;
    }

    public List<ListValue> getLstDanToc() {
        return lstDanToc;
    }

    public void setLstDanToc(List<ListValue> lstDanToc) {
        this.lstDanToc = lstDanToc;
    }

    public List<ListValue> getLstDonVi() {
        return lstDonVi;
    }

    public void setLstDonVi(List<ListValue> lstDonVi) {
        this.lstDonVi = lstDonVi;
    }

    public List<ListValue> getLstChucVu() {
        return lstChucVu;
    }

    public void setLstChucVu(List<ListValue> lstChucVu) {
        this.lstChucVu = lstChucVu;
    }

    public List<ListValue> getLstThanhVien() {
        return lstThanhVien;
    }

    public void setLstThanhVien(List<ListValue> lstThanhVien) {
        this.lstThanhVien = lstThanhVien;
    }

    public List<ListValue> getLstTrangThai() {
        return lstTrangThai;
    }

    public void setLstTrangThai(List<ListValue> lstTrangThai) {
        this.lstTrangThai = lstTrangThai;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public String getIsDisplayTM() {
        return isDisplayTM;
    }

    public void setIsDisplayTM(String isDisplayTM) {
        this.isDisplayTM = isDisplayTM;
    }

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getTt_khoa() {
        return tt_khoa;
    }

    public void setTt_khoa(String tt_khoa) {
        this.tt_khoa = tt_khoa;
    }

    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }

    public List<ListValue> getLstBDD() {
        return lstBDD;
    }

    public void setLstBDD(List<ListValue> lstBDD) {
        this.lstBDD = lstBDD;
    }

    public String getKhoa_cdtt() {
        return khoa_cdtt;
    }

    public void setKhoa_cdtt(String khoa_cdtt) {
        this.khoa_cdtt = khoa_cdtt;
    }

    public List<QT_DULIEU_NT.saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }
//</editor-fold>

}
