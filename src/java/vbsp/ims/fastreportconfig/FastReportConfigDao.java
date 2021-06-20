/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.fastreportconfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Trung
 */
public class FastReportConfigDao {

    private static DaoConnect daoConnect;
    private static Connection conn;

    public FastReportConfigDao() {
        if (daoConnect == null) {
            daoConnect = new DaoConnect();
        }
    }

    public List<GroupQuery> listGroupQuery(String pv_username,int pv_log_grade) {
        ArrayList<GroupQuery> groupQueries = new ArrayList<>();

        CallableStatement calstatement;
        String strStoreproce
                = "{call app_fastreportconfigure.list_GROUP_QUERY(?, ?, ?)}";
        ResultSet rs;
        
        try {            
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_username);
            calstatement.setInt(2, pv_log_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            String lGroupId,lGroupDesc,lGroupFlag,lModule,lApplyRegion,lMkr_id,lMkr_Dt;
            int lGroupOrder;
            GroupQuery groupQuery ;
                    
            while (rs.next()) {
                lGroupId = rs.getString("GROUP_ID");
                lGroupDesc = rs.getString("GROUP_DESC");
                lGroupFlag = rs.getString("GROUP_FLG");
                lGroupOrder = rs.getInt("GROUP_ORDER");
                lModule = rs.getString("MODULE");
                lApplyRegion = rs.getString("APPLY_REGION");
                lMkr_id = rs.getString("MKR_ID");
                lMkr_Dt = rs.getString("MKR_DT");                
                groupQuery = new GroupQuery(lGroupId, lModule, lApplyRegion);
                groupQuery.setGroupDesc(lGroupDesc);
                groupQuery.setGroupFlg(lGroupFlag);
                groupQuery.setGroupOrder(lGroupOrder);
                groupQuery.setMkrId(lMkr_id);
                groupQuery.setMkrDt(lMkr_Dt);
               groupQueries.add(groupQuery);
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("FastReportConfigDao.listGroupQuery-->" + ex.getMessage());
        }
        return groupQueries;
    }
    
    public List<ParaRptQuery> listVariables(String pv_username,int pv_log_grade) {
        ArrayList<ParaRptQuery> paraRptQueries = new ArrayList<>();

        CallableStatement calstatement;
        String strStoreproce
                = "{call app_fastreportconfigure.list_PARA_REPORT(?, ?, ?)}";
        ResultSet rs;
        
        try {            
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_username);
            calstatement.setInt(2, pv_log_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            String lcparaKey;
            String lcparaDesc;
            String lcparaType;
            String lcparaTable;
            String lcparaColDesc;
            String lcparaColValue;
            String lcparaFilter;
            String lcparaSort;
            
            ParaRptQuery paraRptQuery ;
                    
            while (rs.next()) {
                lcparaKey = rs.getString("PARA_KEY");
                lcparaDesc = rs.getString("PARA_DESC");
                lcparaType = rs.getString("PARA_TYPE");
                lcparaTable = rs.getString("PARA_TABLE");
                lcparaColDesc = rs.getString("PARA_COL_DESC");
                lcparaColValue = rs.getString("PARA_COL_VALUE");
                lcparaFilter = rs.getString("PARA_FILTER");
                lcparaSort = rs.getString("PARA_SORT");
                
                paraRptQuery = new ParaRptQuery(lcparaKey);
                paraRptQuery.setParaColDesc(lcparaColDesc);
                paraRptQuery.setParaTable(lcparaTable);
                paraRptQuery.setParaType(lcparaType);                
                paraRptQuery.setParaFilter(lcparaFilter);
                paraRptQuery.setParaOrder(pv_log_grade);
                paraRptQuery.setParaSort(lcparaSort);
                paraRptQuery.setParaDesc(lcparaDesc);
                paraRptQuery.setParaColValue(lcparaColValue);
                
               paraRptQueries.add(paraRptQuery);
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("FastReportConfigDao.listGroupQuery-->" + ex.getMessage());
        }
        return paraRptQueries;
    }
    
    public String getNewCode(String pv_module,String pv_region){
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_fastreportconfigure.get_new_code(?, ?, ?)}";        
        String lnewCode = "Error";
        try {            
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_module);
            calstatement.setString(2, pv_region);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            lnewCode = (String) calstatement.getObject(3);                        
            calstatement.close();
        } catch (Exception ex) {
            System.err.println("FastReportConfigDao.getNewCode-->" + ex.getMessage());
        }
        return lnewCode;
    }
    
