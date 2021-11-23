/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.huydongtk;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.eps.epsAction;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;

/**
 *
 * @author WELCOME
 */
public class HDTKRestApi {

    public Map<String, String> getPosCode(String tendn) {
        Map<String, String> lstPosCode = new HashMap<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GET_POS_CODE_BY_USER(?,?,?)}");
            st.setString(1, tendn);
            st.registerOutParameter(2, OracleTypes.VARCHAR);
            st.registerOutParameter(3, OracleTypes.VARCHAR);
            st.execute();
            lstPosCode.put("macn",st.getString(2));
            lstPosCode.put("mapgd",st.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPosCode;
    }
        
    public int insertHDTK(String sNgaybc, String sUser,String sGrade,String cbocanbo, String chitieu, ArrayList<String> chkChon) {

        Map<String, String> lsPosCode = new HashMap<>();
        lsPosCode = getPosCode(sUser);
        
        DuLieuNTService service = new DuLieuNTService();
      
        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
        for (String chkChon1 : chkChon) { 
            DuLieuNTRow testItem = new DuLieuNTRow();
            testItem.setKey("CB_HUYDONGTK");
            testItem.setName("HDTK");
            testItem.setCode(cbocanbo);
            testItem.setReportDate(sNgaybc + "T00:00:00");
            testItem.setPosCode(lsPosCode.get("mapgd"));
            testItem.setPosFlag("S");
            testItem.setD1(chkChon1);
            testItem.setD2(cbocanbo);
            testItem.setD3("1");
            testItem.setD4(chitieu);
            testItem.setReportYear(Integer.parseInt(sNgaybc.substring(1, 4)));
            testItem.setBranchCode(lsPosCode.get("macn"));
            lstUpdateDate.add(testItem);
        }

        int status = service.insertData("trungnt88","", lstUpdateDate);
        return status;
    }

}
