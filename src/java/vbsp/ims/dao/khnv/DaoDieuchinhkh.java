package vbsp.ims.dao.khnv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.DieuchinhkhModel;
import vbsp.ims.model.khnv.POSModel;

/**
 *
 * @author CuongBM0211
 */
public class DaoDieuchinhkh {
    public ArrayList<DieuchinhkhModel> get_data_dc_kh(String posCD, int namBc, String reportGrade) {
        ArrayList<DieuchinhkhModel> dataList = new ArrayList<DieuchinhkhModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.p_get_data_dckh(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setString(3, reportGrade);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    DieuchinhkhModel obj = new DieuchinhkhModel();
                    
                    obj.setKH_MA_CT(reset.getString("KH_MA_CT"));
                    obj.setKH_STT_HT(reset.getString("KH_STT_HT"));
                    obj.setKH_CHI_TIEU(reset.getString("KH_CHI_TIEU"));
                    obj.setKH_GIAO_DC(reset.getDouble("KH_GIAO_DC"));
                    obj.setKH_DC(0);
                    obj.setKH_CT_SGD(reset.getString("KH_CT_SGD"));
                    obj.setKH_DN(reset.getString("KH_DN"));
                    obj.setKH_FONTWEIGHT(reset.getString("KH_FONTWEIGHT"));
                    obj.setKH_CAPHT(reset.getDouble("KH_CAPHT"));
                    obj.setKH_STT(reset.getDouble("KH_STT"));
                    obj.setKH_CONGTHUC(reset.getString("KH_CONGTHUC"));
                    
                    //Them vao list
                    dataList.add(obj);
                }
                
//                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(4);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(5);
//                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_dc_kh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_dc_kh " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_data_dc_kh -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<POSModel> getPosList(String posCD, String maCn, String reportGrade){
        ArrayList<POSModel> posList = new ArrayList<POSModel>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.p_get_pos_list(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, maCn);          
                calstatement.setString(3, reportGrade);          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
    
    public boolean saveDieuChinhKh(List<String> KH_MA_CT, List<String> KH_DC, String posCdLoadData, 
            String maCn, String reportGrade, int namBc,String userId, String sKey){
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
            //CuongBM: Convert List to array
            String[] arrayKH_MA_CT = KH_MA_CT.toArray(new String[0]);
            String[] arrayKH_DC = KH_DC.toArray(new String[0]);
            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayKH_MA_CT = new ARRAY(des,conn,arrayKH_MA_CT);
            ARRAY oracle_arrayKH_DC = new ARRAY(des,conn,arrayKH_DC);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.save_dc_kh(?,?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setArray(1, oracle_arrayKH_MA_CT);
                calstatement.setArray(2, oracle_arrayKH_DC);
                calstatement.setString(3, posCdLoadData);
                calstatement.setString(4, maCn);
                calstatement.setString(5, reportGrade);
                calstatement.setInt(6, namBc);
                calstatement.setString(7, userId);
                calstatement.setString(8, sKey);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveDieuChinhKh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDieuChinhKh "+posCdLoadData+" " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " saveDieuChinhKh -> "+posCdLoadData+" " + e.getMessage());
        }
        return true;
    }
    
    public boolean ProcessUpfileKHNV(String sFileName){
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
                        
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.sp_process_upfile_khnv(?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, sFileName);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " ProcessUpfileKHNV -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham ProcessUpfileKHNV  " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " ProcessUpfileKHNV ->  " + e.getMessage());
        }
        return true;
    }
    
    public boolean InsertKhnv_12to62(){
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
                        
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.sp_insert_12to62(?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " InsertKhnv_12to62 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham InsertKhnv_12to62  " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " InsertKhnv_12to62 ->  " + e.getMessage());
        }
        return true;
    }
}
