/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author Trung
 */
public class CN23_01 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
          
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
           lstDulieuNt = daoMain.getDataCN23_01(conn, "CN23_01", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);//
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String editCN23(){
        try {
          
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
           lstDulieuNt = daoMain.getDataCN23_EDIT(conn, "CN23_01", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd, soku);//
           List<ListValue> lstBDD_tmp = new ArrayList<ListValue>();
           ListValue obj1 = new ListValue("1","Có","1");
           ListValue obj2 = new ListValue("0","Không","0");
           lstBDD_tmp.add(obj2);
           lstBDD_tmp.add(obj1);
           
           setLstBDD(lstBDD_tmp);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
        }
        return SUCCESS;
    }
    
        public String UploadFileCn23(){
            System.err.println("Save - CN23_01");
       try {      
//           if (!getParaSession()) {
//                return ERROR;
//            }
//            HashMap hmParameter = getParameter();
//           DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();   
//           setLstAllCdtt(daoMain.getCanBo(UserName,"LANGN")); //Lần giải ngân
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> UploadFileCn23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> UploadFileCn23: " + e.getMessage());
        }        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - CN23_01");
        try {
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }
            return SUCCESS;
    }  

    
    public String saveEditCN23() {
        System.err.println("Save - CN23_01 edit");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();              
            
            if(!daoMain.saveCN23_1("CN23_01", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }
            addActionMessage("Cập nhật thành công!");
            return SUCCESS;
    }         
    
    public String UpFileCN23() {
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
            
             //Kiểm tra file xem có đúng là xls ko
            for(int i =0; i<fileUploadFileName.size();i++)
            {
                 if(!DefineFun.isFileExcel(fileUploadFileName.get(i)))
                 {
                      addActionError("(*) File không phải là file excel. "+fileUploadFileName.get(i));
                      return ERROR;
                 }
            }
//            String pattern = "dd-MMM-yyyy";
//                String sNgayBC = hmParameter.get("ngay_bc").toString();
//                
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateAsString = hmParameter.get("ngay_bc").toString();
            Date date = sourceFormat.parse(dateAsString);

            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
//            String start_end = dao.getStartEndCel("CN23_01");
            String poscd = dao.getPosCd(UserName);
            int startrow = 3, endcell = 8;
//            if (!start_end.equals("AAA")) {
//                startrow = Integer.parseInt(start_end.split("-")[0]);
//                endcell = Integer.parseInt(start_end.split("-")[1]);
//            }
            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
                if (!getFileNameNew().contains("HTLS_CN23")) {
                    addActionError("Bạn chọn file upload không đúng với báo cáo !");
                    return ERROR;
                }                
                if (!dao.insert_HTLS_CN23("CN23_01", poscd, getFileNameNew(), date, UserName, lstExcel, masothue, langiangan)) 
                {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }                                           
            }
            Connection conn = new DaoConnect().getConnect();
                lstDulieuNt = dao.getDataAfterUpFile(conn, "CN23_01", dateAsString, poscd, UserName, Grade, langiangan, masothue);
                if (conn != null) {
                    conn.close();
                }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CN23_01: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }        
            return SUCCESS;
    }
    
    
    public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY");
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
