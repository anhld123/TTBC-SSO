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
import vbsp.ims.model.ktnb.Ktnb09Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb09 {
    public ArrayList<Ktnb09Model> get_ktnb09(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb09Model> dataList = new ArrayList<Ktnb09Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb09(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb09Model obj = new Ktnb09Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TN_TS(reset.getDouble("KT_TN_TS"));
                    obj.setKT_TN_TK_NN(reset.getDouble("KT_TN_TK_NN"));
                    obj.setKT_TN_TK_MN(reset.getDouble("KT_TN_TK_MN"));
                    obj.setKT_TN_KT_NN(reset.getDouble("KT_TN_KT_NN"));
                    obj.setKT_TN_KT_MN(reset.getDouble("KT_TN_KT_MN"));
                    obj.setKT_TN_DDK(reset.getDouble("KT_TN_DDK"));
                    obj.setKT_PL_ND_KN_HC_T(reset.getDouble("KT_PL_ND_KN_HC_T"));
                    obj.setKT_PL_ND_KN_HC_DD(reset.getDouble("KT_PL_ND_KN_HC_DD"));
                    obj.setKT_PL_ND_KN_HC_NTS(reset.getDouble("KT_PL_ND_KN_HC_NTS"));
                    obj.setKT_PL_ND_KN_HC_CS(reset.getDouble("KT_PL_ND_KN_HC_CS"));
                    obj.setKT_PL_ND_KN_HC_CT(reset.getDouble("KT_PL_ND_KN_HC_CT"));
                    obj.setKT_PL_ND_KN_TP(reset.getDouble("KT_PL_ND_KN_TP"));
                    obj.setKT_PL_ND_KN_D(reset.getDouble("KT_PL_ND_KN_D"));
                    obj.setKT_PL_ND_TC_T(reset.getDouble("KT_PL_ND_TC_T"));
                    obj.setKT_PL_ND_TC_HC(reset.getDouble("KT_PL_ND_TC_HC"));
                    obj.setKT_PL_ND_TC_TP(reset.getDouble("KT_PL_ND_TC_TP"));
                    obj.setKT_PL_ND_TC_TN(reset.getDouble("KT_PL_ND_TC_TN"));
                    obj.setKT_PL_ND_TC_D(reset.getDouble("KT_PL_ND_TC_D"));
                    obj.setKT_PL_ND_TC_K(reset.getDouble("KT_PL_ND_TC_K"));
                    obj.setKT_PL_TQ_HC(reset.getDouble("KT_PL_TQ_HC"));
                    obj.setKT_PL_TQ_TP(reset.getDouble("KT_PL_TQ_TP"));
                    obj.setKT_PL_TQ_D(reset.getDouble("KT_PL_TQ_D"));
                    obj.setKT_PL_TT_CGQ(reset.getDouble("KT_PL_TT_CGQ"));
                    obj.setKT_PL_TT_DGQ1(reset.getDouble("KT_PL_TT_DGQ1"));
                    obj.setKT_PL_TT_GDQN(reset.getDouble("KT_PL_TT_GDQN"));
                    obj.setKT_DK(reset.getDouble("KT_DK"));
                    obj.setKT_KQ_SVB(reset.getDouble("KT_KQ_SVB"));
                    obj.setKT_KQ_CTQ(reset.getDouble("KT_KQ_CTQ"));
                    obj.setKT_KQ_SCV(reset.getDouble("KT_KQ_SCV"));
                    obj.setKT_KQ_TTQ_KN(reset.getDouble("KT_KQ_TTQ_KN"));
                    obj.setKT_KQ_TTQ_TC(reset.getDouble("KT_KQ_TTQ_TC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb09 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb09 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb09 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb09Model> get_ktnb09_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb09Model> dataList = new ArrayList<Ktnb09Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb09_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb09Model obj = new Ktnb09Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TN_TS(reset.getDouble("KT_TN_TS"));
                    obj.setKT_TN_TK_NN(reset.getDouble("KT_TN_TK_NN"));
                    obj.setKT_TN_TK_MN(reset.getDouble("KT_TN_TK_MN"));
                    obj.setKT_TN_KT_NN(reset.getDouble("KT_TN_KT_NN"));
                    obj.setKT_TN_KT_MN(reset.getDouble("KT_TN_KT_MN"));
                    obj.setKT_TN_DDK(reset.getDouble("KT_TN_DDK"));
                    obj.setKT_PL_ND_KN_HC_T(reset.getDouble("KT_PL_ND_KN_HC_T"));
                    obj.setKT_PL_ND_KN_HC_DD(reset.getDouble("KT_PL_ND_KN_HC_DD"));
                    obj.setKT_PL_ND_KN_HC_NTS(reset.getDouble("KT_PL_ND_KN_HC_NTS"));
                    obj.setKT_PL_ND_KN_HC_CS(reset.getDouble("KT_PL_ND_KN_HC_CS"));
                    obj.setKT_PL_ND_KN_HC_CT(reset.getDouble("KT_PL_ND_KN_HC_CT"));
                    obj.setKT_PL_ND_KN_TP(reset.getDouble("KT_PL_ND_KN_TP"));
                    obj.setKT_PL_ND_KN_D(reset.getDouble("KT_PL_ND_KN_D"));
                    obj.setKT_PL_ND_TC_T(reset.getDouble("KT_PL_ND_TC_T"));
                    obj.setKT_PL_ND_TC_HC(reset.getDouble("KT_PL_ND_TC_HC"));
                    obj.setKT_PL_ND_TC_TP(reset.getDouble("KT_PL_ND_TC_TP"));
                    obj.setKT_PL_ND_TC_TN(reset.getDouble("KT_PL_ND_TC_TN"));
                    obj.setKT_PL_ND_TC_D(reset.getDouble("KT_PL_ND_TC_D"));
                    obj.setKT_PL_ND_TC_K(reset.getDouble("KT_PL_ND_TC_K"));
                    obj.setKT_PL_TQ_HC(reset.getDouble("KT_PL_TQ_HC"));
                    obj.setKT_PL_TQ_TP(reset.getDouble("KT_PL_TQ_TP"));
                    obj.setKT_PL_TQ_D(reset.getDouble("KT_PL_TQ_D"));
                    obj.setKT_PL_TT_CGQ(reset.getDouble("KT_PL_TT_CGQ"));
                    obj.setKT_PL_TT_DGQ1(reset.getDouble("KT_PL_TT_DGQ1"));
                    obj.setKT_PL_TT_GDQN(reset.getDouble("KT_PL_TT_GDQN"));
                    obj.setKT_DK(reset.getDouble("KT_DK"));
                    obj.setKT_KQ_SVB(reset.getDouble("KT_KQ_SVB"));
                    obj.setKT_KQ_CTQ(reset.getDouble("KT_KQ_CTQ"));
                    obj.setKT_KQ_SCV(reset.getDouble("KT_KQ_SCV"));
                    obj.setKT_KQ_TTQ_KN(reset.getDouble("KT_KQ_TTQ_KN"));
                    obj.setKT_KQ_TTQ_TC(reset.getDouble("KT_KQ_TTQ_TC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb09 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb09 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb09 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb09(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_DV,List<String> KT_TN_TS,List<String> KT_TN_TK_NN,List<String> KT_TN_TK_MN,
            List<String> KT_TN_KT_NN,List<String> KT_TN_KT_MN,List<String> KT_TN_DDK,List<String> KT_PL_ND_KN_HC_T,
            List<String> KT_PL_ND_KN_HC_DD,List<String> KT_PL_ND_KN_HC_NTS,List<String> KT_PL_ND_KN_HC_CS,
            List<String> KT_PL_ND_KN_HC_CT,List<String> KT_PL_ND_KN_TP,List<String> KT_PL_ND_KN_D,
            List<String> KT_PL_ND_TC_T,List<String> KT_PL_ND_TC_HC,List<String> KT_PL_ND_TC_TP,
            List<String> KT_PL_ND_TC_TN,List<String> KT_PL_ND_TC_D,List<String> KT_PL_ND_TC_K,
            List<String> KT_PL_TQ_HC,List<String> KT_PL_TQ_TP,List<String> KT_PL_TQ_D,List<String> KT_PL_TT_CGQ,
            List<String> KT_PL_TT_DGQ1,List<String> KT_PL_TT_GDQN,List<String> KT_DK,List<String> KT_KQ_SVB,
            List<String> KT_KQ_CTQ,List<String> KT_KQ_SCV,List<String> KT_KQ_TTQ_KN,List<String> KT_KQ_TTQ_TC,
            List<String> KT_GHICHU,List<String> KT_DN,List<String> KT_CO_DINH,List<String> KT_THEM,List<String> KT_XOA,
            List<String> KT_FONTWEIGHT,List<String> KT_CAPHT,List<String> KT_STT,List<String> NG_CAPNHAT, String strAuth){
    
        try {
            if(strAuth == "")
                strAuth = "''";
            else strAuth = "'"+strAuth+"'";
            //1. XOA DU LIEU CU
//            String sqlDelete = "delete ktnb09 " +
//                    "WHERE kt_mapgd = '" + posCD + "'" +
//                    "    AND kt_quybc = " + quyBc +
//                    "    AND kt_nambc = " + namBc;
            
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();
            
            //Thuc hien xoa du lieu
//            stm.executeUpdate(sqlDelete);            
            
            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
                    
            for (int i = 0; i < KT_DV.size(); i++) {
                if(KT_GHICHU.size() == 0)
                {
                    sqlInsert = "INSERT INTO KTNB09(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_TN_TS,KT_TN_TK_NN,KT_TN_TK_MN,KT_TN_KT_NN,KT_TN_KT_MN,KT_TN_DDK,KT_PL_ND_KN_HC_T,KT_PL_ND_KN_HC_DD,KT_PL_ND_KN_HC_NTS,KT_PL_ND_KN_HC_CS,KT_PL_ND_KN_HC_CT,KT_PL_ND_KN_TP,KT_PL_ND_KN_D,KT_PL_ND_TC_T,KT_PL_ND_TC_HC,KT_PL_ND_TC_TP,KT_PL_ND_TC_TN,KT_PL_ND_TC_D,KT_PL_ND_TC_K,KT_PL_TQ_HC,KT_PL_TQ_TP,KT_PL_TQ_D,KT_PL_TT_CGQ,KT_PL_TT_DGQ1,KT_PL_TT_GDQN,KT_DK,KT_KQ_SVB,KT_KQ_CTQ,KT_KQ_SCV,KT_KQ_TTQ_KN,KT_KQ_TTQ_TC,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
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
                        KT_TN_TS.get(i)+","+ 
                        KT_TN_TK_NN.get(i)+","+ 
                        KT_TN_TK_MN.get(i)+","+ 
                        KT_TN_KT_NN.get(i)+","+ 
                        KT_TN_KT_MN.get(i)+","+ 
                        KT_TN_DDK.get(i)+","+ 
                        KT_PL_ND_KN_HC_T.get(i)+","+ 
                        KT_PL_ND_KN_HC_DD.get(i)+","+ 
                        KT_PL_ND_KN_HC_NTS.get(i)+","+ 
                        KT_PL_ND_KN_HC_CS.get(i)+","+ 
                        KT_PL_ND_KN_HC_CT.get(i)+","+ 
                        KT_PL_ND_KN_TP.get(i)+","+ 
                        KT_PL_ND_KN_D.get(i)+","+ 
                        KT_PL_ND_TC_T.get(i)+","+ 
                        KT_PL_ND_TC_HC.get(i)+","+ 
                        KT_PL_ND_TC_TP.get(i)+","+ 
                        KT_PL_ND_TC_TN.get(i)+","+ 
                        KT_PL_ND_TC_D.get(i)+","+ 
                        KT_PL_ND_TC_K.get(i)+","+ 
                        KT_PL_TQ_HC.get(i)+","+ 
                        KT_PL_TQ_TP.get(i)+","+ 
                        KT_PL_TQ_D.get(i)+","+ 
                        KT_PL_TT_CGQ.get(i)+","+ 
                        KT_PL_TT_DGQ1.get(i)+","+ 
                        KT_PL_TT_GDQN.get(i)+","+ 
                        KT_DK.get(i)+","+ 
                        KT_KQ_SVB.get(i)+","+ 
                        KT_KQ_CTQ.get(i)+","+ 
                        KT_KQ_SCV.get(i)+","+ 
                        KT_KQ_TTQ_KN.get(i)+","+ 
                        KT_KQ_TTQ_TC.get(i)+","+ 
                        "'"+' '+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate,"+strAuth +")";
                }
                else
                {
                    sqlInsert = "INSERT INTO KTNB09(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_TN_TS,KT_TN_TK_NN,KT_TN_TK_MN,KT_TN_KT_NN,KT_TN_KT_MN,KT_TN_DDK,KT_PL_ND_KN_HC_T,KT_PL_ND_KN_HC_DD,KT_PL_ND_KN_HC_NTS,KT_PL_ND_KN_HC_CS,KT_PL_ND_KN_HC_CT,KT_PL_ND_KN_TP,KT_PL_ND_KN_D,KT_PL_ND_TC_T,KT_PL_ND_TC_HC,KT_PL_ND_TC_TP,KT_PL_ND_TC_TN,KT_PL_ND_TC_D,KT_PL_ND_TC_K,KT_PL_TQ_HC,KT_PL_TQ_TP,KT_PL_TQ_D,KT_PL_TT_CGQ,KT_PL_TT_DGQ1,KT_PL_TT_GDQN,KT_DK,KT_KQ_SVB,KT_KQ_CTQ,KT_KQ_SCV,KT_KQ_TTQ_KN,KT_KQ_TTQ_TC,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
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
                        KT_TN_TS.get(i)+","+ 
                        KT_TN_TK_NN.get(i)+","+ 
                        KT_TN_TK_MN.get(i)+","+ 
                        KT_TN_KT_NN.get(i)+","+ 
                        KT_TN_KT_MN.get(i)+","+ 
                        KT_TN_DDK.get(i)+","+ 
                        KT_PL_ND_KN_HC_T.get(i)+","+ 
                        KT_PL_ND_KN_HC_DD.get(i)+","+ 
                        KT_PL_ND_KN_HC_NTS.get(i)+","+ 
                        KT_PL_ND_KN_HC_CS.get(i)+","+ 
                        KT_PL_ND_KN_HC_CT.get(i)+","+ 
                        KT_PL_ND_KN_TP.get(i)+","+ 
                        KT_PL_ND_KN_D.get(i)+","+ 
                        KT_PL_ND_TC_T.get(i)+","+ 
                        KT_PL_ND_TC_HC.get(i)+","+ 
                        KT_PL_ND_TC_TP.get(i)+","+ 
                        KT_PL_ND_TC_TN.get(i)+","+ 
                        KT_PL_ND_TC_D.get(i)+","+ 
                        KT_PL_ND_TC_K.get(i)+","+ 
                        KT_PL_TQ_HC.get(i)+","+ 
                        KT_PL_TQ_TP.get(i)+","+ 
                        KT_PL_TQ_D.get(i)+","+ 
                        KT_PL_TT_CGQ.get(i)+","+ 
                        KT_PL_TT_DGQ1.get(i)+","+ 
                        KT_PL_TT_GDQN.get(i)+","+ 
                        KT_DK.get(i)+","+ 
                        KT_KQ_SVB.get(i)+","+ 
                        KT_KQ_CTQ.get(i)+","+ 
                        KT_KQ_SCV.get(i)+","+ 
                        KT_KQ_TTQ_KN.get(i)+","+ 
                        KT_KQ_TTQ_TC.get(i)+","+ 
                        "'"+KT_GHICHU.get(i)+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate,"+strAuth +")";
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
            Logger.getLogger(DaoKtnb09.class.getName()).log(Level.SEVERE, null, ex);
//            CoreLogger.error(this.getClass().getName()+ " get_ktnb09 -> " + ex.getMessage());
            
            System.err.println("Loi trong ham save_ktnb09 " + posCD + " " + ex.getMessage());
            CoreLogger.error(this.getClass().getName() + " save_ktnb09 ---------> " + posCD + " " + ex.getMessage());
      
            return false;
        }
        
        return true;
                
    }    
    
    public ArrayList<Ktnb09Model> get_ktnb09_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb09Model> dataList = new ArrayList<Ktnb09Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb09_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb09Model obj = new Ktnb09Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_TN_TS(reset.getDouble("KT_TN_TS"));
                    obj.setKT_TN_TK_NN(reset.getDouble("KT_TN_TK_NN"));
                    obj.setKT_TN_TK_MN(reset.getDouble("KT_TN_TK_MN"));
                    obj.setKT_TN_KT_NN(reset.getDouble("KT_TN_KT_NN"));
                    obj.setKT_TN_KT_MN(reset.getDouble("KT_TN_KT_MN"));
                    obj.setKT_TN_DDK(reset.getDouble("KT_TN_DDK"));
                    obj.setKT_PL_ND_KN_HC_T(reset.getDouble("KT_PL_ND_KN_HC_T"));
                    obj.setKT_PL_ND_KN_HC_DD(reset.getDouble("KT_PL_ND_KN_HC_DD"));
                    obj.setKT_PL_ND_KN_HC_NTS(reset.getDouble("KT_PL_ND_KN_HC_NTS"));
                    obj.setKT_PL_ND_KN_HC_CS(reset.getDouble("KT_PL_ND_KN_HC_CS"));
                    obj.setKT_PL_ND_KN_HC_CT(reset.getDouble("KT_PL_ND_KN_HC_CT"));
                    obj.setKT_PL_ND_KN_TP(reset.getDouble("KT_PL_ND_KN_TP"));
                    obj.setKT_PL_ND_KN_D(reset.getDouble("KT_PL_ND_KN_D"));
                    obj.setKT_PL_ND_TC_T(reset.getDouble("KT_PL_ND_TC_T"));
                    obj.setKT_PL_ND_TC_HC(reset.getDouble("KT_PL_ND_TC_HC"));
                    obj.setKT_PL_ND_TC_TP(reset.getDouble("KT_PL_ND_TC_TP"));
                    obj.setKT_PL_ND_TC_TN(reset.getDouble("KT_PL_ND_TC_TN"));
                    obj.setKT_PL_ND_TC_D(reset.getDouble("KT_PL_ND_TC_D"));
                    obj.setKT_PL_ND_TC_K(reset.getDouble("KT_PL_ND_TC_K"));
                    obj.setKT_PL_TQ_HC(reset.getDouble("KT_PL_TQ_HC"));
                    obj.setKT_PL_TQ_TP(reset.getDouble("KT_PL_TQ_TP"));
                    obj.setKT_PL_TQ_D(reset.getDouble("KT_PL_TQ_D"));
                    obj.setKT_PL_TT_CGQ(reset.getDouble("KT_PL_TT_CGQ"));
                    obj.setKT_PL_TT_DGQ1(reset.getDouble("KT_PL_TT_DGQ1"));
                    obj.setKT_PL_TT_GDQN(reset.getDouble("KT_PL_TT_GDQN"));
                    obj.setKT_DK(reset.getDouble("KT_DK"));
                    obj.setKT_KQ_SVB(reset.getDouble("KT_KQ_SVB"));
                    obj.setKT_KQ_CTQ(reset.getDouble("KT_KQ_CTQ"));
                    obj.setKT_KQ_SCV(reset.getDouble("KT_KQ_SCV"));
                    obj.setKT_KQ_TTQ_KN(reset.getDouble("KT_KQ_TTQ_KN"));
                    obj.setKT_KQ_TTQ_TC(reset.getDouble("KT_KQ_TTQ_TC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb09 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb09 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb09 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb09_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB09 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_DV, KT_TN_TS, KT_TN_TK_NN, \n" +
"    KT_TN_TK_MN, KT_TN_KT_NN, KT_TN_KT_MN, KT_TN_DDK, KT_PL_ND_KN_HC_T, \n" +
"    KT_PL_ND_KN_HC_DD, KT_PL_ND_KN_HC_NTS, KT_PL_ND_KN_HC_CS, KT_PL_ND_KN_HC_CT, KT_PL_ND_KN_TP, \n" +
"    KT_PL_ND_KN_D, KT_PL_ND_TC_T, KT_PL_ND_TC_HC, KT_PL_ND_TC_TP, KT_PL_ND_TC_TN, \n" +
"    KT_PL_ND_TC_D, KT_PL_ND_TC_K, KT_PL_TQ_HC, KT_PL_TQ_TP, KT_PL_TQ_D, \n" +
"    KT_PL_TT_CGQ, KT_PL_TT_DGQ1, KT_PL_TT_GDQN, KT_DK, KT_KQ_SVB, \n" +
"    KT_KQ_CTQ, KT_KQ_SCV, KT_KQ_TTQ_KN, KT_KQ_TTQ_TC, KT_GHICHU, \n" +
"    KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, \n" +
"    KT_CAPHT, KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB09 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB09\n" +
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
            Logger.getLogger(DaoKtnb09.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb09_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb09_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
