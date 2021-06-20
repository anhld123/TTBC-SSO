 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
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
public class expExcelgiatrichitieu extends ActionSupport {

    private String Filename = "";
    private InputStream fileInputStream;
    private String paraxuly;

    public String getParaxuly() {
        return paraxuly;
    }

    public void setParaxuly(String paraxuly) {
        this.paraxuly = paraxuly;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String Filename) {
        this.Filename = Filename;
    }

    public expExcelgiatrichitieu() {
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String execute() throws Exception {
        try {
            Random rd = new Random();
            HttpServletRequest request = ServletActionContext.getRequest();
            String strPathSave = !request.getRealPath("/").endsWith("/")
                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XLS
                    : request.getRealPath("/") + Define.M_REPORT_XLS;
            Filename = "Giatrict" + rd.nextInt(100) + 1 + ".xlsx";
            strPathSave += Filename;

            SXSSFWorkbook hwb = new SXSSFWorkbook();
            Sheet sheet = hwb.createSheet("GIATRICT");
            Row rowhead = sheet.createRow((int) 0);
            rowhead.createCell((int) 0).setCellValue("MÃ CHỈ TIÊU");
            rowhead.createCell((int) 1).setCellValue("TÊN CHỈ TIÊU");
            rowhead.createCell((int) 2).setCellValue("GIÁ TRỊ");
            rowhead.createCell((int) 3).setCellValue("NGÀY BÁO CÁO");
            rowhead.createCell((int) 4).setCellValue("MÃ PGD");
            rowhead.createCell((int) 5).setCellValue("MÃ CN");
            // Thực hiện kết nối CSDL
            DaoConnect db = new DaoConnect();
            Connection con = db.getConnect();
            Statement st = con.createStatement();
            // Nếu là truy vấn thì dùng bảng này
            String MSQL = "";
            if (paraxuly == "truyvan") {
                MSQL = "Select * from TEMP_SELECTINDICATOR";
            } else {
                MSQL = "Select * from temp_indicator";
            }
            //TEMP_SELECTINDICATOR
            ResultSet rs = st.executeQuery(MSQL);
            long i = 1;
            while (rs.next()) {
                Row row = sheet.createRow((int) i);
                row.createCell((int) 0).setCellValue(rs.getString("NDATA_1"));
                row.createCell((int) 1).setCellValue(rs.getString("NDATA_2"));
                row.createCell((int) 2).setCellValue(Double.valueOf(rs.getString("NDATA_3")));
                row.createCell((int) 3).setCellValue(rs.getString("NDATA_4"));
                row.createCell((int) 4).setCellValue(rs.getString("NDATA_5"));
                row.createCell((int) 5).setCellValue(rs.getString("NDATA_6"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(strPathSave);
            hwb.write(fileOut);
            fileOut.close();
            fileInputStream = new FileInputStream(new File(strPathSave));
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return "thanhcong";
    }

}
