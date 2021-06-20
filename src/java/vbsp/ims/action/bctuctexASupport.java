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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.jasper.ExportJasperReport;

public class bctuctexASupport extends ActionSupport {

    // Khai báo các tham s? d?u vào
    private String P_Capbc;
    private String P_Mabc;
    private String P_Macn;
    private String P_Mapgd;
    private String P_Ngaybc;
    private ArrayList<String> P_Para1 = new ArrayList<>();
    private ArrayList<String> P_Para2 = new ArrayList<>();
    private String PDFFile;
    private InputStream fileInputStream;
    private String nfile = "";
    private Map session;

    public bctuctexASupport() {
    }

    @Override
    public String execute() throws Exception {
        // Xu ly lay mot so thon tin chung
        session = ActionContext.getContext().getSession();
        P_Capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");
        // Th?c hi?n k?t n?i CSDL
        Connection con = null;
        DaoConnect db = new DaoConnect();
        con = db.getConnect();
        //Export ra file PDF và tr? v? cho trang viewPDF
        HttpServletRequest request = ServletActionContext.getRequest();
        String strPathSave = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        String Fname = "";
        String Fout = "";
        String Fjrxml = "";
        String Fjasper = "";
        // Chuan hoa cap bao cao
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "'");
        String Mdonvi = "";
        while (rs.next()) {
            Mdonvi = rs.getString("ND_MADV");
        }

        Statement stm1 = con.createStatement();
        ResultSet rs1 = stm1.executeQuery("select PO_MACN from DMPOS where PO_STATUS='O' AND PO_MA = '" + Mdonvi + "'");
        String MMacn = "";
        while (rs1.next()) {
            MMacn = rs1.getString("PO_MACN");
        }

        if (P_Mapgd.equals("-1")) {
            P_Mapgd = "";
        }
        if (P_Capbc.equals("1")) {
            P_Macn = MMacn;
            P_Mapgd = Mdonvi;
        }
        if (P_Capbc.equals("2")) {
            P_Macn = MMacn;
        }
        if (P_Capbc.equals("3")) {
            P_Macn = P_Mapgd;
            P_Mapgd = "";
        }

