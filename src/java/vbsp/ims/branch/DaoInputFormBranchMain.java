/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class DaoInputFormBranchMain {

    public boolean saveTenMauBieu(String khoa, String username, String tenmau, String capnhap,
            List<ModelParameter> parameters, String Add_Edit, String donvitinh, String copydl,
            String groupRpt_id, String dongbo_dl) throws Exception {
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            conn.setAutoCommit(false);

            CallableStatement calstatementSaveMau = null;
            CallableStatement calstatementSaveThamso = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String sqlSaveMau = "{call INPUT_BRANCH.SAVE_TENMAUBIEU(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            String sqlSaveThamso = "{call INPUT_BRANCH.SAVE_THAMSO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            //Khoi tao goi store cho phần lưu mẫu
            calstatementSaveMau = conn.prepareCall(sqlSaveMau, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatementSaveMau.setString(1, khoa);
            calstatementSaveMau.setString(2, username);
            calstatementSaveMau.setString(3, tenmau);
            calstatementSaveMau.setString(4, capnhap);
            calstatementSaveMau.setString(5, Add_Edit);
            calstatementSaveMau.setString(6, donvitinh);
            calstatementSaveMau.setString(7, copydl);
            calstatementSaveMau.setString(8, groupRpt_id);
             calstatementSaveMau.setString(9, dongbo_dl);
            //Thuc hien execute lay du lieu
            calstatementSaveMau.execute();

            //Gọi procedure cho phần lưu tham số
            calstatementSaveThamso = conn.prepareCall(sqlSaveThamso, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            for (ModelParameter modelParameter : parameters) {
                calstatementSaveThamso.setString(1, khoa);
                calstatementSaveThamso.setString(2, modelParameter.getThamso());
                calstatementSaveThamso.setString(3, modelParameter.getLoaitso());
                calstatementSaveThamso.setString(4, modelParameter.getMota());
                calstatementSaveThamso.setInt(5, modelParameter.getStt());
                calstatementSaveThamso.setString(6, modelParameter.getBangsl() == null ? "" : modelParameter.getBangsl());
                calstatementSaveThamso.setString(7, modelParameter.getCothienthi() == null ? "" : modelParameter.getCothienthi());
                calstatementSaveThamso.setString(8, modelParameter.getCottso() == null ? "" : modelParameter.getCottso());
                calstatementSaveThamso.setString(9, modelParameter.getDkloc() == null ? "" : modelParameter.getDkloc());
                calstatementSaveThamso.setString(10, modelParameter.getDksapxep() == null ? "" : modelParameter.getDksapxep());
                calstatementSaveThamso.addBatch();
                //Thuc hien execute lay du lieu

            }
            calstatementSaveThamso.executeBatch();

            conn.commit();

            if (calstatementSaveMau != null) {
                calstatementSaveMau.close();
            }
            if (calstatementSaveThamso != null) {
                calstatementSaveThamso.close();
            }
            bSuccess = true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Loi trong ham saveTenMauBieu " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTenMauBieu -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public boolean saveThamso(String khoa, ModelParameter modelParameter) throws Exception {
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SAVE_THAMSO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, khoa);
            calstatement.setString(2, modelParameter.getThamso());
            calstatement.setString(3, modelParameter.getLoaitso());
            calstatement.setString(4, modelParameter.getMota());
            calstatement.setInt(5, modelParameter.getStt());
            calstatement.setString(6, modelParameter.getBangsl() == null ? "" : modelParameter.getBangsl());
            calstatement.setString(7, modelParameter.getCothienthi() == null ? "" : modelParameter.getCothienthi());
            calstatement.setString(8, modelParameter.getCottso() == null ? "" : modelParameter.getCottso());
            calstatement.setString(9, modelParameter.getDkloc() == null ? "" : modelParameter.getDkloc());
            calstatement.setString(10, modelParameter.getDksapxep() == null ? "" : modelParameter.getDksapxep());

            //Thuc hien execute lay du lieu
            calstatement.execute();

            if (calstatement != null) {
                calstatement.close();
            }

            bSuccess = true;
        } catch (SQLException e) {
            System.err.println("Loi trong ham saveTenMauBieu " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTenMauBieu -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public List<ListValue> getAllNhaptayBranch(String username, String capbc, String edit, String nhombc) throws Exception {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_ALL_BC(?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.setString(1, username);
            calstatement.setString(2, capbc);
            calstatement.setString(3, edit);
            calstatement.setString(4, nhombc);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            int stt = 1;
            while (reset.next()) {
                String key = reset.getString(1);
                String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                lstAllBcqt.add(new ListValue(key, des, Integer.toString(stt)));
                stt++;
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getAllNhaptayBranch " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllNhaptayBranch -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lstAllBcqt;
    }

    public List<ListValue> getDmucTso() {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_DMUC_TSO(?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(1);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getDmucTso -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDmucTso " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmucTso -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public boolean saveConfigBranch(List<DULIEU_NT_CN> lstDulieu, String type, String username) throws Exception {
//        SP_SAVE_CONFIG_BRANCH(TAB_DULIEU IN TAB_DULIEU_NT_CN, PV_TYPE IN VARCHAR, PV_USERNAME IN VARCHAR2)
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_SAVE_CONFIG_BRANCH(?, ?, ?)}";
            ResultSet reset = null;

            Object array[] = lstDulieu.toArray();
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor(DULIEU_NT_CN.ORACLE_TABLE_TYPE, conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setArray(1, array_to_pass);
            calstatement.setString(2, type);
            calstatement.setString(3, username);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            if (calstatement != null) {
                calstatement.close();
            }

            bSuccess = true;
        } catch (SQLException e) {
            System.err.println("Loi trong ham saveConfigBranch " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveConfigBranch -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public boolean saveChitieuCot(List<DULIEU_NT_CN> lstChitieu, List<DULIEU_NT_CN> lstCot, String username) throws Exception {
//        SP_SAVE_CONFIG_BRANCH(TAB_DULIEU IN TAB_DULIEU_NT_CN, PV_TYPE IN VARCHAR, PV_USERNAME IN VARCHAR2)
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_SAVE_CHITIEU_COT(?, ?, ?)}";
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor(DULIEU_NT_CN.ORACLE_TABLE_TYPE, conn);

            Object arrayCT[] = lstChitieu.toArray();
            ARRAY array_to_pass_ct = new ARRAY(des, conn, arrayCT);

            Object arrayCot[] = lstCot.toArray();
            ARRAY array_to_pass_cot = new ARRAY(des, conn, arrayCot);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setArray(1, array_to_pass_ct);
            calstatement.setArray(2, array_to_pass_cot);
            calstatement.setString(3, username);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            if (calstatement != null) {
                calstatement.close();
            }

            bSuccess = true;
        } catch (SQLException e) {
            System.err.println("Loi trong ham saveChitieuCot " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveChitieuCot -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public boolean deleteMaubaocao(String khoa) throws Exception {
//        SP_SAVE_CONFIG_BRANCH(TAB_DULIEU IN TAB_DULIEU_NT_CN, PV_TYPE IN VARCHAR, PV_USERNAME IN VARCHAR2)
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_DELETE_MAUBC(?)}";

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.setString(1, khoa);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            if (calstatement != null) {
                calstatement.close();
            }

            bSuccess = true;
        } catch (SQLException e) {
            System.err.println("Loi trong ham deleteMaubaocao " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteMaubaocao -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public List<DULIEU_NT_CN> getDulieuMauCauhinh(String khoa) throws Exception {
        Connection conn = null;
        List<DULIEU_NT_CN> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_CONFIG(?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                DULIEU_NT_CN value = DULIEU_NT_CN.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setCO_TONGHOP(reset.getString(9));
                value.setMACN(reset.getString(10));
                value.setNGUOI_NHAP(reset.getString(11));
                value.setNGAY_NHAP(reset.getDate(12));
                value.setNGUOI_DUYET(reset.getString(13));
                value.setNGAY_DUYET(reset.getDate(14));
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
                value.setD31(reset.getString(45));
                value.setD32(reset.getString(46));
                value.setD33(reset.getString(47));
                value.setD34(reset.getString(48));
                value.setD35(reset.getString(49));
                value.setD36(reset.getString(50));
                value.setD37(reset.getString(51));
                value.setD38(reset.getString(52));
                value.setD39(reset.getString(53));
                value.setD40(reset.getString(54));
                value.setD41(reset.getString(55));
                value.setD42(reset.getString(56));
                value.setD43(reset.getString(57));
                value.setD44(reset.getString(58));
                value.setD45(reset.getString(59));
                value.setD46(reset.getString(60));
                value.setD47(reset.getString(61));
                value.setD48(reset.getString(62));
                value.setD49(reset.getString(63));
                value.setD50(reset.getString(64));
                value.setNHAPTAY(reset.getString(65));
                value.setFONTFORMAT(reset.getString(66));
                value.setKIEUIN(reset.getInt(67));
                value.setKIEUDULIEU(reset.getString(68));

                lstDulieu.add(value);
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuMauCauhinh " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuMauCauhinh -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lstDulieu;
    }

    public List<MappingCot> getDulieuMappingCot(String khoa) throws Exception {
        Connection conn = null;
        List<MappingCot> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_GET_MAPPINGCOT(?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 

            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                MappingCot value = new MappingCot();
                value.setKHOA(reset.getString(1));
                value.setLOAI(reset.getString(2));
                value.setTHUTU(reset.getInt(3));
                value.setTRUONGDL(reset.getString(4));
                value.setTENHIENTHI(reset.getString(5));
                value.setKIEUDULIEU(reset.getString(6));
                value.setNGAYTAO(reset.getDate(7));
                value.setBANGSL(reset.getString(8));
                value.setCOTHIENTHI(reset.getString(9));
                value.setCOTTSO(reset.getString(10));
                value.setDKLOC(reset.getString(11));
                value.setDKSAPXEP(reset.getString(12));
                lstDulieu.add(value);
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuMappingCot " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuMappingCot -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lstDulieu;
    }

    public LoadEditMauView getMaubieuEdit(String khoa) throws Exception {
        Connection conn = null;
        LoadEditMauView editView = new LoadEditMauView();
        List<ModelParameter> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_MAUBIEU_EDIT(?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 

            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                editView.setTenmau(reset.getString(2));
                String loaibc = reset.getString(3);
                if (loaibc == null || !loaibc.equals("Y")) {
                    editView.setDongbo_dl(false);
                } else {
                    editView.setDongbo_dl(true);
                }
                editView.setCapbc(reset.getString(4));
                String copydl = reset.getString(5);
                editView.setCopydl(copydl.equals("Y") ? true : false);
                editView.setDonvitinh(reset.getString(8));
                editView.setNhombc(reset.getString(14));
            }
            if (reset != null) {
                reset.close();
            }
            //Lay du lieu cho tham so
            reset = (ResultSet) calstatement.getObject(3);
            while (reset.next()) {
                ModelParameter value = new ModelParameter();
                value.setKhoa(reset.getString(1));
                value.setThamso(reset.getString(2));
                value.setLoaitso(reset.getString(3));
                value.setMota(reset.getString(4));
                value.setStt(reset.getInt(5));
                value.setBangsl(reset.getString(6));
                value.setCothienthi(reset.getString(7));
                value.setCottso(reset.getString(8));
                value.setDkloc(reset.getString(9));
                value.setStatus(reset.getString(10));
                value.setDksapxep(reset.getString(11));
                lstDulieu.add(value);
            }

            editView.setLstParameter(lstDulieu);
            if (reset != null) {
                reset.close();
            }

            if (calstatement != null) {
                calstatement.close();
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getThamsoEdit " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getThamsoEdit -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return editView;
    }

    public LoadEditMauView getThamsoEdit(String khoa) throws Exception {
        Connection conn = null;
        LoadEditMauView editView = new LoadEditMauView();
        List<ModelParameter> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_PARAMETER_CONFIG(?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 

            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            String tenmau = calstatement.getString(2);
            String capbc = calstatement.getString(3);
            String donvitinh = calstatement.getString(4);
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {
                ModelParameter value = new ModelParameter();
                value.setKhoa(reset.getString(1));
                value.setThamso(reset.getString(2));
                value.setLoaitso(reset.getString(3));
                value.setMota(reset.getString(4));
                value.setStt(reset.getInt(5));
                value.setBangsl(reset.getString(6));
                value.setCothienthi(reset.getString(7));
                value.setCottso(reset.getString(8));
                value.setDkloc(reset.getString(9));
                value.setStatus(reset.getString(10));
                value.setDksapxep(reset.getString(11));
                lstDulieu.add(value);
            }
            editView.setTenmau(tenmau);
            editView.setCapbc(capbc);
            editView.setDonvitinh(donvitinh);
            editView.setLstParameter(lstDulieu);
            if (reset != null) {
                reset.close();
            }

            if (calstatement != null) {
                calstatement.close();
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getThamsoEdit " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getThamsoEdit -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return editView;
    }

    private DULIEU_NT_CN setParamterClass(DULIEU_NT_CN dulieu, List<DULIEU_NT_CN> lstParameter) {
        DULIEU_NT_CN returnDulieu = dulieu;
        for (DULIEU_NT_CN value : lstParameter) {
            switch (value.getMA().toUpperCase()) {
                case "THAMSO_1":
                    returnDulieu.setTHAMSO_1(value.getTEN());
                    break;
                case "THAMSO_2":
                    returnDulieu.setTHAMSO_2(value.getTEN());
                    break;
                case "THAMSO_3":
                    returnDulieu.setTHAMSO_3(value.getTEN());
                    break;
                case "THAMSO_4":
                    returnDulieu.setTHAMSO_4(value.getTEN());
                    break;
                case "THAMSO_5":
                    returnDulieu.setTHAMSO_5(value.getTEN());
                    break;
                case "THAMSO_6":
                    returnDulieu.setTHAMSO_6(value.getTEN());
                    break;
                case "THAMSO_7":
                    returnDulieu.setTHAMSO_7(value.getTEN());
                    break;
                case "THAMSO_8":
                    returnDulieu.setTHAMSO_8(value.getTEN());
                    break;
                case "THAMSO_9":
                    returnDulieu.setTHAMSO_9(value.getTEN());
                    break;
                case "THAMSO_10":
                    returnDulieu.setTHAMSO_10(value.getTEN());
                    break;

            }
        }
        return returnDulieu;
    }

    public List<DULIEU_NT_CN> getDulieuBaocaoNhap(String khoa, List<DULIEU_NT_CN> lstParameter, String username, String capbc) throws Exception {
        Connection conn = null;
        List<DULIEU_NT_CN> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_DATA_BC(?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 

            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor(DULIEU_NT_CN.ORACLE_TABLE_TYPE, conn);

            Object array[] = lstParameter.toArray();
            ARRAY array_to_pass = new ARRAY(des, conn, array);

            calstatement.setString(1, khoa);
            calstatement.setArray(2, array_to_pass);
            calstatement.setString(3, username);
            calstatement.setString(4, capbc);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {
                DULIEU_NT_CN value = DULIEU_NT_CN.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setCO_TONGHOP(reset.getString(9));
                value.setMACN(reset.getString(10));
                value.setNGUOI_NHAP(reset.getString(11));
                value.setNGAY_NHAP(reset.getDate(12));
                value.setNGUOI_DUYET(reset.getString(13));
                value.setNGAY_DUYET(reset.getDate(14));
                value.setTHAMSO_1(reset.getString(15));
                value.setTHAMSO_2(reset.getString(16));
                value.setTHAMSO_3(reset.getString(17));
                value.setTHAMSO_4(reset.getString(18));
                value.setTHAMSO_5(reset.getString(19));
                value.setTHAMSO_6(reset.getString(20));
                value.setTHAMSO_7(reset.getString(21));
                value.setTHAMSO_8(reset.getString(22));
                value.setTHAMSO_9(reset.getString(23));
                value.setTHAMSO_10(reset.getString(24));
                value.setD1(reset.getString(25));
                value.setD2(reset.getString(26));
                value.setD3(reset.getString(27));
                value.setD4(reset.getString(28));
                value.setD5(reset.getString(29));
                value.setD6(reset.getString(30));
                value.setD7(reset.getString(31));
                value.setD8(reset.getString(32));
                value.setD9(reset.getString(33));
                value.setD10(reset.getString(34));
                value.setD11(reset.getString(35));
                value.setD12(reset.getString(36));
                value.setD13(reset.getString(37));
                value.setD14(reset.getString(38));
                value.setD15(reset.getString(39));
                value.setD16(reset.getString(40));
                value.setD17(reset.getString(41));
                value.setD18(reset.getString(42));
                value.setD19(reset.getString(43));
                value.setD20(reset.getString(44));
                value.setD21(reset.getString(45));
                value.setD22(reset.getString(46));
                value.setD23(reset.getString(47));
                value.setD24(reset.getString(48));
                value.setD25(reset.getString(49));
                value.setD26(reset.getString(50));
                value.setD27(reset.getString(51));
                value.setD28(reset.getString(52));
                value.setD29(reset.getString(53));
                value.setD30(reset.getString(54));
                value.setD31(reset.getString(55));
                value.setD32(reset.getString(56));
                value.setD33(reset.getString(57));
                value.setD34(reset.getString(58));
                value.setD35(reset.getString(59));
                value.setD36(reset.getString(60));
                value.setD37(reset.getString(61));
                value.setD38(reset.getString(62));
                value.setD39(reset.getString(63));
                value.setD40(reset.getString(64));
                value.setD41(reset.getString(65));
                value.setD42(reset.getString(66));
                value.setD43(reset.getString(67));
                value.setD44(reset.getString(68));
                value.setD45(reset.getString(69));
                value.setD46(reset.getString(70));
                value.setD47(reset.getString(71));
                value.setD48(reset.getString(72));
                value.setD49(reset.getString(73));
                value.setD50(reset.getString(74));
                value.setNHAPTAY(reset.getString(75));
                value.setFONTFORMAT(reset.getString(76));
                value.setKIEUIN(reset.getInt(77));
                value.setKIEUDULIEU(reset.getString(78));
                setParamterClass(value, lstParameter);
                lstDulieu.add(value);
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuBaocaoNhap " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuBaocaoNhap -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lstDulieu;
    }

    public List<String> getDulieuSync(String khoa, List<DULIEU_NT_CN> lstParameter, String username, String capbc) throws Exception {
        Connection conn = null;
        List<String> lstDulieu = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_GET_DATA_SYNC(?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 

            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor(DULIEU_NT_CN.ORACLE_TABLE_TYPE, conn);

            Object array[] = lstParameter.toArray();
            ARRAY array_to_pass = new ARRAY(des, conn, array);

            calstatement.setString(1, khoa);
            calstatement.setArray(2, array_to_pass);
            calstatement.setString(3, username);
            calstatement.setString(4, capbc);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {
                String xml_string=reset.getString(1);
                lstDulieu.add(xml_string);
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuSync " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuSync -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lstDulieu;
    }
    
    public HashMap<String, Object> getCauhinhChitieuCot1(String khoa) throws Exception {
        Connection conn = null;

        HashMap<String, Object> mapDulieu = new HashMap<String, Object>();
        try {
            List<DULIEU_NT_CN> lstDulieuChitieu = new ArrayList<>();
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_CHITIEU_COT(?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                DULIEU_NT_CN value = DULIEU_NT_CN.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setCO_TONGHOP(reset.getString(9));
                value.setMACN(reset.getString(10));
                value.setNGUOI_NHAP(reset.getString(11));
                value.setNGAY_NHAP(reset.getDate(12));
                value.setNGUOI_DUYET(reset.getString(13));
                value.setNGAY_DUYET(reset.getDate(14));
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
                value.setD31(reset.getString(45));
                value.setD32(reset.getString(46));
                value.setD33(reset.getString(47));
                value.setD34(reset.getString(48));
                value.setD35(reset.getString(49));
                value.setD36(reset.getString(50));
                value.setD37(reset.getString(51));
                value.setD38(reset.getString(52));
                value.setD39(reset.getString(53));
                value.setD40(reset.getString(54));
                value.setD41(reset.getString(55));
                value.setD42(reset.getString(56));
                value.setD43(reset.getString(57));
                value.setD44(reset.getString(58));
                value.setD45(reset.getString(59));
                value.setD46(reset.getString(60));
                value.setD47(reset.getString(61));
                value.setD48(reset.getString(62));
                value.setD49(reset.getString(63));
                value.setD50(reset.getString(64));
                value.setNHAPTAY(reset.getString(65));
                value.setFONTFORMAT(reset.getString(66));
                value.setKIEUIN(reset.getInt(67));
                value.setKIEUDULIEU(reset.getString(68));

                lstDulieuChitieu.add(value);
            }
            mapDulieu.put("CHITIEU", lstDulieuChitieu);
            if (reset != null) {
                reset.close();
            }
            //List<DULIEU_NT_CN> lstDulieuCot = new ArrayList<>();
            List<MappingCot> lstDulieuCot = new ArrayList<>();
            reset = (ResultSet) calstatement.getObject(3);
            while (reset.next()) {
                MappingCot value = new MappingCot();
                value.setKHOA(reset.getString(1));
                value.setLOAI(reset.getString(2));
                value.setTHUTU(reset.getInt(3));
                value.setTRUONGDL(reset.getString(4));
                value.setTENHIENTHI(reset.getString(5));
                value.setKIEUDULIEU(reset.getString(6));
                value.setNGAYTAO(reset.getDate(7));
                value.setBANGSL(reset.getString(8));
                value.setCOTHIENTHI(reset.getString(9));
                value.setCOTTSO(reset.getString(10));
                value.setDKLOC(reset.getString(11));
                value.setDKSAPXEP(reset.getString(12));
                lstDulieuCot.add(value);
            }
            mapDulieu.put("COT", lstDulieuCot);

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuMauCauhinh " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuMauCauhinh -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return mapDulieu;
    }

    public HashMap<String, List<DULIEU_NT_CN>> getCauhinhChitieuCot(String khoa) throws Exception {
        Connection conn = null;

        HashMap<String, List<DULIEU_NT_CN>> mapDulieu = new HashMap<String, List<DULIEU_NT_CN>>();
        try {
            List<DULIEU_NT_CN> lstDulieuChitieu = new ArrayList<>();
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_CHITIEU_COT(?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, khoa);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                DULIEU_NT_CN value = DULIEU_NT_CN.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setCO_TONGHOP(reset.getString(9));
                value.setMACN(reset.getString(10));
                value.setNGUOI_NHAP(reset.getString(11));
                value.setNGAY_NHAP(reset.getDate(12));
                value.setNGUOI_DUYET(reset.getString(13));
                value.setNGAY_DUYET(reset.getDate(14));
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
                value.setD31(reset.getString(45));
                value.setD32(reset.getString(46));
                value.setD33(reset.getString(47));
                value.setD34(reset.getString(48));
                value.setD35(reset.getString(49));
                value.setD36(reset.getString(50));
                value.setD37(reset.getString(51));
                value.setD38(reset.getString(52));
                value.setD39(reset.getString(53));
                value.setD40(reset.getString(54));
                value.setD41(reset.getString(55));
                value.setD42(reset.getString(56));
                value.setD43(reset.getString(57));
                value.setD44(reset.getString(58));
                value.setD45(reset.getString(59));
                value.setD46(reset.getString(60));
                value.setD47(reset.getString(61));
                value.setD48(reset.getString(62));
                value.setD49(reset.getString(63));
                value.setD50(reset.getString(64));
                value.setNHAPTAY(reset.getString(65));
                value.setFONTFORMAT(reset.getString(66));
                value.setKIEUIN(reset.getInt(67));
                value.setKIEUDULIEU(reset.getString(68));

                lstDulieuChitieu.add(value);
            }
            mapDulieu.put("CHITIEU", lstDulieuChitieu);
            if (reset != null) {
                reset.close();
            }
            List<DULIEU_NT_CN> lstDulieuCot = new ArrayList<>();

            reset = (ResultSet) calstatement.getObject(3);
            while (reset.next()) {
                DULIEU_NT_CN value = DULIEU_NT_CN.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(3));
                //value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setKIEUDULIEU(reset.getString(6));
                value.setMA(reset.getString(8));
                lstDulieuCot.add(value);
            }
            mapDulieu.put("COT", lstDulieuCot);

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getDulieuMauCauhinh " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuMauCauhinh -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return mapDulieu;
    }

    public HashMap<String, String> getTenmauDonvitinh(String khoa) throws SQLException {

        HashMap<String, String> hmTieuDe_donvitinh = new HashMap();

        String _retVal = "";
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call INPUT_BRANCH.F_GET_TENMAU_DONVITINH(?, ?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, khoa);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);
            hmTieuDe_donvitinh.put("tenmau", _retVal);
            _retVal = calstatement.getString(3);
            hmTieuDe_donvitinh.put("donvitinh", _retVal);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getTenmau -> " + e.getMessage());
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
        return hmTieuDe_donvitinh;
    }

    public String getLoaimau(String khoa) throws SQLException {
        String _retVal = "";
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call INPUT_BRANCH.F_GET_LOAI_MAU(?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, khoa);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoaimau -> " + e.getMessage());
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

    public String getLoaimauSync(String khoa) throws SQLException {
        String _retVal = "";
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call INPUT_BRANCH.F_GET_SYNC_FLAG(?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, khoa);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoaimau -> " + e.getMessage());
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
    
    public List<ReportParam> getParameterChomau(String khoa, String username, String capbc) throws Exception {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();
        Connection conn = null;
        ResultSet rscur_params = null;
        ResultSet rs_combo = null;
        CallableStatement calstatement = null;
        try {

            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_LOAD_PARA(?, ?, ?, ?, ?)}";

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
            calstatement.setString(1, khoa);
            calstatement.setString(2, username);
            calstatement.setString(3, capbc);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            rscur_params = (ResultSet) calstatement.getObject(4);
            rs_combo = (ResultSet) calstatement.getObject(5);

            //Do toan bo du lieu tu Resultset vao array list
            List<Combo> combo_list_all = fillResultSetComboToArray(rs_combo);

            while (rscur_params.next()) {
                ReportParam rp = new ReportParam();

                rp.setType(rscur_params.getString("LOAITSO"));
                rp.setFieldName(rscur_params.getString("THAMSO"));
                rp.setLabel(rscur_params.getString("MOTA"));
                rp.setOrderNumber(Integer.parseInt(rscur_params.getString("STT")));
                //Neu la kieu list
                if (rp.getType().equalsIgnoreCase("L")) {
                    ArrayList<Combo> combo_list = new ArrayList<>(); //Loc cac cobo can thiet
                    for (Combo cb : combo_list_all) {
                        if (cb.getFieldName().equalsIgnoreCase(rp.getFieldName())) {
                            combo_list.add(cb);
                        }
                    }
                    rp.setComboList(combo_list);
                }
                report_param_list.add(rp);
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (rscur_params != null) {
                rscur_params.close();
            }
            if (rs_combo != null) {
                rs_combo.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return report_param_list;
    }

    private List<Combo> fillResultSetComboToArray(ResultSet rs_combo) throws SQLException, Exception {

        ArrayList<Combo> combo_list = new ArrayList<Combo>();
        try {
            while (rs_combo.next()) {
                Combo cb = new Combo();

                cb.setKey(rs_combo.getString("PARA_KEY"));
                cb.setValue(rs_combo.getString("PARA_DESC"));
                cb.setFieldName(rs_combo.getString("PARA_FIELD_NAME"));

                combo_list.add(cb);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " fillResultSetComboToArray  -> " + e.getMessage());
            throw new Exception(e);
        }
        return combo_list;
    }

    public HashMap<String, List<ListValue>> getDulieuColumnDanhmuc(String khoa, String username, String capbc) throws SQLException {
        HashMap<String, List<ListValue>> hmDataCot = new HashMap<String, List<ListValue>>();
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call INPUT_BRANCH.SP_GET_DATA_COLUMN_DANHMUC(?, ?, ?, ?, ?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, khoa);
            calstatement.setString(2, username);
            calstatement.setString(3, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            reset = (ResultSet) calstatement.getObject(4);
            String key = "";
            List<ListValue> lstValue = new ArrayList();
            while (reset.next()) {
                //ListValue value = new ListValue();
                String keytmp = reset.getString(2);
                if (!key.equals(keytmp)) {

                    if (!key.isEmpty()) {
                        hmDataCot.put(key, lstValue);
                        lstValue = new ArrayList();
                    } else {
                        lstValue = new ArrayList();
                    }
                    key = keytmp;
                }
                lstValue.add(new ListValue(reset.getString(4), reset.getString(3), reset.getString(1)));
            }
            if (lstValue.size() > 0) {
                hmDataCot.put(key, lstValue);
            }
            if (reset == null) {
                reset.close();
            }
            reset = (ResultSet) calstatement.getObject(5);
            //String key = "";
            List<String> lstString = new ArrayList();
            while (reset.next()) {
                lstString.add(reset.getString(4));
                for (ListValue data : lstValue) {

                }
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDulieuColumnDanhmuc -> " + e.getMessage());
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
        return hmDataCot;
    }

    public List<ModelParameterQuery> laydanhsachcactruongtruyvan(String query) throws SQLException {
        List<ModelParameterQuery> paraList = new ArrayList<ModelParameterQuery>();
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call INPUT_BRANCH.SP_GET_PARAMETER_QUERY(?,?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, query);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {

//                  private int idx;
//                    private int col_max_len;
//                    private String col_name;
//                    private String col_datatype;
//                    private int col_precision;
//                    private int col_scale;
//                    private String col_datatype_java;
                ModelParameterQuery model = new ModelParameterQuery();
                model.setIdx(reset.getInt("idx"));
                model.setCol_max_len(reset.getInt("col_max_len"));
                model.setCol_name(reset.getString("col_name"));

                model.setCol_datatype(reset.getString("col_datatype"));

                model.setCol_precision(reset.getInt("col_precision"));
                model.setCol_scale(reset.getInt("col_scale"));

                String col_datatype = model.getCol_datatype();
                if (col_datatype.equals("NUMBER")) {
                    model.setCol_datatype_java("N");
                } else if (col_datatype.equals("DATE")) {
                    model.setCol_datatype_java("D");
                } else {
                    model.setCol_datatype_java("T");
                }
                paraList.add(model);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoaimau -> " + e.getMessage());
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
        return paraList;
    }

    public int checkDulieuDanhmucCotDulieu(String khoa, String username, String capbc,
            String bangsl, String cothienthi, String cottso, String dkloc, String dkSapxep) throws SQLException {
        int rowDulieu = 0;
        Connection conn = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call INPUT_BRANCH.SP_CHECK_TRUYVAN_DANHMUC_COT(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try {
            conn = new DaoConnect().getConnect();
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, khoa);
            calstatement.setString(2, username);
            calstatement.setString(3, capbc);
            calstatement.setString(4, bangsl);
            calstatement.setString(5, cothienthi);
            calstatement.setString(6, cottso);
            calstatement.setString(7, dkloc);
            calstatement.setString(8, dkSapxep);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            reset = (ResultSet) calstatement.getObject(9);
//            if(reset.last())
//                rowDulieu = reset.getRow();
//            else rowDulieu=0;
            while (reset.next()) {
                rowDulieu++;
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkDulieuDanhmucCotDulieu -> " + e.getMessage());
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
        return rowDulieu;
    }

    public List<DULIEU_VIEW> convertDataToView(List<DULIEU_NT_CN> lstDulieu, List<MappingCot> lstMapping, HashMap<String, List<ListValue>> hmDataColumn) throws Exception {
        List<DULIEU_VIEW> lstDataView = new ArrayList<>();
        try {
            for (DULIEU_NT_CN value : lstDulieu) {
                DULIEU_VIEW dataView = MappingObjectClass(value, lstMapping, hmDataColumn);
                lstDataView.add(dataView);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return lstDataView;
    }

    private DULIEU_VIEW MappingObjectClass(DULIEU_NT_CN dulieunt, List<MappingCot> lstMapping, HashMap<String, List<ListValue>> hmDataColumn) {
        DULIEU_VIEW dataview = new DULIEU_VIEW();
        dataview.setKHOA(dulieunt.getKHOA());
        dataview.setTHUTU(dulieunt.getTHUTU());
        dataview.setTT_HIENTHI(dulieunt.getTT_HIENTHI());
        dataview.setMA(dulieunt.getMA());
        dataview.setTEN(dulieunt.getTEN());
        dataview.setNGAYBC(dulieunt.getNGAYBC());
        dataview.setNAMBC(dulieunt.getNAMBC());
        dataview.setMAPGD(dulieunt.getMAPGD());
        dataview.setCO_TONGHOP(dulieunt.getCO_TONGHOP());
        dataview.setMACN(dulieunt.getMACN());
        dataview.setNGUOI_NHAP(dulieunt.getNGUOI_NHAP());
        dataview.setNGAY_NHAP(dulieunt.getNGAY_NHAP());
        dataview.setNGUOI_DUYET(dulieunt.getNGUOI_DUYET());
        dataview.setNGAY_DUYET(dulieunt.getNGAY_DUYET());
        dataview.setTHAMSO_1(dulieunt.getTHAMSO_1());
        dataview.setTHAMSO_2(dulieunt.getTHAMSO_2());
        dataview.setTHAMSO_3(dulieunt.getTHAMSO_3());
        dataview.setTHAMSO_4(dulieunt.getTHAMSO_4());
        dataview.setTHAMSO_5(dulieunt.getTHAMSO_5());
        dataview.setTHAMSO_6(dulieunt.getTHAMSO_6());
        dataview.setTHAMSO_7(dulieunt.getTHAMSO_7());
        dataview.setTHAMSO_8(dulieunt.getTHAMSO_8());
        dataview.setTHAMSO_9(dulieunt.getTHAMSO_9());
        dataview.setTHAMSO_10(dulieunt.getTHAMSO_10());
        dataview.setD1(dulieunt.getD1());
        dataview.setD2(dulieunt.getD2());
        dataview.setD3(dulieunt.getD3());
        dataview.setD4(dulieunt.getD4());
        dataview.setD5(dulieunt.getD5());
        dataview.setD6(dulieunt.getD6());
        dataview.setD7(dulieunt.getD7());
        dataview.setD8(dulieunt.getD8());
        dataview.setD9(dulieunt.getD9());
        dataview.setD10(dulieunt.getD10());
        dataview.setD11(dulieunt.getD11());
        dataview.setD12(dulieunt.getD12());
        dataview.setD13(dulieunt.getD13());
        dataview.setD14(dulieunt.getD14());
        dataview.setD15(dulieunt.getD15());
        dataview.setD16(dulieunt.getD16());
        dataview.setD17(dulieunt.getD17());
        dataview.setD18(dulieunt.getD18());
        dataview.setD19(dulieunt.getD19());
        dataview.setD20(dulieunt.getD20());
        dataview.setD21(dulieunt.getD21());
        dataview.setD22(dulieunt.getD22());
        dataview.setD23(dulieunt.getD23());
        dataview.setD24(dulieunt.getD24());
        dataview.setD25(dulieunt.getD25());
        dataview.setD26(dulieunt.getD26());
        dataview.setD27(dulieunt.getD27());
        dataview.setD28(dulieunt.getD28());
        dataview.setD29(dulieunt.getD29());
        dataview.setD30(dulieunt.getD30());
        dataview.setD31(dulieunt.getD31());
        dataview.setD32(dulieunt.getD32());
        dataview.setD33(dulieunt.getD33());
        dataview.setD34(dulieunt.getD34());
        dataview.setD35(dulieunt.getD35());
        dataview.setD36(dulieunt.getD36());
        dataview.setD37(dulieunt.getD37());
        dataview.setD38(dulieunt.getD38());
        dataview.setD39(dulieunt.getD39());
        dataview.setD40(dulieunt.getD40());
        dataview.setD41(dulieunt.getD41());
        dataview.setD42(dulieunt.getD42());
        dataview.setD43(dulieunt.getD43());
        dataview.setD44(dulieunt.getD44());
        dataview.setD45(dulieunt.getD45());
        dataview.setD46(dulieunt.getD46());
        dataview.setD47(dulieunt.getD47());
        dataview.setD48(dulieunt.getD48());
        dataview.setD49(dulieunt.getD49());
        dataview.setD50(dulieunt.getD50());
        dataview.setNHAPTAY(dulieunt.getNHAPTAY());
        dataview.setFONTFORMAT(dulieunt.getFONTFORMAT());
        dataview.setKIEUIN(dulieunt.getKIEUIN());
        dataview.setKIEUDULIEU(dulieunt.getKIEUDULIEU());

        for (MappingCot cot : lstMapping) {
            if (cot.getTRUONGDL().equals("D1")) {
                dataview.setDATATYPE_D1(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD1(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D2")) {
                dataview.setDATATYPE_D2(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD2(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D3")) {
                dataview.setDATATYPE_D3(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD3(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D4")) {
                dataview.setDATATYPE_D4(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD4(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D5")) {
                dataview.setDATATYPE_D5(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD5(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D6")) {
                dataview.setDATATYPE_D6(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD6(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D7")) {
                dataview.setDATATYPE_D7(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD7(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D8")) {
                dataview.setDATATYPE_D8(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD8(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D9")) {
                dataview.setDATATYPE_D9(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD9(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D10")) {
                dataview.setDATATYPE_D10(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD10(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D11")) {
                dataview.setDATATYPE_D11(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD11(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D12")) {
                dataview.setDATATYPE_D12(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD12(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D13")) {
                dataview.setDATATYPE_D13(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD13(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D14")) {
                dataview.setDATATYPE_D14(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD14(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D15")) {
                dataview.setDATATYPE_D15(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD15(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D16")) {
                dataview.setDATATYPE_D16(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD16(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D17")) {
                dataview.setDATATYPE_D17(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD17(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D18")) {
                dataview.setDATATYPE_D18(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD18(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D19")) {
                dataview.setDATATYPE_D19(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD19(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D20")) {
                dataview.setDATATYPE_D20(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD20(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D21")) {
                dataview.setDATATYPE_D21(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD21(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D22")) {
                dataview.setDATATYPE_D22(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD22(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D23")) {
                dataview.setDATATYPE_D23(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD23(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D24")) {
                dataview.setDATATYPE_D24(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD24(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D25")) {
                dataview.setDATATYPE_D25(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD25(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D26")) {
                dataview.setDATATYPE_D26(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD26(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D27")) {
                dataview.setDATATYPE_D27(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD27(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D28")) {
                dataview.setDATATYPE_D28(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD28(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D29")) {
                dataview.setDATATYPE_D29(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD29(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D30")) {
                dataview.setDATATYPE_D30(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD30(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D31")) {
                dataview.setDATATYPE_D31(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD31(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D32")) {
                dataview.setDATATYPE_D32(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD32(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D33")) {
                dataview.setDATATYPE_D33(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD33(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D34")) {
                dataview.setDATATYPE_D34(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD34(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D35")) {
                dataview.setDATATYPE_D35(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD35(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D36")) {
                dataview.setDATATYPE_D36(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD36(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D37")) {
                dataview.setDATATYPE_D37(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD37(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D38")) {
                dataview.setDATATYPE_D38(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD38(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D39")) {
                dataview.setDATATYPE_D39(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD39(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D40")) {
                dataview.setDATATYPE_D40(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD40(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D41")) {
                dataview.setDATATYPE_D41(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD41(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D42")) {
                dataview.setDATATYPE_D42(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD42(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D43")) {
                dataview.setDATATYPE_D43(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD43(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D44")) {
                dataview.setDATATYPE_D44(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD44(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D45")) {
                dataview.setDATATYPE_D45(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD45(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D46")) {
                dataview.setDATATYPE_D46(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD46(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D47")) {
                dataview.setDATATYPE_D47(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD47(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D48")) {
                dataview.setDATATYPE_D48(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD48(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D49")) {
                dataview.setDATATYPE_D49(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD49(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }
            if (cot.getTRUONGDL().equals("D50")) {
                dataview.setDATATYPE_D50(cot.getKIEUDULIEU());
                if (cot.getKIEUDULIEU().equals("L")) {
                    dataview.setLstD50(hmDataColumn.get(cot.getTRUONGDL()));
                }
            }

        }
        return dataview;
    }

    private DULIEU_VIEW MappingObjectClass1(DULIEU_NT_CN dulieunt, List<MappingCot> lstMapping) {
        DULIEU_VIEW dataview = new DULIEU_VIEW();
        dataview.setKHOA(dulieunt.getKHOA());
        dataview.setTHUTU(dulieunt.getTHUTU());
        dataview.setTT_HIENTHI(dulieunt.getTT_HIENTHI());
        dataview.setMA(dulieunt.getMA());
        dataview.setTEN(dulieunt.getTEN());
        dataview.setNGAYBC(dulieunt.getNGAYBC());
        dataview.setNAMBC(dulieunt.getNAMBC());
        dataview.setMAPGD(dulieunt.getMAPGD());
        dataview.setCO_TONGHOP(dulieunt.getCO_TONGHOP());
        dataview.setMACN(dulieunt.getMACN());
        dataview.setNGUOI_NHAP(dulieunt.getNGUOI_NHAP());
        dataview.setNGAY_NHAP(dulieunt.getNGAY_NHAP());
        dataview.setNGUOI_DUYET(dulieunt.getNGUOI_DUYET());
        dataview.setNGAY_DUYET(dulieunt.getNGAY_DUYET());
        dataview.setTHAMSO_1(dulieunt.getTHAMSO_1());
        dataview.setTHAMSO_2(dulieunt.getTHAMSO_2());
        dataview.setTHAMSO_3(dulieunt.getTHAMSO_3());
        dataview.setTHAMSO_4(dulieunt.getTHAMSO_4());
        dataview.setTHAMSO_5(dulieunt.getTHAMSO_5());
        dataview.setTHAMSO_6(dulieunt.getTHAMSO_6());
        dataview.setTHAMSO_7(dulieunt.getTHAMSO_7());
        dataview.setTHAMSO_8(dulieunt.getTHAMSO_8());
        dataview.setTHAMSO_9(dulieunt.getTHAMSO_9());
        dataview.setTHAMSO_10(dulieunt.getTHAMSO_10());
        dataview.setD1(dulieunt.getD1());
        dataview.setD2(dulieunt.getD2());
        dataview.setD3(dulieunt.getD3());
        dataview.setD4(dulieunt.getD4());
        dataview.setD5(dulieunt.getD5());
        dataview.setD6(dulieunt.getD6());
        dataview.setD7(dulieunt.getD7());
        dataview.setD8(dulieunt.getD8());
        dataview.setD9(dulieunt.getD9());
        dataview.setD10(dulieunt.getD10());
        dataview.setD11(dulieunt.getD11());
        dataview.setD12(dulieunt.getD12());
        dataview.setD13(dulieunt.getD13());
        dataview.setD14(dulieunt.getD14());
        dataview.setD15(dulieunt.getD15());
        dataview.setD16(dulieunt.getD16());
        dataview.setD17(dulieunt.getD17());
        dataview.setD18(dulieunt.getD18());
        dataview.setD19(dulieunt.getD19());
        dataview.setD20(dulieunt.getD20());
        dataview.setD21(dulieunt.getD21());
        dataview.setD22(dulieunt.getD22());
        dataview.setD23(dulieunt.getD23());
        dataview.setD24(dulieunt.getD24());
        dataview.setD25(dulieunt.getD25());
        dataview.setD26(dulieunt.getD26());
        dataview.setD27(dulieunt.getD27());
        dataview.setD28(dulieunt.getD28());
        dataview.setD29(dulieunt.getD29());
        dataview.setD30(dulieunt.getD30());
        dataview.setD31(dulieunt.getD31());
        dataview.setD32(dulieunt.getD32());
        dataview.setD33(dulieunt.getD33());
        dataview.setD34(dulieunt.getD34());
        dataview.setD35(dulieunt.getD35());
        dataview.setD36(dulieunt.getD36());
        dataview.setD37(dulieunt.getD37());
        dataview.setD38(dulieunt.getD38());
        dataview.setD39(dulieunt.getD39());
        dataview.setD40(dulieunt.getD40());
        dataview.setD41(dulieunt.getD41());
        dataview.setD42(dulieunt.getD42());
        dataview.setD43(dulieunt.getD43());
        dataview.setD44(dulieunt.getD44());
        dataview.setD45(dulieunt.getD45());
        dataview.setD46(dulieunt.getD46());
        dataview.setD47(dulieunt.getD47());
        dataview.setD48(dulieunt.getD48());
        dataview.setD49(dulieunt.getD49());
        dataview.setD50(dulieunt.getD50());
        dataview.setNHAPTAY(dulieunt.getNHAPTAY());
        dataview.setFONTFORMAT(dulieunt.getFONTFORMAT());
        dataview.setKIEUIN(dulieunt.getKIEUIN());
        dataview.setKIEUDULIEU(dulieunt.getKIEUDULIEU());

        for (MappingCot cot : lstMapping) {
            if (cot.getTRUONGDL().equals("D1")) {
                dataview.setDATATYPE_D1(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D2")) {
                dataview.setDATATYPE_D2(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D3")) {
                dataview.setDATATYPE_D3(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D4")) {
                dataview.setDATATYPE_D4(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D5")) {
                dataview.setDATATYPE_D5(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D6")) {
                dataview.setDATATYPE_D6(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D7")) {
                dataview.setDATATYPE_D7(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D8")) {
                dataview.setDATATYPE_D8(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D9")) {
                dataview.setDATATYPE_D9(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D10")) {
                dataview.setDATATYPE_D10(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D11")) {
                dataview.setDATATYPE_D11(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D12")) {
                dataview.setDATATYPE_D12(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D13")) {
                dataview.setDATATYPE_D13(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D14")) {
                dataview.setDATATYPE_D14(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D15")) {
                dataview.setDATATYPE_D15(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D16")) {
                dataview.setDATATYPE_D16(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D17")) {
                dataview.setDATATYPE_D17(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D18")) {
                dataview.setDATATYPE_D18(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D19")) {
                dataview.setDATATYPE_D19(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D20")) {
                dataview.setDATATYPE_D20(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D21")) {
                dataview.setDATATYPE_D21(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D22")) {
                dataview.setDATATYPE_D22(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D23")) {
                dataview.setDATATYPE_D23(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D24")) {
                dataview.setDATATYPE_D24(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D25")) {
                dataview.setDATATYPE_D25(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D26")) {
                dataview.setDATATYPE_D26(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D27")) {
                dataview.setDATATYPE_D27(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D28")) {
                dataview.setDATATYPE_D28(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D29")) {
                dataview.setDATATYPE_D29(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D30")) {
                dataview.setDATATYPE_D30(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D31")) {
                dataview.setDATATYPE_D31(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D32")) {
                dataview.setDATATYPE_D32(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D33")) {
                dataview.setDATATYPE_D33(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D34")) {
                dataview.setDATATYPE_D34(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D35")) {
                dataview.setDATATYPE_D35(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D36")) {
                dataview.setDATATYPE_D36(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D37")) {
                dataview.setDATATYPE_D37(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D38")) {
                dataview.setDATATYPE_D38(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D39")) {
                dataview.setDATATYPE_D39(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D40")) {
                dataview.setDATATYPE_D40(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D41")) {
                dataview.setDATATYPE_D41(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D42")) {
                dataview.setDATATYPE_D42(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D43")) {
                dataview.setDATATYPE_D43(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D44")) {
                dataview.setDATATYPE_D44(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D45")) {
                dataview.setDATATYPE_D45(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D46")) {
                dataview.setDATATYPE_D46(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D47")) {
                dataview.setDATATYPE_D47(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D48")) {
                dataview.setDATATYPE_D48(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D49")) {
                dataview.setDATATYPE_D49(cot.getKIEUDULIEU());
            }
            if (cot.getTRUONGDL().equals("D50")) {
                dataview.setDATATYPE_D50(cot.getKIEUDULIEU());
            }

        }
        return dataview;
    }

    public boolean saveDulieuNhapTay(String khoa, List<DULIEU_NT_CN> lstDulieu, String username, String capbc) throws Exception {
        boolean bSuccess = false;
        Connection conn = null;
        try {
            DaoConnect daoconnect = new DaoConnect();

            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.SP_SAVE_DULIEU_NHAP(?, ?, ?, ?)}";

            Object array[] = lstDulieu.toArray();
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor(DULIEU_NT_CN.ORACLE_TABLE_TYPE, conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, khoa);
            calstatement.setArray(2, array_to_pass);
            calstatement.setString(3, username);
            calstatement.setString(4, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            if (calstatement != null) {
                calstatement.close();
            }

            bSuccess = true;
        } catch (SQLException e) {
            System.err.println("Loi trong ham saveDulieuNhapTay " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDulieuNhapTay -> " + e.getMessage());
            throw new Exception(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public static String decode(String url) {
        try {
            String prevURL = "";
            String decodeURL = url;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" + e.getMessage();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException {
        DaoInputFormBranchMain dao = new DaoInputFormBranchMain();
        try {
            String bangsl = "dmxa";
            String cothienthi = "ma||' -> '||ten";
            String cottso = "ma";
            String dkloc = "pgd_ql=pv_mapgd";
            String dksapxep = "ma";
            int rownum = dao.checkDulieuDanhmucCotDulieu("ABC", "TUNGNV", "2", bangsl, cothienthi, cottso, dkloc, dksapxep);

            HashMap<String, List<ListValue>> hmData = dao.getDulieuColumnDanhmuc("MAUMOITAO000999", "TUNGNV", "2");

            String urlString = "select%20'G'%20ma,%20'N%E1%BB%AF'%20giatri%20from%20dual%20union%20all%20select%20'T'%20ma,%20'Nam'%20giatri%20from%20dual#giatri#ma##ma";

            String url = decode(urlString);

            //new String(urlString.getBytes("UTF-8"),"ASCII");
            //decodeValue("select%20%27G%27%20ma%2C%20%27N%u1EEF%27%20giatri%20from%20dual%20union%20all%20select%20%27T%27%20ma%2C%20%27Nam%27%20giatri%20from%20dual%23giatri%23ma%23%23ma");
            System.out.println("vbsp.ims.branch.DaoInputFormBranchMain.main()");
            String queryString = "dmpos#po_ten#po_ma##po_ma";
            String[] arrayQuery = queryString.split("#");
            MappingCot cottruyvan = new MappingCot();
            for (int i = 0; i < arrayQuery.length; i++) {

                switch (i) {
                    case 0:
                        cottruyvan.setBANGSL(arrayQuery[0]);
                        break;
                    case 1:
                        cottruyvan.setCOTHIENTHI(arrayQuery[1]);
                        break;
                    case 2:
                        cottruyvan.setCOTTSO(arrayQuery[2]);
                        break;
                    case 3:
                        cottruyvan.setDKLOC(arrayQuery[3]);
                        break;
                    case 4:
                        cottruyvan.setDKSAPXEP(arrayQuery[4]);
                        break;
                }
            }

//            String capbc1 = "#1#";
//            String[] arrCapbc = capbc1.split("#");
//            List<String> lstCapbc = new ArrayList<>();
//            for (String capbc : arrCapbc) {
//                if (!capbc.isEmpty()) {
//                    lstCapbc.add(capbc);
//                }
//            }
//            System.err.println(lstCapbc.toArray(new String[lstCapbc.size()]));
            dao.laydanhsachcactruongtruyvan("select * from hscv");

            List<DULIEU_NT_CN> lstParameter = new ArrayList<>();
            DULIEU_NT_CN value1 = DULIEU_NT_CN.newInstance();
            value1.setMA("thamso_1");
            value1.setTEN("26-MAY-2020");
            value1.setKIEUDULIEU("D");
//            lstParameter.add(value1);
//            DULIEU_NT_CN value2 = DULIEU_NT_CN.newInstance();
//            value2.setMA("thamso_2");
//            value2.setTEN("002721");
//            value2.setKIEUDULIEU("T");

            //lstParameter.add(value2);
            String khoa = "BC_test";

            try {
                List<DULIEU_NT_CN> lstDulieu = dao.getDulieuBaocaoNhap(khoa, lstParameter, "T0001","3");
                List<MappingCot> lstMapping = dao.getDulieuMappingCot(khoa);

                for (DULIEU_NT_CN cot : lstDulieu) {
                    System.err.println(cot.getTEN());
                }

                for (MappingCot cot : lstMapping) {
                    System.err.println(cot.getTENHIENTHI());
                }
//                List<DULIEU_VIEW> lstDataView = dao.convertDataToView(lstDulieu, lstMapping);
//
//                for (DULIEU_VIEW dataview : lstDataView) {
//                    System.err.println("Du lieu =" + dataview.getTEN() + " d1=" + dataview.getDATATYPE_D1());
//                }

            } catch (Exception ex) {
                String message = ex.getMessage();
                message = message.replaceAll("\\r\\n|\\r|\\n|\"", " ");
                System.err.println(message);
                Logger.getLogger(DaoInputFormBranchMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInputFormBranchMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ListValue> getGroupReport(String UserName) {
        List<ListValue> lstQuery = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call INPUT_BRANCH.sp_get_group(?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, UserName == null ? "" : UserName);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    lstQuery.add(new ListValue(reset.getString("GROUP_ID"), reset.getString("GROUP_DESC"), reset.getString("GROUP_ORDER")));
//                    lstQuery.put("SRQ_QUERY",reset.getString("SRQ_QUERY"));   
//                     lstQuery.put("STRF_TITLE_NAME",reset.getString("STRF_TITLE_NAME"));  
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                CoreLogger.error(this.getClass().getName() + " getGroupQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getGroupQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstQuery;
    }
}
