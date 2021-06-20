/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.phts;

import com.lowagie.text.pdf.PdfName;
import vbsp.ims.sbv.*;
import java.io.File;
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
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.CommuneDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Commune;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class daoPhts {

    public static daoPhts newInstance() {
        return new daoPhts();
    }

    /**
     *
     * @param mabc Mã báo cáo NHNN quy định theo phụ lục 02
     * @param mapgd Mã chi nhánh gửi báo cao
     * @param kybc Mã kỳ báo cáo được quy định tại phu lục 02 duoc dinh nghia
     * trong bang dmkhac voi khoa_1 ='59' 0->9
     * @param ngaybc ngày gửi báo cáo và ngày số liệu của báo cáo
     * @param guimoi_quahan “Gửi mới” (To be submitted): S,“Quá hạn”
     * (Backdated): B.
     * @param loai_bc loại file gửi: -	“Báo cáo chính” (Main report) : M; -	“Báo
     * cáo không phát sinh dữ liệu” (N/A Report) : N; -	“File thuyết minh”
     * (Remark File) : R; -	“File đính kèm file thuyết minh” (Attach File) : A +
     * số thứ tự gồm 2 ký tự;
     * @param langui_bc số lần gửi báo cáo
     * @return trả ra tên file bao cáo
     * @throws SQLException
     */
    public List<QT_DULIEU_NT> getMainDataPhts(Connection conn, String sNgaybc, String sUser,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {            
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_MAIN_DATA(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
//                    value.setD1(reset.getString(14));
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
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKTGS_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getMathongkePhts(Connection conn, String sNgaybc, String sMacn,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {            
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_MATHONGKE(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sMacn);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
//                    value.setD1(reset.getString(14));
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
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKTGS_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    
    public List<QT_DULIEU_NT> getMaubieutt35Phts(Connection conn, String sNgaybc, String sMacn,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {            
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_MAUBIEUTT35(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sMacn);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
//                    value.setD1(reset.getString(14));
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
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKTGS_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    
    public List<QT_DULIEU_NT> getNhapthucongPhts(Connection conn,String sKhoa, String sUser, String sGrade,
            String sNgaybc, String sMacn) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {            
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_NHAPTHUCONG(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(5, sMacn);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
                    value.setNGAY_NHAP(reset.getDate(12));
//                    value.setD1(reset.getString(14));
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
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKTGS_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMainDataPhts -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public String getCheckPsinh(Connection conn, String mabc, String mapgd, String kybc,
            String ngaybc) throws SQLException {
        String fileName = "";
//        Connection conn = null;
//        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_PHTS.F_CHECK_PSINH(?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, mabc);

            calstatement.setString(3, mapgd);
            calstatement.setString(4, kybc);
            calstatement.setString(5, ngaybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            fileName = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getFileNameExport -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        }
        return fileName;
    }
    /**
     * Ham nay tra ra ten file gui NHNN va ma ten file excel
     *
     * @param mabc Mã báo cáo NHNN quy định theo phụ lục 02
     * @param mapgd Mã chi nhánh gửi báo cao
     * @param kybc Mã kỳ báo cáo được quy định tại phu lục 02 duoc dinh nghia
     * trong bang dmkhac voi khoa_1 ='59' 0->9
     * @param ngaybc ngày gửi báo cáo và ngày số liệu của báo cáo
     * @param guimoi_quahan “Gửi mới” (To be submitted): S,“Quá hạn”
     * (Backdated): B.
     * @param loai_bc loại file gửi: -	“Báo cáo chính” (Main report) : M; -	“Báo
     * cáo không phát sinh dữ liệu” (N/A Report) : N; -	“File thuyết minh”
     * (Remark File) : R; -	“File đính kèm file thuyết minh” (Attach File) : A +
     * số thứ tự gồm 2 ký tự;
     * @param langui_bc số lần gửi báo cáo
     * @return trả ra tên file bao cáo
     * @throws SQLException
     */
    public HashMap<String, String> getFileNameExportWithExcelTemplate(String mabc, String mapgd, String kybc,
            String ngaybc, String guimoi_quahan, String loai_bc, String langui_bc) throws SQLException {
        HashMap<String, String> hmFileName = new HashMap<String, String>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_PHTS.F_GET_FILENAME_RPT_EXP(?,?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, mabc);

            calstatement.setString(3, mapgd);
            calstatement.setString(4, kybc);
            calstatement.setString(5, ngaybc);
            calstatement.setString(6, guimoi_quahan);
            calstatement.setString(7, loai_bc);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            hmFileName.put("FILE_NHNN", calstatement.getString(1));
            hmFileName.put("FILE_EXCEL", calstatement.getString(7));

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getFileNameExportWithExcelTemplate -> " + e.getMessage());
            throw new SQLException();
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
        return hmFileName;
    }

    /**
     * Ham nay lay ra tham so khoi tao cho xuat bao cao sbv. cac tham so lay ra
     * tuong ung voi cac khoa loai_bc, ky_bc, loai_file, macn
     *
     * @param userName
     * @param capbc
     * @return
     * @throws SQLException
     */
    public HashMap<String, List<ListValue>> getDmParaExp(String userName, String capbc) throws SQLException {
        HashMap<String, List<ListValue>> hm = new HashMap<String, List<ListValue>>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_DANHMUC_PARA(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(1);

            List<ListValue> lstTemp = new ArrayList<ListValue>();
            String khoa_1 = "";
            String previous_khoa_1 = "";
            boolean fistLoop = true;

            while (reset.next()) {
                khoa_1 = reset.getString(1);
                String key = reset.getString(2);
                String des = reset.getString(3);
//                    String stt = reset.getString("stt");

                if (fistLoop == true) {
                    //Lan dau tien
                    ListValue valueTmp = new ListValue(key, des);
                    lstTemp.add(valueTmp);

                    fistLoop = false;
                } else if (khoa_1.equals(previous_khoa_1)) {
                    //Neu khoa 1 chua thay doi
                    ListValue valueTmp = new ListValue(key, des);
                    lstTemp.add(valueTmp);
                } else {
                    //Neu khoa 1 thay doi
                    hm.put(previous_khoa_1, lstTemp);

                    lstTemp = new ArrayList<ListValue>(); //Loai bo het gia tri trong list
                    ListValue valueTmp = new ListValue(key, des);
                    lstTemp.add(valueTmp);
                }

                previous_khoa_1 = khoa_1; //Luu lai khoa 1
            }
            hm.put(previous_khoa_1, lstTemp); //Khi ra khoi vong lap can them gia tri cuoi cung

            //------------------------------------ lay du lieu cho mapgd
            String procedure_mapgd = "{call VBSP_IMS_PHTS.SP_GET_MAPGD_EXP(?,?,?)}";
            calstatement = conn.prepareCall(procedure_mapgd, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, userName);
            calstatement.setString(2, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            List<ListValue> lstTempmapgd = new ArrayList<ListValue>();
            while (reset.next()) {
                ListValue valueTmp = new ListValue(reset.getString(1), reset.getString(2));
                lstTempmapgd.add(valueTmp);
            }
            hm.put("macn", lstTempmapgd);
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmParaExp -> " + e.getMessage());
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
        return hm;
    }

    /**
     *
     * @param capbc cap bao cao
     * @param loaibc loai bao cao
     * @return
     * @throws SQLException
     */
    public List<ListValue> getAllReportSbv(String capbc, String loaibc) throws SQLException {
        List<ListValue> lstAllReport = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_ALL_SBV(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, capbc);
            calstatement.setString(2, loaibc == null ? "" : loaibc);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                lstAllReport.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNhombc -> " + e.getMessage());
            throw new SQLException();
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
        return lstAllReport;
    }

    public ResultSet getHeaderReport(Connection conn, String file_name, String ngaybc, String macn, String ky_bc, String sUser) throws SQLException {
        CallableStatement calstatement = null;
        String storeProcedure = "{call VBSP_IMS_PHTS.SP_GET_HEADER_RPT(?,?,?,?,?,?)}";
        ResultSet reset = null;
        try {
            calstatement = conn.prepareCall(storeProcedure, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, sUser);
            calstatement.setString(2, file_name);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, macn);
            calstatement.setString(5, ky_bc);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getHeaderReport -> " + e.getMessage());
            throw new SQLException();
        } finally {
//            if (calstatement != null) {
//                calstatement.close();
//            }
        }

        return reset;
    }

    /**
     * Hàm này lấy ra tất cả các bao cáo đã cấu hình (lấy re tên file excel)
     *
     * @param capbc cấp báo cáo
     * @param lstMabc danh sách các mã báo cáo
     * @return
     * @throws SQLException
     */
    public List<ListRptExpModel> getReportExportExcelTemplate(String capbc, List<String> lstMabc) throws SQLException {
        List<ListRptExpModel> lstRptExp = new ArrayList<>();
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        String storeProcedure = "{call VBSP_IMS_PHTS.SP_GET_ALL_RPT_EXP(?,?,?)}";
        ResultSet reset = null;
        try {
            calstatement = conn.prepareCall(storeProcedure, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, capbc);
            String[] arr_mabc = lstMabc.toArray(new String[0]);
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            ARRAY ora_mabc = new ARRAY(des, conn, arr_mabc);
            calstatement.setArray(2, ora_mabc);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(3);

            while (reset.next()) {
                ListRptExpModel exp = new ListRptExpModel();
                exp.setFile_excel(reset.getString(1));
                exp.setTen_bc(reset.getString(2));
                exp.setMa_bc(reset.getString(3));
                exp.setTen_ky(reset.getString(4));
                exp.setMa_ky(reset.getString(5));
                exp.setCapbc(reset.getString(6));
                lstRptExp.add(exp);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportExportExcelTemplate -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (reset != null) {
                reset.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstRptExp;
    }
    
    public List<ListRptExpModel> getReportExportExcelTemplate(String capbc, List<String> lstMabc, String path) throws SQLException, Exception {
        List<ListRptExpModel> lstRptExp = new ArrayList<>();
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        String storeProcedure = "{call VBSP_IMS_PHTS.SP_GET_ALL_RPT_EXP(?,?,?)}";
        ResultSet reset = null;
        try {
            calstatement = conn.prepareCall(storeProcedure, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, capbc);
            String[] arr_mabc = lstMabc.toArray(new String[0]);
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            ARRAY ora_mabc = new ARRAY(des, conn, arr_mabc);
            calstatement.setArray(2, ora_mabc);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(3);

            while (reset.next()) {
                ListRptExpModel exp = new ListRptExpModel();
                exp.setFile_excel(reset.getString(1));
                exp.setTen_bc(reset.getString(2));
                exp.setMa_bc(reset.getString(3));
                exp.setTen_ky(reset.getString(4));
                exp.setMa_ky(reset.getString(5));
                exp.setCapbc(reset.getString(6));
                exp.setSheetName(path+reset.getString(1));
                lstRptExp.add(exp);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportExportExcelTemplate -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (reset != null) {
                reset.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstRptExp;
    }
     public List<ListValue> getLoadAllQuery( String kybc,  String capbc, String psinh) throws SQLException {
        List<ListValue> lstAllRpt = new ArrayList<ListValue>();
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_BAOCAO_KYBC(?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, kybc);
            calstatement.setString(2, capbc);
            calstatement.setString(3, psinh);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            reset = (ResultSet) calstatement.getObject(4);
            //COLUMN_DESC
            while (reset.next()) {
                lstAllRpt.add(new ListValue(reset.getString(2), reset.getString(3), reset.getString(1)));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            throw new SQLException();
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
        return lstAllRpt;
    }

    public List<ModelTreeNode> getDataPosTreeNode(String strUserName, String sGrade) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_BC_TREE(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
//                calstatement.setString(2, sGrade);
//                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
//                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(3);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }
    
    public List<QT_DULIEU_NT> transferDataPhts(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sContent) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_PHTS.SP_TRANSFERDATA(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(6, sContent);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
                    value.setNGAY_NHAP(reset.getDate(12));
//                    value.setD1(reset.getString(14));
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
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " transferDataPhts -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham transferDataPhts " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " transferDataPhts -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    

    public static boolean isCheckfile(String filename) throws Exception {
        //Lay ra duong dan root cua thu muc web
        String filePath = "e:\\excel";
        //convert ve duong dan uri
        filePath = DefineFun.backlashReplace(filePath);
        if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
            filePath += "/";
        }

        filePath = filePath + filename;
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            String file_excel="I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\build\\web\\EXPORT_REPORT\\XLS\\G00014-01207004-01207004-201607-ST-M-01.xlsx";
            
            new daoPhts().getLoadAllQuery("4", "3","1");
//            String file_excel="A00024.xml";
            String extendFile=file_excel.substring(file_excel.lastIndexOf("\\")+1, file_excel.length());
            System.err.println("extend="+extendFile);
//            if(!extendFile.equals("xlsx")&&!extendFile.equals("xls"))
//                System.err.println("Khong dung phan mo rong file");
            if (isCheckfile(file_excel)||isCheckfile("A00024.XM")) {
                System.err.println("da tim thay file ");
            } else {
                System.err.println("Khong tim thay file");
            }
            /*
            String fileName = daoExpSbv.newInstance().getFileNameExport("A00034", "000100", "4", "31-jul-2016", "S", "M", "");
            System.err.println(fileName);
            HashMap<String, List<ListValue>> hmdata = daoExpSbv.newInstance().getDmParaExp("M2505", "1");
            for (String key : hmdata.keySet()) {
                System.out.println("PARA=" + key);
                List<ListValue> value = (List<ListValue>) (hmdata.get(key) == null ? new ArrayList<>() : hmdata.get(key));
                for (ListValue v : value) {
                    System.out.println("---- " + v.getsKey() + " -> " + v.getsDesc());
                }
            }
            List<String> lstMabc = new ArrayList<>();
            lstMabc.add("D00094");
            lstMabc.add("G00774");
            lstMabc.add("H00061");
            List<ListRptExpModel> lstdata = daoExpSbv.newInstance().getReportExportExcelTemplate("3", lstMabc);
            for (ListRptExpModel exp : lstdata) {
                System.err.println(exp.getTen_bc());
            }
             */
        } catch (Exception ex) {
            //Logger.getLogger(daoExpSbv.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    
    public List<String> getDataSendPhts(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_PHTS.SP_GET_DATA_PHTS_SYNC(?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, type);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, ngay_bc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(7);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSendBcqt -> " + e.getMessage());
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
        return lstData;
    }
    
    
    public boolean updateStatusSendPhts(String sKhoa, String sMapgd, String sNgaybc, String sStatus) {                
        Connection conn = null;        
        CallableStatement calstatement = null;
        String strStoreproce
                = "{call VBSP_IMS_PHTS.UpdateStatusSendPhts(?, ?, ?, ?, ?, ?, ?)}";
        int updated_row = 0;
        try {

            DaoConnect daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                      
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, sMapgd);
            calstatement.setString(3, sNgaybc);
            calstatement.setString(4, sStatus);            
            
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.executeUpdate();
            updated_row = (int) calstatement.getObject(7);
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.updated_row-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(daoPhts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updated_row > 0;
    }

    public String getContent(String Khoa,String sGrade,  String sMacn, String sNgaybc, String sInput) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        String sCountTotalCust = "";
        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_PHTS.F_GET_CONTENT(?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
             calstatement.setString(2, Khoa);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sMacn);                                  
            calstatement.setString(5, sNgaybc); 
            calstatement.setString(6, sInput); 
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            sCountTotalCust = calstatement.getString(1);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataTotal " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataTotal -> " + e.getMessage());
        }                
        return sCountTotalCust;
    }
}
