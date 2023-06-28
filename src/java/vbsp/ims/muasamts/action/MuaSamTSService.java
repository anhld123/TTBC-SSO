/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.muasamts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

/**
 *
 * @author HP
 */
public class MuaSamTSService {

    DuLieuNTService _service = new DuLieuNTService();

    public List<DuLieuNTRow> getAssetsPlanList(String posCode, String posFlag, String reportDate) {
        try {
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            List<DuLieuNTRow> _lstData = _service.getData("KTTC_MUASAM_TS_001", posCode, "M", _reportDate);
            return _lstData;
        } catch (Exception e) {
            return null;
        }
    }

    public int saveData(String posCode, String posFlag, String reportDate, String makerId, String authoriseId,
            List<DuLieuNTRow> data, String sourceFlag) {        
        try {
            final String _oracleReportDateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(reportDate));
            if (sourceFlag.equals("0")) {
              MuaSamTSDao _muaSamTSDao = new MuaSamTSDao();
              int _recordCnt = _muaSamTSDao.saveData(posCode, posFlag, _oracleReportDateStr, makerId, authoriseId, data);
              return _recordCnt > 0 ? 200 : 0;
            } else {
                Date _reportDate = new SimpleDateFormat("dd/MM/yyyy").parse(reportDate);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date _curDate = new Date();
                ArrayList<DuLieuNTRow> _data = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    DuLieuNTRow _newItem = data.get(i);
                    _newItem.setName(_newItem.getD1());
                    _newItem.setReportYear(_reportDate.getYear() + 1900);
                    _newItem.setBranchCode(posCode);
                    _newItem.setMakerId(makerId);
                    //_newItem.setMakerDate(formatter.format(_curDate));
                    _newItem.setAuthoriseId(makerId);
                    //_newItem.setAuthoriseDate(formatter.format(_curDate));
                    _data.add(_newItem);
                }
                final String _reportDateStr = new SimpleDateFormat("yyyyMMdd").format(_reportDate);
                return _service.updateData("KTTC_MUASAM_TS_001", posCode, "M", _reportDateStr, makerId, authoriseId, _data);
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public List<PosClass> getDonvi(String flag, String userName) {
        epsModel dao = new epsModel();
        if (flag.equals("AS")) {
            return dao.getDonvi("ALL_POS", "");
        } else if (flag.equals("AM")) {
            return dao.getDonvi("ALL_MAIN_POS", "");
        } else {
            return dao.getDonvi(flag, userName);
        }
    }

    public List<ListOfValue> getAssetGroupList() {
        return _service.getListOfValue("107", "");
    }

    public int deleteData(String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data, String sourceFlag) {
        try {
            final String _oracleReportDateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(reportDate));
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd/MM/yyyy").parse(reportDate));
            if (sourceFlag.equals("0")) {
                MuaSamTSDao _muaSamTSDao = new MuaSamTSDao();
              int _recordCnt = _muaSamTSDao.deleteData(posCode, posFlag, _oracleReportDateStr, makerId, authoriseId, data);
              return _recordCnt > 0 ? 200 : 0;
            } else {
                List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();
                    _normalizeItem.setKey(data.get(i).getKey());
                    _normalizeItem.setOrderValue(i + 1);
                    _normalizeItem.setOrderDescription(String.format("%d", i + 1));
                    _normalizeItem.setCode(data.get(i).getCode());
                    _normalizeItem.setName(data.get(i).getName());
                    _normalizeItem.setReportYear(data.get(i).getReportYear());
                    _normalizeItem.setPosCode(data.get(i).getPosCode());
                    _normalizeItem.setPosFlag(data.get(i).getPosFlag());
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
                    
                    _lstNormalizeData.add(_normalizeItem);
                }
                return _service.deleteManualData("KTTC_MUASAM_TS_001", posCode, "M", _reportDate, makerId, authoriseId, _lstNormalizeData);
            }
        } catch (Exception e) {
            return 0;
        }        
    }
}
