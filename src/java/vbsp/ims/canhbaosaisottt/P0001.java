/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.canhbaosaisottt;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.ExportText2SbvManager;
import vbsp.ims.model.FileInfo;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author Trung
 */
public class P0001 extends ActionCanhbaoSsttMain
        implements CbssFunction {

    private String khoaduyet;
    private String strComment;

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public String getKhoaduyet() {
        return khoaduyet;
    }

    public void setKhoaduyet(String khoaduyet) {
        this.khoaduyet = khoaduyet;
    }

    @Override
    public String load() {
        try {
            System.err.println("P0001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();
            lstDulieuNt = daoMain.getDataCBSS01_Main(conn, khoa_cbss, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("mapgd").toString());
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        return SUCCESS;
    }

    public String saveGiaiTrinh() {
        System.err.println("Save - saveGiaiTrinh");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();
            if (!daoMain.saveP001(hmParameter.get("khoa_detail").toString(), UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
//        giaitrinh();
//        addActionMessage("Lưu dữ liệu thành công.<script>alert('Lưu dữ liệu thành công.');</script>");
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String saveGiaiTrinhTmp() {
        System.err.println("Save - saveGiaiTrinhTmp");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();
            if (!daoMain.saveP001_tmp(hmParameter.get("khoa_detail").toString(), UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
//        giaitrinh();
//        addActionMessage("Lưu dữ liệu thành công.<script>alert('Lưu dữ liệu thành công.');</script>");
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String xuatxls() {

        if (!getParaSession()) {
            return ERROR;
        }
        HashMap hmParameter1;
        String sNg = "";
        String possolieu = "";
        try {
            hmParameter1 = getParameter();
            sNg = hmParameter1.get("ngay_bc").toString();
            possolieu = hmParameter1.get("mapgd").toString();
        } catch (Exception ex) {
            Logger.getLogger(P0001.class.getName()).log(Level.SEVERE, null, ex);
        }

        request = ServletActionContext.getRequest();
        query = request.getParameter("query");
        String strTimeFile = Long.toString(System.currentTimeMillis());
        ArrayList<String> fullPathList = new ArrayList<>();
        String zipFile = "FileNen_" + possolieu + "_" + strTimeFile + ".zip", zipPath = "";
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();

        for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
            if (value != null) {
                if (!value.getMA().equals("false")) {
                    String save_id = value.getMA();
                    if (save_id == null | save_id.isEmpty()) {
                        setMessage("Bạn chưa chọn mẫu báo cáo nên không thể tạo báo cáo ");
                        return ERROR;
                    }

                    HashMap<String, String> paramHashMap = new HashMap<>();
                    String sPos_cd = "";
                    String stringParaPos_cd = "";
                    String sPosFlag = "";
                    //xy lay lay cac tham so cho vao hashmap
                    Map mapCollectPara = new HashMap();

                    //xu ly cho export file ra PDF hoac la Excel
                    String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
                    //duong dan chua file tren o dia + Define.M_REPORT_XLS
                    String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");;
                    //Ham nay lay ra ten file bao cao can tao, ten file jasper report

                    strTimeFile = Long.toString(System.currentTimeMillis());

                    String strFileSave = save_id + "_"
                            + "_" + strCurrDate
                            + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                    strPathSave += Define.M_REPORT_XLS;
                    strFileSave += ".XLSX";
                    filereport = strFileSave;
                    File Checkpath = new File(strPathSave);
                    if (!Checkpath.exists()) {
                        System.out.println("Da tao thu muc: " + strPathSave);
                        Checkpath.mkdirs();
                    }
                    //Xuat file du lieu o day

                    DaoRptQuery daoQuery = new DaoRptQuery();

                    setQuery(daoQuery.getQuery(save_id, new DaoConnect().getConnect()));
                    ImsPlSqlQuery plsql = new ImsPlSqlQuery();

//        paramHashMap.put("PD_REPORT_DATE", new SimpleDateFormat("dd-MMM-yyyy").format("31-JAN-2021"));
                    Date sdf = null;
                    try {
                        sdf = new SimpleDateFormat("dd-MMM-yyyy").parse(sNg);
                    } catch (ParseException ex) {
                        Logger.getLogger(P0001.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    paramHashMap.put("PD_REPORT_DATE", new SimpleDateFormat("dd-MMM-yyyy").format(sdf));

                    mapCollectPara.put("PD_REPORT_DATE",
                            ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
                    sPos_cd = possolieu;
                    stringParaPos_cd = "PV_POS_CD";
                    sPosFlag = "N";

                    daoQuery.getDataExp(save_id, paramHashMap, sPos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave);

                    //Kiem tra xem file da tao thanh cong chua
                    File filerpt = new File(strPathSave + strFileSave);
                    if (!filerpt.exists()) {
                        setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
                        return ERROR;
                    }
                    fileNamelocal = strPathSave + strFileSave;

                    lstOfTextFile.add(fileNamelocal);
                    FileInfo file = new FileInfo(new File(fileNamelocal));
                    filesList.add(new DownloadFileInfor(file.getName(), fileNamelocal,
                            DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                    fullPathList.add(file.getAbsolutePath());
                    zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
                }
            }
        }

        if (fullPathList.size() > 0) {
            try {
                FileZip.ZipFileFromArray(fullPathList, zipPath);
                zipFileList.add(zipFile);
                zipFileList.add(zipPath);
            } catch (Exception ex) {
                Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            filereport = zipFile;
            fileNamelocal = zipPath;
        }

        System.gc();
        return SUCCESS;
    }

    public String giaitrinh() {
        try {
            System.err.println("P0001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String[] values = sngaybc.split("\\-");
            String ssngay = values[0];
            String ssthang = values[1];
            String ssnam = values[2];
            String result = ssthang.length() != 3 ? ssthang.substring(0, 3) : ssthang;
//            System.out.println("check: " +result);
            String formattedDate = ssngay + result + ssnam;
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();
            lstDulieuNt = daoMain.getDataCBSS01_Detail(conn, hmParameter.get("khoa_detail").toString(), formattedDate, UserName, Grade, poscd, hmParameter.get("mapgd").toString());
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String pheduyetP001() {
        try {
            System.err.println("pheduyetP001");
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();

            String sResult = daoMain.authDetail(khoaduyet, UserName, Grade, "", "");

            if (conn != null) {
                conn.close();
            }
            giaitrinh();
            addActionMessage("Thực hiện lệnh thành công.<script>alert('Thực hiện lệnh thành công.');</script>");

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> P0001: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String uploadGiaitrinh() {
        try {
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> uploadGiaitrinh: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> uploadGiaitrinh: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String uploadCbss_Giaitrinh() {
        try {
            System.err.println("Upload file");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String khoa_detail = hmParameter.get("khoa_detail").toString();
            if (fileUploadFileName.isEmpty()) {
                addActionError("Bạn chưa chọn file để thực hiện upload !");
                return ERROR;
            }
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
//            String maxls = hmParameter.get("maxls").toString();
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            DaoCanhbaoSsttMain daoCbss = new DaoCanhbaoSsttMain();
            String start_end = "5-30";
            String poscd = dao.getPosCd(UserName);
            int startrow = 0, endcell = 0;
            if (!start_end.equals("AAA")) {
                startrow = Integer.parseInt(start_end.split("-")[0]);
                endcell = Integer.parseInt(start_end.split("-")[1]);
            }

            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
//                if (!getFileNameNew().contains(maxls)) {
//                    addActionError("Bạn chọn file upload không đúng với báo cáo ! " + maxls);
//                    return ERROR;
//                }  
//                
                String strTimeFile = Long.toString(System.currentTimeMillis());

                String sMa = UserName + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                if (!daoCbss.insert_xlx_giaitrinh(hmParameter.get("khoa_detail").toString(), hmParameter.get("mapgd").toString(), getFileNameNew(), convertStringToDate(hmParameter.get("ngay_bc").toString()), UserName, lstExcel, sMa)) {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }
                if (!daoCbss.mergeAfterUpFile(hmParameter.get("khoa_detail").toString(), UserName, hmParameter.get("mapgd").toString(),
                        hmParameter.get("ngay_bc").toString(), sMa, Grade)) {
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }
            Connection conn = new DaoConnect().getConnect();
            lstDulieuNt = dao.getDataAfterUpFile(conn, "COVID_GIAINGAN", sNgayBC, poscd, UserName, Grade, "", "");
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }
        addActionMessage("Bạn đã upload file thành công. ");
        return SUCCESS;
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

}
