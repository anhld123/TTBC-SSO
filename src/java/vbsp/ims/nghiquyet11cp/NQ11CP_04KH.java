/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.inject.util.Strings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.UploadFileLogObject;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.excel_upload.ExcelUploader;
import vbsp.ims.excel_upload.model.ResultModel;
import vbsp.ims.fileutil.FileUtil;
import vbsp.ims.khnv2021.ReportTemplate;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.NQ11cpModel;
import vbsp.ims.restapi.UpdateLockModel;

import vbsp.ims.util.DateUtil;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author Trung
 */
public class NQ11CP_04KH extends ActionNghiquyet11cpMain
        implements NhaptaycnFunction {

    DuLieuNTService service;
    private String message;

    private static List<UploadFileLogObject> logObj = new ArrayList<>();
    private String logPath;
    private String logPathType;
    private String nghiepvu;

    public String getNghiepvu() {
        return nghiepvu;
    }

    public void setNghiepvu(String nghiepvu) {
        this.nghiepvu = nghiepvu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static List<UploadFileLogObject> getLogObj() {
        return logObj;
    }

    public static void setLogObj(List<UploadFileLogObject> logObj) {
        NQ11CP_04KH.logObj = logObj;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPathType() {
        return logPathType;
    }

    public void setLogPathType(String logPathType) {
        this.logPathType = logPathType;
    }

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstDulieuNt = daoMain.getDataKH04(conn, "NQ11CP_04KH", hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nghiepvu").toString());
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_04KH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_04KH: " + e.getMessage());
        }
        return SUCCESS;

    }

    public String openExcelUpload() throws Exception {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String s = hmParameter.get("ngay_bc").toString();
            System.err.println("Upload file ---" + s);
            String q = "";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_04KH - upfile: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_04KH - upfile: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveNQ11CP_KH04(khoa_nghiquyet11cp, UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, poscd,  hmParameter.get("nghiepvu").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_04KH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_04KH: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    //Upload file
    public String saveUploadKH04() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            

            Date date_ngay_bc = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());

            String ngay_bc = new SimpleDateFormat("yyyyMMdd").format(date_ngay_bc);

            if (fileUploadFileName.isEmpty()) {

                message = "(*) Chưa có file nào được lựa chọn. Bạn hãy kiểm tra lại. ";
                return "success";

            } else {

                /* Phan cap nhat file */
                String new_file_path = copy_file(hmParameter.get("nghiepvu").toString(), ngay_bc);
                File new_file = new File(new_file_path);

                if (new_file.isFile()) {

                    String fileExtend = FilenameUtils.getExtension(new_file_path);
                    String file_name = new_file.getName();

                    if (fileExtend.toLowerCase().equals("zip")) {
                        boolean is_unzip = unzip_file(new_file.getAbsolutePath(), new_file.getParent());

                        if (is_unzip) {

                            ExcelUploader excelUploader = new ExcelUploader();
                            ResultModel status = excelUploader.import_directory(new_file.getParent() + "/"
                                    + FilenameUtils.removeExtension(file_name), "UTF8");

                            DtwUploadDao uploadDao = new DtwUploadDao();
                            String dir_path = new_file.getParent() + "/"
                                    + FilenameUtils.removeExtension(file_name);

                            logPath = dir_path.replace("/", "\\");
                            logPathType = ReportTemplate.DIRECTORY;
                            logObj = uploadDao.get_uploaded_log(dir_path.replace("/", "\\"), ReportTemplate.DIRECTORY);
                            if (status.status) {
                                message = "(*) Copy và giải nén vào thư mục thành công: [" + file_name + "].";
                            } else {
                                message = status.message;
                            }

                        } else {
                            message = "(*) Copy thành công nhưng không giải nén được: [" + file_name + "].";
                        }
                    } else if (fileExtend.toLowerCase().equals("xls")
                            || fileExtend.toLowerCase().equals("xlsx")) {
                        ExcelUploader excelUploader = new ExcelUploader();
                        ResultModel status = excelUploader.import_file(new_file.getAbsolutePath(), "UTF8");

                        DtwUploadDao uploadDao = new DtwUploadDao();
                        String file_path = FilenameUtils.removeExtension(new_file.getAbsolutePath());

                        logPath = file_name;
                        logPathType = ReportTemplate.FILE;
                        logObj = uploadDao.get_uploaded_log(file_name, ReportTemplate.FILE);
                        if (status.status) {
                            if (file_name.startsWith(Define.GIAO_KHTDNQ11)) {
                                List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
                                lstDulieuNt = new XDKHDao2021().getDataQtKehoachByFile(Define.GIAO_KHTDNQ11, file_name);
//                            String sReturn = sendDataNV_QTByApi(lstDulieuNt,file_name);
//                            if(sReturn.equals(SUCCESS))
//                                message = "(*) Xử lý file thành công: [" + file_name + "].";
//                                else
//                                message = "(*) Xử lý api thành công: [" + file_name + "].";                                
                            }

                        } else {
                            message = status.message;
                        }
                    } else {
                        message = "(*) Không hỗ trợ định dạng file: " + fileExtend.toLowerCase();
                    }

                } else {
                    message = "(*) Copy file vào thư mục thất bại.";
                }

                return "success";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }

    }

    private boolean unzip_file(String zip_file_path, String directory_path) {

        File zip_file = new File(zip_file_path);
        String strFileName = zip_file.getName();

        // Xoa du lieu truoc khi giai nen
        FileUtil.deleteFolder(directory_path + "/"
                + FilenameUtils.removeExtension(strFileName));

        String file_ext = FilenameUtils.getExtension(zip_file_path);

        if (zip_file.isFile()
                && file_ext.toLowerCase().equals("zip")) {
            FileZip.UnzipFile(zip_file_path, directory_path);
            return true;
        } else {
            return false;
        }

    }

    public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }

    private String copy_file(String nghiepvu, String ngaybc) throws Exception {
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
                    filename = String.valueOf(System.currentTimeMillis()) + "_" + nghiepvu +  "_"+ ngaybc +  "_"+ filename   ;
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

    public void main(String[] args) {
        saveUploadKH04();
//        DuLieuNTService service = new DuLieuNTService();
//        Date timeServer = service.getTimeServer();

//         Date date = Calendar.getInstance().getTime();  
//                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
//                String strDate = dateFormat.format(timeServer);  
//        System.out.println("Converted String: " + service.getTimeServer());
//          String file = "NV_QT_000401_S_31122021_quyennv_6283";
//          
//          String[] array = file.split("_", -1);
//          String s1 = array[0];
//          String s2 = array[3];
//          String s3 = array[1];    
//        ArrayList<DuLieuNTRow> lstData = service.getData("COVID_03", "000401", "S", "20210630");
//        System.out.println(lstData.size());
//
//        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
//        DuLieuNTRow testItem = new DuLieuNTRow();
//        testItem.setKey("COVID_03");
//        testItem.setCode("1004003452");
//        testItem.setReportDate("2021-06-30T00:00:00");
//        testItem.setPosCode("000401");
//        testItem.setPosFlag("S");
//        testItem.setD1("1004003452");
//        testItem.setD2("100");
//        testItem.setReportYear(2021);
//        lstUpdateDate.add(testItem );
//
//        int status = service.updateData("COVID_03", "000401", "S", "20210630", "trungnt", "", lstUpdateDate);
//        List<PLNO_DULIEU> lstPLNo = new ArrayList<>();
//        PLNO_DULIEU plno_dulieu = PLNO_DULIEU.newInstance();
//        plno_dulieu.setsSoku("6600000715491945");
//        plno_dulieu.setsSoku("6600000717477667");
//        lstPLNo.add(plno_dulieu);
//        List<NQ11cpModel> lstDulieuNt = new ArrayList<>();
//        List<QT_DULIEU_NT> lstDulieuNt1 = new ArrayList<>();
//        service = new DuLieuNTService();
////        lstDulieuNt = service.getDataNQ11CP("000601", "20220228", "03",
////                "060101", "0091543");
//
//        ArrayList<DuLieuNTRow> lstData = service.getDataNQ11CP_01KH("000601", "20220331", "S");
//        margerData(lstData,"0","0000");
    }
}
