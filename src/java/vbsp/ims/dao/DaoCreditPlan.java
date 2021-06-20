package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.KhtdGrid;
import vbsp.ims.model.ModelTreeNode;

/**
 *
 * @author LION
 */
public class DaoCreditPlan {
    
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName, String strCommuneFlg) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHTD.SP_GET_LIST_LOCAL(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                    reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
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
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;

    }
    
    public ArrayList<KhtdGrid> getDataKhtdGrid(String maPGD, String ngayNhap, String UserId){
        ArrayList<KhtdGrid> khtdList = new ArrayList<KhtdGrid>();
        
         try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHTD.SP_GET_GRID_KHTD(?,?,?,?,?,?)}";
            ResultSet rsGrid = null;
            
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, maPGD);
                calstatement.setString(2, ngayNhap);
                calstatement.setString(3, UserId);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                rsGrid = (ResultSet) calstatement.getObject(6);
                
                int id = 1; 
                while (rsGrid.next()) {
                    KhtdGrid obj = new KhtdGrid();
                    
                    obj.setId(id);
                    obj.setMaCT(rsGrid.getString("MA_CT"));
                    obj.setChiTieu(rsGrid.getString("CHI_TIEU"));
                    obj.setKeHoachTon(rsGrid.getString("KEHOACH_TON"));
                    obj.setXayDungKH(rsGrid.getString("KEHOACH_XD"));
                    obj.setGiaoKH(rsGrid.getString("KEHOACH_GIAO"));
                    obj.setDieuChinhKH(rsGrid.getString("KEHOACH_DC"));
                    obj.setChinhSua(rsGrid.getString("C_SUA"));
                    obj.setStt(rsGrid.getString("STT"));
                    obj.setCtTong(rsGrid.getString("CT_TONG"));
                    obj.setCtTongCongThuc(rsGrid.getString("CT_TONG_CTHUC"));
                    obj.setCtTongCongThucChiTiet(rsGrid.getString("CT_TONG_CTHUC_CTIET"));
                    obj.setCapHienThi(Integer.parseInt(rsGrid.getString("CAP_HT")));
                    
                    khtdList.add(obj);
                    id++;
                }

                if (rsGrid != null) {
                    rsGrid.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " getDataKhtdGrid -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKhtdGrid " + e.getMessage());
            CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " getDataKhtdGrid  -> " + e.getMessage());
        }
        
        return khtdList;
    }
    
    //reportGrade: 1. PGD  2. CN    3. TQ
    //kieuLuu: xaydungkh#giaokh#dieuchinhkh      
    //data: MaChiTieu1:444#MaChiTieu2:883#.....
    public void saveKhtdGrid(String maPGD, String ngayBC, String userId, String reportGrade, String kieuLuu, String data){
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_khtd.sp_update_KHTD(?, ?, ?, ?, ?, ?, ?, ?)}";

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, maPGD);
                calstatement.setString(2, ngayBC);
                calstatement.setString(3, userId);
                calstatement.setString(4, reportGrade);
                calstatement.setString(5, kieuLuu);
                calstatement.setString(6, data);
       
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute luu du lieu
                calstatement.execute();
                
                int err_cd = calstatement.getInt(7);                
                String strErrtxt = calstatement.getString(8);
                
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham saveKhtdGrid " + e.getMessage());
                CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " saveKhtdGrid  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveKhtdGrid " + e.getMessage());
            CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " saveKhtdGrid  -> " + e.getMessage());
        }
    }
    
    public boolean saveKhtdGrid_Syn(String maPGD, String ngayBC, String userId, String reportGrade, String kieuLuu, String data, String sSysdate){
        boolean bSuccess=false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_khtd.sp_update_syn_KHTD(?, ?, ?, ?, ?, ?, ?, ?,?)}";

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, maPGD);
                calstatement.setString(2, ngayBC);
                calstatement.setString(3, userId);
                calstatement.setString(4, reportGrade);
                calstatement.setString(5, kieuLuu);
                calstatement.setString(6, data);
                calstatement.setString(7, sSysdate);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute luu du lieu
                bSuccess=calstatement.execute();
                
                int err_cd = calstatement.getInt(8);                
                String strErrtxt = calstatement.getString(9);
                
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess=true;
            } catch (SQLException e) {
                bSuccess=false;
                System.err.println("Loi trong ham saveKhtdGrid_Syn " + e.getMessage());
                CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " saveKhtdGrid_Syn  -> " + e.getMessage());
            }
        } catch (Exception e) {
            bSuccess=false;
            System.err.println("Loi trong ham saveKhtdGrid_Syn " + e.getMessage());
            CoreLogger.error(DaoCreditPlan.class.getCanonicalName() + " saveKhtdGrid_Syn  -> " + e.getMessage());
        }
        return bSuccess;
    }
}
