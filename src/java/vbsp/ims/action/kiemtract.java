/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;

public class kiemtract extends ActionSupport {

    private List<vbsp.ims.model.kiemtract> lst;
    private Double pgtotal;
    private int numpage;
    private int start;
    private int end;
    private String mapgd;
    private String ngaybc;

    public kiemtract() {
    }

    @Override
    public String execute() throws Exception {
        //Thực hiện kết nối CSDL
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement stm = con.prepareCall("{call SP_DTCHK(?,?,?,?,?,?,?,?)}");
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
        //---------------------------------------------
        if (numpage <= 0) {
            numpage = 1;
        }
        start = (numpage - 1) * (50 + 1);
        end = numpage * 50;
        stm.setInt(4, start);
        stm.setInt(5, end);
        stm.registerOutParameter(6, OracleTypes.CURSOR);
        stm.registerOutParameter(7, OracleTypes.INTEGER);
        stm.setString(8,tendn);
        stm.execute();
        ResultSet rs = (ResultSet) stm.getObject(6);
        lst = new ArrayList<>();
        pgtotal = Math.ceil(stm.getDouble(7) / 50);
        //----------------------------------------------------------
        while (rs.next()) {
            vbsp.ims.model.kiemtract obj = new vbsp.ims.model.kiemtract();
            obj.setPOS_CD(rs.getString("POS_CD"));
            obj.setDESCRIPT(rs.getString("DESCRIPT"));
            obj.setDIFFER_AMT(rs.getDouble("DIFFER_AMT"));
            obj.setVALUE_1(rs.getDouble("VALUE_1"));
            obj.setVALUE_2(rs.getDouble("VALUE_2"));
            lst.add(obj);
        }
        return "thanhcong";
    }

    public List<vbsp.ims.model.kiemtract> getLst() {
        return lst;
    }

    public void setLst(List<vbsp.ims.model.kiemtract> lst) {
        this.lst = lst;
    }

    public Double getPgtotal() {
        return pgtotal;
    }

    public void setPgtotal(Double pgtotal) {
        this.pgtotal = pgtotal;
    }

    public int getNumpage() {
        return numpage;
    }

    public void setNumpage(int numpage) {
        this.numpage = numpage;
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
