/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ctieu;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Administrator
 */
public class Excttotxt extends ActionSupport {

    private String nfile = "";
    private InputStream fileInputStream;
    private String loaict;

    public Excttotxt() {
    }

    public String execute() throws Exception {
        // Lấy đường dẫn trên server
        HttpServletRequest request = ServletActionContext.getRequest();
        String strPathSave = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");
        // Kết nối CSDL để xuất File Txt
        Connection DBConn = null;
        Statement rsdata = null;
        CallableStatement Statement = null;
        ResultSet rsgetdata = null;

        DaoConnect db = new DaoConnect();
        DBConn = db.getConnect();

        String Msql = "";
        if (loaict.equals("getct")) {
            Msql = "select * from temp_indicator";
        } else {
            Msql = "select * from TEMP_SELECTINDICATOR";
        }
        rsdata = DBConn.createStatement();
        rsgetdata = rsdata.executeQuery(Msql);

        // Định nghĩa về File
        String path = strPathSave + "/chitieu/txt/Ex_Check.txt";
        File file = new File(path);

        FileWriter writer;
        writer = new FileWriter(file, false);
        String MString = "";
        try {
            while (rsgetdata.next()) {
                MString = "";
                MString = MString + rsgetdata.getString("NDATA_1") + "#";
                MString = MString + rsgetdata.getString("NDATA_3") + "#";
                MString = MString + rsgetdata.getString("NDATA_4") + "#";
                MString = MString + rsgetdata.getString("NDATA_5") + "#";
                MString = MString + rsgetdata.getString("NDATA_6") + "#";
                MString = MString + rsgetdata.getString("NDATA_7") + "\r\n";
                writer.write(MString);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nfile = "Check.txt";
        fileInputStream = new FileInputStream(new File(path));
        return SUCCESS;
    }

    public String getNfile() {
        return nfile;
    }

    public void setNfile(String nfile) {
        this.nfile = nfile;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getLoaict() {
        return loaict;
    }

    public void setLoaict(String loaict) {
        this.loaict = loaict;
    }
}
