/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import vbsp.ims.ctieu.ChiTieu;
import com.opensymphony.xwork2.ActionSupport;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.ConnectDatabase;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Administrator
 */
public class viewIndicatorReport extends ActionSupport {

    private String idReport;
    private ResultSet rs;
    private ResultSet rs1;
    private boolean getpara1 = false;
    private boolean getpara2 = false;
    private List<ChiTieu> loadDanhmuclienquan1;
    private List<ChiTieu> loadDanhmuclienquan2;
    private List<String> listabc;

    public List<String> getListabc() {
        return listabc;
    }

    public void setListabc(List<String> listabc) {
        this.listabc = listabc;
    }

    public viewIndicatorReport() {
    }

    public List<ChiTieu> getLoadDanhmuclienquan1() {
        return loadDanhmuclienquan1;
    }

    public void setLoadDanhmuclienquan1(List<ChiTieu> loadDanhmuclienquan1) {
        this.loadDanhmuclienquan1 = loadDanhmuclienquan1;
    }

    public List<ChiTieu> getLoadDanhmuclienquan2() {
        return loadDanhmuclienquan2;
    }

    public void setLoadDanhmuclienquan2(List<ChiTieu> loadDanhmuclienquan2) {
        this.loadDanhmuclienquan2 = loadDanhmuclienquan2;
    }

    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
    }

    public boolean isGetpara1() {
        return getpara1;
    }

    public void setGetpara1(boolean getpara1) {
        this.getpara1 = getpara1;
    }

    public boolean isGetpara2() {
        return getpara2;
    }

    public void setGetpara2(boolean getpara2) {
        this.getpara2 = getpara2;
    }

    @Override
    public String execute() throws Exception {
        //ket noi
        DaoConnect db = new DaoConnect();
        loadDanhmuclienquan1 = new ArrayList<ChiTieu>();
        loadDanhmuclienquan2 = new ArrayList<ChiTieu>();
        listabc = new ArrayList<String>();

        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        dbConnection = db.getConnect();

        String CursorSql = "{call P_INDICATOR_REPORT(?,?,?)}";
        callableStatement = dbConnection.prepareCall(CursorSql);
        callableStatement.setString(1, idReport); // Ma bao cao khi click chon tren combo
        callableStatement.registerOutParameter(2, OracleTypes.CURSOR); // danh muc lien quan 1
        callableStatement.registerOutParameter(3, OracleTypes.CURSOR); // danh muc lien quan 2
        // execute DIVIDE_PAGES store procedure
        callableStatement.executeUpdate();
        rs = (ResultSet) callableStatement.getObject(2);
        while (rs.next()) {
            loadDanhmuclienquan1.add(new ChiTieu(rs.getString("KHOA"), rs.getString("GIATRI")));
        }
        if (loadDanhmuclienquan1.size() > 0) {
            getpara1 = true;
        } else {
            getpara1 = false;
        }
        rs1 = (ResultSet) callableStatement.getObject(3);
        while (rs1.next()) {
            loadDanhmuclienquan2.add(new ChiTieu(rs1.getString("KHOA"), rs1.getString("GIATRI")));
        }
        if (loadDanhmuclienquan2.size() > 0) {
            getpara2 = true;
        } else {
            getpara2 = false;
        }
        return "hienthi";
    }

}
