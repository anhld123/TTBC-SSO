package vbsp.ims.nhaptaycn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dmctieu.dmctieu_Model;

public class HANOI_004 extends ActionSupport {

    private InputStream fileInputStream;
    private Map session;
    private File Mfile;
    private String Filename, capbc, strPathSave, tendn, myFileContentType, myFileFileName, Para1, Para2, Para3, Para4, Para5, Para6, Para7, Para8, Para9, ext1, ext2, ext3, ext4, ext5, ext6, ext7, ext8, ext9, dtNgayBC, strMaxa;
    Integer indexItem = 0;
    Integer inext = 0;
    HttpServletRequest request;

    @Override
    public String execute() throws Exception {
        tendn = (String) ActionContext.getContext().getSession().get("username");

        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        System.out.println("HANOI_04");
        try {
            CallableStatement st = con.prepareCall("{call PROC_HN004_SELECT(?,?,?,?)}");
            st.setString(1, dtNgayBC);
            st.setString(2, strMaxa);
            st.setString(3, tendn);
            st.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(4);
            Random rd = new Random();
            request = ServletActionContext.getRequest();
            strPathSave = !request.getRealPath("/").endsWith("/") ? (request.getRealPath("/") + "/" + "EXPORT_REPORT/XLS/") : (request.getRealPath("/") + "EXPORT_REPORT/XLS/");
            Filename = "GQVLHN004" + rd.nextInt(100) + tendn + ".xlsx";
            String Filetmp = !request.getRealPath("/").endsWith("/") ? (request.getRealPath("/") + "/") : request.getRealPath("/");
            Filetmp = Filetmp + "/EXCEL_TEMPLATE/";
            File sourceFile = new File(Filetmp, "GQVLHN004TMP.xlsx");
            XSSFWorkbook wb_template;
            try (FileInputStream inputStream = new FileInputStream(sourceFile)) {
                wb_template = new XSSFWorkbook(inputStream);
            }
            SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
            wb.setCompressTempFiles(true);
            SXSSFSheet sh = wb.getSheetAt(0);
            sh.setRandomAccessWindowSize(100);
            Integer rownum = 1;
            while (rs.next()) {
                SXSSFRow sXSSFRow = sh.createRow(rownum);
                for (int cellnum = 0; cellnum < 50; cellnum++) {
                    Cell cell = sXSSFRow.createCell(cellnum);
                    switch (cellnum) {
                        case 0:
                            cell.setCellValue(rs.getString("MAPGD"));
                            break;
                        case 1:
                            cell.setCellValue(rs.getString("MAXA"));
                            break;
                        case 2:
                            cell.setCellValue(rs.getString("MATO"));
                            break;
                        case 3:
                            cell.setCellValue(rs.getString("TENTT"));
                            break;
                        case 4:
                            cell.setCellValue(rs.getString("MAKH"));
                            break;
                        case 5:
                            cell.setCellValue(rs.getString("TENKH"));
                            break;
                        case 6:
                            cell.setCellValue(rs.getString("SOKU"));
                            break;
                        case 7:
                            cell.setCellValue(rs.getString("MUCDICH"));
                            break;
                        case 8:
                            cell.setCellValue(rs.getString("NGAYBC"));
                            break;
                        case 9:
                            cell.setCellValue(rs.getString("EXT1"));
                            break;
                        case 10:
                            cell.setCellValue(rs.getString("EXT2"));
                            break;
                        case 11:
                            cell.setCellValue(rs.getString("EXT3"));
                            break;
                        case 12:
                            cell.setCellValue(rs.getString("EXT4"));
                            break;
                        case 13:
                            cell.setCellValue(rs.getString("EXT5"));
                            break;
                        case 14:
                            cell.setCellValue(rs.getString("EXT6"));
                            break;
                        case 15:
                            cell.setCellValue(rs.getString("EXT7"));
                            break;
                        case 16:
                            cell.setCellValue(rs.getString("EXT8"));
                            break;
                        case 17:
                            cell.setCellValue(rs.getString("EXT9"));
                            break;
                    }
                }
                rownum++;
            }
            try (FileOutputStream out = new FileOutputStream(strPathSave + Filename)) {
                wb.write(out);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(dmctieu_Model.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        fileInputStream = new FileInputStream(new File(strPathSave + Filename));
        return "success";
    }

    public String HN04UploadFile() throws Exception {
        Para1 = Para2 = Para3 = Para4 = Para5 = Para6 = Para7 = Para8 = Para9 = "";
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        HttpServletRequest request = ServletActionContext.getRequest();
        String destPath = !request.getRealPath("/").endsWith("/") ? (request.getRealPath("/") + "/" + "EXPORT_REPORT/XLS/") : (request.getRealPath("/") + "EXPORT_REPORT/XLS/");
        Random rd = new Random();
        myFileFileName = "HNO04TMP" + rd.nextInt(100) + ".xlsx";
        File destFile = new File(destPath, myFileFileName);
        FileUtils.copyFile(Mfile, destFile);
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        FileInputStream inputStream = new FileInputStream(new File(destPath + myFileFileName));
        XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = xSSFWorkbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        inext = 0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (inext == 0) {
                nextRow = iterator.next();
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            indexItem = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (indexItem) {
                    case 0:
                        Para1 = cell.getStringCellValue();
                        break;
                    case 1:
                        Para2 = cell.getStringCellValue();
                        break;
                    case 2:
                        Para3 = cell.getStringCellValue();
                        break;
                    case 3:
                        Para4 = cell.getStringCellValue();
                        break;
                    case 4:
                        Para5 = cell.getStringCellValue();
                        break;
                    case 5:
                        Para6 = cell.getStringCellValue();
                        break;
                    case 6:
                        Para7 = cell.getStringCellValue();
                        break;
                    case 7:
                        Para8 = cell.getStringCellValue();
                        break;
                    case 8:
                        Para9 = cell.getStringCellValue();
                        break;
                    case 9:
                        ext1 = cell.getStringCellValue();
                        break;
                     case 10:
                        ext2 = cell.getStringCellValue();
                        break;
                    case 11:
                        ext3 = cell.getStringCellValue();
                        break;
                    case 12:
                        ext4 = cell.getStringCellValue();
                        break;
                    case 13:
                        ext5 = cell.getStringCellValue();
                        break;
                    case 14:
                        ext6 = cell.getStringCellValue();
                        break;
                    case 15:
                        ext7 = cell.getStringCellValue();
                        break;
                    case 16:
                        ext8 = cell.getStringCellValue();
                        break;
                    case 17:
                        ext9 = cell.getStringCellValue();
                        break;
                       
                }
                indexItem++;
            }
            inext++;
            CallableStatement st = con.prepareCall("{call PROC_HN004_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            st.setString(1, Para1);
            st.setString(2, Para2);
            st.setString(3, Para3);
            st.setString(4, Para4);
            st.setString(5, Para5);
            st.setString(6, Para6);
            st.setString(7, Para7);
            st.setString(8, Para8);
            st.setString(9, Para9);
            st.setString(10, ext1);
            st.setString(11, ext2);
            st.setString(12, ext3);
            st.setString(13, ext4);
            st.setString(14, ext5);
            st.setString(15, ext6);
            st.setString(16, ext7);
            st.setString(17, ext8);
            st.setString(18, ext9);
            
            st.execute();
        }
        return "success";
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public File getMfile() {
        return Mfile;
    }

    public void setMfile(File Mfile) {
        this.Mfile = Mfile;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String Filename) {
        this.Filename = Filename;
    }

    public String getCapbc() {
        return capbc;
    }

    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }

    public String getStrPathSave() {
        return strPathSave;
    }

    public void setStrPathSave(String strPathSave) {
        this.strPathSave = strPathSave;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String getPara1() {
        return Para1;
    }

    public void setPara1(String Para1) {
        this.Para1 = Para1;
    }

    public String getPara2() {
        return Para2;
    }

    public void setPara2(String Para2) {
        this.Para2 = Para2;
    }

    public String getPara3() {
        return Para3;
    }

    public void setPara3(String Para3) {
        this.Para3 = Para3;
    }

    public String getPara4() {
        return Para4;
    }

    public void setPara4(String Para4) {
        this.Para4 = Para4;
    }

    public String getPara5() {
        return Para5;
    }

    public void setPara5(String Para5) {
        this.Para5 = Para5;
    }

    public String getPara6() {
        return Para6;
    }

    public void setPara6(String Para6) {
        this.Para6 = Para6;
    }

    public String getPara7() {
        return Para7;
    }

    public void setPara7(String Para7) {
        this.Para7 = Para7;
    }

    public String getPara8() {
        return Para8;
    }

    public void setPara8(String Para8) {
        this.Para8 = Para8;
    }

    public String getPara9() {
        return Para9;
    }

    public void setPara9(String Para9) {
        this.Para9 = Para9;
    }

    public String getDtNgayBC() {
        return dtNgayBC;
    }

    public void setDtNgayBC(String dtNgayBC) {
        this.dtNgayBC = dtNgayBC;
    }

    public String getStrMaxa() {
        return strMaxa;
    }

    public void setStrMaxa(String strMaxa) {
        this.strMaxa = strMaxa;
    }

    public Integer getIndexItem() {
        return indexItem;
    }

    public void setIndexItem(Integer indexItem) {
        this.indexItem = indexItem;
    }

    public Integer getInext() {
        return inext;
    }

    public void setInext(Integer inext) {
        this.inext = inext;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
