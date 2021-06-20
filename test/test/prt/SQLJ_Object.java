/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.prt;

import java.sql.*;
import java.io.*;
import java.util.*;
import oracle.sql.*;
import oracle.jdbc.*;
import java.math.*;
import vbsp.ims.dao.DaoConnect;

public class SQLJ_Object {
    /*
     * Retrieving an instance of Person object type
     */

    public static List<PersonObj> getPersonObjArr(int id)
            throws SQLException {
        DaoConnect dao = new DaoConnect();
//    oracle.jdbc.driver.OracleConnection conn = (oracle.jdbc.driver.OracleConnection)dao.getConnect();
        OracleConnection oconn
                = (OracleConnection) dao.getConnect();
        java.util.Dictionary map
                = (java.util.Dictionary) (oconn.getTypeMap());
        Statement stmt = oconn.createStatement();
        try {
            map.put("PERSON_T", Class.forName("test.prt.PersonObj"));
            map.put("ADDRESS_T",Class.forName("test.prt.AddressObj"));
            oconn.setTypeMap((Map) map);
            oconn.commit();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        List<PersonObj> lstObj = new ArrayList<PersonObj>();
        PreparedStatement ps = oconn.prepareStatement("SELECT adtcol1 from PersonObjTab1 where id = ?");
        ps.setInt(1, id);
        OracleResultSet ors = (OracleResultSet) ps.executeQuery();
        java.util.Hashtable ht = new java.util.Hashtable();
        try {
            ht.put("PERSON_T", Class.forName("test.prt.PersonObj"));
            ht.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        while (ors.next()) {
            oconn.commit();
            Array arr = ors.getArray(1);
            Object[] obj = (Object[])arr.getArray();
            
            for (int i=0;i<obj.length;i++)
            {
                PersonObj pobj = (PersonObj)obj[i];
                lstObj.add(pobj);
            }
//            pobj = (PersonObj) ors.getObject(1, (Map) ht);
        }
        ps.close();
        oconn.close();
        stmt.close();
        return lstObj;
    }
    
    
    public static PersonObj getPersonObj(int id)
            throws SQLException {
        DaoConnect dao = new DaoConnect();
//    oracle.jdbc.driver.OracleConnection conn = (oracle.jdbc.driver.OracleConnection)dao.getConnect();
        OracleConnection oconn
                = (OracleConnection) dao.getConnect();
        java.util.Dictionary map
                = (java.util.Dictionary) (oconn.getTypeMap());
        Statement stmt = oconn.createStatement();
        try {
            map.put("PERSON_T", Class.forName("test.prt.PersonObj"));
            map.put("ADDRESS_T",Class.forName("test.prt.AddressObj"));
            oconn.setTypeMap((Map) map);
            oconn.commit();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        PersonObj pobj = null;
        PreparedStatement ps = oconn.prepareStatement("SELECT adtcol1 from PersonObjTab where id = ?");
        ps.setInt(1, id);
        OracleResultSet ors = (OracleResultSet) ps.executeQuery();
        java.util.Hashtable ht = new java.util.Hashtable();
        try {
            ht.put("PERSON_T", Class.forName("test.prt.PersonObj"));
            ht.put("ADDRESS_T", Class.forName("test.prt.AddressObj"));
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        while (ors.next()) {
            oconn.commit();
            pobj = (PersonObj) ors.getObject(1, (Map) ht);
        }
        ps.close();
        oconn.close();
        stmt.close();
        return pobj;
    }
    /*
     * Inserting an instance of Person object type
     */

    public static void insPersonObj(int id, PersonObj personin,
            PersonObj[] personout) throws SQLException {
        Connection conn
                = DriverManager.getConnection("jdbc:oracle:kprb:");
        OracleConnection orconn = (OracleConnection) conn;
        PreparedStatement ps = conn.prepareStatement("insert into PersonObjTab values(?,?)");
        java.util.Dictionary map
                = (java.util.Dictionary) (orconn.getTypeMap());
        try {
            map.put("PERSON_T", Class.forName("PersonObj"));
            map.put("ADDRESS_T", Class.forName("AddressObj"));
            orconn.setTypeMap((Map) map);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        ps.setInt(1, id);
        ps.setObject(2, personin);
        ps.executeUpdate();
        ps.close();
        PreparedStatement ps1 = conn.prepareStatement("select adtcol1 from PersonObjTab where id = ?");
        ps1.setInt(1, id);
        OracleResultSet ors1 = (OracleResultSet) ps1.executeQuery();
        while (ors1.next()) {
            personout[0] = (PersonObj) ors1.getObject(1);
        }
        ors1.close();
        conn.close();
    }
    
      public static void main(String args []) throws Exception
      {
          PersonObj obj = SQLJ_Object.getPersonObj(10);
          List<PersonObj> lstPobj = SQLJ_Object.getPersonObjArr(10);
      }
}
