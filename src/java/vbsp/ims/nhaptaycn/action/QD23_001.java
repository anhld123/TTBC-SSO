/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.encrypt.DES;
import vbsp.ims.khnv2021.excel.ExcelExport;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class QD23_001 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    @Override
    public String load() {
        try {
//            System.err.println("QD23_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();

            // Nhập kế hoạch
            if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("1")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                setLstHinhthucTNHS(daoMain.getCanBo(UserName, "HINHTHUCTNHS"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstLuongVung(daoMain.getCanBo(UserName, "LUONGVUNG"));
                setLstPLKT(daoMain.getCanBo(UserName, "PLKT1A"));
                setLstDTTH(daoMain.getCanBo(UserName, "DTTH"));
                setLstThangvay(daoMain.getCanBo(UserName, "THANGVAY"));
                return "nhap_1_kh_pgd";
            } else if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("2")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                setLstHinhthucTNHS(daoMain.getCanBo(UserName, "HINHTHUCTNHS"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstLuongVung(daoMain.getCanBo(UserName, "LUONGVUNG"));
                setLstPLKT(daoMain.getCanBo(UserName, "PLKT1A"));
                setLstDTTH(daoMain.getCanBo(UserName, "DTTH"));
                setLstDNVON(daoMain.getCanBo(UserName, "DNVON"));
                return "nhap_2_pheduyetcv_pgd";
            } else if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("3")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                setLstHinhthucTNHS(daoMain.getCanBo(UserName, "HINHTHUCTNHS"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
                setLstLuongVung(daoMain.getCanBo(UserName, "LUONGVUNG"));
                setLstPLKT(daoMain.getCanBo(UserName, "PLKT1A"));
                setLstDTTH(daoMain.getCanBo(UserName, "DTTH"));
                setLstDNVON(daoMain.getCanBo(UserName, "DNVON"));
                setLstSoKU(daoMain.getCanBo(UserName, "SOKU23"));
                return "nhap_3_pheduyetgn_pgd";
            } //Điều chỉnh giảm
            //            else if (Grade.equals("1") && hmParameter.get("type_action").toString().substring(0, 1).equals("G")) {
            //                lstDulieuNt = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
            //                setLstHinhthucTNHS(daoMain.getCanBo(UserName, "HINHTHUCTNHS"));
            //                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
            //                setLstNgayluongHD(daoMain.getCanBo(UserName, "NGAYLUONGHD"));
            //                setLstLuongVung(daoMain.getCanBo(UserName, "LUONGVUNG"));
            //                return "dieuchinh_pgd";
            //            }
            else if (Grade.equals("2") && hmParameter.get("type_action").toString().equals("1")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                return "xaydungkh_cn";
            } else if (Grade.equals("2") && hmParameter.get("type_action").toString().equals("2")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                return "dieuchinhkh_cn";
            } else if (Grade.equals("3") && hmParameter.get("type_action").toString().equals("1")) {
                String soquyetdinh = hmParameter.get("soqd").toString();
                String ngayquyetdinh = hmParameter.get("ngay_qd").toString();
                String lanquyetdinh = hmParameter.get("lanqd").toString();
                String sotide = hmParameter.get("sotide").toString();
                String tinhchatvon = hmParameter.get("tc_von").toString();
                String chot_kh = hmParameter.get("chot_kh").toString();

                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd,
                        soquyetdinh + "#" + ngayquyetdinh + "#" + lanquyetdinh + "#" + sotide + "#" + tinhchatvon + "#" + chot_kh,
                        hmParameter.get("type_action").toString());
                setLstTinhchatNV(daoMain.getCanBo(UserName, "TINHTCHAT_NV"));
                setLstChotKH(daoMain.getCanBo(UserName, "CHOTKH"));
                return "xaydungkh_tw";
            } else if (Grade.equals("3") && hmParameter.get("type_action").toString().equals("2")) {
                String soquyetdinh = hmParameter.get("soqd").toString();
                String ngayquyetdinh = hmParameter.get("ngay_qd").toString();
                String lanquyetdinh = hmParameter.get("lanqd").toString();
                String chot_kh = hmParameter.get("chot_kh").toString();
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, soquyetdinh + "#" + ngayquyetdinh + "#" + lanquyetdinh + "#" + chot_kh,
                        hmParameter.get("type_action").toString());
                setLstTinhchatNV(daoMain.getCanBo(UserName, "TTDUYET"));
                setLstChotKH(daoMain.getCanBo(UserName, "CHOTKH"));
                return "dieuchinhkh_tw";
            } else if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("5")) {
                lstDulieuNt50 = daoMain.getDataQd23_001(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
                return "canhbao_pgd";
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QD23_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QD23_001: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.err.println("Save - QD23_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt50 == null || lstDulieuNt50.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
//            String sNv = hmParameter.get("type_action").toString();
            if (daoMain.checkSave_Send(khoa_nhaptaycn, Grade, hmParameter.get("ngay_bc").toString(), "SAVE", UserName, poscd) == 0) {
                addActionError("Bạn vui lòng chọn ngày hiện tại và nhập cột 43,44 (cấp chi nhánh)!");
                return ERROR;
            }

            if (hmParameter.get("type_action").toString().equals("2") && Grade.equals("3")) { //Duyệt TH điều chỉnh KH
                if (!daoMain.saveQD23_001("QD23_003", UserName, "", hmParameter.get("ngay_bc").toString(), Grade, lstDulieuNt50, hmParameter.get("type_action").toString())) {
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            } else {
                String iCheck = daoMain.checkData_Info_50(lstDulieuNt50, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, lstDat, hmParameter.get("type_action").toString());
                if (!iCheck.equals("XXXAAA")) {
                    addActionError("Lỗi! " + iCheck);
                    return ERROR;
                }
                if (!daoMain.saveQD23_001(khoa_nhaptaycn, UserName, "", hmParameter.get("ngay_bc").toString(), Grade, lstDulieuNt50, hmParameter.get("type_action").toString())) {
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String saveDieuchinhKH() {
//        System.err.println("Save - QD23_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt50 == null || lstDulieuNt50.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(date1);

            if (daoMain.checkSave_Send("QD23_003", Grade, dateStr, "SAVE", UserName, poscd) == 0) {
                addActionError("Bạn chỉ được lưu số liệu ngày hiện tại. Vui lòng chọn ngày hiện tại!");
                return ERROR;
            }
            if (!daoMain.saveQD23_001_Dieuchinh("QD23_003", UserName, "", hmParameter.get("ngay_bc").toString(), Grade, lstDulieuNt50, hmParameter.get("thangbc").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveDieuchinhKH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveDieuchinhKH: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String saveDieuchinhPheduyet() {
//        System.err.println("Save - QD23_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt50 == null || lstDulieuNt50.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(date1);

            if (daoMain.checkSave_Send(hmParameter.get("khoadc").toString(), Grade, dateStr, "SAVE", UserName, poscd) == 0) {
                addActionError("Bạn chỉ được lưu số liệu ngày hiện tại. Vui lòng chọn ngày hiện tại!");
                return ERROR;
            }
            if (!daoMain.saveQD23_001_Dieuchinh(hmParameter.get("khoadc").toString(), UserName, "", hmParameter.get("ngay_bc").toString(), Grade, lstDulieuNt50, hmParameter.get("thangbc").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveDieuchinhKH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveDieuchinhKH: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String loadDieuchinhKh() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            lstDulieuNt50 = daoMain.getDataQd23_001_Dieuchinh(conn, "QD23_003", hmParameter.get("ngay_bc").toString(), UserName, Grade, hmParameter.get("masothue").toString(), hmParameter.get("thangbc").toString());
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadDieuchinhPheduyetChovay() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            lstDulieuNt50 = daoMain.getDataQd23_001_Dc_pheduyet(conn, hmParameter.get("khoadc").toString(),
                    hmParameter.get("ngay_bc").toString(), UserName, Grade, hmParameter.get("masothue").toString(), hmParameter.get("thangbc").toString());
            if (conn != null) {
                conn.close();
            }
            if (hmParameter.get("khoadc").toString().equals("QD23_004")) {
                return "pgd_dc_pheduyet_cv";
            } else if (hmParameter.get("khoadc").toString().equals("QD23_005")) {
                return "pgd_dc_pheduyet_gn";
            } else if (hmParameter.get("khoadc").toString().equals("QD23_006")) {
                return "pgd_dc_pheduyet_dn";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//        userGroupManager.delete(userGroupCode);
            HashMap hmParameter = getParameter();
            String s42 = masothue;
//        String s4 = thangbc;
//        String s = dtth;
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            if (daoMain.deleteQD23("QD23_001", UserName, "", Grade, masothue) == 0) {
                addActionError("Xóa lỗi");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            addActionError("Xóa lỗi");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String uploadCoVid() {
        try {
            System.err.println("Upload file");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            if (fileUploadFileName.isEmpty()) {
                addActionError("Bạn chưa chọn file để thực hiện upload !");
                return ERROR;
            }
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            String start_end = dao.getStartEndCel("COVID_NLD_GOC");
            String poscd = dao.getPosCd(UserName);
            int startrow = 0, endcell = 0;
            if (!start_end.equals("AAA")) {
                startrow = Integer.parseInt(start_end.split("-")[0]);
                endcell = Integer.parseInt(start_end.split("-")[1]);
            }
            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
                if (!getFileNameNew().contains("COVID_NLD_GOC")) {
                    addActionError("Bạn chọn file upload không đúng với báo cáo !");
                    return ERROR;
                }
                if (!dao.insert_PL02_FILE("COVID_NLD_GOC", poscd, getFileNameNew(), convertStringToDate(sNgayBC), UserName, lstExcel, masothue)) {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }
            }
            Connection conn = new DaoConnect().getConnect();
            lstDulieuNt = dao.getDataAfterUpFile(conn, "COVID_NLD_GOC", sNgayBC, poscd, UserName, Grade, langiangan, masothue);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_NLD_GOC: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_NLD_GOC: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }
        return SUCCESS;
    }

    public String updateDsNguoiLD_QD23() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            Connection conn = new DaoConnect().getConnect();
            String ngay = hmParameter.get("ngay_bc").toString();
            lstDulieuNt = dao.getDataAfterUpFile(conn, "QD23_002", ngay, "", UserName, Grade, hmParameter.get("thangbc").toString(), masothue);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> updateDsNguoiLD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> updateDsNguoiLD: " + e.getMessage());
            return SUCCESS;
        }
        return SUCCESS;
    }

    public String loadParaUploadDsNguoild() {
        try {

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> UploadDSGiaiNgan: " + e.getMessage());
            return SUCCESS;
        }
        return SUCCESS;
    }

    //Upload danh sách giải ngân
    public String saveUploadDsNguoiLD_QD23() {
        try {
            System.err.println("Upload file");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            if (fileUploadFileName.isEmpty()) {
                addActionError("Bạn chưa chọn file để thực hiện upload !");
                return ERROR;
            }
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            String start_end = dao.getStartEndCel("COVID_NLD_QD23");
            String poscd = dao.getPosCd(UserName);
            int startrow = 0, endcell = 0;
            if (!start_end.equals("AAA")) {
                startrow = Integer.parseInt(start_end.split("-")[0]);
                endcell = Integer.parseInt(start_end.split("-")[1]);
            }
            if (new_file.isFile()) {
                setLstExcel(readFileExcel(new_file_path, startrow, endcell));

                setFileNameNew(new_file.getName());
                if (!getFileNameNew().contains("COVID_NLD_QD23")) {
                    addActionError("Bạn chọn file upload không đúng với báo cáo !");
                    return ERROR;
                }
                if (!dao.insert_DS_NGUOILD_QD23("QD23_002", poscd, getFileNameNew(), convertStringToDate(sNgayBC), UserName, lstExcel, masothue, hmParameter.get("thangbc").toString())) {
                    addActionError("Lỗi khi đọc dữ liệu từ file excel ");
                    return ERROR;
                }
                String sCheck = dao.checkUploadNLD(khoa_nhaptaycn, Grade, hmParameter.get("thangbc").toString(), masothue);
                if (!sCheck.equals("AAA")) {
                    addActionError("Lỗi! Trùng số sổ BHYT " + sCheck);
                    return ERROR;
                }
            }
            Connection conn = new DaoConnect().getConnect();
            lstDulieuNt = dao.getDataAfterUpFile(conn, "QD23_002", sNgayBC, poscd, UserName, Grade, hmParameter.get("thangbc").toString(), masothue);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_GIAINGAN: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replace("\\", "/").replace("'", "\""));
            return ERROR;
        }
        return SUCCESS;
    }

    public String QD23_001_ExpExcel() {
        try {
//            getInfo();
            if (!getParaSession()) {
                return ERROR;
            }
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstSubCommune = new ArrayList<>();
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
//            lstSubCommune = dao.getPosList(UserName, Grade);

            lstSubCommune.add("000100");
//            lstSubCommune.add("000402");
//            if (commune_cd.equals("000000")) {
//                addActionError("Bạn chưa chọn xã/phường");
//                return ERROR;
//            }
//
//            List<POSModel> lstCommuneFull = new ArrayList<>();
//            lstCommuneFull = daoXdkh.getCommuneListAll(pos_cd_username);
//
//            if (!subcommune_cd.equals("000000")) {
//                lstSubCommune.add(subcommune_cd);
//            } else {
//                lstSubCommune = daoXdkh.getAllSubCommune(pos_cd_username, commune_cd);
//            }

//            String communeName = "";
//            for (int i = 0; i < lstCommuneFull.size(); i++) {
//                if (lstCommuneFull.get(i).getId().equals(commune_cd)) {
//                    communeName = lstCommuneFull.get(i).getDesc();
//                    break;
//                }
//            }
            HashMap hmParameter = getParameter();
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            FileExportInfo fileInfo = excelExport.xuatExcelMau01BCTK_QD23(poscd, hmParameter.get("ngay_bc").toString(), Grade, savedDir, pos_cd_username);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01a " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01a " + ex.getMessage());
            return ERROR;
        }
    }

    public String QD23_001_ExpExcel_Temp() {
        try {
//            getInfo();
            if (!getParaSession()) {
                return ERROR;
            }
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstSubCommune = new ArrayList<>();
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
//            lstSubCommune = dao.getPosList(UserName, Grade);

            lstSubCommune.add(pos_cd_username);

            HashMap hmParameter = getParameter();
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            FileExportInfo fileInfo = excelExport.xuatExcelMau01BCTK_QD23_Temp(poscd, hmParameter.get("ngay_bc").toString(), Grade, savedDir, pos_cd_username);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01a " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01a " + ex.getMessage());
            return ERROR;
        }
    }

    public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }

    private String copy_file() throws Exception {
        String destPath, mainReportPath = "";
        try {

            File destFile;
            int index = 0;
            for (String filename : fileUploadFileName) {
                try {
                    destPath = getPathRoot() + Define.M_UPLOAD_DIR;
                    if (!new File(destPath).exists()) {
                        new File(destPath).mkdirs();
                    }
                    filename = String.valueOf(System.currentTimeMillis()) + "_" + filename;
                    destFile = new File(destPath, filename);
                    //FileUtils.copyFile(fileUpload.get(index), destFile);
                    copyFileUsingFileStreams(fileUpload.get(index), destFile);
                    if (index == 0) {
                        mainReportPath = destPath + filename;
                    }
                    index++;
                } catch (IOException e) {
                    System.err.println("error when copy large file: " //+fileUpload.get(index)
                            + "~" + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

        return mainReportPath;
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {

        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    public String getPathRoot() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        path = DefineFun.backlashReplace(path);
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path;
    }

    private List<ModelExcelFile> readFileExcel(String fileName, int startRow, int EndCell) throws IOException, InvalidFormatException {
        List<ModelExcelFile> lstExcelKhnv = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(new File(fileName));

//            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if (startRow >= row.getRowNum() + 1) {
                    continue;
                }
                ModelExcelFile value = new ModelExcelFile();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() + 1 > EndCell) {
                        continue;
                    }
                    int cellType = cell.getCellType();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen

                    switch (cellType) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            System.err.println(cell.getNumericCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            System.err.println(cell.getStringCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                            value.setFormula(value.getFormula() + " -> " + evaluator.evaluate(cell).getStringValue());
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            //Not again
                            break;
                    }
                }
//                System.out.println("");
                lstExcelKhnv.add(value);
            }

            workbook.close();

        } catch (IOException e) {
            throw new IOException(e);
        }
        return lstExcelKhnv;
    }

    private List<ModelExcelFile> readFileExcelQD23(String fileName, int startRow, int EndCell) throws IOException, InvalidFormatException {
        List<ModelExcelFile> lstExcelKhnv = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(new File(fileName));

//            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if (startRow >= row.getRowNum() + 1) {
                    continue;
                }
                ModelExcelFile value = new ModelExcelFile();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() + 1 > EndCell) {
                        continue;
                    }
                    int cellType = cell.getCellType();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen

                    switch (cellType) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            System.err.println(cell.getNumericCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            System.err.println(cell.getStringCellValue() + "\t");
                            value.setValue(cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                            value.setFormula(value.getFormula() + " -> " + evaluator.evaluate(cell).getStringValue());
                            value.setValue(cell.getColumnIndex(), cell.getNumericCellValue());
                            //Not again
                            break;
                    }
                }
//                System.out.println("");
                lstExcelKhnv.add(value);
            }

            workbook.close();

        } catch (IOException e) {
            throw new IOException(e);
        }
        return lstExcelKhnv;
    }

}
