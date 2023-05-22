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
import vbsp.ims.restapi.DuLieuNTRowX;


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
    
    public List<DuLieuNTRow> getDataDebtHandling(String sKhoa,String sPosCD, String sNgaybc, 
            String sGrade, String maKH) {
        List<DuLieuNTRow> lstBcqt_NT = new ArrayList<DuLieuNTRow>();
        try {
            final DaoConnect db = new DaoConnect();
            final Connection conn = db.getConnect();    
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call BODI_KHOIDP.SP_GET_DEBT_HANDLING_INFO(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sPosCD);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);                
                calstatement.setString(8, maKH);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                while (reset.next()) {
//                        DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();
                    DuLieuNTRow value = new DuLieuNTRow();
                    value.setKey(reset.getString(1));
                    value.setOrderValue(reset.getString(2));
                    value.setOrderDescription(reset.getString(3));
                    value.setCode(reset.getString(4));
                    value.setName(reset.getString(5));
                    value.setReportDate(reset.getString(6));
                    value.setReportYear(reset.getInt(7));
                    value.setPosCode(reset.getString(8));
                    value.setPosFlag(reset.getString(9));
                    value.setBranchCode(reset.getString(10));
                    value.setMakerId(reset.getString(11));
                    value.setMakerDate(reset.getString(12));
                    value.setAuthoriseId(reset.getString(13));
                    value.setAuthoriseDate(reset.getString(14));
                    value.setD1(reset.getString(15));
                    value.setD2(reset.getString(16));
                    value.setD3(reset.getString(17));
                    value.setD4(reset.getString(18));
                    value.setD5(reset.getString(19));
                    value.setD6(reset.getString(20));
                    value.setD7(reset.getString(21));
                    value.setD8(reset.getString(22));
                    value.setD9(reset.getString(23));
                    value.setD10(reset.getString(24));
                    value.setD11(reset.getString(25));
                    value.setD12(reset.getString(26));
                    value.setD13(reset.getString(27));
                    value.setD14(reset.getString(28));
                    value.setD15(reset.getString(29));
                    value.setD16(reset.getString(30));
                    value.setD17(reset.getString(31));

                    value.setD18(reset.getString(32));
                    value.setD19(reset.getString(33));
                    value.setD20(reset.getString(34));
                    value.setD21(reset.getString(35));
                    value.setD22(reset.getString(36));
                    value.setD23(reset.getString(37));
                    value.setD24(reset.getString(38));
                    value.setD25(reset.getString(39));
                    value.setD26(reset.getString(40));
                    value.setD27(reset.getString(41));
                    value.setD28(reset.getString(42));
                    value.setD29(reset.getString(43));
                    value.setD30(reset.getString(44));
//                    value.setNHAPTAY(reset.getString(45));
//                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    
    public int checkRuleUser(String UserName, String CapBC) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call BODI_KHOIDP.F_CHECK_RULE_USER(?, ? ) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, UserName);
            calstatement.setString(3, CapBC);            
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkRuleUser -> " + e.getMessage());
            throw new SQLException(e);
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return _retVal;
    }
}