    public String update_group(String pv_group_id,String pv_module,String pv_region,
            String pv_group_desc,String pv_username,String action_type){
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_fastreportconfigure.update_group(?, ?, ?, ?, ?, ?, ?, ?)}";  
        String message ="ERROR";
        try {            
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_group_id);
            calstatement.setString(2, pv_group_desc);
            calstatement.setString(3, pv_module);
            calstatement.setString(4, pv_region);
            calstatement.setString(5, pv_username);
            calstatement.setString(6, action_type);            
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();            
            message = (String) calstatement.getObject(8); 
            calstatement.close();
        } catch (Exception ex) {
            System.err.println("app_fastreportconfigure.update_group-->" + ex.getMessage());
        }        
        return message;
    }
    
    public String update_Para(ParaRptQuery queryPara,String pv_username,String action_type){
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_fastreportconfigure.update_query_para(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";  
        String message ="ERROR";
        try {            
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, queryPara.getParaKey());
            calstatement.setString(2, queryPara.getParaDesc());
            calstatement.setString(3, queryPara.getParaType());
            calstatement.setString(4, queryPara.getParaTable());
            calstatement.setString(5, queryPara.getParaColDesc());
            calstatement.setString(6, queryPara.getParaColValue());            
            calstatement.setString(7, queryPara.getParaFilter());            
            calstatement.setString(8, queryPara.getParaSort());            
            calstatement.setString(9, pv_username);            
            calstatement.setString(10, action_type);            
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();            
            message = (String) calstatement.getObject(12); 
            calstatement.close();
        } catch (Exception ex) {
            System.err.println("app_fastreportconfigure.update_Para-->" + ex.getMessage());
        }        
        return message;
    }
    

    public List<QueryGroup> get_QueryGroup(String pv_username,String pv_groupid,
            String pv_module,String pv_applyRegion) {

        ArrayList<QueryGroup> groups = new ArrayList<>();
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {
            
            conn  = new DaoConnect().getConnect();
            
            String strStoreproce
                    = "{call app_fastreportconfigure.p_get_groupquerypri(?, ? , ?, ?, ?)}";
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_module);
            calstatement.setString(2, pv_applyRegion);
            calstatement.setString(3, pv_groupid);
            calstatement.setString(4, pv_username);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            String lcGroupId, lcDescript, lcShortcut, lcPrivileage;            
            result = (ResultSet) calstatement.getObject(5);
            while (result.next()) {
                lcGroupId = result.getString("GROUP_ID");
                lcDescript = result.getString("DESCRIPT");
                lcShortcut = result.getString("SHORTCUT");
                lcPrivileage = result.getString("PRIVILEAGE");
                groups.add(
                        new QueryGroup(pv_groupid, pv_module, pv_applyRegion, lcGroupId, lcDescript, lcShortcut, lcPrivileage)
                );
            }

        } catch (Exception e) {
            System.err.println("Loi get_QueryGroup" + e.getMessage());
        } finally {
            try {
                if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi get_QueryGroup" + e.getMessage());
            }
        }
        return groups;
    }
    
    public boolean save_DataTemp(String pv_module,String pv_applyRegion,String pv_groupid,
            String pv_username, String pv_groupStr){        
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {
            if (conn == null) {
                conn = new DaoConnect().getConnect();
            }
            String strStoreproce
                    = "{call app_fastreportconfigure.p_save_data_temp(? , ?, ?, ?, ?)}";
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_module);
            calstatement.setString(2, pv_applyRegion);
            calstatement.setString(3, pv_groupid);
            calstatement.setString(4, pv_username);
            calstatement.setString(5, pv_groupStr);
            calstatement.executeUpdate();            
        } catch (Exception e) {
            System.err.println("Loi save_DataTemp " + e.getMessage());
            return false;
        } finally {
            try {
                if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi " + e.getMessage());
            }
        }        
        return true;
    }
    
}
