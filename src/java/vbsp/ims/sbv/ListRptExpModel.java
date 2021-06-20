/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sbv;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author BAOANH
 */
public class ListRptExpModel {
    String file_excel;
    String ten_bc;
    String ma_bc;
    String ten_ky;
    String ma_ky;
    String capbc;
    List<String> lstSheetName;
    
    private String getExtendFile(String file_excel) throws Exception {
        if (file_excel.indexOf(".") < 0) {
            throw new Exception("Not found extend file " + file_excel);
        }
        String extendFile = file_excel.substring(file_excel.lastIndexOf(".") + 1, file_excel.length());
        if (!extendFile.equals("xlsx") && !extendFile.equals("xls")) {
            throw new Exception("Incorrect file excel template extend. Extend file only xlsx or xls ");
        }
        return extendFile;
    }

    /**
     * ham nay get ra tat ca cac sheet trong file excel
     *
     * @param file_excel duong dan day du toi file excel
     * @return
     * @throws Exception
     */
    public List<String> getSheetName(String file_excel) throws Exception {
        List<String> lstSheet = new ArrayList<>();
        String ext = getExtendFile(file_excel);
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        if (ext.toLowerCase().equals("xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(file_excel));
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(file_excel));
        }
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            String sheet = workbook.getSheetName(i);
            lstSheet.add(sheet);
            System.err.println("sheet=" + sheet);
        }
        return lstSheet;
    }

    public List<String> getLstSheetName() {
        return lstSheetName;
    }

    public void setSheetName(String file_excel) throws Exception {
        this.lstSheetName = getSheetName(file_excel);
    }

    public void setLstSheetName(List<String> lstSheetName) {
        this.lstSheetName = lstSheetName;
    }
    
    
    public String getFile_excel() {
        return file_excel;
    }

    public void setFile_excel(String file_excel) {
        this.file_excel = file_excel;
    }

    public String getTen_bc() {
        return ten_bc;
    }

    public void setTen_bc(String ten_bc) {
        this.ten_bc = ten_bc;
    }

    public String getMa_bc() {
        return ma_bc;
    }

    public void setMa_bc(String ma_bc) {
        this.ma_bc = ma_bc;
    }

    public String getTen_ky() {
        return ten_ky;
    }

    public void setTen_ky(String ten_ky) {
        this.ten_ky = ten_ky;
    }

    public String getMa_ky() {
        return ma_ky;
    }

    public void setMa_ky(String ma_ky) {
        this.ma_ky = ma_ky;
    }

    public String getCapbc() {
        return capbc;
    }

    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }
    
    
}
