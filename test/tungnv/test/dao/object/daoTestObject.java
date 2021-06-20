/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnv.test.dao.object;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import static java.sql.Types.ARRAY;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import static oracle.jdbc.OracleTypes.ARRAY;
import static oracle.jdbc.OracleTypes.STRUCT;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import test.prt.PersonObj;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author LION
 */
public class daoTestObject {

    /**
     * Su ding Array trong column thuc chat la su dung kieu table array trong 1
     * cot cua bang Vidu: create type tungnv_type_object as table of
     * varchar2(6); create table Tungnv_tab_object( so_pgd int,city
     * varchar2(100), ma_pgd tungnv_type_object ) nested table ma_pgd store as
     * ma_pgd_nt; select * from Tungnv_tab_object order by so_pgd insert into
     * Tungnv_tab_object (so_pgd,city,ma_pgd) values(5,'Hải
     * phòng',tungnv_type_object('000301', '000302','000303',
     * '000304','000305'));
     *
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Cho pos_cd list">
    public List<ObjectTest> getPosObjectTest() {
        List<ObjectTest> lstObjTest = new ArrayList<ObjectTest>();
        try {
            OracleConnection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            conn = (OracleConnection) new DaoConnect().getConnect();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from Tungnv_tab_object order by so_pgd");

            while (rs.next()) {
                ObjectTest value = new ObjectTest();
                value.setnSopgd(rs.getInt(1));
                value.setsTentinh(rs.getString(2));
                Array poscd = rs.getArray(3);
                String[] empValues = (String[]) poscd.getArray();
                List<String> lstPos = Arrays.asList(empValues);
                value.setLstPoscd(lstPos);
//                System.out.println("poscd=" + empValues[0]);
//                System.out.println("city=" + value.getsTentinh());
//                System.err.println("Sopgd=" + value.getnSopgd());
                lstObjTest.add(value);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("loi -> " + e.getMessage());
        }
        return lstObjTest;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get list from table">
    public List<ObjectTabWithTab> getDataTab(String sPoscd) {
        List<ObjectTabWithTab> lstObjTabByTab = new ArrayList<>();
        try {
            Connection conn = null;
            conn = (OracleConnection) new DaoConnect().getConnect();

            java.util.Dictionary map
                    = (java.util.Dictionary) (conn.getTypeMap());

            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call sp_tab_by_tab(?,?,?,?)}";
            OracleResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            try {
                map.put(KhachhangObj.ORACLE_OBJECT_NAME, KhachhangObj.class);
//                map.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
                conn.setTypeMap((Map) map);
                conn.commit();
            } catch (Exception e) {
                throw new SQLException(e.getMessage());
            }
//Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sPoscd);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (OracleResultSet) calstatement.getObject(4);

            java.util.Hashtable ht = new java.util.Hashtable();
            try {
                map.put(KhachhangObj.ORACLE_OBJECT_NAME, KhachhangObj.class);
//                ht.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
            } catch (Exception e) {
                throw new SQLException(e.getMessage());
            }

            while (reset.next()) {
                ObjectTabWithTab objtab = new ObjectTabWithTab();
                objtab.setsMapgd(reset.getString(1));
                objtab.setsTenpgd(reset.getString(2));
                objtab.setsMaxa(reset.getString(3));
                objtab.setsTenxa(reset.getString(4));
                objtab.setsMathon(reset.getString(5));
                objtab.setsTenthon(reset.getString(6));
                conn.commit();
                Array arr = reset.getArray(7);
                Object[] obj = (Object[]) arr.getArray();
//                Object[] obj_kh = (Object[]) objkh.getArray();

                //for(int i=0;obj_kh.length;i++)
                List<KhachhangObj> lstObjKh = new ArrayList<>();
                for (int i = 0; i < obj.length; i++) {
                    KhachhangObj kh = (KhachhangObj) obj[i];
//                    System.err.println("phan tu i=" + kh.getsTenkh());
                    lstObjKh.add(kh);

                }
                objtab.setLstObjkh(lstObjKh);
                /*Object obj=reset.getObject(7);
                 ObjectKh[] obj_kh = (ObjectKh[])obj;
                 //Array khArr = (Array)reset.getArray(7);
                 ///Object[] khobj =(Object[])khArr.getArray();
                
                 //xu ly cho object table
                 STRUCT  khStruct = (STRUCT)reset.getObject(7);
                 Object[] objKh = khStruct.getAttributes();
                 */
                System.err.println("TenThon=" + reset.getString(6));
                lstObjTabByTab.add(objtab);
            }
            reset.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Loi to roi " + e.getMessage());
        }
        return lstObjTabByTab;
    }

    public List<ObjectTabWithTab> getPersonObjArr(String Mathon)
            throws SQLException {
        DaoConnect dao = new DaoConnect();
//    oracle.jdbc.driver.OracleConnection conn = (oracle.jdbc.driver.OracleConnection)dao.getConnect();
        OracleConnection oconn
                = (OracleConnection) dao.getConnect();
        Map map
                = oconn.getTypeMap();
        Statement stmt = oconn.createStatement();
        try {
            map.put(KhachhangObj.ORACLE_OBJECT_NAME, KhachhangObj.class);
//            map.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
            oconn.setTypeMap(map);
            oconn.commit();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        List<ObjectTabWithTab> lstObj = new ArrayList<ObjectTabWithTab>();
        PreparedStatement ps = oconn.prepareStatement("SELECT * FROM KH_OBJECT_TAB WHERE KH_MATHON= ?");
        ps.setString(1, Mathon);
        OracleResultSet ors = (OracleResultSet) ps.executeQuery();
        while (ors.next()) {
            ObjectTabWithTab objtab = new ObjectTabWithTab();
            objtab.setsMapgd(ors.getString(1));
            objtab.setsTenpgd(ors.getString(2));
            objtab.setsMaxa(ors.getString(3));
            objtab.setsTenxa(ors.getString(4));
            objtab.setsMathon(ors.getString(5));
            objtab.setsTenthon(ors.getString(6));
            oconn.commit();
            Array arr = ors.getArray(7);
            Object[] obj = (Object[]) arr.getArray();
            List<KhachhangObj> lstkh = new ArrayList<>();
            for (int i = 0; i < obj.length; i++) {
                KhachhangObj pobj = (KhachhangObj) obj[i];
                System.err.println("TenKh=" + pobj.kh_tenkh);
                lstkh.add(pobj);
            }
            objtab.setLstObjkh(lstkh);
            lstObj.add(objtab);
        }
        ps.close();
        oconn.close();
        stmt.close();
        return lstObj;
    }

    public List<ObjectTabWithTab> getPersonObjArr1(String Mathon)
            throws SQLException {
        DaoConnect dao = new DaoConnect();
//    oracle.jdbc.driver.OracleConnection conn = (oracle.jdbc.driver.OracleConnection)dao.getConnect();
        OracleConnection oconn
                = (OracleConnection) dao.getConnect();
        Map map
                = oconn.getTypeMap();
        Statement stmt = oconn.createStatement();
        try {
            map.put(ObjectKh.ORACLE_OBJECT_NAME, ObjectKh.class);
//            map.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
            oconn.setTypeMap(map);
            oconn.commit();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        List<ObjectTabWithTab> lstObj = new ArrayList<ObjectTabWithTab>();
        PreparedStatement ps = oconn.prepareStatement("SELECT KH_LIST FROM KH_OBJECT_TAB WHERE KH_MATHON= ?");
        ps.setString(1, Mathon);
        OracleResultSet ors = (OracleResultSet) ps.executeQuery();
        java.util.Hashtable ht = new java.util.Hashtable();
//        try {
//            ht.put("KH_TYPE", Class.forName("tungnv.test.dao.object.KhachhangObj"));
////            ht.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
//        } catch (Exception e) {
//            throw new SQLException(e.getMessage());
//        }
        while (ors.next()) {
            oconn.commit();
            Array arr = ors.getArray(1);
            Object[] obj = (Object[]) arr.getArray();

            for (int i = 0; i < obj.length; i++) {
                ObjectKh pobj = (ObjectKh) obj[i];
//                System.err.println("TenKh="+pobj.kh_tenkh);
//                lstObj.add(pobj);
            }
        }
        ps.close();
        oconn.close();
        stmt.close();
        return lstObj;
    }
//</editor-fold>

    public void insertTabByTab(String sMapgd, String sTenpgd, String sMaxa, String sTenxa,
            String sMathon, String sTenthon, List<KhachhangObj> lstObjKh) {
        try {
            Connection conn = null;
            conn = (OracleConnection) new DaoConnect().getConnect();

            java.util.Dictionary map
                    = (java.util.Dictionary) (conn.getTypeMap());

            Object array[] = lstObjKh.toArray();
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor("KH_TAB", conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call sp_ins_tab_by_tab(?,?,?,?,?,?,?,?,?)}";
            OracleResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            try {
//                map.put(KhachhangObj.ORACLE_OBJECT_NAME, KhachhangObj.class);
////                map.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
//                conn.setTypeMap((Map) map);
//                conn.commit();
//            } catch (Exception e) {
//                throw new SQLException(e.getMessage());
//            }
//Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sMapgd);
            calstatement.setString(2, sTenpgd);
            calstatement.setString(3, sMaxa);
            calstatement.setString(4, sTenxa);
            calstatement.setString(5, sMathon);
            calstatement.setString(6, sTenthon);
            calstatement.setArray(7, array_to_pass);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            conn.close();

        } catch (Exception e) {
            System.err.println("loi " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        daoTestObject dao = new daoTestObject();
        List<ObjectTabWithTab> lstTabByTab = dao.getPersonObjArr("10031501");
//        List<ObjectTabWithTab>lstTabByTab=dao.getDataTab("001003");

        ObjectTabWithTab objtab = lstTabByTab.get(0);
        dao.insertTabByTab("999999", objtab.getsTenpgd(), objtab.getsMaxa(), objtab.getsTenxa(), objtab.getsMathon(), objtab.getsTenthon(), objtab.getLstObjkh());
        List<ObjectTest> lstObjTest = dao.getPosObjectTest();
        for (ObjectTest value : lstObjTest) {
            System.out.println("SoPgd = " + value.getnSopgd());
            System.out.println("Ten Tinh = " + value.getsTentinh());
            for (String str : value.getLstPoscd()) {
                System.out.println("        Mapgd = " + str);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Object with pos array">
    public static class ObjectTest {

        int nSopgd;
        String sTentinh;
        List<String> lstPoscd;

        public int getnSopgd() {
            return nSopgd;
        }

        public void setnSopgd(int nSopgd) {
            this.nSopgd = nSopgd;
        }

        public String getsTentinh() {
            return sTentinh;
        }

        public void setsTentinh(String sTentinh) {
            this.sTentinh = sTentinh;
        }

        public List<String> getLstPoscd() {
            return lstPoscd;
        }

        public void setLstPoscd(List<String> lstPoscd) {
            this.lstPoscd = lstPoscd;
        }

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho kieu table in table">
    public static class ObjectTabWithTab {

        String sMapgd;
        String sTenpgd;
        String sMaxa;
        String sTenxa;
        String sMathon;
        String sTenthon;
        List<KhachhangObj> lstObjkh;

        public String getsMapgd() {
            return sMapgd;
        }

        public void setsMapgd(String sMapgd) {
            this.sMapgd = sMapgd;
        }

        public String getsTenpgd() {
            return sTenpgd;
        }

        public void setsTenpgd(String sTenpgd) {
            this.sTenpgd = sTenpgd;
        }

        public String getsMaxa() {
            return sMaxa;
        }

        public void setsMaxa(String sMaxa) {
            this.sMaxa = sMaxa;
        }

        public String getsTenxa() {
            return sTenxa;
        }

        public void setsTenxa(String sTenxa) {
            this.sTenxa = sTenxa;
        }

        public String getsMathon() {
            return sMathon;
        }

        public void setsMathon(String sMathon) {
            this.sMathon = sMathon;
        }

        public String getsTenthon() {
            return sTenthon;
        }

        public void setsTenthon(String sTenthon) {
            this.sTenthon = sTenthon;
        }

        public List<KhachhangObj> getLstObjkh() {
            return lstObjkh;
        }

        public void setLstObjkh(List<KhachhangObj> lstObjkh) {
            this.lstObjkh = lstObjkh;
        }

    }
//</editor-fold>
}
