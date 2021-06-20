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
import vbsp.ims.model.ktnb.Ktnb08Model;
import vbsp.ims.model.ktnb.Ktnb10Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb10 {
    public ArrayList<Ktnb10Model> get_ktnb10(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb10Model> dataList = new ArrayList<Ktnb10Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb10(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb10Model obj = new Ktnb10Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_DKN_TS(reset.getDouble("KT_DKN_TS"));
                    obj.setKT_DKN_TD_TK(reset.getDouble("KT_DKN_TD_TK"));
                    obj.setKT_DKN_TD_KT(reset.getDouble("KT_DKN_TD_KT"));
                    obj.setKT_DKN_TD_TS(reset.getDouble("KT_DKN_TD_TS"));
                    obj.setKT_KQ_DGQ_SD(reset.getDouble("KT_KQ_DGQ_SD"));
                    obj.setKT_KQ_DGQ_SVV(reset.getDouble("KT_KQ_DGQ_SVV"));
                    obj.setKT_KQ_DGQ_HC(reset.getDouble("KT_KQ_DGQ_HC"));
                    obj.setKT_KQ_DGQ_TP(reset.getDouble("KT_KQ_DGQ_TP"));
                    obj.setKT_KQ_PT_KND(reset.getDouble("KT_KQ_PT_KND"));
                    obj.setKT_KQ_PT_KNS(reset.getDouble("KT_KQ_PT_KNS"));
                    obj.setKT_KQ_PT_KND1(reset.getDouble("KT_KQ_PT_KND1"));
                    obj.setKT_KQ_PT_GQL1(reset.getDouble("KT_KQ_PT_GQL1"));
                    obj.setKT_KQ_PT_GQL2_CN(reset.getDouble("KT_KQ_PT_GQL2_CN"));
                    obj.setKT_KQ_PT_GQL2_HCS(reset.getDouble("KT_KQ_PT_GQL2_HCS"));
                    obj.setKT_KQ_KN_T(reset.getDouble("KT_KQ_KN_T"));
                    obj.setKT_KQ_KN_D(reset.getDouble("KT_KQ_KN_D"));
                    obj.setKT_KQ_TL_T(reset.getDouble("KT_KQ_TL_T"));
                    obj.setKT_KQ_TL_D(reset.getDouble("KT_KQ_TL_D"));
                    obj.setKT_KQ_SN(reset.getDouble("KT_KQ_SN"));
                    obj.setKT_KQ_KN_TS(reset.getDouble("KT_KQ_KN_TS"));
                    obj.setKT_KQ_KN_SN(reset.getDouble("KT_KQ_KN_SN"));
                    obj.setKT_KQ_CCQ_SV(reset.getDouble("KT_KQ_CCQ_SV"));
                    obj.setKT_KQ_CCQ_SDT(reset.getDouble("KT_KQ_CCQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_SV(reset.getDouble("KT_KQ_CCQ_KQ_SV"));
                    obj.setKT_KQ_CCQ_KQ_SDT(reset.getDouble("KT_KQ_CCQ_KQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_DTH(reset.getDouble("KT_KQ_CCQ_KQ_DTH"));
                    obj.setKT_KQ_CCQ_KQ_QTH(reset.getDouble("KT_KQ_CCQ_KQ_QTH"));
                    obj.setKT_TH_TS(reset.getDouble("KT_TH_TS"));
                    obj.setKT_TH_DTH(reset.getDouble("KT_TH_DTH"));
                    obj.setKT_TH_THNN_PT_T(reset.getDouble("KT_TH_THNN_PT_T"));
                    obj.setKT_TH_THNN_PT_D(reset.getDouble("KT_TH_THNN_PT_D"));
                    obj.setKT_TH_THNN_DT_T(reset.getDouble("KT_TH_THNN_DT_T"));
                    obj.setKT_TH_THNN_DT_D(reset.getDouble("KT_TH_THNN_DT_D"));
                    obj.setKT_TH_TL_PT_T(reset.getDouble("KT_TH_TL_PT_T"));
                    obj.setKT_TH_TL_PT_D(reset.getDouble("KT_TH_TL_PT_D"));
                    obj.setKT_TH_TL_DT_T(reset.getDouble("KT_TH_TL_DT_T"));
                    obj.setKT_TH_TL_DT_D(reset.getDouble("KT_TH_TL_DT_D"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb10 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb10 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb10 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb10Model> get_ktnb10_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb10Model> dataList = new ArrayList<Ktnb10Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb10_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb10Model obj = new Ktnb10Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_DKN_TS(reset.getDouble("KT_DKN_TS"));
                    obj.setKT_DKN_TD_TK(reset.getDouble("KT_DKN_TD_TK"));
                    obj.setKT_DKN_TD_KT(reset.getDouble("KT_DKN_TD_KT"));
                    obj.setKT_DKN_TD_TS(reset.getDouble("KT_DKN_TD_TS"));
                    obj.setKT_KQ_DGQ_SD(reset.getDouble("KT_KQ_DGQ_SD"));
                    obj.setKT_KQ_DGQ_SVV(reset.getDouble("KT_KQ_DGQ_SVV"));
                    obj.setKT_KQ_DGQ_HC(reset.getDouble("KT_KQ_DGQ_HC"));
                    obj.setKT_KQ_DGQ_TP(reset.getDouble("KT_KQ_DGQ_TP"));
                    obj.setKT_KQ_PT_KND(reset.getDouble("KT_KQ_PT_KND"));
                    obj.setKT_KQ_PT_KNS(reset.getDouble("KT_KQ_PT_KNS"));
                    obj.setKT_KQ_PT_KND1(reset.getDouble("KT_KQ_PT_KND1"));
                    obj.setKT_KQ_PT_GQL1(reset.getDouble("KT_KQ_PT_GQL1"));
                    obj.setKT_KQ_PT_GQL2_CN(reset.getDouble("KT_KQ_PT_GQL2_CN"));
                    obj.setKT_KQ_PT_GQL2_HCS(reset.getDouble("KT_KQ_PT_GQL2_HCS"));
                    obj.setKT_KQ_KN_T(reset.getDouble("KT_KQ_KN_T"));
                    obj.setKT_KQ_KN_D(reset.getDouble("KT_KQ_KN_D"));
                    obj.setKT_KQ_TL_T(reset.getDouble("KT_KQ_TL_T"));
                    obj.setKT_KQ_TL_D(reset.getDouble("KT_KQ_TL_D"));
                    obj.setKT_KQ_SN(reset.getDouble("KT_KQ_SN"));
                    obj.setKT_KQ_KN_TS(reset.getDouble("KT_KQ_KN_TS"));
                    obj.setKT_KQ_KN_SN(reset.getDouble("KT_KQ_KN_SN"));
                    obj.setKT_KQ_CCQ_SV(reset.getDouble("KT_KQ_CCQ_SV"));
                    obj.setKT_KQ_CCQ_SDT(reset.getDouble("KT_KQ_CCQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_SV(reset.getDouble("KT_KQ_CCQ_KQ_SV"));
                    obj.setKT_KQ_CCQ_KQ_SDT(reset.getDouble("KT_KQ_CCQ_KQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_DTH(reset.getDouble("KT_KQ_CCQ_KQ_DTH"));
                    obj.setKT_KQ_CCQ_KQ_QTH(reset.getDouble("KT_KQ_CCQ_KQ_QTH"));
                    obj.setKT_TH_TS(reset.getDouble("KT_TH_TS"));
                    obj.setKT_TH_DTH(reset.getDouble("KT_TH_DTH"));
                    obj.setKT_TH_THNN_PT_T(reset.getDouble("KT_TH_THNN_PT_T"));
                    obj.setKT_TH_THNN_PT_D(reset.getDouble("KT_TH_THNN_PT_D"));
                    obj.setKT_TH_THNN_DT_T(reset.getDouble("KT_TH_THNN_DT_T"));
                    obj.setKT_TH_THNN_DT_D(reset.getDouble("KT_TH_THNN_DT_D"));
                    obj.setKT_TH_TL_PT_T(reset.getDouble("KT_TH_TL_PT_T"));
                    obj.setKT_TH_TL_PT_D(reset.getDouble("KT_TH_TL_PT_D"));
                    obj.setKT_TH_TL_DT_T(reset.getDouble("KT_TH_TL_DT_T"));
                    obj.setKT_TH_TL_DT_D(reset.getDouble("KT_TH_TL_DT_D"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb10 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb10 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb10 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb10(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_DV,List<String> KT_DKN_TS,List<String> KT_DKN_TD_TK,List<String> KT_DKN_TD_KT,
            List<String> KT_DKN_TD_TS,List<String> KT_KQ_DGQ_SD,List<String> KT_KQ_DGQ_SVV,List<String> KT_KQ_DGQ_HC,
            List<String> KT_KQ_DGQ_TP,List<String> KT_KQ_PT_KND,List<String> KT_KQ_PT_KNS,List<String> KT_KQ_PT_KND1,
            List<String> KT_KQ_PT_GQL1,List<String> KT_KQ_PT_GQL2_CN,List<String> KT_KQ_PT_GQL2_HCS,
            List<String> KT_KQ_KN_T,List<String> KT_KQ_KN_D,List<String> KT_KQ_TL_T,List<String> KT_KQ_TL_D,
            List<String> KT_KQ_SN,List<String> KT_KQ_KN_TS,List<String> KT_KQ_KN_SN,List<String> KT_KQ_CCQ_SV,
            List<String> KT_KQ_CCQ_SDT,List<String> KT_KQ_CCQ_KQ_SV,List<String> KT_KQ_CCQ_KQ_SDT,
            List<String> KT_KQ_CCQ_KQ_DTH,List<String> KT_KQ_CCQ_KQ_QTH,List<String> KT_TH_TS,List<String> KT_TH_DTH,
            List<String> KT_TH_THNN_PT_T,List<String> KT_TH_THNN_PT_D,List<String> KT_TH_THNN_DT_T,
            List<String> KT_TH_THNN_DT_D,List<String> KT_TH_TL_PT_T,List<String> KT_TH_TL_PT_D,List<String> KT_TH_TL_DT_T,
            List<String> KT_TH_TL_DT_D,List<String> KT_GHICHU,List<String> KT_DN,List<String> KT_CO_DINH,
            List<String> KT_THEM,List<String> KT_XOA,List<String> KT_FONTWEIGHT,List<String> KT_CAPHT,
            List<String> KT_STT,List<String> NG_CAPNHAT, String strAuth){
    
        try {
            if(strAuth == "")
                strAuth = "''";
            else strAuth = "'"+strAuth+"'";
            //1. XOA DU LIEU CU
//            String sqlDelete = "delete ktnb10 " +
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
                    sqlInsert = "INSERT INTO KTNB10(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_DKN_TS,KT_DKN_TD_TK,KT_DKN_TD_KT,KT_DKN_TD_TS,KT_KQ_DGQ_SD,KT_KQ_DGQ_SVV,KT_KQ_DGQ_HC,KT_KQ_DGQ_TP,KT_KQ_PT_KND,KT_KQ_PT_KNS,KT_KQ_PT_KND1,KT_KQ_PT_GQL1,KT_KQ_PT_GQL2_CN,KT_KQ_PT_GQL2_HCS,KT_KQ_KN_T,KT_KQ_KN_D,KT_KQ_TL_T,KT_KQ_TL_D,KT_KQ_SN,KT_KQ_KN_TS,KT_KQ_KN_SN,KT_KQ_CCQ_SV,KT_KQ_CCQ_SDT,KT_KQ_CCQ_KQ_SV,KT_KQ_CCQ_KQ_SDT,KT_KQ_CCQ_KQ_DTH,KT_KQ_CCQ_KQ_QTH,KT_TH_TS,KT_TH_DTH,KT_TH_THNN_PT_T,KT_TH_THNN_PT_D,KT_TH_THNN_DT_T,KT_TH_THNN_DT_D,KT_TH_TL_PT_T,KT_TH_TL_PT_D,KT_TH_TL_DT_T,KT_TH_TL_DT_D,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
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
                        KT_DKN_TS.get(i)+","+ 
                        KT_DKN_TD_TK.get(i)+","+ 
                        KT_DKN_TD_KT.get(i)+","+ 
                        KT_DKN_TD_TS.get(i)+","+ 
                        KT_KQ_DGQ_SD.get(i)+","+ 
                        KT_KQ_DGQ_SVV.get(i)+","+ 
                        KT_KQ_DGQ_HC.get(i)+","+ 
                        KT_KQ_DGQ_TP.get(i)+","+ 
                        KT_KQ_PT_KND.get(i)+","+ 
                        KT_KQ_PT_KNS.get(i)+","+ 
                        KT_KQ_PT_KND1.get(i)+","+ 
                        KT_KQ_PT_GQL1.get(i)+","+ 
                        KT_KQ_PT_GQL2_CN.get(i)+","+ 
                        KT_KQ_PT_GQL2_HCS.get(i)+","+ 
                        KT_KQ_KN_T.get(i)+","+ 
                        KT_KQ_KN_D.get(i)+","+ 
                        KT_KQ_TL_T.get(i)+","+ 
                        KT_KQ_TL_D.get(i)+","+ 
                        KT_KQ_SN.get(i)+","+ 
                        KT_KQ_KN_TS.get(i)+","+ 
                        KT_KQ_KN_SN.get(i)+","+ 
                        KT_KQ_CCQ_SV.get(i)+","+ 
                        KT_KQ_CCQ_SDT.get(i)+","+ 
                        KT_KQ_CCQ_KQ_SV.get(i)+","+ 
                        KT_KQ_CCQ_KQ_SDT.get(i)+","+ 
                        KT_KQ_CCQ_KQ_DTH.get(i)+","+ 
                        KT_KQ_CCQ_KQ_QTH.get(i)+","+ 
                        KT_TH_TS.get(i)+","+ 
                        KT_TH_DTH.get(i)+","+ 
                        KT_TH_THNN_PT_T.get(i)+","+ 
                        KT_TH_THNN_PT_D.get(i)+","+ 
                        KT_TH_THNN_DT_T.get(i)+","+ 
                        KT_TH_THNN_DT_D.get(i)+","+ 
                        KT_TH_TL_PT_T.get(i)+","+ 
                        KT_TH_TL_PT_D.get(i)+","+ 
                        KT_TH_TL_DT_T.get(i)+","+ 
                        KT_TH_TL_DT_D.get(i)+","+ 
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
                    sqlInsert = "INSERT INTO KTNB10(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_DKN_TS,KT_DKN_TD_TK,KT_DKN_TD_KT,KT_DKN_TD_TS,KT_KQ_DGQ_SD,KT_KQ_DGQ_SVV,KT_KQ_DGQ_HC,KT_KQ_DGQ_TP,KT_KQ_PT_KND,KT_KQ_PT_KNS,KT_KQ_PT_KND1,KT_KQ_PT_GQL1,KT_KQ_PT_GQL2_CN,KT_KQ_PT_GQL2_HCS,KT_KQ_KN_T,KT_KQ_KN_D,KT_KQ_TL_T,KT_KQ_TL_D,KT_KQ_SN,KT_KQ_KN_TS,KT_KQ_KN_SN,KT_KQ_CCQ_SV,KT_KQ_CCQ_SDT,KT_KQ_CCQ_KQ_SV,KT_KQ_CCQ_KQ_SDT,KT_KQ_CCQ_KQ_DTH,KT_KQ_CCQ_KQ_QTH,KT_TH_TS,KT_TH_DTH,KT_TH_THNN_PT_T,KT_TH_THNN_PT_D,KT_TH_THNN_DT_T,KT_TH_THNN_DT_D,KT_TH_TL_PT_T,KT_TH_TL_PT_D,KT_TH_TL_DT_T,KT_TH_TL_DT_D,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH) "+
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
                        KT_DKN_TS.get(i)+","+ 
                        KT_DKN_TD_TK.get(i)+","+ 
                        KT_DKN_TD_KT.get(i)+","+ 
                        KT_DKN_TD_TS.get(i)+","+ 
                        KT_KQ_DGQ_SD.get(i)+","+ 
                        KT_KQ_DGQ_SVV.get(i)+","+ 
                        KT_KQ_DGQ_HC.get(i)+","+ 
                        KT_KQ_DGQ_TP.get(i)+","+ 
                        KT_KQ_PT_KND.get(i)+","+ 
                        KT_KQ_PT_KNS.get(i)+","+ 
                        KT_KQ_PT_KND1.get(i)+","+ 
                        KT_KQ_PT_GQL1.get(i)+","+ 
                        KT_KQ_PT_GQL2_CN.get(i)+","+ 
                        KT_KQ_PT_GQL2_HCS.get(i)+","+ 
                        KT_KQ_KN_T.get(i)+","+ 
                        KT_KQ_KN_D.get(i)+","+ 
                        KT_KQ_TL_T.get(i)+","+ 
                        KT_KQ_TL_D.get(i)+","+ 
                        KT_KQ_SN.get(i)+","+ 
                        KT_KQ_KN_TS.get(i)+","+ 
                        KT_KQ_KN_SN.get(i)+","+ 
                        KT_KQ_CCQ_SV.get(i)+","+ 
                        KT_KQ_CCQ_SDT.get(i)+","+ 
                        KT_KQ_CCQ_KQ_SV.get(i)+","+ 
                        KT_KQ_CCQ_KQ_SDT.get(i)+","+ 
                        KT_KQ_CCQ_KQ_DTH.get(i)+","+ 
                        KT_KQ_CCQ_KQ_QTH.get(i)+","+ 
                        KT_TH_TS.get(i)+","+ 
                        KT_TH_DTH.get(i)+","+ 
                        KT_TH_THNN_PT_T.get(i)+","+ 
                        KT_TH_THNN_PT_D.get(i)+","+ 
                        KT_TH_THNN_DT_T.get(i)+","+ 
                        KT_TH_THNN_DT_D.get(i)+","+ 
                        KT_TH_TL_PT_T.get(i)+","+ 
                        KT_TH_TL_PT_D.get(i)+","+ 
                        KT_TH_TL_DT_T.get(i)+","+ 
                        KT_TH_TL_DT_D.get(i)+","+ 
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
            Logger.getLogger(DaoKtnb09.class.getName()).log(Level.SEVERE, null, ex);
//            CoreLogger.error(this.getClass().getName()+ " get_ktnb10 -> " + ex.getMessage());
            
            System.err.println("Loi trong ham save_ktnb10 " + posCD + " " + ex.getMessage());
            CoreLogger.error(this.getClass().getName() + " save_ktnb10 ---------> " + posCD + " " + ex.getMessage());
      
            
            return false;
        }
        
        return true;
                
    }   
    
    public ArrayList<Ktnb10Model> get_ktnb10_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb10Model> dataList = new ArrayList<Ktnb10Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb10_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb10Model obj = new Ktnb10Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_DKN_TS(reset.getDouble("KT_DKN_TS"));
                    obj.setKT_DKN_TD_TK(reset.getDouble("KT_DKN_TD_TK"));
                    obj.setKT_DKN_TD_KT(reset.getDouble("KT_DKN_TD_KT"));
                    obj.setKT_DKN_TD_TS(reset.getDouble("KT_DKN_TD_TS"));
                    obj.setKT_KQ_DGQ_SD(reset.getDouble("KT_KQ_DGQ_SD"));
                    obj.setKT_KQ_DGQ_SVV(reset.getDouble("KT_KQ_DGQ_SVV"));
                    obj.setKT_KQ_DGQ_HC(reset.getDouble("KT_KQ_DGQ_HC"));
                    obj.setKT_KQ_DGQ_TP(reset.getDouble("KT_KQ_DGQ_TP"));
                    obj.setKT_KQ_PT_KND(reset.getDouble("KT_KQ_PT_KND"));
                    obj.setKT_KQ_PT_KNS(reset.getDouble("KT_KQ_PT_KNS"));
                    obj.setKT_KQ_PT_KND1(reset.getDouble("KT_KQ_PT_KND1"));
                    obj.setKT_KQ_PT_GQL1(reset.getDouble("KT_KQ_PT_GQL1"));
                    obj.setKT_KQ_PT_GQL2_CN(reset.getDouble("KT_KQ_PT_GQL2_CN"));
                    obj.setKT_KQ_PT_GQL2_HCS(reset.getDouble("KT_KQ_PT_GQL2_HCS"));
                    obj.setKT_KQ_KN_T(reset.getDouble("KT_KQ_KN_T"));
                    obj.setKT_KQ_KN_D(reset.getDouble("KT_KQ_KN_D"));
                    obj.setKT_KQ_TL_T(reset.getDouble("KT_KQ_TL_T"));
                    obj.setKT_KQ_TL_D(reset.getDouble("KT_KQ_TL_D"));
                    obj.setKT_KQ_SN(reset.getDouble("KT_KQ_SN"));
                    obj.setKT_KQ_KN_TS(reset.getDouble("KT_KQ_KN_TS"));
                    obj.setKT_KQ_KN_SN(reset.getDouble("KT_KQ_KN_SN"));
                    obj.setKT_KQ_CCQ_SV(reset.getDouble("KT_KQ_CCQ_SV"));
                    obj.setKT_KQ_CCQ_SDT(reset.getDouble("KT_KQ_CCQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_SV(reset.getDouble("KT_KQ_CCQ_KQ_SV"));
                    obj.setKT_KQ_CCQ_KQ_SDT(reset.getDouble("KT_KQ_CCQ_KQ_SDT"));
                    obj.setKT_KQ_CCQ_KQ_DTH(reset.getDouble("KT_KQ_CCQ_KQ_DTH"));
                    obj.setKT_KQ_CCQ_KQ_QTH(reset.getDouble("KT_KQ_CCQ_KQ_QTH"));
                    obj.setKT_TH_TS(reset.getDouble("KT_TH_TS"));
                    obj.setKT_TH_DTH(reset.getDouble("KT_TH_DTH"));
                    obj.setKT_TH_THNN_PT_T(reset.getDouble("KT_TH_THNN_PT_T"));
                    obj.setKT_TH_THNN_PT_D(reset.getDouble("KT_TH_THNN_PT_D"));
                    obj.setKT_TH_THNN_DT_T(reset.getDouble("KT_TH_THNN_DT_T"));
                    obj.setKT_TH_THNN_DT_D(reset.getDouble("KT_TH_THNN_DT_D"));
                    obj.setKT_TH_TL_PT_T(reset.getDouble("KT_TH_TL_PT_T"));
                    obj.setKT_TH_TL_PT_D(reset.getDouble("KT_TH_TL_PT_D"));
                    obj.setKT_TH_TL_DT_T(reset.getDouble("KT_TH_TL_DT_T"));
                    obj.setKT_TH_TL_DT_D(reset.getDouble("KT_TH_TL_DT_D"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb10 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb10 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb10 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb10_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB10 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_DV, KT_DKN_TS, KT_DKN_TD_TK, \n" +
"    KT_DKN_TD_KT, KT_DKN_TD_TS, KT_KQ_DGQ_SD, KT_KQ_DGQ_SVV, KT_KQ_DGQ_HC, \n" +
"    KT_KQ_DGQ_TP, KT_KQ_PT_KND, KT_KQ_PT_KNS, KT_KQ_PT_KND1, KT_KQ_PT_GQL1, \n" +
"    KT_KQ_PT_GQL2_CN, KT_KQ_PT_GQL2_HCS, KT_KQ_KN_T, KT_KQ_KN_D, KT_KQ_TL_T, \n" +
"    KT_KQ_TL_D, KT_KQ_SN, KT_KQ_KN_TS, KT_KQ_KN_SN, KT_KQ_CCQ_SV, \n" +
"    KT_KQ_CCQ_SDT, KT_KQ_CCQ_KQ_SV, KT_KQ_CCQ_KQ_SDT, KT_KQ_CCQ_KQ_DTH, KT_KQ_CCQ_KQ_QTH, \n" +
"    KT_TH_TS, KT_TH_DTH, KT_TH_THNN_PT_T, KT_TH_THNN_PT_D, KT_TH_THNN_DT_T, \n" +
"    KT_TH_THNN_DT_D, KT_TH_TL_PT_T, KT_TH_TL_PT_D, KT_TH_TL_DT_T, KT_TH_TL_DT_D, \n" +
"    KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, \n" +
"    KT_FONTWEIGHT, KT_CAPHT, KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB10 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB10\n" +
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
            Logger.getLogger(DaoKtnb10.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb10_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb10_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
