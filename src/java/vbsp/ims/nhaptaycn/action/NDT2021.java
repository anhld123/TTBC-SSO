/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
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
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class NDT2021 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    DuLieuNTService service;
    
    @Override
    public String load(){
         try {
//            System.err.println("QD23_001");
            if (!getParaSession()) {
                return ERROR;
            }
            service = new DuLieuNTService();
            ArrayList<IntDeductionModel> lstData = service.getDataHTLS2021("001801", "20211031", "01", "","");
            int i =1;
            for (IntDeductionModel item : lstData) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                row.setKHOA("ID_001");
                row.setTHUTU(i);          
                i++;
//                row.setTT_HIENTHI(item.getOrderDescription());
//                row.setMA(item.getCode());
//                row.setTEN(item.getName());
                
                Date reportDate = DateUtil.toDate( item.getReportDate());
                row.setNGAYBC(reportDate);
                //row.setNAMBC(item.getReportYear());
                row.setMAPGD(item.getPosCode());
//                row.setCO_TONGHOP(item.getPosFlag());
                row.setMACN(item.getMainPos());
//                row.setNGUOI_NHAP(item.getMakerId());
                //row.setNGAY_NHAP(item.getMakerDate());
//                Date makerDate = DateUtil.toDate( item.getMakerDate());
//                row.setNGAY_NHAP(makerDate);
//                row.setNGUOI_DUYET(item.getAuthoriseId());
                //row.setNGAY_DUYET(item.getAuthoriseDate());
//                Date authoriseDate = DateUtil.toDate( item.getAuthoriseDate());
//                row.setNGAY_DUYET(authoriseDate);
                row.setD1(item.getGroupId());
                row.setD2(item.getCustomerId());
                row.setD3(item.getLoanId());
                row.setD4(Float.toString(item.getPrinTotal()));
                row.setD5(Float.toString(item.getNormalAmt()));
                row.setD6(Float.toString(item.getOverdueAmt()));
                row.setD7(Float.toString(item.getFreezeAmt()));
                row.setD8(Float.toString(item.getInterestRate()));
                row.setD9(item.getLoanProgram());
                row.setD10(item.getSpecificProductCode());
                row.setD11(item.getDecisionCode());
                row.setD12(item.getLoanStatus());
                row.setD13(item.getCapitalSourceCode());
                row.setD14(item.getInvestorCode());
                row.setD15(item.getCasaAccount());
                row.setD16(Float.toString(item.getIntTotalAmt()));
                
                row.setD17(Float.toString(item.getIntDeductionTotalAmt()));
                row.setD18(Float.toString(item.getIntDeductionM10Amt()));
                row.setD19(Float.toString(item.getIntDeductionM11Amt()));
                row.setD20(Float.toString(item.getIntDeductionM12Amt()));
                row.setD21(Float.toString(item.getIntDeductionAdjustM10Amt()));
                row.setD22(Float.toString(item.getIntDeductionAdjustM11Amt()));
                row.setD23(Float.toString(item.getIntDeductionAdjustM12Amt()));
                
                row.setD24(item.getPaymentFlag());
                row.setD25(item.getIntConfirmFlag());
                row.setD26(item.getDeductionTranRef());
                row.setD27(item.getDeductionTranDate());
                
                row.setD28(Float.toString(item.getAccountingIntAmt()));
                row.setD29(Float.toString(item.getRpaAmt()));
                row.setD30(Float.toString(item.getCasaAmt()));
                row.setD31(Float.toString(item.getCashAmt()));
                
                row.setD32(item.getPosTranRef());
                row.setD33(item.getM10Status());
                row.setD34(item.getM11Status());
                row.setD35(item.getM12Status());
                row.setD36(item.getPaymentFlag());
                row.setD37(item.getIntConfirmFlag());
                row.setD38(item.getDeductionTranRef());
                row.setD39(item.getDeductionTranDate());
                
                row.setD43(Float.toString(item.getIntTotalM10Amt()));
                row.setD44(Float.toString(item.getIntTotalM11Amt()));
                row.setD45(Float.toString(item.getIntTotalM12Amt()));
                row.setD46(item.getCommuneId());    
                
//                row.setNHAPTAY(item.getManualFlag());
//                row.setFONTFORMAT(item.getFontFormat());
//                row.setKIEUIN(item.getStyle());
                if (i>10)
                {
                    continue;
                }
                lstDulieuNt.add(row);
                
            }  
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
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
           if (lstDulieuNt == null || lstDulieuNt.size() == 0 || lstCombox.size() == 0 ) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();   
            
            String iCheck = daoMain.checkData_Info(lstCombox,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat);
            if(!iCheck.equals("XXXAAA"))
            {
                addActionError("Lỗi! "+ iCheck);
                    return ERROR; 
            }     
            
            
            if(!daoMain.saveNtmoi01("NTMOI_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, lstCombox))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }     
    
    
}
