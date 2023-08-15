/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class HTLS_2023 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _leaveHomeService;
    private List<DuLieuNTRow> lstData;

    @Override
    public String load() {
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
//            main_pos_username = posMainModel.getMainPosCd();

            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            _leaveHomeService = new Service_GQVL2023();
            if (Grade.equals("3")) {
            } else if (Grade.equals("2")) {
                this.lstData = _leaveHomeService.getCustomers(pos_cd_username, "M", hmParameter.get("ngay_bc").toString(), "1", "KS_HTLS_CN");
            }
            if (lstData == null || lstData.size() ==0)
            {
                addActionError("Số liệu chưa được tạo tại Tw. Vui lòng liên hệ với TT CNTT");;
                return ERROR;
            }
            int iStt = 1;
            for (DuLieuNTRow item : lstData) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                row.setKHOA(item.getKey());
                row.setTHUTU(iStt);
                iStt++;
                row.setTT_HIENTHI(item.getOrderDescription());
                row.setMA(item.getCode());
                row.setTEN(item.getName());

                Date reportDate = DateUtil.toDate(item.getReportDate());
                row.setNGAYBC(reportDate);
                row.setNAMBC(item.getReportYear());
                row.setMAPGD(item.getCode());
                row.setCO_TONGHOP(item.getPosFlag());
                row.setMACN(item.getBranchCode());
                row.setNGUOI_NHAP(item.getMakerId());
//                row.setNGAY_NHAP(item.getMakerDate());
                Date makerDate = DateUtil.toDate(item.getMakerDate());
                row.setNGAY_NHAP(makerDate);
                row.setNGUOI_DUYET(item.getAuthoriseId());
//                row.setNGAY_DUYET(item.getAuthoriseDate());
                Date authoriseDate = DateUtil.toDate(item.getAuthoriseDate());
                row.setNGAY_DUYET(authoriseDate);
                row.setD1(item.getD1());
                row.setD2(item.getD2());
                row.setD3(item.getD3() == null ? "0" : item.getD3());
                row.setD4(item.getD4() == null ? "0" : item.getD4());
                row.setD5(item.getD5() == null ? "0" : item.getD5());
                row.setD6(item.getD6() == null ? "0" : item.getD6());
                row.setD7(item.getD7() == null ? "0" : item.getD7());
                row.setD8(item.getD8() == null ? "0" : item.getD8());
                row.setD9(item.getD9() == null ? "0" : item.getD9());
                row.setD10(item.getD10() == null ? "0" : item.getD10());
                row.setD11(item.getD11() == null ? "0" : item.getD11());
                row.setD12(item.getD12() == null ? "0" : item.getD12());
                row.setD13(item.getD13() == null ? "0" : item.getD13());
                row.setD14(item.getD14());
                row.setD15(item.getD15());
                row.setD16(item.getD16());
                row.setD17(item.getD17());
                row.setD18(item.getD18());
                row.setD19(item.getD19());
                row.setD20(item.getD20());
                row.setD21(item.getD21());
                row.setD22(item.getD22());
                row.setD23(item.getD23());
                row.setD24(item.getD24());
                row.setD25(item.getD25());
                row.setD26(item.getD26());
                row.setD27(item.getD27());
                row.setD28(item.getD28());
                row.setD29(item.getD29());
                row.setD50(item.getD50());
                row.setNHAPTAY(item.getManualFlag());
                row.setFONTFORMAT(item.getFontFormat());
                row.setKIEUIN(item.getStyle());
                lstDulieuNt.add(row);
            }
//            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            addActionError("Đã có lỗi. Vui lòng liên hệ với quản trị");;
                return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();

            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {

                DuLieuNTRow tempadd = new DuLieuNTRow();

                tempadd.setKey("KS_HTLS_CN");
//                tempadd.setOrderValue("");
//                tempadd.setOrderDescription("");
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setAuthoriseId(UserName);
                tempadd.setReportDate(totalDataView);
//                tempadd.setName(tmp.getTEN());
                tempadd.setReportYear(2023);
                tempadd.setPosCode(tmp.getMAPGD());
                tempadd.setPosFlag("M");
                tempadd.setBranchCode(tmp.getMACN());
//                tempadd.setD2(tmp.getMAPGD());
//                tempadd.setD3(tmp.getD3());
//                tempadd.setD4(tmp.getD4());
//                tempadd.setD5(tmp.getD5());
//                tempadd.setD6(tmp.getD6());
//                tempadd.setD7(tmp.getD7());
                tempadd.setD8(tmp.getD8());
//                tempadd.setD9(tmp.getD9());
//                tempadd.setD10(tmp.getD10());
                tempadd.setD11(tmp.getD11());
//                tempadd.setD12(tmp.getD12());
//                tempadd.setD13(tmp.getD13());
//                tempadd.setD14(tmp.getD14());
//                tempadd.setD15(tmp.getD15());
//                tempadd.setD16(tmp.getD16());
//                tempadd.setD17(tmp.getD17());
//                tempadd.setD18(tmp.getD18());
//                tempadd.setD19(tmp.getD19());
//                tempadd.setD20(tmp.getD20());
//                tempadd.setD21(tmp.getD21());
//                tempadd.setD22(tmp.getD22());
//                tempadd.setD23(tmp.getD23());
//                tempadd.setD24(tmp.getD24());
//                tempadd.setD25(tmp.getD25());
//                tempadd.setD26(tmp.getD26());
//                tempadd.setD27(tmp.getD27());
//                tempadd.setD28(tmp.getD28());
//                tempadd.setD29(tmp.getD29());
                tempadd.setD50("2");
                lstUpdateDate.add(tempadd);
                lstLocalDataUpdate.add(tmp);

            }
            _leaveHomeService = new Service_GQVL2023();
            int status = _leaveHomeService.saveHTLS2023(pos_cd_username, "M", hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
            if (status == 200) {
                if (!daoMain.saveGQVL2023("KS_HTLS_CN", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, poscd)) {
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }

        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
//            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
