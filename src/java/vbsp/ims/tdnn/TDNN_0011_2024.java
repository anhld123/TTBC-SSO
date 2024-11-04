/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tdnn;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.gqvl_2023.Service_GQVL2023;
import vbsp.ims.huydongtk.clsHuyDongTK;
import vbsp.ims.leavelocal.LeaveHomeService;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.ListTransactionPoint;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author 1
 */
public class TDNN_0011_2024 extends ActionTdnnMain implements TdnnFunction {

    Service_GQVL2023 _server;
    private List<DuLieuNTRow> lstData;
    private List<DuLieuNTRow> lstData_tmp;
    private String txtSoku;
    private String txtGetData;
    private String txtType;
    private String gradeAuthor1;
    private InputStream pageResult;
    private String pos_cd;
    private String main_pos;
    private String PosFlag;
    private String check_form;
    private String disintctD5;
    private String disintctD6;
    private String disintctD8;
    private String disintctD50;
    private String check_Flag;
    private String alfet_canhbao;
    private List<ListMainPos> lstCN_API;
    private List<ListPosCode> lstPGD_API;
    private List<ListCommune> lstXa_API;
    private List<ListTransactionPoint> lstPoint_API;
    //<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getAlfet_canhbao() {
        return alfet_canhbao;
    }

    public void setAlfet_canhbao(String alfet_canhbao) {
        this.alfet_canhbao = alfet_canhbao;
    }

    public String getCheck_Flag() {
        return check_Flag;
    }

    public void setCheck_Flag(String check_Flag) {
        this.check_Flag = check_Flag;
    }

    public String getCheck_form() {
        return check_form;
    }

    public void setCheck_form(String check_form) {
        this.check_form = check_form;
    }

    public String getDisintctD50() {
        return disintctD50;
    }

    public void setDisintctD50(String disintctD50) {
        this.disintctD50 = disintctD50;
    }

    public String getDisintctD8() {
        return disintctD8;
    }

    public void setDisintctD8(String disintctD8) {
        this.disintctD8 = disintctD8;
    }

    public String getDisintctD5() {
        return disintctD5;
    }

    public void setDisintctD5(String disintctD5) {
        this.disintctD5 = disintctD5;
    }

    public String getDisintctD6() {
        return disintctD6;
    }

    public void setDisintctD6(String disintctD6) {
        this.disintctD6 = disintctD6;
    }

    public String getPosFlag() {
        return PosFlag;
    }

    public void setPosFlag(String PosFlag) {
        this.PosFlag = PosFlag;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public List<ListTransactionPoint> getLstPoint_API() {
        return lstPoint_API;
    }

    public void setLstPoint_API(List<ListTransactionPoint> lstPoint_API) {
        this.lstPoint_API = lstPoint_API;
    }

    @Override
    public DaoListPosFromUser getListKTNBDA() {
        return listKTNBDA;
    }

    @Override
    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    @Override
    public PosMainModel getPosMainModel() {
        return posMainModel;
    }

    @Override
    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }

    @Override
    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getMain_pos() {
        return main_pos;
    }

    public void setMain_pos(String main_pos) {
        this.main_pos = main_pos;
    }

    public String getGradeAuthor1() {
        return gradeAuthor1;
    }

    public void setGradeAuthor1(String gradeAuthor1) {
        this.gradeAuthor1 = gradeAuthor1;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public String getTxtSoku() {
        return txtSoku;
    }

    public void setTxtSoku(String txtSoku) {
        this.txtSoku = txtSoku;
    }
//</editor-fold>

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd = posMainModel.getPosCd();
            setMain_pos(posMainModel.getMainPosCd());
//            System.out.println("load : name " + UserName + " caP " + Grade + " pgd " + pos_cd + " cn " + main_pos);
            String PosFlag = "";
            if (Grade.equals("3")) {
                PosFlag = "H";
            } else if (Grade.equals("2")) {
                PosFlag = "M";
            } else {
                PosFlag = "S";
            }
            if (hmParameter.size() < 9) {
                addActionError("Bạn chưa chọn đủ thông tin để tải dữ liệu!");
                return ERROR;
            }
            String s = hmParameter.get("ngay_bc").toString();
            String mapgd = hmParameter.get("lstPGD").toString();
            String maxa = hmParameter.get("lstXa").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            if (hmParameter.get("lstPGD").toString().equals("000000")) {
                addActionError("Bạn chưa chọn chi nhánh!");
                return ERROR;
            }
            if (maxa.equals("000000")) {
                addActionError("Bạn chưa chọn điểm giao dịch xã!");
                return ERROR;
            }
            if (mapgd.equals("000000")) {
                addActionError("Bạn chưa chọn phòng giao dịch!");
                return ERROR;
            }
            String check = hmParameter.getOrDefault("check_2", "off").equals("on") ? "1"
                    : hmParameter.getOrDefault("check_1", "off").equals("on") ? "2" : "0";
            String CO_TONGHOP = "";
            if (check == "2" && PosFlag == "H") {
                CO_TONGHOP = "M";
            } else if (check == "2" && PosFlag == "M") {
                CO_TONGHOP = "S";
            } else if (check == "1") {
                CO_TONGHOP = PosFlag;
            }
            setCheck_Flag(CO_TONGHOP);
            setCheck_form(check);
            _server = new Service_GQVL2023();
            if (check == "1") {
                SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                Date inputDate = sdfInput.parse(s);

                // Lấy ngày đầu tiên của tháng của ngày s truyền vào
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(inputDate);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date startDate = calendar.getTime();

                // Lấy ngày cuối cùng của tháng của ngày s truyền vào
                calendar.add(Calendar.MONTH, 1);
                calendar.add(Calendar.DATE, -1);
                Date endDate = calendar.getTime();

                // Duyệt qua từng ngày trong tháng và gọi hàm
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

                calendar.setTime(startDate);
                while (calendar.getTime().compareTo(endDate) <= 0) {
                    String ngay = sdf1.format(calendar.getTime());
                    String checkdate1 = sdf.format(calendar.getTime());
                    String checkdate2 = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy", Locale.US).parse(s));

                    this.lstData_tmp = _server.getTTND_2024("KTGS_01GDX", mapgd, CO_TONGHOP, maxa, ngay, "", "0");

                    // Kiểm tra điều kiện dừng khi có dữ liệu và ngày kiểm tra đúng
                    if (lstData_tmp.size() > 0 && !checkdate1.equals(checkdate2)) {
                        String ngay1 = sdf2.format(calendar.getTime());
                        setAlfet_canhbao("Điểm giao dịch đã nhập dữ liệu vào ngày " + ngay1);
                    }
                    calendar.add(Calendar.DATE, 1);
                }
                lstCanBo = new clsHuyDongTK().getCanBo(CO_TONGHOP, UserName);
                this.lstData = _server.getTTND_2024("KTGS_01GDX", mapgd, CO_TONGHOP, maxa, s, "", "0");
                if (lstData == null || lstData.isEmpty()) {
                    this.lstData = _server.getTTND_2024("KTGS_01GDX", mapgd, CO_TONGHOP, maxa, s, "", "1");
                    if (lstData == null || lstData.isEmpty()) {
                        addActionError("Không có kết nối đến API từ TW, vui lòng liên hệ tin học để hỗ trợ!");
                        return ERROR;
                    }
                }
            } else if (check == "2") {
                lstCanBo = new clsHuyDongTK().getCanBo("A", UserName);
                this.lstData = _server.getTTND_2024("KTGS_01GDX", mapgd, CO_TONGHOP, maxa, s, "", "0");
                if (lstData == null || lstData.isEmpty()) {
                    addActionError("Điểm giao dịch chưa có dữ liệu");
                    return ERROR;
                }
            }
            int iStt = 1;
            for (DuLieuNTRow item : lstData) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(iStt);
                    iStt++;
                    row.setTT_HIENTHI(item.getOrderDescription());
                    row.setMA(item.getCode());
                    row.setTEN(item.getName());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
                    row.setCO_TONGHOP(item.getPosFlag());
                    row.setMACN(item.getBranchCode());
                    row.setNGUOI_NHAP(item.getMakerId());
                    Date makerDate = DateUtil.toDate(item.getMakerDate());
                    row.setNGAY_NHAP(makerDate);
                    row.setNGUOI_DUYET(item.getAuthoriseId());
                    Date authoriseDate = DateUtil.toDate(item.getAuthoriseDate());
                    row.setNGAY_DUYET(authoriseDate);
                    row.setD1(item.getD1());
                    row.setD2(item.getD2());
                    row.setD3(item.getD3());
                    row.setD4(item.getD4() != null && !item.getD4().isEmpty() ? item.getD4() : "0");
                    row.setD5(item.getD5());
                    row.setD6(item.getD6());
                    row.setD7(item.getD7());
                    row.setD8(item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD50(item.getD50());
                    row.setNHAPTAY(item.getManualFlag());
                    row.setFONTFORMAT(item.getFontFormat());
                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);

                } catch (Exception e) {
                }
                setDisintctD5(item.getD5());
                setDisintctD6(item.getD6());
                setDisintctD8(item.getD8());
                setDisintctD50(item.getD50());
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            HashMap hmParameter = getParameter();
            String PosFlag = "";
            if (Grade.equals("3")) {
                PosFlag = "H";
            } else if (Grade.equals("2")) {
                PosFlag = "M";
            } else {
                PosFlag = "S";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd = posMainModel.getPosCd();
            main_pos = posMainModel.getMainPosCd();
            String maxa = hmParameter.get("lstXa").toString();
            String mapgd = hmParameter.get("lstPGD").toString();
            String macn = hmParameter.get("lstCN").toString();
            String macb = hmParameter.get("cboCanBo").toString();
            String D6_tmp = hmParameter.get("namedistinctD6").toString();
            String D8_tmp = hmParameter.get("namedistinctD8").toString();
            String D50_tmp = hmParameter.get("nameD50").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String tranPoint = "TXN0" + maxa;
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRow tempadd = new DuLieuNTRow();
                int iStt = 1;
                iStt++;
                tempadd.setKey("KTGS_01GDX");
                tempadd.setOrderValue("");
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setAuthoriseId(UserName);
                tempadd.setReportDate(hmParameter.get("ngay_bc").toString());
                tempadd.setName(tmp.getTEN());
                tempadd.setReportYear(year);
                tempadd.setPosCode(mapgd);
                tempadd.setPosFlag(check_Flag);
                tempadd.setBranchCode(macn != "000000" ? macn : main_pos);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(maxa);
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(macb);
                tempadd.setD6(D6_tmp);
                tempadd.setD7(tranPoint);
                tempadd.setD8(D8_tmp);
                tempadd.setD9(tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD50("1");
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setFontFormat(tmp.getFONTFORMAT());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);
                lstLocalDataUpdate.add(tmp);
//                System.out.println("macn: " + macn + " c1 " + main_pos + " 2 ");
            }
            _server = new Service_GQVL2023();
            int status = _server.saveTDNN_2024("KTGS_01GDX", mapgd, PosFlag, maxa, hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
            if (status != 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            String code = String.valueOf(1);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String send() {
//        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            HashMap hmParameter = getParameter();
            String PosFlag = "";
            if (Grade.equals("3")) {
                PosFlag = "H";
            } else if (Grade.equals("2")) {
                PosFlag = "M";
            } else {
                PosFlag = "S";
            }
            String maxa = hmParameter.get("lstXa").toString();
            String mapgd = hmParameter.get("lstPGD").toString();
            String macn = hmParameter.get("lstCN").toString();
            String macb = hmParameter.get("cboCanBo").toString();
            String D6_tmp = hmParameter.get("namedistinctD6").toString();
            String D8_tmp = hmParameter.get("namedistinctD8").toString();
            String ssngaybc = hmParameter.get("ngay_bc").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            if (macb.equals("000000")) {
                addActionError("Bạn chưa chọn cán bộ kiểm tra!");
            }
            String tranPoint = "TXN0" + maxa;
//            System.out.println(macb + " " + tranPoint);
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd = posMainModel.getPosCd();
            main_pos = posMainModel.getMainPosCd();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRow tempadd = new DuLieuNTRow();
                tempadd.setKey("KTGS_01GDX");
                tempadd.setOrderValue("");
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setAuthoriseId(UserName);
                tempadd.setReportDate(ssngaybc);
                tempadd.setName(tmp.getTEN());
                tempadd.setReportYear(year);
                tempadd.setPosCode(mapgd);
                tempadd.setPosFlag(check_Flag);
                tempadd.setBranchCode(macn != "000000" ? macn : main_pos);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(maxa);
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(macb);
                tempadd.setD6(D6_tmp);
                tempadd.setD7(tranPoint);
                tempadd.setD8(D8_tmp);
                tempadd.setD9(tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD50("2");
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setFontFormat(tmp.getFONTFORMAT());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);

                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
                temlocal.setKHOA("KTGS_01GDX");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA());
                temlocal.setNGUOI_DUYET(UserName);
                temlocal.setNGUOI_NHAP(UserName);
                Date reportDate = DateUtil.toDate(ssngaybc);
                temlocal.setNGAYBC(reportDate);
                temlocal.setNAMBC(tmp.getNAMBC());
                temlocal.setMAPGD(tmp.getMAPGD());
                temlocal.setCO_TONGHOP(check_Flag);
                temlocal.setMACN(macn != "000000" ? macn : main_pos);
                temlocal.setD1(tmp.getD1());
                temlocal.setD2(maxa);
                temlocal.setD3(tmp.getD3());
                temlocal.setD4(tmp.getD4());
                temlocal.setD5(macb);
                temlocal.setD6(D6_tmp);
                temlocal.setD7(tranPoint);
                temlocal.setD8(D8_tmp);
                temlocal.setD9(tmp.getD9());
                temlocal.setD10(tmp.getD10());
                temlocal.setD11("2");
                temlocal.setD12(toString().valueOf(tmp.getKIEUIN()));
                lstLocalDataUpdate.add(temlocal);

            }
            _server = new Service_GQVL2023();
            int status = _server.saveTDNN_2024("KTGS_01GDX", mapgd, PosFlag, maxa, hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
//            System.out.println("dateStr: "+ dateStr+ " mapgd: "+ mapgd+" maxa= " +maxa+ " PosFlag= " + PosFlag);
            if (status == 200) {
                DaoTdnnMain daoMain = new DaoTdnnMain();
                if (!daoMain.save_GDX_2024("KTGS_01GDX", ssngaybc, UserName, mapgd, maxa, PosFlag, lstLocalDataUpdate, "KTGS_01GDX")) {
                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

                }
            }
        } catch (Exception e) {
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            String code = String.valueOf(1);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock() {
//        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            HashMap hmParameter = getParameter();
            String PosFlag = "";
            if (Grade.equals("3")) {
                PosFlag = "M";
            } else if (Grade.equals("2")) {
                PosFlag = "S";
            } else {
                PosFlag = "S";
            }
            String maxa = hmParameter.get("lstXa").toString();
            String mapgd = hmParameter.get("lstPGD").toString();
            String macn = hmParameter.get("lstCN").toString();
            String macb = hmParameter.get("cboCanBo").toString();
            String D6_tmp = hmParameter.get("namedistinctD6").toString();
            String D8_tmp = hmParameter.get("namedistinctD8").toString();
            String D50_tmp = hmParameter.get("nameD50").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String tranPoint = "TXN0" + maxa;
//            System.out.println(macb + " " + tranPoint);
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd = posMainModel.getPosCd();
            main_pos = posMainModel.getMainPosCd();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRow tempadd = new DuLieuNTRow();
                int iStt = 1;
                iStt++;
                tempadd.setKey("KTGS_01GDX");
                tempadd.setOrderValue("");
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setAuthoriseId(UserName);
                tempadd.setReportDate(hmParameter.get("ngay_bc").toString());
                tempadd.setName(tmp.getTEN());
                tempadd.setReportYear(year);
                tempadd.setPosCode(mapgd);
                tempadd.setPosFlag(check_Flag);
                tempadd.setBranchCode(macn != "000000" ? macn : main_pos);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(maxa);
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(macb);
                tempadd.setD6(D6_tmp);
                tempadd.setD7(tranPoint);
                tempadd.setD8(D8_tmp);
                tempadd.setD9(tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD50("1");
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setFontFormat(tmp.getFONTFORMAT());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);
                lstLocalDataUpdate.add(tmp);

            }
            _server = new Service_GQVL2023();
            int status = _server.deleteTDNN_2024("KTGS_01GDX", mapgd, PosFlag, maxa, hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");

            if (status == 200) {
                DaoTdnnMain daoMain = new DaoTdnnMain();
                if (!daoMain.save_GDX_2024("KTGS_01GDX", hmParameter.get("ngay_bc").toString(), UserName, mapgd, maxa, PosFlag, lstLocalDataUpdate, "DELETE_GDX_1")) {
                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

                }
            }
        } catch (Exception e) {
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String popup_seach() throws Exception {
        try {
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd = posMainModel.getPosCd();
            main_pos = posMainModel.getMainPosCd();
            _server_tmp = new LeaveHomeService();
            lstCN_API = _server_tmp.getListCn("");
            lstPGD_API = _server_tmp.getListPgd("", "");
            lstXa_API = _server_tmp.getListXa("", "", "", "");
            lstPoint_API = _server_tmp.getListPoint("", "", "TXN");
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return "success";
    }
}
