/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.leavelocal;

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

/**
 *
 * @author HP
 */
public class LeaveHomeDao {

    public LeaveHomeDao() {}
    
    public List<DuLieuNTRow> getUploadExcelData(String user, String posCode, String reportDate, String customerCode, String fromDate, String toDate, String type)
            throws SQLException {

        ArrayList<DuLieuNTRow> _lstData = new ArrayList();

        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();
        final CallableStatement st = con.prepareCall("{call BODI_KHOIDP.UPLOAD_EXCEL_DATA(?,?,?,?,?,?,?,?)}");
        st.setString(1, user);
        st.setString(2, posCode);
        st.setString(3, reportDate);
        st.setString(4, customerCode);
        st.setString(5, fromDate);
        st.setString(6, toDate);
        st.setString(7, type);
        st.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
        st.execute();
        final ResultSet rs = (ResultSet) st.getObject(8);
        while (rs.next()) {
            final DuLieuNTRow obj = new DuLieuNTRow();
            obj.setKey(rs.getString("KHOA"));
            obj.setOrderValue(rs.getString("THUTU"));
            obj.setCode(rs.getString("MA"));
            obj.setName(rs.getString("TEN"));
            obj.setReportDate(rs.getString("NGAYBC"));
            obj.setReportYear(rs.getInt("NAMBC"));
            obj.setPosCode(rs.getString("MAPGD"));
            obj.setPosFlag(rs.getString("CO_TONGHOP"));
            obj.setMakerId(rs.getString("NGUOI_NHAP"));
            obj.setMakerDate(rs.getString("NGAY_NHAP"));
            obj.setAuthoriseId(rs.getString("NGUOI_DUYET"));
            obj.setAuthoriseDate(rs.getString("NGAY_DUYET"));
            obj.setD1(rs.getString("D1"));
            obj.setD2(rs.getString("D2"));
            obj.setD3(rs.getString("D3"));
            obj.setD4(rs.getString("D4"));
            obj.setD5(rs.getString("D5"));
            obj.setD6(rs.getString("D6"));
            obj.setD7(rs.getString("D7"));
            obj.setD8(rs.getString("D8"));
            obj.setD9(rs.getString("D9"));
            obj.setD10(rs.getString("D10"));
            obj.setD11(rs.getString("D11"));
            obj.setD12(rs.getString("D12"));
            obj.setD13(rs.getString("D13"));
            obj.setD14(rs.getString("D14"));
            obj.setD15(rs.getString("D15"));
            obj.setD16(rs.getString("D16"));
            obj.setD17(rs.getString("D17"));
            obj.setD18(rs.getString("D18"));
            obj.setD19(rs.getString("D19"));
            obj.setD20(rs.getString("D20"));
            obj.setD21(rs.getString("D21"));
            obj.setD22(rs.getString("D22"));
            obj.setD23(rs.getString("D23"));
            obj.setD24(rs.getString("D24"));
            obj.setD25(rs.getString("D25"));
            obj.setD26(rs.getString("D26"));
            obj.setD27(rs.getString("D27"));
            obj.setD28(rs.getString("D28"));
            obj.setD29(rs.getString("D29"));
            obj.setD30(rs.getString("D30"));
            obj.setD31(rs.getString("D31"));
            obj.setD32(rs.getString("D32"));
            obj.setD33(rs.getString("D33"));
            obj.setD34(rs.getString("D34"));
            obj.setD35(rs.getString("D35"));
            obj.setD36(rs.getString("D36"));
            obj.setD37(rs.getString("D37"));
            obj.setD38(rs.getString("D38"));
            obj.setD39(rs.getString("D39"));
            obj.setD50(rs.getString("D50"));
            _lstData.add(obj);
        }

        return _lstData;
    }
    
    public int updateUploadExcelDataStatus(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String status)
    throws SQLException, ParseException       
    {
        
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
            //obj.setTHUTU(data.get(i).getOrderValue());
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
            obj.setD15(data.get(i).getD15());
            obj.setD16(data.get(i).getD16()); 
            obj.setD17(data.get(i).getD17());
            obj.setD18(data.get(i).getD18());
            obj.setD19(data.get(i).getD19());
            obj.setD20(data.get(i).getD20());
            obj.setD21(data.get(i).getD21());
            obj.setD22(data.get(i).getD22());
            obj.setD23(data.get(i).getD23());
            obj.setD24(data.get(i).getD24());
            obj.setD25(data.get(i).getD25());
            obj.setD26(data.get(i).getD26());
            obj.setD27(data.get(i).getD27());
            obj.setD28(data.get(i).getD28());
            obj.setD29(data.get(i).getD29());
            obj.setD30(data.get(i).getD30());
            obj.setD31(data.get(i).getD31());
            obj.setD32(data.get(i).getD32());
            obj.setD33(data.get(i).getD33());
            obj.setD34(data.get(i).getD34());
            obj.setD35(data.get(i).getD35());
            obj.setD36(data.get(i).getD36());
            obj.setD37(data.get(i).getD37());
            obj.setD38(data.get(i).getD38());
            obj.setD39(data.get(i).getD39());
            
            obj.setD50(status);
            
            lstData.add(obj);
        }
        
        final DaoConnect db = new DaoConnect();
        final Connection con = db.getConnect();        
      
        final Object[] array = lstData.toArray();
        final ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT_TQ", con);
        final ARRAY array_to_pass = new ARRAY(des, con, (Object) array);
        CallableStatement calstatement = null;
        final String strStoreproce = "{call BODI_KHOIDP.UPDATE_EXCEL_UPLOAD_STATUS(?,?,?,?,?,?)}";

        calstatement = con.prepareCall(strStoreproce);
        calstatement.setString(1, makerId);
        calstatement.setString(2, posCode);
        calstatement.setString(3, reportDate);
        calstatement.setArray(4, (Array) array_to_pass);
        calstatement.setString(5, status);
        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
        calstatement.execute();
        int _recordCnt = calstatement.getInt(6);
        
        if (calstatement != null) {
            calstatement.close();
        }               
                           
        return _recordCnt;
    }
}
