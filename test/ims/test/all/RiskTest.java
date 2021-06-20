package ims.test.all;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.net.nt.ConnOption;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.report.fast.ListValue;
import oracle.xdb.XMLType;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.xml.XmlDcptNo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BAOANH
 */
public class RiskTest {

    public List<String> getData() {
        List<String> lstViewHis = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call sp_get_data(?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  

            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            reset = (ResultSet) calstatement.getObject(1);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
//            SQLXML xmlVal = conn.createSQLXML();
            ResultSetMetaData mtdata = reset.getMetaData();
            int ncol = mtdata.getColumnType(1);
            String sData = mtdata.getColumnTypeName(1);

            System.err.println("sData=" + sData + " ncol=" + ncol);
            while (reset.next()) {
                // get the XMLType
//                oracle.xdb.XMLType xml = oracle.xdb.XMLType.createXML((oracle.sql.OPAQUE)reset.getObject(1));
//                String poString=xml.getStringVal();
                lstViewHis.add(reset.getString(1));
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(" Loi trong ham getViewHistorySend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getViewHistorySend -> " + e.getMessage());
        }
        return lstViewHis;
    }

    public HashMap<Integer, Object> getDataSend(String sUserName, String sGrade, List<String> lstPoscd, String sNgaysl, String sDvut,
            String sMato) {
        HashMap<Integer, Object> hmObjOut = new HashMap<Integer, Object>();

        List<String> lstDataSend = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPT_NO.SP_GET_DATA_SEND(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sNgaysl);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            reset = (ResultSet) calstatement.getObject(10);
            hmObjOut.put(1, calstatement.getString(9));
//            ResultSetMetaData mtdata = reset.getMetaData();
//            int ncol = mtdata.getColumnType(9);
//            String sData = mtdata.getColumnTypeName(9);
//
//            System.err.println("sData=" + sData + " ncol=" + ncol);
            while (reset.next()) {
                lstDataSend.add(reset.getString(1));
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
            hmObjOut.put(2, lstDataSend);
        } catch (Exception e) {
            System.err.println(" Loi trong ham getDataSend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSend -> " + e.getMessage());
        }
        return hmObjOut;
    }

     public boolean updateData_Sync(String sdate) {
        boolean bSuccess = false;
        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call sp_tungnv_test(?)}";
            ResultSet reset = null;

            try {

                
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

               
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sdate);


//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " updateData_Sync -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham updateData_Sync " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setStatusRisk_Sync -> " + e.getMessage());
        }
        return bSuccess;
    }
     
     
       public boolean updateData_Sync(String strUserName, String sGrade, String sPoscd, List<DcptNoModel> lstDcptNo, String sDate) {
        boolean bSuccess = false;
        if (lstDcptNo.size() == 0) {
            //neu du lieu la ko co khach hang thi insert log
//            insertHistotySendLog(sPoscd, strUserName, sGrade, sNambc, sDotrr, sNhomrr, new BigDecimal(BigInteger.ZERO), Define.KHOA_SEND_RR);
            return true;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call SP_UPDATE_SYNC(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("DCPT_TYPE_SYNC", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstDcptNo.size()];
                int index = 0;
                for (DcptNoModel value : lstDcptNo) {

                    Object[] params = new Object[22];
                    params[0]=value.getsSoku();
                    params[1]=value.getsTk_Casa1();
                    params[2]=value.getsTk_Casa2();
                    params[3]=value.getsDvut();
                    params[4]=value.getsMato();
                    params[5]=value.getsNogoc_Clech();
                    params[6]=value.getsNolai_Clech();
                    params[7]=value.getsSoducasa_Clech();
                    params[8]=value.getsNguyennhan_Lech();
                    params[9]=value.getsThuctrang_Dtdt();
                    params[10]=value.getsDno_Chayy();
                    params[11]=value.getsDno_Xlrr();
                    params[12]=value.getsDno_Sxkd_Thualo();
                    params[13]=value.getsDno_Khong_Dc();
                    params[14]=value.getsDno_Ditu();
                    params[15]=value.getsDno_Khnhan_No();
                    params[16]=value.getsDno_Nn_Khac();
                    params[17]=value.getsTrangthai_Dc();
                    params[18]=value.getsNguoi_Nhap_Dc();
                    params[19]=value.getsNgay_Nhap_Dc();
                    params[20]=value.getsNguoi_Nhap_Pt();
                    params[21]=value.getsNgay_Nhap_Pt();

                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "DCPT_TAB_SYNC", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sPoscd);
                calstatement.setArray(4, oracleArray);

                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(7, sDate);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " updateData_Sync -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham updateData_Sync " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setStatusRisk_Sync -> " + e.getMessage());
        }
        return bSuccess;
    }
//    HashMap<Integer, List<ListValue>>
    public static void main(String[] args) throws ParseException {

         String strqry="SELECT HSKU.KU_LAITONTHAN,HSKU.KU_LAIQHAN,HSKU.KU_LAITONQHAN,HSKU.KU_TON_RPA,HSKU.KU_TKTHAN,HSKU.KU_TKQHAN,HSKU.KU_TKKHOANH,HSKU.KU_TKTHULAI,HSKU.KU_SCHEM_CD,HSKU.KU_PROD_CD,HSKU.KU_NGUONVON,HSKU.KU_CHTRINH,HSKU.KU_MAQD,HSKU.KU_TNTH,HSKU.KU_TNQH,HSKU.KU_TNKH,HSTO.TO_TENTT,HSTO.TO_DVUT,HSKU.KU_MAPGD,HSKH.KH_MAKH,HSKH.KH_TENKH,HSKH.KH_DIACHI,HSKH.KH_MADP,HSKU.KU_MATO,HSKU.KU_SOKU,TO_CHAR(TRUNC(HSKU.KU_NGAYVAY), 'DD/MM/YYYY') KU_NGAYVAY,TO_CHAR(TRUNC(HSKU.KU_NGAYDHAN_1), 'DD/MM/YYYY') KU_NGAYDHAN_1,TO_CHAR(TRUNC(HSKU.KU_NGAYDHAN_3), 'DD/MM/YYYY') KU_NGAYDHAN_3,HSKU.KU_HTHUCVAY,HSKU.KU_SPRD_CD,HSKU.KU_CAPQLV,HSKU.KU_LSUAT,HSKU.KU_DTTH,HSKU.KU_MANDT,TO_CHAR(TRUNC(HSKU.KU_NGKTAHSV), 'DD/MM/YYYY') KU_NGKTAHSV,HSKU.KU_MAPNKT51,HSKU.KU_HQDT_CD,HSKU.KU_HQDT_VAL1,HSKU.KU_GNGAN,HSKU.KU_DNOTHAN,HSKU.KU_DNOQHAN,HSKU.KU_DNOKHOANH,HSKU.KU_LAITHAN FROM HSKH,HSKU,HSTO WHERE HSKU.KU_MAKH=HSKH.KH_MAKH AND HSKU.KU_MATO=HSTO.TO_MATO AND TRUNC(HSKU.KU_NGAYBC)=TO_DATE('RPTFAST_KU_NGAYBC') AND DECODE(NVL('RPTFAST_KU_NGUONVON',''),'',1,HSKU.KU_NGUONVON)=DECODE(NVL('RPTFAST_KU_NGUONVON',''),'',1,'RPTFAST_KU_NGUONVON') AND DECODE(NVL('RPTFAST_KU_CHTRINH',''),'',1,HSKU.KU_CHTRINH)=DECODE(NVL('RPTFAST_KU_CHTRINH',''),'',1,'RPTFAST_KU_CHTRINH') AND DECODE(NVL('RPTFAST_TO_DVUT',''),'',1,HSTO.TO_DVUT)=DECODE(NVL('RPTFAST_TO_DVUT',''),'',1,'RPTFAST_TO_DVUT') AND DECODE(NVL('RPTFAST_KU_HTHUCVAY',''),'',1,HSKU.KU_HTHUCVAY)=DECODE(NVL('RPTFAST_KU_HTHUCVAY',''),'',1,'RPTFAST_KU_HTHUCVAY') AND DECODE(NVL('RPTFAST_KU_DTTH',''),'',1,HSKU.KU_DTTH)=DECODE(NVL('RPTFAST_KU_DTTH',''),'',1,'RPTFAST_KU_DTTH') AND DECODE(NVL('RPTFAST_KH_MADP',''),'',1,HSKH.KH_MADP)=DECODE(NVL('RPTFAST_KH_MADP',''),'',1,'RPTFAST_KH_MADP') AND DECODE(NVL('RPTFAST_KU_MATO',''),'',1,HSKU.KU_MATO)=DECODE(NVL('RPTFAST_KU_MATO',''),'',1,'RPTFAST_KU_MATO') AND DECODE(NVL('RPTFAST_KU_SPRD_CD',''),'',1,HSKU.KU_SPRD_CD)=DECODE(NVL('RPTFAST_KU_SPRD_CD',''),'',1,'RPTFAST_KU_SPRD_CD') AND DECODE(NVL('RPTFAST_KU_CAPQLV',''),'',1,HSKU.KU_CAPQLV)=DECODE(NVL('RPTFAST_KU_CAPQLV',''),'',1,'RPTFAST_KU_CAPQLV') AND DECODE(NVL('RPTFAST_KU_MAPNKT51',''),'',1,HSKU.KU_MAPNKT51)=DECODE(NVL('RPTFAST_KU_MAPNKT51',''),'',1,'RPTFAST_KU_MAPNKT51') AND DECODE(NVL('RPTFAST_KU_HQDT_CD',''),'',1,HSKU.KU_HQDT_CD)=DECODE(NVL('RPTFAST_KU_HQDT_CD',''),'',1,'RPTFAST_KU_HQDT_CD') AND DECODE(NVL('RPTFAST_KU_TKTHAN',''),'',1,HSKU.KU_TKTHAN)=DECODE(NVL('RPTFAST_KU_TKTHAN',''),'',1,'RPTFAST_KU_TKTHAN') AND DECODE(NVL('RPTFAST_KU_TKQHAN',''),'',1,HSKU.KU_TKQHAN)=DECODE(NVL('RPTFAST_KU_TKQHAN',''),'',1,'RPTFAST_KU_TKQHAN') AND DECODE(NVL('RPTFAST_KU_TKKHOANH',''),'',1,HSKU.KU_TKKHOANH)=DECODE(NVL('RPTFAST_KU_TKKHOANH',''),'',1,'RPTFAST_KU_TKKHOANH') AND DECODE(NVL('RPTFAST_KU_TKTHULAI',''),'',1,HSKU.KU_TKTHULAI)=DECODE(NVL('RPTFAST_KU_TKTHULAI',''),'',1,'RPTFAST_KU_TKTHULAI') AND DECODE(NVL('RPTFAST_KU_SCHEM_CD',''),'',1,HSKU.KU_SCHEM_CD)=DECODE(NVL('RPTFAST_KU_SCHEM_CD',''),'',1,'RPTFAST_KU_SCHEM_CD') AND DECODE(NVL('RPTFAST_KU_PROD_CD',''),'',1,HSKU.KU_PROD_CD)=DECODE(NVL('RPTFAST_KU_PROD_CD',''),'',1,'RPTFAST_KU_PROD_CD') AND DECODE(NVL('RPTFAST_KU_MAQD',''),'',1,HSKU.KU_MAQD)=DECODE(NVL('RPTFAST_KU_MAQD',''),'',1,'RPTFAST_KU_MAQD') AND DECODE(NVL('RPTFAST_KH_MAPGD',''),'',1,HSKH.KH_MAPGD)=DECODE(NVL('RPTFAST_KH_MAPGD',''),'',1,'RPTFAST_KH_MAPGD') AND KU_DNOTHAN+KU_DNOQHAN+KU_DNOKHOANH>0 AND KU_TTMONVAY<>'CLOSE'\n" +
" UNION ALL \n" +
"SELECT HSKU.KU_LAITONTHAN,HSKU.KU_LAIQHAN,HSKU.KU_LAITONQHAN,HSKU.KU_TON_RPA,HSKU.KU_TKTHAN,HSKU.KU_TKQHAN,HSKU.KU_TKKHOANH,HSKU.KU_TKTHULAI,HSKU.KU_SCHEM_CD,HSKU.KU_PROD_CD,HSKU.KU_NGUONVON,HSKU.KU_CHTRINH,HSKU.KU_MAQD,HSKU.KU_TNTH,HSKU.KU_TNQH,HSKU.KU_TNKH,HSTO.TO_TENTT,HSTO.TO_DVUT,HSKU.KU_MAPGD,HSKH_DN.DN_MA,HSKH_DN.DN_TEN,HSKH_DN.DN_DIACHI,HSKH_DN.DN_MADP,HSKU.KU_MATO,HSKU.KU_SOKU,TO_CHAR(TRUNC(HSKU.KU_NGAYVAY), 'DD/MM/YYYY') KU_NGAYVAY,TO_CHAR(TRUNC(HSKU.KU_NGAYDHAN_1), 'DD/MM/YYYY') KU_NGAYDHAN_1,TO_CHAR(TRUNC(HSKU.KU_NGAYDHAN_3), 'DD/MM/YYYY') KU_NGAYDHAN_3,HSKU.KU_HTHUCVAY,HSKU.KU_SPRD_CD,HSKU.KU_CAPQLV,HSK";
        
        System.err.println("do dai="+strqry.length());
        String strDate = "08/09/2015 22:04:51";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        java.util.Date parsedDate = dateFormat.parse(strDate);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        int n=33;
        int a=33;
        int b = (int) Math.ceil((double)a / 10);
        System.err.println("b="+b*10);
        
        /*new RiskTest().updateData_Sync(strDate);
        
        List<String> lstPoscd = new ArrayList<String>();
        lstPoscd.add("040101");
        lstPoscd.add("040102");
        lstPoscd.add("040103");
        lstPoscd.add("040104");
        lstPoscd.add("040105");
        lstPoscd.add("040106");
        lstPoscd.add("040107");
        lstPoscd.add("040108");
        lstPoscd.add("040109");
        lstPoscd.add("040110");
        lstPoscd.add("040111");
        lstPoscd.add("040112");
        lstPoscd.add("040113");
        lstPoscd.add("040114");
        lstPoscd.add("040115");
        lstPoscd.add("040116");
        lstPoscd.add("040117");
        lstPoscd.add("040118");
        lstPoscd.add("040119");
        lstPoscd.add("040120");
        lstPoscd.add("040121");

        String sPos_cd = "";
//        new DaoDcptNo().getDataSend("P0401", "1", lstPoscd, "07-sep-2015", "", "", sPos_cd);
//        System.err.println("Pos_cd=" + sPos_cd);
        HashMap<Integer, Object> hmObjData = new RiskTest().getDataSend("P0401", "1", lstPoscd, "07-sep-2015", "", "");
        List<String> lstData = (List<String>) hmObjData.get(2);
        sPos_cd = (String) hmObjData.get(1);
        System.err.println("Pos_cd=" + sPos_cd);
        XmlDcptNo test = new XmlDcptNo();
//        test.createXmlFileDcpt("10", "P0401", "1", "000401", lstData, "F:\\tungnv.xml");
//        for (String str : lstData) {
//            System.err.println(str);
//        }
        HashMap<String, Object> hmPara = test.readXmlDcPt("F:\\tungnv.xml");

        List<DcptNoModel> lstDataDc = (List<DcptNoModel>) hmPara.get(Define.XML_DATA);
        new DaoDcptNo().updateData_Sync("P0401", "1", "000401", lstDataDc);
        for (DcptNoModel value : lstDataDc) {
            System.err.println("Soku=" + value.getsSoku()+"  -> "+value.getsNgay_Nhap_Pt());
        }
                */
    }
}
