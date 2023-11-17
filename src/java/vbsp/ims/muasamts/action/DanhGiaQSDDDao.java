/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.muasamts.action;


import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.restapi.DuLieuNTRow;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.restapi.DuLieuNTRowX;

/**
 *
 * @author HP
 */
public class DanhGiaQSDDDao {
    public int saveData(String key, String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data)
    throws SQLException, ParseException {        
        final Date _reportDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse(reportDate);        
        List<DULIEU_NT_TQ> lstData = new ArrayList<>();
        
        for(int i = 0; i < data.size(); i++) {
            final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
            obj.setKHOA(data.get(i).getKey());
            obj.setMA(data.get(i).getCode());
            obj.setTEN(data.get(i).getName());
            obj.setNGAYBC(_reportDate);
            obj.setNAMBC(data.get(i).getReportYear());
            obj.setMAPGD(data.get(i).getPosCode());
            obj.setMACN(data.get(i).getBranchCode());
            obj.setCO_TONGHOP(data.get(i).getPosFlag());            
            obj.setD1(data.get(i).getD1());
            obj.setD2(data.get(i).getD2());
            obj.setD3(data.get(i).getD3());
            obj.setD4(data.get(i).getD4());
            obj.setD5(data.get(i).getD5());
            obj.setD6(data.get(i).getD6());
            obj.setD7(data.get(i).getD7());
            obj.setD8(data.get(i).getD8());
            obj.setD9(data.get(i).getD9());
            obj.setD10(data.get(i).getD10());
            obj.setD11(data.get(i).getD11());
            obj.setD12(data.get(i).getD12());
            obj.setD13(data.get(i).getD13());
            obj.setD14(data.get(i).getD14());    
            obj.setD15(data.get(i).getD1());
            obj.setD16(data.get(i).getD2());
            obj.setD17(data.get(i).getD3());
            obj.setD18(data.get(i).getD4());
            obj.setD19(data.get(i).getD5());
            obj.setD20(data.get(i).getD6());
            obj.setD21(data.get(i).getD7());
            obj.setD22(data.get(i).getD8());
            obj.setD23(data.get(i).getD9());
            obj.setD24(data.get(i).getD10());
            obj.setD25(data.get(i).getD11());
            obj.setD26(data.get(i).getD12());
            obj.setD27(data.get(i).getD13());
            obj.setD28(data.get(i).getD14()); 
            obj.setD29(data.get(i).getD13());
            obj.setD30(data.get(i).getD14()); 
            lstData.add(obj);
        }
        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();        
      
        final Object[] array = lstData.toArray();
        final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
        final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
        CallableStatement calstatement = null;
        final String strStoreproce = "{call PK_DULIEU_NT.INSERT_DULIEU_NT(?,?,?,?,?,?,?)}";

        calstatement = con.prepareCall(strStoreproce);
        calstatement.setString(1, key);        
        calstatement.setString(2, posCode);
        calstatement.setString(3, posFlag);
        calstatement.setString(4, reportDate);
        calstatement.setString(5, makerId);
        calstatement.setArray(6, (Array) array_to_pass);        
        calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
        calstatement.execute();
        
        int _recordCnt = calstatement.getInt(7);
        
        if (calstatement != null) {
            calstatement.close();
        }                                          
        return _recordCnt;
    }
    
    public int deleteData(String key, String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data)
    throws SQLException, ParseException {        
        final Date _reportDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse(reportDate);        
        List<DULIEU_NT_TQ> lstData = new ArrayList<>();
        
        for(int i = 0; i < data.size(); i++) {
            final DULIEU_NT_TQ obj = DULIEU_NT_TQ.newInstance();
            obj.setKHOA(data.get(i).getKey());
            obj.setMA(data.get(i).getCode());
            obj.setTEN(data.get(i).getName());
            obj.setNGAYBC(_reportDate);
            obj.setNAMBC(data.get(i).getReportYear());
            obj.setMAPGD(data.get(i).getPosCode());
            obj.setMACN(data.get(i).getBranchCode());
            obj.setCO_TONGHOP(data.get(i).getPosFlag());            
            obj.setD1(data.get(i).getD1());
            obj.setD2(data.get(i).getD2());
            obj.setD3(data.get(i).getD3());
            obj.setD4(data.get(i).getD4());
            obj.setD5(data.get(i).getD5());
            obj.setD6(data.get(i).getD6());
            obj.setD7(data.get(i).getD7());
            obj.setD8(data.get(i).getD8());
            obj.setD9(data.get(i).getD9());
            obj.setD10(data.get(i).getD10());
            obj.setD11(data.get(i).getD11());
            obj.setD12(data.get(i).getD12());
            obj.setD13(data.get(i).getD13());
            obj.setD14(data.get(i).getD14());            
            lstData.add(obj);
        }
        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();        
      
        final Object[] array = lstData.toArray();
        final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
        final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
        CallableStatement calstatement = null;
        final String strStoreproce = "{call PK_DULIEU_NT.DELETE_DULIEU_NT(?,?,?,?,?,?,?)}";

        calstatement = con.prepareCall(strStoreproce);
        calstatement.setString(1, key);        
        calstatement.setString(2, posCode);
        calstatement.setString(3, posFlag);
        calstatement.setString(4, reportDate);
        calstatement.setString(5, makerId);
        calstatement.setArray(6, (Array) array_to_pass);        
        calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
        calstatement.execute();
        
        int _recordCnt = calstatement.getInt(7);
        
        if (calstatement != null) {
            calstatement.close();
        }                                          
        return _recordCnt;
    }
}
