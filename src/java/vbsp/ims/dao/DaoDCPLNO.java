/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcplnModel;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.DcptNoModel.ViewTotalCust;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.PLNO_DULIEU;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Sr. Chữ (Write Date: 26.01.2016)
 */
public class DaoDCPLNO {
    
    //<editor-fold defaultstate="collapsed" desc="Hàm thực hiện trả về Danh sách các đơn vị (CN/PGD/Xã(phường)/Tổ TK&VV">
    /**
     * Hàm thực hiện Load danh sách các danh mục phục vụ cho chức năng: ĐVUT,
     * Chương trình, Nguồn vốn, Chi nhánh
     *
     * @return Danh sách danh mục cần thiết cho Load dữ liệu chức năng Phân loại
     * nợ
     */
    public HashMap<Integer, List<ListValue>> getDmKhac() {
        HashMap<Integer, List<ListValue>> hash_Map = new HashMap<Integer, List<ListValue>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DM(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(1);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);

                List<ListValue> lstTemp = new ArrayList<ListValue>();
                int khoa_1 = 0;
                int previous_khoa_1 = 0;
                boolean fistLoop = true;
                while (reset.next()) {
                    khoa_1 = Integer.parseInt(reset.getString("khoa_1"));
                    String key = reset.getString("khoa_2");
                    String des = reset.getString("giatri");
                    if (fistLoop == true) {
                        //Lan dau tien
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                        fistLoop = false;
                    } else if (khoa_1 == previous_khoa_1) {
                        //Neu khoa 1 chua thay doi
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    } else {
                        //Neu khoa 1 thay doi
                        hash_Map.put(previous_khoa_1, lstTemp);
                        lstTemp = new ArrayList<ListValue>(); //Loai bo het gia tri trong list
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    }
                    previous_khoa_1 = khoa_1; //Luu lai khoa 1
                }
                hash_Map.put(previous_khoa_1, lstTemp); //Khi ra khoi vong lap can them gia tri cuoi cung
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
                CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDmKhac " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
        }
        return hash_Map;
    }

    /**
     * Hàm thực hiện Load danh sách danh mục Nguyên nhân không có khả năng trả
     * nợ (Phân loại nợ)
     *
     * @return
     */
    public List<ListValue> getNgNhan_KCKNTN(Connection conn) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_NGNHAN_KCKNTN(?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(1);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
                    lstDMNgNhan.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }
    
    public List<ListValue> getNgNhan_KCKNTN_C2(Connection conn) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_NGNHAN_KCKNTN_C2(?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(1);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
                    lstDMNgNhan.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }

    /**
     * Hàm thực hiện Load danh sach các đơn vị trực thuộc. Voi quy uoc: - TW:
     * Load các chi nhánh: PARENT_CD (000100), PARENT_DESC (NHCSXH Việt Nam),
     * CHILD_CD (Ma Chi nhanh. Ex: 005420, CHILD_DESC (Ten chi nhanh. Ex: Long
     * An) - Chi nhanh: Load DS cac PGD: PARENT_CD (002505), PARENT_DESC (Hội sở
     * tỉnh-Hà Giang), CHILD_CD (Ma PGD. Ex: 002501, CHILD_DESC (Ten PGD. Ex:
     * PGD huyện Mèo Vạc) - Phong GD: Load DS cac xa (phuong): 002503,PGD huyện
     * Yên Minh,250301,Yên Minh
     *
     * @param strUserName: Ten nguoi dung truyen vao trong ham
     * @param sGrade: Cap chay chuong trinh: '1' - PGD; '2' - Chi nhanh; '3' -
     * Toan quoc
     * @return: Danh sach don vi truc thuoc tra ve
     */
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName, String sGrade) {
        List<ModelTreeNode> lstPos = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_TREE_NODE(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(3);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC: PARENT_CD, PARENT_DESC, CHILD_CD, CHILD_DESC
                while (reset.next()) {
                    lstPos.add(new ModelTreeNode(reset.getString("PARENT_CD"),
                            reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"),
                            reset.getString("CHILD_DESC")));
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
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPos;
    }

    /**
     * Hàm thực hiện Load danh sách các tổ TK&VV thuộc Xã, Đơn vị Ủy thác truyền
     * vào Với quy ước: PARENT_CD (Mã Phòng GD), PARENT_DESC (Tên Phòng giao
     * dịch), CHILD_CD (Mã tổ) và CHILD_DESC (Tên Tổ TK&VV)
     *
     * @param strUserName: Tên người dùng. Ex: P2503
     * @param sGrade: Cap chay chuong trinh: '1' - PGD; '2' - Chi nhanh; '3' -
     * Toan quoc
     * @param sMaXa: Mã xã/phường cần load danh sách tổ TK&VV. Ex: 250301
     * @param sDVUT: Mã đơn vị ủy thác. Ex: '11'
     * @return: Danh sách tổ TK&VV trả về
     */
    public List<ModelTreeNode> getDataPosTreeNodeGroup(String strUserName, String sGrade, String sMaXa, String sDVUT) {
        List<ModelTreeNode> lstGroup = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_TREE_NODE_GROUP(?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sMaXa);
                calstatement.setString(4, sDVUT);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(5);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                //COLUMN_DESC: PARENT_CD, PARENT_DESC, CHILD_CD, CHILD_DESC
                while (reset.next()) {
                    lstGroup.add(new ModelTreeNode(reset.getString("PARENT_CD"),
                            reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"),
                            reset.getString("CHILD_DESC")));
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNodeGroup -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDataPosTreeNodeGroup " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNodeGroup -> " + e.getMessage());
        }
        return lstGroup;
    }

    /**
     * Hàm thực hiện Load danh sách tổ trưởng tổ TK&VV thuộc Xã, Đơn vị UT
     * truyền vào
     *
     * @param sUserName: Tên người dùng. Ex: P2503
     * @param sGrade: Cap chay chuong trinh: '1' - PGD; '2' - Chi nhanh; '3' -
     * Toan quoc
     * @param lstPosCD: Danh sách các xã/phường truyền vào
     * @param sDvut: Mã hiệu đơn vị ủy thác
     * @return DS tổ TK&VV cần lấy ra. Ds gồm 2 trường: MATO (Ex:0197092), TENTT
     * (Ex: 0197092 - Phạm Tuấn Sinh)
     */
    public List<ListValue> getToTruong(String sUserName, String sGrade, ArrayList<String> lstPosCD, String sDvut, String sNgaybc) {
        List<ListValue> lstToTruong = new ArrayList<ListValue>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DATA_GROUP(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sDvut);
            calstatement.setString(5, sNgaybc);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
            iErr_CD = calstatement.getInt(6);
            //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
            sEdd_TXT = calstatement.getString(7);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(8);
            while (reset.next()) {
                lstToTruong.add(new ListValue(reset.getString(1), reset.getString(2)));
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
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getToTruong " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getToTruong -> " + e.getMessage());
        }
        return lstToTruong;
    }

    /**
     * Hàm thực hiện Load danh sách các Phòng giao dịch trực thuộc
     * (MA_PGD,TEN_PGD) Ex: 002501, 002501 - PGD huyện Mèo Vạc
     *
     * @param sMaCN: Mã hiệu chi nhánh truyền vào theo quy ước 2 ký tự. Ex: '25'
     * @return Danh sách Phòng giao dịch trực thuộc Chi nhánh trả về
     */
    public List<ListValue> getPGD(String sMaCN) {
        List<ListValue> lstMaPGD = new ArrayList<ListValue>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DATA_PGD(?,?,?,?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sMaCN);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
            iErr_CD = calstatement.getInt(2);
            //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
            sEdd_TXT = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);

            while (reset.next()) {
                lstMaPGD.add(new ListValue(reset.getString(1), reset.getString(2)));
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
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getPGD " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getPGD -> " + e.getMessage());
        }
        return lstMaPGD;
    }

    /**
     * Hàm thực hiện Load danh sách xã/Phường thuộc mã phòng giao dịch truyền
     * vào.
     *
     * @param sMaPGD: Mã phòng giao dịch truyền vào. Ex: 002501
     * @return: Danh sách Xã/Phường trực thuộc Phòng giao dịch: MAXA, TENXA (Ex:
     * 250102, 250102 - Sủng Trà)
     */
    public List<ListValue> getXa(String sMaPGD) {
        List<ListValue> lstXaPhuong = new ArrayList<ListValue>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DATA_XA(?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sMaPGD);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
            iErr_CD = calstatement.getInt(2);
            //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
            sEdd_TXT = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);
            while (reset.next()) {
                lstXaPhuong.add(new ListValue(reset.getString(1), reset.getString(2)));
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
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getXa " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getXa -> " + e.getMessage());
        }
        return lstXaPhuong;
    }

    /**
     * Hàm thực hiện Load danh sách Xã/Phường thuộc Phòng giao dịch theo
     * UserName và cấp BC truyền vào
     *
     * @param sUserName: Mã hiệu người dùng
     * @param sGrade: Cấp báo cáo
     * @return Danh sách xã/phường thuộc Phòng giao dịch (MAXA, TENXA)
     */
    public List<ListValue> getMaXa(String sUserName, String sGrade) {
        List<ListValue> lstMaXa = new ArrayList<ListValue>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DATA_COMMUNE(?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo Pos hay Main Pos  
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
            iErr_CD = calstatement.getInt(3);
            //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
            sEdd_TXT = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {
                lstMaXa.add(new ListValue(reset.getString(1), reset.getString(2)));
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
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getMaXa " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getMaXa -> " + e.getMessage());
        }
        return lstMaXa;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hàm thực hiện lấy Danh sách bản ghi cho màn hình nhập liệu">
    /**
     * Hàm thực hiện trả về Danh sách món vay tải lên thực hiện Đối chiếu, Phân
     * loại nợ
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sNgaySL: Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPosCD: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @param sTrangThai: Giá trị mã Trạng thái bản ghi theo quy ước.
     * @param nStartRow: Chỉ số đầu bản ghi cần lấy (Giới hạn số thứ tự bản ghi
     * đầu)
     * @param nEndRow: Chỉ số cuối bản ghi cần lấy (Giới hạn số thứ tự bản ghi
     * cuối)
     * @return: Danh sách thông tin món vay thực hiện Đối chiếu, Phân loại nợ
     */
    public List<DcplnModel> getDataPLN(Connection conn, String sUserName, String sGrade, String sNgaySL,
            List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon,
            String sChuongTrinh, String sTrangThai, int nStartRow, int nEndRow) {
        List<DcplnModel> lstDcplNo = new ArrayList<DcplnModel>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_DATA_PLNO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaySL);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMaTo);
            calstatement.setString(7, sNguonVon);
            calstatement.setString(8, sChuongTrinh);
            calstatement.setString(9, sTrangThai);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            iErr_CD = calstatement.getInt(12);
            sEdd_TXT = calstatement.getString(13);

            while (reset.next()) {
                DcplnModel value = new DcplnModel();
                value.setbStt(reset.getInt(1));
                value.setsMacn(reset.getString(2));
                value.setsMapgd(reset.getString(3));
                value.setsMaxa(reset.getString(4));
                value.setsNguonvon(reset.getString(5));
                value.setsNguonvon_Ten(reset.getString(6));
                value.setsDvut(reset.getString(7));
                value.setsDvut_Ten(reset.getString(8));
                value.setsChtrinh(reset.getString(9));
                value.setsChtrinh_Ten(reset.getString(10));
                value.setsChtrinh_Tenvt(reset.getString(11));
                value.setsMato(reset.getString(12));
                value.setsTentt(reset.getString(13));
                value.setsMakh(reset.getString(14));
                value.setsTenkh(reset.getString(15));
                value.setsSoku(reset.getString(16));
                value.setsDnothan(reset.getBigDecimal(17).toString());
                value.setsDnoqhan(reset.getBigDecimal(18).toString());
                value.setsDnokhoanh(reset.getBigDecimal(19).toString());

                BigDecimal bTongDN = BigDecimal.ZERO;
                bTongDN = bTongDN.add(reset.getBigDecimal(17));
                bTongDN = bTongDN.add(reset.getBigDecimal(18));
                bTongDN = bTongDN.add(reset.getBigDecimal(19));
                value.setsTongDN(bTongDN.toString());
                value.setsTonglaiton(reset.getBigDecimal(20).toString());
                value.setsC_Kntn_Sodu(reset.getBigDecimal(21).toString());
                value.setsK_Kntn_Sodu(reset.getBigDecimal(22).toString());
                value.setsNgnhan_Kckntn(reset.getString(23));
                value.setsK_Ngnhan_Kh(reset.getString(24));
                value.setsQuanhe_Kh(reset.getString(25));
                value.setsTrangthai(reset.getString(26));
                value.setsNogoc_Clech(reset.getBigDecimal(27).toString());
                value.setsNolai_Clech(reset.getBigDecimal(28).toString());
                value.setsNgnhan_Clech(reset.getString(29));
                value.setsTt_Monvay(reset.getString(30));
                value.setsNgaybc(reset.getString(31));
                value.setsNguoi_Pln(reset.getString(32));
                value.setsNgay_Pln(reset.getString(33));
                lstDcplNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDataPLN " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPLN -> " + e.getMessage());
        }
        return lstDcplNo;
    }

    /**
     * Hàm thực hiện trả về Số lượng Món vay thực hiện phân loại nợ trên màn
     * hình. Tùy theo tham số truyền vào
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sNgaySL: Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPosCD: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @param sTrangThai: Giá trị mã Trạng thái bản ghi theo quy ước.
     * @return: Tổng số món vay
     */
    public int getCountTotalPLN(Connection conn, String sUserName, String sGrade, String sNgaySL, List<String> lstArrPosCD,
            String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh, String sTrangThai) {
        int nCountLoan = 0;
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call PLNO_TAOSOLIEU.F_GET_TOTAL_COUNT_PLNO(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaySL);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMaTo);
            calstatement.setString(8, sNguonVon);
            calstatement.setString(9, sChuongTrinh);
            calstatement.setString(10, sTrangThai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountLoan = calstatement.getInt(1);
            iErr_CD = calstatement.getInt(11);
            sEdd_TXT = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalPLN " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalPLN -> " + e.getMessage());
        }
        return nCountLoan;
    }

    /**
     * Hàm thực hiện trả về Thông tin chi tiết của món vay theo tham số truyền
     * vào
     *
     * @param sSoKU: Số hiệu mã khoản vay cần lấy thông tin chi tiết
     * @param sNgaySL: Ngày tạo số liệu. Ngày báo cáo
     * @param sMaTo: Mã hiểu tổ nhóm của món vay
     * @return: Thông tin chi tiết món vay
     */
    
    
    
    public List<DcplnModel> getDetailLoan(String sSoKU, String sNgaySL, String sMaTo) {
        List<DcplnModel> lstDcPlno = new ArrayList<DcplnModel>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_DETAIL_LOAN(?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sSoKU);
            calstatement.setString(2, sNgaySL);
            calstatement.setString(3, sMaTo);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(6);

            iErr_CD = calstatement.getInt(4);
            sEdd_TXT = calstatement.getString(5);

            while (reset.next()) {
                DcplnModel value = new DcplnModel();
                value.setbStt(reset.getInt(1));
                value.setsSoku(reset.getString(2));
                value.setsMakh(reset.getString(3));
                value.setsTenkh(reset.getString(4));
                value.setsMato(reset.getString(5));
                value.setsTentt(reset.getString(6));
                value.setsDvut(reset.getString(7));
                value.setsDvut_Ten(reset.getString(8));
                value.setsMadp(reset.getString(9));
                value.setsNguonvon(reset.getString(10));
                value.setsNguonvon_Ten(reset.getString(11));
                value.setsSprd_Cd(reset.getString(12));
                value.setsSprd_Cd_Ten(reset.getString(13));
                value.setsChtrinh(reset.getString(14));
                value.setsChtrinh_Ten(reset.getString(15));
                value.setsChtrinh_Tenvt(reset.getString(16));
                value.setsDnothan(reset.getBigDecimal(17).toString());
                value.setsDnoqhan(reset.getBigDecimal(18).toString());
                value.setsDnokhoanh(reset.getBigDecimal(19).toString());

                BigDecimal bTongDN = BigDecimal.ZERO;
                bTongDN = bTongDN.add(reset.getBigDecimal(17));
                bTongDN = bTongDN.add(reset.getBigDecimal(18));
                bTongDN = bTongDN.add(reset.getBigDecimal(19));
                value.setsTongDN(bTongDN.toString());

                value.setsLaitonthan(reset.getBigDecimal(20).toString());
                value.setsLaitonqhan(reset.getBigDecimal(21).toString());
                value.setsTonglaiton(reset.getBigDecimal(22).toString());
                value.setsTonglai_Tt(reset.getBigDecimal(23).toString());
                value.setsC_Kntn_Sodu(reset.getBigDecimal(24).toString());
                value.setsK_Kntn_Sodu(reset.getBigDecimal(25).toString());

                value.setsNgnhan_Kckntn(reset.getString(26));
                value.setsQuanhe_Kh(reset.getString(27));
                value.setsTrangthai(reset.getString(28));
                value.setsTrangthai_Ten(reset.getString(29));
                value.setsNogoc_Clech(reset.getBigDecimal(30).toString());
                value.setsNolai_Clech(reset.getBigDecimal(31).toString());
                value.setsNgnhan_Clech(reset.getString(32));
                value.setsTt_Monvay(reset.getString(33));
                value.setsNgaybc(reset.getString(34));

                value.setsNguoi_Pln(reset.getString(35));
                value.setsNgay_Pln(reset.getString(36));
                value.setsTrangthaino(reset.getString(37));
                value.setsTrangthaino_Ten(reset.getString(38));
                value.setsTchat_No(reset.getString(39));
                value.setsTchat_No_Ten(reset.getString(40));
                value.setsLoaito(reset.getString(41));
                value.setsLoaito_Ten(reset.getString(42));
                value.setsNgaycn(reset.getString(43));
                value.setsMacn(reset.getString(44));
                value.setsMapgd(reset.getString(45));
                value.setsMaxa(reset.getString(46));

                lstDcPlno.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDetailLoan " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetailLoan -> " + e.getMessage());
        }
        return lstDcPlno;
    }

    /**
     * Hàm thực hiện Lấy danh sách dòng bản ghi tổng cộng thông tin: Số lượng món vay, Khách hàng, Tổng dư nợ, lãi tồn
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sNgaySL: Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPoscd: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @param sTrangThai: Giá trị mã Trạng thái bản ghi theo quy ước.
     * @return
     */
    public List<DcplnModel.ViewTotalLoan> getViewTotalLoanData(Connection conn, String sUserName, String sGrade,
            String sNgaySL, List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh,
            String sTrangThai) {
        List<DcplnModel.ViewTotalLoan> lstViewLoan = new ArrayList<DcplnModel.ViewTotalLoan>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_VIEW_TOTAL_LOAN(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaySL);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMaTo);
            calstatement.setString(7, sNguonVon);
            calstatement.setString(8, sChuongTrinh);
            calstatement.setString(9, sTrangThai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            iErr_CD = calstatement.getInt(10);
            sEdd_TXT = calstatement.getString(11);
            reset = (ResultSet) calstatement.getObject(12);
            while (reset.next()) {
                DcplnModel.ViewTotalLoan value = new DcplnModel.ViewTotalLoan();
                value.setsSlg_KH(Integer.toString(reset.getInt(1)));
                value.setsSlg_KU(Integer.toString(reset.getInt(2)));
                value.setsDnothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsDnoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsDnokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsTongDN(DefineFun.FormatNumber(reset.getBigDecimal(6)));
                value.setsTonglaiton(DefineFun.FormatNumber(reset.getBigDecimal(7)));
                lstViewLoan.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getViewTotalLoanData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalLoanData -> " + e.getMessage());
        }
        return lstViewLoan;
    }

    /**
     * Hàm thực hiện Lấy danh sách dòng bản ghi tổng cộng thông tin: Số lượng món vay, Khách hàng, Tổng dư nợ, lãi tồn
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sNgaySL: Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPoscd: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @param sTrangThai: Giá trị mã Trạng thái bản ghi theo quy ước.
     * @return
     */
    public List<DcplnModel.ViewTotalLoan> getViewTotalSearchData(Connection conn, String sUserName, String sGrade,
            String sNgaySL, List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh) {
        List<DcplnModel.ViewTotalLoan> lstViewLoan = new ArrayList<DcplnModel.ViewTotalLoan>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_VIEW_TOTAL_SEARCH_LOAN(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaySL);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMaTo);
            calstatement.setString(7, sNguonVon);
            calstatement.setString(8, sChuongTrinh);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            iErr_CD = calstatement.getInt(9);
            sEdd_TXT = calstatement.getString(10);
            reset = (ResultSet) calstatement.getObject(11);
            while (reset.next()) {
                DcplnModel.ViewTotalLoan value = new DcplnModel.ViewTotalLoan();
                value.setsSlg_KH(Integer.toString(reset.getInt(1)));
                value.setsSlg_KU(Integer.toString(reset.getInt(2)));
                value.setsDnothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsDnoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsDnokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsTongDN(DefineFun.FormatNumber(reset.getBigDecimal(6)));
                value.setsTonglaiton(DefineFun.FormatNumber(reset.getBigDecimal(7)));
                lstViewLoan.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getViewTotalSearchData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalSearchData -> " + e.getMessage());
        }
        return lstViewLoan;
    }
    
    /**
     * Hàm thực hiện trả về Danh sách món vay tải lên thực hiện Đối chiếu, Phân
     * loại nợ (Khi tìm kiếm theo Số khế ước)
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sSoKU: Mã món vay cần tìm kiếm (Có thể Tuyệt đối hoặc Tương đối)
     * @param sNgaySL: Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPosCD: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @param nStartRow: Chỉ số đầu bản ghi cần lấy (Giới hạn số thứ tự bản ghi
     * đầu)
     * @param nEndRow: Chỉ số cuối bản ghi cần lấy (Giới hạn số thứ tự bản ghi
     * cuối)
     * @return: Danh sách thông tin món vay thực hiện Đối chiếu, Phân loại nợ
     * Trả về
     */
    public List<DcplnModel> getDataSearchLoan(Connection conn, String sUserName, String sGrade, String sSoKU,
            String sNgaySL, List<String> lstArrPosCD,
            String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh, int nStartRow, int nEndRow) {
        List<DcplnModel> lstDcplNo = new ArrayList<DcplnModel>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_SEARCH_LOAN_PLNO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
    
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sSoKU);
            calstatement.setString(4, sNgaySL);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMaTo);
            calstatement.setString(8, sNguonVon);
            calstatement.setString(9, sChuongTrinh);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            iErr_CD = calstatement.getInt(12);
            sEdd_TXT = calstatement.getString(13);

            while (reset.next()) {
                DcplnModel value = new DcplnModel();
                value.setbStt(reset.getInt(1));
                value.setsMacn(reset.getString(2));
                value.setsMapgd(reset.getString(3));
                value.setsMaxa(reset.getString(4));
                value.setsNguonvon(reset.getString(5));
                value.setsNguonvon_Ten(reset.getString(6));
                value.setsDvut(reset.getString(7));
                value.setsDvut_Ten(reset.getString(8));
                value.setsChtrinh(reset.getString(9));
                value.setsChtrinh_Ten(reset.getString(10));
                value.setsChtrinh_Tenvt(reset.getString(11));
                value.setsMato(reset.getString(12));
                value.setsTentt(reset.getString(13));
                value.setsMakh(reset.getString(14));
                value.setsTenkh(reset.getString(15));
                value.setsSoku(reset.getString(16));
                value.setsDnothan(reset.getBigDecimal(17).toString());
                value.setsDnoqhan(reset.getBigDecimal(18).toString());
                value.setsDnokhoanh(reset.getBigDecimal(19).toString());

                BigDecimal bTongDN = BigDecimal.ZERO;
                bTongDN = bTongDN.add(reset.getBigDecimal(17));
                bTongDN = bTongDN.add(reset.getBigDecimal(18));
                bTongDN = bTongDN.add(reset.getBigDecimal(19));
                value.setsTongDN(bTongDN.toString());
                value.setsTonglaiton(reset.getBigDecimal(20).toString());
                value.setsC_Kntn_Sodu(reset.getBigDecimal(21).toString());
                value.setsK_Kntn_Sodu(reset.getBigDecimal(22).toString());
                value.setsNgnhan_Kckntn(reset.getString(23));
                value.setsK_Ngnhan_Kh(reset.getString(24));
                value.setsQuanhe_Kh(reset.getString(25));
                value.setsTrangthai(reset.getString(26));
                value.setsNogoc_Clech(reset.getBigDecimal(27).toString());
                value.setsNolai_Clech(reset.getBigDecimal(28).toString());
                value.setsNgnhan_Clech(reset.getString(29));
                value.setsTt_Monvay(reset.getString(30));
                value.setsNgaybc(reset.getString(31));
                value.setsNguoi_Pln(reset.getString(32));
                value.setsNgay_Pln(reset.getString(33));
                lstDcplNo.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDataSearchLoan " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataSearchLoan -> " + e.getMessage());
        }
        return lstDcplNo;
    }


    /**
     * Hàm thực hiện trả về Tổng số món vay khi thực hiện Tìm kiếm theo điều
     * kiện truyền vào (Phục vụ phân trang)
     *
     * @param conn: Object Connection
     * @param sUserName: Tên người dùng đăng nhập chương trình
     * @param sGrade: Cấp chạy chương trình ('1' - Ngân hàng; '2' - Chi nhánh;
     * '3' - Toàn quốc)
     * @param sSoKU: Mã món vay cần tìm kiếm (Có thể Tuyệt đối hoặc Tương đối)
     * @param sNgaySL Ngày báo cáo (Ngày lấy số liệu)
     * @param lstArrPosCD: Mảng danh sách Pos tùy theo cấp truyền vào (Ví dụ:
     * Cấp ngân hàng -> Mảng các xã)
     * @param sDvut: Giá trị mã hiệu Đơn vị ủy thác
     * @param sMaTo: Mã tổ TK&VV
     * @param sNguonVon: Giá trị mã nguồn vốn ('1' - TW; '2' - ĐP)
     * @param sChuongTrinh: Giá trị mã Chương trình cho vay
     * @return: Tổng số món vay trả về
     */
    public int getCountTotalSearchLoan(Connection conn, String sUserName, String sGrade, String sSoKU, String sNgaySL,
            List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh) {
        int nCountLoan = 0;
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call PLNO_TAOSOLIEU.F_GET_TOTAL_SEARCH_LOAN_PLNO(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sSoKU);
            calstatement.setString(5, sNgaySL);
            calstatement.setArray(6, oracle_arrayPoscd);
            calstatement.setString(7, sDvut);
            calstatement.setString(8, sMaTo);
            calstatement.setString(9, sNguonVon);
            calstatement.setString(10, sChuongTrinh);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountLoan = calstatement.getInt(1);
            iErr_CD = calstatement.getInt(11);
            sEdd_TXT = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getCountTotalSearchLoan " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalSearchLoan -> " + e.getMessage());
        }
        return nCountLoan;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hàm thực hiện Lưu thông tin Phân loại nợ - Đối chiếu">    
        public boolean SaveDataPLNO_KHTN(String sUserName, String sNgaySL, String sMaTo, List<PLNO_DULIEU> lstPLNo) throws SQLException {
        if (lstPLNo == null || lstPLNo.size() == 0) {
            return false;
        }
        int iErr_CD = 0;
        String sEdd_TXT = "";
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstPLNo.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor(PLNO_DULIEU.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement calstatement = null;
        boolean bSuccess = false;
        try {
            calstatement = connection.prepareCall("{call PLNO_TAOSOLIEU.SP_SAVE_PLNO_KNTN(?, ?, ?, ?, ?, ?)}");
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sNgaySL);
            calstatement.setString(3, sMaTo);
            calstatement.setArray(4, array_to_pass);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            bSuccess = true;
            iErr_CD = calstatement.getInt(5);
            sEdd_TXT = calstatement.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham SaveDataPLNO_KHTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SaveDataPLNO_KHTN -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }

    public boolean SaveDataPLNO_DC(String sUserName, String sNgaySL, String sMaTo, List<PLNO_DULIEU> lstPLNo) throws SQLException {
        if (lstPLNo == null || lstPLNo.size() == 0) {
            return false;
        }
        int iErr_CD = 0;
        String sEdd_TXT = "";
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstPLNo.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor(PLNO_DULIEU.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement calstatement = null;
        boolean bSuccess = false;
        try {
            calstatement = connection.prepareCall("{call PLNO_TAOSOLIEU.SP_SAVE_PLNO_DC(?, ?, ?, ?, ?, ?)}");
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sNgaySL);
            calstatement.setString(3, sMaTo);
            calstatement.setArray(4, array_to_pass);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            bSuccess = true;
            iErr_CD = calstatement.getInt(5);
            sEdd_TXT = calstatement.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham SaveDataPLNO_DC " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SaveDataPLNO_DC -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }
    
    //    Quyennv - Gui so lieu (Viết từ đây)
    public int getCountTotalSendGrpPLN(Connection conn, String sUserName, String sGrade, String sNgaySL, List<String> lstArrPosCD,
            String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh, String sTrangThai)
    {
        int nCountLoan = 0;
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call PLNO_TAOSOLIEU.F_GET_TOTAL_COUNT_SENDGRP_PLNO(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaySL);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMaTo);
            calstatement.setString(8, sNguonVon);
            calstatement.setString(9, sChuongTrinh);
            calstatement.setString(10, sTrangThai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountLoan = calstatement.getInt(1);
            iErr_CD = calstatement.getInt(11);
            sEdd_TXT = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalPLN " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalPLN -> " + e.getMessage());
        }
        return nCountLoan;
    }
    
    public List<DcplnModel> getDataSendGrpPLN(Connection conn, String sUserName,String sGrade, String sNgaySL,
                                       List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon, 
                                       String sChuongTrinh, String sTrangThai, int nStartRow, int nEndRow)
    {
        List<DcplnModel> lstDcplNo = new ArrayList<DcplnModel>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_DATA_SENDGRP_PLNO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaySL);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMaTo);
            calstatement.setString(7, sNguonVon);
            calstatement.setString(8, sChuongTrinh);
            calstatement.setString(9, sTrangThai);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            iErr_CD = calstatement.getInt(12);
            sEdd_TXT = calstatement.getString(13);

            while (reset.next()) {
                DcplnModel value = new DcplnModel();
                value.setbStt(reset.getInt(1));
                value.setsMacn(reset.getString(2));
                value.setsMapgd(reset.getString(3));
                value.setsMaxa(reset.getString(4));
                value.setsNguonvon(reset.getString(5));
                value.setsNguonvon_Ten(reset.getString(6));
                value.setsDvut(reset.getString(7));
                value.setsDvut_Ten(reset.getString(8));
                value.setsChtrinh(reset.getString(9));
                value.setsChtrinh_Ten(reset.getString(10));
                value.setsChtrinh_Tenvt(reset.getString(11));
                value.setsMato(reset.getString(12));
                value.setsTentt(reset.getString(13));
                value.setsMakh(reset.getString(14));
                value.setsTenkh(reset.getString(15));
                value.setsSoku(reset.getString(16));
                value.setsDnothan(reset.getBigDecimal(17).toString());
                value.setsDnoqhan(reset.getBigDecimal(18).toString());
                value.setsDnokhoanh(reset.getBigDecimal(19).toString());
                
                BigDecimal bTongDN = BigDecimal.ZERO;
                bTongDN = bTongDN.add(reset.getBigDecimal(17));
                bTongDN = bTongDN.add(reset.getBigDecimal(18));
                bTongDN = bTongDN.add(reset.getBigDecimal(19));
                value.setsTongDN(bTongDN.toString());
                value.setsTonglaiton(reset.getBigDecimal(20).toString());
                value.setsC_Kntn_Sodu(reset.getBigDecimal(21).toString());
                value.setsK_Kntn_Sodu(reset.getBigDecimal(22).toString());
                value.setsNgnhan_Kckntn(reset.getString(23));
                value.setsQuanhe_Kh(reset.getString(24));
                value.setsTrangthai(reset.getString(25));
                value.setsNogoc_Clech(reset.getBigDecimal(26).toString());
                value.setsNolai_Clech(reset.getBigDecimal(27).toString());
                value.setsNgnhan_Clech(reset.getString(28));
                value.setsTt_Monvay(reset.getString(29));
                value.setsNgaybc(reset.getString(30));
                value.setsNguoi_Pln(reset.getString(31));
                value.setsNgay_Pln(reset.getString(32));
                lstDcplNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getDataPLN " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPLN -> " + e.getMessage());
        }
        return lstDcplNo;
    }
    
    public List<DcplnModel.ViewTotalLoan> getViewTotalLoanDataGrpSend(Connection conn, String sUserName, String sGrade,
            String sNgaySL, List<String> lstArrPosCD, String sDvut, String sMaTo, String sNguonVon, String sChuongTrinh,
            String sTrangThai)
    {
        List<DcplnModel.ViewTotalLoan> lstViewLoan = new ArrayList<DcplnModel.ViewTotalLoan>();
        int iErr_CD = 0;
        String sEdd_TXT = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPosCD.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_VIEW_TOTAL_LOAN_SENDGRP(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaySL);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMaTo);
            calstatement.setString(7, sNguonVon);
            calstatement.setString(8, sChuongTrinh);
            calstatement.setString(9, sTrangThai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            iErr_CD = calstatement.getInt(10);
            sEdd_TXT = calstatement.getString(11);
            reset = (ResultSet) calstatement.getObject(12);
            while (reset.next()) 
            {
                DcplnModel.ViewTotalLoan value = new DcplnModel.ViewTotalLoan();
                value.setsSlg_KH(Integer.toString(reset.getInt(1)));
                value.setsSlg_KU(Integer.toString(reset.getInt(2)));
                value.setsDnothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsDnoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsDnokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsTongDN(DefineFun.FormatNumber(reset.getBigDecimal(6)));
                value.setsTonglaiton(DefineFun.FormatNumber(reset.getBigDecimal(7)));
                lstViewLoan.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) 
        {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getViewTotalLoanData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalLoanData -> " + e.getMessage());
        }
        return lstViewLoan;
    }
    
    public HashMap<Integer,Object> getDataSendPln(String sUserName, String sGrade,List<String> lstPoscd, String sNgaysl, String sDvut,
            String sMato, List<String> lstSoku) {
        HashMap<Integer,Object> hmObjOut = new HashMap<Integer,Object>();
        List<String> lstDataSend = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_GET_DATA_SEND_PNL(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
            ArrayDescriptor des_soku = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            String[] arraySoku= lstSoku.toArray(new String[0]);
            ARRAY oracle_arraySoku= new ARRAY(des_soku, conn, arraySoku);
            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sNgaysl);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setArray(7, oracle_arraySoku);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            String sPos_cd=calstatement.getString(10);
            reset = (ResultSet) calstatement.getObject(11);
            while (reset.next()) {
                lstDataSend.add(reset.getString(1));
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
            hmObjOut.put(1, sPos_cd==null?"999999":sPos_cd==""?"999999":sPos_cd);
            hmObjOut.put(2, lstDataSend);
        } catch (Exception e) {
            System.err.println(" Loi trong ham getDataSend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSend -> " + e.getMessage());
        }
        return hmObjOut;
    }
    
    public List<ViewTotalCust> getViewTotalCustDataSendAll(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sTrangthai) {
        List<ViewTotalCust> lstViewCust = new ArrayList<ViewTotalCust>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_VIEW_TOTAL_SEND_ALL(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sTrangthai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            pn_err_cd = calstatement.getInt(7);
            strEdd_txt = calstatement.getString(8);
            reset = (ResultSet) calstatement.getObject(9);
            while (reset.next()) {
                ViewTotalCust value = new ViewTotalCust();
                value.setsSoKh(Integer.toString(reset.getInt(1)));
                value.setsTongtien(DefineFun.FormatNumber(reset.getBigDecimal(2)));
                value.setsNothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsNoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsNokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsNolai(DefineFun.FormatNumber(reset.getBigDecimal(8)));
                value.setsSoduCasa(DefineFun.FormatNumber(reset.getBigDecimal(9)));
                value.setsNoNCK(DefineFun.FormatNumber(reset.getBigDecimal(10)));
                value.setsNoKCKN(DefineFun.FormatNumber(reset.getBigDecimal(11)));
                System.err.println("Tong so kh=" + Integer.toString(reset.getInt(1)));
                lstViewCust.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getViewTotalCustDataSend " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalCustDataSend -> " + e.getMessage());
        }
        return lstViewCust;
    }
    
    public List<DcptNoModel.senddcpt> getDataCustSendAll(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sTrangthai) {
        List<DcptNoModel.senddcpt> lstDcptNo = new ArrayList<DcptNoModel.senddcpt>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL PLNO_TAOSOLIEU.SP_GET_DATA_SEND_ALL(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sTrangthai);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(9);

            pn_err_cd = calstatement.getInt(7);
            strEdd_txt = calstatement.getString(8);

            while (reset.next()) {
                DcptNoModel.senddcpt value = new DcptNoModel.senddcpt();
                value.setsMato(reset.getString(2));
                value.setsSoKH(reset.getString(3));
                value.setsNogoc(reset.getString(4));
                value.setsNolai(reset.getString(5));
                value.setsTietkiem(reset.getString(6));
                value.setsCLGoc(reset.getBigDecimal(7).toString());
                value.setsCLLai(reset.getBigDecimal(8).toString());
                value.setsCLTietkiem(reset.getBigDecimal(9).toString());              
                value.setsNoCKN(reset.getBigDecimal(10).toString());
                value.setsNoKCKN(reset.getBigDecimal(11).toString());               

                lstDcptNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCustSend " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCustSend -> " + e.getMessage());
        }
        return lstDcptNo;
    }
    
    public boolean updateData_Sync(String strUserName, String sGrade, String sPoscd, List<DcplnModel> lstDcPln) {
        boolean bSuccess = false;
        if (lstDcPln.size() == 0) {
            //neu du lieu la ko co khach hang thi insert log
//            insertHistotySendLog(sPoscd, strUserName, sGrade, sNambc, sDotrr, sNhomrr, new BigDecimal(BigInteger.ZERO), Define.KHOA_SEND_RR);
            return true;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call PLNO_TAOSOLIEU.SP_UPDATE_SYNC(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("DCPLN_TYPE_SYNC", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstDcPln.size()];
                int index = 0;
                for (DcplnModel value : lstDcPln) {

                    Object[] params = new Object[24];
                    params[0]=value.getsSoku();
                    params[1]=value.getsDvut();
                    params[2]=value.getsMato();

                    params[3]=value.getsC_Kntn_Sodu();
                    params[4]=value.getsK_Kntn_Sodu();
                    params[5]=value.getsK_Kntn_Sd01();
                    params[6]=value.getsK_Kntn_Sd02();
                    params[7]=value.getsK_Kntn_Sd03();
                    params[8]=value.getsK_Kntn_Sd04();
                    params[9]=value.getsK_Kntn_Sd05();
                    params[10]=value.getsK_Kntn_Sd06();
                    params[11]=value.getsK_Kntn_Sd07();
                    params[12]=value.getsK_Kntn_Sd08();
                    params[13]=value.getsK_Kntn_Sd09();
                    params[14]=value.getsK_Kntn_Sd10();
                    params[15]=value.getsK_Kntn_Sd11();
                    
                    params[16]=value.getsNogoc_Clech();
                    params[17]=value.getsNolai_Clech();
                    params[18]=value.getsNgnhan_Clech();
                    params[19]=value.getsK_Ngnhan_Kh();
                    
                    params[20]=value.getsTrangthai();
                    params[21]=value.getsNgay_Pln();
                    params[22]=value.getsNgay_Pln();
                    params[23]=value.getsQuanhe_Kh();

                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "DCPLN_TAB_SYNC", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sPoscd);
                calstatement.setArray(4, oracleArray);

                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " updateData_Sync_Pln -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham updateData_Sync_Pln " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " updateData_Sync_Pln -> " + e.getMessage());
        }
        return bSuccess;
    }
    
    public boolean updateAfterSend(String sUserName, String sGrade,List<String> lstPoscd, String sNgaysl, String sDvut,
            String sMato, List<String> lstSoku) {
        boolean bSuccess = false;
        HashMap<Integer,Object> hmObjOut = new HashMap<Integer,Object>();
        List<String> lstDataSend = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PLNO_TAOSOLIEU.SP_UPDATE_AFTER_SEND(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
            ArrayDescriptor des_soku = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            String[] arraySoku= lstSoku.toArray(new String[0]);
            ARRAY oracle_arraySoku= new ARRAY(des_soku, conn, arraySoku);
            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sNgaysl);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setArray(7, oracle_arraySoku);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            bSuccess = calstatement.execute();
            String sPos_cd=calstatement.getString(10);            
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
            hmObjOut.put(1, sPos_cd==null?"999999":sPos_cd==""?"999999":sPos_cd);
            hmObjOut.put(2, lstDataSend);
        } catch (Exception e) {
            System.err.println(" Loi trong ham updateAfterSend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " updateAfterSend -> " + e.getMessage());
            return false;
        }
        return true;
    }
    //</editor-fold>
}
