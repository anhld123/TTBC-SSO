/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class COVID_03 extends ActionNhaptaycnMain 
        
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
       try {
            System.err.println("COVID_03");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
                lstDulieuNt = daoMain.getDataCovid_03(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd, "", hmParameter.get("nha_dt").toString() );
//                setLstCBKetoan(daoMain.getCanBo(UserName,"LT"));
            if (conn != null) {
                conn.close();
            }                                       
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
        }        
        return SUCCESS;
    }
    

    @Override
    public String save() {
        System.err.println("Save - COVID_03");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
//            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
//                if(value != null)                    
//                    if (!value.getD2().equals("false")) {
//                        lstDat.add(value.getD2());
//                    }
//            }            
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();            
            
//            String iCheck = daoMain.checkData_Info(lstDulieuNt,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat);
//            if(!iCheck.equals("XXXAAA"))
//            {
//                addActionError("Lỗi! "+ iCheck);
//                    return ERROR; 
//            }                        
            
            if(!daoMain.saveCoVid03(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } 

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }   
    
    public String UploadPL02(){
       try {                                     
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
        }        
        return SUCCESS;
    }
    
    public String uploadCoVid() {
        try {
            System.err.println("Upload file");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            if (fileUploadFileName.isEmpty()) {
                addActionError("Bạn chưa chọn file để thực hiện upload !");
                return ERROR;
            }
            String pattern = "dd-MMM-yyyy";
                String sNgayBC = hmParameter.get("ngay_bc").toString();
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            String start_end = dao.getStartEndCel("COVID_NLD_GOC");
            String poscd = dao.getPosCd(UserName);
            int startrow = 0, endcell = 0;
            if (!start_end.equals("AAA")) {
                startrow = Integer.parseInt(start_end.split("-")[0]);
                endcell = Integer.parseInt(start_end.split("-")[1]);
            }
            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
                if (!getFileNameNew().contains("COVID_NLD_GOC")) {
                    addActionError("Bạn chọn file upload không đúng với báo cáo !");
                    return ERROR;
                }                
                if (!dao.insert_PL02_FILE("COVID_NLD_GOC", poscd, getFileNameNew(), convertStringToDate(sNgayBC), UserName, lstExcel, masothue)) 
                {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }                                           
            }
            Connection conn = new DaoConnect().getConnect();
                lstDulieuNt = dao.getDataAfterUpFile(conn, "COVID_NLD_GOC", sNgayBC, poscd, UserName, Grade,  langiangan, masothue);
                if (conn != null) {
                    conn.close();
                }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_NLD_GOC: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_NLD_GOC: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }        
            return SUCCESS;
    }
    
    public String UploadDSGiaiNgan(){
       try {      
//           if (!getParaSession()) {
//                return ERROR;
//            }
//            HashMap hmParameter = getParameter();
//           DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();   
//           setLstAllCdtt(daoMain.getCanBo(UserName,"LANGN")); //Lần giải ngân
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
        }        
        return SUCCESS;
    }
    
    public String UploadDSGiaiNgan1(){
       try {      
           if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
           DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();   
           setLstAllCdtt(daoMain.getCanBo(UserName,"LANGN")); //Lần giải ngân
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
        }        
        return SUCCESS;
    }
    
    //Upload danh sách giải ngân
    public String uploadCoVid_GN() {
        try {
            System.err.println("Upload file");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            if (fileUploadFileName.isEmpty()) {
                addActionError("Bạn chưa chọn file để thực hiện upload !");
                return ERROR;
            }
            String pattern = "dd-MMM-yyyy";
                String sNgayBC = hmParameter.get("ngay_bc").toString();
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            String start_end = dao.getStartEndCel("COVID_GIAINGAN");
            String poscd = dao.getPosCd(UserName);
            int startrow = 0, endcell = 0;
            if (!start_end.equals("AAA")) {
                startrow = Integer.parseInt(start_end.split("-")[0]);
                endcell = Integer.parseInt(start_end.split("-")[1]);
            }
            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
                if (!getFileNameNew().contains("COVID_GIAINGAN")) {
                    addActionError("Bạn chọn file upload không đúng với báo cáo !");
                    return ERROR;
                }                
                if (!dao.insert_COV_GAINGAN("COVID_GIAINGAN", poscd, getFileNameNew(), convertStringToDate(sNgayBC), UserName, lstExcel, masothue, langiangan)) 
                {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }                                           
            }
            Connection conn = new DaoConnect().getConnect();
                lstDulieuNt = dao.getDataAfterUpFile(conn, "COVID_GIAINGAN", sNgayBC, poscd, UserName, Grade, langiangan, masothue);
                if (conn != null) {
                    conn.close();
                }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }        
            return SUCCESS;
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