        parameters.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);

        ResultSet Rsul = stm.executeQuery("SELECT PARA_BCJAS,PARA_CTRINH,FILE_JAS FROM BCTUCT_THAMSOBC WHERE MABC = '" + P_Mabc.trim() + "'");

        while (Rsul.next()) {
            Random range = new Random();
            int random = range.nextInt(99 - 1) + 1;
            Fname = "EXPORT_REPORT/PDF/" + Rsul.getString("FILE_JAS").trim() + random + ".pdf";
            Fout = strPathSave + "/EXPORT_REPORT/PDF/" + Rsul.getString("FILE_JAS").trim() + random + ".pdf";
            Fjrxml = strPathSave + "/REPORTS/" + Rsul.getString("FILE_JAS").trim() + ".jrxml";
            Fjasper = strPathSave + "/REPORTS/" + Rsul.getString("FILE_JAS").trim() + ".jasper";
            String Para_Ctrinh = Rsul.getString("PARA_CTRINH").trim();
            String Para_jas = "";
            switch (Para_Ctrinh) {
                case "P_Capbc":
                    Para_jas = P_Capbc;
                    break;
                case "P_Mabc":
                    Para_jas = P_Mabc;
                    break;
                case "P_Macn":
                    Para_jas = P_Macn;
                    break;
                case "P_Mapgd":
                    Para_jas = P_Mapgd;
                    break;
                case "P_Ngaybc":
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = sdf.parse(P_Ngaybc);
                    DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                    Para_jas = df3.format(date);
                    break;
                }
                case "P_Para1":
                    Para_jas = P_Para1.toString();
                    break;
                case "P_Para2":
                    Para_jas = P_Para2.toString();
                    break;
            }
            Para_jas = Para_jas.replace(" ", "");
            Para_jas = Para_jas.replace("[", "");
            Para_jas = Para_jas.replace("]", "");
            parameters.put(Rsul.getString("PARA_BCJAS").trim(),Para_jas);
        }
        ExportJasperReport exp = new ExportJasperReport();
        exp.ExportJasperPdf(Fjrxml, parameters, con, Fout);
        PDFFile = Fname.replace("/", "/");
        
        return SUCCESS;
    }

    public String getP_Mabc() {
        return P_Mabc;
    }

    public void setP_Mabc(String P_Mabc) {
        this.P_Mabc = P_Mabc;
    }

    public String getP_Mapgd() {
        return P_Mapgd;
    }

    public void setP_Mapgd(String P_Mapgd) {
        this.P_Mapgd = P_Mapgd;
    }

    public String getP_Ngaybc() {
        return P_Ngaybc;
    }

    public void setP_Ngaybc(String P_Ngaybc) {
        this.P_Ngaybc = P_Ngaybc;
    }

    public ArrayList<String> getP_Para1() {
        return P_Para1;
    }

    public void setP_Para1(ArrayList<String> P_Para1) {
        this.P_Para1 = P_Para1;
    }

    public ArrayList<String> getP_Para2() {
        return P_Para2;
    }

    public void setP_Para2(ArrayList<String> P_Para2) {
        this.P_Para2 = P_Para2;
    }

    public String getPDFFile() {
        return PDFFile;
    }

    public void setPDFFile(String PDFFile) {
        this.PDFFile = PDFFile;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getNfile() {
        return nfile;
    }

    public void setNfile(String nfile) {
        this.nfile = nfile;
    }

    public String getP_Capbc() {
        return P_Capbc;
    }

    public void setP_Capbc(String P_Capbc) {
        this.P_Capbc = P_Capbc;
    }

    public String getP_Macn() {
        return P_Macn;
    }

    public void setP_Macn(String P_Macn) {
        this.P_Macn = P_Macn;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String DownExcel() throws JRException, FileNotFoundException, SQLException, ParseException {
        // Xu ly lay mot so thon tin chung
        session = ActionContext.getContext().getSession();
        P_Capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");
        // Th?c hi?n k?t n?i CSDL
        Connection con = null;
        DaoConnect db = new DaoConnect();
        con = db.getConnect();
        //Export ra file PDF và tr? v? cho trang viewPDF
        HttpServletRequest request = ServletActionContext.getRequest();
        String strPathSave = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        String Fout = "";
        String Fjrxml = "";
        String Fjasper = "";
        // Chuan hoa cap bao cao
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select ND_MADV from NG_DUNG where ND_MA = '" + tendn + "'");
        String Mdonvi = "";
        while (rs.next()) {
            Mdonvi = rs.getString("ND_MADV");
        }

        Statement stm1 = con.createStatement();
        ResultSet rs1 = stm1.executeQuery("select PO_MACN from DMPOS where PO_STATUS='O' AND PO_MA = '" + Mdonvi + "'");
        String MMacn = "";
        while (rs1.next()) {
            MMacn = rs1.getString("PO_MACN");
        }

        if (P_Mapgd.equals("-1")) {
            P_Mapgd = "";
        }
        if (P_Capbc.equals("1")) {
            P_Macn = MMacn;
            P_Mapgd = Mdonvi;
        }
        if (P_Capbc.equals("2")) {
            P_Macn = MMacn;
        }
        if (P_Capbc.equals("3")) {
            P_Macn = P_Mapgd;
            P_Mapgd = "";
        }

        parameters.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);
        
        ResultSet Rsul = stm.executeQuery("SELECT PARA_BCJAS,PARA_CTRINH,FILE_JAS FROM BCTUCT_THAMSOBC WHERE MABC = '" + P_Mabc.trim() + "'");

        while (Rsul.next()) {
            Fout = strPathSave + "/EXPORT_REPORT/PDF/" + Rsul.getString("FILE_JAS").trim() + ".xlsx";
            Fjrxml = strPathSave + "/REPORTS/" + Rsul.getString("FILE_JAS").trim() + ".jrxml";
            Fjasper = strPathSave + "/REPORTS/" + Rsul.getString("FILE_JAS").trim() + ".jasper";
            String Para_Ctrinh = Rsul.getString("PARA_CTRINH").trim();
            String Para_jas = "";
            switch (Para_Ctrinh) {
                case "P_Capbc":
                    Para_jas = P_Capbc;
                    break;
                case "P_Mabc":
                    Para_jas = P_Mabc;
                    break;
                case "P_Macn":
                    Para_jas = P_Macn;
                    break;
                case "P_Mapgd":
                    Para_jas = P_Mapgd;
                    break;
                case "P_Ngaybc":
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = sdf.parse(P_Ngaybc);
                    DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                    Para_jas = df3.format(date);
                    break;
                case "P_Para1":
                    Para_jas = P_Para1.toString();
                    break;
                case "P_Para2":
                    Para_jas = P_Para2.toString();
                    break;
            }
            Para_jas = Para_jas.replace(" ", "");
            Para_jas = Para_jas.replace("[", "");
            Para_jas = Para_jas.replace("]", "");
            parameters.put(Rsul.getString("PARA_BCJAS").trim(),Para_jas);
        }
        
        ExportJasperReport exp = new ExportJasperReport();
        exp.ExportJasperExcel(Fjrxml, parameters, con, Fout);

        Random range = new Random();
        int random = range.nextInt(99 - 1) + 1;
        nfile = "BCTCT" + P_Mapgd + new DecimalFormat("00").format(random) + ".xlsx";
        fileInputStream = new FileInputStream(new File(Fout));

        return SUCCESS;
    }
}
