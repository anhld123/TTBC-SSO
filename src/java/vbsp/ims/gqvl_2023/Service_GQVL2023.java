/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import vbsp.ims.restapi.*;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import java.util.List;
import vbsp.ims.log.CoreLogger;
import java.util.ArrayList;
import java.util.Locale;
import vbsp.ims.dao.*;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Service_GQVL2023 {

    DuLieuNTService _service = new DuLieuNTService();

    public List<DuLieuNTRow> getCustomers(String posCode, String posFlag, String fromDate, String sourceFlag) {
        if (sourceFlag.equals("0")) {

        } else {
            try {
                final String _fromDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate));
                {
                    return _service.getData("GQVL_2023", posCode, posFlag, _fromDate);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public int saveCustomers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
        try {

            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date date = new Date();
            String dateFormat = formatter.format(date);
            if (sourceFlag.equals("0")) {

            } else {
                List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();

                    _normalizeItem.setKey("GQVL_2023");
                    _normalizeItem.setOrderValue(i + 1);
                    _normalizeItem.setOrderDescription(String.format("%d", i + 1));
                    _normalizeItem.setName(data.get(i).getName());
                    _normalizeItem.setAuthoriseId(data.get(i).getAuthoriseId());
                    _normalizeItem.setMakerId(data.get(i).getMakerId());
                    _normalizeItem.setReportYear(2023);
                    _normalizeItem.setReportDate(_reportDate1);
                    _normalizeItem.setAuthoriseDate(dateFormat);
                    _normalizeItem.setMakerDate(dateFormat);
                    _normalizeItem.setCode(data.get(i).getCode());
                    _normalizeItem.setPosCode(data.get(i).getPosCode());
                    _normalizeItem.setPosFlag("S");
                    _normalizeItem.setBranchCode(data.get(i).getBranchCode());
                    _normalizeItem.setD1(data.get(i).getD1());
                    _normalizeItem.setD2(data.get(i).getD2());
                    _normalizeItem.setD3(data.get(i).getD3());
                    _normalizeItem.setD4(data.get(i).getD4());
                    _normalizeItem.setD5(data.get(i).getD5());
                    _normalizeItem.setD6(data.get(i).getD6());
                    _normalizeItem.setD7(data.get(i).getD7());
                    _normalizeItem.setD8(data.get(i).getD8());
                    _normalizeItem.setD9(data.get(i).getD9());
                    _normalizeItem.setD10(data.get(i).getD10());
                    _normalizeItem.setD11(data.get(i).getD11());
                    _normalizeItem.setD12(data.get(i).getD12());
                    _normalizeItem.setD13(data.get(i).getD13());
                    _normalizeItem.setD14(data.get(i).getD14());
                    _normalizeItem.setD15(data.get(i).getD15());
                    _normalizeItem.setD16(data.get(i).getD16());
                    _normalizeItem.setD17(data.get(i).getD17());
                    _normalizeItem.setD18(data.get(i).getD18());
                    _normalizeItem.setD19(data.get(i).getD19());
                    _normalizeItem.setD20(data.get(i).getD20());
                    _normalizeItem.setD21(data.get(i).getD21());
                    _normalizeItem.setD22(data.get(i).getD22());
                    _normalizeItem.setD23(data.get(i).getD23());
                    _normalizeItem.setD24(data.get(i).getD24());
                    _normalizeItem.setD25(data.get(i).getD25());
                    _normalizeItem.setD26(data.get(i).getD26());
                    _normalizeItem.setD27(data.get(i).getD27());
                    _normalizeItem.setD28(data.get(i).getD28());
                    _normalizeItem.setD29(data.get(i).getD29());
                    _normalizeItem.setD30(data.get(i).getD30());

                    _lstNormalizeData.add(_normalizeItem);
                }
                return _service.getGQVL2023("GQVL_2023", posCode, posFlag, _reportDate, makerId, authoriseId, _lstNormalizeData);

            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public List<DuLieuNTRow> getMembers(String posCode, String posFlag, String customerCode, String sourceFlag) {

        if (sourceFlag.equals("0")) {

        } else {
            try {
                return _service.getClhMembers("BO_DI_KHOI_DP", posCode, "S", customerCode);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public int saveMembers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
        try {
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();

                _normalizeItem.setKey("BO_DI_KHOI_DP");
                _normalizeItem.setOrderValue(i + 1);
                _normalizeItem.setOrderDescription(String.format("%d", i + 1));
                _normalizeItem.setCode(data.get(i).getD11() + "_" + String.format("%02d", i + 1));
                _normalizeItem.setName("");
                //_normalizeItem.setReportDate("");
                _normalizeItem.setReportYear(2050);
                _normalizeItem.setPosCode(data.get(i).getD5());
                _normalizeItem.setPosFlag("S");
                _normalizeItem.setBranchCode(data.get(i).getD3());
                //_normalizeItem.setD11("");
                _normalizeItem.setD20("ThanhVien");
                _normalizeItem.setD1(data.get(i).getD1());
                _normalizeItem.setD2(data.get(i).getD2());
                _normalizeItem.setD3(data.get(i).getD3());
                _normalizeItem.setD4(data.get(i).getD4());
                _normalizeItem.setD5(data.get(i).getD5());
                _normalizeItem.setD6(data.get(i).getD6());
                _normalizeItem.setD7(data.get(i).getD7());
                _normalizeItem.setD8(data.get(i).getD8());
                _normalizeItem.setD9(data.get(i).getD9());
                _normalizeItem.setD10(data.get(i).getD10());
                _normalizeItem.setD11(data.get(i).getD11());
                _normalizeItem.setD12(data.get(i).getD12());
                _normalizeItem.setD13(data.get(i).getD13());
                _normalizeItem.setD14(data.get(i).getD14());
                _normalizeItem.setD15(data.get(i).getD15());
                _normalizeItem.setD16(data.get(i).getD16());
                _normalizeItem.setD17(data.get(i).getD17());
                _normalizeItem.setD18(data.get(i).getD18());
                _normalizeItem.setD19(data.get(i).getD19());
                _normalizeItem.setD20(data.get(i).getD20());
                _normalizeItem.setD21(data.get(i).getD21());
                _normalizeItem.setD22(data.get(i).getD22());
                _normalizeItem.setD23(data.get(i).getD23());
                _normalizeItem.setD24(data.get(i).getD24());
                _normalizeItem.setD25(data.get(i).getD25());
                _normalizeItem.setD26(data.get(i).getD26());
                _normalizeItem.setD27(data.get(i).getD27());
                _normalizeItem.setD28(data.get(i).getD28());
                _normalizeItem.setD29(data.get(i).getD29());
                _normalizeItem.setD30(data.get(i).getD30());
                _normalizeItem.setD31(data.get(i).getD31());
                _normalizeItem.setD32(data.get(i).getD32());
                _normalizeItem.setD33(data.get(i).getD33());
                _normalizeItem.setD34(data.get(i).getD34());
                _normalizeItem.setD50(data.get(i).getD50());

                _lstNormalizeData.add(_normalizeItem);
            }

            if (sourceFlag.equals("0")) {

            } else {
                return _service.updateClhMembers("BO_DI_KHOI_DP", posCode, "S", _reportDate, makerId, authoriseId, _lstNormalizeData);

            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int clearMembers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, String customerCode, String sourceFlag) {
        try {
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            if (sourceFlag.equals("0")) {

            } else {
                return _service.clearClhMembers("BO_DI_KHOI_DP", posCode, "S", _reportDate, makerId, authoriseId, customerCode);

            }
            //Kiểm tra xem check nào được chọn
            //Lấy giá trị từ mảng 
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int clearCustomersLeave(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
        try {
            //Kiểm tra xem check nào được chọn
            String mKey = "", mcheck = "", mposCode = "", mposFlag = "";
            List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            DuLieuNTService ser = new DuLieuNTService();
            for (int i = 0; i < data.size(); i++) {
                mcheck = data.get(i).getManualFlag();
                if (mcheck.equalsIgnoreCase("1")) {
                    mposCode = data.get(i).getPosCode();
                    mKey = data.get(i).getD11();
                    mposFlag = "S";

                    int APICheck = ser.DeleteCustomers("BO_DI_KHOI_DP", mposCode, "S", _reportDate, "", "", _lstNormalizeData);
                    System.out.println("API trả về lỗi  <> 200 = " + APICheck);
                    if (sourceFlag.equals("0")) {
                    } else {
                        return ser.DeleteCustomers("BO_DI_KHOI_DP", mposCode, "S", _reportDate, "", "", _lstNormalizeData);
                    }
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int suggestDeleteCustomers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
        try {
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            if (sourceFlag.equals("0")) {

            } else {
                List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();

                    _normalizeItem.setKey("BO_DI_KHOI_DP");
                    _normalizeItem.setOrderValue(i + 1);
                    _normalizeItem.setOrderDescription(String.format("%d", i + 1));
                    if (data.get(i).getD20().equals("HOVAY")) {
                        _normalizeItem.setCode(data.get(i).getD11() + "_" + String.format("%02d", 0));
                    } else {
                        _normalizeItem.setCode(data.get(i).getD11() + "_" + String.format("%02d", i + 1));
                    }
                    _normalizeItem.setName("");
                    _normalizeItem.setReportYear(2050);
                    _normalizeItem.setPosCode(data.get(i).getD5());
                    _normalizeItem.setPosFlag("S");
                    _normalizeItem.setBranchCode(data.get(i).getD3());
                    _normalizeItem.setD1(data.get(i).getD1());
                    _normalizeItem.setD2(data.get(i).getD2());
                    _normalizeItem.setD3(data.get(i).getD3());
                    _normalizeItem.setD4(data.get(i).getD4());
                    _normalizeItem.setD5(data.get(i).getD5());
                    _normalizeItem.setD6(data.get(i).getD6());
                    _normalizeItem.setD7(data.get(i).getD7());
                    _normalizeItem.setD8(data.get(i).getD8());
                    _normalizeItem.setD9(data.get(i).getD9());
                    _normalizeItem.setD10(data.get(i).getD10());
                    _normalizeItem.setD11(data.get(i).getD11());
                    _normalizeItem.setD12(data.get(i).getD12());
                    _normalizeItem.setD13(data.get(i).getD13());
                    _normalizeItem.setD14(data.get(i).getD14());
                    _normalizeItem.setD15(data.get(i).getD15());
                    _normalizeItem.setD16(data.get(i).getD16());
                    _normalizeItem.setD17(data.get(i).getD17());
                    _normalizeItem.setD18(data.get(i).getD18());
                    _normalizeItem.setD19(data.get(i).getD19());
                    _normalizeItem.setD20(data.get(i).getD20());
                    _normalizeItem.setD21(data.get(i).getD21());
                    _normalizeItem.setD22(data.get(i).getD22());
                    _normalizeItem.setD23(data.get(i).getD23());
                    _normalizeItem.setD24(data.get(i).getD24());
                    _normalizeItem.setD25(data.get(i).getD25());
                    _normalizeItem.setD26(data.get(i).getD26());
                    _normalizeItem.setD27(data.get(i).getD27());
                    _normalizeItem.setD28(data.get(i).getD28());
                    _normalizeItem.setD29(data.get(i).getD29());
                    _normalizeItem.setD30(data.get(i).getD30());
                    _normalizeItem.setD31(data.get(i).getD31());
                    _normalizeItem.setD32(data.get(i).getD32());
                    _normalizeItem.setD33(data.get(i).getD33());
                    _normalizeItem.setD34(data.get(i).getD34());
                    _normalizeItem.setD50(data.get(i).getD50());

                    _lstNormalizeData.add(_normalizeItem);
                }
                return _service.suggestDeleteClhCustomers("BO_DI_KHOI_DP", posCode, "S", _reportDate, makerId, authoriseId, _lstNormalizeData);

            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public List<PosClass> getDonvi(String flag) {
        epsModel dao = new epsModel();
        if (flag.equals("S")) {
            return dao.getDonvi("ALL_POS", "");
        } else if (flag.equals("M")) {
            return dao.getDonvi("ALL_MAIN_POS", "");
        } else {
            return null;
        }
    }
}
