/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import oracle.jdbc.OracleTypes;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;

/**
 *
 * @author Administrator
 */
public class excheckct extends ActionSupport {

    private String Filename = "";
    private InputStream fileInputStream;
    private String mapgd;
    private String ngaybc;
    
    public excheckct() {
    }

    public String execute() throws Exception {
        Random rd = new Random();
        HttpServletRequest request = ServletActionContext.getRequest();
        String strPathSave = !request.getRealPath("/").endsWith("/")
                ?request.getRealPath("/")+"/"+ Define.M_REPORT_XLS
                :request.getRealPath("/")+ Define.M_REPORT_XLS;
        Filename = "Kiemtract" + rd.nextInt(100) + 1 + ".xlsx";
        strPathSave += Filename;
        SXSSFWorkbook hwb = new SXSSFWorkbook();
        Sheet sheet = hwb.createSheet("Kiem tra CT");
        Row rowhead = sheet.createRow((int) 0);
        rowhead.createCell((int) 0).setCellValue("Mã đơn vị");
        rowhead.createCell((int) 1).setCellValue("Ghi chú");
        rowhead.createCell((int) 2).setCellValue("Giá trị 1");
        rowhead.createCell((int) 3).setCellValue("Giá trị 2");
        rowhead.createCell((int) 4).setCellValue("Chênh lệch");
        //Thực hiện kết nối CSDL
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement stm = con.prepareCall("{call SP_DTCHK_EXP(?,?,?,?,?)}");
        Map session = ActionContext.getContext().getSession();
        String capbc = (String) session.get("reportGrade");
        //---------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        String tendn = (String) session.get("username");
        if(mapgd.equals("-1")){
            mapgd="";
        }
        //-------------------------------------------------------------
        stm.setString(1, capbc);
        stm.setString(2, mapgd);
        stm.setString(3, ngaybc);
        stm.setString(4,tendn);
        stm.registerOutParameter(5, OracleTypes.CURSOR);
        stm.execute();
        ResultSet rs = (ResultSet) stm.getObject(5);
        long i = 1;
        while (rs.next()) {
            Row row = sheet.createRow((int) i);
            row.createCell((int) 0).setCellValue(rs.getString("POS_CD"));
            row.createCell((int) 1).setCellValue(rs.getString("DESCRIPT"));
            row.createCell((int) 2).setCellValue(rs.getDouble("VALUE_1"));
            row.createCell((int) 3).setCellValue(rs.getDouble("VALUE_2"));
            row.createCell((int) 4).setCellValue(rs.getDouble("DIFFER_AMT"));
            i++;
        }
        FileOutputStream fileOut = new FileOutputStream(strPathSave);
        hwb.write(fileOut);
        fileOut.close();
        fileInputStream = new FileInputStream(new File(strPathSave));
        return "thanhcong";
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String Filename) {
        this.Filename = Filename;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public String getNgaybc() {
        return ngaybc;
    }

    public void setNgaybc(String ngaybc) {
        this.ngaybc = ngaybc;
    }
    
}
