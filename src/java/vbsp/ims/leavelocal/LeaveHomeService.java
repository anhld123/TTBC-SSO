/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.leavelocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import vbsp.ims.restapi.*;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import java.util.List;
import vbsp.ims.log.CoreLogger;
import java.util.ArrayList;
import vbsp.ims.dao.*;

/**
 *
 * @author HP
 */
public class LeaveHomeService {

    DuLieuNTService _service = new DuLieuNTService();

    //gradeAuthor = 1 tạo lập; 2 phê duyệt
//    typeAuth = 1 phê duyệt cho xoa; 2 phê duyệt cho XLN
    public List<DuLieuNTRow> getCustomers(String posCode, String posFlag, String customerCode, String fromDate, String toDate, String sourceFlag, String openFlag,
            String gradeAuthor, String typeAuth) {
        //gradeAuthor = 1 tạo lập; 2 phê duyệt
        if (gradeAuthor.equals("1")) {
            if (sourceFlag.equals("0")) {

            } else {
                try {
                    final String _fromDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate));
                    final String _toDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(toDate));

                    if (customerCode == null || customerCode.isEmpty()) {
                        return _service.getClhCustomers("BO_DI_KHOI_DP", posCode, "S", "", _fromDate, _toDate, openFlag);
                    } else {
                        return _service.getClhCustomers("BO_DI_KHOI_DP", posCode, "S", customerCode, _fromDate, _toDate, openFlag);
                    }
                } catch (Exception e) {
                    return null;
                }
            }
        } else {
            try {
                final String _fromDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate));
                final String _toDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(toDate));
                List<DuLieuNTRow> lstTmp = new ArrayList<>();
                List<DuLieuNTRow> lstReturn = new ArrayList<>();
                if (customerCode == null || customerCode.isEmpty()) {
                    lstReturn = _service.getClhCustomers("BO_DI_KHOI_DP", posCode, "S", "", _fromDate, _toDate, openFlag);
                } else {
                    lstReturn = _service.getClhCustomers("BO_DI_KHOI_DP", posCode, "S", customerCode, _fromDate, _toDate, openFlag);
                }
                System.err.println("lstReturn = " + lstReturn.size());
                if (typeAuth.equals("1")) {
                    for (DuLieuNTRow item : lstReturn) {
                        if (item.getD20().equals("HOVAY") && (item.getD36() == null ? "0" : item.getD36()).equals("1")) {
                            lstTmp.add(item);
                        }
                    }
                } else {
                    if (customerCode == null || customerCode.isEmpty()) {
                        lstReturn = _service.getDelete("BO_DI_KHOI_DP", posCode, "S", "", _fromDate, _toDate);
                    } else {
                        lstReturn = _service.getDelete("BO_DI_KHOI_DP", posCode, "S", customerCode, _fromDate, _toDate);
                    }
                    System.err.println("lstReturn = " + lstReturn.size());
                    if (typeAuth.equals("3")) {
                        for (DuLieuNTRow item : lstReturn) {
                            System.err.println("lstReturn D12  = " + item.getD12() + item.getD50());
                            if (item.getD20().equals("HOVAY") && (item.getD50() == null ? "0" : item.getD50()).equals("3")) {
                                lstTmp.add(item);
                            }
                        }
                    }
                }
                return lstTmp;
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public int saveCustomers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
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
                    _normalizeItem.setD35(data.get(i).getD35());
                    _normalizeItem.setD36(data.get(i).getD36());
                    _normalizeItem.setD37(data.get(i).getD37());
                    _normalizeItem.setD38(data.get(i).getD38());
                    _normalizeItem.setD39(data.get(i).getD39());
                    _normalizeItem.setD40(data.get(i).getD40());
                    _normalizeItem.setD50(data.get(i).getD50());

                    _lstNormalizeData.add(_normalizeItem);
                }
                return _service.updateClhCustomers("BO_DI_KHOI_DP", posCode, "S", _reportDate, makerId, authoriseId, _lstNormalizeData);

            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int sendCustomers(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data) {
        try {
            int _result = saveCustomers(posCode, posFlag, reportDate, makerId, authoriseId, data, "1");
            if (_result == 200) {
                LeaveHomeDao _leaveHomeDao = new LeaveHomeDao();
                int _status = _leaveHomeDao.updateUploadExcelDataStatus(posCode, posFlag, reportDate, makerId, authoriseId, data, "2");
                if (_status > 0) {
                    return _result;
                } else {
                    return 0;
                }
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

    public List<DuLieuNTRow> getUploadExcelData(String user, String posCode, String reportDate, String customerCode, String fromDate, String toDate, String type) {

        List<DuLieuNTRow> _lstData = new ArrayList();
        try {
            LeaveHomeDao _leaveHomeDao = new LeaveHomeDao();
            _lstData = _leaveHomeDao.getUploadExcelData(user, posCode, reportDate, customerCode, fromDate, toDate, type);
        } catch (Exception ex) {
            CoreLogger.error(LeaveHomeService.class.getName() + " getUploadExcelData -> " + ex.getMessage());
        }
        return _lstData;
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
