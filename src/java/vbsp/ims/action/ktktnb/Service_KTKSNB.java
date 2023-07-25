/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.ktktnb;

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
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author HP
 */
public class Service_KTKSNB {

    DuLieuNTService _service = new DuLieuNTService();

    public List<QT_DULIEU_NT> getDataKTKSNB(String khoa, String posCode, String posFlag, String repportDate, String condition, String defaultListFlag) {

        try {
            List<QT_DULIEU_NT> dulieuNt = new ArrayList<>();
            final String _rpDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(repportDate));
            {
                List<DuLieuNTRow> lstData = new ArrayList<>();
                lstData = _service.getDataKTKSNB(khoa, posCode, posFlag, _rpDate, condition, defaultListFlag);
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
                    row.setMAPGD(item.getPosCode());
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
                    row.setD3(item.getD3());
                    row.setD4(item.getD4());
                    row.setD5(item.getD5());
                    row.setD6(item.getD6());
                    row.setD7(item.getD7());
                    row.setD8(item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setD13(item.getD13());
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
                    row.setD30(item.getD30());
                    row.setD50(item.getD50());
                    row.setNHAPTAY(item.getManualFlag());
                    row.setFONTFORMAT(item.getFontFormat());
                    row.setKIEUIN(item.getStyle());
                    dulieuNt.add(row);
                }
            }
            return dulieuNt;
        } catch (Exception e) {
            return null;
        }
    }

    public int saveDataKTKSNB(String key, String posCode, String posFlag, String reportDate, String makerId, String authoriseId, List<DuLieuNTRow> data) {
        try {

            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date date = new Date();
            String dateFormat = formatter.format(date);

            List<DuLieuNTRowX> _lstNormalizeData = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                DuLieuNTRowX _normalizeItem = new DuLieuNTRowX();

                _normalizeItem.setKey(data.get(i).getKey());
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
                _normalizeItem.setD50(data.get(i).getD50());

                _lstNormalizeData.add(_normalizeItem);
            }
            return _service.updateKTKSNB(key, posCode, posFlag, _reportDate, makerId, authoriseId, _lstNormalizeData);

        } catch (Exception e) {
            return 0;
        }        
    }
}
