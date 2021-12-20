/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author QUYENNV
 */
public class CDTT_CN extends ActionChamdiemttMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("CDTT_PGD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();

            int input = daoMain.isCheckPGDInput(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 2 && Grade.equals("3")) {
                addActionError("Ban CMNV chưa duyệt hết số liệu");
                setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
                return ERROR;
            }

            //khoi tao cho treeview cac pos  
            setTt_cdtt(hmParameter.get("tt_cdtt").toString());
            setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
            lstDulieuNt = daoMain.getDataCDTT_CN(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
//            lstDulieuNt_TH = daoMain.getDataCDTT_Status(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadDulieuCN() {
        try {
            System.err.println("CDTT_CN");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            if (poscd.size() > 11)
            {
                addActionError("Bạn chỉ được chọn tối đa 10 đơn vị để duyệt.");
                return ERROR;
            }
            int input = daoMain.isCheckPGDInput(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 2 && Grade.equals("3")) {
                addActionError("Ban CMNV chưa duyệt hết số liệu");
                setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
                return ERROR;
            }
            //khoi tao cho treeview cac pos  
//            setTt_cdtt(hmParameter.get("tt_cdtt").toString());
            setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
            poscd.remove("999999");
            lstDulieuNt = daoMain.getDataCDTT_CN_TUNGNV(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
            if (Grade.equals("2") && !RULEUSER.equals("9")) {
                setTt_cdtt(hmParameter.get("tt_cdtt").toString());
                return "CHINHANH_NHAP";
            } else if (Grade.equals("2") && RULEUSER.equals("9")) {
                setTt_cdtt(hmParameter.get("tt_cdtt").toString());
                String Chuaduyet = daoMain.isChitieuchuaduyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade, UserName);
                if (Chuaduyet == null || Chuaduyet.isEmpty()) {
                    return "HOIDONG_CHINHANH";
                } else {
//                    Chuaduyet = "<strong>Còn phòng/Ban chưa nhập liệu <p>. Mới có các phòng/ban sau nhập liệu:<strong><p>" + Chuaduyet;
                    addActionError(Chuaduyet);
                    return ERROR;
                }
            } //            Ban chuyên môn nghiệp vụ tại hội sở chính duyệt
            else if (Grade.equals("3") && !RULEUSER.equals("9")) {

                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getHOIDONGCNChuaDuyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
                    return "CMNV_TW";
                } else {

                    setLstDulieuNt(listChuaDuyet);
                    return "CMNV_TW_CHUADUYET";
                }
            } else if (Grade.equals("3") && RULEUSER.equals("9")) {
                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getCMNVTWChuaDuyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
                    return "HOIDONG_TW";
                } else {

                    setLstDulieuNt(listChuaDuyet);
                    return "CMNV_TW_CHUADUYET";
                }
            }
//            lstDulieuNt_TH = daoMain.getDataCDTT_Status(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();

            if (!daoMain.saveCDTT_CN(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
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

    public String saveCNNhap() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();

            if (!daoMain.saveCDTT_CNNHAP(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
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

    public String saveHoidongCN() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();

            String Chuaduyet = daoMain.isChitieuchuaduyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade, UserName);

            if (Chuaduyet == null || Chuaduyet.isEmpty()) {

            } else {
//                    Chuaduyet = "<strong>Còn phòng/Ban chưa nhập liệu <p>. Mới có các phòng/ban sau nhập liệu:<strong><p>" + Chuaduyet;
                addActionError(Chuaduyet);
                return ERROR;
            }

            List<String> lstMaPGD = new ArrayList<>();
            lstMaPGD.add("999999");
//            String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc;
            if (!daoMain.saveHOIDONGCN(khoa_cdtt, UserName, "999999", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
                addActionError("Bạn chưa phê duyệt được số liệu, Xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveHoidongCN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveHoidongCN: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String saveCMNVTW() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            List<String> lstMaPGD = new ArrayList<>();
            lstMaPGD.add("999999");
//            lockCDTT_pgd(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc);
            if (!daoMain.saveCMNVTW(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
                addActionError("Bạn chưa lưu được dữ liệu, Xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveCMNVTW: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveCMNVTW: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String saveHOIDONGTW() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            List<String> lstMaPGD = new ArrayList<>();
            lstMaPGD.add("999999");
//            lockCDTT_pgd(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc);
            if (!daoMain.saveHOIDONGTW(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
                addActionError("Bạn chưa lưu được dữ liệu, Xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveHOIDONGTW: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveHOIDONGTW: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChitietChamdiem() {
        try {
//            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            if (pos_string == null || pos_string.isEmpty()) {
                addActionError("Không thể lấy ra được đơn vị để xem chi tiết");
                tableDetail = "<th> Không thể lấy ra được đơn vị để xem chi tiết </th>";
                excelDetail = "<th> Không thể lấy ra được đơn vị để xem chi tiết </th>";
                return SUCCESS;

            }

            poscd = convertStringtoList(pos_string.replace(" ", "").split(","));

            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                tableDetail = "<th> Ngày báo cáo không phải là ngày cuối tháng " + sNgayBC + "</th>";
                excelDetail = "<th> Ngày báo cáo không phải là ngày cuối tháng " + sNgayBC + "</th>";
                return SUCCESS;
            }

            int iRule = daoMain.checkRuleUser(UserName, Grade);
            setRULEUSER(String.valueOf(iRule));
            
            //Khoi tao cho treeview cac pos
            //lstDulieuNt = daoMain.get_DetailCT(conn, khoa_cdtt, sNgayBC, UserName, Grade, poscd, MACT);
            HashMap<String, String> mapValue = daoMain.getQueryTableDetail(khoa_cdtt, MACT, sNgayBC, "000100", UserName, Grade, poscd);
            tableDetail = mapValue.get("DETAILDATA");
            thuyetminh = mapValue.get("THUYETMINH");
            excelDetail = mapValue.get("EXCELDATA");
            Message = mapValue.get("TENCHITIEU");
//            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChitietChamdiem: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChitietChamdiem: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            //return ERROR;
            tableDetail = "<th> " + e.getMessage() + "</th>";
            excelDetail = "<th> " + e.getMessage() + "</th>";
        }
        return SUCCESS;
    }

    public String saveChitiet01() {
        System.err.println("Save - GSCMR_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if (value != null) {
                    if (!value.getMA().equals("false")) {
                        lstDat.add(value.getMA());
                    }
                }
            }

            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveChitiet01(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, "", lstDat)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChotCDTT() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            int input = daoMain.isCheckPGDInput(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 0) {
                addActionError("Bạn chưa nhập số liệu cho đơn vị nên không thể chốt số liệu");
                return ERROR;
            }
            List<String> lpos = new ArrayList<String>();

            if (!daoMain.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "1", Grade)) {
                addActionError("Bạn chưa chốt được báo cáo xin liên hệ với quản trị để khắc phục");
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
    
    public String SaveDeNghiLoaiTru() {        
        List<DeNghiLoaiTru> saveData = lstDeNghiLoaiTru;
        String key = khoa_cdtt;
        String ngayBC = getNgay_bc();
        int capBC =  Integer.parseInt(Grade);
        String username = UserName;
        DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
        try {
            Boolean valid = true; 
            int denghiTotal = 0;
            switch(capBC) {
                case 2:                    
                    for(int i = 0; i < saveData.size(); i++) {
                        int denghi = saveData.get(i).getCN_DeNghi_LoaiTru()!= null && saveData.get(i).getCN_DeNghi_LoaiTru()==true?1:0;
                        String lydo = saveData.get(i).getCN_LyDo()==null?"":saveData.get(i).getCN_LyDo().trim();
                        if (denghi == 1 && lydo.isEmpty()) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid){        
//                        if (RULEUSER.equals("9")) {
//                            setMessage("Bạn không được quyền thực hiện chức năng này!");
//                        } else {
                            daoMain.save_LOAI_TRU_CDTT_CN(key, username, ngayBC, capBC, saveData);
                            setMessage("Cập nhật dữ liệu thành công!");
//                        }
                    } else {
                        setMessage("Bạn phải nhập đầy đủ lý do trong trường hợp đề nghị loại trừ!");
                    }
                    break;
                case 3:                    
                    for(int i = 0; i < saveData.size(); i++) {
                        int denghi = saveData.get(i).getCN_DeNghi_LoaiTru()!= null && saveData.get(i).getCN_DeNghi_LoaiTru()==true?1:0;
                        int pheduyet = saveData.get(i).getTW_Duyet_DeNghi()!= null && saveData.get(i).getTW_Duyet_DeNghi()==true?1:0;
                        String lydo = saveData.get(i).getTW_LyDo()==null?"":saveData.get(i).getTW_LyDo().trim();
                        if ((denghi == 1 && pheduyet == 0 && lydo.isEmpty())
                                || (denghi == 1 && pheduyet == 1 && !lydo.isEmpty())) {
                            valid = false;
                            break;
                        }
                        if (denghi==1)
                            denghiTotal++;
                    }
                    if (valid){
                        if (denghiTotal == 0) {
                            setMessage("Trường hợp chi nhánh không đề nghị loại trừ thì bạn không cần lưu dữ liệu!");
                        } else {
                            daoMain.save_LOAI_TRU_CDTT_CN(key, username, ngayBC, capBC, saveData);
                            setMessage("Cập nhật dữ liệu thành công!");
                        }
                    } else {
                        setMessage("Bạn phải nhập đầy đủ lý do trong trường hợp từ chối, trường hợp phê duyệt không cần nhập lý do!");
                    }
                    break;
            }
        }catch(Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save SaveDeNghiLoaiTru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save SaveDeNghiLoaiTru: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }        
        return SUCCESS;
    }

}
