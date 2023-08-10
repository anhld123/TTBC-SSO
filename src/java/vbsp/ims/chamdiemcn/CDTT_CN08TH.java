/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import vbsp.ims.chamdiemcn.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.iapi.reference.ClassName;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.ktgs.action.*;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author QUYENNV
 */
public class CDTT_CN08TH extends ActionChamdiemcnMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("CDTT_PGD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();
            //khoi tao cho treeview cac pos    
            setNhomnv(daoMain.getNhomNVByUser(UserName, Grade));
            if (tt_cdtt.equals("00000")) {
                List<QT_DULIEU_NT> listChuaDuyet = daoMain.viewAllStatus(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
                setLstDulieuNt(listChuaDuyet);
                if (conn != null) {
                    conn.close();
                }
                return "LANHDAO_DUYET_ERROR";
            } else if (tt_cdtt.equals(daoMain.getMabcByUser(UserName, Grade))) {
                String sThongbao = daoMain.getCheck_Nhaplieu("", Grade, UserName, hmParameter.get("ngay_bc").toString(), hmParameter.get("tt_cdtt").toString());
                if (!sThongbao.equals("AAA")) {
                    addActionError(sThongbao);
                    return ERROR;
                }
                poscd.add("999999");
                setTT_DUYET(daoMain.getStatusInput_0607(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), tt_cdtt, tt_cdtt));
                lstDulieuNt = daoMain.getDataCDTT_CN08TH(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                if (conn != null) {
                    conn.close();
                }
                if (nhomnv.equals("PGD_TD_PGD11") || nhomnv.equals("PGD_TD_PGD12") || nhomnv.equals("CN_PGD") || nhomnv.equals("GD_CN_27"))
                {
                    setLstXeploaiABC(daoMain.getLOV(UserName,"XEPLOAI_ABC",Grade,""));
                    setLstXeploai(daoMain.getLOV(UserName,"XEPLOAI",Grade,""));
                    setLstXeploaiHTNV(daoMain.getLOV(UserName,"XEPLOAI_HTNV",Grade,""));
                    return "success_extra";
                }
                else
                    return SUCCESS;
            } else {
                poscd.add("999999");
                setTT_DUYET(daoMain.getStatusInput_0607(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), tt_cdtt, tt_cdtt));
                lstDulieuNt = daoMain.getDataCDTT_CN08TH(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                if (conn != null) {
                    conn.close();
                }
                setNhomnv_duyet(daoMain.getNhomNVByUser_Duyet(UserName, Grade,hmParameter.get("tt_cdtt").toString()));
                System.out.println("Nhom nghiep vu = " + nhomnv_duyet);
                if (nhomnv_duyet.equals("PGD_TD_PGD11") || nhomnv_duyet.equals("PGD_TD_PGD12") || nhomnv.equals("GD_CN_27") || nhomnv.equals("CN_PGD") )
                {
                    setLstXeploaiABC(daoMain.getLOV(UserName,"XEPLOAI_ABC",Grade,""));
                    setLstXeploai(daoMain.getLOV(UserName,"XEPLOAI",Grade,""));
                    setLstXeploaiHTNV(daoMain.getLOV(UserName,"XEPLOAI_HTNV",Grade,""));
                    return "LANHDAO_DUYET_EXTRA";
                }
                else
                    return "LANHDAO_DUYET";
            }

//            if (!RULEUSER.equals("9") || (RULEUSER.equals("9") && poscd.size() == 0)) {
//                setTT_DUYET(daoMain.getStatusInput_0607(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : "", hmParameter.get("tt_cdtt").toString()));
//                lstDulieuNt = daoMain.getDataCDTT_CN08TH(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
//                if (conn != null) {
//                    conn.close();
//                }
//                return SUCCESS;
//            } else {
//                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getLANHDAODUYET_LOI_08AB(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
//                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
//                    lstDulieuNt = daoMain.getDataCDTT_CN08TH(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
//                    if (conn != null) {
//                        conn.close();
//                    }
//                    return "LANHDAO_DUYET";
//                } else {
//                    setLstDulieuNt(listChuaDuyet);
//                    return "LANHDAO_DUYET_ERROR";
//                }
//            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));

            return ERROR;
        }
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            DaoChamdiemcnMain daoMain = DaoChamdiemcnMain.newInstance();
            HashMap hmParameter = getParameter();

            if (tt_cdtt.equals(daoMain.getMabcByUser(UserName, Grade))) {
                poscd.removeAll(poscd);
            }
            if (!daoMain.saveCDTT_CMNV08AB(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade, hmParameter.get("tt_cdtt").toString(), poscd)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String savePopup() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemcnMain daoMain = DaoChamdiemcnMain.newInstance();
            HashMap hmParameter = getParameter();
            Date date_ngay_bc = new SimpleDateFormat("dd/MM/yyyy").parse(hmParameter.get("ngay_bc").toString());
            poscd.add("999999");
            if (!daoMain.saveCDTT_CMNV08AB(khoa_cdtt, UserName, "", new SimpleDateFormat("dd-MMM-yyyy").format(date_ngay_bc), lstDulieuNt, Grade, hmParameter.get("tt_cdtt").toString(), poscd)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ReCallCDTT() {
        try {
            System.err.println("ReCallCDTT");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();
            //khoi tao cho treeview cac pos    
            Date date_ngay_bc = new SimpleDateFormat("dd/MM/yyyy").parse(hmParameter.get("ngay_bc").toString());

//            String khoa, String mact, String ngaybc, String mapgd, String username, String Capbc, String macb
            poscd.add("999999");
            setTT_DUYET(daoMain.getStatusInput_0607(Grade, khoa_cdtt, UserName, new SimpleDateFormat("dd-MMM-yyyy").format(date_ngay_bc), tt_cdtt, tt_cdtt));
            lstDulieuNt = daoMain.getDataCDTT_CN08TH(conn, khoa_cdtt, new SimpleDateFormat("dd-MMM-yyyy").format(date_ngay_bc), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
            if (conn != null) {
                conn.close();
            }
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
    }

    public String ChitietChamdiem() {
        try {
//            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                return ERROR;
            }

            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_chitieudiemtru(khoa_cdtt, UserName, sNgayBC, macb);
            int iRule = daoMain.checkRuleUser_CN08AB(UserName, Grade, khoa_cdtt);

            if (iRule == 9 && !macb.equals(daoMain.getMabcByUser(UserName, Grade))) {
                return "success_duyet";
            } else {
                return SUCCESS;
            }
//            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            //addActionError("Có lỗi xảy ra: " + e.getMessage());
             setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
//        return SUCCESS;
    }

    public String ChitietChamdiemChitieu() {
        try {
//            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            if (pos_string == null || pos_string.isEmpty()) {
                addActionError("Không thể lấy ra được đơn vị để xem chi tiết");
                tableDetail = "<th> Không thể lấy ra được đơn vị để xem chi tiết </th>";
                return SUCCESS;

            }

            poscd = convertStringtoList(pos_string.replace(" ", "").split(","));

            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            String macb = hmParameter.get("macb").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                tableDetail = "<th> Ngày báo cáo không phải là ngày cuối tháng " + sNgayBC + "</th>";
                return SUCCESS;
            }
//            String khoa, String mact, String ngaybc, String mapgd, String username, String Capbc, String macb
            //Khoi tao cho treeview cac pos
            //lstDulieuNt = daoMain.get_DetailCT(conn, khoa_cdtt, sNgayBC, UserName, Grade, poscd, MACT);
            HashMap<String, String> mapValue = daoMain.getQueryTableDetail(khoa_cdtt, sNgayBC, "000100", MACT, UserName, Grade, macb);
            tableDetail = mapValue.get("DETAILDATA");
            thuyetminh = mapValue.get("THUYETMINH");
            Message = mapValue.get("TENCHITIEU");
//            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChitietChamdiem: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChitietChamdiem: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            //return ERROR;
            tableDetail = "<th> " + e.getMessage() + "</th>";
        }
        return SUCCESS;
    }

    public String Nhapdiemtru() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            String sNgayBC = hmParameter.get("ngay_bc").toString();
            String pattern = "dd-MMM-yyyy";
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Ngày báo cáo không phải là ngày cuối tháng " + sNgayBC);
                return SUCCESS;
            }

//            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();

            lstDulieuNt = daoMain.get_chitieudiemtru(khoa_cdtt, UserName, sNgayBC, macb);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> Nhapdiemtru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> Nhapdiemtru: " + e.getMessage());
             setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveDiemTru() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemcnMain daoMain = DaoChamdiemcnMain.newInstance();
            HashMap hmParameter = getParameter();

            String sNgayBC = hmParameter.get("ngay_bc").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!daoMain.saveCDTT_DIEMTRU(khoa_cdtt, UserName, "", sNgayBC, lstDulieuNt, Grade, macb, poscd)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String viewStatusCDCN() {
        try {
            System.err.println("CDTT_PGD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();
            //khoi tao cho treeview cac pos              

            List<QT_DULIEU_NT> listChuaDuyet = daoMain.viewAllStatus(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);

            setLstDulieuNt(listChuaDuyet);
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
             setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
    }

    public String unlockCDTT_CN08TH() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoChamdiemcnMain daoMain = DaoChamdiemcnMain.newInstance();
            HashMap hmParameter = getParameter();

            List<String> lpos = new ArrayList<String>();
            if (poscd.size() > 1) {
                addActionError("Chỉ được phép chọn 1 đơn vị để mở duyệt!");
                return ERROR;
            }

            if (!daoMain.UnlockCDCN(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "0", Grade, hmParameter.get("tt_cdtt").toString())) {
                addActionError("Bạn chưa mở duyệt được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
             setMessage("Có lỗi xảy ra: " + e.getMessage());
            addActionError(getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
