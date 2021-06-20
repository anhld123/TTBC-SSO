/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class BCQT_M01 extends ActionBcqtMain 
implements BcqtFunction
{
    private String valTonQuySS;
    @Override
    public String load(){
        try {
            System.err.println("BCQT_M01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            lstDulieuNt = daoMain.getDataM01(khoa_bcqt,hmParameter.get("ngay_bc").toString(),poscd,Grade,UserName);
            valTonQuySS = String.format ("%f",daoMain.getValueTQSSMS01(
                    khoa_bcqt,hmParameter.get("ngay_bc").toString(),poscd,Grade,UserName));
            System.err.println("ton quy lay tu dong:"+ valTonQuySS);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_M01: " + e.getMessage());
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
//            lstDulieuNt.removeAll(null);
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }

            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqtM01(khoa_bcqt, UserName, "000401", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_PL01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_PL01: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String getValTonQuySS() {
        return valTonQuySS;
    }

    public void setValTonQuySS(String valTonQuySS) {
        this.valTonQuySS = valTonQuySS;
    }

    
    
}
