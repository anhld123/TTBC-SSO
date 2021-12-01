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
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.InvestorModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class NDT2021 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    DuLieuNTService service;

    @Override
    public String load() {
        try {
            System.err.println("NDT2021");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
//            Connection conn = new DaoConnect().getConnect();
//            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
//            setThangbc(dateStr);
//
//            setLstHinhthucTNHS(daoMain.getCanBo(UserName, "PHANLOAIRPA"));
//            setLstNgayluongHD(daoMain.getCanBo(UserName, "XACNHANSL"));
//            lstDulieuNt = daoMain.getDataHTLS2021(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
//            if (conn != null) {
//                conn.close();
//            }
             posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            service = new DuLieuNTService();
               System.err.println("service");
               System.err.println(pos_cd_username + " - lstData - " +dateStr);
            ArrayList<InvestorModel> lstData = service.getDataNDT2021(pos_cd_username, dateStr);
            
            int i =1;
            for (InvestorModel item : lstData) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                row.setKHOA("NDT2021_001");
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
                row.setD1(item.getInvestorCode());
                row.setD2(item.getInvestorName());
                row.setD3(item.getSpecificProductCode());
                
                row.setD4(item.getSpecificProductName());
                row.setD5(item.getMakerId());
                row.setD6(item.getMakerDate());
                row.setD7(item.getUpdateId());
                row.setD8(item.getUpdateDate());
                row.setD9(item.getStatus());

                lstDulieuNt.add(row);

            }  
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NDT2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NDT2021: " + e.getMessage());
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
            if (lstDulieuNt == null || lstDulieuNt.size() == 0 || lstCombox.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

//            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            Date date1=new SimpleDateFormat("dd-MMM-yyyy").parse(sngaybc); 
            
//            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
            String strDate = dateFormat.format(date1); 
            service = new DuLieuNTService();
            ArrayList<InvestorModel> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                InvestorModel tempadd = new InvestorModel();
                if (findStringInList(tmp.getD1() + tmp.getD3(),lstCombox) >0) {
//                    tempadd.setMAPGD(INPUT);
                    tempadd.setMainPos(tmp.getMACN());
                    tempadd.setPosCode(tmp.getMAPGD());
//                    tempadd.setReportDate("20211031");
                    tempadd.setInvestorCode(tmp.getD1());
                    tempadd.setSpecificProductCode(tmp.getD3());
                    tempadd.setStatus("1");
                    lstUpdateDate.add(tempadd);
                }
                else
                {
                    tempadd.setMainPos(tmp.getMACN());
                    tempadd.setPosCode(tmp.getMAPGD());
//                    tempadd.setReportDate("20211031");
                    tempadd.setInvestorCode(tmp.getD1());
                    tempadd.setSpecificProductCode(tmp.getD3());
                    tempadd.setStatus("0");
                    lstUpdateDate.add(tempadd);
                }
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            int status = service.updateData2021Ndt(pos_cd_username, strDate,UserName, lstUpdateDate);

//            if (!daoMain.saveNtmoi01("NTMOI_001", UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, lstCombox)) {
//                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
//                return ERROR;
//            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public int findStringInList(
            String name, List<QT_DULIEU_NT> customers) {
        for (QT_DULIEU_NT customer : customers) {
            if (customer == null)
            {
                continue;
            }
            if (customer.getD1().equals(name)) {
                return 1;
            }
        }
        return 0;
    }

}
