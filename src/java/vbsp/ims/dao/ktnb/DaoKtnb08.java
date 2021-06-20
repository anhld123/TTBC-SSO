package vbsp.ims.dao.ktnb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb04Model;
import vbsp.ims.model.ktnb.Ktnb08Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb08 {
    public ArrayList<Ktnb08Model> get_ktnb08(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb08Model> dataList = new ArrayList<Ktnb08Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb08(?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb08Model obj = new Ktnb08Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TX_L(reset.getDouble("KT_TX_L"));
                    obj.setKT_TX_N(reset.getDouble("KT_TX_N"));
                    obj.setKT_TX_VV_C(reset.getDouble("KT_TX_VV_C"));
                    obj.setKT_TX_VV_M(reset.getDouble("KT_TX_VV_M"));
                    obj.setKT_TX_DDN_SD(reset.getDouble("KT_TX_DDN_SD"));
                    obj.setKT_TX_DDN_N(reset.getDouble("KT_TX_DDN_N"));
                    obj.setKT_TX_DDN_VV_C(reset.getDouble("KT_TX_DDN_VV_C"));
                    obj.setKT_TX_DDN_VV_M(reset.getDouble("KT_TX_DDN_VV_M"));
                    obj.setKT_DK_L(reset.getDouble("KT_DK_L"));
                    obj.setKT_DK_N(reset.getDouble("KT_DK_N"));
                    obj.setKT_DK_VV_C(reset.getDouble("KT_DK_VV_C"));
                    obj.setKT_DK_VV_M(reset.getDouble("KT_DK_VV_M"));
                    obj.setKT_DK_DDN_SD(reset.getDouble("KT_DK_DDN_SD"));
                    obj.setKT_DK_DDN_N(reset.getDouble("KT_DK_DDN_N"));
                    obj.setKT_DK_DDN_VV_C(reset.getDouble("KT_DK_DDN_VV_C"));
                    obj.setKT_DK_DDN_VV_M(reset.getDouble("KT_DK_DDN_VV_M"));
                    obj.setKT_ND_KN_HC_TC(reset.getDouble("KT_ND_KN_HC_TC"));
                    obj.setKT_ND_KN_HC_CS(reset.getDouble("KT_ND_KN_HC_CS"));
                    obj.setKT_ND_KN_HC_NTS(reset.getDouble("KT_ND_KN_HC_NTS"));
                    obj.setKT_ND_KN_HC_CD(reset.getDouble("KT_ND_KN_HC_CD"));
                    obj.setKT_ND_KN_TP(reset.getDouble("KT_ND_KN_TP"));
                    obj.setKT_ND_KN_CT(reset.getDouble("KT_ND_KN_CT"));
                    obj.setKT_ND_TC_HC(reset.getDouble("KT_ND_TC_HC"));
                    obj.setKT_ND_TC_TP(reset.getDouble("KT_ND_TC_TP"));
                    obj.setKT_ND_TC_TN(reset.getDouble("KT_ND_TC_TN"));
                    obj.setKT_ND_KHAC(reset.getDouble("KT_ND_KHAC"));
                    obj.setKT_KQ_CGQ(reset.getDouble("KT_KQ_CGQ"));
                    obj.setKT_KQ_GQ_CCQD(reset.getDouble("KT_KQ_GQ_CCQD"));
                    obj.setKT_KQ_GQ_DCQD(reset.getDouble("KT_KQ_GQ_DCQD"));
                    obj.setKT_KQ_GQ_DCBA(reset.getDouble("KT_KQ_GQ_DCBA"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));

                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb08 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb08 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb08 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb08Model> get_ktnb08_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb08Model> dataList = new ArrayList<Ktnb08Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb08_default(?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb08Model obj = new Ktnb08Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TX_L(reset.getDouble("KT_TX_L"));
                    obj.setKT_TX_N(reset.getDouble("KT_TX_N"));
                    obj.setKT_TX_VV_C(reset.getDouble("KT_TX_VV_C"));
                    obj.setKT_TX_VV_M(reset.getDouble("KT_TX_VV_M"));
                    obj.setKT_TX_DDN_SD(reset.getDouble("KT_TX_DDN_SD"));
                    obj.setKT_TX_DDN_N(reset.getDouble("KT_TX_DDN_N"));
                    obj.setKT_TX_DDN_VV_C(reset.getDouble("KT_TX_DDN_VV_C"));
                    obj.setKT_TX_DDN_VV_M(reset.getDouble("KT_TX_DDN_VV_M"));
                    obj.setKT_DK_L(reset.getDouble("KT_DK_L"));
                    obj.setKT_DK_N(reset.getDouble("KT_DK_N"));
                    obj.setKT_DK_VV_C(reset.getDouble("KT_DK_VV_C"));
                    obj.setKT_DK_VV_M(reset.getDouble("KT_DK_VV_M"));
                    obj.setKT_DK_DDN_SD(reset.getDouble("KT_DK_DDN_SD"));
                    obj.setKT_DK_DDN_N(reset.getDouble("KT_DK_DDN_N"));
                    obj.setKT_DK_DDN_VV_C(reset.getDouble("KT_DK_DDN_VV_C"));
                    obj.setKT_DK_DDN_VV_M(reset.getDouble("KT_DK_DDN_VV_M"));
                    obj.setKT_ND_KN_HC_TC(reset.getDouble("KT_ND_KN_HC_TC"));
                    obj.setKT_ND_KN_HC_CS(reset.getDouble("KT_ND_KN_HC_CS"));
                    obj.setKT_ND_KN_HC_NTS(reset.getDouble("KT_ND_KN_HC_NTS"));
                    obj.setKT_ND_KN_HC_CD(reset.getDouble("KT_ND_KN_HC_CD"));
                    obj.setKT_ND_KN_TP(reset.getDouble("KT_ND_KN_TP"));
                    obj.setKT_ND_KN_CT(reset.getDouble("KT_ND_KN_CT"));
                    obj.setKT_ND_TC_HC(reset.getDouble("KT_ND_TC_HC"));
                    obj.setKT_ND_TC_TP(reset.getDouble("KT_ND_TC_TP"));
                    obj.setKT_ND_TC_TN(reset.getDouble("KT_ND_TC_TN"));
                    obj.setKT_ND_KHAC(reset.getDouble("KT_ND_KHAC"));
                    obj.setKT_KQ_CGQ(reset.getDouble("KT_KQ_CGQ"));
                    obj.setKT_KQ_GQ_CCQD(reset.getDouble("KT_KQ_GQ_CCQD"));
                    obj.setKT_KQ_GQ_DCQD(reset.getDouble("KT_KQ_GQ_DCQD"));
                    obj.setKT_KQ_GQ_DCBA(reset.getDouble("KT_KQ_GQ_DCBA"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));

                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb08 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb08 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb08 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb08(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_DV,List<String> KT_TX_L,List<String> KT_TX_N,List<String> KT_TX_VV_C,
            List<String> KT_TX_VV_M,List<String> KT_TX_DDN_SD,List<String> KT_TX_DDN_N,
            List<String> KT_TX_DDN_VV_C,List<String> KT_TX_DDN_VV_M,List<String> KT_DK_L,
            List<String> KT_DK_N,List<String> KT_DK_VV_C,List<String> KT_DK_VV_M,List<String> KT_DK_DDN_SD,
            List<String> KT_DK_DDN_N,List<String> KT_DK_DDN_VV_C,List<String> KT_DK_DDN_VV_M,
            List<String> KT_ND_KN_HC_TC,List<String> KT_ND_KN_HC_CS,List<String> KT_ND_KN_HC_NTS,
            List<String> KT_ND_KN_HC_CD,List<String> KT_ND_KN_TP,List<String> KT_ND_KN_CT,
            List<String> KT_ND_TC_HC,List<String> KT_ND_TC_TP,List<String> KT_ND_TC_TN,List<String> KT_ND_KHAC,
            List<String> KT_KQ_CGQ,List<String> KT_KQ_GQ_CCQD,List<String> KT_KQ_GQ_DCQD,List<String> KT_KQ_GQ_DCBA,
            List<String> KT_GHICHU,List<String> KT_DN,List<String> KT_CO_DINH,List<String> KT_THEM,
            List<String> KT_XOA,List<String> KT_FONTWEIGHT,List<String> KT_CAPHT,List<String> KT_STT,
            List<String> NG_CAPNHAT, String strAuth){
    
        try {
            if(strAuth == "")
                strAuth = "''";
            else strAuth = "'"+strAuth+"'";
            //1. XOA DU LIEU CU
//            String sqlDelete = "delete ktnb08 " +
//                    "WHERE kt_mapgd = '" + posCD + "'" +
//                    "    AND kt_quybc = " + quyBc +
//                    "    AND kt_nambc = " + namBc;
            
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();
            
            //Thuc hien xoa du lieu
           // stm.executeUpdate(sqlDelete);            
            
            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
                    
            for (int i = 0; i < KT_DV.size(); i++) {
                if(KT_GHICHU.size() == 0)
                {
                    sqlInsert = "INSERT INTO KTNB08(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_TX_L,KT_TX_N,KT_TX_VV_C,KT_TX_VV_M,KT_TX_DDN_SD,KT_TX_DDN_N,KT_TX_DDN_VV_C,KT_TX_DDN_VV_M,KT_DK_L,KT_DK_N,KT_DK_VV_C,KT_DK_VV_M,KT_DK_DDN_SD,KT_DK_DDN_N,KT_DK_DDN_VV_C,KT_DK_DDN_VV_M,KT_ND_KN_HC_TC,KT_ND_KN_HC_CS,KT_ND_KN_HC_NTS,KT_ND_KN_HC_CD,KT_ND_KN_TP,KT_ND_KN_CT,KT_ND_TC_HC,KT_ND_TC_TP,KT_ND_TC_TN,KT_ND_KHAC,KT_KQ_CGQ,KT_KQ_GQ_CCQD,KT_KQ_GQ_DCQD,KT_KQ_GQ_DCBA,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
                        "VALUES(" + 
                        i + ",'" + 
                        posCD + "','" + 
                        maCn + "'," + 
                        quyBc + "," + 
                        namBc + 
                        "," + 
                        "sysdate" + ",'" + 
                        userId + "'," +                //Cac truong co dinh
                        "'"+KT_DV.get(i)+"',"+ 
                        KT_TX_L.get(i)+","+ 
                        KT_TX_N.get(i)+","+ 
                        KT_TX_VV_C.get(i)+","+ 
                        KT_TX_VV_M.get(i)+","+ 
                        KT_TX_DDN_SD.get(i)+","+ 
                        KT_TX_DDN_N.get(i)+","+ 
                        KT_TX_DDN_VV_C.get(i)+","+ 
                        KT_TX_DDN_VV_M.get(i)+","+ 
                        KT_DK_L.get(i)+","+ 
                        KT_DK_N.get(i)+","+ 
                        KT_DK_VV_C.get(i)+","+ 
                        KT_DK_VV_M.get(i)+","+ 
                        KT_DK_DDN_SD.get(i)+","+ 
                        KT_DK_DDN_N.get(i)+","+ 
                        KT_DK_DDN_VV_C.get(i)+","+ 
                        KT_DK_DDN_VV_M.get(i)+","+ 
                        KT_ND_KN_HC_TC.get(i)+","+ 
                        KT_ND_KN_HC_CS.get(i)+","+ 
                        KT_ND_KN_HC_NTS.get(i)+","+ 
                        KT_ND_KN_HC_CD.get(i)+","+ 
                        KT_ND_KN_TP.get(i)+","+ 
                        KT_ND_KN_CT.get(i)+","+ 
                        KT_ND_TC_HC.get(i)+","+ 
                        KT_ND_TC_TP.get(i)+","+ 
                        KT_ND_TC_TN.get(i)+","+ 
                        KT_ND_KHAC.get(i)+","+ 
                        KT_KQ_CGQ.get(i)+","+ 
                        KT_KQ_GQ_CCQD.get(i)+","+ 
                        KT_KQ_GQ_DCQD.get(i)+","+ 
                        KT_KQ_GQ_DCBA.get(i)+","+ 
                        "'"+' '+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate,"+strAuth + ")";
                }
                else
                {
                    sqlInsert = "INSERT INTO KTNB08(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_TX_L,KT_TX_N,KT_TX_VV_C,KT_TX_VV_M,KT_TX_DDN_SD,KT_TX_DDN_N,KT_TX_DDN_VV_C,KT_TX_DDN_VV_M,KT_DK_L,KT_DK_N,KT_DK_VV_C,KT_DK_VV_M,KT_DK_DDN_SD,KT_DK_DDN_N,KT_DK_DDN_VV_C,KT_DK_DDN_VV_M,KT_ND_KN_HC_TC,KT_ND_KN_HC_CS,KT_ND_KN_HC_NTS,KT_ND_KN_HC_CD,KT_ND_KN_TP,KT_ND_KN_CT,KT_ND_TC_HC,KT_ND_TC_TP,KT_ND_TC_TN,KT_ND_KHAC,KT_KQ_CGQ,KT_KQ_GQ_CCQD,KT_KQ_GQ_DCQD,KT_KQ_GQ_DCBA,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
                        "VALUES(" + 
                        i + ",'" + 
                        posCD + "','" + 
                        maCn + "'," + 
                        quyBc + "," + 
                        namBc + 
                        "," + 
                        "sysdate" + ",'" + 
                        userId + "'," +                //Cac truong co dinh
                        "'"+KT_DV.get(i)+"',"+ 
                        KT_TX_L.get(i)+","+ 
                        KT_TX_N.get(i)+","+ 
                        KT_TX_VV_C.get(i)+","+ 
                        KT_TX_VV_M.get(i)+","+ 
                        KT_TX_DDN_SD.get(i)+","+ 
                        KT_TX_DDN_N.get(i)+","+ 
                        KT_TX_DDN_VV_C.get(i)+","+ 
                        KT_TX_DDN_VV_M.get(i)+","+ 
                        KT_DK_L.get(i)+","+ 
                        KT_DK_N.get(i)+","+ 
                        KT_DK_VV_C.get(i)+","+ 
                        KT_DK_VV_M.get(i)+","+ 
                        KT_DK_DDN_SD.get(i)+","+ 
                        KT_DK_DDN_N.get(i)+","+ 
                        KT_DK_DDN_VV_C.get(i)+","+ 
                        KT_DK_DDN_VV_M.get(i)+","+ 
                        KT_ND_KN_HC_TC.get(i)+","+ 
                        KT_ND_KN_HC_CS.get(i)+","+ 
                        KT_ND_KN_HC_NTS.get(i)+","+ 
                        KT_ND_KN_HC_CD.get(i)+","+ 
                        KT_ND_KN_TP.get(i)+","+ 
                        KT_ND_KN_CT.get(i)+","+ 
                        KT_ND_TC_HC.get(i)+","+ 
                        KT_ND_TC_TP.get(i)+","+ 
                        KT_ND_TC_TN.get(i)+","+ 
                        KT_ND_KHAC.get(i)+","+ 
                        KT_KQ_CGQ.get(i)+","+ 
                        KT_KQ_GQ_CCQD.get(i)+","+ 
                        KT_KQ_GQ_DCQD.get(i)+","+ 
                        KT_KQ_GQ_DCBA.get(i)+","+ 
                        "'"+KT_GHICHU.get(i)+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate,"+strAuth + ")";
                }
                

                stm.executeUpdate(sqlInsert);  
            }
            
            if (stm != null) {
                    stm.close();
                }            
            
            if (con != null) {
                con.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoKtnb08.class.getName()).log(Level.SEVERE, null, ex);
//            CoreLogger.error(this.getClass().getName()+ " get_ktnb08 -> " + ex.getMessage());
            
            System.err.println("Loi trong ham save_ktnb08 " + posCD + " " + ex.getMessage());
            CoreLogger.error(this.getClass().getName() + " save_ktnb08 ---------> " + posCD + " " + ex.getMessage());
      
            return false;
        }
        
        return true;
                
    }
    
    public ArrayList<Ktnb08Model> get_ktnb08_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb08Model> dataList = new ArrayList<Ktnb08Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb08_auth(?, ?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(7, auth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb08Model obj = new Ktnb08Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TX_L(reset.getDouble("KT_TX_L"));
                    obj.setKT_TX_N(reset.getDouble("KT_TX_N"));
                    obj.setKT_TX_VV_C(reset.getDouble("KT_TX_VV_C"));
                    obj.setKT_TX_VV_M(reset.getDouble("KT_TX_VV_M"));
                    obj.setKT_TX_DDN_SD(reset.getDouble("KT_TX_DDN_SD"));
                    obj.setKT_TX_DDN_N(reset.getDouble("KT_TX_DDN_N"));
                    obj.setKT_TX_DDN_VV_C(reset.getDouble("KT_TX_DDN_VV_C"));
                    obj.setKT_TX_DDN_VV_M(reset.getDouble("KT_TX_DDN_VV_M"));
                    obj.setKT_DK_L(reset.getDouble("KT_DK_L"));
                    obj.setKT_DK_N(reset.getDouble("KT_DK_N"));
                    obj.setKT_DK_VV_C(reset.getDouble("KT_DK_VV_C"));
                    obj.setKT_DK_VV_M(reset.getDouble("KT_DK_VV_M"));
                    obj.setKT_DK_DDN_SD(reset.getDouble("KT_DK_DDN_SD"));
                    obj.setKT_DK_DDN_N(reset.getDouble("KT_DK_DDN_N"));
                    obj.setKT_DK_DDN_VV_C(reset.getDouble("KT_DK_DDN_VV_C"));
                    obj.setKT_DK_DDN_VV_M(reset.getDouble("KT_DK_DDN_VV_M"));
                    obj.setKT_ND_KN_HC_TC(reset.getDouble("KT_ND_KN_HC_TC"));
                    obj.setKT_ND_KN_HC_CS(reset.getDouble("KT_ND_KN_HC_CS"));
                    obj.setKT_ND_KN_HC_NTS(reset.getDouble("KT_ND_KN_HC_NTS"));
                    obj.setKT_ND_KN_HC_CD(reset.getDouble("KT_ND_KN_HC_CD"));
                    obj.setKT_ND_KN_TP(reset.getDouble("KT_ND_KN_TP"));
                    obj.setKT_ND_KN_CT(reset.getDouble("KT_ND_KN_CT"));
                    obj.setKT_ND_TC_HC(reset.getDouble("KT_ND_TC_HC"));
                    obj.setKT_ND_TC_TP(reset.getDouble("KT_ND_TC_TP"));
                    obj.setKT_ND_TC_TN(reset.getDouble("KT_ND_TC_TN"));
                    obj.setKT_ND_KHAC(reset.getDouble("KT_ND_KHAC"));
                    obj.setKT_KQ_CGQ(reset.getDouble("KT_KQ_CGQ"));
                    obj.setKT_KQ_GQ_CCQD(reset.getDouble("KT_KQ_GQ_CCQD"));
                    obj.setKT_KQ_GQ_DCQD(reset.getDouble("KT_KQ_GQ_DCQD"));
                    obj.setKT_KQ_GQ_DCBA(reset.getDouble("KT_KQ_GQ_DCBA"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
  

                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb08 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb08 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb08 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb08_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB08 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_DV, KT_TX_L, KT_TX_N, \n" +
"    KT_TX_VV_C, KT_TX_VV_M, KT_TX_DDN_SD, KT_TX_DDN_N, KT_TX_DDN_VV_C, \n" +
"    KT_TX_DDN_VV_M, KT_DK_L, KT_DK_N, KT_DK_VV_C, KT_DK_VV_M, \n" +
"    KT_DK_DDN_SD, KT_DK_DDN_N, KT_DK_DDN_VV_C, KT_DK_DDN_VV_M, KT_ND_KN_HC_TC, \n" +
"    KT_ND_KN_HC_CS, KT_ND_KN_HC_NTS, KT_ND_KN_HC_CD, KT_ND_KN_TP, KT_ND_KN_CT, \n" +
"    KT_ND_TC_HC, KT_ND_TC_TP, KT_ND_TC_TN, KT_ND_KHAC, KT_KQ_CGQ, \n" +
"    KT_KQ_GQ_CCQD, KT_KQ_GQ_DCQD, KT_KQ_GQ_DCBA, KT_GHICHU, KT_DN, \n" +
"    KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, \n" +
"    KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB08 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and KT_AUTH is null and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB08\n" +
"                    WHERE KT_MAPGD = '" + posCD +
"'                    AND KT_NAMBC = " + namBc +
"                    AND KT_QUYBC = " + quyBc + " and KT_AUTH is null)"; 
                     
            stm.executeUpdate(sqlInsert);  
            
            
            if (stm != null) {
                    stm.close();
                }            
            
            if (con != null) {
                con.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoKtnb04.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb08_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb08_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
