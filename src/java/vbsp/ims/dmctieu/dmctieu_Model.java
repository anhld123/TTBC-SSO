/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dmctieu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;
import oracle.jdbc.OracleTypes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;

/**
 *
 * @author Administrator
 */
public class dmctieu_Model {

    //Hàm lấy danh sách Treeview
    public static void gentreeview(String Nganhang) throws ClassNotFoundException, SQLException, IOException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String Fname;
        if ("NHCS".equals(Nganhang)) {
            Fname = "tree01.js";
        } else {
            Fname = "tree02.js";
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        String Pathfile = !request.getRealPath("/").endsWith("/")
                ?request.getRealPath("/")+"/"+"/DMChitieu/js/" + Fname
                :request.getRealPath("/") + "/DMChitieu/js/" + Fname;
                
        File file = new File(Pathfile);
        String Msql = "{call GETTREE_CT(?,?,?)}";
        CallableStatement st = con.prepareCall(Msql);
        st.setString(1, Nganhang);
        st.registerOutParameter(2, OracleTypes.INTEGER);
        st.registerOutParameter(3, OracleTypes.CURSOR);
        st.executeQuery();
        Integer maxtree = (Integer) st.getObject(2);
        ResultSet rs = (ResultSet) st.getObject(3);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Pathfile), "UTF8"));
        out.write("var setting = {view: {showIcon: showIconForTree},data: {simpleData: {enable: true}},callback: {onClick: getvalue}};function showIconForTree(treeId, treeNode) {return !treeNode.isParent;};function getvalue(event, treeId, treeNode) {document.getElementById(\"cbotrang\").value=1;document.getElementById(\"txtmact\").value = treeNode.value;};$(document).ready(function () {$.fn.zTree.init($(\"#treechitieu\"), setting, zNodes);});var zNodes = [");
        Integer Recno = 0;
        while (rs.next()) {
            Recno++;
            String mfile = "";
            if (rs.getInt("PARENT") == 0) {
                mfile = "{id: " + rs.getInt("ID") + ", pId: " + rs.getInt("PARENT") + ", value: \"" + rs.getString("CHITIEU") + "\", name: \"" + rs.getString("CHITIEU") + " - " + rs.getString("NAME") + "\", open: true},";
            } else if (!Recno.equals(maxtree) && rs.getInt("PARENT") != 0) {
                mfile = "{id: " + rs.getInt("ID") + ", pId: " + rs.getInt("PARENT") + ", value: \"" + rs.getString("CHITIEU") + "\", name: \"" + rs.getString("CHITIEU") + " - " + rs.getString("NAME") + "\"},";
            } else {
                mfile = "{id: " + rs.getInt("ID") + ", pId: " + rs.getInt("PARENT") + ", value: \"" + rs.getString("CHITIEU") + "\", name: \"" + rs.getString("CHITIEU") + " - " + rs.getString("NAME") + "\"}";
            }
            out.write(mfile);
        };
        out.write("];");
        out.close();
    }

    //Hàm lấy danh sách đon vị theo tên đăng nhâp.
    public List<dmctieu_util> getdsdonvi(String capbc, String tendn) {
        List<dmctieu_util> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            CallableStatement st = con.prepareCall("{call app_priv_view.p_get_priv_4indi(?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                dmctieu_util obj = new dmctieu_util();
                obj.setMADV(rs.getString("PO_MA"));
                obj.setTENDV(rs.getString("PO_TEN"));
                lst.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dmctieu_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    //Hàm lấy kỳ báo cáo - chỉ áp dụng cho NHNN

    public List<dmctieu_util> getdskybc(String nganhang) {
        List<dmctieu_util> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            CallableStatement st = con.prepareCall("{call GET_DMKYBC(?,?)}");
            st.setString(1, nganhang);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(2);
            while (rs.next()) {
                dmctieu_util obj = new dmctieu_util();
                obj.setKYBC(rs.getString("KYBC"));
                obj.setTENKYBC(rs.getString("TENKYBC"));
                lst.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(dmctieu_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    //Hàm lấy danh sách chỉ tiêu khi chọn trên Case

    public List<dmctieu_util> getnhombc(String nganhang, String capbc, String madv, String tendn, String nhomct, String ngaybc, Integer nstart, Integer nend, String nkybc, Holder<Double> reccount, Holder<Double> sumgiatr) {
        List<dmctieu_util> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GETALLNHOMCT(?,?,?,?,?,?,?,?,?,?,?,?)}");
            st.setString(1, nganhang);
            st.setString(2, capbc);
            st.setString(3, madv);
            st.setString(4, tendn);
            st.setString(5, nhomct);
            st.setString(6, ngaybc);
            st.setInt(7, nstart);
            st.setInt(8, nend);
            st.setString(9, nkybc);
            st.registerOutParameter(10, OracleTypes.INTEGER);
            st.registerOutParameter(11, OracleTypes.INTEGER);
            st.registerOutParameter(12, OracleTypes.CURSOR);
            st.execute();
            reccount.value = st.getDouble(10);
            sumgiatr.value = st.getDouble(11);
            ResultSet rs = (ResultSet) st.getObject(12);
            while (rs.next()) {
                dmctieu_util obj = new dmctieu_util();
                obj.setCT_MACT(rs.getString("CT_MACT"));
                obj.setTENCT(rs.getString("TENCT"));
                obj.setCT_GIATRI(rs.getDouble("CT_GIATRI"));
                obj.setCT_NGAYBC(rs.getString("CT_NGAYBC"));
                obj.setCT_MAPGD(rs.getString("CT_MAPGD"));
                obj.setCT_MACN(rs.getString("CT_MACN"));
                lst.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(dmctieu_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    //Hàm xuất Excel chỉ tiêu

    public String xuatexcelct(String nganhang, String capbc, String madv, String tendn, String nhomct, String ngaybc, String nkybc, Holder<String> FPfile) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String PFile = "";
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_EXCHITIEU(?,?,?,?,?,?,?,?)}");
            st.setString(1, nganhang);
            st.setString(2, capbc);
            st.setString(3, madv);
            st.setString(4, tendn);
            st.setString(5, nhomct);
            st.setString(6, ngaybc);
            st.setString(7, nkybc);
            st.registerOutParameter(8, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(8);
            // Thực hiện lấy đường dẫn File
            Random rd = new Random();
            HttpServletRequest request = ServletActionContext.getRequest();
            String strPathSave = !request.getRealPath("/").endsWith("/")
                                 ?request.getRealPath("/")+"/"+ Define.M_REPORT_XLS
                                 :request.getRealPath("/")+ Define.M_REPORT_XLS;
            String Filename = "ExChitieu" + rd.nextInt(100) + 1 + ".xlsx";
            FPfile.value = Filename;
            strPathSave += Filename;
            String Filetmp = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");
                   Filetmp+= "/DMChitieu/FTem/TMPFile.xlsx";
            FileInputStream inputStream = new FileInputStream(Filetmp);
            XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
            inputStream.close();
            SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
            wb.setCompressTempFiles(true);
            SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
            sh.setRandomAccessWindowSize(100);
            Integer rownum = 1;
            while (rs.next()) {
                Row row = sh.createRow(rownum);
                for (int cellnum = 0; cellnum < 6; cellnum++) {
                    Cell cell = row.createCell(cellnum);
                    switch (cellnum) {
                        case 0:
                            cell.setCellValue(rs.getString("CT_MACT"));
                            break;
                        case 1:
                            cell.setCellValue(rs.getString("TENCT"));
                            break;
                        case 2:
                            cell.setCellValue(rs.getDouble("CT_GIATRI"));
                            break;
                        case 3:
                            cell.setCellValue(rs.getString("CT_NGAYBC"));
                            break;
                        case 4:
                            cell.setCellValue(rs.getString("CT_MAPGD"));
                            break;
                        case 5:
                            cell.setCellValue(rs.getString("CT_MACN"));
                            break;
                    }
                }
                rownum++;
            }
            FileOutputStream out = new FileOutputStream(strPathSave);
            wb.write(out);
            out.close();
            PFile = strPathSave;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(dmctieu_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PFile;
    }
}
